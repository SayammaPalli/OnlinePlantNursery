package com.exceptions;

public class ErrorMessage {
	
	public String status;
	public String message;
		
	public ErrorMessage() {
		super();
	}

	public ErrorMessage(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}