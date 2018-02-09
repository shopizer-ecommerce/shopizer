package com.salesmanager.core.model.payments;

import java.math.BigDecimal;
import java.util.Map;

import com.salesmanager.core.model.reference.currency.Currency;

public class Payment {
	
	private PaymentType paymentType;
	private TransactionType transactionType = TransactionType.AUTHORIZECAPTURE;
	private String moduleName;
	private Currency currency;
	private BigDecimal amount;
	private Map<String,String> paymentMetaData = null;

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Map<String,String> getPaymentMetaData() {
		return paymentMetaData;
	}

	public void setPaymentMetaData(Map<String,String> paymentMetaData) {
		this.paymentMetaData = paymentMetaData;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
