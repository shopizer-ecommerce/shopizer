package com.salesmanager.core.business.catalog.product.model.type;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductType.class)
public abstract class ProductType_ {

	public static volatile SingularAttribute<ProductType, Long> id;
	public static volatile SingularAttribute<ProductType, Boolean> allowAddToCart;
	public static volatile SingularAttribute<ProductType, String> code;
	public static volatile SingularAttribute<ProductType, AuditSection> auditSection;

}

