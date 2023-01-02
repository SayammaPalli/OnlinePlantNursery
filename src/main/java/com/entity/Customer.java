package com.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;


@Entity
@Table(name="CUSTOMERS")
public class Customer extends User {

	@NotBlank
	@Size(min = 3, message = "Name should be atleast three characters")
	private String name;

	@NotNull
	private String phone;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();

	public Customer() {
		super();
	}

	public Customer(Integer id, @NotBlank @Email(message = "Enter a valid Email") String email,
			@NotBlank String password, Role role,
			@NotBlank @Size(min = 3, message = "Name should be atleast three characters") String name,
			@NotNull String phone, Address address) {
		super(id, email, password, role);
		this.name = name;
		this.phone = phone;
		this.address = address;

	}

	public Customer(Integer id, @NotBlank @Email(message = "Enter a valid Email") String email, Role role,
			@NotBlank @Size(min = 3, message = "Name should be atleast three characters") String name,
			@NotNull String phone, Address address) {
		super(id, email, role);
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public Customer(@NotBlank @Email(message = "Enter a valid Email") String email, @NotBlank String password,
			Role role, @NotBlank @Size(min = 3, message = "Name should be atleast three characters") String name,
			@NotNull String phone, Address address) {
		super(email, password, role);
		this.name = name;
		this.phone = phone;
		this.address = address;

	}

	public Customer(@NotBlank @Size(min = 3, message = "Name should be atleast three characters") String name,
			@NotNull String phone, Address address) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	
	public Customer(Integer id) {
	super(id);
}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", phone=" + phone + ", address=" + address + ", orders=" + orders + "]";
	}

}