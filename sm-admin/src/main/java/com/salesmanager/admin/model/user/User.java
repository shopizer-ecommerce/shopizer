package com.salesmanager.admin.model.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.salesmanager.admin.model.common.AbstractModel;

public class User extends AbstractModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "UserName must not be empty")
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
