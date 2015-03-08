package com.salesmanager.web.entity.catalog.category;

import java.io.Serializable;
import java.util.List;

public class PersistableCategory extends CategoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CategoryDescription> descriptions;//always persist description
	private Category parent;//saves a reference 
	public List<CategoryDescription> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(List<CategoryDescription> descriptions) {
		this.descriptions = descriptions;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public Category getParent() {
		return parent;
	}

}
