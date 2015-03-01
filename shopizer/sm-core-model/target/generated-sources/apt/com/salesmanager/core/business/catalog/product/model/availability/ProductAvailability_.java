package com.salesmanager.core.business.catalog.product.model.availability;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.price.ProductPrice;
import java.util.Date;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductAvailability.class)
public abstract class ProductAvailability_ {

	public static volatile SingularAttribute<ProductAvailability, String> regionVariant;
	public static volatile SingularAttribute<ProductAvailability, String> region;
	public static volatile SingularAttribute<ProductAvailability, Product> product;
	public static volatile SingularAttribute<ProductAvailability, Long> id;
	public static volatile SingularAttribute<ProductAvailability, Integer> productQuantityOrderMax;
	public static volatile SingularAttribute<ProductAvailability, Boolean> productStatus;
	public static volatile SingularAttribute<ProductAvailability, Date> productDateAvailable;
	public static volatile SingularAttribute<ProductAvailability, Integer> productQuantityOrderMin;
	public static volatile SetAttribute<ProductAvailability, ProductPrice> prices;
	public static volatile SingularAttribute<ProductAvailability, Integer> productQuantity;
	public static volatile SingularAttribute<ProductAvailability, Boolean> productIsAlwaysFreeShipping;

}

