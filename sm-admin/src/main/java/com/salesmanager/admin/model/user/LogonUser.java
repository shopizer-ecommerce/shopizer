package com.salesmanager.admin.model.user;

import org.hibernate.validator.constraints.NotEmpty;

public class LogonUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
