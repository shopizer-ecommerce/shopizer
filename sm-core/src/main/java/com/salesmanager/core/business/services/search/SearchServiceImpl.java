package com.salesmanager.core.business.services.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.utils.CoreConfiguration;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.search.IndexProduct;
import com.salesmanager.core.model.search.SearchEntry;
import com.salesmanager.core.model.search.SearchFacet;
import com.salesmanager.core.model.search.SearchKeywords;
import com.shopizer.search.services.Facet;
import com.shopizer.search.services.SearchHit;
import com.shopizer.search.services.SearchRequest;
import com.shopizer.search.services.SearchResponse;



@Service("productSearchService")
public class SearchServiceImpl implements com.salesmanager.core.business.services.search.SearchService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);
	
	
	private final static String PRODUCT_INDEX_NAME = "product";
	private final static String UNDERSCORE = "_";
	private final static String INDEX_PRODUCTS = "INDEX_PRODUCTS";

	@Inject
	private com.shopizer.search.services.SearchService searchService;
	
	@Inject
	private PricingService pricingService;
	
	@Inject
	private CoreConfiguration configuration;
	

	public void initService() {
		searchService.initService();
	}

	@Async
	@SuppressWarnings("rawtypes")
	public void index(MerchantStore store, Product product)
			throws ServiceException {
		
		/**
		 * When a product is saved or updated the indexing process occurs
		 * 
		 * A product entity will have to be transformed to a bean ProductIndex
		 * which contains the indices as described in product.json
		 * 
		 * {"product": {
						"properties" :  {
							"name" : {"type":"string","index":"analyzed"},
							"price" : {"type":"string","index":"not_analyzed"},
							"category" : {"type":"string","index":"not_analyzed"},
							"lang" : {"type":"string","index":"not_analyzed"},
							"available" : {"type":"string","index":"not_analyzed"},
							"description" : {"type":"string","index":"analyzed","index_analyzer":"english"}, 
							"tags" : {"type":"string","index":"not_analyzed"} 
						 } 
			            }
			}
		 *
		 * productService saveOrUpdate as well as create and update will invoke
		 * productSearchService.index	
		 * 
		 * A copy of properies between Product to IndexProduct
		 * Then IndexProduct will be transformed to a json representation by the invocation
		 * of .toJSONString on IndexProduct
		 * 
		 * Then index product
		 * searchService.index(json, "product_<LANGUAGE_CODE>_<MERCHANT_CODE>", "product");
		 * 
		 * example ...index(json,"product_en_default",product)
		 * 
		 */
		
		if(configuration.getProperty(INDEX_PRODUCTS)==null || configuration.getProperty(INDEX_PRODUCTS).equals(Constants.FALSE)) {
			return;
		}
		
		FinalPrice price = pricingService.calculateProductPrice(product);

		
		Set<ProductDescription> descriptions = product.getDescriptions();
		for(ProductDescription description : descriptions) {
			
			StringBuilder collectionName = new StringBuilder();
			collectionName.append(PRODUCT_INDEX_NAME).append(UNDERSCORE).append(description.getLanguage().getCode()).append(UNDERSCORE).append(store.getCode().toLowerCase());
			
			IndexProduct index = new IndexProduct();

			index.setId(String.valueOf(product.getId()));
			index.setStore(store.getCode().toLowerCase());
			index.setLang(description.getLanguage().getCode());
			index.setAvailable(product.isAvailable());
			index.setDescription(description.getDescription());
			index.setName(description.getName());
			if(product.getManufacturer()!=null) {
				index.setManufacturer(String.valueOf(product.getManufacturer().getId()));
			}
			if(price!=null) {
				index.setPrice(price.getFinalPrice().doubleValue());
			}
			index.setHighlight(description.getProductHighlight());
			if(!StringUtils.isBlank(description.getMetatagKeywords())){
				String[] tags = description.getMetatagKeywords().split(",");
				@SuppressWarnings("unchecked")
				List<String> tagsList = new ArrayList(Arrays.asList(tags));
				index.setTags(tagsList);
			}

			
			Set<Category> categories = product.getCategories();
			if(!CollectionUtils.isEmpty(categories)) {
				List<String> categoryList = new ArrayList<String>();
				for(Category category : categories) {
					categoryList.add(category.getCode());
				}
				index.setCategories(categoryList);
			}
			
			String jsonString = index.toJSONString();
			try {
				searchService.index(jsonString, collectionName.toString(), new StringBuilder().append(PRODUCT_INDEX_NAME).append(UNDERSCORE).append(description.getLanguage().getCode()).toString());
			} catch (Exception e) {
				throw new ServiceException("Cannot index product id [" + product.getId() + "], " + e.getMessage() ,e);
			}
		}
	}


	public void deleteIndex(MerchantStore store, Product product) throws ServiceException {
		
		if(configuration.getProperty(INDEX_PRODUCTS)==null || configuration.getProperty(INDEX_PRODUCTS).equals(Constants.FALSE)) {
			return;
		}
		
		Set<ProductDescription> descriptions = product.getDescriptions();
		for(ProductDescription description : descriptions) {
			
			StringBuilder collectionName = new StringBuilder();
			collectionName.append(PRODUCT_INDEX_NAME).append(UNDERSCORE).append(description.getLanguage().getCode()).append(UNDERSCORE).append(store.getCode().toLowerCase());

			try {
				searchService.deleteObject(collectionName.toString(), new StringBuilder().append(PRODUCT_INDEX_NAME).append(UNDERSCORE).append(description.getLanguage().getCode()).toString(), String.valueOf(product.getId()));
			} catch (Exception e) {
				LOGGER.error("Cannot delete index for product id [" + product.getId() + "], ",e);
			}
		}
	
	}
	

	public SearchKeywords searchForKeywords(String collectionName, String jsonString, int entriesCount) throws ServiceException {
		
     		
		try {

			SearchResponse response = searchService.searchAutoComplete(collectionName, jsonString, entriesCount);
			
			SearchKeywords keywords = new SearchKeywords();
			keywords.setKeywords(Arrays.asList(response.getInlineSearchList()));
			
			return keywords;
			
		} catch (Exception e) {
			LOGGER.error("Error while searching keywords " + jsonString,e);
			throw new ServiceException(e);
		}

		
	}
	

	public com.salesmanager.core.model.search.SearchResponse search(MerchantStore store, String languageCode, String jsonString, int entriesCount, int startIndex) throws ServiceException {
		

		try {
			
			StringBuilder collectionName = new StringBuilder();
			collectionName.append(PRODUCT_INDEX_NAME).append(UNDERSCORE).append(languageCode).append(UNDERSCORE).append(store.getCode().toLowerCase());
			
			
			SearchRequest request = new SearchRequest();
			request.addCollection(collectionName.toString());
			request.setSize(entriesCount);
			request.setStart(startIndex);
			request.setJson(jsonString);
			
			SearchResponse response = searchService.search(request);
			
			com.salesmanager.core.model.search.SearchResponse resp = new com.salesmanager.core.model.search.SearchResponse();
			resp.setTotalCount(0);
			
			if(response != null) {
				resp.setTotalCount(response.getCount());
				
				List<SearchEntry> entries = new ArrayList<SearchEntry>();
				
				Collection<SearchHit> hits = response.getSearchHits();
				
				if(!CollectionUtils.isEmpty(hits)) {
					for(SearchHit hit : hits) {
						
						SearchEntry entry = new SearchEntry();
		
						//Map<String,Object> metaEntries = hit.getMetaEntries();
						Map<String,Object> metaEntries = hit.getItem();
						IndexProduct indexProduct = new IndexProduct();
						//Map sourceEntries = (Map)metaEntries.get("source");
						
						indexProduct.setDescription((String)metaEntries.get("description"));
						indexProduct.setHighlight((String)metaEntries.get("highlight"));
						indexProduct.setId((String)metaEntries.get("id"));
						indexProduct.setLang((String)metaEntries.get("lang"));
						indexProduct.setName(((String)metaEntries.get("name")));
						indexProduct.setManufacturer(((String)metaEntries.get("manufacturer")));
						indexProduct.setPrice(Double.valueOf(((String)metaEntries.get("price"))));
						indexProduct.setStore(((String)metaEntries.get("store")));
						entry.setIndexProduct(indexProduct);
						entries.add(entry);
						
						/**
						 * no more support for highlighted
						 */

					}
					
					resp.setEntries(entries);
					
					//Map<String,List<FacetEntry>> facets = response.getFacets();
					Map<String,Facet> facets = response.getFacets();
					if(facets!=null) {
						Map<String,List<SearchFacet>> searchFacets = new HashMap<String,List<SearchFacet>>();
						for(String key : facets.keySet()) {
							
							Facet f = facets.get(key);
							List<com.shopizer.search.services.Entry> ent = f.getEntries();
							
							//List<FacetEntry> f = facets.get(key);
							
							List<SearchFacet> fs = searchFacets.get(key);
							if(fs==null) {
								fs = new ArrayList<SearchFacet>();
								searchFacets.put(key, fs);
							}
		
							for(com.shopizer.search.services.Entry facetEntry : ent) {
							
								SearchFacet searchFacet = new SearchFacet();
								searchFacet.setKey(facetEntry.getName());
								searchFacet.setName(facetEntry.getName());
								searchFacet.setCount(facetEntry.getCount());
								
								fs.add(searchFacet);
							
							}
							
						}
						
						resp.setFacets(searchFacets);
					
					}
				
				}
			}
			
			
			
			return resp;
			
			
		} catch (Exception e) {
			LOGGER.error("Error while searching keywords " + jsonString,e);
			throw new ServiceException(e);
		}
		
	}
	
}

