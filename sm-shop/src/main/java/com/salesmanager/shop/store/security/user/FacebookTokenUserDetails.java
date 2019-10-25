package com.salesmanager.shop.store.security.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

public class FacebookTokenUserDetails extends SocialUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String firstName;
	private String lastName;


	public FacebookTokenUserDetails(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
