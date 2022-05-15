package com.salesmanager.shop.model.catalog.product.product.instanceGroup;

import java.util.List;

import com.salesmanager.shop.model.catalog.product.product.instance.ProductInstance;

public class PersistableProductInstanceGroup extends ProductInstance {

	private static final long serialVersionUID = 1L;
	
	List<Long> productInstances = null;

	public List<Long> getProductInstances() {
		return productInstances;
	}

	public void setProductInstances(List<Long> productInstances) {
		this.productInstances = productInstances;
	}

}
