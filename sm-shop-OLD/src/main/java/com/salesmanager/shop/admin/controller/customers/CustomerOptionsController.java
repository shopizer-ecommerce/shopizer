package com.salesmanager.shop.admin.controller.customers;

import com.salesmanager.core.business.services.customer.attribute.CustomerOptionService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.customer.attribute.CustomerOption;
import com.salesmanager.core.model.customer.attribute.CustomerOptionDescription;
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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
public class CustomerOptionsController {
	
	@Inject
	private LanguageService languageService;
	
	@Inject
	private CustomerOptionService customerOptionService;
	
	@Inject
	private LabelUtils messages;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerOptionsController.class);
	
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/options/list.html", method=RequestMethod.GET)
	public String displayOptions(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);
		return ControllerConstants.Tiles.Customer.optionsList;
		

	}
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/options/edit.html", method=RequestMethod.GET)
	public String displayOptionEdit(@RequestParam("id") long id, HttpServletRequest request, HttpServletResponse response, Model model, Locale locale) throws Exception {
		return displayOption(id,request,response,model,locale);
	}
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/options/create.html", method=RequestMethod.GET)
	public String displayOptionCreate(HttpServletRequest request, HttpServletResponse response, Model model, Locale locale) throws Exception {
		return displayOption(null,request,response,model,locale);
	}
	
	private String displayOption(Long optionId, HttpServletRequest request, HttpServletResponse response,Model model,Locale locale) throws Exception {

		
		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		List<Language> languages = store.getLanguages();

		Set<CustomerOptionDescription> descriptions = new HashSet<CustomerOptionDescription>();
		
		CustomerOption option = new CustomerOption();
		
		if(optionId!=null && optionId!=0) {//edit mode
			
			
			option = customerOptionService.getById(optionId);
			
			
			if(option==null) {
				return "redirect:/admin/customers/options/list.html";
			}
			
			if(option.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				return "redirect:/admin/customers/options/list.html";
			}
			
			Set<CustomerOptionDescription> optionDescriptions = option.getDescriptions();
			
			
			
			for(Language l : languages) {
			
				CustomerOptionDescription optionDescription = null;
				
				if(optionDescriptions!=null) {
					
					for(CustomerOptionDescription description : optionDescriptions) {
						
						String code = description.getLanguage().getCode();
						if(code.equals(l.getCode())) {
							optionDescription = description;
						}
					}
					
				}
				
				if(optionDescription==null) {
					optionDescription = new CustomerOptionDescription();
					optionDescription.setLanguage(l);
				}
				descriptions.add(optionDescription);
			}

		} else {
			for(Language l : languages) {
				CustomerOptionDescription desc = new CustomerOptionDescription();
				desc.setLanguage(l);
				descriptions.add(desc);
			}
		}
		

		option.setDescriptions(descriptions);
		model.addAttribute("option", option);
		return ControllerConstants.Tiles.Customer.optionDetails;
		
		
	}
		
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/options/save.html", method=RequestMethod.POST)
	public String saveOption(@Valid @ModelAttribute("option") CustomerOption option, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {
		

		//display menu
		setMenu(model,request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		CustomerOption dbEntity =	null;	

		if(option.getId() != null && option.getId() >0) { //edit entry
			
			//get from DB
			dbEntity = customerOptionService.getById(option.getId());
			
			if(dbEntity==null) {
				return "redirect:/admin/options/options.html";
			}
		}
		
		//validate if it contains an existing code
		CustomerOption byCode = customerOptionService.getByCode(store, option.getCode());
		if(byCode!=null && option.getId()==null) {
			ObjectError error = new ObjectError("code",messages.getMessage("message.code.exist", locale));
			result.addError(error);
		}

			
		Map<String,Language> langs = languageService.getLanguagesMap();
			

		List<CustomerOptionDescription> descriptions = option.getDescriptionsList();
		
		if(descriptions!=null) {
				
				for(CustomerOptionDescription description : descriptions) {
					
					if(StringUtils.isBlank(description.getName())) {
						ObjectError error = new ObjectError("name",messages.getMessage("message.name.required", locale));
						result.addError(error);
					} else {
					
						String code = description.getLanguage().getCode();
						Language l = langs.get(code);
						description.setLanguage(l);
						description.setCustomerOption(option);
					
					}
	
				}
				
		}
			
		option.setDescriptions(new HashSet<CustomerOptionDescription>(descriptions));
		option.setMerchantStore(store);

		
		if (result.hasErrors()) {
			return ControllerConstants.Tiles.Customer.optionDetails;
		}
		

		
		
		customerOptionService.saveOrUpdate(option);


		

		model.addAttribute("success","success");
		return ControllerConstants.Tiles.Customer.optionDetails;
	}

	
	
	@SuppressWarnings("unchecked")
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/options/paging.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> pageOptions(HttpServletRequest request, HttpServletResponse response) {

		AjaxResponse resp = new AjaxResponse();

		
		try {
			
			
			Language language = (Language)request.getAttribute("LANGUAGE");	
		
			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			List<CustomerOption> options = null;
					

				
			options = customerOptionService.listByStore(store, language);
				

					
					

			for(CustomerOption option : options) {
				
				@SuppressWarnings("rawtypes")
				Map entry = new HashMap();
				entry.put("id", option.getId());
				
				CustomerOptionDescription description = option.getDescriptions().iterator().next();
				
				entry.put("name", description.getName());
				entry.put("type", option.getCustomerOptionType());
				entry.put("active", option.isActive());
				entry.put("public", option.isPublicOption());
				resp.addDataEntry(entry);
				
				
			}
			
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
	@RequestMapping(value="/admin/customers/options/remove.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> deleteOption(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		String sid = request.getParameter("id");

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		AjaxResponse resp = new AjaxResponse();

		
		try {
			
			Long id = Long.parseLong(sid);
			
			CustomerOption entity = customerOptionService.getById(id);

			if(entity==null || entity.getMerchantStore().getId().intValue()!=store.getId().intValue()) {

				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				
			} else {
				
				customerOptionService.delete(entity);
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

}
