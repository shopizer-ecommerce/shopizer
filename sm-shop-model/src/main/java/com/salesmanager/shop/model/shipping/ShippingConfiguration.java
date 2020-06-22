package com.salesmanager.shop.model.shipping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShippingConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private boolean taxOnShipping = false;
	private List<BoxConfiguration> boxConfigurations = new ArrayList<BoxConfiguration>();


	public boolean isTaxOnShipping() {
		return taxOnShipping;
	}

	public void setTaxOnShipping(boolean taxOnShipping) {
		this.taxOnShipping = taxOnShipping;
	}

	public List<BoxConfiguration> getBoxConfigurations() {
		return boxConfigurations;
	}

	public void setBoxConfigurations(List<BoxConfiguration> boxConfigurations) {
		this.boxConfigurations = boxConfigurations;
	}

}
