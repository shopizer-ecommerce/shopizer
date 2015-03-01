package com.salesmanager.core.business.catalog.product.model.image;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductImageDescription.class)
public abstract class ProductImageDescription_ extends com.salesmanager.core.business.common.model.Description_ {

	public static volatile SingularAttribute<ProductImageDescription, ProductImage> productImage;
	public static volatile SingularAttribute<ProductImageDescription, String> altTag;

}

