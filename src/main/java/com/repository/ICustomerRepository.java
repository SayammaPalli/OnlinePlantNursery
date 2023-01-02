package com.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Customer;


@Repository

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
	
	Optional<Customer> findByEmail(String email);
    
     Optional<Customer> findById(Integer id);
    
     
        
}