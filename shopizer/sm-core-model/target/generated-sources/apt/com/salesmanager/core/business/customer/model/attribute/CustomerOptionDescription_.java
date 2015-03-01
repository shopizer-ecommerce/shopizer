package com.salesmanager.core.business.customer.model.attribute;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CustomerOptionDescription.class)
public abstract class CustomerOptionDescription_ extends com.salesmanager.core.business.common.model.Description_ {

	public static volatile SingularAttribute<CustomerOptionDescription, CustomerOption> customerOption;
	public static volatile SingularAttribute<CustomerOptionDescription, String> customerOptionComment;

}

