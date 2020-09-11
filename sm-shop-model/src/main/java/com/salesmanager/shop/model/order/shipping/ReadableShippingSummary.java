package com.salesmanager.shop.model.order.shipping;

import com.salesmanager.core.model.shipping.ShippingOption;
import com.salesmanager.shop.model.customer.ReadableDelivery;
import com.salesmanager.shop.model.customer.address.Address;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadableShippingSummary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal shipping;
	private BigDecimal handling;
	private String shippingModule;
	private String shippingOption;
	private boolean freeShipping;
	private boolean taxOnShipping;
	private boolean shippingQuote;
	private String shippingText;
	private String handlingText;
	private ReadableDelivery delivery;
	
	
	private ShippingOption selectedShippingOption = null;//Default selected option
	private List<ShippingOption> shippingOptions = null;
	
	/** additional information that comes from the quote **/
	private Map<String,String> quoteInformations = new HashMap<String,String>();
	
	
	public BigDecimal getShipping() {
		return shipping;
	}
	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
	}
	public BigDecimal getHandling() {
		return handling;
	}
	public void setHandling(BigDecimal handling) {
		this.handling = handling;
	}
	public String getShippingModule() {
		return shippingModule;
	}
	public void setShippingModule(String shippingModule) {
		this.shippingModule = shippingModule;
	}
	public String getShippingOption() {
		return shippingOption;
	}
	public void setShippingOption(String shippingOption) {
		this.shippingOption = shippingOption;
	}
	public boolean isFreeShipping() {
		return freeShipping;
	}
	public void setFreeShipping(boolean freeShipping) {
		this.freeShipping = freeShipping;
	}
	public boolean isTaxOnShipping() {
		return taxOnShipping;
	}
	public void setTaxOnShipping(boolean taxOnShipping) {
		this.taxOnShipping = taxOnShipping;
	}
	public String getShippingText() {
		return shippingText;
	}
	public void setShippingText(String shippingText) {
		this.shippingText = shippingText;
	}
	public String getHandlingText() {
		return handlingText;
	}
	public void setHandlingText(String handlingText) {
		this.handlingText = handlingText;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<ShippingOption> getShippingOptions() {
		return shippingOptions;
	}
	public void setShippingOptions(List<ShippingOption> shippingOptions) {
		this.shippingOptions = shippingOptions;
	}
	public ShippingOption getSelectedShippingOption() {
		return selectedShippingOption;
	}
	public void setSelectedShippingOption(ShippingOption selectedShippingOption) {
		this.selectedShippingOption = selectedShippingOption;
	}
	public Map<String,String> getQuoteInformations() {
		return quoteInformations;
	}
	public void setQuoteInformations(Map<String,String> quoteInformations) {
		this.quoteInformations = quoteInformations;
	}
	public Address getDelivery() {
		return delivery;
	}
	public void setDelivery(ReadableDelivery delivery) {
		this.delivery = delivery;
	}
	public boolean isShippingQuote() {
		return shippingQuote;
	}
	public void setShippingQuote(boolean shippingQuote) {
		this.shippingQuote = shippingQuote;
	}

}
