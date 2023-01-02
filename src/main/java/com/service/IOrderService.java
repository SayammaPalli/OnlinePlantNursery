package com.service;

import java.util.List;

import com.entity.Order;
import com.entity.TransactionMode;



public interface IOrderService {
	Order addOrder(Order order);
	
	Order deleteOrder(int bookingId);
	
	Order viewOrder(int bookingId);
	
	List<Order> viewAllOrders();
	
	Order patchOrder(Order order);

	List<Order> sortOrderLowToHigh();

	List<Order> sortOrderHighToLow();

	List<Order> filterByTransactionMode(TransactionMode transactionMode);

	
}