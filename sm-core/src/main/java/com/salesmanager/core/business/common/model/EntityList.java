package com.salesmanager.core.business.common.model;

import java.io.Serializable;

public class EntityList implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6135941880202635567L;
	private int totalCount;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
