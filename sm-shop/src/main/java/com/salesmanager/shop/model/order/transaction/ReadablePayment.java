package com.salesmanager.shop.model.order.transaction;

import com.salesmanager.core.model.payments.PaymentType;
import com.salesmanager.core.model.payments.TransactionType;

public class ReadablePayment extends PaymentEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PaymentType paymentType;
	private TransactionType transactionType;
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

}
