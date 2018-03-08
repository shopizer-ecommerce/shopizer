package com.salesmanager.shop.store.api.v1.shoppingCart;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.shoppingcart.PersistableShoppingCartItem;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCart;
import com.salesmanager.shop.store.controller.shoppingCart.facade.ShoppingCartFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;

@Controller
@RequestMapping("/api/v1")
public class ShoppingCartApi {
	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private LanguageUtils languageUtils;
	
	@Inject
	private ShoppingCartFacade shoppingCartFacade;
	
	@Inject
	private CustomerService customerService;
	
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartApi.class);
	

	
    @ResponseStatus(HttpStatus.CREATED)
	@RequestMapping( value="/customers/{id}/cart", method=RequestMethod.POST)
    public @ResponseBody ReadableShoppingCart addToCart(@PathVariable Long id, @Valid @RequestBody PersistableShoppingCartItem shoppingCartItem, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(com.salesmanager.core.business.constants.Constants.DEFAULT_STORE);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);	
			
			//lookup customer
			Customer customer = customerService.getById(id);
			
 			if(customer == null){
 				response.sendError(404, "No Customer found for ID : " + id);
 				return null;
 			}
			
 			ReadableShoppingCart cart = shoppingCartFacade.addToCart(customer, shoppingCartItem, merchantStore, language);

 			return cart;
 			
		} catch (Exception e) {
			LOGGER.error("Error while adding product to cart",e);
			try {
				response.sendError(503, "Error while adding product to cart " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
		
	}
    
    
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping( value="/customers/{id}/cart", method=RequestMethod.GET)
    public @ResponseBody ReadableShoppingCart get(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(com.salesmanager.core.business.constants.Constants.DEFAULT_STORE);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);	
			
			//lookup customer
			Customer customer = customerService.getById(id);
			
 			if(customer == null){
 				response.sendError(404, "No Customer found for ID : " + id);
 				return null;
 			}
			
 			ReadableShoppingCart cart = shoppingCartFacade.getCart(customer, merchantStore, language);
 			
 			if(cart == null){
 				response.sendError(404, "No ShoppingCart found for customer ID : " + id);
 				return null;
 			}
 			
 			return cart;
 			
		} catch (Exception e) {
			LOGGER.error("Error while getting cart",e);
			try {
				response.sendError(503, "Error while getting cart " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
		
	}

}
