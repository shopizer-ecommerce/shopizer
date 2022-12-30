package com.salesmanager.core.modules.integration.shipping.model;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class CustomShippingQuoteWeightItem extends CustomShippingQuoteItem implements JSONAware {
	
	private int maximumWeight;
	
	private String priceText;

	public String getPriceText() {
		return priceText;
	}

	public void setPriceText(String priceText) {
		this.priceText = priceText;
	}

	public void setMaximumWeight(int maximumWeight) {
		this.maximumWeight = maximumWeight;
	}

	public int getMaximumWeight() {
		return maximumWeight;
	}

	@SuppressWarnings("unchecked")
	public String toJSONString() {
		JSONObject data = new JSONObject();
		data.put("price", super.getPrice());
		data.put("maximumWeight", this.getMaximumWeight());
		
		return data.toJSONString();
	}



}
