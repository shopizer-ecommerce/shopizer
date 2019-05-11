package com.salesmanager.shop.model;

import java.io.Serializable;

public abstract class ReadableList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalCount;
	private int recordsTotal;
	private int draw;
	private int recordsFiltered;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

}
