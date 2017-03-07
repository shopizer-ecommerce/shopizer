package com.salesmanager.shop.admin.controller.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.relationship.ProductRelationshipService;
import com.salesmanager.core.business.utils.ajax.AjaxPageableResponse;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.admin.controller.ControllerConstants;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.utils.LabelUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Controller
public class CustomProductGroupsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomProductGroupsController.class);
	
	@Inject
	CategoryService categoryService;
	
	@Inject
	ProductService productService;
	
	@Inject
	ProductRelationshipService productRelationshipService;
	
	@Inject
	LabelUtils messages;
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/groups/list.html", method=RequestMethod.GET)
	public String displayProductGroups(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		setMenu(model,request);
		
		Language language = (Language)request.getAttribute("LANGUAGE");
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		ProductRelationship group = new ProductRelationship();
		
		
		model.addAttribute("group", group);

		return ControllerConstants.Tiles.Product.customGroups;
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/groups/paging.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> pageCustomGroups(HttpServletRequest request, HttpServletResponse response) {
		
		
		AjaxResponse resp = new AjaxResponse();
		
		try {

			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			

			List<ProductRelationship> relationships = productRelationshipService.getGroups(store);
			
			for(ProductRelationship relationship : relationships) {
				
				if(!"FEATURED_ITEM".equals(relationship.getCode())) {//do not add featured items

					Map entry = new HashMap();
					entry.put("code", relationship.getCode());
					entry.put("active", relationship.isActive());
	
					resp.addDataEntry(entry);
				
				}
				
			}
			

			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_SUCCESS);
		
		} catch (Exception e) {
			LOGGER.error("Error while paging products", e);
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);


	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/groups/save.html", method=RequestMethod.POST)
	public String saveCustomProductGroup(@ModelAttribute("group") ProductRelationship group, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		setMenu(model,request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		
		//check if group already exist
		
		
		if(StringUtils.isBlank(group.getCode())) {
			FieldError fieldError = new FieldError("group","code",group.getCode(),false,null,null,messages.getMessage("message.group.required",locale));
			result.addError(fieldError);
			return ControllerConstants.Tiles.Product.customGroups;
		}
		
		//String msg = messages.getMessage("message.group.alerady.exists",locale);
		//String[] messages = {msg};
		
		String[] messages = {"message.group.alerady.exists"};
		
		List<ProductRelationship> groups = productRelationshipService.getGroups(store);
		for(ProductRelationship grp : groups) {
			if(grp.getCode().equalsIgnoreCase(group.getCode())) {
				String[] args = {group.getCode()};
				FieldError fieldError = new FieldError("group","code",group.getCode(),false,messages,args,null);
				result.addError(fieldError);
			}
		}
		
		if(result.hasErrors()) {
			return ControllerConstants.Tiles.Product.customGroups;
		}

		group.setActive(true);
		group.setStore(store);
		
		productRelationshipService.addGroup(store,group.getCode());

		
		model.addAttribute("success","success");
		
		return ControllerConstants.Tiles.Product.customGroups;
		
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/groups/remove.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> removeCustomProductGroup(HttpServletRequest request, HttpServletResponse response) {
		
		String groupCode = request.getParameter("code");

		AjaxResponse resp = new AjaxResponse();


		try {
			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			productRelationshipService.deleteGroup(store, groupCode);
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		} catch (Exception e) {
			LOGGER.error("Error while deleting a group", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}

		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);

	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/groups/update.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> activateProductGroup(HttpServletRequest request, HttpServletResponse response) {
		String values = request.getParameter("_oldValues");
		String active = request.getParameter("active");
		

		AjaxResponse resp = new AjaxResponse();

		try {
			
			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("rawtypes")
			Map conf = mapper.readValue(values, Map.class);
			String groupCode = (String)conf.get("code");

			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			//get groups
			List<ProductRelationship> groups = productRelationshipService.getGroups(store);
			
			for(ProductRelationship relation : groups) {
				if(relation.getCode().equals(groupCode)) {
					if("true".equals(active)) {
						relation.setActive(true);
					} else {
						relation.setActive(false);
					}
					productRelationshipService.saveOrUpdate(relation);
				}
			}
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		} catch (Exception e) {
			LOGGER.error("Error while updateing groups", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/group/edit.html", method=RequestMethod.GET)
	public String displayCustomProductGroup(@RequestParam("id") String groupCode, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		setMenu(model,request);
		
		Language language = (Language)request.getAttribute("LANGUAGE");
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		List<Category> categories = categoryService.listByStore(store,language);//for categories
		
		
		model.addAttribute("group", groupCode);
		model.addAttribute("categories", categories);
		return ControllerConstants.Tiles.Product.customGroupsDetails;
		
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/group/details/paging.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> pageProducts(HttpServletRequest request, HttpServletResponse response) {
		
		String code = request.getParameter("code");
		AjaxResponse resp = new AjaxResponse();
		
		try {
			

			
			Language language = (Language)request.getAttribute("LANGUAGE");
			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			

			List<ProductRelationship> relationships = productRelationshipService.getByGroup(store, code, language);
			
			for(ProductRelationship relationship : relationships) {
				
				Product product = relationship.getRelatedProduct();
				Map entry = new HashMap();
				entry.put("relationshipId", relationship.getId());
				entry.put("productId", product.getId());
				
				ProductDescription description = product.getDescriptions().iterator().next();
				Set<ProductDescription> descriptions = product.getDescriptions();
				for(ProductDescription desc : descriptions) {
					if(desc.getLanguage().getId().intValue()==language.getId().intValue()) {
						description = desc;
					}
				}
				
				entry.put("name", description.getName());
				entry.put("sku", product.getSku());
				entry.put("available", product.isAvailable());
				resp.addDataEntry(entry);
				
			}
			

			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_SUCCESS);
		
		} catch (Exception e) {
			LOGGER.error("Error while paging products", e);
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);


	}
	
	
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/group/details/addItem.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addItem(HttpServletRequest request, HttpServletResponse response) {
		
		String code = request.getParameter("code");
		String productId = request.getParameter("productId");
		AjaxResponse resp = new AjaxResponse();
		
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		try {
			

			Long lProductId = Long.parseLong(productId);

			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			Product product = productService.getById(lProductId);
			
			if(product==null) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			if(product.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}


			ProductRelationship relationship = new ProductRelationship();
			relationship.setActive(true);
			relationship.setCode(code);
			relationship.setStore(store);
			relationship.setRelatedProduct(product);
			
			productRelationshipService.saveOrUpdate(relationship);
			

			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_SUCCESS);
		
		} catch (Exception e) {
			LOGGER.error("Error while paging products", e);
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/group/details/removeItem.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> removeItem(HttpServletRequest request, HttpServletResponse response) {
		
		String code = request.getParameter("code");
		String productId = request.getParameter("productId");
		AjaxResponse resp = new AjaxResponse();
		
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		try {
			

			Long lproductId = Long.parseLong(productId);

			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			Product product = productService.getById(lproductId);
			
			if(product==null) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			if(product.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			
			ProductRelationship relationship = null;
			List<ProductRelationship> relationships = productRelationshipService.getByGroup(store, code);
			
			for(ProductRelationship r : relationships) {
				if(r.getRelatedProduct().getId().longValue()==lproductId.longValue()) {
					relationship = r;
					break;
				}
			}
			
			if(relationship==null) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			if(relationship.getStore().getId().intValue()!=store.getId().intValue()) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}


			
			
			productRelationshipService.delete(relationship);
			

			resp.setStatus(AjaxPageableResponse.RESPONSE_OPERATION_COMPLETED);
		
		} catch (Exception e) {
			LOGGER.error("Error while paging products", e);
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
		
	}

	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("catalogue", "catalogue");
		activeMenus.put("catalogue-products-group", "catalogue-products-group");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("catalogue");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}

}
