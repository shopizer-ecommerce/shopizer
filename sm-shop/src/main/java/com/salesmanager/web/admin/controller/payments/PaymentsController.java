package com.salesmanager.web.admin.controller.payments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.payments.model.TransactionType;
import com.salesmanager.core.business.payments.service.PaymentService;
import com.salesmanager.core.business.system.model.IntegrationConfiguration;
import com.salesmanager.core.business.system.model.IntegrationModule;
import com.salesmanager.core.modules.integration.IntegrationException;
import com.salesmanager.web.admin.controller.ControllerConstants;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.LabelUtils;

@Controller
public class PaymentsController {
	
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	LabelUtils messages;

	
	@RequestMapping(value="/admin/payments/paymentMethods.html", method=RequestMethod.GET)
	public String getPaymentMethods(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		//set menu
		setMenu(model,request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		//get payment methods
		List<IntegrationModule> modules = paymentService.getPaymentMethods(store);

		//get configured payment modules
		Map<String,IntegrationConfiguration> configuredModules = paymentService.getPaymentModulesConfigured(store);
		


		model.addAttribute("modules", modules);
		model.addAttribute("configuredModules", configuredModules);
		
		return ControllerConstants.Tiles.Payment.paymentMethods;

	}
	
	@PreAuthorize("hasRole('PAYMENT')")
	@RequestMapping(value="/admin/payments/paymentMethod.html", method=RequestMethod.GET)
	public String displayPaymentMethod(@RequestParam("code") String code, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {


		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		

		//get configured shipping modules
		IntegrationConfiguration configuration = paymentService.getPaymentConfiguration(code, store);
		if(configuration==null) {
			configuration = new IntegrationConfiguration();
			configuration.setEnvironment(com.salesmanager.core.constants.Constants.PRODUCTION_ENVIRONMENT);
			
			Map<String,String> keys = new HashMap<String,String>();
			keys.put("transaction", TransactionType.AUTHORIZECAPTURE.name());
			
			configuration.setIntegrationKeys(keys);
			
		}
		
		configuration.setModuleCode(code);
		
		List<String> environments = new ArrayList<String>();
		environments.add(com.salesmanager.core.constants.Constants.TEST_ENVIRONMENT);
		environments.add(com.salesmanager.core.constants.Constants.PRODUCTION_ENVIRONMENT);
		
		model.addAttribute("configuration", configuration);
		model.addAttribute("environments", environments);
		return ControllerConstants.Tiles.Payment.paymentMethod;
		
		
	}
	
	@PreAuthorize("hasRole('PAYMENT')")
	@RequestMapping(value="/admin/payments/savePaymentMethod.html", method=RequestMethod.POST)
	public String savePaymentMethod(@ModelAttribute("configuration") IntegrationConfiguration configuration, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {


		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		

		
		List<String> environments = new ArrayList<String>();
		environments.add(com.salesmanager.core.constants.Constants.TEST_ENVIRONMENT);
		environments.add(com.salesmanager.core.constants.Constants.PRODUCTION_ENVIRONMENT);

		model.addAttribute("environments", environments);
		model.addAttribute("configuration", configuration);

		try {
			paymentService.savePaymentModuleConfiguration(configuration, store);
		} catch (Exception e) {
			if(e instanceof IntegrationException) {
				if(((IntegrationException)e).getErrorCode()==IntegrationException.ERROR_VALIDATION_SAVE) {
					
					List<String> errorCodes = ((IntegrationException)e).getErrorFields();
					for(String errorCode : errorCodes) {
						model.addAttribute(errorCode,messages.getMessage("message.fielderror", locale));
					}
					model.addAttribute("validationError","validationError");
					return ControllerConstants.Tiles.Payment.paymentMethod;
				}
			} else {
				throw new Exception(e);
			}
		}
		
		
		
		model.addAttribute("success","success");
		return ControllerConstants.Tiles.Payment.paymentMethod;
		
		
	}
	
	@RequestMapping(value="/admin/payments/deletePaymentMethod.html", method=RequestMethod.POST)
	public String deletePaymentMethod(@RequestParam("code") String code, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		paymentService.removePaymentModuleConfiguration(code, store);
		
		return "redirect:/admin/payments/paymentMethods.html";
		
	}
	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("payment", "payment");
		activeMenus.put("payment-methods", "payment-methods");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("payment");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}
	
	
}
