package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.Customer;
import com.entity.Order;
import com.exceptions.ResourceNotFoundException;
import com.exceptions.UserNotFoundException;


@Service
public interface ICustomerService {
		
	
	Customer deleteCustomer(int customerId) throws UserNotFoundException;
	
	Customer getCustomer(int customerId) throws UserNotFoundException;
	
	List<Customer> getCustomers() throws UserNotFoundException;
		
	List<Order> getOrders(Integer id) throws UserNotFoundException,ResourceNotFoundException;
	
	Order getOrderDetails(Integer customerId, Integer orderId)throws UserNotFoundException,ResourceNotFoundException;
	
	
	
}