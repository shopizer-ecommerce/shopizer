package com.salesmanager.shop.model.order.total;

import java.io.Serializable;

public class ReadableOrderTotal extends OrderTotal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String total;
	private boolean discounted;
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public boolean isDiscounted() {
		return discounted;
	}
	public void setDiscounted(boolean discounted) {
		this.discounted = discounted;
	}

}
