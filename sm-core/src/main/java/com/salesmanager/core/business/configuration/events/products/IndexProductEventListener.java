package com.salesmanager.core.business.configuration.events.products;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;



/**
 * Index product in search module if it is configured to do so !
 * 
 * Should receive events that a product was created or updated or deleted
 * @author carlsamson
 *
 */
@Component
public class IndexProductEventListener implements ApplicationListener<ProductEvent> {
	
	

	@Override
	public void onApplicationEvent(ProductEvent event) {
		//search service
		
	}

}
