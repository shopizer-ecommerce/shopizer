package com.salesmanager.core.business.order.model.orderproduct;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderProductPrice.class)
public abstract class OrderProductPrice_ {

	public static volatile SingularAttribute<OrderProductPrice, Date> productPriceSpecialStartDate;
	public static volatile SingularAttribute<OrderProductPrice, Long> id;
	public static volatile SingularAttribute<OrderProductPrice, OrderProduct> orderProduct;
	public static volatile SingularAttribute<OrderProductPrice, Date> productPriceSpecialEndDate;
	public static volatile SingularAttribute<OrderProductPrice, String> productPriceCode;
	public static volatile SingularAttribute<OrderProductPrice, String> productPriceName;
	public static volatile SingularAttribute<OrderProductPrice, BigDecimal> productPriceSpecial;
	public static volatile SingularAttribute<OrderProductPrice, Boolean> defaultPrice;
	public static volatile SingularAttribute<OrderProductPrice, BigDecimal> productPrice;

}

