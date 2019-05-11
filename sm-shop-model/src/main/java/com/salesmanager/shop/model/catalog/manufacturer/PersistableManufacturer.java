package com.salesmanager.shop.model.catalog.manufacturer;

import java.io.Serializable;
import java.util.List;

public class PersistableManufacturer extends ManufacturerEntity implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ManufacturerDescription> descriptions;
	public void setDescriptions(List<ManufacturerDescription> descriptions) {
		this.descriptions = descriptions;
	}
	public List<ManufacturerDescription> getDescriptions() {
		return descriptions;
	}

}
