package com.salesmanager.web.entity.shoppingcart;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.salesmanager.web.entity.ShopEntity;
import com.salesmanager.web.entity.order.OrderTotal;

@Component
@Scope(value = "prototype")
public class ShoppingCartData extends ShopEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String code;
	private int quantity;
	private String total;
	private String subTotal;
	
	private List<OrderTotal> totals;//calculated from OrderTotalSummary
	private List<ShoppingCartItem> shoppingCartItems;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}
	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	public List<OrderTotal> getTotals() {
		return totals;
	}
	public void setTotals(List<OrderTotal> totals) {
		this.totals = totals;
	}



}
