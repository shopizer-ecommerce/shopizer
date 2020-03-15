package com.salesmanager.core.model.search;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class IndexProduct implements JSONAware {
	
	private String name;
	private Double price;
	private List<String> categories;//category code
	private String manufacturer;//id of the manufacturer
	private boolean available;
	private String description;
	private List<String> tags;//keywords ?
	private String highlight;
	private String store;
	private String lang;
	private String id;//required by the search framework

	@SuppressWarnings("unchecked")
	@Override
	public String toJSONString() {
		
		
		
		
		JSONObject obj = new JSONObject();
		obj.put("name", this.getName());
		obj.put("price", this.getPrice());
		obj.put("description", this.getDescription());
		obj.put("highlight", this.getHighlight());
		obj.put("store", this.getStore());
		obj.put("manufacturer", this.getManufacturer());
		obj.put("lang", this.getLang());
		obj.put("id", this.getId());
		if(categories!=null) {
			JSONArray categoriesArray = new JSONArray();
			for(String category : categories) {
				categoriesArray.add(category);
			}
			obj.put("categories", categoriesArray);
		}
		
		if(tags!=null) {
			JSONArray tagsArray = new JSONArray();
			for(String tag : tags) {
				tagsArray.add(tag);
			}
			obj.put("tags", tagsArray);
		}
		
		return obj.toJSONString();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getStore() {
		return store;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getLang() {
		return lang;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getManufacturer() {
		return manufacturer;
	}

}
