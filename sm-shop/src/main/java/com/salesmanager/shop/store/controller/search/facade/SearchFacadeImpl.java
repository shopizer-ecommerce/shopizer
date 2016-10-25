package com.salesmanager.web.shop.controller.search.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.search.service.SearchService;

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
