package com.salesmanager.core.business.services.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.helper.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.configuration.ApplicationSearchConfiguration;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.pricing.PricingService;
import com.salesmanager.core.business.utils.CoreConfiguration;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.category.CategoryDescription;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValueDescription;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.merchant.MerchantStore;

import modules.commons.search.SearchModule;
import modules.commons.search.configuration.SearchConfiguration;
import modules.commons.search.request.Document;
import modules.commons.search.request.IndexItem;
import modules.commons.search.request.RequestOptions;
import modules.commons.search.request.SearchRequest;
import modules.commons.search.request.SearchResponse;

@Service("productSearchService")
@EnableConfigurationProperties(value = ApplicationSearchConfiguration.class)
public class SearchServiceImpl implements com.salesmanager.core.business.services.search.SearchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

	private final static String PRODUCT_INDEX_NAME = "product";
	private final static String UNDERSCORE = "_";
	private final static String INDEX_PRODUCTS = "INDEX_PRODUCTS";

	// @Inject
	// private com.shopizer.search.services.SearchService searchService;

	@Inject
	private PricingService pricingService;

	@Inject
	private CoreConfiguration configuration;

	// @Autowired
	// private ApplicationSearchConfiguration applicationSearchConfiguration;

	@Autowired
	private ApplicationSearchConfiguration applicationSearchConfiguration;

	@Autowired(required = false)
	private SearchModule searchModule;

	@PostConstruct
	public void init() {

		/**
		 * Configure search module
		 */

		if (searchModule != null) {

			SearchConfiguration searchConfiguration = this.config();
			try {
				searchModule.configure(searchConfiguration);
			} catch (Exception e) {
				LOGGER.error("SearchModule cannot be configured [" + e.getMessage() + "]", e);
			}
		}
	}

	@Async
	@SuppressWarnings("rawtypes")
	public void index(MerchantStore store, Product product) throws ServiceException {

		Validate.notNull(product.getId(),"Product.id cannot be null");
		
		if(configuration.getProperty(INDEX_PRODUCTS)==null||configuration.getProperty(INDEX_PRODUCTS).equals(Constants.FALSE)){return;}

		List<String> languages = this.languages(product);
		
		
		//existing documents
		List<Document> documents;
		try {
			documents = searchModule.getDocument(product.getId(), languages, RequestOptions.DEFAULT);
	
			if(!CollectionUtils.isEmpty(documents)){
				searchModule.delete(languages, product.getId());
			}
	
	
			FinalPrice price=pricingService.calculateProductPrice(product);
	
			Set<ProductDescription>descriptions=product.getDescriptions();
			
			for(ProductDescription description:descriptions){

				IndexItem item = new IndexItem();
				item.setId(product.getId());
				item.setStore(store.getCode().toLowerCase());
				item.setDescription(description.getDescription());
				item.setName(description.getName());
				item.setPrice(price.getStringPrice());
				
				if(product.getManufacturer()!=null){
					item.setBrand(manufacturer(product.getManufacturer(), description.getLanguage().getCode()));
				}
				
				if(!CollectionUtils.isEmpty(product.getCategories())) {
					item.setCategory(category(product.getCategories().iterator().next(), description.getLanguage().getCode()));
				}
				
				if(!CollectionUtils.isEmpty(product.getAttributes())) {
					Map<String,String> attributes = attributes(product, description.getLanguage().getCode());
					item.setAttributes(attributes);
				}
				
				item.setLanguage(description.getLanguage().getCode());
				
				this.searchModule.index(item);
	
			}
		
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		

	}



	/*
	 * public SearchKeywords searchForKeywords(String collectionName, String word,
	 * int entriesCount) throws ServiceException {
	 * 
	 * try {
	 * 
	 * // SearchResponse response = null; //
	 * searchService.searchAutoComplete(collectionName, word, entriesCount);
	 * 
	 * SearchKeywords keywords = new SearchKeywords(); // if (response != null &&
	 * response.getInlineSearchList() != null) { //
	 * keywords.setKeywords(Arrays.asList(response.getInlineSearchList())); // }
	 * 
	 * return keywords;
	 * 
	 * } catch (Exception e) { LOGGER.error("Error while searching keywords " +
	 * word, e); throw new ServiceException(e); }
	 * 
	 * }
	 */

	/*
	 * public com.salesmanager.core.model.search.SearchResponse search(MerchantStore
	 * store, String languageCode, String term, int entriesCount, int startIndex)
	 * throws ServiceException {
	 * 
	 * try {
	 * 
	 * StringBuilder collectionName = new StringBuilder();
	 * collectionName.append(PRODUCT_INDEX_NAME).append(UNDERSCORE).append(
	 * languageCode).append(UNDERSCORE) .append(store.getCode().toLowerCase());
	 * 
	 *//**
		 * 
		 * SearchRequest request = new SearchRequest();
		 * request.addCollection(collectionName.toString());
		 * request.setSize(entriesCount); request.setStart(startIndex);
		 * request.setMatch(term);
		 * 
		 * SearchResponse response = searchService.search(request);
		 **/

	/*
	 * 
	 * com.salesmanager.core.model.search.SearchResponse resp = new
	 * com.salesmanager.core.model.search.SearchResponse(); resp.setTotalCount(0);
	 * 
	 * 
	 * 
	 * if (response != null) { resp.setTotalCount(response.getCount());
	 * 
	 * List<SearchEntry> entries = new ArrayList<SearchEntry>();
	 * 
	 * Collection<SearchHit> hits = response.getSearchHits();
	 * 
	 * if (!CollectionUtils.isEmpty(hits)) { for (SearchHit hit : hits) {
	 * 
	 * SearchEntry entry = new SearchEntry();
	 * 
	 * Map<String, Object> metaEntries = hit.getItem(); IndexProduct indexProduct =
	 * new IndexProduct();
	 * 
	 * Object desc = metaEntries.get("description"); if (desc instanceof JsonNull ==
	 * false) { indexProduct.setDescription((String)
	 * metaEntries.get("description")); }
	 * 
	 * Object hl = metaEntries.get("highlight"); if (hl instanceof JsonNull ==
	 * false) { indexProduct.setHighlight((String) metaEntries.get("highlight")); }
	 * indexProduct.setId((String) metaEntries.get("id"));
	 * indexProduct.setLang((String) metaEntries.get("lang"));
	 * 
	 * Object nm = metaEntries.get("name"); if (nm instanceof JsonNull == false) {
	 * indexProduct.setName(((String) metaEntries.get("name"))); }
	 * 
	 * Object mf = metaEntries.get("manufacturer"); if (mf instanceof JsonNull ==
	 * false) { indexProduct.setManufacturer(((String)
	 * metaEntries.get("manufacturer"))); }
	 * indexProduct.setPrice(Double.valueOf(((String) metaEntries.get("price"))));
	 * indexProduct.setStore(((String) metaEntries.get("store")));
	 * entry.setIndexProduct(indexProduct); entries.add(entry);
	 * 
	 *//**
		 * no more support for highlighted
		 *//*
			 * 
			 * }
			 * 
			 * resp.setEntries(entries);
			 * 
			 * // Map<String,List<FacetEntry>> facets = response.getFacets(); Map<String,
			 * Facet> facets = response.getFacets(); if (facets != null && facets.size() >
			 * 0) { Map<String, List<SearchFacet>> searchFacets = new HashMap<String,
			 * List<SearchFacet>>(); for (String key : facets.keySet()) {
			 * 
			 * Facet f = facets.get(key); List<com.shopizer.search.services.Entry> ent =
			 * f.getEntries();
			 * 
			 * // List<FacetEntry> f = facets.get(key);
			 * 
			 * List<SearchFacet> fs = searchFacets.computeIfAbsent(key, k -> new
			 * ArrayList<>());
			 * 
			 * for (com.shopizer.search.services.Entry facetEntry : ent) {
			 * 
			 * SearchFacet searchFacet = new SearchFacet();
			 * searchFacet.setKey(facetEntry.getName());
			 * searchFacet.setName(facetEntry.getName());
			 * searchFacet.setCount(facetEntry.getCount());
			 * 
			 * fs.add(searchFacet);
			 * 
			 * }
			 * 
			 * }
			 * 
			 * resp.setFacets(searchFacets);
			 * 
			 * }
			 * 
			 * } }
			 * 
			 * 
			 * return resp;
			 * 
			 * } catch (Exception e) { LOGGER.error("Error while searching keywords " +
			 * term, e); throw new ServiceException(e); }
			 * 
			 * }
			 */

	private SearchConfiguration config() {

		SearchConfiguration config = new SearchConfiguration();
		config.setClusterName(applicationSearchConfiguration.getClusterName());
		config.setHosts(applicationSearchConfiguration.getHost());
		config.setCredentials(applicationSearchConfiguration.getCredentials());

		config.setLanguages(applicationSearchConfiguration.getSearchLanguages());

		/**
		 * The mapping
		 */
		config.getProductMappings().put("variants", "nested");
		config.getProductMappings().put("attributes", "nested");
		config.getProductMappings().put("brand", "keyword");
		config.getProductMappings().put("store", "keyword");
		config.getProductMappings().put("reviews", "keyword");
		config.getProductMappings().put("image", "keyword");
		config.getProductMappings().put("category", "text");
		config.getProductMappings().put("name", "text");
		config.getProductMappings().put("description", "text");
		config.getProductMappings().put("price", "float");
		config.getProductMappings().put("id", "long");

		config.getKeywordsMappings().put("store", "keyword");

		return config;

	}

	@Override
	public void index(MerchantStore store, Product product, ProductInstance instance) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteDocument(MerchantStore store, Product product) throws ServiceException {
		
		List<String> languages = this.languages(product);
		
		try {
			searchModule.delete(languages, product.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}


	}

	@Override
	public SearchResponse searchKeywords(MerchantStore store, String language, SearchRequest search, int entriesCount)
			throws ServiceException {
		try {
			return searchModule.searchKeywords(search);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public SearchResponse search(MerchantStore store, String language, SearchRequest search, int entriesCount,
			int startIndex) throws ServiceException {
		
		try {
			return searchModule.searchProducts(search);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public Document getDocument(String language, MerchantStore store, Long productId) throws ServiceException {
		try {
			return searchModule.getDocument(productId, language, RequestOptions.DEFAULT);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	private List<String> languages(Product product) {
		return product.getDescriptions().stream().map(d -> d.getLanguage().getCode()).collect(Collectors.toList());
	}
	
	private String manufacturer(Manufacturer manufacturer, String language) {
		ManufacturerDescription description = manufacturer.getDescriptions().stream().filter(d -> d.getLanguage().getCode().equals(language)).findFirst().get();
		return description.getName();
	}
	
	private String category(Category category, String language) {
		CategoryDescription description = category.getDescriptions().stream().filter(d -> d.getLanguage().getCode().equals(language)).findFirst().get();
		return description.getName();
	}
	
	private Map<String, String> attributes(Product product, String language) {
		
		/**
		 * ProductAttribute
		 * 	ProductOption
		 * 	ProductOptionValue
		 */
		
		Map<String, String> allAttributes = new HashMap<String, String>();
		
		for(ProductAttribute attribute: product.getAttributes()) {
			Map<String, String> attr = this.attribute(attribute, language);
			allAttributes.putAll(attr);
		}
		
		return allAttributes;
		
	}
	
	private Map<String, String> attribute(ProductAttribute attribute, String language) {
		Map<String, String> attributeValue = new HashMap<String, String>();
		
		String attributeCode = attribute.getProductOption().getCode();
		ProductOptionValueDescription value = attribute.getProductOptionValue().getDescriptions().stream().filter(a -> a.getLanguage().getCode().equals(language)).findFirst().get();
		
		attributeValue.put(attributeCode, value.getName());
		
		return attributeValue;
	}
		
		

}
