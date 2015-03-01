package com.salesmanager.core.business.order.model;

import com.salesmanager.core.business.common.model.Billing;
import com.salesmanager.core.business.common.model.Delivery;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.orderproduct.OrderProduct;
import com.salesmanager.core.business.order.model.orderstatus.OrderStatus;
import com.salesmanager.core.business.order.model.orderstatus.OrderStatusHistory;
import com.salesmanager.core.business.order.model.payment.CreditCard;
import com.salesmanager.core.business.payments.model.PaymentType;
import com.salesmanager.core.business.reference.currency.model.Currency;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SingularAttribute<Order, BigDecimal> total;
	public static volatile SingularAttribute<Order, String> paymentModuleCode;
	public static volatile SingularAttribute<Order, String> shippingModuleCode;
	public static volatile SetAttribute<Order, OrderTotal> orderTotal;
	public static volatile SingularAttribute<Order, Date> lastModified;
	public static volatile SingularAttribute<Order, MerchantStore> merchant;
	public static volatile SingularAttribute<Order, OrderStatus> status;
	public static volatile SingularAttribute<Order, OrderType> orderType;
	public static volatile SingularAttribute<Order, PaymentType> paymentType;
	public static volatile SingularAttribute<Order, Locale> locale;
	public static volatile SingularAttribute<Order, Date> datePurchased;
	public static volatile SingularAttribute<Order, BigDecimal> currencyValue;
	public static volatile SingularAttribute<Order, Date> orderDateFinished;
	public static volatile SingularAttribute<Order, Currency> currency;
	public static volatile SingularAttribute<Order, CreditCard> creditCard;
	public static volatile SingularAttribute<Order, Long> id;
	public static volatile SingularAttribute<Order, Billing> billing;
	public static volatile SingularAttribute<Order, Long> customerId;
	public static volatile SetAttribute<Order, OrderProduct> orderProducts;
	public static volatile SingularAttribute<Order, Delivery> delivery;
	public static volatile SingularAttribute<Order, OrderChannel> channel;
	public static volatile SetAttribute<Order, OrderStatusHistory> orderHistory;
	public static volatile SingularAttribute<Order, String> customerEmailAddress;
	public static volatile SingularAttribute<Order, String> ipAddress;

}

