package com.salesmanager.shop.store.api.v1.category;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.Produces;

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
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.store.controller.category.facade.CategoryFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.ImageFilePath;
import com.salesmanager.shop.utils.LanguageUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import javax.ws.rs.core.MediaType;


@Produces({MediaType.APPLICATION_JSON})
@Controller
@Api(value = "/api/v1/category")
@RequestMapping("/api/v1")
public class CategoryApi {
	
	@Inject
	private CategoryFacade categoryFacade;
	
	@Inject
	private CategoryService categoryService;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;
	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private LanguageUtils languageUtils;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryApi.class);
	
	
	@RequestMapping(value = "/category/{id}", method=RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Get category list for an given Category id", notes = "List current Category and child category")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "List of category found", response = ReadableCategory.class) })
	public @ResponseBody ReadableCategory get(@PathVariable final Long id, @RequestParam(value = "lang", required=false) String lang, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		try {
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);	
			
			ReadableCategory category  = categoryFacade.getById(merchantStore, id, language);
			
			if(category==null) {
				response.sendError(404,  "Category id not found");
				return null;
			}
	
	
			return category;
		
		} catch (Exception e) {
			LOGGER.error("Error while getting category",e);
			try {
				response.sendError(503, "Error while getting category " + e.getMessage());
			} catch (Exception ignore) {
			}
			return null;
		}
	}
	
	/**
	 * Get all category starting from root
	 * filter can be used for filtering on fields
	 * only featured is supported
	 * @param lang
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/category", method=RequestMethod.GET)
	public @ResponseBody List <ReadableCategory> getFiltered(
			@RequestParam(value = "filter", required=false) String filter,
			@RequestParam(value = "lang", required=false) 
			String lang, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		try {
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);	
			
			List <ReadableCategory> category  = categoryFacade.getCategoryHierarchy(merchantStore, 0, language, filter);


			return category;
		
		} catch (Exception e) {
			LOGGER.error("Error while getting root category",e);
			try {
				response.sendError(503, "Error while getting root category " + e.getMessage());
			} catch (Exception ignore) {
			}
			return null;
		}
	}
	
	/**
	 * Category creation
	 */
	@RequestMapping( value="/private/category", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody PersistableCategory createCategory(@Valid @RequestBody PersistableCategory category, HttpServletRequest request, HttpServletResponse response) {
		
		
		try {


			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);	
			categoryFacade.saveCategory(merchantStore, category);

			
			category.setId(category.getId());

			return category;
		
		} catch (Exception e) {
			LOGGER.error("Error while creating category",e);
			try {
				response.sendError(503, "Error while creating category " + e.getMessage());
			} catch (Exception ignore) {
			}
			return null;
		}
	}
	
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping( value="/private/category/{id}", method=RequestMethod.PUT)
    public @ResponseBody PersistableCategory update(@PathVariable Long id, @Valid @RequestBody PersistableCategory category, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			categoryFacade.saveCategory(merchantStore, category);

			return category;
			
		} catch (Exception e) {
			LOGGER.error("Error while updating category",e);
			try {
				response.sendError(503, "Error while updating category " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
		
	}
    
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping( value="/private/category/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			Category category = categoryService.getById(id);
			if(category != null){
				categoryFacade.deleteCategory(category);
			}else{
				response.sendError(404, "No Category found for ID : " + id);
			}
		} catch (Exception e) {
			LOGGER.error("Error while deleting category",e);
			try {
				response.sendError(503, "Error while deleting category " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
	}

}
