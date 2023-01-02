package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Plant;
import com.entity.Planter;
import com.entity.Product;
import com.entity.Seed;
import com.entity.Type;
import com.service.IProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	IProductService productService;	
	
	@PostMapping("admin/plantertype")
	public ResponseEntity<Product> addProduct(@RequestBody Planter planter) {
		Product product =  productService.addProduct(planter);
			return new ResponseEntity<Product>(product,HttpStatus.CREATED);
	}
	
	@PostMapping("admin/planttype")
	public ResponseEntity<Product> addProduct(@RequestBody Plant plant) {
		Product product =  productService.addProduct(plant);
			return new ResponseEntity<Product>(product,HttpStatus.CREATED);
	}
	
	@PostMapping("admin/seedtype")
	public ResponseEntity<Product> addProduct(@RequestBody Seed seed) {
		Product product =  productService.addProduct(seed);
			return new ResponseEntity<Product>(product,HttpStatus.CREATED);
	}
	
	@GetMapping({"admin/products", "customers/products"})
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products =  productService.getProducts();
			return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping({"admin/costLowToHigh", "customers/costLowToHigh"})
	public ResponseEntity<List<Product>> costLowToHigh() {
		List<Product> products =  productService.costLowToHigh();
			return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping({"admin/costHighToLow", "customers/costHighToLow"})
	public ResponseEntity<List<Product>> HighToLow() {
		List<Product> products =  productService.costHighToLow();
			return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping({"admin/filterByType/{type}", "customers/filterByType/{type}"})
	public ResponseEntity<List<Product>> filterByType(@PathVariable Type type) {
		List<Product> products =  productService.filterByType(type);
			return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping({"admin/{min}/{max}", "customers/{min}/{max}"})
	public ResponseEntity<List<Product>> getAllPlantersInRange(@PathVariable double min, @PathVariable double max ){
		List<Product> products = productService.getProducts(min, max);
		if ( products.size() != 0)
			return new ResponseEntity <List<Product>>(products,HttpStatus.OK);
		return new ResponseEntity <List<Product>>(products,HttpStatus.NOT_FOUND);
	}

}
