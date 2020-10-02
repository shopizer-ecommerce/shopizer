package com.salesmanager.shop.model.catalog.catalog;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.entity.ReadableList;

public class ReadableCatalogCategoryEntryList extends ReadableList {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ReadableCatalogCategoryEntry> catalogEntry = new ArrayList<ReadableCatalogCategoryEntry>();
	public List<ReadableCatalogCategoryEntry> getCatalogEntry() {
		return catalogEntry;
	}
	public void setCatalogEntry(List<ReadableCatalogCategoryEntry> catalogEntry) {
		this.catalogEntry = catalogEntry;
	}

}
