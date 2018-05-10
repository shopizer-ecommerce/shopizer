package com.salesmanager.admin.components.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * Authentication token for admin application
 * @author carlsamson
 *
 */
public class AdminAuthenticationToken extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
	
	public AdminAuthenticationToken(Object principal, Object credentials) {		
		super(principal,credentials);
	}
	
	public void setDetails(Object details) {
		super.setDetails(details);
	}
	
	public Object getDetails() {
		return super.getDetails();
	}



}
