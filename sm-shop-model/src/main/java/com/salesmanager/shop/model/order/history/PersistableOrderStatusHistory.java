package com.salesmanager.shop.model.order.history;

import com.salesmanager.core.model.order.orderstatus.OrderStatusHistory;

public class PersistableOrderStatusHistory extends OrderStatusHistory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

}
