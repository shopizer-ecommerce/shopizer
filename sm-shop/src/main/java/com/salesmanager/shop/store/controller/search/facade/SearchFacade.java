package com.salesmanager.shop.store.controller.search.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.search.SearchResponse;
import com.salesmanager.shop.model.ValueList;
import com.salesmanager.shop.model.catalog.SearchProductList;
import com.salesmanager.shop.model.catalog.SearchProductRequest;

/**
 * Different services for searching and indexing data
 * @author c.samson
 *
 */
public interface SearchFacade {
	

	/**
	 * This utility method will re-index all products in the catalogue
	 * @param store
	 * @throws Exception
	 */
	public void indexAllData(MerchantStore store) throws Exception;
	
	/**
	 * Produces a search request against elastic search
	 * @param searchRequest
	 * @return
	 * @throws Exception
	 */
	public SearchProductList search(MerchantStore store, Language language, SearchProductRequest searchRequest) throws Exception;

	/**
	 * Copy sm-core search response to a simple readable format populated with corresponding products
	 * @param searchResponse
	 * @return
	 */
	public SearchProductList copySearchResponse(SearchResponse searchResponse, MerchantStore store, int start, int count, Language language) throws Exception;

	/**
	 * List of keywords / autocompletes for a given word being typed
	 * @param query
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ValueList autocompleteRequest(String query, MerchantStore store, Language language) throws Exception;
}
