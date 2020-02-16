package com.salesmanager.shop.model.shoppingcart;

import java.io.Serializable;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.attribute.ProductAttribute;

/**
 * Compatible with v1
 * @author c.samson
 *
 */
public class PersistableShoppingCartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long product;//product id
	private int quantity;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	private List<ProductAttribute> attributes;
	public Long getProduct() {
		return product;
	}
	public void setProduct(Long product) {
		this.product = product;
	}
	public List<ProductAttribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<ProductAttribute> attributes) {
		this.attributes = attributes;
	}

}
