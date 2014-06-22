package com.salesmanager.web.admin.controller.tax;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.tax.model.taxclass.TaxClass;
import com.salesmanager.core.business.tax.service.TaxClassService;
import com.salesmanager.core.utils.ajax.AjaxPageableResponse;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.LabelUtils;

@Controller
public class TaxClassController {
	
	@Autowired
	private TaxClassService taxClassService = null;
	
	@Autowired
	private ProductService productService=null;
	
	@Autowired
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
		
		return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Tax.taxClasses;
	}
	
	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value = "/admin/tax/taxclass/paging.html", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	String pageTaxClasses(HttpServletRequest request,
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
		return returnString;
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
			return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Tax.taxClasses;
		}
		
		taxClassService.create(taxClass);
		
		model.addAttribute("success","success");
		
		return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Tax.taxClasses;
		
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
			return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Tax.taxClass;
		}
		
		taxClassService.update(taxClass);
		
		model.addAttribute("success","success");
		
		return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Tax.taxClass;
		
	}
	
	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value="/admin/tax/taxclass/remove.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String removeTaxClass(HttpServletRequest request, Locale locale) throws Exception {
		
		//do not remove super admin
		
		String taxClassId = request.getParameter("taxClassId");

		AjaxResponse resp = new AjaxResponse();
		

		try {
			

			/**
			 * In order to remove a User the logged in ser must be STORE_ADMIN
			 * or SUPER_USER
			 */
			

			if(taxClassId==null){
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				return resp.toJSONString();
			}
			
			long lTaxClassId;
			try {
				lTaxClassId = Long.parseLong(taxClassId);
			} catch (Exception e) {
				LOGGER.error("Invalid taxClassId " + taxClassId);
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				return resp.toJSONString();
			}
			
			TaxClass taxClass = taxClassService.getById(lTaxClassId);
			
			if(taxClass==null) {
				LOGGER.error("Invalid taxClassId " + taxClassId);
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				return resp.toJSONString();
			}
			
			//look if the taxclass is used for products
			List<Product> products = productService.listByTaxClass(taxClass);

			if(products!=null && products.size()>0) {
				resp.setStatusMessage(messages.getMessage("message.product.association", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				return resp.toJSONString();
			}
			
			
			taxClassService.delete(taxClass);
			
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting tax class", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
		
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
		
		return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Tax.taxClass;
		
		
		
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
