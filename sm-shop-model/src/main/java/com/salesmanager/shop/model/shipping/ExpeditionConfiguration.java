package com.salesmanager.shop.model.shipping;

import java.util.ArrayList;
import java.util.List;

public class ExpeditionConfiguration {
	
	private boolean iternationalShipping = false;
	private boolean taxOnShipping = false;
	private List<String> shipToCountry = new ArrayList<String>();

	public boolean isIternationalShipping() {
		return iternationalShipping;
	}

	public void setIternationalShipping(boolean iternationalShipping) {
		this.iternationalShipping = iternationalShipping;
	}

	public List<String> getShipToCountry() {
		return shipToCountry;
	}

	public void setShipToCountry(List<String> shipToCountry) {
		this.shipToCountry = shipToCountry;
	}

	public boolean isTaxOnShipping() {
		return taxOnShipping;
	}

	public void setTaxOnShipping(boolean taxOnShipping) {
		this.taxOnShipping = taxOnShipping;
	}

}
