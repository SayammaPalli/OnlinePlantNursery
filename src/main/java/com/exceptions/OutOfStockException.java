package com.exceptions;

public class OutOfStockException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OutOfStockException(String message) {
		super();
		this.message = message;
	}
	
	public OutOfStockException() {
		super();
	}

}