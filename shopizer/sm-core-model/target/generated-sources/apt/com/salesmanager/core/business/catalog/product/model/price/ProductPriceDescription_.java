package com.salesmanager.core.business.catalog.product.model.price;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductPriceDescription.class)
public abstract class ProductPriceDescription_ extends com.salesmanager.core.business.common.model.Description_ {

	public static volatile SingularAttribute<ProductPriceDescription, ProductPrice> productPrice;

}

