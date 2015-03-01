package com.salesmanager.core.business.catalog.product.model;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute;
import com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.catalog.product.model.image.ProductImage;
import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.catalog.product.model.relationship.ProductRelationship;
import com.salesmanager.core.business.catalog.product.model.type.ProductType;
import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.tax.model.taxclass.TaxClass;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, MerchantStore> merchantStore;
	public static volatile SingularAttribute<Product, BigDecimal> productLength;
	public static volatile SingularAttribute<Product, ProductType> type;
	public static volatile SetAttribute<Product, ProductDescription> descriptions;
	public static volatile SingularAttribute<Product, Long> id;
	public static volatile SingularAttribute<Product, BigDecimal> productHeight;
	public static volatile SingularAttribute<Product, Boolean> productVirtual;
	public static volatile SetAttribute<Product, ProductRelationship> relationships;
	public static volatile SingularAttribute<Product, AuditSection> auditSection;
	public static volatile SingularAttribute<Product, Integer> productReviewCount;
	public static volatile SingularAttribute<Product, BigDecimal> productWidth;
	public static volatile SingularAttribute<Product, Boolean> productShipeable;
	public static volatile SingularAttribute<Product, Integer> sortOrder;
	public static volatile SingularAttribute<Product, Boolean> available;
	public static volatile SingularAttribute<Product, String> sku;
	public static volatile SingularAttribute<Product, Integer> productOrdered;
	public static volatile SingularAttribute<Product, BigDecimal> productReviewAvg;
	public static volatile SetAttribute<Product, ProductAvailability> availabilities;
	public static volatile SingularAttribute<Product, Boolean> productIsFree;
	public static volatile SingularAttribute<Product, TaxClass> taxClass;
	public static volatile SingularAttribute<Product, Manufacturer> manufacturer;
	public static volatile SingularAttribute<Product, BigDecimal> productWeight;
	public static volatile SetAttribute<Product, ProductImage> images;
	public static volatile SetAttribute<Product, Category> categories;
	public static volatile SetAttribute<Product, ProductAttribute> attributes;
	public static volatile SingularAttribute<Product, Date> dateAvailable;

}

