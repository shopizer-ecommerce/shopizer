package com.salesmanager.shop.model.tax;

import com.salesmanager.shop.model.references.ReadableCountry;
import com.salesmanager.shop.model.references.ReadableZone;
import com.salesmanager.shop.model.store.ReadableMerchantStore;

public class ReadableTaxRate extends TaxRateEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String rate;
	private ReadableMerchantStore store;
	private ReadableZone zone;
	private ReadableCountry country;
	private ReadableTaxRateDescription description;
	public ReadableTaxRateDescription getDescription() {
		return description;
	}
	public void setDescription(ReadableTaxRateDescription description) {
		this.description = description;
	}
	public ReadableCountry getCountry() {
		return country;
	}
	public void setCountry(ReadableCountry country) {
		this.country = country;
	}
	public ReadableZone getZone() {
		return zone;
	}
	public void setZone(ReadableZone zone) {
		this.zone = zone;
	}
	public ReadableMerchantStore getStore() {
		return store;
	}
	public void setStore(ReadableMerchantStore store) {
		this.store = store;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}

}
