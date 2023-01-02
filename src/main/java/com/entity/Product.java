package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Positive(message = "The cost should be positive")
	private int cost;
	@Positive(message = "The stock should be positive")
	private int stock;
	@Enumerated(EnumType.STRING)
	private Type type;
	@Column(unique = true)
	@NotBlank(message = "Name is necessary")
	private String name;  //NotEmpty

	
	public Product(int cost, int stock, Type type) {
		super();
		this.cost = cost;
		this.stock = stock;
		this.type = type;
	}
	public Product(int id, int cost, int stock, Type type) {
		super();
		this.id = id;
		this.cost = cost;
		this.stock = stock;
		this.type = type;
	}
	public Product(int id, int cost, int stock) {
		super();
		this.id = id;
		this.cost = cost;
		this.stock = stock;
	}
	
	
	
	
	

	public Product(@Positive(message = "The cost should be positive") int cost,
			@Positive(message = "The stock should be positive") int stock,
			@NotBlank(message = "Name is necessary") String name) {
		super();
		this.cost = cost;
		this.stock = stock;
		this.name = name;
	}
	public Product(@Positive(message = "The cost should be positive") int cost,
			@Positive(message = "The stock should be positive") int stock, Type type,
			@NotBlank(message = "Name is necessary") String name) {
		super();
		this.cost = cost;
		this.stock = stock;
		this.type = type;
		this.name = name;
	}
	public Product(int id, @Positive(message = "The cost should be positive") int cost,
			@Positive(message = "The stock should be positive") int stock, Type type,
			@NotBlank(message = "Name is necessary") String name) {
		super();
		this.id = id;
		this.cost = cost;
		this.stock = stock;
		this.type = type;
		this.name = name;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Product() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public Product(int cost, int stock) {
		super();
		this.cost = cost;
		this.stock = stock;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", cost=" + cost + ", stock=" + stock + ", type=" + type + ", name=" + name + "]";
	}

	

}
