package com.salesmanager.shop.model.entity;

import java.io.Serializable;

public abstract class ReadableList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalPages;//totalPages
	private int number;//number of record in current page
	private long recordsTotal;//total number of records in db
	private int recordsFiltered;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalCount) {
		this.totalPages = totalCount;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}