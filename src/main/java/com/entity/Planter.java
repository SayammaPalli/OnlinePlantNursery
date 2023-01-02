package com.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import com.sun.istack.NotNull;

@Entity
public class Planter extends Product{
	
	@Positive (message = "The height must be positive")
	private float height;
	
	@NotNull
	private String shape;
	private int capacity;
	private int drainageHoles;
	private String color;
			
	public Planter(@Positive(message = "The height must be positive") float height, String shape, int capacity,
			int drainageHoles, String color) {
		super();
		this.height = height;
		this.shape = shape;
		this.capacity = capacity;
		this.drainageHoles = drainageHoles;
		this.color = color;
	}

	public Planter(int cost, int stock, @Positive(message = "The height must be positive") float height, String shape,
			int capacity, int drainageHoles, String color) {
		super(cost, stock);
		this.height = height;
		this.shape = shape;
		this.capacity = capacity;
		this.drainageHoles = drainageHoles;
		this.color = color;
	}

	public Planter(int id, int cost, int stock, Type type,
			@Positive(message = "The height must be positive") float height, String shape, int capacity,
			int drainageHoles, String color) {
		super(id, cost, stock, type);
		this.height = height;
		this.shape = shape;
		this.capacity = capacity;
		this.drainageHoles = drainageHoles;
		this.color = color;
	}
	
	public Planter(int cost, int stock, Type type, @Positive(message = "The height must be positive") float height,
			String shape, int capacity, int drainageHoles, String color) {
		super(cost, stock, type);
		this.height = height;
		this.shape = shape;
		this.capacity = capacity;
		this.drainageHoles = drainageHoles;
		this.color = color;
	}
	
	

	public Planter(int id, @Positive(message = "The cost should be positive") int cost,
			@Positive(message = "The stock should be positive") int stock, Type type,
			@NotBlank(message = "Name is necessary") String name,
			@Positive(message = "The height must be positive") float height, String shape, int capacity,
			int drainageHoles, String color) {
		super(id, cost, stock, type, name);
		this.height = height;
		this.shape = shape;
		this.capacity = capacity;
		this.drainageHoles = drainageHoles;
		this.color = color;
	}

	public Planter(@Positive(message = "The cost should be positive") int cost,
			@Positive(message = "The stock should be positive") int stock, Type type,
			@NotBlank(message = "Name is necessary") String name,
			@Positive(message = "The height must be positive") float height, String shape, int capacity,
			int drainageHoles, String color) {
		super(cost, stock, type, name);
		this.height = height;
		this.shape = shape;
		this.capacity = capacity;
		this.drainageHoles = drainageHoles;
		this.color = color;
	}

	public Planter(@Positive(message = "The cost should be positive") int cost,
			@Positive(message = "The stock should be positive") int stock,
			@NotBlank(message = "Name is necessary") String name,
			@Positive(message = "The height must be positive") float height, String shape, int capacity,
			int drainageHoles, String color) {
		super(cost, stock, name);
		this.height = height;
		this.shape = shape;
		this.capacity = capacity;
		this.drainageHoles = drainageHoles;
		this.color = color;
	}

	public Planter(int id, @Positive(message = "The height must be positive") float height, String shape, int cost, int stock
			) {
		super(id, cost, stock);
		this.height = height;
		this.shape = shape;
	}

	public Planter() {
		super();
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getDrainageHoles() {
		return drainageHoles;
	}

	public void setDrainageHoles(int drainageHoles) {
		this.drainageHoles = drainageHoles;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Planter [height=" + height + ", shape=" + shape + ", capacity=" + capacity + ", drainageHoles="
				+ drainageHoles + ", color=" + color + ", getType()=" + getType() + ", getId()=" + getId()
				+ ", getCost()=" + getCost() + ", getStock()=" + getStock() + "]";
	}

}
