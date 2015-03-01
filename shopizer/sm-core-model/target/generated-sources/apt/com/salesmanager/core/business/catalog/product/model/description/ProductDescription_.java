package com.salesmanager.core.business.catalog.product.model.description;

import com.salesmanager.core.business.catalog.product.model.Product;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductDescription.class)
public abstract class ProductDescription_ extends com.salesmanager.core.business.common.model.Description_ {

	public static volatile SingularAttribute<ProductDescription, String> metatagTitle;
	public static volatile SingularAttribute<ProductDescription, Product> product;
	public static volatile SingularAttribute<ProductDescription, String> productHighlight;
	public static volatile SingularAttribute<ProductDescription, String> metatagDescription;
	public static volatile SingularAttribute<ProductDescription, String> metatagKeywords;
	public static volatile SingularAttribute<ProductDescription, String> productExternalDl;
	public static volatile SingularAttribute<ProductDescription, String> seUrl;

}

