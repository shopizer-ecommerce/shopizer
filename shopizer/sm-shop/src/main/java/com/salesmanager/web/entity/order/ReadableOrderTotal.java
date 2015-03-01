package com.salesmanager.web.entity.order;

import java.io.Serializable;

public class ReadableOrderTotal extends OrderTotal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String total;
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}

}
