package com.salesmanager.core.business.catalog.product.model.attribute;

import com.salesmanager.core.business.catalog.product.model.Product;
import java.math.BigDecimal;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductAttribute.class)
public abstract class ProductAttribute_ {

	public static volatile SingularAttribute<ProductAttribute, Product> product;
	public static volatile SingularAttribute<ProductAttribute, Boolean> attributeDiscounted;
	public static volatile SingularAttribute<ProductAttribute, Long> id;
	public static volatile SingularAttribute<ProductAttribute, BigDecimal> productAttributePrice;
	public static volatile SingularAttribute<ProductAttribute, Boolean> productAttributeIsFree;
	public static volatile SingularAttribute<ProductAttribute, Boolean> attributeRequired;
	public static volatile SingularAttribute<ProductAttribute, Boolean> attributeDefault;
	public static volatile SingularAttribute<ProductAttribute, ProductOptionValue> productOptionValue;
	public static volatile SingularAttribute<ProductAttribute, BigDecimal> productAttributeWeight;
	public static volatile SingularAttribute<ProductAttribute, ProductOption> productOption;
	public static volatile SingularAttribute<ProductAttribute, Boolean> attributeDisplayOnly;
	public static volatile SingularAttribute<ProductAttribute, Integer> productOptionSortOrder;

}

