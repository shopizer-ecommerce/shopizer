package com.salesmanager.shop.model.customer;


public class SecuredShopPersistableCustomer extends SecuredCustomer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String checkPassword;
	

	public String getCheckPassword() {
		return checkPassword;
	}
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
	


}
