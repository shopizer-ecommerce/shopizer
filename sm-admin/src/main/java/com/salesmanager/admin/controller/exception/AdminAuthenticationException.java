package com.salesmanager.admin.controller.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * To be used for authentication only
 * @author carlsamson
 *
 */
public class AdminAuthenticationException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminAuthenticationException(String msg) {
		super(msg);
	}

}
