package com.salesmanager.core.business.catalog.product.model.attribute;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductOptionDescription.class)
public abstract class ProductOptionDescription_ extends com.salesmanager.core.business.common.model.Description_ {

	public static volatile SingularAttribute<ProductOptionDescription, String> productOptionComment;
	public static volatile SingularAttribute<ProductOptionDescription, ProductOption> productOption;

}

