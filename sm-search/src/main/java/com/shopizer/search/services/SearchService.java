package com.shopizer.search.services;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.shopizer.search.services.worker.KeywordIndexerImpl;
import com.shopizer.search.services.worker.ObjectIndexerImpl;
import com.shopizer.search.services.workflow.DeleteObjectWorkflow;
import com.shopizer.search.services.workflow.GetWorkflow;
import com.shopizer.search.services.workflow.IndexWorkflow;
import com.shopizer.search.services.workflow.SearchWorkflow;
import com.shopizer.search.utils.SearchClient;


/**
 * This is the main class for indexing and searching services
 * @author Carl Samson
 *
 */

public class SearchService {
	

	private static Logger log = Logger.getLogger(SearchService.class);
	
	@Inject
	private DeleteObjectWorkflow deleteWorkflow;
	
	@Inject
	private IndexWorkflow indexWorkflow;
	
	@Inject
	private GetWorkflow getWorkflow;
	
	@Inject
	private SearchWorkflow searchWorkflow;
	
	@Inject
	private ObjectIndexerImpl index;
	
	@Inject
	private KeywordIndexerImpl keyword;
	
	@Inject
	private SearchClient searchClient;
	
	public void initService() {
		log.debug("Initializing search service");
		
		try {
			index.init(searchClient);
			keyword.init(searchClient);
		} catch (Exception e) {
			log.error("Cannot initialize SearchService correctly, will be initialized lazily",e);
		}
		
	}


	
	public void deleteObject(String collection, String object, String id) throws Exception {	
		deleteWorkflow.deleteObject(collection, object, id);
		
	}
	
	
	public com.shopizer.search.services.GetResponse getObject(String collection, String object, String id) throws Exception {
		
		return getWorkflow.getObject(collection,object,id);
	}
	
	/**
	 * Index a document
	 * @param json
	 * @param collection (name of the collection)
	 * Might be product_en or product_fr or any name of the index container
	 * @param object
	 * That corresponds to the name of the entity to be indexed as defined in the
	 * indice file (product.json). In this case it will be product
	 * @param id
	 */
	public void index(String json, String collection, String object) throws Exception {

		indexWorkflow.index(json, collection, object);
	}
	

	public SearchResponse searchAutoComplete(String collection,String json,int size) throws Exception {

		return searchWorkflow.searchAutocomplete(collection,json,size);
	}
	
	public SearchResponse search(SearchRequest request) throws Exception {
		
		return searchWorkflow.search(request);
	}
}
