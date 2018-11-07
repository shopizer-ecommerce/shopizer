package com.salesmanager.shop.model.catalog.product;

import com.salesmanager.shop.model.Entity;
import com.salesmanager.shop.model.customer.address.Address;

/**
 * RENTAL customer
 * @author c.samson
 *
 */
public class RentalOwner extends Entity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private Address address;
	private String emailAddress;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
