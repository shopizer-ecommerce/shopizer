package com.salesmanager.core.model.catalog.product.attribute;

import java.util.List;

import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.merchant.MerchantStore;

/**
 * Create a list of option and option value in order to accelerate and 
 * prepare product attribute creation
 * @author carlsamson
 *
 */
public class ProductOptionSet extends SalesManagerEntity<Long, ProductOptionSet> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private ProductOption option;
	private List<ProductOptionValue> values;
	private MerchantStore store;
	
	
	public ProductOption getOption() {
		return option;
	}
	public void setOption(ProductOption option) {
		this.option = option;
	}
	public List<ProductOptionValue> getValues() {
		return values;
	}
	public void setValues(List<ProductOptionValue> values) {
		this.values = values;
	}
	public MerchantStore getStore() {
		return store;
	}
	public void setStore(MerchantStore store) {
		this.store = store;
	}
	@Override
	public Long getId() {
		return this.id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
