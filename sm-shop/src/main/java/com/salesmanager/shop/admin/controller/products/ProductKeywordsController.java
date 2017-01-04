package com.salesmanager.shop.admin.controller.products;

import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.utils.ajax.AjaxPageableResponse;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.admin.controller.ControllerConstants;
import com.salesmanager.shop.admin.model.catalog.Keyword;
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
import javax.validation.Valid;
import java.util.*;

@Controller
public class ProductKeywordsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductKeywordsController.class);
	
	@Inject
	private ProductService productService;
	
	@Inject
	LabelUtils messages;
	

	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value={"/admin/products/product/keywords.html"}, method=RequestMethod.GET)
	public String displayKeywords(@RequestParam("id") long productId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		Product product = productService.getById(productId);
		
		if(product==null || product.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
			return "redirect:/admin/products/products.html";
		}
		
		model.addAttribute("store", store);
		model.addAttribute("product", product);
		model.addAttribute("productKeyword", new Keyword());

		return ControllerConstants.Tiles.Product.productKeywords;
		
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/product/addKeyword.html", method=RequestMethod.POST)
	public String addKeyword(@Valid @ModelAttribute("productKeyword") Keyword keyword, final BindingResult bindingResult,final Model model, final HttpServletRequest request, Locale locale) throws Exception{
		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		
		Product product = productService.getById(keyword.getProductId());
		
		model.addAttribute("store", store);
		model.addAttribute("product", product);
		model.addAttribute("productKeyword", new Keyword());
		
		if(product==null || product.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
			return "redirect:/admin/products/products.html";
		}
		
		Set<ProductDescription> descriptions = product.getDescriptions();
		ProductDescription productDescription = null;
		for(ProductDescription description : descriptions) {
			
			if(description.getLanguage().getCode().equals(keyword.getLanguageCode())) {
				productDescription = description;
				break;
			}
			
		}
		
		if(productDescription==null) {
			FieldError error = new FieldError("keyword","keyword",messages.getMessage("message.product.language", locale));
			bindingResult.addError(error);
			return ControllerConstants.Tiles.Product.productKeywords;
		}
		
		
		String keywords = productDescription.getMetatagKeywords();
		List<String> keyWordsList = null;
		if(!StringUtils.isBlank(keywords)) {
			String[] splits = keywords.split(",");
			keyWordsList = new ArrayList(Arrays.asList(splits));
		}
		
		if(keyWordsList==null) {
			keyWordsList = new ArrayList<String>();
		}
		keyWordsList.add(keyword.getKeyword());
		
		StringBuilder kwString = new StringBuilder();
		for(String s : keyWordsList) {
			kwString.append(s).append(",");
		}
		
		productDescription.setMetatagKeywords(kwString.toString());
		Set<ProductDescription> updatedDescriptions = new HashSet<ProductDescription>();
		for(ProductDescription description : descriptions) {
			
			if(!description.getLanguage().getCode().equals(keyword.getLanguageCode())) {
				updatedDescriptions.add(description);
			}
		}
		
		updatedDescriptions.add(productDescription);
		product.setDescriptions(updatedDescriptions);
		
		productService.update(product);
		model.addAttribute("success","success");

		
        return ControllerConstants.Tiles.Product.productKeywords;
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/product/removeKeyword.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> removeKeyword(@RequestParam("id") long productId, HttpServletRequest request, HttpServletResponse response, Locale locale) {

		
		String code = request.getParameter("code");
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		AjaxResponse resp = new AjaxResponse();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		
		try {
			
			//parse code i,lang (0,en)
			String ids[] = code.split(",");
			
			String languageCode = ids[1];
			
			int index = Integer.parseInt(ids[0]);
			
			Product product = productService.getById(productId);

			
			if(product==null) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				resp.setErrorString("Product id is not valid");
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			if(product.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				resp.setErrorString("Product id is not valid");
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			Set<ProductDescription> descriptions = product.getDescriptions();
			Set<ProductDescription> editedDescriptions = new HashSet<ProductDescription>();
			for(ProductDescription description : descriptions) {

				Language lang = description.getLanguage();
				if(!lang.getCode().equals(languageCode)){
					editedDescriptions.add(description);
					continue;
				}

				List<String> keyWordsList = new ArrayList<String>();
	
				
				String keywords = description.getMetatagKeywords();
				if(!StringUtils.isBlank(keywords)) {
					String splitKeywords[] = keywords.split(",");
					for(int i = 0; i < splitKeywords.length; i++) {
						
						if(i!=index) {
							keyWordsList.add(splitKeywords[i]);
						}
						
						
					}
				}
				

				
				
				StringBuilder kwString = new StringBuilder();
				for(String s : keyWordsList) {
					kwString.append(s).append(",");
				}
				
				description.setMetatagKeywords(kwString.toString());
				editedDescriptions.add(description);
				
			}
			
			product.setDescriptions(editedDescriptions);
			productService.update(product);
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting product", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	
	
	@SuppressWarnings("unchecked")
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/product/keywords/paging.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> pageKeywords(HttpServletRequest request, HttpServletResponse response) {
		
		String sProductId = request.getParameter("id");
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		AjaxResponse resp = new AjaxResponse();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		Long productId;
		Product product = null;
		
		try {
			productId = Long.parseLong(sProductId);
		} catch (Exception e) {
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorString("Product id is not valid");
			String returnString = resp.toJSONString();
			return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
		}

		
		try {

			product = productService.getById(productId);

			
			if(product==null) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				resp.setErrorString("Product id is not valid");
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			if(product.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				resp.setErrorString("Product id is not valid");
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			
			@SuppressWarnings("rawtypes")
			

			Set<ProductDescription> descriptions = product.getDescriptions();

			for(ProductDescription description : descriptions) {
				
				
				Language lang = description.getLanguage();
				
				
				String keywords = description.getMetatagKeywords();
				if(!StringUtils.isBlank(keywords)) {
					
					String splitKeywords[] = keywords.split(",");
					for(int i = 0; i < splitKeywords.length; i++) {
						Map entry = new HashMap();
						entry.put("language", lang.getCode());
						String keyword = splitKeywords[i];
						StringBuilder code = new StringBuilder();
						code.append(i).append(",").append(lang.getCode());
						
						entry.put("code", code.toString());
						entry.put("keyword", keyword);
						resp.addDataEntry(entry);
	
						
					}
					
				}
				
			}

			

			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_SUCCESS);
		
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
		activeMenus.put("catalogue-products", "catalogue-products");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("catalogue");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}

}
