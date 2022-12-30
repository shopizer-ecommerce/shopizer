package com.salesmanager.shop.populator.customer;

import java.util.ArrayList;
import java.util.List;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.model.entity.ReadableList;

public class ReadableCustomerList extends ReadableList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ReadableCustomer> customers = new ArrayList<ReadableCustomer>();

	public List<ReadableCustomer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<ReadableCustomer> customers) {
		this.customers = customers;
	}

}
