package com.salesmanager.shop.model.order.v0;

import com.salesmanager.core.model.reference.currency.Currency;
import com.salesmanager.shop.model.customer.ReadableBilling;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.model.customer.ReadableDelivery;
import com.salesmanager.shop.model.customer.address.Address;
import com.salesmanager.shop.model.order.OrderEntity;
import com.salesmanager.shop.model.order.ReadableOrderProduct;
import com.salesmanager.shop.model.order.total.OrderTotal;
import com.salesmanager.shop.model.store.ReadableMerchantStore;

import java.io.Serializable;
import java.util.List;

@Deprecated
public class ReadableOrder extends OrderEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReadableCustomer customer;
	private List<ReadableOrderProduct> products;
	private Currency currencyModel;
	
	private ReadableBilling billing;
	private ReadableDelivery delivery;
	private ReadableMerchantStore store;
	
	
	
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

	//public Currency getCurrencyModel() {
	//	return currencyModel;
	//}
	public void setCurrencyModel(Currency currencyModel) {
		this.currencyModel = currencyModel;
	}

	public ReadableBilling getBilling() {
		return billing;
	}
	public void setBilling(ReadableBilling billing) {
		this.billing = billing;
	}

	public Address getDelivery() {
		return delivery;
	}
	public void setDelivery(ReadableDelivery delivery) {
		this.delivery = delivery;
	}

	public ReadableMerchantStore getStore() {
		return store;
	}
	public void setStore(ReadableMerchantStore store) {
		this.store = store;
	}

	private OrderTotal total;
	private OrderTotal tax;
	private OrderTotal shipping;

}
