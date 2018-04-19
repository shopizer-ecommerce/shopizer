package com.salesmanager.admin.model.user;

import javax.validation.constraints.NotNull;

public class LogonUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
