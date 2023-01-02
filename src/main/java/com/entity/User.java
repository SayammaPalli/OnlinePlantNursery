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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "NURSERY_USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ_FOR_USER", initialValue = 201, allocationSize = 1)
	private Integer id;

	@NotBlank
	@Email(message = "Enter a valid Email")
	@Column(unique = true)
	private String email;

	@NotBlank
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	// constructors

	public User() {
		super();
	}

	public User(Integer id) {
	super();
	this.id = id;
}
	public User(@Email(message = "Enter a valid  Email") String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	

	public User(Integer id, @NotBlank @Email(message = "Enter a valid Email") String email, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.role = role;
	}


	public User(@NotBlank @Email(message = "Enter a valid Email") String email, @NotBlank String password, Role role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public User(Integer id, @NotBlank @Email(message = "Enter a valid Email") String email, @NotBlank String password,
			Role role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
	}

//setters and getters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}

}