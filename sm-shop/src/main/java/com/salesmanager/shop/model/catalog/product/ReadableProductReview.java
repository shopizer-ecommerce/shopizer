package com.salesmanager.shop.model.catalog.product;

import java.io.Serializable;

import com.salesmanager.shop.model.customer.ReadableCustomer;


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
