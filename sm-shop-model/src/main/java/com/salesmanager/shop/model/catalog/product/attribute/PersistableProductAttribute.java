package com.salesmanager.shop.model.catalog.product.attribute;

import java.io.Serializable;
import java.math.BigDecimal;

import com.salesmanager.shop.model.catalog.product.attribute.api.ProductAttributeEntity;

public class PersistableProductAttribute extends ProductAttributeEntity
		implements Serializable {
	
	private BigDecimal productAttributeWeight;
	private BigDecimal productAttributePrice;
	private Long productId;
	
	private ProductOption option;
	private ProductOptionValue optionValue;
	public void setOptionValue(ProductOptionValue optionValue) {
		this.optionValue = optionValue;
	}
	public ProductOptionValue getOptionValue() {
		return optionValue;
	}
	public void setOption(ProductOption option) {
		this.option = option;
	}
	public ProductOption getOption() {
		return option;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BigDecimal getProductAttributeWeight() {
		return productAttributeWeight;
	}
	public void setProductAttributeWeight(BigDecimal productAttributeWeight) {
		this.productAttributeWeight = productAttributeWeight;
	}
	public BigDecimal getProductAttributePrice() {
		return productAttributePrice;
	}
	public void setProductAttributePrice(BigDecimal productAttributePrice) {
		this.productAttributePrice = productAttributePrice;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
