package com.salesmanager.shop.admin.security;

import org.springframework.dao.DataAccessException;

public class SecurityDataAccessException extends DataAccessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityDataAccessException(String msg) {
		super(msg);
	}
	
	public SecurityDataAccessException(String msg, Exception e) {
		super(msg,e);
	}

}
