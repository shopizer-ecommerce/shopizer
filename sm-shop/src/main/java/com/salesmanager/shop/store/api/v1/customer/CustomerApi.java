package com.salesmanager.shop.store.api.v1.customer;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.CustomerCriteria;
import com.salesmanager.core.model.customer.CustomerList;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.populator.customer.ReadableCustomerList;
import com.salesmanager.shop.populator.customer.ReadableCustomerPopulator;
import com.salesmanager.shop.store.controller.customer.facade.CustomerFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/v1")
public class CustomerApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerApi.class);
	
	
	@Inject
	private CustomerFacade customerFacade;
	
	@Inject
	private CustomerService customerService;
	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private LanguageUtils languageUtils;
	
	
	/**
	 * Create new customer for a given MerchantStore
	 */
	@RequestMapping( value={"/private/customers"}, method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod = "POST", value = "Creates a customer", notes = "Requires administration access", produces = "application/json", response = PersistableCustomer.class)
	@ResponseBody
	public PersistableCustomer create(@Valid @RequestBody PersistableCustomer customer, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);

			customerFacade.create(customer, merchantStore);
			
			customer.setEncodedPassword(null);
			customer.setClearPassword(null);
			
			return customer;
			
		} catch (Exception e) {
			LOGGER.error("Error while saving customer",e);
			try {
				response.sendError(503, "Error while saving customer " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}

		
	}
	
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping( value="/private/customers/{id}", method=RequestMethod.PUT)
	@ApiOperation(httpMethod = "PUT", value = "Updates a customer", notes = "Requires administration access", produces = "application/json", response = PersistableCustomer.class)
    public @ResponseBody PersistableCustomer update(@PathVariable Long id, @Valid @RequestBody PersistableCustomer customer, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
    	
		try {

			MerchantStore merchantStore = storeFacade.getByCode(request);
			customerFacade.update(customer, merchantStore);
			return customer;
			
		} catch (Exception e) {
			LOGGER.error("Error while saving customer",e);
			try {
				response.sendError(503, "Error while saving customer " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
    }
    
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping( value="/private/customers/{id}", method=RequestMethod.DELETE)
    @ApiOperation(httpMethod = "DELETE", value = "Deletes a customer", notes = "Requires administration access",response = Void.class)
    public void delete(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			

			Customer customer = customerService.getById(id);
			if(customer != null){
				customerService.delete(customer);
			}else{
				response.sendError(404, "No Customer found for ID : " + id);
			}
		} catch (Exception e) {
			LOGGER.error("Error while deleting customer",e);
			try {
				response.sendError(503, "Error while deleting customer " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
	}
    
    /**
     * Get all customers
     * @param start
     * @param count
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	@RequestMapping( value="/private/customers", method=RequestMethod.GET)
		@ResponseStatus(HttpStatus.OK)
		@ResponseBody
		public ReadableCustomerList getFilteredCustomers(
			@RequestParam(value = "start", required=false) Integer start,
			@RequestParam(value = "count", required=false) Integer count,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);	
			
			CustomerCriteria customerCriteria = new CustomerCriteria();
			if(start != null) {
				customerCriteria.setStartIndex(start.intValue());
			}
			
			if(count != null) {
				customerCriteria.setMaxCount(count.intValue());
			}
			
			CustomerList customerList = customerService.listByStore(merchantStore, customerCriteria);
			List<ReadableCustomer> readableCustomers = new ArrayList<ReadableCustomer>();
			
			ReadableCustomerPopulator populator = new ReadableCustomerPopulator();
			
			if(CollectionUtils.isNotEmpty(customerList.getCustomers())) {
				for(Customer c : customerList.getCustomers()) {
					ReadableCustomer readable = new ReadableCustomer();
					populator.populate(c, readable, merchantStore, language);
					readableCustomers.add(readable);
				}
			}
			
			ReadableCustomerList readableCustomerList = new ReadableCustomerList();
			readableCustomerList.setCustomers(readableCustomers);
			readableCustomerList.setTotalCount(customerList.getTotalCount());
			
			return readableCustomerList;

		} catch (Exception e) {
			LOGGER.error("Error while getting all customers",e);
			try {
				response.sendError(503, "Error while getting all customers " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
	}
	
	@RequestMapping( value="/private/customers/{id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ReadableCustomer get(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);

			ReadableCustomer customer = customerFacade.getCustomerById(id, merchantStore, language);
			if(customer == null){
				response.sendError(404, "No Customer found for ID : " + id);
			}
			
			return customer;
		} catch (Exception e) {
			LOGGER.error("Error while getting customer",e);
			try {
				response.sendError(503, "Error while getting customer " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
		
	}
	
	@RequestMapping( value="/auth/customers/profile", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ReadableCustomer get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);

			Principal principal = request.getUserPrincipal();
			String userName = principal.getName();
			
			Customer c = customerService.getByNick(userName);
			
			if(c == null) {
				response.sendError(401, "Error while getting customer, customer not authorized");
				return null;
			}
			
			
			ReadableCustomer customer = customerFacade.getCustomerById(c.getId(), merchantStore, language);
			if(customer == null){
				response.sendError(404, "No Customer found for ID : " + c.getId());
			}
			
			return customer;
		} catch (Exception e) {
			LOGGER.error("Error while getting customer",e);
			try {
				response.sendError(503, "Error while getting customer " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
		
	}


}
