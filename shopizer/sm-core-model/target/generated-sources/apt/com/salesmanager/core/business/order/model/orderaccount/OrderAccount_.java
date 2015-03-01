package com.salesmanager.core.business.order.model.orderaccount;

import com.salesmanager.core.business.order.model.Order;
import java.util.Date;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderAccount.class)
public abstract class OrderAccount_ {

	public static volatile SingularAttribute<OrderAccount, Date> orderAccountEndDate;
	public static volatile SingularAttribute<OrderAccount, Long> id;
	public static volatile SingularAttribute<OrderAccount, Order> order;
	public static volatile SetAttribute<OrderAccount, OrderAccountProduct> orderAccountProducts;
	public static volatile SingularAttribute<OrderAccount, Integer> orderAccountBillDay;
	public static volatile SingularAttribute<OrderAccount, Date> orderAccountStartDate;

}

