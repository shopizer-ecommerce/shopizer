package com.salesmanager.shop.model.shoppingcart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.product.variant.ReadableProductVariant;
import com.salesmanager.shop.model.order.total.ReadableOrderTotal;

/**
 * Compatible with v1 + v2
 * @author c.samson
 *
 */
public class ReadableShoppingCart extends ShoppingCartEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private BigDecimal subtotal;


	private String displaySubTotal;
	private BigDecimal total;
	private String displayTotal;
	private int quantity;
	private Long order;
	private String promoCode;
	
	private ReadableProductVariant variant;
	
	List<ReadableShoppingCartItem> products = new ArrayList<ReadableShoppingCartItem>();
	List<ReadableOrderTotal> totals;
	
	private Long customer;



	public Long getCustomer() {
		return customer;
	}



	public void setCustomer(Long customer) {
		this.customer = customer;
	}



	public List<ReadableOrderTotal> getTotals() {
		return totals;
	}



	public void setTotals(List<ReadableOrderTotal> totals) {
		this.totals = totals;
	}



	public List<ReadableShoppingCartItem> getProducts() {
		return products;
	}



	public void setProducts(List<ReadableShoppingCartItem> products) {
		this.products = products;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}
	
	public BigDecimal getSubtotal() {
		return subtotal;
	}



	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}



	public String getDisplaySubTotal() {
		return displaySubTotal;
	}



	public void setDisplaySubTotal(String displaySubTotal) {
		this.displaySubTotal = displaySubTotal;
	}



	public BigDecimal getTotal() {
		return total;
	}



	public void setTotal(BigDecimal total) {
		this.total = total;
	}



	public String getDisplayTotal() {
		return displayTotal;
	}



	public void setDisplayTotal(String displayTotal) {
		this.displayTotal = displayTotal;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public Long getOrder() {
		return order;
	}



	public void setOrder(Long order) {
		this.order = order;
	}



	public String getPromoCode() {
		return promoCode;
	}



	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}



	public ReadableProductVariant getVariant() {
		return variant;
	}



	public void setVariant(ReadableProductVariant variant) {
		this.variant = variant;
	}




}
