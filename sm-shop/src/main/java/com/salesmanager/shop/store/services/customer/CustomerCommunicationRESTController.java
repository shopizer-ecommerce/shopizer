package com.salesmanager.shop.store.services.customer;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.store.controller.customer.facade.CustomerFacade;
import com.salesmanager.shop.store.model.optinnewsletter.OptinCustomerDTO;


@RestController
@RequestMapping("/public")
public class CustomerCommunicationRESTController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerCommunicationRESTController.class);

	@Inject
	private CustomerFacade customerFacade;
	
	/**
	 * Store the email of the customer for the newsletter
	 */
	@RequestMapping( value="/customer/optin.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> createOptin(@RequestBody final OptinCustomerDTO optin, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AjaxResponse resp = new AjaxResponse();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		try {
			LOGGER.debug("Creating an optin : " + optin);
			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
			optin.setStore(merchantStore);
			customerFacade.createCustomerOptin(optin);
			response.setStatus(200);
			String returnString = resp.toJSONString();
			return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
		
			
		} catch(Exception e) {
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
			response.sendError(503, "Exception while creating optin " + e.getMessage());
			LOGGER.error(e.getMessage());
			String returnString = resp.toJSONString();
			return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
		}


	}
}
