package com.salesmanager.shop.model.catalog.product.product.definition;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.model.catalog.product.ProductDescription;
import com.salesmanager.shop.model.catalog.product.ReadableImage;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductAttribute;
import com.salesmanager.shop.model.catalog.product.inventory.ReadableInventory;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;

public class ReadableProductDefinition extends ProductDefinition {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReadableProductType type;
	private List<ReadableCategory> categories = new ArrayList<ReadableCategory>();
	private ReadableManufacturer manufacturer;
	private ProductDescription description = null;
	private List<PersistableProductAttribute> properties = new ArrayList<PersistableProductAttribute>();
	private List<ReadableImage> images = new ArrayList<ReadableImage>();
	private ReadableInventory inventory;
	
	
	public ReadableProductType getType() {
		return type;
	}
	public void setType(ReadableProductType type) {
		this.type = type;
	}
	public List<ReadableCategory> getCategories() {
		return categories;
	}
	public void setCategories(List<ReadableCategory> categories) {
		this.categories = categories;
	}
	public ReadableManufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(ReadableManufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	public List<PersistableProductAttribute> getProperties() {
		return properties;
	}
	public void setProperties(List<PersistableProductAttribute> properties) {
		this.properties = properties;
	}
	public ProductDescription getDescription() {
		return description;
	}
	public void setDescription(ProductDescription description) {
		this.description = description;
	}
	public List<ReadableImage> getImages() {
		return images;
	}
	public void setImages(List<ReadableImage> images) {
		this.images = images;
	}
	public ReadableInventory getInventory() {
		return inventory;
	}
	public void setInventory(ReadableInventory inventory) {
		this.inventory = inventory;
	}


}
