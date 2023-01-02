package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Customer;
import com.entity.Order;
import com.exceptions.ResourceNotFoundException;
import com.exceptions.UserNotFoundException;
import com.repository.ICustomerRepository;


@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;

	// method to find the details of the customer using customer Id

	@Override
	public Customer getCustomer(int customerId) {
		Optional<Customer> findById = customerRepository.findById(customerId);

		return findById.orElseThrow(() -> new UserNotFoundException("There are no customer having id:" + customerId));
	}

	// method to get all the customers

	@Override
	public List<Customer> getCustomers() {

		if (customerRepository.findAll().isEmpty())
			throw new UserNotFoundException("There are no records");

		return customerRepository.findAll();

	}

	// method to delete the customer using customer Id

	@Override
	public Customer deleteCustomer(int customerId) {

		Optional<Customer> findById = customerRepository.findById(customerId);
		findById.orElseThrow(() -> new UserNotFoundException("There are no customer having id:" + customerId));

		customerRepository.deleteById(customerId);

		return findById.get();
	}

	// method to find the all the order details of the customer using customer Id
	@Override
	public List<Order> getOrders(Integer id) {

		Optional<Customer> findById = customerRepository.findById(id);
		Customer customer = findById.orElseThrow(() -> new UserNotFoundException("Customer not found:"));

		if (customer.getOrders().isEmpty())
			throw new ResourceNotFoundException("Order not found");
		return customer.getOrders();
	}

	// method to get the order details of a customerId and orderId

	@Override
	public Order getOrderDetails(Integer customerId, Integer orderId) {

		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new UserNotFoundException("Customer not found"));

		List<Order> collect = customer.getOrders().stream().filter(order -> order.getBookingId() == orderId)
				.collect(Collectors.toList());

		if (collect.isEmpty()) {
			throw new ResourceNotFoundException("Orders not found");
		}

		return collect.get(0);
	}

}