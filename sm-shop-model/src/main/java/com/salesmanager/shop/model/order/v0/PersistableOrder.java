package com.salesmanager.shop.model.order.v0;

import java.io.Serializable;
import java.util.List;

import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.order.OrderEntity;
import com.salesmanager.shop.model.order.PersistableOrderProduct;


public class PersistableOrder extends OrderEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PersistableCustomer customer;//might already exist if id > 0, otherwise persist
	private List<PersistableOrderProduct> orderProductItems;
	private boolean shipToBillingAdress = true;
	private boolean shipToDeliveryAddress = false;
	
	
	public void setOrderProductItems(List<PersistableOrderProduct> orderProductItems) {
		this.orderProductItems = orderProductItems;
	}
	public List<PersistableOrderProduct> getOrderProductItems() {
		return orderProductItems;
	}
	public void setCustomer(PersistableCustomer customer) {
		this.customer = customer;
	}
	public PersistableCustomer getCustomer() {
		return customer;
	}
	public boolean isShipToBillingAdress() {
		return shipToBillingAdress;
	}
	public void setShipToBillingAdress(boolean shipToBillingAdress) {
		this.shipToBillingAdress = shipToBillingAdress;
	}
	public boolean isShipToDeliveryAddress() {
		return shipToDeliveryAddress;
	}
	public void setShipToDeliveryAddress(boolean shipToDeliveryAddress) {
		this.shipToDeliveryAddress = shipToDeliveryAddress;
	}


}
