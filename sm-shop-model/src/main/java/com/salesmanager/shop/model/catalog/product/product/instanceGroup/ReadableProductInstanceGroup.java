package com.salesmanager.shop.model.catalog.product.product.instanceGroup;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.ReadableImage;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;

public class ReadableProductInstanceGroup extends ProductInstanceGroup {

	private static final long serialVersionUID = 1L;
	
	List<ReadableImage> images = new ArrayList<ReadableImage>();
	
	private List<ReadableProductInstance> productInstances = new ArrayList<ReadableProductInstance>();
	public List<ReadableProductInstance> getProductInstances() {
		return productInstances;
	}
	public void setProductInstances(List<ReadableProductInstance> productInstances) {
		this.productInstances = productInstances;
	}
	public List<ReadableImage> getImages() {
		return images;
	}
	public void setImages(List<ReadableImage> images) {
		this.images = images;
	}

}
