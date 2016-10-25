package com.shopizer.search.services.impl;

import java.util.Collection;
import java.util.Set;

import com.shopizer.search.services.IndexKeywordRequest;
import com.shopizer.search.services.SearchRequest;
import com.shopizer.search.services.SearchResponse;

public interface SearchDelegate {

	public boolean indexExist(String indexName) throws Exception;

	/**
	 * Creates a structure that represents the object and the content to be indexed
	 */
	void createIndice(String mappingJson, String settingsJson, String collection,
			String object) throws Exception;

	/**
	 * Will index an object in json format in a collection
	 * of indexes
	 * @param collection
	 * @param object
	 * @param id
	 */
	void index(String json, String collection, String object,
			String id) throws Exception;

	void delete(String collection, String object, String id)
			throws Exception;

	void bulkDeleteIndex(Collection<String> ids,
			String collection, String object) throws Exception;

	/**
	 * Index keywords in bulk
	 * @param bulks
	 * @param collection
	 * @param object
	 * @param id
	 */
	void bulkIndexKeywords(
			Collection<IndexKeywordRequest> bulks, String collection,
			String object) throws Exception;

	com.shopizer.search.services.GetResponse getObject(
			String collection, String object, String id) throws Exception;

	/**
	 * Search for a term
	 * @param term
	 * @param collection
	 * @param field
	 * @return
	 */
    SearchResponse search(SearchRequest request)
			throws Exception;

	Set<String> searchAutocomplete(String collection,
			String json, int size) throws Exception;

}