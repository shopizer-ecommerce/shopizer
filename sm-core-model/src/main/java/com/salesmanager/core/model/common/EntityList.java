package com.salesmanager.core.model.common;

import java.io.Serializable;

public class EntityList implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6135941880202635567L;
	private int totalCount;
	private int totalPage;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
