package com.salesmanager.core.business.services.catalog.product.review;

import java.util.List;

import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.review.ProductReview;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.reference.language.Language;

public interface ProductReviewService extends
		SalesManagerEntityService<Long, ProductReview> {
	
	
	List<ProductReview> getByCustomer(Customer customer);
	List<ProductReview> getByProduct(Product product);
	List<ProductReview> getByProduct(Product product, Language language);
	ProductReview getByProductAndCustomer(Long productId, Long customerId);
	/**
	 * @param product
	 * @return
	 */
	List<ProductReview> getByProductNoCustomers(Product product);



}
