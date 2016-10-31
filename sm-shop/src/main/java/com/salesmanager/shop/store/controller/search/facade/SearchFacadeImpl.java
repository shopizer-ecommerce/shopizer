package com.salesmanager.shop.store.controller.search.facade;

import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.search.SearchService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.merchant.MerchantStore;


@Service("searchFacade")
public class SearchFacadeImpl implements SearchFacade {
	
	@Inject
	private SearchService searchService;
	
	@Inject
	private ProductService productService;

	/**
	 * Index all products from the catalogue
	 * Better stop the system, remove ES indexex manually
	 * restart ES and run this query
	 */
	@Override
	@Async
	public void indexAllData(MerchantStore store) throws Exception {
		
		
		List<Product> products = productService.listByStore(store);
		
		for(Product product : products) {
			searchService.index(store, product);
		}
		
	}

}
