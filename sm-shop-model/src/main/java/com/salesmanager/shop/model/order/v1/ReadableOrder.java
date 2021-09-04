package com.salesmanager.shop.model.order.v1;

import java.util.List;

import com.salesmanager.core.model.shipping.ShippingOption;
import com.salesmanager.shop.model.customer.ReadableBilling;
import com.salesmanager.shop.model.customer.ReadableDelivery;
import com.salesmanager.shop.model.order.ReadableOrderProduct;
import com.salesmanager.shop.model.order.total.ReadableTotal;
import com.salesmanager.shop.model.order.transaction.ReadablePayment;

public class ReadableOrder extends Order {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ReadableBilling billing;
	private ReadableDelivery delivery;
	private ShippingOption shippingOption;               
	private ReadablePayment payment;
	private ReadableTotal total;
	private List<ReadableOrderProduct> products;
	
	public List<ReadableOrderProduct> getProducts() {
		return products;
	}
	public void setProducts(List<ReadableOrderProduct> products) {
		this.products = products;
	}
	public ReadableDelivery getDelivery() {
		return delivery;
	}
	public void setDelivery(ReadableDelivery delivery) {
		this.delivery = delivery;
	}
	public ReadablePayment getPayment() {
		return payment;
	}
	public void setPayment(ReadablePayment payment) {
		this.payment = payment;
	}
	public ReadableTotal getTotal() {
		return total;
	}
	public void setTotal(ReadableTotal total) {
		this.total = total;
	}
	public ShippingOption getShippingOption() {
		return shippingOption;
	}
	public void setShippingOption(ShippingOption shippingOption) {
		this.shippingOption = shippingOption;
	}
	public ReadableBilling getBilling() {
		return billing;
	}
	public void setBilling(ReadableBilling billing) {
		this.billing = billing;
	}

}
