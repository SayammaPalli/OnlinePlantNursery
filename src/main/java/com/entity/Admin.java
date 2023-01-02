package com.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;


@Entity
@Table(name = "ADMIN")
public class Admin extends User {

	@NotBlank
	private String name;

	@NotBlank
	private String phone;

	public Admin() {
		super();
	}

	public Admin(@NotBlank @Email(message = "Enter a valid Email") String email, @NotBlank String password, Role role,
			@NotBlank String name, @NotBlank String phone) {
		super(email, password, role);
		this.name = name;
		this.phone = phone;
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
	
	@Override
	public String toString() {
		return "Admin [name=" + name + ", phone=" + phone + "]";
	}

}