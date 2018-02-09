package com.salesmanager.shop.store.security.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Abstract authentication manager to be used by various internal Authentication manager
 * invoked from a SecurityFilter placed in the security filter chain of given http configuration.
 * @author c.samson
 *
 */
public abstract class CustomAuthenticationManager {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
    @Value("${authToken.header}")
    private String tokenHeader;

	
	public String getTokenHeader() {
		return tokenHeader;
	}

	public void setTokenHeader(String tokenHeader) {
		this.tokenHeader = tokenHeader;
	}

	public void authenticateRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Processing authentication");
		}
		
		Authentication authResult = null;

		try {
			authResult = this.attemptAuthentication(request, response);
			if (authResult == null) {
				// return immediately as subclass has indicated that it hasn't completed
				// authentication
				return;
			}
		} catch (AuthenticationException failed) {
			// Authentication failed
			unsuccess(request, response);
			return;
		}

		this.success(request, response, authResult);

		
	}
	
	private void success(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws AuthenticationException {

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Authentication success");
			logger.debug("Updated SecurityContextHolder to containAuthentication");
		}
		
		successfullAuthentication(request, response, authentication);
	}
	
	private void unsuccess(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		SecurityContextHolder.clearContext();
		
		if (logger.isDebugEnabled()) {
			logger.debug("Authentication request failed");
			logger.debug("Updated SecurityContextHolder to contain null Authentication");
		}
		
		unSuccessfullAuthentication(request, response);
	}

	
	abstract Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, Exception;
	
	abstract void successfullAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws AuthenticationException;

	abstract void unSuccessfullAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException;

}
