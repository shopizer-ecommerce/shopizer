package com.salesmanager.shop.model.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.customer.attribute.ReadableCustomerAttribute;


public class ReadableCustomer extends CustomerEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ReadableCustomerAttribute> attributes = new ArrayList<ReadableCustomerAttribute>();
	public void setAttributes(List<ReadableCustomerAttribute> attributes) {
		this.attributes = attributes;
	}
	public List<ReadableCustomerAttribute> getAttributes() {
		return attributes;
	}

}
