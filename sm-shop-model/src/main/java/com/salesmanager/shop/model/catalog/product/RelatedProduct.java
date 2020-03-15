package com.salesmanager.shop.model.catalog.product;

import java.io.Serializable;

public class RelatedProduct extends Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String relationShipType; //RELATED_ITEM ~ BUNDLED_ITEM
	public void setRelationShipType(String relationShipType) {
		this.relationShipType = relationShipType;
	}
	public String getRelationShipType() {
		return relationShipType;
	}

}
