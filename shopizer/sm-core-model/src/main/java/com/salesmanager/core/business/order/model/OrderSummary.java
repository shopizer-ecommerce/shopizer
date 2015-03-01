package com.salesmanager.core.business.order.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.salesmanager.core.business.shipping.model.ShippingSummary;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCartItem;


/**
 * This object is used as input object for many services
 * such as order total calculation and tax calculation
 * @author Carl Samson
 *
 */
public class OrderSummary implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ShippingSummary shippingSummary;
	private List<ShoppingCartItem> products = new ArrayList<ShoppingCartItem>();

	public void setProducts(List<ShoppingCartItem> products) {
		this.products = products;
	}
	public List<ShoppingCartItem> getProducts() {
		return products;
	}
	public void setShippingSummary(ShippingSummary shippingSummary) {
		this.shippingSummary = shippingSummary;
	}
	public ShippingSummary getShippingSummary() {
		return shippingSummary;
	}

}
