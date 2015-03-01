package com.salesmanager.core.business.customer.model.attribute;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CustomerOptionValue.class)
public abstract class CustomerOptionValue_ {

	public static volatile SingularAttribute<CustomerOptionValue, Long> id;
	public static volatile SingularAttribute<CustomerOptionValue, MerchantStore> merchantStore;
	public static volatile SingularAttribute<CustomerOptionValue, Integer> sortOrder;
	public static volatile SingularAttribute<CustomerOptionValue, String> code;
	public static volatile SingularAttribute<CustomerOptionValue, String> customerOptionValueImage;
	public static volatile SetAttribute<CustomerOptionValue, CustomerOptionValueDescription> descriptions;

}

