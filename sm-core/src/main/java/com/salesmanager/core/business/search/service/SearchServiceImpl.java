package com.salesmanager.core.business.search.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.catalog.product.model.price.FinalPrice;
import com.salesmanager.core.business.catalog.product.service.PricingService;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.search.model.IndexProduct;
import com.salesmanager.core.business.search.model.SearchEntry;
import com.salesmanager.core.business.search.model.SearchFacet;
import com.salesmanager.core.business.search.model.SearchKeywords;
import com.salesmanager.core.constants.Constants;
import com.salesmanager.core.utils.CoreConfiguration;
import com.shopizer.search.services.Facet;
import com.shopizer.search.services.SearchHit;
import com.shopizer.search.services.SearchRequest;
import com.shopizer.search.services.SearchResponse;


@Service("productSearchService")
public class SearchServiceImpl implements SearchService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);
	
	
	private final static String PRODUCT_INDEX_NAME = "product";
	private final static String UNDERSCORE = "_";
	private final static String INDEX_PRODUCTS = "INDEX_PRODUCTS";

	@Autowired
	private com.shopizer.search.services.SearchService searchService;
	
	@Autowired
	private PricingService pricingService;
	
	@Autowired
	private CoreConfiguration configuration;
	
	@Override
	public void initService() {
		searchService.initService();
	}

	@SuppressWarnings("rawtypes")
	@Override
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

	@Override
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
	
	@Override
	public SearchKeywords searchForKeywords(String collectionName, String jsonString, int entriesCount) throws ServiceException {
		
		/**
		 * 	$('#search').searchAutocomplete({
			url: '<%=request.getContextPath()%>/search/autocomplete/keyword_en'
		  		//filter: function() { 
				//return '\"filter\" : {\"numeric_range\" : {\"price\" : {\"from\" : \"22\",\"to\" : \"45\",\"include_lower\" : true,\"include_upper\" : true}}}';
		  		//}
     		});
     		
     	**/	
     		
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
	
	@Override
	public com.salesmanager.core.business.search.model.SearchResponse search(MerchantStore store, String languageCode, String jsonString, int entriesCount, int startIndex) throws ServiceException {
		

		try {
			
			StringBuilder collectionName = new StringBuilder();
			collectionName.append(PRODUCT_INDEX_NAME).append(UNDERSCORE).append(languageCode).append(UNDERSCORE).append(store.getCode().toLowerCase());
			
			
			SearchRequest request = new SearchRequest();
			request.setCollection(collectionName.toString());
			request.setSize(entriesCount);
			request.setStart(startIndex);
			request.setJson(jsonString);
			
			SearchResponse response =searchService.search(request);
			
			com.salesmanager.core.business.search.model.SearchResponse resp = new com.salesmanager.core.business.search.model.SearchResponse();
			
			
			resp.setTotalCount(response.getCount());
			
			List<SearchEntry> entries = new ArrayList<SearchEntry>();
			
			Collection<SearchHit> hits = response.getSearchHits();
			for(SearchHit hit : hits) {
				
				SearchEntry entry = new SearchEntry();
				
				Map<String,Object> metaEntries = hit.getMetaEntries();
				IndexProduct indexProduct = new IndexProduct();
				Map sourceEntries = (Map)metaEntries.get("source");
				
				indexProduct.setDescription((String)sourceEntries.get("description"));
				indexProduct.setHighlight((String)sourceEntries.get("highlight"));
				indexProduct.setId((String)sourceEntries.get("id"));
				indexProduct.setLang((String)sourceEntries.get("lang"));
				indexProduct.setName(((String)sourceEntries.get("name")));
				indexProduct.setManufacturer(((String)sourceEntries.get("manufacturer")));
				indexProduct.setPrice(((Double)sourceEntries.get("price")));
				indexProduct.setStore(((String)sourceEntries.get("store")));
				entry.setIndexProduct(indexProduct);
				entries.add(entry);
				
				Map<String, HighlightField> fields = hit.getHighlightFields();
				if(fields!=null) {
					List<String> highlights = new ArrayList<String>();
					for(HighlightField field : fields.values()) {
						
						Text[] text = field.getFragments();
						//text[0]
						String f = field.getName();
						highlights.add(f);
						
						
					}
					
					entry.setHighlights(highlights);
				
				}
			}
			
			resp.setEntries(entries);
			
			Map<String,Facet> facets = response.getFacets();
			if(facets!=null) {
				Map<String,List<SearchFacet>> searchFacets = new HashMap<String,List<SearchFacet>>();
				for(String key : facets.keySet()) {
					
					Facet f = facets.get(key);
					
					List<SearchFacet> fs = searchFacets.get(key);
					if(fs==null) {
						fs = new ArrayList<SearchFacet>();
						searchFacets.put(key, fs);
					}
					
					List<com.shopizer.search.services.Entry> facetEntries = f.getEntries();
					for(com.shopizer.search.services.Entry facetEntry : facetEntries) {
					
						SearchFacet searchFacet = new SearchFacet();
						searchFacet.setKey(facetEntry.getName());
						searchFacet.setName(facetEntry.getName());
						searchFacet.setCount(f.getEntries().size());
						
						fs.add(searchFacet);
					
					}
					
				}
				
				resp.setFacets(searchFacets);
			
			}
			
			
			
			return resp;
			
			
		} catch (Exception e) {
			LOGGER.error("Error while searching keywords " + jsonString,e);
			throw new ServiceException(e);
		}
		
	}
	
}

