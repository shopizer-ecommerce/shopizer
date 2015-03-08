package com.salesmanager.web.entity.order;

import java.io.Serializable;
import java.util.List;

import com.salesmanager.core.business.reference.currency.model.Currency;
import com.salesmanager.web.entity.customer.Address;
import com.salesmanager.web.entity.customer.ReadableCustomer;

public class ReadableOrder extends OrderEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReadableCustomer customer;
	private List<ReadableOrderProduct> products;
	private Currency currencyModel;
	
	private Address billing;
	private Address delivery;
	
	
	
	public void setCustomer(ReadableCustomer customer) {
		this.customer = customer;
	}
	public ReadableCustomer getCustomer() {
		return customer;
	}
	public OrderTotal getTotal() {
		return total;
	}
	public void setTotal(OrderTotal total) {
		this.total = total;
	}
	public OrderTotal getTax() {
		return tax;
	}
	public void setTax(OrderTotal tax) {
		this.tax = tax;
	}
	public OrderTotal getShipping() {
		return shipping;
	}
	public void setShipping(OrderTotal shipping) {
		this.shipping = shipping;
	}

	public List<ReadableOrderProduct> getProducts() {
		return products;
	}
	public void setProducts(List<ReadableOrderProduct> products) {
		this.products = products;
	}

	public Currency getCurrencyModel() {
		return currencyModel;
	}
	public void setCurrencyModel(Currency currencyModel) {
		this.currencyModel = currencyModel;
	}

	public Address getBilling() {
		return billing;
	}
	public void setBilling(Address billing) {
		this.billing = billing;
	}

	public Address getDelivery() {
		return delivery;
	}
	public void setDelivery(Address delivery) {
		this.delivery = delivery;
	}

	private OrderTotal total;
	private OrderTotal tax;
	private OrderTotal shipping;

}
