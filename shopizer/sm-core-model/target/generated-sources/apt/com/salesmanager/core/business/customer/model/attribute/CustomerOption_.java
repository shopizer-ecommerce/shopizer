package com.salesmanager.core.business.customer.model.attribute;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CustomerOption.class)
public abstract class CustomerOption_ {

	public static volatile SingularAttribute<CustomerOption, Long> id;
	public static volatile SingularAttribute<CustomerOption, MerchantStore> merchantStore;
	public static volatile SingularAttribute<CustomerOption, String> customerOptionType;
	public static volatile SingularAttribute<CustomerOption, Integer> sortOrder;
	public static volatile SingularAttribute<CustomerOption, Boolean> publicOption;
	public static volatile SingularAttribute<CustomerOption, Boolean> active;
	public static volatile SingularAttribute<CustomerOption, String> code;
	public static volatile SetAttribute<CustomerOption, CustomerOptionDescription> descriptions;

}

