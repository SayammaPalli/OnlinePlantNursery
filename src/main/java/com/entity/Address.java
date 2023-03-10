package com.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {

	@NotBlank
	private String houseNo;
	private String colony;
	private String city;
	private String state;
	@NotNull
	private int pincode;

	public Address() {
		super();
	}

	public Address(@NotBlank String houseNo, String colony, String city, String state, @NotNull int pincode) {
		super();
		this.houseNo = houseNo;
		this.colony = colony;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getColony() {
		return colony;
	}

	public void setColony(String colony) {
		this.colony = colony;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Address [houseNo=" + houseNo + ", colony=" + colony + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + "]";
	}

}