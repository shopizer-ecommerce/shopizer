package com.salesmanager.shop.store.controller.customer;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartCalculationService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartService;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.customer.SecuredCustomer;
import com.salesmanager.shop.model.shoppingcart.ShoppingCartData;
import com.salesmanager.shop.populator.shoppingCart.ShoppingCartDataPopulator;
import com.salesmanager.shop.store.controller.AbstractController;
import com.salesmanager.shop.store.controller.customer.facade.CustomerFacade;
import com.salesmanager.shop.utils.ImageFilePath;

/**
 * Custom Spring Security authentication
 * @author Carl Samson
 *
 */
@Controller
@RequestMapping("/shop/customer")
public class CustomerLoginController extends AbstractController {


    @Inject
    private  CustomerFacade customerFacade;

    @Inject
    private ShoppingCartService shoppingCartService;
    
    @Inject
    private ShoppingCartCalculationService shoppingCartCalculationService;
    
    @Inject
    private PricingService pricingService;

    
	 @Inject
	 @Qualifier("img")
	 private ImageFilePath imageUtils;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerLoginController.class);
	
	
	private AjaxResponse logon(String userName, String password, String storeCode, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        AjaxResponse jsonObject = new AjaxResponse();
        

        try {

        	LOG.debug("Authenticating user " + userName);
        	
        	//user goes to shop filter first so store and language are set
        	MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
        	Language language = (Language)request.getAttribute("LANGUAGE");

            //check if username is from the appropriate store
            Customer customerModel = customerFacade.getCustomerByUserName(userName, store);
            if(customerModel==null) {
            	jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
            	return jsonObject;
            }
            
            if(!customerModel.getMerchantStore().getCode().equals(storeCode)) {
            	jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
            	return jsonObject;
            }
            
            customerFacade.authenticate(customerModel, userName, password);
            //set customer in the http session
            super.setSessionAttribute(Constants.CUSTOMER, customerModel, request);
            jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
            jsonObject.addEntry(Constants.RESPONSE_KEY_USERNAME, customerModel.getNick());


            
            
            LOG.info( "Fetching and merging Shopping Cart data" );
            String sessionShoppingCartCode= (String)request.getSession().getAttribute( Constants.SHOPPING_CART );
            if(!StringUtils.isBlank(sessionShoppingCartCode)) {
	            ShoppingCart shoppingCart = customerFacade.mergeCart( customerModel, sessionShoppingCartCode, store, language );
	            if(shoppingCart != null) {
		            ShoppingCartData shoppingCartData=this.populateShoppingCartData(shoppingCart, store, language);
		            if(shoppingCartData !=null){
		                jsonObject.addEntry(Constants.SHOPPING_CART, shoppingCartData.getCode());
		                request.getSession().setAttribute(Constants.SHOPPING_CART, shoppingCartData.getCode());
		                
			            //set cart in the cookie
			            Cookie c = new Cookie(Constants.COOKIE_NAME_CART, shoppingCartData.getCode());
			            c.setMaxAge(60 * 24 * 3600);
			            c.setPath(Constants.SLASH);
			            response.addCookie(c);
		                
		            } else {
		            	//DELETE COOKIE
		            	Cookie c = new Cookie(Constants.COOKIE_NAME_CART, "");
			            c.setMaxAge(0);
			            c.setPath(Constants.SLASH);
			            response.addCookie(c);
		            }
	            }
	            
	            
	            
            } else {

	            ShoppingCart cartModel = shoppingCartService.getShoppingCart(customerModel);
	            if(cartModel!=null) {
	                jsonObject.addEntry( Constants.SHOPPING_CART, cartModel.getShoppingCartCode());
	                request.getSession().setAttribute(Constants.SHOPPING_CART, cartModel.getShoppingCartCode());
	                
		            Cookie c = new Cookie(Constants.COOKIE_NAME_CART, cartModel.getShoppingCartCode());
		            c.setMaxAge(60 * 24 * 3600);
		            c.setPath(Constants.SLASH);
		            response.addCookie(c);
	                
	            }

            
            }
            
            StringBuilder cookieValue = new StringBuilder();
            cookieValue.append(store.getCode()).append("_").append(customerModel.getNick());
            
            //set username in the cookie
            Cookie c = new Cookie(Constants.COOKIE_NAME_USER, cookieValue.toString());
            c.setMaxAge(60 * 24 * 3600);
            c.setPath(Constants.SLASH);
            response.addCookie(c);
            
            
        } catch (AuthenticationException ex) {
        	jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
        } catch(Exception e) {
        	jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
        }
        
        return jsonObject;
		
		
	}
	//http://localhost:8080/sm-shop/shop/customer/authenticate.html?userName=shopizer&password=password&storeCode=DEFAULT
	@RequestMapping(value="/authenticate.html", method=RequestMethod.GET)
	public @ResponseBody String basicLogon(@RequestParam String userName, @RequestParam String password, @RequestParam String storeCode, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AjaxResponse jsonObject = this.logon(userName, password, storeCode, request, response);
		return jsonObject.toJSONString();
		
	}
	
	/**
	 * Customer login entry point
	 * @param securedCustomer
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/logon.html", method=RequestMethod.POST)
	public @ResponseBody String jsonLogon(@ModelAttribute SecuredCustomer securedCustomer, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        AjaxResponse jsonObject = this.logon(securedCustomer.getUserName(), securedCustomer.getPassword(), securedCustomer.getStoreCode(), request, response);
        return jsonObject.toJSONString();
        
	
	}
	

	
    private ShoppingCartData populateShoppingCartData(final ShoppingCart cartModel , final MerchantStore store, final Language language){

        ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
        shoppingCartDataPopulator.setShoppingCartCalculationService( shoppingCartCalculationService );
        shoppingCartDataPopulator.setPricingService( pricingService );
        
        try
        {
            return shoppingCartDataPopulator.populate(  cartModel ,  store,  language);
        }
        catch ( ConversionException ce )
        {
           LOG.error( "Error in converting shopping cart to shopping cart data", ce );

        }
        return null;
    }

}
