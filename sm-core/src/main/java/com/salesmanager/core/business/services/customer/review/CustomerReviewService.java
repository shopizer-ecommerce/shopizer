package com.salesmanager.core.business.services.customer.review;

import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.review.CustomerReview;

import java.util.List;

public interface CustomerReviewService extends
        SalesManagerEntityService<Long, CustomerReview> {

    /**
     * All reviews created by a given customer
     *
     */
    List<CustomerReview> getByCustomer(Customer customer);

    /**
     * All reviews received by a given customer
     *
     */
    List<CustomerReview> getByReviewedCustomer(Customer customer);

    /**
     * Get a review made by a customer to another customer
     *
     */
    CustomerReview getByReviewerAndReviewed(Long reviewer, Long reviewed);

}
