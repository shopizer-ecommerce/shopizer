package com.salesmanager.shop.model.order;

import java.io.Serializable;
import java.util.List;
import com.salesmanager.shop.model.entity.ReadableList;
import com.salesmanager.shop.model.entity.ServiceEntity;


public class ReadableOrderList extends ReadableList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private List<ReadableOrder> orders;
	
	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<ReadableOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<ReadableOrder> orders) {
		this.orders = orders;
	}

}
