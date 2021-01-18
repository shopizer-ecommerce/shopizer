package com.salesmanager.shop.model.user;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class UserNameEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


}
