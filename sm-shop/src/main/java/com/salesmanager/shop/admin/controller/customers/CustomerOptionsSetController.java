package com.salesmanager.shop.admin.controller.customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.core.business.services.customer.attribute.CustomerOptionService;
import com.salesmanager.core.business.services.customer.attribute.CustomerOptionSetService;
import com.salesmanager.core.business.services.customer.attribute.CustomerOptionValueService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.customer.attribute.*;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.admin.controller.ControllerConstants;
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
public class CustomerOptionsSetController {
	
	@Inject
	private LanguageService languageService;
	
	@Inject
	private CustomerOptionSetService customerOptionSetService;
	
	@Inject
	private CustomerOptionService customerOptionService;
	
	@Inject
	private CustomerOptionValueService customerOptionValueService;
	
	@Inject
	private LabelUtils messages;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerOptionsSetController.class);
	
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/optionsset/list.html", method=RequestMethod.GET)
	public String displayOptions(Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		Language language = languageService.toLanguage(locale);
		
		
		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		
		//get options 
		List<CustomerOption> options = customerOptionService.listByStore(store, language);
		
		
		//get values
		List<CustomerOptionValue> optionsValues = customerOptionValueService.listByStore(store, language);

		
		CustomerOptionSet optionSet = new CustomerOptionSet();
		
		model.addAttribute("optionSet", optionSet);
		model.addAttribute("options", options);
		model.addAttribute("optionsValues", optionsValues);
		return ControllerConstants.Tiles.Customer.optionsSet;
		

	}
	
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/optionsset/save.html", method=RequestMethod.POST)
	public String saveOptionSet(@Valid @ModelAttribute("optionSet") CustomerOptionSet optionSet, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {
		

		//display menu
		setMenu(model,request);
		
		Language language = languageService.toLanguage(locale);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		
		/** reference objects **/
		
		//get options 
		List<CustomerOption> options = customerOptionService.listByStore(store, language);
		
		
		//get values
		List<CustomerOptionValue> optionsValues = customerOptionValueService.listByStore(store, language);


		model.addAttribute("options", options);
		model.addAttribute("optionsValues", optionsValues);

		if(optionSet.getCustomerOption()==null || optionSet.getCustomerOptionValue()==null) {
			model.addAttribute("errorMessageAssociation",messages.getMessage("message.optionset.noassociation", locale));
			ObjectError error = new ObjectError("customerOptionValue.id",messages.getMessage("message.optionset.noassociation", locale));
			result.addError(error);
			return ControllerConstants.Tiles.Customer.optionsSet;
		}
		
		//see if association already exist
		CustomerOption option =	null;	

		//get from DB
		//option = customerOptionService.getById(optionSet.getPk().getCustomerOption().getId());
		option = customerOptionService.getById(optionSet.getCustomerOption().getId());
			
		if(option==null) {
				return "redirect:/admin/customers/optionsset/list.html";
		}

		//CustomerOptionValue optionValue = customerOptionValueService.getById(optionSet.getPk().getCustomerOptionValue().getId());
		CustomerOptionValue optionValue = customerOptionValueService.getById(optionSet.getCustomerOptionValue().getId());
			
		if(optionValue==null) {
			return "redirect:/admin/customers/optionsset/list.html";
		}
		
		
		List<CustomerOptionSet> optionsSet = customerOptionSetService.listByStore(store, language);
		
		if(optionsSet!=null && optionsSet.size()>0) {
			
			for(CustomerOptionSet optSet : optionsSet) {
				
				//CustomerOption opt = optSet.getPk().getCustomerOption();
				CustomerOption opt = optSet.getCustomerOption();
				//CustomerOptionValue optValue = optSet.getPk().getCustomerOptionValue();
				CustomerOptionValue optValue = optSet.getCustomerOptionValue();
				
				//if(opt.getId().longValue()==optionSet.getPk().getCustomerOption().getId().longValue() 
				if(opt.getId().longValue()==optionSet.getCustomerOption().getId().longValue()
						//&& optValue.getId().longValue() == optionSet.getPk().getCustomerOptionValue().getId().longValue()) {
						&& optValue.getId().longValue() == optionSet.getCustomerOptionValue().getId().longValue()) {
						model.addAttribute("errorMessageAssociation",messages.getMessage("message.optionset.optionassociationexists", locale));
						ObjectError error = new ObjectError("customerOptionValue.id",messages.getMessage("message.optionset.optionassociationexists", locale));
						result.addError(error);
						break;
				}
			}
		}
		
		if (result.hasErrors()) {
			return ControllerConstants.Tiles.Customer.optionsSet;
		}
		
		
		//optionSet.getPk().setCustomerOption(option);
		optionSet.setCustomerOption(option);
		//optionSet.getPk().setCustomerOptionValue(optionValue);
		optionSet.setCustomerOptionValue(optionValue);
		customerOptionSetService.create(optionSet);

		


		model.addAttribute("success","success");
		return ControllerConstants.Tiles.Customer.optionsSet;
	}

	
	
	@SuppressWarnings("unchecked")
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/optionsset/paging.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String>  pageOptionsSet(HttpServletRequest request, HttpServletResponse response) {

		AjaxResponse resp = new AjaxResponse();

		
		try {
			
			
			Language language = (Language)request.getAttribute("LANGUAGE");	
			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			//List<CustomerOption> options = null;
				
			List<CustomerOptionSet> optionSet = customerOptionSetService.listByStore(store, language);
			//for(CustomerOption option : options) {
				
				
				//Set<CustomerOptionSet> optionSet = option.getCustomerOptions();
				
				if(optionSet!=null && optionSet.size()>0) {
					
					for(CustomerOptionSet optSet : optionSet) {
						
						//CustomerOption customerOption = optSet.getPk().getCustomerOption();
						CustomerOption customerOption = optSet.getCustomerOption();
						//CustomerOptionValue customerOptionValue = optSet.getPk().getCustomerOptionValue();
						CustomerOptionValue customerOptionValue = optSet.getCustomerOptionValue();
						
						@SuppressWarnings("rawtypes")
						Map entry = new HashMap();
						
						
						entry.put("id", optSet.getId());

						CustomerOptionDescription description = customerOption.getDescriptionsSettoList().get(0);
						CustomerOptionValueDescription valueDescription = customerOptionValue.getDescriptionsSettoList().get(0);
						
						entry.put("optionCode", customerOption.getCode());
						entry.put("optionName", description.getName());
						entry.put("optionValueCode", customerOptionValue.getCode());
						entry.put("optionValueName", valueDescription.getName());
						entry.put("order", customerOptionValue.getSortOrder());
						resp.addDataEntry(entry);
					
					}
				
				}
				
				
			//}
			
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
			

		
		} catch (Exception e) {
			LOGGER.error("Error while paging options", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
		
		
	}
	
	

	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("customer", "customer");
		activeMenus.put("customer-options", "customer-options");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("customer");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/optionsset/remove.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> deleteOptionSet(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		String sid = request.getParameter("id");

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		AjaxResponse resp = new AjaxResponse();

		
		try {
			

			
			Long optionSetId = Long.parseLong(sid);

			
			CustomerOptionSet entity = customerOptionSetService.getById(optionSetId);
			//if(entity==null || entity.getPk().getCustomerOption().getMerchantStore().getId().intValue()!=store.getId().intValue()) {
			if(entity==null || entity.getCustomerOption().getMerchantStore().getId().intValue()!=store.getId().intValue()) {

				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				
			} else {
				
				customerOptionSetService.delete(entity);
				resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
				
			}
		
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting option", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	
	

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/optionsset/update.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> updateOrder(HttpServletRequest request, HttpServletResponse response) {
		String values = request.getParameter("_oldValues");
		String order = request.getParameter("order");

		AjaxResponse resp = new AjaxResponse();

		try {
			
			/**
			 * Values
			 */
			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("rawtypes")
			Map conf = mapper.readValue(values, Map.class);
			
			String sid = (String)conf.get("id");

			Long optionId = Long.parseLong(sid);

			CustomerOptionSet entity = customerOptionSetService.getById(optionId);
			
			
			if(entity!=null) {
				
				entity.setSortOrder(Integer.parseInt(order));
				customerOptionSetService.update(entity);
				resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
				
			}

		
		} catch (Exception e) {
			LOGGER.error("Error while paging shipping countries", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	
	

}
