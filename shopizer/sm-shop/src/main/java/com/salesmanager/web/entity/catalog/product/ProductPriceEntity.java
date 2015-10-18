package com.salesmanager.web.entity.catalog.product;

import java.io.Serializable;

import com.salesmanager.web.entity.Entity;


/**
 * A product entity is used by services API
 * to populate or retrieve a Product price entity
 * @author Carl Samson
 *
 */
public class ProductPriceEntity extends Entity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean discounted = false;
	private String discountStatrDate;
	private String discountEndDate;
	private boolean defaultPrice = false;
	
	public boolean isDiscounted() {
		return discounted;
	}
	public void setDiscounted(boolean discounted) {
		this.discounted = discounted;
	}
	public String getDiscountStatrDate() {
		return discountStatrDate;
	}
	public void setDiscountStatrDate(String discountStatrDate) {
		this.discountStatrDate = discountStatrDate;
	}
	public String getDiscountEndDate() {
		return discountEndDate;
	}
	public void setDiscountEndDate(String discountEndDate) {
		this.discountEndDate = discountEndDate;
	}
	public boolean isDefaultPrice() {
		return defaultPrice;
	}
	public void setDefaultPrice(boolean defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	
	
	


}
