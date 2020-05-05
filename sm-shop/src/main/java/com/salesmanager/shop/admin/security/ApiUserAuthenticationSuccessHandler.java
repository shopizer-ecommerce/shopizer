package com.salesmanager.shop.admin.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiUserAuthenticationSuccessHandler extends AbstractAuthenticatinSuccessHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiUserAuthenticationSuccessHandler.class);

	@Override
	protected void redirectAfterSuccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// nothing
		
	}
	





}
