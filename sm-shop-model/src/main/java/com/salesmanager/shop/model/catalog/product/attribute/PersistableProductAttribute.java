package com.salesmanager.shop.model.catalog.product.attribute;

import java.io.Serializable;
import java.math.BigDecimal;

import com.salesmanager.shop.model.catalog.product.attribute.api.ProductAttributeEntity;

public class PersistableProductAttribute extends ProductAttributeEntity
		implements Serializable {
	
	private BigDecimal productAttributeWeight;
	private BigDecimal productAttributePrice;
	private Long productId;
	
	private ProductPropertyOption option;
	private PersistableProductOptionValue optionValue;


	public void setOption(ProductPropertyOption option) {
		this.option = option;
	}
	public ProductPropertyOption getOption() {
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
	public PersistableProductOptionValue getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(PersistableProductOptionValue optionValue) {
		this.optionValue = optionValue;
	}

}
