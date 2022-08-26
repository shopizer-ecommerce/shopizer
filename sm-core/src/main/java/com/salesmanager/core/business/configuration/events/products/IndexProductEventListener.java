package com.salesmanager.core.business.configuration.events.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.services.search.SearchService;



/**
 * Index product in search module if it is configured to do so !
 * 
 * Should receive events that a product was created or updated or deleted
 * @author carlsamson
 *
 */
@Component
public class IndexProductEventListener implements ApplicationListener<ProductEvent> {
	
	@Autowired
	private SearchService searchService;
	
	

	@Override
	public void onApplicationEvent(ProductEvent event) {
		//search service
		
		System.out.println("Event");
		
		//check if SaveProduct or DeleteProduct
		
	}

}
