package com.salesmanager.shop.model.tax;

import java.math.BigDecimal;
import java.util.List;

public class PersistableTaxRate extends TaxRateEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal rate;
	private String store;
	private String zone;
	private String country;
	private List<TaxRateDescription> descriptions;
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<TaxRateDescription> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(List<TaxRateDescription> descriptions) {
		this.descriptions = descriptions;
	}
}
