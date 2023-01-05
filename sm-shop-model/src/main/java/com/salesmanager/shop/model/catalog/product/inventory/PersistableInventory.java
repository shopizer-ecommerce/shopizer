package com.salesmanager.shop.model.catalog.product.inventory;

import java.util.List;
import com.salesmanager.shop.model.catalog.product.PersistableProductPrice;

import javax.validation.constraints.NotNull;

public class PersistableInventory extends InventoryEntity {

	/**
	 * An inventory for a given product and possibly a given variant
	 */
	private static final long serialVersionUID = 1L;
	private String store;
	@NotNull
	private Long productId;
	private Long variant;
	private List<PersistableProductPrice> prices;

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public List<PersistableProductPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<PersistableProductPrice> prices) {
		this.prices = prices;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getVariant() {
		return variant;
	}

	public void setVariant(Long instance) {
		this.variant = instance;
	}

}
