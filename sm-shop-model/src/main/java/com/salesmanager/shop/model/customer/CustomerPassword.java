package com.salesmanager.shop.model.customer;

import java.io.Serializable;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import com.salesmanager.shop.validation.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first="password",second="checkPassword",message="password.notequal")
})
public class CustomerPassword implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty( message="{currentpassword.not.empty}")
	private String currentPassword;
	@Size(min=6, message="{newpassword.not.empty}")
	private String password;
	@Size(min=6, message="{repeatpassword.not.empty}")
	private String checkPassword;
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCheckPassword() {
		return checkPassword;
	}
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

}
