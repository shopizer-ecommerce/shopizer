package com.salesmanager.core.business.search.model;

import java.util.List;

public class SearchEntry {
	
	private IndexProduct indexProduct;//product as saved in the index
	private List<String> highlights;
	public void setHighlights(List<String> highlights) {
		this.highlights = highlights;
	}
	public List<String> getHighlights() {
		return highlights;
	}
	public void setIndexProduct(IndexProduct indexProduct) {
		this.indexProduct = indexProduct;
	}
	public IndexProduct getIndexProduct() {
		return indexProduct;
	}

}
