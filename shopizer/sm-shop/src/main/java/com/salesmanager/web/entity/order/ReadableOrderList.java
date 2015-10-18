package com.salesmanager.web.entity.order;

import java.io.Serializable;
import java.util.List;

import com.salesmanager.web.entity.ServiceEntity;

public class ReadableOrderList extends ServiceEntity implements Serializable {

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
