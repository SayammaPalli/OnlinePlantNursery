package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Plant;
import com.entity.Planter;
import com.entity.Product;
import com.entity.Seed;
import com.entity.Type;
import com.exceptions.ResourceNotFoundException;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private IPlanterService planterService;
	
	@Autowired
	private IPlantService plantService;
	
	@Autowired
	private ISeedService seedService;

	@Override
	public Product addProduct(Product product) {
		if (product instanceof Planter) {
			return planterService.addPlanter((Planter)product);
		}else if(product instanceof Seed) {
			return seedService.addSeed((Seed)product);
		}
		return plantService.addNewPlant((Plant)product);
	}
	

	@Override
	public List<Product> getProducts() {
		List<Product> list = new ArrayList<Product>();
		List<Planter> planterList = planterService.getPlanters();
		List<Plant> plantList = plantService.getAllPlants();
		List<Seed> seedList = seedService.getSeeds();
		for(Planter p:planterList) {
			list.add((Product)p);
		}
		for(Plant p:plantList) {
			list.add((Product)p);
		}
		for(Seed s:seedList) {
			list.add((Product)s);
		}
		if (list.size() == 0)
			throw new ResourceNotFoundException("No resource found");
		return list;
}

	@Override
	public Product updateProduct(Product product) {
		if (product instanceof Planter) {
			return planterService.updatePlanter((Planter)product);
		}else if(product instanceof Seed) {
			return seedService.updateSeed((Seed)product);
		}
		return plantService.updatePlant((Plant)product, product.getId());
	}

	@Override
	public Product deleteProduct(Product product) {
		if (product instanceof Planter) {
			return planterService.deletePlanter((Planter)product);
		}else if(product instanceof Seed) {
			return seedService.deleteSeed((Seed)product);
		}
		return plantService.deletePlant(product.getId());
	}

	@Override
	public Product getProductById(int productId) {
		List<Product> products = getProducts().stream().filter((p) -> p.getId() == productId).collect(Collectors.toList());
		if (products.size() == 0)
			throw new ResourceNotFoundException("No product found with the given id");
		return products.get(0);
	}
	
	
	@Override
	public List<Product> costLowToHigh() {
		List<Product> allProducts = getProducts();
		List<Product> sortedProducts = allProducts.stream().sorted((Product o1,Product o2) -> o1.getCost() - o2.getCost()).collect(Collectors.toList());
		return sortedProducts;
	}
	
	@Override
	public List<Product> costHighToLow() {
		List<Product> allProducts = getProducts();
		List<Product> sortedProducts = allProducts.stream().sorted((Product o1,Product o2) -> o2.getCost() - o1.getCost()).collect(Collectors.toList());
		return sortedProducts;
	}
	
	@Override
	public List<Product> filterByType(Type type) {
		List<Product> allProducts = getProducts();
		List<Product> requiredProducts = allProducts.stream().filter((p) -> p.getType() == type).collect(Collectors.toList());
		return requiredProducts;	
	}
	
	@Override
	public List<Product> getProducts(double minCost, double maxCost) {
		List<Product> allProducts = getProducts();
		List<Product> requiredProducts = allProducts.stream().filter((p) -> p.getCost() >minCost && p.getCost() < maxCost).collect(Collectors.toList());
		return requiredProducts;
	}
	
	
}
