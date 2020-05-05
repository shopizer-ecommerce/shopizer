package com.salesmanager.shop.model.customer;

public class ReadableCustomerReview extends CustomerReviewEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReadableCustomer reviewedCustomer;
	public ReadableCustomer getReviewedCustomer() {
		return reviewedCustomer;
	}
	public void setReviewedCustomer(ReadableCustomer reviewedCustomer) {
		this.reviewedCustomer = reviewedCustomer;
	}


}
