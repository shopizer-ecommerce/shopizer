package com.salesmanager.admin.controller.exception;

import org.springframework.http.HttpStatus;
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
	private HttpStatus status = HttpStatus.OK;

	public AdminAuthenticationException(String msg) {
		super(msg);
	}
	public AdminAuthenticationException(String msg, HttpStatus status) {
		super(msg);
		this.setStatus(status);
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
