package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Order;

@Repository
public interface IOrderRepository  extends JpaRepository<Order, Integer> {
	

}