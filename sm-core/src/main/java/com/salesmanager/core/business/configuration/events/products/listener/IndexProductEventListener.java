package com.salesmanager.core.business.configuration.events.products.listener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.configuration.events.products.DeleteProductEvent;
import com.salesmanager.core.business.configuration.events.products.DeleteProductInstanceEvent;
import com.salesmanager.core.business.configuration.events.products.ProductEvent;
import com.salesmanager.core.business.configuration.events.products.SaveProductEvent;
import com.salesmanager.core.business.configuration.events.products.SaveProductInstanceEvent;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.search.SearchService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.merchant.MerchantStore;

/**
 * Index product in search module if it is configured to do so !
 * 
 * Should receive events that a product was created or updated or deleted
 * 
 * @author carlsamson
 *
 */
@Component
public class IndexProductEventListener implements ApplicationListener<ProductEvent> {

	@Autowired
	private SearchService searchService;

	@Autowired
	private ProductService productService;
	
    @Value("${search.noindex:false}")
    private boolean noIndex;

	/**
	 * Listens to ProductEvent and ProductInstanceEvent
	 */
	@Override
	public void onApplicationEvent(ProductEvent event) {
		
		
		if(!noIndex) {

			if (event instanceof SaveProductEvent) {
				saveProduct((SaveProductEvent) event);
			}
	
			if (event instanceof DeleteProductEvent) {
				deleteProduct((DeleteProductEvent) event);
			}
	
			if (event instanceof SaveProductInstanceEvent) {
				saveProductInstance((SaveProductInstanceEvent) event);
			}
	
			if (event instanceof DeleteProductInstanceEvent) {
				deleteProductInstance((DeleteProductInstanceEvent) event);
			}
		
		}

	}

	void saveProduct(SaveProductEvent event) {
		Product product = event.getProduct();
		MerchantStore store = product.getMerchantStore();
		try {

			/**
			 * Refresh product
			 */

			product = productService.findOne(product.getId(), store);

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

		Product product = event.getProduct();

		Long id = product.getId();
		MerchantStore store = product.getMerchantStore();

		/**
		 * Refresh product
		 */

		product = productService.findOne(id, store);

		ProductInstance instance = event.getInstance();// to be removed

		/**
		 * add new instance to be saved
		 **/

		List<ProductInstance> filteredInstances = product.getInstances().stream()
				.filter(i -> instance.getId().longValue() != i.getId().longValue()).collect(Collectors.toList());

		filteredInstances.add(instance);

		Set<ProductInstance> allInstances = new HashSet<ProductInstance>(filteredInstances);
		product.setInstances(allInstances);

		try {
			searchService.index(store, product);
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}

	}

	void deleteProductInstance(DeleteProductInstanceEvent event) {

		Product product = event.getProduct();

		Long id = product.getId();
		MerchantStore store = product.getMerchantStore();

		/**
		 * Refresh product
		 */

		product = productService.findOne(id, store);
		ProductInstance instance = event.getInstance();// to be removed

		/**
		 * remove instance to be saved
		 **/

		List<ProductInstance> filteredInstances = product.getInstances().stream()
				.filter(i -> instance.getId().longValue() != i.getId().longValue()).collect(Collectors.toList());

		Set<ProductInstance> allInstances = new HashSet<ProductInstance>(filteredInstances);
		product.setInstances(allInstances);

		try {
			searchService.index(store, product);
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Get document by product id and document exist if event is Product delete
	 * document create document if event is Instance get document get variants
	 * replace variant
	 */

}
