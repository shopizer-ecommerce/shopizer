package com.salesmanager.web.shop.controller.category;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.ProductCriteria;
import com.salesmanager.core.business.catalog.product.service.PricingService;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.catalog.product.service.manufacturer.ManufacturerService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.merchant.service.MerchantStoreService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.utils.CacheUtils;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.entity.catalog.ProductList;
import com.salesmanager.web.entity.catalog.category.ReadableCategory;
import com.salesmanager.web.entity.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.web.entity.catalog.product.ReadableProduct;
import com.salesmanager.web.entity.shop.Breadcrumb;
import com.salesmanager.web.entity.shop.PageInformation;
import com.salesmanager.web.populator.catalog.ReadableCategoryPopulator;
import com.salesmanager.web.populator.catalog.ReadableProductPopulator;
import com.salesmanager.web.populator.manufacturer.ReadableManufacturerPopulator;
import com.salesmanager.web.shop.controller.ControllerConstants;
import com.salesmanager.web.shop.model.filter.QueryFilter;
import com.salesmanager.web.shop.model.filter.QueryFilterType;
import com.salesmanager.web.utils.BreadcrumbsUtils;
import com.salesmanager.web.utils.LabelUtils;
import com.salesmanager.web.utils.PageBuilderUtils;

import edu.emory.mathcs.backport.java.util.Collections;


/**
 * Renders a given category page based on friendly url
 * Can also filter by facets such as manufacturer
 * @author Carl Samson
 *
 */
@Controller
public class ShoppingCategoryController {
	

	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private MerchantStoreService merchantStoreService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	@Autowired
	private LabelUtils messages;
	
	@Autowired
	private BreadcrumbsUtils breadcrumbsUtils;
	
	@Autowired
	private CacheUtils cache;
	
	@Autowired
	private PricingService pricingService;
	

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCategoryController.class);
	
	
	/**
	 * 
	 * @param friendlyUrl
	 * @param ref
	 * @param model
	 * @param request
	 * @param response
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/shop/category/{friendlyUrl}.html/ref={ref}")
	public String displayCategoryWithReference(@PathVariable final String friendlyUrl, @PathVariable final String ref, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		
		
		return this.displayCategory(friendlyUrl,ref,model,request,response,locale);
	}
	

	
	/**
	 * Category page entry point
	 * @param friendlyUrl
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/shop/category/{friendlyUrl}.html")
	public String displayCategoryNoReference(@PathVariable final String friendlyUrl, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		return this.displayCategory(friendlyUrl,null,model,request,response,locale);
	}
	
	@SuppressWarnings("unchecked")
	private String displayCategory(final String friendlyUrl, final String ref, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
		
		
		
		
		//get category
		Category category = categoryService.getBySeUrl(store, friendlyUrl);
		
		Language language = (Language)request.getAttribute("LANGUAGE");
		
		if(category==null) {
			LOGGER.error("No category found for friendlyUrl " + friendlyUrl);
			//redirect on page not found
			return PageBuilderUtils.build404(store);
			
		}
		
		ReadableCategoryPopulator populator = new ReadableCategoryPopulator();
		ReadableCategory categoryProxy = populator.populate(category, new ReadableCategory(), store, language);

		Breadcrumb breadCrumb = breadcrumbsUtils.buildCategoryBreadcrumb(categoryProxy, store, language, request.getContextPath());
		request.getSession().setAttribute(Constants.BREADCRUMB, breadCrumb);
		request.setAttribute(Constants.BREADCRUMB, breadCrumb);
		
		
		//meta information
		PageInformation pageInformation = new PageInformation();
		pageInformation.setPageDescription(categoryProxy.getDescription().getMetaDescription());
		pageInformation.setPageKeywords(categoryProxy.getDescription().getKeyWords());
		pageInformation.setPageTitle(categoryProxy.getDescription().getTitle());
		pageInformation.setPageUrl(categoryProxy.getDescription().getFriendlyUrl());
		
		//** retrieves category id drill down**//
		String lineage = new StringBuilder().append(category.getLineage()).append(category.getId()).append(Constants.CATEGORY_LINEAGE_DELIMITER).toString();

		
		
		request.setAttribute(Constants.REQUEST_PAGE_INFORMATION, pageInformation);
		
		//TODO add to caching
		List<Category> subCategs = categoryService.listByLineage(store, lineage);
		List<Long> subIds = new ArrayList<Long>();
		if(subCategs!=null && subCategs.size()>0) {
			for(Category c : subCategs) {
				subIds.add(c.getId());
			}
		}
		subIds.add(category.getId());


		StringBuilder subCategoriesCacheKey = new StringBuilder();
		subCategoriesCacheKey
		.append(store.getId())
		.append("_")
		.append(category.getId())
		.append("_")
		.append(Constants.SUBCATEGORIES_CACHE_KEY)
		.append("-")
		.append(language.getCode());
		
		StringBuilder subCategoriesMissed = new StringBuilder();
		subCategoriesMissed
		.append(subCategoriesCacheKey.toString())
		.append(Constants.MISSED_CACHE_KEY);
		
		List<BigDecimal> prices = new ArrayList<BigDecimal>();
		List<ReadableCategory> subCategories = null;
		Map<Long,Long> countProductsByCategories = null;

		if(store.isUseCache()) {

			//get from the cache
			subCategories = (List<ReadableCategory>) cache.getFromCache(subCategoriesCacheKey.toString());
			if(subCategories==null) {
				//get from missed cache
				//Boolean missedContent = (Boolean)cache.getFromCache(subCategoriesMissed.toString());

				//if(missedContent==null) {
					countProductsByCategories = getProductsByCategory(store, category, lineage, subCategs);
					subCategories = getSubCategories(store,category,countProductsByCategories,language,locale);
					
					if(subCategories!=null) {
						cache.putInCache(subCategories, subCategoriesCacheKey.toString());
					} else {
						//cache.putInCache(new Boolean(true), subCategoriesCacheKey.toString());
					}
				//}
			}
		} else {
			countProductsByCategories = getProductsByCategory(store, category, lineage, subCategs);
			subCategories = getSubCategories(store,category,countProductsByCategories,language,locale);
		}

		//Parent category
		ReadableCategory parentProxy  = null;
		if(!StringUtils.isBlank(ref) && ref.contains("c")) {
			try {
				//get preceding id from the reference chain
				String categoryChain = ref.substring(ref.indexOf(Constants.REF_SPLITTER)+1);
				int categoryPosition = categoryChain.indexOf(String.valueOf(category.getId()));
				String sCategoryId = categoryChain.substring(categoryPosition++,categoryPosition++);
				Long parentId = Long.parseLong(sCategoryId);
				Category parent = categoryService.getById(parentId);
				parentProxy = populator.populate(parent, new ReadableCategory(), store, language);
			} catch(Exception e) {
				LOGGER.error("Cannot parse category id to Long ",ref );
			}
		}
		
		
		//** List of manufacturers **//
		List<ReadableManufacturer> manufacturerList = getManufacturersByProductAndCategory(store,category,subIds,language);

		model.addAttribute("manufacturers", manufacturerList);
		model.addAttribute("parent", parentProxy);
		model.addAttribute("category", categoryProxy);
		model.addAttribute("subCategories", subCategories);
		
		if(parentProxy!=null) {
			request.setAttribute(Constants.LINK_CODE, parentProxy.getDescription().getFriendlyUrl());
		}
		
		
		/** template **/
		StringBuilder template = new StringBuilder().append(ControllerConstants.Tiles.Category.category).append(".").append(store.getStoreTemplate());

		return template.toString();
	}
	
	@SuppressWarnings("unchecked")
	private List<ReadableManufacturer> getManufacturersByProductAndCategory(MerchantStore store, Category category, List<Long> subCategoryIds, Language language) throws Exception {

		List<ReadableManufacturer> manufacturerList = null;
		/** List of manufacturers **/
		if(subCategoryIds!=null && subCategoryIds.size()>0) {
			
			StringBuilder manufacturersKey = new StringBuilder();
			manufacturersKey
			.append(store.getId())
			.append("_")
			.append(Constants.MANUFACTURERS_BY_PRODUCTS_CACHE_KEY)
			.append("-")
			.append(language.getCode());
			
			StringBuilder manufacturersKeyMissed = new StringBuilder();
			manufacturersKeyMissed
			.append(manufacturersKey.toString())
			.append(Constants.MISSED_CACHE_KEY);

			if(store.isUseCache()) {

				//get from the cache
				 
				manufacturerList = (List<ReadableManufacturer>) cache.getFromCache(manufacturersKey.toString());
				

				if(manufacturerList==null) {
					//get from missed cache
					//Boolean missedContent = (Boolean)cache.getFromCache(manufacturersKeyMissed.toString());
					//if(missedContent==null) {
						manufacturerList = this.getManufacturers(store, subCategoryIds, language);
						if(CollectionUtils.isEmpty(manufacturerList)) {
							cache.putInCache(new Boolean(true), manufacturersKeyMissed.toString());
						} else {
							//cache.putInCache(manufacturerList, manufacturersKey.toString());
						}
					//}
				}
			} else {
				manufacturerList  = this.getManufacturers(store, subCategoryIds, language);
			}
		}
		return manufacturerList;
	}
		
	private List<ReadableManufacturer> getManufacturers(MerchantStore store, List<Long> ids, Language language) throws Exception {
		List<ReadableManufacturer> manufacturerList = null;
		List<com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer> manufacturers = manufacturerService.listByProductsByCategoriesId(store, ids, language);
		if(!CollectionUtils.isEmpty(manufacturers)) {
			manufacturerList = new ArrayList<ReadableManufacturer>();
			for(com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer manufacturer : manufacturers) {
				ReadableManufacturer manuf = new ReadableManufacturerPopulator().populate(manufacturer, new ReadableManufacturer(), store, language);
				manufacturerList.add(manuf);
				
			}
		}
		return manufacturerList;
	}
	
	private Map<Long,Long> getProductsByCategory(MerchantStore store, Category category, String lineage, List<Category> subCategories) throws Exception {

		if(CollectionUtils.isEmpty(subCategories)) {
			return null;
		}
		List<Long> ids = new ArrayList<Long>();
		if(subCategories!=null && subCategories.size()>0) {
			for(Category c : subCategories) {
				ids.add(c.getId());
			}
		} 

		List<Object[]> countProductsByCategories = categoryService.countProductsByCategories(store, ids);
		Map<Long, Long> countByCategories = new HashMap<Long,Long>();
		
		for(Object[] counts : countProductsByCategories) {
			Category c = (Category)counts[0];
			if(c.getParent().getId()==category.getId()) {
				countByCategories.put(c.getId(), (Long)counts[1]);
			} else {
				//get lineage
				String lin = c.getLineage();
				String[] categoryPath = lin.split(Constants.CATEGORY_LINEAGE_DELIMITER);
				for(int i=0 ; i<categoryPath.length; i++) {
					String sId = categoryPath[i];
					if(!StringUtils.isBlank(sId)) {
							Long count = countByCategories.get(Long.parseLong(sId));
							if(count!=null) {
								count = count + (Long)counts[1];
								countByCategories.put(Long.parseLong(sId), count);
							}
					}
				}
			}
		}
		
		return countByCategories;
		
	}
	
	private List<ReadableCategory> getSubCategories(MerchantStore store, Category category, Map<Long,Long> productCount, Language language, Locale locale) throws Exception {
		
		
		//sub categories
		List<Category> subCategories = categoryService.listByParent(category, language);
		ReadableCategoryPopulator populator = new ReadableCategoryPopulator();
		List<ReadableCategory> subCategoryProxies = new ArrayList<ReadableCategory>();
		
		
		
		for(Category sub : subCategories) {
			ReadableCategory cProxy  = populator.populate(sub, new ReadableCategory(), store, language);
			//com.salesmanager.web.entity.catalog.Category cProxy =  catalogUtils.buildProxyCategory(sub, store, locale);
			if(productCount!=null) {
				Long total = productCount.get(cProxy.getId());
				if(total!=null) {
					cProxy.setProductCount(total.intValue());
				}
			}
			subCategoryProxies.add(cProxy);
		}
		
		return subCategoryProxies;
		
	}
	
	

	/**
	 * Returns all categories for a given MerchantStore
	 */
	@RequestMapping("/services/public/category/{store}/{language}")
	@ResponseBody
	public List<ReadableCategory> getCategories(@PathVariable final String language, @PathVariable final String store, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String,Language> langs = languageService.getLanguagesMap();
		Language l = langs.get(language);
		if(l==null) {
			l = languageService.getByCode(Constants.DEFAULT_LANGUAGE);
		}
		
		MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);

		if(merchantStore!=null) {
			if(!merchantStore.getCode().equals(store)) {
				merchantStore = null; //reset for the current request
			}
		}
		
		if(merchantStore== null) {
			merchantStore = merchantStoreService.getByCode(store);
		}
		
		if(merchantStore==null) {
			LOGGER.error("Merchant store is null for code " + store);
			response.sendError(503, "Merchant store is null for code " + store);//TODO localized message
			return null;
		}
		
		List<Category> categories = categoryService.listByStore(merchantStore, l);
		
		ReadableCategoryPopulator populator = new ReadableCategoryPopulator();
		
		List<ReadableCategory> returnCategories = new ArrayList<ReadableCategory>();
		for(Category category : categories) {
			ReadableCategory categoryProxy = populator.populate(category, new ReadableCategory(), merchantStore, l);
			returnCategories.add(categoryProxy);
		}
		
		return returnCategories;
	}

	/**
	 * Returns an array of products belonging to a given category
	 * in a given language for a given store
	 * url example :  http://<host>/sm-shop/shop/services/public/products/DEFAULT/BOOKS
	 * @param store
	 * @param language
	 * @param category
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/services/public/products/{store}/{language}/{category}")
	@ResponseBody
	public ProductList getProducts(@PathVariable final String store, @PathVariable final String language, @PathVariable final String category, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//http://localhost:8080/sm-shop/services/public/products/DEFAULT/en/book.html

		try {

		
			/**
			 * How to Spring MVC Rest web service - ajax / jquery
			 * http://codetutr.com/2013/04/09/spring-mvc-easy-rest-based-json-services-with-responsebody/
			 */
			
			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
			Map<String,Language> langs = languageService.getLanguagesMap();

			if(merchantStore!=null) {
				if(!merchantStore.getCode().equals(store)) {
					merchantStore = null; //reset for the current request
				}
			}
			
			if(merchantStore== null) {
				merchantStore = merchantStoreService.getByCode(store);
			}
			
			if(merchantStore==null) {
				LOGGER.error("Merchant store is null for code " + store);
				response.sendError(503, "Merchant store is null for code " + store);//TODO localized message
				return null;
			}
			
			//get the category by code
			Category cat = categoryService.getBySeUrl(merchantStore, category);

			if(cat==null) {
				LOGGER.error("Category with friendly url " + category + " is null");
				response.sendError(503, "Category is null");//TODO localized message
			}
			
			String lineage = new StringBuilder().append(cat.getLineage()).append(cat.getId()).append("/").toString();
			
			List<Category> categories = categoryService.listByLineage(store, lineage);
			
			List<Long> ids = new ArrayList<Long>();
			if(categories!=null && categories.size()>0) {
				for(Category c : categories) {
					ids.add(c.getId());
				}
			} 
			ids.add(cat.getId());
			
			Language lang = langs.get(language);
			if(lang==null) {
				lang = langs.get(Constants.DEFAULT_LANGUAGE);
			}
			
			List<com.salesmanager.core.business.catalog.product.model.Product> products = productService.getProducts(ids, lang);
			
			ProductList productList = new ProductList();
			
			ReadableProductPopulator populator = new ReadableProductPopulator();
			populator.setPricingService(pricingService);

			for(Product product : products) {
				//create new proxy product
				ReadableProduct  p = populator.populate(product, new ReadableProduct(), merchantStore, lang);
				productList.getProducts().add(p);
	
			}
			
			productList.setProductCount(productList.getProducts().size());
			return productList;
			
		
		} catch (Exception e) {
			LOGGER.error("Error while getting category",e);
			response.sendError(503, "Error while getting category");
		}
		
		return null;
	}
	
	
	/**
	 * Will page products of a given category
	 * @param store
	 * @param language
	 * @param category
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/services/public/products/page/{start}/{max}/{store}/{language}/{category}.html")
	@ResponseBody
	public ProductList getProducts(@PathVariable int start, @PathVariable int max, @PathVariable String store, @PathVariable final String language, @PathVariable final String category, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		return this.getProducts(start, max, store, language, category, null, model, request, response);
	}
	
	
	/**
	 * An entry point for filtering by another entity such as Manufacturer
	 * filter=BRAND&filter-value=123
	 * @param start
	 * @param max
	 * @param store
	 * @param language
	 * @param category
	 * @param filterType
	 * @param filterValue
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/services/public/products/page/{start}/{max}/{store}/{language}/{category}.html/filter={filterType}/filter-value={filterValue}")
	@ResponseBody
	public ProductList getProductsFilteredByType(@PathVariable int start, @PathVariable int max, @PathVariable String store, @PathVariable final String language, @PathVariable final String category, @PathVariable final String filterType, @PathVariable final String filterValue, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<QueryFilter> queryFilters = null;
		try {
			if(filterType.equals(QueryFilterType.BRAND.name())) {//the only one implemented so far
				QueryFilter filter = new QueryFilter();
				filter.setFilterType(QueryFilterType.BRAND);
				filter.setFilterId(Long.parseLong(filterValue));
				if(queryFilters==null) {
					queryFilters = new ArrayList<QueryFilter>();
				}
				queryFilters.add(filter);
			}
		} catch(Exception e) {
			LOGGER.error("Invalid filter or filter-value " + filterType + " - " + filterValue,e);
		}
		
		return this.getProducts(start, max, store, language, category, queryFilters, model, request, response);
	}
	
	
	private ProductList getProducts(final int start, final int max, final String store, final String language, final String category, final List<QueryFilter> filters, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		try {

			
			/**
			 * How to Spring MVC Rest web service - ajax / jquery
			 * http://codetutr.com/2013/04/09/spring-mvc-easy-rest-based-json-services-with-responsebody/
			 */
			
			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
			List<BigDecimal> prices = new ArrayList<BigDecimal>();
			
			Map<String,Language> langs = languageService.getLanguagesMap();
			
			if(merchantStore!=null) {
				if(!merchantStore.getCode().equals(store)) {
					merchantStore = null; //reset for the current request
				}
			}
			
			if(merchantStore== null) {
				merchantStore = merchantStoreService.getByCode(store);
			}
			
			if(merchantStore==null) {
				LOGGER.error("Merchant store is null for code " + store);
				response.sendError(503, "Merchant store is null for code " + store);//TODO localized message
				return null;
			}
			
			//get the category by code
			Category cat = categoryService.getBySeUrl(merchantStore, category);
			
			if(cat==null) {
				LOGGER.error("Category " + category + " is null");
				response.sendError(503, "Category is null");//TODO localized message
				return null;
			}
			
			String lineage = new StringBuilder().append(cat.getLineage()).append(cat.getId()).append("/").toString();
			
			List<Category> categories = categoryService.listByLineage(store, lineage);
			
			List<Long> ids = new ArrayList<Long>();
			if(categories!=null && categories.size()>0) {
				for(Category c : categories) {
					ids.add(c.getId());
				}
			} 
			ids.add(cat.getId());
			

			Language lang = langs.get(language);
			if(lang==null) {
				lang = langs.get(Constants.DEFAULT_LANGUAGE);
			}
			
			ProductCriteria productCriteria = new ProductCriteria();
			productCriteria.setMaxCount(max);
			productCriteria.setStartIndex(start);
			productCriteria.setCategoryIds(ids);
			
			if(filters!=null) {
				for(QueryFilter filter : filters) {
					if(filter.getFilterType().name().equals(QueryFilterType.BRAND.name())) {//the only filter implemented
						productCriteria.setManufacturerId(filter.getFilterId());
					}
				}
			}

			com.salesmanager.core.business.catalog.product.model.ProductList products = productService.listByStore(merchantStore, lang, productCriteria);

			ReadableProductPopulator populator = new ReadableProductPopulator();
			populator.setPricingService(pricingService);
			
			
			ProductList productList = new ProductList();
			for(Product product : products.getProducts()) {

				//create new proxy product
				ReadableProduct  p = populator.populate(product, new ReadableProduct(), merchantStore, lang);
				productList.getProducts().add(p);
				prices.add(p.getPrice());
				
			}
			
			productList.setProductCount(products.getTotalCount());
			
			if(CollectionUtils.isNotEmpty(prices)) {
				BigDecimal minPrice = (BigDecimal)Collections.min(prices);
				BigDecimal maxPrice = (BigDecimal)Collections.max(prices);
				
				if(minPrice !=null && maxPrice !=null) {
					productList.setMinPrice(minPrice);
					productList.setMaxPrice(maxPrice);
				}
			}
			
			
			
			return productList;
			
		
		} catch (Exception e) {
			LOGGER.error("Error while getting products",e);
			response.sendError(503, "An error occured while retrieving products " + e.getMessage());
		}
		
		return null;

	}
	

	

	

}
