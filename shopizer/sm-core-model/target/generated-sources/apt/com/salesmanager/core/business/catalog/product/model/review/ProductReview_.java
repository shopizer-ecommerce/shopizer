package com.salesmanager.core.business.catalog.product.model.review;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.customer.model.Customer;
import java.util.Date;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductReview.class)
public abstract class ProductReview_ {

	public static volatile SingularAttribute<ProductReview, Product> product;
	public static volatile SingularAttribute<ProductReview, Long> id;
	public static volatile SingularAttribute<ProductReview, Integer> status;
	public static volatile SingularAttribute<ProductReview, Double> reviewRating;
	public static volatile SingularAttribute<ProductReview, Date> reviewDate;
	public static volatile SingularAttribute<ProductReview, AuditSection> audit;
	public static volatile SingularAttribute<ProductReview, Customer> customer;
	public static volatile SingularAttribute<ProductReview, Long> reviewRead;
	public static volatile SetAttribute<ProductReview, ProductReviewDescription> descriptions;

}

