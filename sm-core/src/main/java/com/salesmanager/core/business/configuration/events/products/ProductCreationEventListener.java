package com.salesmanager.core.business.configuration.events.products;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Index product
 * @author carlsamson
 *
 */
@Component
public class ProductCreationEventListener implements ApplicationListener<ProductEvent> {

	@Override
	public void onApplicationEvent(ProductEvent event) {
		// TODO Auto-generated method stub
		
	}

}
