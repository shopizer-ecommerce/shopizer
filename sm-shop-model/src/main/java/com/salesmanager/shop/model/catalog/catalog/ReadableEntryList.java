package com.salesmanager.shop.model.catalog.catalog;

import com.salesmanager.shop.model.entity.ReadableList;

import java.util.ArrayList;
import java.util.List;

public class ReadableEntryList<T> extends ReadableList {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<T> catalogEntry = new ArrayList<>();
	public List<T> getCatalogEntry() {
		return catalogEntry;
	}
	public void setCatalogEntry(List<T> catalogEntry) {
		this.catalogEntry = catalogEntry;
	}

}
