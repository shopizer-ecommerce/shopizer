package com.salesmanager.shop.admin.controller.tax;

import com.salesmanager.core.business.services.tax.TaxService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.tax.TaxConfiguration;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class TaxConfigurationController {
	
	@Inject
	private TaxService taxService = null;
	
	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value={"/admin/tax/taxconfiguration/edit.html"}, method=RequestMethod.GET)
	public String displayTaxConfiguration(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		
		TaxConfiguration taxConfiguration = taxService.getTaxConfiguration(store);
		if(taxConfiguration == null) {
			
			taxConfiguration = new TaxConfiguration();
			
		}
		
		model.addAttribute("taxConfiguration", taxConfiguration);
		
		return com.salesmanager.shop.admin.controller.ControllerConstants.Tiles.Tax.taxConfiguration;
	}
	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value="/admin/tax/taxconfiguration/save.html", method=RequestMethod.POST)
	public String saveTaxConfiguration(@Valid @ModelAttribute("taxConfiguration") TaxConfiguration taxConfiguration, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {
		
		
		setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		taxService.saveTaxConfiguration(taxConfiguration, store);
		
		model.addAttribute("success","success");
		
		return com.salesmanager.shop.admin.controller.ControllerConstants.Tiles.Tax.taxConfiguration;
		
	}
	
	
	private void setMenu(Model model, HttpServletRequest request)
	throws Exception {

		// display menu
		Map<String, String> activeMenus = new HashMap<String, String>();
		activeMenus.put("tax", "tax");
		activeMenus.put("taxconfiguration", "taxconfiguration");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>) request
				.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu) menus.get("tax");
		model.addAttribute("currentMenu", currentMenu);
		model.addAttribute("activeMenus", activeMenus);
		//

	}

}
