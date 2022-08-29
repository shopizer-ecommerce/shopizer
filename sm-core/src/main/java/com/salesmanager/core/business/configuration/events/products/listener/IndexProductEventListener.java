package com.salesmanager.core.business.configuration.events.products.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.configuration.events.products.DeleteProductEvent;
import com.salesmanager.core.business.configuration.events.products.DeleteProductInstanceEvent;
import com.salesmanager.core.business.configuration.events.products.ProductEvent;
import com.salesmanager.core.business.configuration.events.products.SaveProductEvent;
import com.salesmanager.core.business.configuration.events.products.SaveProductInstanceEvent;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.search.SearchService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.merchant.MerchantStore;



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
	
	
	/**
	 * Listens to ProductEvent and ProductInstanceEvent
	 */
	@Override
	public void onApplicationEvent(ProductEvent event) {

		
	    if (event instanceof SaveProductEvent) {
	        saveProduct((SaveProductEvent)event);
	    }
	    
	    if (event instanceof DeleteProductEvent) {
	        deleteProduct((DeleteProductEvent)event);
	    }
	    
	    if (event instanceof SaveProductInstanceEvent) {
	        saveProductInstance((SaveProductInstanceEvent)event);
	    }
	    
	    if (event instanceof DeleteProductInstanceEvent) {
	        deleteProductInstance((DeleteProductInstanceEvent)event);
	    }
		
	}
	
	void saveProduct(SaveProductEvent event) {
		Product product = event.getProduct();
		MerchantStore store = product.getMerchantStore();
		try {
			searchService.index(store, product);
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
	}
	
	void deleteProduct(DeleteProductEvent event) {
		Product product = event.getProduct();
		MerchantStore store = product.getMerchantStore();
		try {
			searchService.deleteDocument(store, product);
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
	}
	
	void saveProductInstance(SaveProductInstanceEvent event) {
		
	}
	
	void deleteProductInstance(DeleteProductInstanceEvent event) {
		
	}
	
	/**
	 * Get document by product id and document exist
	 * if event is Product
	 * 	delete document
	 *  create document
	 * if event is Instance
	 *  get document
	 *   get variants
	 *    replace variant
	 */

	
	

}
