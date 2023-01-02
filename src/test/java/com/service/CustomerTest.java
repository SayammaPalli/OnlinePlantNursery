package com.service;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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

import com.entity.Address;
import com.entity.Customer;
import com.entity.Role;
import com.exceptions.UserNotFoundException;
import com.repository.ICustomerRepository;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CustomerTest {

	@Mock
	ICustomerRepository customerRepositoryMock;

	@InjectMocks
	CustomerServiceImpl customerServiceMock;
	
	 Customer c1, c2, c3;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		c1 = new Customer(1, "pavan@gmail.com", "Pavan@123", Role.CUSTOMER, "Pavan Kumar", "9182183522",
				new Address("2-31", "Sai nagar", "Ongole", "Andhra Pradesh", 523279));

		c2 = new Customer(2, "rahul@gmail.com", "rahul@123", Role.CUSTOMER, "Rahul sai", "9666635326",
				new Address("3-21", "sarathnagar", "kondepi", "Andhra Pradesh", 523276));

		c3 = new Customer(3, "kantu@gmail.com", "kantu@123", Role.CUSTOMER, "Kantu Kumar", "9846478462",
				new Address("3-22", "Jogipet", "Sangareddy", "Telangana", 532456));
	}

	@AfterEach
	void tearDown() throws Exception {
	}



	@Test
	public void deleteCustomerTest() {

		when(customerRepositoryMock.findById(2)).thenReturn(Optional.of(c2));
		assertEquals("kondepi", customerServiceMock.deleteCustomer(2).getAddress().getCity());

		Exception exception = assertThrows(UserNotFoundException.class, () -> customerServiceMock.deleteCustomer(12));
		assertTrue(exception.getMessage().contains("There are no customer having id:12"));

	}

	@Test
	public void getCustomerTest() {

		when(customerRepositoryMock.findById(2)).thenReturn(Optional.of(c2));
		assertEquals("Rahul sai", customerServiceMock.getCustomer(2).getName());

		Exception exception = assertThrows(UserNotFoundException.class, () -> customerServiceMock.getCustomer(11));
		assertTrue(exception.getMessage().contains("There are no customer having id:11"));

	}

	@Test
	public void getCustomersTest() {
		List<Customer> customers = new ArrayList<>();
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);

		when(customerRepositoryMock.findAll()).thenReturn(customers);
		assertEquals(3, customerServiceMock.getCustomers().size());

	}

	
}