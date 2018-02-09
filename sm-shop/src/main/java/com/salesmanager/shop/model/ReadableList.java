package com.salesmanager.shop.model;

import java.io.Serializable;

public abstract class ReadableList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalCount;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
