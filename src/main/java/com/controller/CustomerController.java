package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Customer;
import com.entity.Order;
import com.entity.Product;
import com.entity.User;
import com.service.ICustomerService;
import com.service.IProductService;
import com.service.IUserService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IUserService userService;

	@Autowired
	IProductService productService;


	@PostMapping("/register")
	public ResponseEntity<User> register(@Valid @RequestBody Customer customer) {

		return new ResponseEntity<>(userService.register(customer), HttpStatus.CREATED);
	}

	@PostMapping("/account")
	public ResponseEntity<String> login(@RequestBody User user) {

		return new ResponseEntity<String>("Welcome " + ((Customer) userService.login(user)).getName(),
				HttpStatus.ACCEPTED);

	}

//	@PatchMapping("/resetPassword/{id}")
//	public ResponseEntity<String> resetPassword(@Valid @PathVariable Integer id,
//			@RequestBody Map<Object, Object> fields) {
//
//		return new ResponseEntity<>("Your password is successfully updated "
//				+ ((Customer) userService.resetPasswordById(id, fields)).getName(), HttpStatus.CREATED);
//	}

	@PutMapping("/id/{id}")
	public ResponseEntity<Customer> update(@Valid @PathVariable Integer id, @RequestBody Customer customer) {

		return new ResponseEntity<Customer>((Customer)userService.updateUser(id, customer), HttpStatus.CREATED);
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<Customer> deleteById(@PathVariable Integer id) {

		return new ResponseEntity<Customer>(customerService.deleteCustomer(id), HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {

		return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {

		return new ResponseEntity<List<Product>>(productService.getProducts(), HttpStatus.OK);
	}


	@GetMapping("/orders/id/{id}")
	public ResponseEntity<List<Order>> getByCustomerId(@PathVariable Integer id) {

		return new ResponseEntity<>(customerService.getOrders(id), HttpStatus.OK);
	}

	@GetMapping("/orders/{customerId}/{orderId}")
	public ResponseEntity<Order> getBycustomerIdAndOrderId(@PathVariable Integer customerId,
			@PathVariable Integer orderId) {

		return new ResponseEntity<>(customerService.getOrderDetails(customerId, orderId), HttpStatus.OK);
	}

}