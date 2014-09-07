package com.salesmanager.web.entity.catalog.product;

import java.io.Serializable;

import com.salesmanager.web.entity.customer.ReadableCustomer;

public class ReadableProductReview extends ProductReviewEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReadableCustomer customer;
	public ReadableCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(ReadableCustomer customer) {
		this.customer = customer;
	}

}
