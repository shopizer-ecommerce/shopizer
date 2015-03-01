package com.salesmanager.web.populator.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.Order;
import com.salesmanager.core.business.order.model.OrderTotal;
import com.salesmanager.core.business.order.model.OrderTotalType;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.utils.AbstractDataPopulator;
import com.salesmanager.web.entity.customer.Address;
import com.salesmanager.web.entity.order.ReadableOrder;

public class ReadableOrderPopulator extends
		AbstractDataPopulator<Order, ReadableOrder> {

	@Override
	public ReadableOrder populate(Order source, ReadableOrder target,
			MerchantStore store, Language language) throws ConversionException {
		
		
		
		target.setId(source.getId());
		target.setDatePurchased(source.getDatePurchased());
		target.setOrderStatus(source.getStatus());
		target.setCurrency(source.getCurrency().getCode());
		target.setCurrencyModel(source.getCurrency());
		com.salesmanager.web.entity.order.OrderTotal taxTotal = null;
		com.salesmanager.web.entity.order.OrderTotal shippingTotal = null;
		
		
		if(source.getBilling()!=null) {
			Address address = new Address();
			address.setCity(source.getBilling().getCity());
			address.setAddress(source.getBilling().getAddress());
			address.setCompany(source.getBilling().getCompany());
			address.setFirstName(source.getBilling().getFirstName());
			address.setLastName(source.getBilling().getLastName());
			address.setPostalCode(source.getBilling().getPostalCode());
			address.setPhone(source.getBilling().getTelephone());
			if(source.getBilling().getCountry()!=null) {
				address.setCountry(source.getBilling().getCountry().getIsoCode());
			}
			if(source.getBilling().getZone()!=null) {
				address.setZone(source.getBilling().getZone().getCode());
			}
			
			target.setBilling(address);
		}
		
		if(source.getDelivery()!=null) {
			Address address = new Address();
			address.setCity(source.getDelivery().getCity());
			address.setAddress(source.getDelivery().getAddress())
;			address.setCompany(source.getDelivery().getCompany());
			address.setFirstName(source.getDelivery().getFirstName());
			address.setLastName(source.getDelivery().getLastName());
			address.setPostalCode(source.getDelivery().getPostalCode());
			address.setPhone(source.getDelivery().getTelephone());
			if(source.getDelivery().getCountry()!=null) {
				address.setCountry(source.getDelivery().getCountry().getIsoCode());
			}
			if(source.getDelivery().getZone()!=null) {
				address.setZone(source.getDelivery().getZone().getCode());
			}
			
			target.setDelivery(address);
		}
		
		List<com.salesmanager.web.entity.order.OrderTotal> totals = new ArrayList<com.salesmanager.web.entity.order.OrderTotal>();
		for(OrderTotal t : source.getOrderTotal()) {
			if(t.getOrderTotalType()==null) {
				continue;
			}
			if(t.getOrderTotalType().name().equals(OrderTotalType.TOTAL.name())) {
				com.salesmanager.web.entity.order.OrderTotal totalTotal = createTotal(t);
				target.setTotal(totalTotal);
				totals.add(totalTotal);
			}
			else if(t.getOrderTotalType().name().equals(OrderTotalType.TAX.name())) {
				com.salesmanager.web.entity.order.OrderTotal totalTotal = createTotal(t);
				if(taxTotal==null) {
					taxTotal = totalTotal;
				} else {
					BigDecimal v = taxTotal.getValue();
					v = v.add(totalTotal.getValue());
					taxTotal.setValue(v);
				}
				target.setTax(totalTotal);
				totals.add(totalTotal);
			}
			else if(t.getOrderTotalType().name().equals(OrderTotalType.SHIPPING.name())) {
				com.salesmanager.web.entity.order.OrderTotal totalTotal = createTotal(t);
				if(shippingTotal==null) {
					shippingTotal = totalTotal;
				} else {
					BigDecimal v = shippingTotal.getValue();
					v = v.add(totalTotal.getValue());
					shippingTotal.setValue(v);
				}
				target.setShipping(totalTotal);
				totals.add(totalTotal);
			}
			else if(t.getOrderTotalType().name().equals(OrderTotalType.HANDLING.name())) {
				com.salesmanager.web.entity.order.OrderTotal totalTotal = createTotal(t);
				if(shippingTotal==null) {
					shippingTotal = totalTotal;
				} else {
					BigDecimal v = shippingTotal.getValue();
					v = v.add(totalTotal.getValue());
					shippingTotal.setValue(v);
				}
				target.setShipping(totalTotal);
				totals.add(totalTotal);
			}
			else if(t.getOrderTotalType().name().equals(OrderTotalType.SUBTOTAL.name())) {
				com.salesmanager.web.entity.order.OrderTotal subTotal = createTotal(t);
				totals.add(subTotal);
				
			}
			else {
				com.salesmanager.web.entity.order.OrderTotal otherTotal = createTotal(t);
				totals.add(otherTotal);
			}
		}
		
		target.setTotals(totals);
		
		return target;
	}
	
	private com.salesmanager.web.entity.order.OrderTotal createTotal(OrderTotal t) {
		com.salesmanager.web.entity.order.OrderTotal totalTotal = new com.salesmanager.web.entity.order.OrderTotal();
		totalTotal.setCode(t.getOrderTotalCode());
		totalTotal.setId(t.getId());
		totalTotal.setModule(t.getModule());
		totalTotal.setOrder(t.getSortOrder());
		totalTotal.setValue(t.getValue());
		return totalTotal;
	}

	@Override
	protected ReadableOrder createTarget() {

		return null;
	}

}
