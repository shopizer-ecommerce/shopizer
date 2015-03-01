package com.salesmanager.core.business.shoppingcart.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ShoppingCartAttributeItem.class)
public abstract class ShoppingCartAttributeItem_ {

	public static volatile SingularAttribute<ShoppingCartAttributeItem, Long> id;
	public static volatile SingularAttribute<ShoppingCartAttributeItem, ShoppingCartItem> shoppingCartItem;
	public static volatile SingularAttribute<ShoppingCartAttributeItem, Long> productAttributeId;
	public static volatile SingularAttribute<ShoppingCartAttributeItem, AuditSection> auditSection;

}

