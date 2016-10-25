package com.shopizer.search.services.worker;

import com.shopizer.search.services.SearchRequest;
import com.shopizer.search.services.SearchResponse;
import com.shopizer.search.utils.SearchClient;

public interface SearchWorker {
	
	public SearchResponse execute(SearchClient client, SearchRequest request, ExecutionContext context) throws Exception;

}
