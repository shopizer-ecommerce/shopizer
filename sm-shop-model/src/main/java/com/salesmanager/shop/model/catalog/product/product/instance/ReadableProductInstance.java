package com.salesmanager.shop.model.catalog.product.product.instance;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.ReadableImage;
import com.salesmanager.shop.model.catalog.product.inventory.ReadableInventory;
import com.salesmanager.shop.model.catalog.product.variation.ReadableProductVariation;

public class ReadableProductInstance extends ProductInstance {

	private static final long serialVersionUID = 1L;
	
	private ReadableProductVariation variant;
	private ReadableProductVariation variantValue;
	private String code;
	private List<ReadableImage> images = new ArrayList<ReadableImage>();
	private List<ReadableInventory> inventory = new ArrayList<ReadableInventory>();
	
	public ReadableProductVariation getVariant() {
		return variant;
	}
	public void setVariant(ReadableProductVariation variant) {
		this.variant = variant;
	}
	public ReadableProductVariation getVariantValue() {
		return variantValue;
	}
	public void setVariantValue(ReadableProductVariation variantValue) {
		this.variantValue = variantValue;
	}
	public List<ReadableImage> getImages() {
		return images;
	}
	public void setImages(List<ReadableImage> images) {
		this.images = images;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<ReadableInventory> getInventory() {
		return inventory;
	}
	public void setInventory(List<ReadableInventory> inventory) {
		this.inventory = inventory;
	}

}
