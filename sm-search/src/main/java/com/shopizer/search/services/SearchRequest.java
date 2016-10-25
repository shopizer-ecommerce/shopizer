package com.shopizer.search.services;

import java.util.ArrayList;
import java.util.List;

public class SearchRequest {
	
	private List<String> collections;
	
	public List<String> getCollections() {
		return collections;
	}
	public void addCollection(String collection) {
		if(this.collections == null) {
			this.collections = new ArrayList<String>();
		}
		this.collections.add(collection);
	}


	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	private String json;
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	private int size = -1;
	private int start;

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}

}
