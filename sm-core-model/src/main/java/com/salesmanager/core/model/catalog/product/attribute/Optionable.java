package com.salesmanager.core.model.catalog.product.attribute;

public interface Optionable {
	
	ProductOption getProductOption();
	void setProductOption(ProductOption option);
	
	ProductOptionValue getProductOptionValue();
	void setProductOptionValue(ProductOptionValue optionValue);

}
