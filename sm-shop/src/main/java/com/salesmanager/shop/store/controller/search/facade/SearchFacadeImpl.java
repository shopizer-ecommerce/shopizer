package com.salesmanager.shop.store.controller.search.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.search.SearchService;
import com.salesmanager.core.business.utils.CoreConfiguration;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.ProductList;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.search.IndexProduct;
import com.salesmanager.core.model.search.SearchEntry;
import com.salesmanager.core.model.search.SearchFacet;
import com.salesmanager.core.model.search.SearchKeywords;
import com.salesmanager.core.model.search.SearchResponse;
import com.salesmanager.shop.model.ValueList;
import com.salesmanager.shop.model.catalog.SearchProductList;
import com.salesmanager.shop.model.catalog.SearchProductRequest;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.populator.catalog.ReadableCategoryPopulator;
import com.salesmanager.shop.populator.catalog.ReadableProductPopulator;
import com.salesmanager.shop.store.model.search.AutoCompleteRequest;
import com.salesmanager.shop.utils.ImageFilePath;


@Service("searchFacade")
public class SearchFacadeImpl implements SearchFacade {
	
	@Inject
	private SearchService searchService;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private CategoryService categoryService;
	
	@Inject
	private PricingService pricingService;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;
	
	@Inject
	private CoreConfiguration coreConfiguration;

    
	private final static String CATEGORY_FACET_NAME = "categories";
	private final static String MANUFACTURER_FACET_NAME = "manufacturer";
	private final static int AUTOCOMPLETE_ENTRIES_COUNT = 15;

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

	@Override
	public SearchProductList search(MerchantStore store, Language language, SearchProductRequest searchRequest) throws Exception {

		String query = String.format(coreConfiguration.getProperty("SEARCH_QUERY"), searchRequest.getQuery());
		SearchResponse response =  searchService.search(store, language.getCode(), query, searchRequest.getCount(), searchRequest.getStart());
		return this.copySearchResponse(response, store, searchRequest.getStart(), searchRequest.getCount(), language);
	}

	@Override
	public SearchProductList copySearchResponse(SearchResponse searchResponse, MerchantStore merchantStore, int start, int count, Language language) throws Exception {
		
		SearchProductList returnList = new SearchProductList();
		List<SearchEntry> entries = searchResponse.getEntries();
		
		if(!CollectionUtils.isEmpty(entries)) {
			List<Long> ids = new ArrayList<Long>();
			for(SearchEntry entry : entries) {
				IndexProduct indexedProduct = entry.getIndexProduct();
				Long id = Long.parseLong(indexedProduct.getId());
				
				//No highlights	
				ids.add(id);
			}
			
			ProductCriteria searchCriteria = new ProductCriteria();
			searchCriteria.setMaxCount(count);
			searchCriteria.setStartIndex(start);
			searchCriteria.setProductIds(ids);
			searchCriteria.setAvailable(true);
			
			ProductList productList = productService.listByStore(merchantStore, language, searchCriteria);
			
			ReadableProductPopulator populator = new ReadableProductPopulator();
			populator.setPricingService(pricingService);
			populator.setimageUtils(imageUtils);
			
			for(Product product : productList.getProducts()) {
				//create new proxy product
				ReadableProduct p = populator.populate(product, new ReadableProduct(), merchantStore, language);
				
				returnList.getProducts().add(p);
	
			}
			returnList.setProductCount(productList.getProducts().size());
		}
		
		//Facets
		Map<String,List<SearchFacet>> facets = searchResponse.getFacets();
		List<SearchFacet> categoriesFacets = null;
		List<SearchFacet> manufacturersFacets = null;
		if(facets!=null) {
			for(String key : facets.keySet()) {
				//supports category and manufacturer
				if(CATEGORY_FACET_NAME.equals(key)) {
					categoriesFacets = facets.get(key);
				}
				
				if(MANUFACTURER_FACET_NAME.equals(key)) {
					manufacturersFacets = facets.get(key);
				}
			}
			
			
			if(categoriesFacets!=null) {
				List<String> categoryCodes = new ArrayList<String>();
				Map<String,Long> productCategoryCount = new HashMap<String,Long>();
				for(SearchFacet facet : categoriesFacets) {
					categoryCodes.add(facet.getName());
					productCategoryCount.put(facet.getKey(), facet.getCount());
				}
				
				List<Category> categories = categoryService.listByCodes(merchantStore, categoryCodes, language);
				List<ReadableCategory> categoryProxies = new ArrayList<ReadableCategory>();
				ReadableCategoryPopulator populator = new ReadableCategoryPopulator();
				
				for(Category category : categories) {
					ReadableCategory categoryProxy = populator.populate(category, new ReadableCategory(), merchantStore, language);
					Long total = productCategoryCount.get(categoryProxy.getCode());
					if(total!=null) {
						categoryProxy.setProductCount(total.intValue());
					}
					categoryProxies.add(categoryProxy);
				}
				returnList.setCategoryFacets(categoryProxies);
			}
			
			//todo manufacturer facets
			if(manufacturersFacets!=null) {
				
			}
			
			
		}
		
		return returnList;
	}

	@Override
	public ValueList autocompleteRequest(String query, MerchantStore store, Language language) throws Exception {
		
		AutoCompleteRequest req = new AutoCompleteRequest(store.getCode(),language.getCode());
		String q = String.format(coreConfiguration.getProperty("AUTOCOMPLETE_QUERY"), query);
		/** formatted toJSONString because of te specific field names required in the UI **/
		SearchKeywords keywords = searchService.searchForKeywords(req.getCollectionName(), q, AUTOCOMPLETE_ENTRIES_COUNT);
		ValueList returnList = new ValueList();
		returnList.setValues(keywords.getKeywords());
		
		return returnList;

		
		
		
	}
	

}
