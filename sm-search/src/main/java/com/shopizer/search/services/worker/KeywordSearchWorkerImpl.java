package com.shopizer.search.services.worker;

import java.util.Collection;

import javax.inject.Inject;

import com.shopizer.search.services.SearchResponse;
import com.shopizer.search.services.impl.SearchDelegate;
import com.shopizer.search.utils.SearchClient;


public class KeywordSearchWorkerImpl implements KeywordSearchWorker {
	
	@Inject
	private SearchDelegate searchDelegate;

	public SearchResponse execute(SearchClient client, String collection,String json,int size, ExecutionContext context) throws Exception{

		

		Collection<String> hits = searchDelegate.searchAutocomplete(collection, json, size);
		SearchResponse resp = new SearchResponse();

		String[] array = (String[])hits.toArray(new String[hits.size()]);
		
		
		resp.setInlineSearchList(array);
		if(array.length>0) {
			resp.setCount(array.length);
		}
		
	    return resp; 

	}

}
