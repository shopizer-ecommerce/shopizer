package com.shopizer.search.services;

import java.util.HashMap;
import java.util.Map;

public class SearchHit {
	
	//private org.elasticsearch.search.SearchHit searchit;
	private String id;
	private String index;
	private String internalId;
	private String type;//object type
	//private float score;
	private Map<String,Object> item = new HashMap<String,Object>();

	
	public Map<String,Object> getItem() {
		return item;
	}

	public SearchHit(Map<String, Object> item, String internalId) {
		
		this.id = (String) item.get("id");
		this.internalId = internalId;
		this.item = item;
		//metaEntries.put("source", item);
		
		//if(searchit.getHighlightFields()!=null && searchit.getHighlightFields().size()>0) {
		//	metaEntries.put("highlightFields", searchit.getHighlightFields());
		//}
		
	}
	

	
	public String getId() {
		return id;
	}
	
	public String getIndex() {
		return index;
	}

	public String getInternalId() {
		return internalId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



}
