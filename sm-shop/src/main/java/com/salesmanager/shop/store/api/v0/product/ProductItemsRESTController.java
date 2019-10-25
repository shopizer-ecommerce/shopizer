package com.salesmanager.shop.store.api.v0.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionValueService;
import com.salesmanager.core.business.services.catalog.product.manufacturer.ManufacturerService;
import com.salesmanager.core.business.services.catalog.product.relationship.ProductRelationshipService;
import com.salesmanager.core.business.services.catalog.product.review.ProductReviewService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.tax.TaxClassService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.catalog.product.ReadableProductList;
import com.salesmanager.shop.store.controller.items.facade.ProductItemsFacade;
import com.salesmanager.shop.store.controller.product.facade.ProductFacade;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.ImageFilePath;
import com.salesmanager.shop.utils.LanguageUtils;

/**
 * API to create, read, updat and delete a Product
 * API to create Manufacturer
 * @author Carl Samson
 *
 */
@Controller
@RequestMapping("/services")
public class ProductItemsRESTController {
	
	@Inject
	private MerchantStoreService merchantStoreService;

	
	@Inject
	private ProductItemsFacade productItemsFacade;

	
	@Inject
	private LanguageService languageService;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;
	
	@Inject
	private LanguageUtils languageUtils;
	
	@Inject
	private ProductRelationshipService productRelationshipService;
	

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductItemsRESTController.class);
	
	
	

	/**
	 * Items for manufacturer
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
	/** fixed filter **/
	@RequestMapping("/public/products/page/{start}/{max}/{store}/{language}/manufacturer/{id}")
	@ResponseBody
	public ReadableProductList getProductItemsByManufacturer(@PathVariable int start, @PathVariable int max, @PathVariable String store, @PathVariable final String language, @PathVariable final Long id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
			
	
	
			Language lang = langs.get(language);
			if(lang==null) {
				lang = langs.get(Constants.DEFAULT_LANGUAGE);
			}
			

			
			ReadableProductList list = productItemsFacade.listItemsByManufacturer(merchantStore, lang, id, start, max);
			
			
			return list;
		
		} catch (Exception e) {
			LOGGER.error("Error while getting products",e);
			response.sendError(503, "An error occured while retrieving products " + e.getMessage());
		}
		
		return null;
		
	}
	
	/**
	 * Query for a product group
	 * public/products/{store code}/products/group/{id}?lang=fr|en
	 * no lang it will take session lang or default store lang
	 * @param store
	 * @param language
	 * @param groupCode
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/public/{store}/products/group/{code}")
	@ResponseBody
	public ReadableProductList getProductItemsByGroup(@PathVariable String store, @PathVariable final String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {

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
			
	
	
			Language lang = languageUtils.getRESTLanguage(request, merchantStore);
			
			//get product group
			List<ProductRelationship> group = productRelationshipService.getByGroup(merchantStore, code, lang);

			if(group!=null) {
				
				Date today = new Date();
				List<Long> ids = new ArrayList<Long>();
				for(ProductRelationship relationship : group) {
					Product product = relationship.getRelatedProduct();
					if(product.isAvailable() && DateUtil.dateBeforeEqualsDate(product.getDateAvailable(), today)) {
						ids.add(product.getId());
					}
				}
				
				ReadableProductList list = productItemsFacade.listItemsByIds(merchantStore, lang, ids, 0, 0);
				return list;
			}
			
			
		
		} catch (Exception e) {
			LOGGER.error("Error while getting products",e);
			response.sendError(503, "An error occured while retrieving products " + e.getMessage());
		}
		
		return null;
		
	}

	



}
