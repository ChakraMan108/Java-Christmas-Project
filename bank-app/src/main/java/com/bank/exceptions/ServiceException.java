package com.bank.exceptions;

public class ServiceException extends Exception {

	private String id;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, String id) {
		super(message);
		this.id = id;
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message, Throwable cause, String id) {
		super(message, cause);
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
