package com.salesmanager.shop.model.catalog.catalog;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.entity.ReadableList;

public class ReadableCatalogList extends ReadableList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ReadableCatalog> catalogs = new ArrayList<ReadableCatalog>();
	public List<ReadableCatalog> getCatalogs() {
		return catalogs;
	}
	public void setCatalogs(List<ReadableCatalog> catalogs) {
		this.catalogs = catalogs;
	}

}
