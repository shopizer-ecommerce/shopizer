package com.salesmanager.core.business.payments.model;

public enum PaymentType {
	
	
	
	CREDITCARD("creditcard"), FREE("free"), COD("moneyorder"), MONEYORDER("moneyorder"), PAYPAL("creditcard"), BEANSTREAM("creditcard"), STRIPE("creditcard"), AUTHORIZENET("creditcard"), WEPAY("creditcard");

	
	private String paymentType;
	
	PaymentType(String type) {
		paymentType = type;
	}
	
    public static PaymentType fromString(String text) {
		    if (text != null) {
		      for (PaymentType b : PaymentType.values()) {
		    	String payemntType = text.toUpperCase(); 
		        if (payemntType.equalsIgnoreCase(b.name())) {
		          return b;
		        }
		      }
		    }
		    return null;
	}
}
