package com.salesmanager.core.business.catalog.product.model.file;

import com.salesmanager.core.business.catalog.product.model.Product;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DigitalProduct.class)
public abstract class DigitalProduct_ {

	public static volatile SingularAttribute<DigitalProduct, Product> product;
	public static volatile SingularAttribute<DigitalProduct, Long> id;
	public static volatile SingularAttribute<DigitalProduct, String> productFileName;

}

