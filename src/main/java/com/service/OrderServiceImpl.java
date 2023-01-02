package com.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Order;
import com.entity.Plant;
import com.entity.Planter;
import com.entity.Product;
import com.entity.Seed;
import com.entity.TransactionMode;
import com.exceptions.OrderIdNotFoundException;
import com.repository.IOrderRepository;
import com.repository.IPlantRepository;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderRepository orderRepository;
	
	@Autowired 
	IPlantService plantService;
	  
	@Autowired 
	IPlantRepository plantRepository;
	  
	@Autowired 
	IPlanterService planterService;
	  
	@Autowired
	ISeedService seedService;
	
	@Autowired
	IProductService productService;
	
	@Override
	public Order addOrder(Order order) {
		Map<Integer, Integer> prod = order.getProducts();
		order.setBookingDate(LocalDate.now());
		int totalQuantity = 0;
		double totalCost = 0.0;
		for(Map.Entry<Integer, Integer> entry : prod.entrySet()) {
			Product product = productService.getProductById(entry.getKey());
			totalQuantity += entry.getValue();
			totalCost += product.getCost() * entry.getValue();
	
			if(product instanceof Plant)
				plantService.decreaseStock(entry.getKey(), entry.getValue());	
			else if(product instanceof Seed) 
				seedService.buySeeds(entry.getKey(), entry.getValue());
			else 
				planterService.removePlanterStock((Planter) product, entry.getValue());
		}
		order.setQuantity(totalQuantity);
		order.setTotalCost(totalCost);
		return orderRepository.save(order);
	}
	
	
	@Override
	public Order patchOrder(Order order) {
		Optional<Order> orderToBePatched = orderRepository.findById(order.getBookingId());
		if(orderToBePatched.isPresent() && order.getTransactionMode() != null) {
			orderToBePatched.get().setTransactionMode(order.getTransactionMode());
			orderRepository.save(orderToBePatched.get());
		}
		return orderToBePatched.orElseThrow(()->new OrderIdNotFoundException("Order with id: "+order.getBookingId()+" is not found"));
	}
	
	@Override
	public Order deleteOrder(int bookingId) {
		Optional<Order> orderToBeRemoved = orderRepository.findById(bookingId);
		if(orderToBeRemoved.isPresent()) {
			Map<Integer, Integer> prod = orderToBeRemoved.get().getProducts();
			for(Map.Entry<Integer, Integer> entry : prod.entrySet()) {
				Product product = productService.getProductById(entry.getKey());
				if(product instanceof Plant)
					plantService.addPlantStock(plantService.getPlant(entry.getKey()).getName(), entry.getValue());	
				else if(product instanceof Seed) 
					seedService.addStock(seedService.getSeed(entry.getKey()).getName(), entry.getValue());
				else 
					planterService.addPlanterStock(entry.getKey(), entry.getValue());
			}
			orderRepository.deleteById(bookingId);
		}
		return orderToBeRemoved.orElseThrow(()-> new OrderIdNotFoundException("Order with id: "+bookingId+" is not found"));
	}

	@Override
	public Order viewOrder(int bookingId){
		Optional<Order> orderOptional = orderRepository.findById(bookingId);
		return orderOptional.orElseThrow(()-> new OrderIdNotFoundException("Order with id: "+bookingId+" is not found"));
	}

	@Override
	public List<Order> viewAllOrders() {
		return orderRepository.findAll();
	}
	
	@Override
	public List<Order> sortOrderLowToHigh() {
		List<Order> orders = orderRepository.findAll();
		List<Order> sortedOrders = orders.stream().sorted((Order ord1, Order ord2) -> (int) ord1.getTotalCost() - (int) ord2.getTotalCost()).collect(Collectors.toList());
		return sortedOrders;
	}

	@Override
	public List<Order> sortOrderHighToLow() {
		List<Order> orders = orderRepository.findAll();
		List<Order> sortedOrders = orders.stream().sorted((Order ord1, Order ord2) -> (int)ord2.getTotalCost() - (int)ord1.getTotalCost()).collect(Collectors.toList());
		return sortedOrders;
	}
	
	@Override
	public List<Order> filterByTransactionMode(TransactionMode transactionMode) {
		List<Order> orders = orderRepository.findAll();
		List<Order> sameTransactionModeOrders = orders.stream().filter((t) -> t.getTransactionMode().equals(transactionMode)).collect(Collectors.toList());
		return sameTransactionModeOrders;
	}
	
}