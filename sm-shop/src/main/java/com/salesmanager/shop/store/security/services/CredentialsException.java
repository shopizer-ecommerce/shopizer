package com.salesmanager.shop.store.security.services;

public class CredentialsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CredentialsException(String message) {
		super(message);
	}
	
	public CredentialsException(String message, Exception e) {
		super(message, e);
	}

}
