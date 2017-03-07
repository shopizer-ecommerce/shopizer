package com.salesmanager.shop.store.controller.customer;

import com.salesmanager.core.business.services.customer.attribute.CustomerOptionSetService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.attribute.CustomerAttribute;
import com.salesmanager.core.model.customer.attribute.CustomerOptionSet;
import com.salesmanager.core.model.customer.attribute.CustomerOptionType;
import com.salesmanager.core.model.customer.attribute.CustomerOptionValueDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.admin.model.customer.attribute.CustomerOption;
import com.salesmanager.shop.admin.model.customer.attribute.CustomerOptionValue;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.populator.customer.ReadableCustomerOptionPopulator;
import com.salesmanager.shop.store.controller.AbstractController;
import com.salesmanager.shop.store.controller.ControllerConstants;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Entry point for logged in customers
 * @author Carl Samson
 *
 */
@Controller
@RequestMapping("/shop/customer")
public class CustomerDashboardController extends AbstractController {
	
	@Inject
    private AuthenticationManager customerAuthenticationManager;
	
	@Inject
	private CustomerOptionSetService customerOptionSetService;
	
	
	@PreAuthorize("hasRole('AUTH_CUSTOMER')")
	@RequestMapping(value="/dashboard.html", method=RequestMethod.GET)
	public String displayCustomerDashboard(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		

	    MerchantStore store = getSessionAttribute(Constants.MERCHANT_STORE, request);
	    Language language = (Language)request.getAttribute(Constants.LANGUAGE);
	    
		Customer customer = (Customer)request.getAttribute(Constants.CUSTOMER);
		getCustomerOptions(model, customer, store, language);
        

		model.addAttribute("section","dashboard");
		
		
		/** template **/
		StringBuilder template = new StringBuilder().append(ControllerConstants.Tiles.Customer.customer).append(".").append(store.getStoreTemplate());

		return template.toString();
		
	}
	
	
	private void getCustomerOptions(Model model, Customer customer, MerchantStore store, Language language) throws Exception {

		Map<Long,CustomerOption> options = new HashMap<Long,CustomerOption>();
		//get options
		List<CustomerOptionSet> optionSet = customerOptionSetService.listByStore(store, language);
		if(!CollectionUtils.isEmpty(optionSet)) {
			
			
			ReadableCustomerOptionPopulator optionPopulator = new ReadableCustomerOptionPopulator();
			
			Set<CustomerAttribute> customerAttributes = customer.getAttributes();
			
			for(CustomerOptionSet optSet : optionSet) {
				
				com.salesmanager.core.model.customer.attribute.CustomerOption custOption = optSet.getCustomerOption();
				if(!custOption.isActive() || !custOption.isPublicOption()) {
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
							com.salesmanager.core.model.customer.attribute.CustomerOptionValue attributeValue = customerAttribute.getCustomerOptionValue();
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
	
	

}
