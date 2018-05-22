package com.salesmanager.shop.store.api.v1.product;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProductList;
import com.salesmanager.shop.store.controller.product.facade.ProductFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.ImageFilePath;
import com.salesmanager.shop.utils.LanguageUtils;

/**
 * API to create, read, update and delete a Product
 * API to create Manufacturer
 * @author Carl Samson
 *
 */
@Controller
@RequestMapping("/api/v1")
public class ProductApi {
	
	@Inject
	private MerchantStoreService merchantStoreService;
	
	@Inject
	private CategoryService categoryService;
	
	@Inject
	private CustomerService customerService;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private ProductFacade productFacade;
	
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;
	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private LanguageUtils languageUtils;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductApi.class);
	

    @ResponseStatus(HttpStatus.CREATED)
	@RequestMapping( value={"/private/products","/auth/products"}, method=RequestMethod.POST)
    public @ResponseBody PersistableProduct create(@Valid @RequestBody PersistableProduct product, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(com.salesmanager.core.business.constants.Constants.DEFAULT_STORE);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);	
			
			productFacade.saveProduct(merchantStore, product, language);
			
			return product;

			
		} catch (Exception e) {
			LOGGER.error("Error while creating product",e);
			try {
				response.sendError(503, "Error while creating product " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
		
	}
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping( value={"/private/products/{id}","/auth/products/{id}"}, method=RequestMethod.PUT)
    public @ResponseBody PersistableProduct update(@PathVariable Long id, @Valid @RequestBody PersistableProduct product, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(com.salesmanager.core.business.constants.Constants.DEFAULT_STORE);
			productFacade.saveProduct(merchantStore, product, merchantStore.getDefaultLanguage());
			
			return product;
			
		} catch (Exception e) {
			LOGGER.error("Error while updating product",e);
			try {
				response.sendError(503, "Error while updating product " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
		
	}
    
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping( value={"/private/products/{id}","/auth/products/{id}"}, method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			Product product = productService.getById(id);
			if(product != null){
				productFacade.deleteProduct(product);
			}else{
				response.sendError(404, "No Product found for ID : " + id);
			}
		} catch (Exception e) {
			LOGGER.error("Error while deleting product",e);
			try {
				response.sendError(503, "Error while deleting product " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
	}
    

/**
	@RequestMapping( value="/private/{store}/manufacturer", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public PersistableManufacturer createManufacturer(@PathVariable final String store, @Valid @RequestBody PersistableManufacturer manufacturer, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		try {
			
			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
			if(merchantStore!=null) {
				if(!merchantStore.getCode().equals(store)) {
					merchantStore = null;
				}
			}
			
			if(merchantStore== null) {
				merchantStore = merchantStoreService.getByCode(store);
			}
			
			if(merchantStore==null) {
				LOGGER.error("Merchant store is null for code " + store);
				response.sendError(503, "Merchant store is null for code " + store);
				return null;
			}

			PersistableManufacturerPopulator populator = new PersistableManufacturerPopulator();
			populator.setLanguageService(languageService);
			
			com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer manuf = new com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer();
			
			populator.populate(manufacturer, manuf, merchantStore, merchantStore.getDefaultLanguage());
		
			manufacturerService.save(manuf);
			
			manufacturer.setId(manuf.getId());
			
			return manufacturer;
			
		} catch (Exception e) {
			LOGGER.error("Error while saving product",e);
			try {
				response.sendError(503, "Error while saving product " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
		
	}
	
	
	@RequestMapping( value="/private/{store}/product/optionValue", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public PersistableProductOptionValue createProductOptionValue(@PathVariable final String store, @Valid @RequestBody PersistableProductOptionValue optionValue, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		try {
			
			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
			if(merchantStore!=null) {
				if(!merchantStore.getCode().equals(store)) {
					merchantStore = null;
				}
			}
			
			if(merchantStore== null) {
				merchantStore = merchantStoreService.getByCode(store);
			}
			
			if(merchantStore==null) {
				LOGGER.error("Merchant store is null for code " + store);
				response.sendError(503, "Merchant store is null for code " + store);
				return null;
			}

			PersistableProductOptionValuePopulator populator = new PersistableProductOptionValuePopulator();
			populator.setLanguageService(languageService);
			
			com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue optValue = new com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue();
			populator.populate(optionValue, optValue, merchantStore, merchantStore.getDefaultLanguage());
		
			productOptionValueService.save(optValue);
			
			optionValue.setId(optValue.getId());
			
			return optionValue;
			
		} catch (Exception e) {
			LOGGER.error("Error while saving product option value",e);
			try {
				response.sendError(503, "Error while saving product option value" + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
		
	}
	
	
	@RequestMapping( value="/private/{store}/product/option", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public PersistableProductOption createProductOption(@PathVariable final String store, @Valid @RequestBody PersistableProductOption option, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		try {
			
			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
			if(merchantStore!=null) {
				if(!merchantStore.getCode().equals(store)) {
					merchantStore = null;
				}
			}
			
			if(merchantStore== null) {
				merchantStore = merchantStoreService.getByCode(store);
			}
			
			if(merchantStore==null) {
				LOGGER.error("Merchant store is null for code " + store);
				response.sendError(503, "Merchant store is null for code " + store);
				return null;
			}

			PersistableProductOptionPopulator populator = new PersistableProductOptionPopulator();
			populator.setLanguageService(languageService);
			
			com.salesmanager.core.model.catalog.product.attribute.ProductOption opt = new com.salesmanager.core.model.catalog.product.attribute.ProductOption();
			populator.populate(option, opt, merchantStore, merchantStore.getDefaultLanguage());
		
			productOptionService.save(opt);
			
			option.setId(opt.getId());
			
			return option;
			
		} catch (Exception e) {
			LOGGER.error("Error while saving product option",e);
			try {
				response.sendError(503, "Error while saving product option" + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
	}
	
	
	@RequestMapping( value="/private/{store}/product/review", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public PersistableProductReview createProductReview(@PathVariable final String store, @Valid @RequestBody PersistableProductReview review, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		try {
			
			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
			if(merchantStore!=null) {
				if(!merchantStore.getCode().equals(store)) {
					merchantStore = null;
				}
			}
			
			if(merchantStore== null) {
				merchantStore = merchantStoreService.getByCode(store);
			}
			
			if(merchantStore==null) {
				LOGGER.error("Merchant store is null for code " + store);
				response.sendError(500, "Merchant store is null for code " + store);
				return null;
			}
			
			
			//rating already exist
			ProductReview prodReview = productReviewService.getByProductAndCustomer(review.getProductId(), review.getCustomerId());
			if(prodReview!=null) {
				response.sendError(500, "A review already exist for this customer and product");
				return null;
			}
			
			//rating maximum 5
			if(review.getRating()>Constants.MAX_REVIEW_RATING_SCORE) {
				response.sendError(503, "Maximum rating score is " + Constants.MAX_REVIEW_RATING_SCORE);
				return null;
			}
			
			

			PersistableProductReviewPopulator populator = new PersistableProductReviewPopulator();
			populator.setLanguageService(languageService);
			populator.setCustomerService(customerService);
			populator.setProductService(productService);
			
			com.salesmanager.core.model.catalog.product.review.ProductReview rev = new com.salesmanager.core.model.catalog.product.review.ProductReview();
			populator.populate(review, rev, merchantStore, merchantStore.getDefaultLanguage());
		
			productReviewService.create(rev);

			
			review.setId(rev.getId());
			
			return review;
			
		} catch (Exception e) {
			LOGGER.error("Error while saving product review",e);
			try {
				response.sendError(503, "Error while saving product review" + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
	}
	

	@RequestMapping("/public/products/{store}")
	@ResponseBody
	public ReadableProductList getProducts(@PathVariable String store, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		*//** default routine **//*
		
		MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
		if(merchantStore!=null) {
			if(!merchantStore.getCode().equals(store)) {
				merchantStore = null;
			}
		}
		
		if(merchantStore== null) {
			merchantStore = merchantStoreService.getByCode(store);
		}
		
		if(merchantStore==null) {
			LOGGER.error("Merchant store is null for code " + store);
			response.sendError(503, "Merchant store is null for code " + store);
			return null;
		}
		
		Language l = merchantStore.getDefaultLanguage();
		
		String lang = l.getCode();
		
		if(!StringUtils.isBlank(request.getParameter(Constants.LANG))) {
			
			lang = request.getParameter(Constants.LANG);
			
		}
		
		
		*//** end default routine **//*
		
		

		
		return this.getProducts(0, 10000, store, lang, null, null, request, response);
	}*/
	


	/**
	 * Filtering product lists based on product attributes
	 * ?category=1
	 * &manufacturer=2
	 * &type=...
	 * &lang=en|fr NOT REQUIRED, will use request language
	 * &start=0 NOT REQUIRED, can be used for pagination
	 * &count=10 NOT REQUIRED, can be used to limit item count
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/products", method=RequestMethod.GET)
	@ResponseBody
	public ReadableProductList getFiltered(
			@RequestParam(value = "lang", required=false) String lang, 
			@RequestParam(value = "category", required=false) Long category, 
			@RequestParam(value = "manufacturer", required=false) Long manufacturer,
			@RequestParam(value = "status", required=false) String status,
			@RequestParam(value = "owner", required=false) Long owner,
			@RequestParam(value = "start", required=false) Integer start,
			@RequestParam(value = "count", required=false) Integer count,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		ProductCriteria criteria = new ProductCriteria();
		if(!StringUtils.isBlank(lang)) {
			criteria.setLanguage(lang);
		}
		if(!StringUtils.isBlank(status)) {
			criteria.setStatus(status);
		}
		if(category != null) {
			List<Long> categoryIds = new ArrayList<Long>();
			categoryIds.add(category);
			criteria.setCategoryIds(categoryIds);
		}
		if(manufacturer != null) {
			criteria.setManufacturerId(manufacturer);
		}
		
		if(owner != null) {
			criteria.setOwnerId(owner);
		}
		
		if(start != null) {
			criteria.setStartIndex(start);
		}
		if(count != null) {
			criteria.setMaxCount(count);
		}
		
		//TODO
		//RENTAL add filter by owner
		//REPOSITORY to use the new filters
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(com.salesmanager.core.business.constants.Constants.DEFAULT_STORE);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);	
			
			
			ReadableProductList productList = productFacade.getProductListsByCriterias(merchantStore, language, criteria);
	
			return productList;
			
		} catch(Exception e) {
			
			LOGGER.error("Error while filtering products product",e);
			try {
				response.sendError(503, "Error while filtering products " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
			
		}
		

		
	}
	
	/**
	 * API for getting a product
	 * @param id
	 * @param lang
	 * 	?lang=fr|en
	 * @param request
	 * @param response
	 * @return ReadableProduct
	 * @throws Exception
	 * 
	 * 	/api/v1/product/123
	 */
	@RequestMapping(value = "/products/{id}", method=RequestMethod.GET)
	@ResponseBody
	public ReadableProduct get(@PathVariable final Long id, @RequestParam(value = "lang", required=false) String lang, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		MerchantStore merchantStore = storeFacade.getByCode(com.salesmanager.core.business.constants.Constants.DEFAULT_STORE);
		Language language = languageUtils.getRESTLanguage(request, merchantStore);	
		
		ReadableProduct product = productFacade.getProduct(merchantStore, id, language);
		
		if(product==null) {
			response.sendError(404, "Product not fount for id " + id);
			return null;
		}
		
		return product;
		
	}

	
    @ResponseStatus(HttpStatus.CREATED)
	@RequestMapping( value={"/private/products/{productId}/category/{categoryId}","/auth/products/{productId}/category/{categoryId}"}, method=RequestMethod.POST)
    public @ResponseBody ReadableProduct addProductToCategory(@PathVariable Long productId, @PathVariable Long categoryId, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		try {
    	
	    	MerchantStore merchantStore = storeFacade.getByCode(com.salesmanager.core.business.constants.Constants.DEFAULT_STORE);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);	
	    	
	    	//get the product
	    	Product product = productService.getById(productId);
	    	
	    	Category category = categoryService.getById(categoryId);
	    	
	    	ReadableProduct readableProduct = productFacade.addProductToCategory(category, product, language);
    	
	    	return readableProduct;
	    	
		} catch (Exception e) {
			LOGGER.error("Error while adding product to category",e);
			try {
				response.sendError(503, "Error while adding product to category " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
    }
    
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping( value={"/private/products/{productId}/category/{categoryId}","/auth/products/{productId}/category/{categoryId}"}, method=RequestMethod.DELETE)
    public @ResponseBody ReadableProduct removeProductFromCategory(@PathVariable Long productId, @PathVariable Long categoryId, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		try {
    	
	    	MerchantStore merchantStore = storeFacade.getByCode(com.salesmanager.core.business.constants.Constants.DEFAULT_STORE);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);	
	    	
	    	//get the product
	    	Product product = productService.getById(productId);
	    	
	    	Category category = categoryService.getById(categoryId);
	    	
	    	ReadableProduct readableProduct = productFacade.removeProductFromCategory(category, product, language);
    	
	    	return readableProduct;
	    	
		} catch (Exception e) {
			LOGGER.error("Error while removing product from category",e);
			try {
				response.sendError(503, "Error while removing product from category " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
    }

}
