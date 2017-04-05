package com.salesmanager.shop.store.model.optinnewsletter;

import com.salesmanager.core.model.merchant.MerchantStore;

public class OptinCustomerDTO {
	private String email;
	private String name;
	private String surname;
	private String optinCode;
	
	public String getOptinCode() {
		return optinCode;
	}
	public void setOptinCode(String optinCode) {
		this.optinCode = optinCode;
	}
	private MerchantStore store;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public MerchantStore getStore() {
		return store;
	}
	public void setStore(MerchantStore store) {
		this.store = store;
	}
	
	
	
	

}
