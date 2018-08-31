package com.salesmanager.shop.store.api.v1.customer;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.customer.optin.PersistableCustomerOptin;
import com.salesmanager.shop.store.controller.customer.facade.CustomerFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;

import io.swagger.annotations.ApiOperation;


/**
 * Optin a customer to newsletter
 * @author carlsamson
 *
 */
@Controller
@RequestMapping("/api/v1")
public class CustomerNewsletterApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerNewsletterApi.class);
	
	
	@Inject
	private CustomerFacade customerFacade;
	

	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private LanguageUtils languageUtils;
	
	
	/**
	 * Create new optin
	 */
	@RequestMapping( value={"/newsletter"}, method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod = "POST", value = "Creates a newsletter optin", notes = "", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Void> create(@Valid @RequestBody PersistableCustomerOptin optin, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		MerchantStore merchantStore = storeFacade.getByCode(request);
		Language language = languageUtils.getRESTLanguage(request, merchantStore);
		
		
		try {

			customerFacade.optinCustomer(optin, merchantStore);

			return new ResponseEntity<Void>(HttpStatus.OK);
			
		} catch (Exception e) {
			LOGGER.error("Error while optin in customer",e);
			try {
				response.sendError(503, "Error while optin in customer " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}

		
	}

    @ResponseStatus(HttpStatus.OK)
	@RequestMapping( value="/newsletter/{email}", method=RequestMethod.PUT)
	@ApiOperation(httpMethod = "PUT", value = "Updates a customer", notes = "Requires administration access", produces = "application/json", response = PersistableCustomer.class)
    public ResponseEntity<Void> update(@PathVariable String email, @Valid @RequestBody PersistableCustomer customer, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
    	
		try {

			return null;
			
		} catch (Exception e) {
			LOGGER.error("Error while updating optin customer",e);
			try {
				response.sendError(503, "Error while updating optin customer " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
    }
    
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping( value="/newsletter/{email}", method=RequestMethod.DELETE)
    @ApiOperation(httpMethod = "DELETE", value = "Deletes a customer", notes = "Requires administration access",response = Void.class)
    public ResponseEntity<Void> delete(@PathVariable String email, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			return null;
		} catch (Exception e) {
			LOGGER.error("Error while deleting optin",e);
			try {
				response.sendError(503, "Error while deleting customer " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
		
		
	}




}
