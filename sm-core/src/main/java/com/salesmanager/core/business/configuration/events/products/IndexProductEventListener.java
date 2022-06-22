package com.salesmanager.core.business.configuration.events.products;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Index product in opensearch if it is confugured to do so !
 * @author carlsamson
 *
 */
@Component
public class IndexProductEventListener implements ApplicationListener<ProductEvent> {

	@Override
	public void onApplicationEvent(ProductEvent event) {
		// TODO Auto-generated method stub
		
	}

}
