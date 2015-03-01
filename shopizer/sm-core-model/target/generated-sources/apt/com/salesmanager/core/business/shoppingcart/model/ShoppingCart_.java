package com.salesmanager.core.business.shoppingcart.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ShoppingCart.class)
public abstract class ShoppingCart_ {

	public static volatile SingularAttribute<ShoppingCart, String> shoppingCartCode;
	public static volatile SingularAttribute<ShoppingCart, Long> id;
	public static volatile SingularAttribute<ShoppingCart, MerchantStore> merchantStore;
	public static volatile SingularAttribute<ShoppingCart, Long> customerId;
	public static volatile SetAttribute<ShoppingCart, ShoppingCartItem> lineItems;
	public static volatile SingularAttribute<ShoppingCart, AuditSection> auditSection;

}

