package com.shopizer.search.services.worker;

import javax.inject.Inject;

import com.shopizer.search.services.SearchRequest;
import com.shopizer.search.services.SearchResponse;
import com.shopizer.search.services.impl.SearchDelegate;
import com.shopizer.search.utils.SearchClient;


public class SearchWorkerImpl implements SearchWorker {
	
	@Inject
	private SearchDelegate searchDelegate;

	public SearchResponse execute(SearchClient client, SearchRequest request, ExecutionContext context) throws Exception{

		SearchResponse response = searchDelegate.search(request);

		response.setInputSearchJson(request.getJson());
		if(context == null) {
			context = new ExecutionContext();
		}
		context.setObject("response", response);
		return response;

	}

}
