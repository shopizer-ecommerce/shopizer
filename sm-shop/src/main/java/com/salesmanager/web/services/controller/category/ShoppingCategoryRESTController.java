package com.salesmanager.web.services.controller.category;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.merchant.service.MerchantStoreService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.entity.catalog.category.PersistableCategory;
import com.salesmanager.web.entity.catalog.category.ReadableCategory;
import com.salesmanager.web.populator.catalog.ReadableCategoryPopulator;
import com.salesmanager.web.shop.controller.category.facade.CategoryFacade;

/**
 * Rest services for category management
 * @author Carl Samson
 *
 */
@Controller
@RequestMapping("/services")
public class ShoppingCategoryRESTController {
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private MerchantStoreService merchantStoreService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryFacade categoryFacade;
	

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCategoryRESTController.class);
	

	
	@RequestMapping( value="/public/{store}/category/{id}", method=RequestMethod.GET)
	@ResponseBody
	public ReadableCategory getCategory(@PathVariable final String store, @PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			
			/** default routine **/
			
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
			
			Language language = merchantStore.getDefaultLanguage();
			
			Map<String,Language> langs = languageService.getLanguagesMap();

			
			if(!StringUtils.isBlank(request.getParameter(Constants.LANG))) {
				String lang = request.getParameter(Constants.LANG);
				if(lang!=null) {
					language = langs.get(language);
				}
			}
			
			if(language==null) {
				language = merchantStore.getDefaultLanguage();
			}
			
			
			/** end default routine **/

			
			Category dbCategory = categoryService.getByLanguage(id, language);
			
			if(dbCategory==null) {
				response.sendError(503,  "Invalid category id");
				return null;
			}
			
			if(dbCategory.getMerchantStore().getId().intValue()!=merchantStore.getId().intValue()){
				response.sendError(503, "Invalid category id");
				return null;
			}
			

			ReadableCategoryPopulator populator = new ReadableCategoryPopulator();

			//TODO count products by category
			ReadableCategory category = populator.populate(dbCategory, new ReadableCategory(), merchantStore, merchantStore.getDefaultLanguage());

			return category;
		
		} catch (Exception e) {
			LOGGER.error("Error while saving category",e);
			try {
				response.sendError(503, "Error while saving category " + e.getMessage());
			} catch (Exception ignore) {
			}
			return null;
		}
	}
	

	
	
	/**
	 * Create new category for a given MerchantStore
	 */
	@RequestMapping( value="/private/{store}/category", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public PersistableCategory createCategory(@PathVariable final String store, @Valid @RequestBody PersistableCategory category, HttpServletRequest request, HttpServletResponse response) {
		
		
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
			
			categoryFacade.saveCategory(merchantStore, category);

			
			category.setId(category.getId());

			return category;
		
		} catch (Exception e) {
			LOGGER.error("Error while saving category",e);
			try {
				response.sendError(503, "Error while saving category " + e.getMessage());
			} catch (Exception ignore) {
			}
			return null;
		}
	}
	

	
	/**
	 * Deletes a category for a given MerchantStore
	 */
	@RequestMapping( value="/private/{store}/category/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCategory(@PathVariable final String store, @PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Category category = categoryService.getById(id);
		if(category != null && category.getMerchantStore().getCode().equalsIgnoreCase(store)){
			categoryService.delete(category);
		}else{
			response.sendError(404, "No Category found for ID : " + id);
		}
	}

	
	

	
}
