package com.salesmanager.web.entity.order;

import java.io.Serializable;
import java.util.List;

public class ReadableShopOrder extends ReadableOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ReadableShippingSummary shippingSummary;

	private String errorMessage = null;//global error message
	private List<ReadableOrderTotal> subTotals;//order calculation	
	private String grandTotal;//grand total - order calculation
	

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}
	public List<ReadableOrderTotal> getSubTotals() {
		return subTotals;
	}
	public void setSubTotals(List<ReadableOrderTotal> subTotals) {
		this.subTotals = subTotals;
	}
	public ReadableShippingSummary getShippingSummary() {
		return shippingSummary;
	}
	public void setShippingSummary(ReadableShippingSummary shippingSummary) {
		this.shippingSummary = shippingSummary;
	}

}
