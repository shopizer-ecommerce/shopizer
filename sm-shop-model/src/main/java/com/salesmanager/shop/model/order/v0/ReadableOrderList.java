package com.salesmanager.shop.model.order.v0;

import java.io.Serializable;
import java.util.List;

import com.salesmanager.shop.model.entity.ReadableList;

@Deprecated
public class ReadableOrderList extends ReadableList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ReadableOrder> orders;
	
	
	
	public List<ReadableOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<ReadableOrder> orders) {
		this.orders = orders;
	}

}
