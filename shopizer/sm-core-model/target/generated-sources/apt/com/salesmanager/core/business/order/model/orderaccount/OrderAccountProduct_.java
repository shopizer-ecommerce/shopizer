package com.salesmanager.core.business.order.model.orderaccount;

import com.salesmanager.core.business.order.model.orderproduct.OrderProduct;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderAccountProduct.class)
public abstract class OrderAccountProduct_ {

	public static volatile SingularAttribute<OrderAccountProduct, OrderProduct> orderProduct;
	public static volatile SingularAttribute<OrderAccountProduct, Date> orderAccountProductEot;
	public static volatile SingularAttribute<OrderAccountProduct, Date> orderAccountProductStartDate;
	public static volatile SingularAttribute<OrderAccountProduct, Date> orderAccountProductAccountedDate;
	public static volatile SingularAttribute<OrderAccountProduct, Long> orderAccountProductId;
	public static volatile SingularAttribute<OrderAccountProduct, Integer> orderAccountProductStatus;
	public static volatile SingularAttribute<OrderAccountProduct, Date> orderAccountProductLastStatusDate;
	public static volatile SingularAttribute<OrderAccountProduct, Integer> orderAccountProductPaymentFrequencyType;
	public static volatile SingularAttribute<OrderAccountProduct, Date> orderAccountProductEndDate;
	public static volatile SingularAttribute<OrderAccountProduct, OrderAccount> orderAccount;
	public static volatile SingularAttribute<OrderAccountProduct, Integer> orderAccountProductLastTransactionStatus;

}

