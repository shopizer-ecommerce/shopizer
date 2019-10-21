package com.salesmanager.shop.model.entity;

import java.io.Serializable;

public abstract class ReadableList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalPages;//totalPages
	private int recordsTotal;
	private int draw;
	private int recordsFiltered;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalCount) {
		this.totalPages = totalCount;
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
