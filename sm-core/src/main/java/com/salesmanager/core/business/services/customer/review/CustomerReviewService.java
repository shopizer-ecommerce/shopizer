package com.salesmanager.core.business.services.customer.review;

import java.util.List;

import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.review.CustomerReview;

public interface CustomerReviewService extends
	SalesManagerEntityService<Long, CustomerReview> {
	
	/**
	 * All reviews created by a given customer
	 * @param customer
	 * @return
	 */
	List<CustomerReview> getByCustomer(Customer customer);
	
	/**
	 * All reviews received by a given customer
	 * @param customer
	 * @return
	 */
	List<CustomerReview> getByReviewedCustomer(Customer customer);
	
	/**
	 * Get a review made by a customer to another customer
	 * @param reviewer
	 * @param reviewed
	 * @return
	 */
	CustomerReview getByReviewerAndReviewed(Long reviewer, Long reviewed);

}
