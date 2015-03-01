package com.salesmanager.core.business.catalog.product.model.price;

import com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductPrice.class)
public abstract class ProductPrice_ {

	public static volatile SingularAttribute<ProductPrice, BigDecimal> productPriceSpecialAmount;
	public static volatile SingularAttribute<ProductPrice, Date> productPriceSpecialStartDate;
	public static volatile SingularAttribute<ProductPrice, Long> id;
	public static volatile SingularAttribute<ProductPrice, Date> productPriceSpecialEndDate;
	public static volatile SingularAttribute<ProductPrice, ProductAvailability> productAvailability;
	public static volatile SingularAttribute<ProductPrice, BigDecimal> productPriceAmount;
	public static volatile SingularAttribute<ProductPrice, String> code;
	public static volatile SingularAttribute<ProductPrice, Boolean> defaultPrice;
	public static volatile SingularAttribute<ProductPrice, ProductPriceType> productPriceType;
	public static volatile SetAttribute<ProductPrice, ProductPriceDescription> descriptions;

}

