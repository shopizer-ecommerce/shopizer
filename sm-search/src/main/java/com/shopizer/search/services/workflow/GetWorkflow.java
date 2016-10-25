package com.shopizer.search.services.workflow;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.shopizer.search.services.impl.SearchDelegate;



@Component
public class GetWorkflow extends Workflow {
	
	@Inject
	private SearchDelegate searchDelegate;
	public com.shopizer.search.services.GetResponse getObject(String collection, String object, String id) throws Exception {

		return searchDelegate.getObject(collection, object, id);
		
	}

}
