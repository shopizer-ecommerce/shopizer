package com.salesmanager.shop.model.catalog.product.product.instanceGroup;

import java.util.List;

public class PersistableProductInstanceGroup extends ProductInstanceGroup {

	private static final long serialVersionUID = 1L;
	
	List<Long> productInstances = null;

	public List<Long> getProductInstances() {
		return productInstances;
	}

	public void setProductInstances(List<Long> productInstances) {
		this.productInstances = productInstances;
	}

}
