package com.salesmanager.core.model.catalog.product;

import java.util.List;

import com.salesmanager.core.model.catalog.product.attribute.AttributeCriteria;
import com.salesmanager.core.model.common.Criteria;

public class ProductCriteria extends Criteria {
	
	public static final String ORIGIN_SHOP = "shop";
	public static final String ORIGIN_ADMIN = "admin";
	
	private String productName;
	private List<AttributeCriteria> attributeCriteria;
	private String origin = ORIGIN_SHOP;

	
	private Boolean available = null;
	
	private List<Long> categoryIds;
	private List<String> availabilities;
	private List<Long> productIds;
	private List<Long> optionValueIds;
	private String sku;
	
	//V2
	private List<String> optionValueCodes;
	private String option;
	
	private String status;
	
	private Long manufacturerId = null;
	
	private Long ownerId = null;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public List<String> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<String> availabilities) {
		this.availabilities = availabilities;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public void setAttributeCriteria(List<AttributeCriteria> attributeCriteria) {
		this.attributeCriteria = attributeCriteria;
	}

	public List<AttributeCriteria> getAttributeCriteria() {
		return attributeCriteria;
	}

	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}

	public List<Long> getProductIds() {
		return productIds;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public List<Long> getOptionValueIds() {
		return optionValueIds;
	}

	public void setOptionValueIds(List<Long> optionValueIds) {
		this.optionValueIds = optionValueIds;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public List<String> getOptionValueCodes() {
		return optionValueCodes;
	}

	public void setOptionValueCodes(List<String> optionValueCodes) {
		this.optionValueCodes = optionValueCodes;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}



}
