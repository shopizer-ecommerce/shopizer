package com.salesmanager.shop.admin.controller.tax;

import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.tax.TaxClassService;
import com.salesmanager.core.business.utils.ajax.AjaxPageableResponse;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.tax.taxclass.TaxClass;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.utils.LabelUtils;
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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class TaxClassController {
	
	@Inject
	private TaxClassService taxClassService = null;
	
	@Inject
	private ProductService productService=null;
	
	@Inject
	LabelUtils messages;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaxClassController.class);

	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value={"/admin/tax/taxclass/list.html"}, method=RequestMethod.GET)
	public String displayTaxClasses(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		TaxClass taxClass = new TaxClass();
		taxClass.setMerchantStore(store);
		
		model.addAttribute("taxClass", taxClass);
		
		return com.salesmanager.shop.admin.controller.ControllerConstants.Tiles.Tax.taxClasses;
	}
	
	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value = "/admin/tax/taxclass/paging.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<String> pageTaxClasses(HttpServletRequest request,
			HttpServletResponse response, Locale locale) {
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		AjaxResponse resp = new AjaxResponse();
		try {

				List<TaxClass> taxClasses = taxClassService.listByStore(store);
				for(TaxClass tax : taxClasses) {
					if(!tax.getCode().equals(TaxClass.DEFAULT_TAX_CLASS)) {
						Map<String,String> entry = new HashMap<String,String>();
						entry.put("taxClassId", String.valueOf(tax.getId()));
						entry.put("code", tax.getCode());
						entry.put("name", tax.getTitle());
						resp.addDataEntry(entry);
					}
				}

				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_SUCCESS);
		
		} catch (Exception e) {
			LOGGER.error("Error while paging permissions", e);
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value="/admin/tax/taxclass/save.html", method=RequestMethod.POST)
	public String saveTaxClass(@Valid @ModelAttribute("taxClass") TaxClass taxClass, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {
		
		
		setMenu(model, request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		
		//requires code and name
		if(taxClass.getCode().equals(TaxClass.DEFAULT_TAX_CLASS)) {
			ObjectError error = new ObjectError("code",messages.getMessage("message.taxclass.alreadyexist", locale));
			result.addError(error);
		}
		

		
		//check if the code already exist
		TaxClass taxClassDb = taxClassService.getByCode(taxClass.getCode(),store);
		
		if(taxClassDb!=null) {
			ObjectError error = new ObjectError("code",messages.getMessage("message.taxclass.alreadyexist", locale));
			result.addError(error);
		}
		
		if (result.hasErrors()) {
			return com.salesmanager.shop.admin.controller.ControllerConstants.Tiles.Tax.taxClasses;
		}
		
		taxClassService.create(taxClass);
		
		model.addAttribute("success","success");
		
		return com.salesmanager.shop.admin.controller.ControllerConstants.Tiles.Tax.taxClasses;
		
	}
	
	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value="/admin/tax/taxclass/update.html", method=RequestMethod.POST)
	public String updateTaxClass(@Valid @ModelAttribute("taxClass") TaxClass taxClass, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {
		
		
		setMenu(model, request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		
		//requires code and name
		if(taxClass.getCode().equals(TaxClass.DEFAULT_TAX_CLASS)) {
			ObjectError error = new ObjectError("code",messages.getMessage("message.taxclass.alreadyexist", locale));
			result.addError(error);
		}
		

		
		//check if the code already exist
		TaxClass taxClassDb = taxClassService.getByCode(taxClass.getCode(),store);
		
		if(taxClassDb!=null && taxClassDb.getId().longValue()!=taxClass.getId().longValue()) {
			ObjectError error = new ObjectError("code",messages.getMessage("message.taxclass.alreadyexist", locale));
			result.addError(error);
		}
		
		if (result.hasErrors()) {
			return com.salesmanager.shop.admin.controller.ControllerConstants.Tiles.Tax.taxClass;
		}
		
		taxClassService.update(taxClass);
		
		model.addAttribute("success","success");
		
		return com.salesmanager.shop.admin.controller.ControllerConstants.Tiles.Tax.taxClass;
		
	}
	
	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value="/admin/tax/taxclass/remove.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> removeTaxClass(HttpServletRequest request, Locale locale) throws Exception {
		
		//do not remove super admin
		
		String taxClassId = request.getParameter("taxClassId");

		AjaxResponse resp = new AjaxResponse();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		try {
			

			/**
			 * In order to remove a User the logged in ser must be STORE_ADMIN
			 * or SUPER_USER
			 */
			

			if(taxClassId==null){
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			long lTaxClassId;
			try {
				lTaxClassId = Long.parseLong(taxClassId);
			} catch (Exception e) {
				LOGGER.error("Invalid taxClassId " + taxClassId);
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			TaxClass taxClass = taxClassService.getById(lTaxClassId);
			
			if(taxClass==null) {
				LOGGER.error("Invalid taxClassId " + taxClassId);
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			//look if the taxclass is used for products
			List<Product> products = productService.listByTaxClass(taxClass);

			if(products!=null && products.size()>0) {
				resp.setStatusMessage(messages.getMessage("message.product.association", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			
			taxClassService.delete(taxClass);
			
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting tax class", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value="/admin/tax/taxclass/edit.html", method=RequestMethod.GET)
	public String editTaxClass(@ModelAttribute("id") String id, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		setMenu(model,request);

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		TaxClass taxClass = null;
		try {
			Long taxClassId = Long.parseLong(id);
			taxClass = taxClassService.getById(taxClassId);
		} catch (Exception e) {
			LOGGER.error("Cannot parse taxclassid " + id);
			return "redirect:/admin/tax/taxclass/list.html";
		}
		
		if(taxClass==null || taxClass.getMerchantStore().getId()!=store.getId()) {
			return "redirect:/admin/tax/taxclass/list.html";
		}
		
		
		
		
		model.addAttribute("taxClass", taxClass);
		
		return com.salesmanager.shop.admin.controller.ControllerConstants.Tiles.Tax.taxClass;
		
		
		
	}


	
	private void setMenu(Model model, HttpServletRequest request)
	throws Exception {

		// display menu
		Map<String, String> activeMenus = new HashMap<String, String>();
		activeMenus.put("tax", "tax");
		activeMenus.put("taxclass", "taxclass");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>) request
				.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu) menus.get("tax");
		model.addAttribute("currentMenu", currentMenu);
		model.addAttribute("activeMenus", activeMenus);
		//

	}

}
