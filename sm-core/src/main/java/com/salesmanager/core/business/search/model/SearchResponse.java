package com.salesmanager.core.business.search.model;

import java.util.List;
import java.util.Map;

public class SearchResponse {
	
	private int totalCount = 0;//total number of entries
	private int entryCount = 0;//number of entries asked
	
	private List<SearchEntry> entries;
	private Map<String,List<SearchFacet>> facets;//facet key (example : category) & facet description (example : category code)
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setEntryCount(int entryCount) {
		this.entryCount = entryCount;
	}
	public int getEntryCount() {
		return entryCount;
	}
	public void setEntries(List<SearchEntry> entries) {
		this.entries = entries;
	}
	public List<SearchEntry> getEntries() {
		return entries;
	}
	public void setFacets(Map<String,List<SearchFacet>> facets) {
		this.facets = facets;
	}
	public Map<String,List<SearchFacet>> getFacets() {
		return facets;
	}

}
