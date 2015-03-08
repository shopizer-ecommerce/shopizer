package com.salesmanager.web.entity.customer.attribute;

import java.io.Serializable;
import java.util.List;

public class PersistableCustomerOption extends CustomerOptionEntity
		implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CustomerOptionDescription> descriptions;

	public void setDescriptions(List<CustomerOptionDescription> descriptions) {
		this.descriptions = descriptions;
	}

	public List<CustomerOptionDescription> getDescriptions() {
		return descriptions;
	}

}
