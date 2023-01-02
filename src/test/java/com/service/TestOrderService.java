package com.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.entity.Customer;
import com.entity.Order;
import com.entity.Product;
import com.entity.TransactionMode;
import com.entity.Type;
import com.repository.IOrderRepository;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class TestOrderService {

	@Mock
	IOrderRepository orderRepoMock;

	@InjectMocks
	OrderServiceImpl orderServiceMock;

	static Order order1;
	static Order order2;
	static List<Order> orders = new ArrayList<>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Product prod = new Product(1, 20, 100, Type.PLANT);
		Map<Integer, Integer> prodMap = new HashMap<>(); 
		prodMap.put(prod.getId(), 5);
		prodMap.put(prod.getId(), 10);
		
		Customer cust = new Customer(1);
		  
		order1 = new Order(1, LocalDate.now(), TransactionMode.CARD, 15, 300, prodMap, cust);
		order2 = new Order(2, LocalDate.now(), TransactionMode.CASH, 15, 300, prodMap, cust);
		 
		orders.add(order1);
		orders.add(order2);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {//id, cost, stock, type
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	// @Order(2)
	// @Disabled
	@Test
	public void updateOrderTest() {
		when(orderRepoMock.findById(2)).thenReturn(Optional.of(order2));
		Order updateOrder2 = new Order(2, TransactionMode.UPI);
		when(orderRepoMock.save(order2)).thenReturn(order2);
		assertEquals(TransactionMode.UPI, orderServiceMock.patchOrder(updateOrder2).getTransactionMode());

	}
	
	// @Order(3)
	// @Disabled
	@Test
	public void deleteOrderTest() {
		when(orderRepoMock.findById(1)).thenReturn(Optional.of(order1));
		assertEquals(order1, orderServiceMock.deleteOrder(1));
	}

	// @Order(4)
	// @Disabled
	@Test
	public void viewOrderTest() {
		when(orderRepoMock.findById(1)).thenReturn(Optional.of(order1));
		assertEquals((Integer) 1, orderServiceMock.viewOrder(1).getBookingId());
	}

	// @Order(5)
	// @Disabled
	@Test
	public void viewAllOrdersTest() {
		when(orderRepoMock.findAll()).thenReturn(orders);
		assertEquals(2, orderServiceMock.viewAllOrders().size());
	}
	
	@Test
	public void sortOrderLowToHighTest() {
		when(orderRepoMock.findAll()).thenReturn(orders);
		assertEquals(300, (int) orderServiceMock.sortOrderLowToHigh().get(0).getTotalCost());
	}
	
	@Test
	public void sortOrderHighToLowTest() {
		when(orderRepoMock.findAll()).thenReturn(orders);
		assertEquals(300, (int) orderServiceMock.sortOrderHighToLow().get(0).getTotalCost());
	}

	@Test
	public void filterByTransactionModeTest() {
		when(orderRepoMock.findAll()).thenReturn(orders);
		assertEquals(TransactionMode.CARD, orderServiceMock.filterByTransactionMode(TransactionMode.CARD).get(0).getTransactionMode());
	}
}