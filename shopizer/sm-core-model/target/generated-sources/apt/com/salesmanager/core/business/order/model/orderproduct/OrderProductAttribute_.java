package com.salesmanager.core.business.order.model.orderproduct;

import java.math.BigDecimal;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderProductAttribute.class)
public abstract class OrderProductAttribute_ {

	public static volatile SingularAttribute<OrderProductAttribute, Long> id;
	public static volatile SingularAttribute<OrderProductAttribute, BigDecimal> productAttributePrice;
	public static volatile SingularAttribute<OrderProductAttribute, String> productAttributeName;
	public static volatile SingularAttribute<OrderProductAttribute, Long> productOptionId;
	public static volatile SingularAttribute<OrderProductAttribute, OrderProduct> orderProduct;
	public static volatile SingularAttribute<OrderProductAttribute, Boolean> productAttributeIsFree;
	public static volatile SingularAttribute<OrderProductAttribute, Long> productOptionValueId;
	public static volatile SingularAttribute<OrderProductAttribute, String> productAttributeValueName;
	public static volatile SingularAttribute<OrderProductAttribute, BigDecimal> productAttributeWeight;

}

