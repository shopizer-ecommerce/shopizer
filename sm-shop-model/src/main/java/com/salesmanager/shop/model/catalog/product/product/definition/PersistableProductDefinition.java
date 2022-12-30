package com.salesmanager.shop.model.catalog.product.product.definition;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.category.Category;
import com.salesmanager.shop.model.catalog.product.ProductDescription;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductAttribute;

public class PersistableProductDefinition extends ProductDefinition {

	/**
	 * type and manufacturer are String type corresponding to the unique code
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProductDescription> descriptions = new ArrayList<ProductDescription>();
	private List<PersistableProductAttribute> properties = new ArrayList<PersistableProductAttribute>();
	private List<Category> categories = new ArrayList<Category>();
	private String type;
	private String manufacturer;
	private BigDecimal price;
	private int quantity;
	public List<ProductDescription> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(List<ProductDescription> descriptions) {
		this.descriptions = descriptions;
	}
	public List<PersistableProductAttribute> getProperties() {
		return properties;
	}
	public void setProperties(List<PersistableProductAttribute> properties) {
		this.properties = properties;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
