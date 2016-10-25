package com.shopizer.search.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Object used for autocomplete and regular search
 * @author Carl Samson
 *
 */
public class SearchResponse {
	
	private String inputSearchJson;
	private Collection<String> ids;
	private long count;


	private Collection<SearchHit> searchHits;
	

	
	private Map<String,List<FacetEntry>> facets;
	
	public Map<String,List<FacetEntry>> getFacets() {
		return facets;
	}

	public void setFacets(Map<String,List<FacetEntry>> facets) {
		this.facets = facets;
	}

	private String[] inlineSearchList;
	
	
	public String[] getInlineSearchList() {
		return inlineSearchList;
	}

	public void setInlineSearchList(String[] inlineSearchList) {
		this.inlineSearchList = inlineSearchList;
	}

	public Collection<SearchHit> getSearchHits() {
		return searchHits;
	}

	public void setSearchHits(Collection searchHits) {
		this.searchHits = searchHits;
	}

	public String getInputSearchJson() {
		return inputSearchJson;
	}

	public void setInputSearchJson(String inputSearchJson) {
		this.inputSearchJson = inputSearchJson;
	}
	
	public Collection<String> getIds() {
		return ids;
	}

	public void setIds(Collection<String> ids) {
		this.ids = ids;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}




}
