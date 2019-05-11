package com.salesmanager.shop.model.order.transaction;

import java.io.Serializable;
import com.salesmanager.core.model.payments.PaymentType;
import com.salesmanager.core.model.payments.TransactionType;

/**
 * This class is used for writing a transaction in the System
 * @author c.samson
 *
 */
public class PersistableTransaction extends TransactionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@com.salesmanager.shop.validation.Enum(enumClass=PaymentType.class, ignoreCase=true) 
	private String paymentType;

	@com.salesmanager.shop.validation.Enum(enumClass=TransactionType.class, ignoreCase=true) 
	private String transactionType;

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
}
