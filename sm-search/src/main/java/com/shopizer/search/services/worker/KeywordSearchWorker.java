package com.shopizer.search.services.worker;

import com.shopizer.search.services.SearchResponse;
import com.shopizer.search.utils.SearchClient;

public interface KeywordSearchWorker {
	
	public SearchResponse execute(SearchClient client,String collection,String json,int size, ExecutionContext context) throws Exception;

}
