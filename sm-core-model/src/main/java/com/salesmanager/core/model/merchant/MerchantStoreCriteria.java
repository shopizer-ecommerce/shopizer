package com.salesmanager.core.model.merchant;

import com.salesmanager.core.model.common.Criteria;

public class MerchantStoreCriteria extends Criteria {
	
	private boolean retailers = false;
	private boolean stores = false;

	public boolean isRetailers() {
		return retailers;
	}

	public void setRetailers(boolean retailers) {
		this.retailers = retailers;
	}

	public boolean isStores() {
		return stores;
	}

	public void setStores(boolean stores) {
		this.stores = stores;
	}
	
	


}
