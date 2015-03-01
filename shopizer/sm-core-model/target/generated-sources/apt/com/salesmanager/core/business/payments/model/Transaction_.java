package com.salesmanager.core.business.payments.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.order.model.Order;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Transaction.class)
public abstract class Transaction_ {

	public static volatile SingularAttribute<Transaction, BigDecimal> amount;
	public static volatile SingularAttribute<Transaction, Long> id;
	public static volatile SingularAttribute<Transaction, TransactionType> transactionType;
	public static volatile SingularAttribute<Transaction, String> details;
	public static volatile SingularAttribute<Transaction, Order> order;
	public static volatile SingularAttribute<Transaction, PaymentType> paymentType;
	public static volatile SingularAttribute<Transaction, Date> transactionDate;
	public static volatile SingularAttribute<Transaction, AuditSection> auditSection;

}

