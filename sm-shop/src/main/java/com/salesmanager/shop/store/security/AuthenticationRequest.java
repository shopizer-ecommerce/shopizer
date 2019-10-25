package com.salesmanager.shop.store.security;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class AuthenticationRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Username and password must be used when using normal system authentication
	 * for a registered customer
	 */
	@NotEmpty(message="{NotEmpty.customer.userName}")
    private String username;
	@NotEmpty(message="{message.password.required}")
    private String password;
    


    public AuthenticationRequest() {
        super();
    }

    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
