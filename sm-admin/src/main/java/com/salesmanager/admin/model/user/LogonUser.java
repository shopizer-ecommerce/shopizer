package com.salesmanager.admin.model.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class LogonUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Password must not be empty")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
