package com.salesmanager.shop.model.order;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.salesmanager.core.model.order.OrderTotalSummary;
import com.salesmanager.core.model.shipping.ShippingOption;
import com.salesmanager.core.model.shipping.ShippingSummary;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.shop.model.order.v0.PersistableOrder;


/**
 * Orders saved on the website
 * @author Carl Samson
 *
 */
public class ShopOrder extends PersistableOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ShoppingCartItem> shoppingCartItems;//overrides parent API list of shoppingcartitem
	private String cartCode = null;

	private OrderTotalSummary orderTotalSummary;//The order total displayed to the end user. That object will be used when committing the order
	
	
	private ShippingSummary shippingSummary;
	private ShippingOption selectedShippingOption = null;//Default selected option
	
	private String defaultPaymentMethodCode = null;
	
	
	private String paymentMethodType = null;//user selected payment type
	private Map<String,String> payment;//user payment information
	
	private String errorMessage = null;

	
	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}
	public List<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}

	public void setOrderTotalSummary(OrderTotalSummary orderTotalSummary) {
		this.orderTotalSummary = orderTotalSummary;
	}
	public OrderTotalSummary getOrderTotalSummary() {
		return orderTotalSummary;
	}

	public ShippingSummary getShippingSummary() {
		return shippingSummary;
	}
	public void setShippingSummary(ShippingSummary shippingSummary) {
		this.shippingSummary = shippingSummary;
	}
	public ShippingOption getSelectedShippingOption() {
		return selectedShippingOption;
	}
	public void setSelectedShippingOption(ShippingOption selectedShippingOption) {
		this.selectedShippingOption = selectedShippingOption;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getPaymentMethodType() {
		return paymentMethodType;
	}
	public void setPaymentMethodType(String paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}
	public Map<String,String> getPayment() {
		return payment;
	}
	public void setPayment(Map<String,String> payment) {
		this.payment = payment;
	}
	public String getDefaultPaymentMethodCode() {
		return defaultPaymentMethodCode;
	}
	public void setDefaultPaymentMethodCode(String defaultPaymentMethodCode) {
		this.defaultPaymentMethodCode = defaultPaymentMethodCode;
	}
	public String getCartCode() {
		return cartCode;
	}
	public void setCartCode(String cartCode) {
		this.cartCode = cartCode;
	}



}
