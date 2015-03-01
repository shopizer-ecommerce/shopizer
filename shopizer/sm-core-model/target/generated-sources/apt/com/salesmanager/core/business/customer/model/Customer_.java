package com.salesmanager.core.business.customer.model;

import com.salesmanager.core.business.catalog.product.model.review.ProductReview;
import com.salesmanager.core.business.common.model.Billing;
import com.salesmanager.core.business.common.model.Delivery;
import com.salesmanager.core.business.customer.model.attribute.CustomerAttribute;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.user.model.Group;
import java.util.Date;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, Date> dateOfBirth;
	public static volatile ListAttribute<Customer, ProductReview> reviews;
	public static volatile SingularAttribute<Customer, MerchantStore> merchantStore;
	public static volatile SingularAttribute<Customer, String> emailAddress;
	public static volatile SingularAttribute<Customer, Language> defaultLanguage;
	public static volatile SingularAttribute<Customer, String> password;
	public static volatile SingularAttribute<Customer, Long> id;
	public static volatile SingularAttribute<Customer, Boolean> anonymous;
	public static volatile SingularAttribute<Customer, Billing> billing;
	public static volatile SingularAttribute<Customer, String> nick;
	public static volatile SingularAttribute<Customer, String> company;
	public static volatile SingularAttribute<Customer, CustomerGender> gender;
	public static volatile SetAttribute<Customer, CustomerAttribute> attributes;
	public static volatile SingularAttribute<Customer, Delivery> delivery;
	public static volatile ListAttribute<Customer, Group> groups;

}

