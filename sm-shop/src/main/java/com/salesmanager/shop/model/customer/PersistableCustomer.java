package com.salesmanager.shop.model.customer;

import java.io.Serializable;
import java.util.List;

import com.salesmanager.shop.model.customer.attribute.PersistableCustomerAttribute;
import com.salesmanager.shop.model.security.PersistableGroup;



public class PersistableCustomer extends CustomerEntity implements Serializable {

	/**
	 * 
	 */
	private String encodedPassword = null;
	private String clearPassword = null;
	private static final long serialVersionUID = 1L;
	private List<PersistableCustomerAttribute> attributes;
	private List<PersistableGroup> groups;
	
	
	public void setAttributes(List<PersistableCustomerAttribute> attributes) {
		this.attributes = attributes;
	}
	public List<PersistableCustomerAttribute> getAttributes() {
		return attributes;
	}
	public String getEncodedPassword() {
		return encodedPassword;
	}
	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}
	public String getClearPassword() {
		return clearPassword;
	}
	public void setClearPassword(String clearPassword) {
		this.clearPassword = clearPassword;
	}
	public List<PersistableGroup> getGroups() {
		return groups;
	}
	public void setGroups(List<PersistableGroup> groups) {
		this.groups = groups;
	}
	

}
