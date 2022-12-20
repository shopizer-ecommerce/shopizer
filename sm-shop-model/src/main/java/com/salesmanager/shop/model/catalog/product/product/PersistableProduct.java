package com.salesmanager.shop.model.catalog.product.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.salesmanager.shop.model.catalog.category.Category;
import com.salesmanager.shop.model.catalog.product.PersistableImage;
import com.salesmanager.shop.model.catalog.product.ProductDescription;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductAttribute;
import com.salesmanager.shop.model.catalog.product.product.variant.PersistableProductVariant;



public class PersistableProduct extends ProductEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProductDescription> descriptions = new ArrayList<ProductDescription>();
	private List<PersistableProductAttribute> attributes = new ArrayList<PersistableProductAttribute>();//persist attribute and save reference
	private List<PersistableImage> images;//persist images and save reference
	private List<Category> categories = new ArrayList<Category>();
	private PersistableProductInventory inventory;
	private List<PersistableProductVariant> variants = new ArrayList<PersistableProductVariant>();
	private String type;

	public List<ProductDescription> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(List<ProductDescription> descriptions) {
		this.descriptions = descriptions;
	}

	public List<PersistableImage> getImages() {
		return images;
	}
	public void setImages(List<PersistableImage> images) {
		this.images = images;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public void setAttributes(List<PersistableProductAttribute> attributes) {
		this.attributes = attributes;
	}
	public List<PersistableProductAttribute> getAttributes() {
		return attributes;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public PersistableProductInventory getInventory() {
		return inventory;
	}
	public void setInventory(PersistableProductInventory inventory) {
		this.inventory = inventory;
	}
	public List<PersistableProductVariant> getVariants() {
		return variants;
	}
	public void setVariants(List<PersistableProductVariant> variants) {
		this.variants = variants;
	}

}
