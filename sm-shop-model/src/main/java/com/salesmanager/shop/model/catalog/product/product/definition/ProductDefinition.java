package com.salesmanager.shop.model.catalog.product.product.definition;

import com.salesmanager.shop.model.entity.Entity;

/**
 * Applies to product version 2 management
 * @author carlsamson
 *
 */
public class ProductDefinition extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean visible = true;
	private Double rating = 0D;
	private int ratingCount;
	private String dateAvailable;
	private String identifier;
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public int getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}
	public String getDateAvailable() {
		return dateAvailable;
	}
	public void setDateAvailable(String dateAvailable) {
		this.dateAvailable = dateAvailable;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}
