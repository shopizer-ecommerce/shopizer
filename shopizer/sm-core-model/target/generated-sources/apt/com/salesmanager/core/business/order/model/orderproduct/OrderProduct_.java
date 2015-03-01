package com.salesmanager.core.business.order.model.orderproduct;

import com.salesmanager.core.business.order.model.Order;
import java.math.BigDecimal;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderProduct.class)
public abstract class OrderProduct_ {

	public static volatile SingularAttribute<OrderProduct, Long> id;
	public static volatile SingularAttribute<OrderProduct, Order> order;
	public static volatile SetAttribute<OrderProduct, OrderProductAttribute> orderAttributes;
	public static volatile SetAttribute<OrderProduct, OrderProductPrice> prices;
	public static volatile SingularAttribute<OrderProduct, Integer> productQuantity;
	public static volatile SingularAttribute<OrderProduct, String> sku;
	public static volatile SetAttribute<OrderProduct, OrderProductDownload> downloads;
	public static volatile SingularAttribute<OrderProduct, String> productName;
	public static volatile SingularAttribute<OrderProduct, BigDecimal> oneTimeCharge;

}

