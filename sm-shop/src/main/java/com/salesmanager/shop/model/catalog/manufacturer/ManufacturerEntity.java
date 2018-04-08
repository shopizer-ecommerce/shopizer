package com.salesmanager.shop.model.catalog.manufacturer;

import java.io.Serializable;



public class ManufacturerEntity extends Manufacturer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int order;

	public void setOrder(int order) {
		this.order = order;
	}
	public int getOrder() {
		return order;
	}


}
