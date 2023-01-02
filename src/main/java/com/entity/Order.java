package com.entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ_FOR_ORDER", initialValue = 101, allocationSize = 1)
	private Integer bookingId;
	
	private LocalDate bookingDate;
	
	@Enumerated(EnumType.STRING)
	private TransactionMode transactionMode;
	
	private int quantity;
	
	private double totalCost;
	
	@CollectionTable(name = "Order_Products")
	@ElementCollection
	private Map<Integer, @Positive (message = "Not a valid order. Quantity must be positive")Integer> products = new HashMap<Integer, Integer>(); //Productid -> Quantity bought
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Customer customer;
	
	public Order() {
		super();
	}

	public Order(LocalDate bookingDate, TransactionMode transactionMode, int quantity, double totalCost) {
		super();
		this.bookingDate = bookingDate;
		this.transactionMode = transactionMode;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}

	public Order(Integer bookingId, LocalDate bookingDate, TransactionMode transactionMode, int quantity, double totalCost) {
		super();
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.transactionMode = transactionMode;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}

	public Order(LocalDate bookingDate, TransactionMode transactionMode, int quantity, double totalCost,
			Map<Integer, @Positive(message = "Not a valid order. Quantity must be positive") Integer> products,
			Customer customer) {
		super();
		this.bookingDate = bookingDate;
		this.transactionMode = transactionMode;
		this.quantity = quantity;
		this.totalCost = totalCost;
		this.products = products;
		this.customer = customer;
	}

	public Order(Integer bookingId, LocalDate bookingDate, TransactionMode transactionMode, int quantity, double totalCost,
			Map<Integer, @Positive(message = "Not a valid order. Quantity must be positive") Integer> products,
			Customer customer) {
		super();
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.transactionMode = transactionMode;
		this.quantity = quantity;
		this.totalCost = totalCost;
		this.products = products;
		this.customer = customer;
	}

	public Order(Integer bookingId, TransactionMode transactionMode) {
		super();
		this.bookingId = bookingId;
		this.transactionMode = transactionMode;
	}

	public Order(Map<Integer, @Positive(message = "Not a valid order. Quantity must be positive") Integer> products,
			Customer customer) {
		super();
		this.products = products;
		this.customer = customer;
	}

	public Order(Map<Integer, @Positive(message = "Not a valid order. Quantity must be positive") Integer> products) {
		super();
		this.products = products;
	}

	public Order(TransactionMode transactionMode,
			Map<Integer, @Positive(message = "Not a valid order. Quantity must be positive") Integer> products) {
		super();
		this.transactionMode = transactionMode;
		this.products = products;
	}

	public Order(Integer bookingId, TransactionMode transactionMode,
			Map<Integer, @Positive(message = "Not a valid order. Quantity must be positive") Integer> products,
			Customer customer) {
		super();
		this.bookingId = bookingId;
		this.transactionMode = transactionMode;
		this.products = products;
		this.customer = customer;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public TransactionMode getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(TransactionMode transactionMode) {
		this.transactionMode = transactionMode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Map<Integer, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Integer, Integer> products) {
		this.products = products;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingDate == null) ? 0 : bookingDate.hashCode());
		result = prime * result + ((bookingId == null) ? 0 : bookingId.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + quantity;
		long temp;
		temp = Double.doubleToLongBits(totalCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transactionMode == null) ? 0 : transactionMode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (bookingDate == null) {
			if (other.bookingDate != null)
				return false;
		} else if (!bookingDate.equals(other.bookingDate))
			return false;
		if (bookingId == null) {
			if (other.bookingId != null)
				return false;
		} else if (!bookingId.equals(other.bookingId))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Double.doubleToLongBits(totalCost) != Double.doubleToLongBits(other.totalCost))
			return false;
		if (transactionMode != other.transactionMode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", transactionMode=" + transactionMode
				+ ", quantity=" + quantity + ", totalCost=" + totalCost + ", products=" + products + ", customer="
				+ customer + "]";
	}

	
}