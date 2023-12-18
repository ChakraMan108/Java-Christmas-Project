package com.bank.exceptions;

public class RepositoryException extends Exception {

	private String id;

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(String message, String id) {
		super(message);
		this.id = id;
	}

	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryException(String message, Throwable cause, String id) {
		super(message, cause);
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
}
