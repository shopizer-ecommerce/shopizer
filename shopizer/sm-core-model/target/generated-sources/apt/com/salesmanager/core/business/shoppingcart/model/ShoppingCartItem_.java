package com.salesmanager.core.business.shoppingcart.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ShoppingCartItem.class)
public abstract class ShoppingCartItem_ {

	public static volatile SingularAttribute<ShoppingCartItem, Long> id;
	public static volatile SingularAttribute<ShoppingCartItem, ShoppingCart> shoppingCart;
	public static volatile SingularAttribute<ShoppingCartItem, Integer> quantity;
	public static volatile SetAttribute<ShoppingCartItem, ShoppingCartAttributeItem> attributes;
	public static volatile SingularAttribute<ShoppingCartItem, AuditSection> auditSection;
	public static volatile SingularAttribute<ShoppingCartItem, Long> productId;

}

