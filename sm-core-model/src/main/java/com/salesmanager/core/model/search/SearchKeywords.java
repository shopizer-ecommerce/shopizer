package com.salesmanager.core.model.search;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class SearchKeywords implements JSONAware{
	
	private List<String> keywords;

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toJSONString() {
		JSONArray jsonArray = new JSONArray();
		for(String kw : keywords) {
			JSONObject data = new JSONObject();
			data.put("name", kw);
			data.put("value", kw);
			jsonArray.add(data);
		}
		return jsonArray.toJSONString();
	}

}
