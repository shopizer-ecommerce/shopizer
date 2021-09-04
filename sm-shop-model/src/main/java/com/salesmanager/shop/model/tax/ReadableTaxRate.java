package com.salesmanager.shop.model.tax;

public class ReadableTaxRate extends TaxRateEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String rate;
	private String store;
	private String zone;
	private String country;
	private ReadableTaxRateDescription description;
	private ReadableTaxClass taxClass;
	
	public ReadableTaxClass getTaxClass() {
		return taxClass;
	}
	public void setTaxClass(ReadableTaxClass taxClass) {
		this.taxClass = taxClass;
	}
	public ReadableTaxRateDescription getDescription() {
		return description;
	}
	public void setDescription(ReadableTaxRateDescription description) {
		this.description = description;
	}

	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
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

}
