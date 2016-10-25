package com.shopizer.search.utils;

import java.util.List;

public class CustomIndexConfiguration extends IndexConfiguration {

	
	private String createOnIndexName;
	private List<CustomIndexFieldConfiguration> fields;
	private List<CustomIndexFieldConfiguration> filters;


	public String getCreateOnIndexName() {
		return createOnIndexName;
	}

	public void setCreateOnIndexName(String createOnIndexName) {
		this.createOnIndexName = createOnIndexName;
	}

	

	public List<CustomIndexFieldConfiguration> getFields() {
		return fields;
	}

	public void setFields(List<CustomIndexFieldConfiguration> fields) {
		this.fields = fields;
	}
	
	



	public List<CustomIndexFieldConfiguration> getFilters() {
		return filters;
	}

	public void setFilters(List<CustomIndexFieldConfiguration> filters) {
		this.filters = filters;
	}
}
