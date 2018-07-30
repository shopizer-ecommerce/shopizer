package com.salesmanager.core.business.repositories.customer.review;

import com.salesmanager.core.model.customer.review.CustomerReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerReviewRepository extends JpaRepository<CustomerReview, Long> {

    String customerQuery = ""
            + "select distinct r from CustomerReview r join fetch "
            + "r.customer rc "
            + "join fetch r.reviewedCustomer rr join fetch rc.merchantStore rrm "
            + "left join fetch r.descriptions rd ";


    @Query("select r from CustomerReview r join fetch r.customer rc join fetch r.reviewedCustomer rr join fetch rc.merchantStore rrm left join fetch r.descriptions rd where r.id = ?1")
    CustomerReview findOne(Long id);

    @Query("select distinct r from CustomerReview r join fetch r.customer rc join fetch r.reviewedCustomer rr join fetch rc.merchantStore rrm left join fetch r.descriptions rd where rc.id = ?1")
    List<CustomerReview> findByReviewer(Long id);

    @Query("select distinct r from CustomerReview r join fetch r.customer rc join fetch r.reviewedCustomer rr join fetch rc.merchantStore rrm left join fetch r.descriptions rd where rr.id = ?1")
    List<CustomerReview> findByReviewed(Long id);

    @Query(customerQuery + "where rc.id = ?1 and rr.id = ?2")
    CustomerReview findByRevieweAndReviewed(Long reviewer, Long reviewed);


}
