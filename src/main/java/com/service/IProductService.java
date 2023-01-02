package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.Product;
import com.entity.Type;


@Service
public interface IProductService {
	
	Product addProduct(Product product);

	Product updateProduct(Product product);

	Product deleteProduct(Product product);

	Product getProductById(int productId);

	List<Product> getProducts();

	List<Product> costLowToHigh();

	List<Product> costHighToLow();

	List<Product> filterByType(Type type);

	List<Product> getProducts(double minCost, double maxCost);

}
