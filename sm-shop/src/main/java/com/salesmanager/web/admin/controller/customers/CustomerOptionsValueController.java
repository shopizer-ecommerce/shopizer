package com.salesmanager.web.admin.controller.customers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.customer.model.attribute.CustomerOptionValue;
import com.salesmanager.core.business.customer.model.attribute.CustomerOptionValueDescription;
import com.salesmanager.core.business.customer.service.attribute.CustomerOptionValueService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.controller.ControllerConstants;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.LabelUtils;

@Controller
public class CustomerOptionsValueController {
	
	@Autowired
	LanguageService languageService;
	

	@Autowired
	private CustomerOptionValueService customerOptionValueService;
	
	@Autowired
	LabelUtils messages;

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerOptionsValueController.class);
	
	/**
	 * Displays the list of customer options values
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/options/values/list.html", method=RequestMethod.GET)
	public String displayOptionValues(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		setMenu(model,request);
		return ControllerConstants.Tiles.Customer.optionsValuesList;
		
		
		
	}
	
	/**
	 * Display an option value in edit mode
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/options/values/edit.html", method=RequestMethod.GET)
	public String displayOptionValueEdit(@RequestParam("id") long id, HttpServletRequest request, HttpServletResponse response, Model model, Locale locale) throws Exception {
		return displayOption(id,request,response,model,locale);
	}
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/options/values/create.html", method=RequestMethod.GET)
	public String displayOptionValueCreate(HttpServletRequest request, HttpServletResponse response, Model model, Locale locale) throws Exception {
		return displayOption(null,request,response,model,locale);
	}
	
	private String displayOption(Long id, HttpServletRequest request, HttpServletResponse response,Model model,Locale locale) throws Exception {

		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		List<Language> languages = store.getLanguages();

		Set<CustomerOptionValueDescription> descriptions = new HashSet<CustomerOptionValueDescription>();
		CustomerOptionValue option = new CustomerOptionValue();
		
		if(id!=null && id!=0) {//edit mode
			
			
			option = customerOptionValueService.getById(id);
			
			
			if(option==null) {
				return "redirect:/admin/customers/options/values/list.html";
			}
			
			if(option.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				return "redirect:/admin/customers/options/values/list.html";
			}
			
			Set<CustomerOptionValueDescription> optionDescriptions = option.getDescriptions();

			for(Language l : languages) {
			
				CustomerOptionValueDescription optionDescription = null;
				
				if(optionDescriptions!=null) {
					for(CustomerOptionValueDescription description : optionDescriptions) {
						String code = description.getLanguage().getCode();
						if(code.equals(l.getCode())) {
							optionDescription = description;
						}
					}
				}
				
				if(optionDescription==null) {
					optionDescription = new CustomerOptionValueDescription();
					optionDescription.setLanguage(l);
				}
				
				descriptions.add(optionDescription);
			}

		} else {
			
			for(Language l : languages) {
				CustomerOptionValueDescription desc = new CustomerOptionValueDescription();
				desc.setLanguage(l);
				descriptions.add(desc);
			}
			
			option.setDescriptions(descriptions);
		}
		

		
		model.addAttribute("optionValue", option);
		return ControllerConstants.Tiles.Customer.optionsValueDetails;
		
		
	}
		
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/options/values/save.html", method=RequestMethod.POST)
	public String saveOption(@Valid @ModelAttribute("optionValue") CustomerOptionValue optionValue, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {
		

		//display menu
		setMenu(model,request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		CustomerOptionValue dbEntity =	null;	

		if(optionValue.getId() != null && optionValue.getId() >0) { //edit entry
			
			//get from DB
			dbEntity = customerOptionValueService.getById(optionValue.getId());
			
			if(dbEntity==null) {
				return "redirect:/admin/customers/options/values/list.html";
			}
			
			if(dbEntity.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				return "redirect:/admin/customers/options/values/list.html";
			}
		}
		
		//validate if it contains an existing code
		CustomerOptionValue byCode = customerOptionValueService.getByCode(store, optionValue.getCode());
		if(byCode!=null && optionValue.getId()==null) {
			ObjectError error = new ObjectError("code",messages.getMessage("message.code.exist", locale));
			result.addError(error);
		}

			
		Map<String,Language> langs = languageService.getLanguagesMap();
			

		List<CustomerOptionValueDescription> descriptions = optionValue.getDescriptionsList();
		if(descriptions!=null && descriptions.size()>0) {
			
				Set<CustomerOptionValueDescription> descs = new HashSet<CustomerOptionValueDescription>();
					optionValue.setDescriptions(descs);
					for(CustomerOptionValueDescription description : descriptions) {
						
						if(StringUtils.isBlank(description.getName())) {
							ObjectError error = new ObjectError("name",messages.getMessage("message.name.required", locale));
							result.addError(error);
						} else {
							String code = description.getLanguage().getCode();
							Language l = langs.get(code);
							description.setLanguage(l);
							description.setCustomerOptionValue(optionValue);
							descs.add(description);
						}	
					}

		} else {
			
			ObjectError error = new ObjectError("name",messages.getMessage("message.name.required", locale));
			result.addError(error);
			
		}
			

		optionValue.setMerchantStore(store);

		
		if (result.hasErrors()) {
			return ControllerConstants.Tiles.Customer.optionsValueDetails;
		}
		

		customerOptionValueService.saveOrUpdate(optionValue);

		model.addAttribute("success","success");
		return ControllerConstants.Tiles.Customer.optionsValueDetails;
	}

	
	
	@SuppressWarnings("unchecked")
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/options/values/paging.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String pageOptions(HttpServletRequest request, HttpServletResponse response) {

		AjaxResponse resp = new AjaxResponse();

		
		try {
			
			
			Language language = (Language)request.getAttribute("LANGUAGE");	
		
			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			List<CustomerOptionValue> options = null;
					
	
			options = customerOptionValueService.listByStore(store, language);

			for(CustomerOptionValue option : options) {
				
				@SuppressWarnings("rawtypes")
				Map entry = new HashMap();
				entry.put("id", option.getId());
				entry.put("code", option.getCode());
				CustomerOptionValueDescription description = option.getDescriptions().iterator().next();
				
				entry.put("name", description.getName());
				resp.addDataEntry(entry);
				
				
			}
			
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
			

		
		} catch (Exception e) {
			LOGGER.error("Error while paging options", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
		
		
	}
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/options/values/remove.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String deleteOptionValue(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		String sid = request.getParameter("id");

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		AjaxResponse resp = new AjaxResponse();

		
		try {
			
			Long id = Long.parseLong(sid);
			
			CustomerOptionValue entity = customerOptionValueService.getById(id);

			if(entity==null || entity.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
			} else {
				customerOptionValueService.delete(entity);
				resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
				
			}
		
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting option", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
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

}
