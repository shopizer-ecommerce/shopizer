package com.salesmanager.shop.model.customer;

public class PersistableCustomerReview extends CustomerReviewEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long reviewedCustomer;

	public Long getReviewedCustomer() {
		return reviewedCustomer;
	}

	public void setReviewedCustomer(Long reviewedCustomer) {
		this.reviewedCustomer = reviewedCustomer;
	}

}
