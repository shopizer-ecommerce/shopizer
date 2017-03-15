package com.salesmanager.shop.store.services.customer;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.populator.customer.ReadableCustomerPopulator;
import com.salesmanager.shop.store.services.category.ShoppingCategoryRESTController;

@Controller
@RequestMapping("/public/customer/optin")
public class CustomerCommunicationRESTController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerCommunicationRESTController.class);
	@Inject
	private MerchantStoreService merchantStoreService;
	
	/**
	 * Store the email of the customer for the newsletter
	 */
	@RequestMapping( value="/private/system/optin", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResponse createOptin(@RequestBody final String json, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
//		AjaxResponse resp = new AjaxResponse();
//		
//		try {
//			LOGGER.debug("Creating an optin : " + json);
//			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
//			if(merchantStore!=null) {
//				if(!merchantStore.getCode().equals(store)) {
//					merchantStore = null;
//				}
//			}
//			
//			if(merchantStore== null) {
//				merchantStore = merchantStoreService.getByCode(store);
//			}
//			
//			if(merchantStore==null) {
//				LOGGER.error("Merchant store is null for code " + store);
//				response.sendError(503, "Merchant store is null for code " + store);
//				return null;
//			}
//			response.setStatus(200);
//			resp.setStatus(200);
//			
//		} catch(Exception e) {
//			resp.setStatus(500);
//			resp.setErrorMessage(e);
//			response.sendError(503, "Exception while creating optin " + e.getMessage());
//		}
//
//		return resp;
		return null;

	}
}
