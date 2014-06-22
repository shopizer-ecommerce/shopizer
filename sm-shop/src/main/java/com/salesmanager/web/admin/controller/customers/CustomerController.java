package com.salesmanager.web.admin.controller.customers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.model.CustomerCriteria;
import com.salesmanager.core.business.customer.model.CustomerList;
import com.salesmanager.core.business.customer.model.attribute.CustomerAttribute;
import com.salesmanager.core.business.customer.model.attribute.CustomerOptionSet;
import com.salesmanager.core.business.customer.model.attribute.CustomerOptionType;
import com.salesmanager.core.business.customer.model.attribute.CustomerOptionValueDescription;
import com.salesmanager.core.business.customer.service.CustomerService;
import com.salesmanager.core.business.customer.service.attribute.CustomerAttributeService;
import com.salesmanager.core.business.customer.service.attribute.CustomerOptionService;
import com.salesmanager.core.business.customer.service.attribute.CustomerOptionSetService;
import com.salesmanager.core.business.customer.service.attribute.CustomerOptionValueService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.core.business.reference.zone.service.ZoneService;
import com.salesmanager.core.business.system.service.EmailService;
import com.salesmanager.core.modules.email.Email;
import com.salesmanager.core.utils.ajax.AjaxPageableResponse;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.entity.customer.attribute.CustomerOption;
import com.salesmanager.web.admin.entity.customer.attribute.CustomerOptionValue;
import com.salesmanager.web.admin.entity.userpassword.UserReset;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.constants.EmailConstants;
import com.salesmanager.web.populator.customer.CustomerOptionPopulator;
import com.salesmanager.web.utils.EmailUtils;
import com.salesmanager.web.utils.LabelUtils;
import com.salesmanager.web.utils.LocaleUtils;

@Controller
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	private static final String CUSTOMER_ID_PARAMETER = "customer";
	
	private final static String RESET_PASSWORD_TPL = "email_template_password_reset_customer.ftl";
	
	@Autowired
	private LabelUtils messages;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerOptionService customerOptionService;
	
	@Autowired
	private CustomerOptionValueService customerOptionValueService;
	
	@Autowired
	private CustomerOptionSetService customerOptionSetService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private CustomerAttributeService customerAttributeService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	EmailService emailService;
	
	
	/**
	 * Customer details
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/customer.html", method=RequestMethod.GET)
	public String displayCustomer(Long id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		//display menu
		this.setMenu(model, request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		List<Language> languages = languageService.getLanguages();

		model.addAttribute("languages",languages);
		
		Customer customer = null;
		
		//if request.attribute contains id then get this customer from customerService
		if(id!=null && id!=0) {//edit mode
			
			//get from DB
			customer = customerService.getById(id);
			if(customer==null) {
				return "redirect:/admin/customers/list.html";
			}
			if(customer.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				return "redirect:/admin/customers/list.html";
			}
			
		} else {
			 customer = new Customer();
		}
		//get list of countries (see merchant controller)
		Language language = (Language)request.getAttribute("LANGUAGE");				
		//get countries
		List<Country> countries = countryService.getCountries(language);
		
		//get list of zones
		List<Zone> zones = zoneService.list();
		
		this.getCustomerOptions(model, customer, store, language);

		model.addAttribute("zones", zones);
		model.addAttribute("countries", countries);
		model.addAttribute("customer", customer);
		return "admin-customer";	
		
	}
	
	private void getCustomerOptions(Model model, Customer customer, MerchantStore store, Language language) throws Exception {

		Map<Long,CustomerOption> options = new HashMap<Long,CustomerOption>();
		//get options
		List<CustomerOptionSet> optionSet = customerOptionSetService.listByStore(store, language);
		if(!CollectionUtils.isEmpty(optionSet)) {
			
			
			CustomerOptionPopulator optionPopulator = new CustomerOptionPopulator();
			
			Set<CustomerAttribute> customerAttributes = customer.getAttributes();
			
			for(CustomerOptionSet optSet : optionSet) {
				
				com.salesmanager.core.business.customer.model.attribute.CustomerOption custOption = optSet.getCustomerOption();
				if(!custOption.isActive()) {
					continue;
				}
				CustomerOption customerOption = options.get(custOption.getId());
				
				optionPopulator.setOptionSet(optSet);
				
				
				
				if(customerOption==null) {
					customerOption = new CustomerOption();
					customerOption.setId(custOption.getId());
					customerOption.setType(custOption.getCustomerOptionType());
					customerOption.setName(custOption.getDescriptionsSettoList().get(0).getName());
					
				} 
				
				optionPopulator.populate(custOption, customerOption, store, language);
				options.put(customerOption.getId(), customerOption);

				if(!CollectionUtils.isEmpty(customerAttributes)) {
					for(CustomerAttribute customerAttribute : customerAttributes) {
						if(customerAttribute.getCustomerOption().getId().longValue()==customerOption.getId()){
							CustomerOptionValue selectedValue = new CustomerOptionValue();
							com.salesmanager.core.business.customer.model.attribute.CustomerOptionValue attributeValue = customerAttribute.getCustomerOptionValue();
							selectedValue.setId(attributeValue.getId());
							CustomerOptionValueDescription optValue = attributeValue.getDescriptionsSettoList().get(0);
							selectedValue.setName(optValue.getName());
							customerOption.setDefaultValue(selectedValue);
							if(customerOption.getType().equalsIgnoreCase(CustomerOptionType.Text.name())) {
								selectedValue.setName(customerAttribute.getTextValue());
							} 
						}
					}
				}
			}
		}
		
		
		model.addAttribute("options", options.values());

		
	}
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/save.html", method=RequestMethod.POST)
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception{
	
		this.setMenu(model, request);
		
		String email_regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
		Pattern pattern = Pattern.compile(email_regEx);
		
		Language language = (Language)request.getAttribute("LANGUAGE");
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		List<Language> languages = languageService.getLanguages();
		
		model.addAttribute("languages",languages);
		
		this.getCustomerOptions(model, customer, store, language);
		
		//get countries
		List<Country> countries = countryService.getCountries(language);

		
		if(!StringUtils.isBlank(customer.getEmailAddress() ) ){
			 java.util.regex.Matcher matcher = pattern.matcher(customer.getEmailAddress());
			 
			 if(!matcher.find()) {
				ObjectError error = new ObjectError("customerEmailAddress",messages.getMessage("Email.customer.EmailAddress", locale));
				result.addError(error);
			 }
		}else{
			ObjectError error = new ObjectError("customerEmailAddress",messages.getMessage("NotEmpty.customer.EmailAddress", locale));
			result.addError(error);
		}
		

		 
		if( StringUtils.isBlank(customer.getBilling().getFirstName() ) ){
			 ObjectError error = new ObjectError("billingFirstName", messages.getMessage("NotEmpty.customer.billingFirstName", locale));
			 result.addError(error);
		}
		
		if( StringUtils.isBlank(customer.getBilling().getLastName() ) ){
			 ObjectError error = new ObjectError("billingLastName", messages.getMessage("NotEmpty.customer.billingLastName", locale));
			 result.addError(error);
		}
		
		if( StringUtils.isBlank(customer.getBilling().getAddress() ) ){
			 ObjectError error = new ObjectError("billingAddress", messages.getMessage("NotEmpty.customer.billingStreetAddress", locale));
			 result.addError(error);
		}
		 
		if( StringUtils.isBlank(customer.getBilling().getCity() ) ){
			 ObjectError error = new ObjectError("billingCity",messages.getMessage("NotEmpty.customer.billingCity", locale));
			 result.addError(error);
		}
		 
		if( customer.getShowBillingStateList().equalsIgnoreCase("yes" ) && customer.getBilling().getZone().getCode() == null ){
			 ObjectError error = new ObjectError("billingState",messages.getMessage("NotEmpty.customer.billingState", locale));
			 result.addError(error);
			 
		}else if( customer.getShowBillingStateList().equalsIgnoreCase("no" ) && customer.getBilling().getState() == null ){
				 ObjectError error = new ObjectError("billingState",messages.getMessage("NotEmpty.customer.billingState", locale));
				 result.addError(error);
			
		}
		 
		if( StringUtils.isBlank(customer.getBilling().getPostalCode() ) ){
			 ObjectError error = new ObjectError("billingPostalCode", messages.getMessage("NotEmpty.customer.billingPostCode", locale));
			 result.addError(error);
		}
		
		//check if error from the @valid
		if (result.hasErrors()) {
			model.addAttribute("countries", countries);
			return "admin-customer";
		}
				
		Customer newCustomer = new Customer();

		if( customer.getId()!=null && customer.getId().longValue()>0 ) {
			newCustomer = customerService.getById( customer.getId() );
			
			if(newCustomer==null) {
				return "redirect:/admin/customers/list.html";
			}
			
			if(newCustomer.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				return "redirect:/admin/customers/list.html";
			}
			
			
			
		}else{
			//  new customer set marchant_Id
			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			newCustomer.setMerchantStore(merchantStore);
		}
		

		newCustomer.setEmailAddress(customer.getEmailAddress() );		
		
		//get Customer country/zone 		
		Country deliveryCountry = countryService.getByCode( customer.getDelivery().getCountry().getIsoCode()); 
		Country billingCountry  = countryService.getByCode( customer.getBilling().getCountry().getIsoCode()) ;

		Zone deliveryZone = customer.getDelivery().getZone();
		Zone billingZone  = customer.getBilling().getZone();
		

		
		if (customer.getShowDeliveryStateList().equalsIgnoreCase("yes" )) {
			deliveryZone = zoneService.getByCode(customer.getDelivery().getZone().getCode());
			customer.getDelivery().setState( null );
			
		}else if (customer.getShowDeliveryStateList().equalsIgnoreCase("no" )){
			deliveryZone = null ;
			customer.getDelivery().setState( customer.getDelivery().getState() );
		}
	
		if (customer.getShowBillingStateList().equalsIgnoreCase("yes" )) {
			billingZone = zoneService.getByCode(customer.getBilling().getZone().getCode());
			customer.getBilling().setState( null );
			
		}else if (customer.getShowBillingStateList().equalsIgnoreCase("no" )){
			billingZone = null ;
			customer.getBilling().setState( customer.getBilling().getState() );
		}
				

		
		newCustomer.setDefaultLanguage(customer.getDefaultLanguage() );
		
		customer.getDelivery().setZone(  deliveryZone);
		customer.getDelivery().setCountry(deliveryCountry );
		newCustomer.setDelivery( customer.getDelivery() );
		
		customer.getBilling().setZone(  billingZone);
		customer.getBilling().setCountry(billingCountry );
		newCustomer.setBilling( customer.getBilling()  );
		
		customerService.saveOrUpdate(newCustomer);
		
		model.addAttribute("customer", newCustomer);
		model.addAttribute("countries", countries);
		model.addAttribute("success","success");
		
		return "admin-customer";
		
	}
	
	/**
	 * Deserves shop and admin
	 * @param request
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value={"/admin/customers/attributes/save.html"}, method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String saveCustomerAttributes(HttpServletRequest request, Locale locale) throws Exception {
		

		AjaxResponse resp = new AjaxResponse();
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		//1=1&2=on&3=eeee&4=on&customer=1

		@SuppressWarnings("rawtypes")
		Enumeration parameterNames = request.getParameterNames();
		
		Customer customer = null;
		
		while(parameterNames.hasMoreElements()) {

			String parameterName = (String)parameterNames.nextElement();
			String parameterValue = request.getParameter(parameterName);
			if(CUSTOMER_ID_PARAMETER.equals(parameterName)) {
				customer = customerService.getById(new Long(parameterValue));
				break;
			}
		}
		
		if(customer==null) {
			LOGGER.error("Customer id [customer] is not defined in the parameters");
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			return resp.toJSONString();
		}
		
		if(customer.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
			LOGGER.error("Customer id does not belong to current store");
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			return resp.toJSONString();
		}
		
		List<CustomerAttribute> customerAttributes = customerAttributeService.getByCustomer(store, customer);
		Map<Long,CustomerAttribute> customerAttributesMap = new HashMap<Long,CustomerAttribute>();
		
		for(CustomerAttribute attr : customerAttributes) {
			customerAttributesMap.put(attr.getCustomerOption().getId(), attr);
		}

		parameterNames = request.getParameterNames();
		
		while(parameterNames.hasMoreElements()) {
			
			String parameterName = (String)parameterNames.nextElement();
			String parameterValue = request.getParameter(parameterName);
			try {
				
				String[] parameterKey = parameterName.split("-");
				com.salesmanager.core.business.customer.model.attribute.CustomerOption customerOption = null;
				com.salesmanager.core.business.customer.model.attribute.CustomerOptionValue customerOptionValue = null;

				
				if(CUSTOMER_ID_PARAMETER.equals(parameterName)) {
					continue;
				}
				
					if(parameterKey.length>1) {
						//parse key - value
						String key = parameterKey[0];
						String value = parameterKey[1];
						//should be on
						customerOption = customerOptionService.getById(new Long(key));
						customerOptionValue = customerOptionValueService.getById(new Long(value));
						

						
					} else {
						customerOption = customerOptionService.getById(new Long(parameterName));
						customerOptionValue = customerOptionValueService.getById(new Long(parameterValue));

					}
					
					//get the attribute
					//CustomerAttribute attribute = customerAttributeService.getByCustomerOptionId(store, customer.getId(), customerOption.getId());
					CustomerAttribute attribute = customerAttributesMap.get(customerOption.getId());
					if(attribute==null) {
						attribute = new CustomerAttribute();
						attribute.setCustomer(customer);
						attribute.setCustomerOption(customerOption);
					} else {
						customerAttributes.remove(attribute);
					}
					
					if(customerOption.getCustomerOptionType().equals(CustomerOptionType.Text.name())) {
						if(!StringUtils.isBlank(parameterValue)) {
							attribute.setCustomerOptionValue(customerOptionValue);
							attribute.setTextValue(parameterValue);
						} else {
							attribute.setTextValue(null);
						}
					} else {
						attribute.setCustomerOptionValue(customerOptionValue);
					}
					
					
					if(attribute.getId()!=null && attribute.getId().longValue()>0) {
						if(attribute.getCustomerOptionValue()==null){
							customerAttributeService.delete(attribute);
						} else {
							customerAttributeService.update(attribute);
						}
					} else {
						customerAttributeService.save(attribute);
					}
					


			} catch (Exception e) {
				LOGGER.error("Cannot get parameter information " + parameterName,e);
			}
			
		}
		
		//and now the remaining to be removed
		for(CustomerAttribute attr : customerAttributes) {
			customerAttributeService.delete(attr);
		}
		
		resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
		return resp.toJSONString();
		

	}


	
	/**
	 * List of customers
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/customers/list.html", method=RequestMethod.GET)
	public String displayCustomers(Model model,HttpServletRequest request) throws Exception {
		
		
		this.setMenu(model, request);
	
		return "admin-customers";
		
		
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/admin/customers/page.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody
	String pageCustomers(HttpServletRequest request,HttpServletResponse response) {


		AjaxPageableResponse resp = new AjaxPageableResponse();
		
		//Language language = (Language)request.getAttribute("LANGUAGE");
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		
		try {
			

			
			//Map<String,Country> countriesMap = countryService.getCountriesMap(language);
			
			
			int startRow = Integer.parseInt(request.getParameter("_startRow"));
			int endRow = Integer.parseInt(request.getParameter("_endRow"));
			String	email = request.getParameter("email");
			String name = request.getParameter("name");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String	country = request.getParameter("country");
			
			
			CustomerCriteria criteria = new CustomerCriteria();
			criteria.setStartIndex(startRow);
			criteria.setMaxCount(endRow);
			
			if(!StringUtils.isBlank(email)) {
				criteria.setEmail(email);
			}
			
			if(!StringUtils.isBlank(name)) {
				criteria.setName(name);
			}
			
			if(!StringUtils.isBlank(country)) {
				criteria.setCountry(country);
			}
			
			if(!StringUtils.isBlank(firstName)) {
				criteria.setFirstName(firstName);
			}
			
			if(!StringUtils.isBlank(lastName)) {
				criteria.setLastName(lastName);
			}
			

			CustomerList customerList = customerService.listByStore(store,criteria);
			
			if(customerList.getCustomers()!=null) {
			
				for(Customer customer : customerList.getCustomers()) {
					@SuppressWarnings("rawtypes")
					Map entry = new HashMap();
					entry.put("id", customer.getId());
					entry.put("firstName", customer.getBilling().getFirstName());
					entry.put("lastName", customer.getBilling().getLastName());
					entry.put("email", customer.getEmailAddress());
					entry.put("country", customer.getBilling().getCountry().getIsoCode());
					resp.addDataEntry(entry);
					
				}
			
			}
			
		} catch (Exception e) {
			LOGGER.error("Error while paging orders", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
		
	
	}
	
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/resetPassword.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody
	String resetPassword(HttpServletRequest request,HttpServletResponse response) {
		
		String customerId = request.getParameter("customerId");
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		AjaxResponse resp = new AjaxResponse();
		
		
		
		try {
			
			Long id = Long.parseLong(customerId);
			
			Customer customer = customerService.getById(id);
			
			if(customer==null) {
				resp.setErrorString("Customer does not exist");
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			if(customer.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				resp.setErrorString("Invalid customer id");
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			Language userLanguage = customer.getDefaultLanguage();
			
			Locale customerLocale = LocaleUtils.getLocale(userLanguage);
			
			String password = UserReset.generateRandomString();
			String encodedPassword = passwordEncoder.encodePassword(password, null);
			
			customer.setPassword(encodedPassword);
			
			customerService.saveOrUpdate(customer);
			
			//send email
			
			try {

				//creation of a user, send an email
				String[] storeEmail = {store.getStoreEmailAddress()};
				
				
				Map<String, String> templateTokens = EmailUtils.createEmailObjectsMap(request.getContextPath(), store, messages, customerLocale);
				templateTokens.put(EmailConstants.LABEL_HI, messages.getMessage("label.generic.hi", customerLocale));
		        templateTokens.put(EmailConstants.EMAIL_CUSTOMER_FIRSTNAME, customer.getBilling().getFirstName());
		        templateTokens.put(EmailConstants.EMAIL_CUSTOMER_LASTNAME, customer.getBilling().getLastName());
				templateTokens.put(EmailConstants.EMAIL_RESET_PASSWORD_TXT, messages.getMessage("email.customer.resetpassword.text", customerLocale));
				templateTokens.put(EmailConstants.EMAIL_CONTACT_OWNER, messages.getMessage("email.contactowner", storeEmail, customerLocale));
				templateTokens.put(EmailConstants.EMAIL_PASSWORD_LABEL, messages.getMessage("label.generic.password",customerLocale));
				templateTokens.put(EmailConstants.EMAIL_CUSTOMER_PASSWORD, password);


				Email email = new Email();
				email.setFrom(store.getStorename());
				email.setFromEmail(store.getStoreEmailAddress());
				email.setSubject(messages.getMessage("label.generic.changepassword",customerLocale));
				email.setTo(customer.getEmailAddress());
				email.setTemplateName(RESET_PASSWORD_TPL);
				email.setTemplateTokens(templateTokens);
	
	
				
				emailService.sendHtmlEmail(store, email);
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
			
			} catch (Exception e) {
				LOGGER.error("Cannot send email to user",e);
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			}
			
			
			
			
		} catch (Exception e) {
			LOGGER.error("An exception occured while changing password",e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		
		return resp.toJSONString();
		
		
	}
	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("customer", "customer");
		activeMenus.put("customer-list", "customer-list");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("customer");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);


		//
		
	}
	
/*	private void setMenuCreate(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("customer", "customer");
		activeMenus.put("customer-create", "customer-create");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("customer");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);


		//
		
	}*/
	
		

}
