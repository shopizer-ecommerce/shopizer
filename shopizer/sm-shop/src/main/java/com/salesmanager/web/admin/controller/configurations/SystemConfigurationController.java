package com.salesmanager.web.admin.controller.configurations;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.system.model.MerchantConfig;
import com.salesmanager.core.business.system.service.MerchantConfigurationService;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;


@Controller
public class SystemConfigurationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SystemConfigurationController.class);
	
	@Autowired
	private MerchantConfigurationService merchantConfigurationService;

	@Autowired
	Environment env;
	

	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/configuration/system.html", method=RequestMethod.GET)
	public String displaySysyemConfgurations(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		MerchantConfig merchantConfiguration = merchantConfigurationService.getMerchantConfig(store);

		if(merchantConfiguration==null) {
			merchantConfiguration = new MerchantConfig();
		}
		
		model.addAttribute("store", store);
		model.addAttribute("configuration",merchantConfiguration);
		return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Configuration.system;
	}
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/configuration/saveSystemConfiguration.html", method=RequestMethod.POST)
	public String saveSystemConfigurations(@ModelAttribute("configuration") MerchantConfig merchantConfiguration, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception
	{
		setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		merchantConfigurationService.saveMerchantConfig(merchantConfiguration, store);
		model.addAttribute("success","success");
		model.addAttribute("store", store);
		model.addAttribute("configuration",merchantConfiguration);
		return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Configuration.system;
		
	}


	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("configuration", "configuration");
		activeMenus.put("system-configurations", "system-configurations");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("configuration");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
	}
}
