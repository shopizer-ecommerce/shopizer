package com.salesmanager.shop.model.catalog.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.salesmanager.shop.model.catalog.category.Category;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductAttribute;



public class PersistableProduct extends ProductEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProductDescription> descriptions = new ArrayList<ProductDescription>();
	private List<PersistableProductAttribute> attributes;//persist attribute and save reference
	private List<PersistableImage> images;//persist images and save reference
	private List<PersistableProductPrice> productPrices;//to be set when using discounts
	private List<Category> categories = new ArrayList<Category>();
	private List<RelatedProduct> relatedProducts;//save reference
	private String type;
	
	//RENTAL
	private RentalOwner owner;
	
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
	public List<RelatedProduct> getRelatedProducts() {
		return relatedProducts;
	}
	public void setRelatedProducts(List<RelatedProduct> relatedProducts) {
		this.relatedProducts = relatedProducts;
	}
	public void setAttributes(List<PersistableProductAttribute> attributes) {
		this.attributes = attributes;
	}
	public List<PersistableProductAttribute> getAttributes() {
		return attributes;
	}
	public List<PersistableProductPrice> getProductPrices() {
		return productPrices;
	}
	public void setProductPrices(List<PersistableProductPrice> productPrices) {
		this.productPrices = productPrices;
	}
	public RentalOwner getOwner() {
		return owner;
	}
	public void setOwner(RentalOwner owner) {
		this.owner = owner;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
