package com.salesmanager.core.business.customer.model.attribute;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CustomerOptionSet.class)
public abstract class CustomerOptionSet_ {

	public static volatile SingularAttribute<CustomerOptionSet, Long> id;
	public static volatile SingularAttribute<CustomerOptionSet, Integer> sortOrder;
	public static volatile SingularAttribute<CustomerOptionSet, CustomerOption> customerOption;
	public static volatile SingularAttribute<CustomerOptionSet, CustomerOptionValue> customerOptionValue;

}

