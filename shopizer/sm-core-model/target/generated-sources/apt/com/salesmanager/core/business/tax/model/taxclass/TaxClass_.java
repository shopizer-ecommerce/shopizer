package com.salesmanager.core.business.tax.model.taxclass;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.tax.model.taxrate.TaxRate;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TaxClass.class)
public abstract class TaxClass_ {

	public static volatile SingularAttribute<TaxClass, Long> id;
	public static volatile SingularAttribute<TaxClass, MerchantStore> merchantStore;
	public static volatile SingularAttribute<TaxClass, String> title;
	public static volatile ListAttribute<TaxClass, TaxRate> taxRates;
	public static volatile SingularAttribute<TaxClass, String> code;
	public static volatile ListAttribute<TaxClass, Product> products;

}

