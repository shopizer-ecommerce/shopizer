package com.salesmanager.shop.store.security.common;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomAuthenticationException(String msg) {
		super(msg);
	}

}
