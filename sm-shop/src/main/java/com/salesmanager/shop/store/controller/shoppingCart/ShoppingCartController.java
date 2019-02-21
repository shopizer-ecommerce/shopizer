package com.salesmanager.shop.store.controller.shoppingCart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartService;
import com.salesmanager.core.business.utils.ProductPriceUtils;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.shop.PageInformation;
import com.salesmanager.shop.model.shoppingcart.ShoppingCartData;
import com.salesmanager.shop.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.shop.store.controller.AbstractController;
import com.salesmanager.shop.store.controller.ControllerConstants;
import com.salesmanager.shop.store.controller.shoppingCart.facade.ShoppingCartFacade;
import com.salesmanager.shop.utils.LabelUtils;
import com.salesmanager.shop.utils.LanguageUtils;


/**
 * A mini shopping cart is available on the public shopping section from the upper menu
 * Landing page, Category page (list of products) and Product details page contains a form
 * that let the user add an item to the cart, see the quantity of items, total price of items
 * in the cart and remove items
 *
 * Add To Cart
 * ---------------
 * The add to cart is 100% driven by javascript / ajax. The code is available in webapp\resources\js\functions.js
 *
 * <!-- Simple add to cart html example ${id} is the product id -->
 * <form id="input-${id}">
 *  <input type="text" class="input-small" id="quantity-productId-${id}" placeholder="1" value="1">
 * 	<a href="#" class="addToCart" productId="${id}">Add to cart</a>
 * </form>
 *
 * The javascript function creates com.salesmanager.web.entity.shoppingcart.ShoppingCartItem and ShoppingCartAttribute based on user selection
 * The javascript looks in the cookie if a shopping cart code exists ex $.cookie( 'cart' ); // requires jQuery-cookie
 * The javascript posts the ShoppingCartItem and the shopping cart code if present to /shop/addShoppingCartItem.html
 *
 * @see
 *
 *  javascript re-creates the shopping cart div item (div id shoppingcart) (see webapp\pages\shop\templates\bootstrap\sections\header.jsp)
 * The javascript set the shopping cart code in the cookie
 *
 * Display a page
 * ----------------
 *
 * When a page is displayed from the shopping section, the shopping cart has to be displayed
 * 4 paths 1) No shopping cart 2) A shopping cart exist in the session 3) A shopping cart code exists in the cookie  4) A customer is logeed in and a shopping cart exists in the database
 *
 * 1) No shopping cart, nothing to do !
 *
 * 2) StoreFilter will tak care of a ShoppingCart present in the HttpSession
 *
 * 3) Once a page is displayed and no cart returned from the controller, a javascript looks on load in the cookie to see if a shopping cart code is present
 * 	  If a code is present, by ajax the cart is loaded and displayed
 *
 * 4) No cart in the session but the customer logs in, the system looks in the DB if a shopping cart exists, if so it is putted in the session so the StoreFilter can manage it and putted in the request
 *
 * @author Carl Samson
 * @author Umesh
 */

@Controller
@RequestMapping("/shop/cart/")
public class ShoppingCartController extends AbstractController {

	private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartController.class);
	@Inject
	private ProductService productService;

	@Inject
	private ProductAttributeService productAttributeService;

	@Inject
	private PricingService pricingService;

	@Inject
	private OrderService orderService;

	@Inject
	private ShoppingCartService shoppingCartService;

	@Inject
	private ProductPriceUtils productPriceUtils;

	@Inject
	private ShoppingCartFacade shoppingCartFacade;
	
	@Inject
	private LabelUtils messages;
	
	@Inject
	private LanguageUtils languageUtils;
	
	

	/**
	 * Add an item to the ShoppingCart (AJAX exposed method)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value={"/addShoppingCartItem"}, method=RequestMethod.POST)
	public @ResponseBody
	ShoppingCartData addShoppingCartItem(@RequestBody final ShoppingCartItem item, final HttpServletRequest request, final HttpServletResponse response, final Locale locale) throws Exception {


		ShoppingCartData shoppingCart=null;
		

		//Look in the HttpSession to see if a customer is logged in
	    MerchantStore store = getSessionAttribute(Constants.MERCHANT_STORE, request);
	    Language language = (Language)request.getAttribute(Constants.LANGUAGE);
	    Customer customer = getSessionAttribute(  Constants.CUSTOMER, request );


		if(customer != null) {
			com.salesmanager.core.model.shoppingcart.ShoppingCart customerCart = shoppingCartService.getByCustomer(customer);
			if(customerCart!=null) {
				shoppingCart = shoppingCartFacade.getShoppingCartData( customerCart, language);

			} else {
			  /**
			   * BUG that used a previous customer cart
			   */
			  item.setCode(null);
			}
		}

		
		if(shoppingCart==null && !StringUtils.isBlank(item.getCode())) {
			shoppingCart = shoppingCartFacade.getShoppingCartData(item.getCode(), store, language);
		}


		//if shoppingCart is null create a new one
		if(shoppingCart==null) {
			shoppingCart = new ShoppingCartData();
			String code = UUID.randomUUID().toString().replaceAll("-", "");
			shoppingCart.setCode(code);
		}

		shoppingCart=shoppingCartFacade.addItemsToShoppingCart( shoppingCart, item, store,language,customer );
		request.getSession().setAttribute(Constants.SHOPPING_CART, shoppingCart.getCode());


		/******************************************************/
		//TODO validate all of this

		//if a customer exists in http session
			//if a cart does not exist in httpsession
				//get cart from database
					//if a cart exist in the database add the item to the cart and put cart in httpsession and save to the database
					//else a cart does not exist in the database, create a new one, set the customer id, set the cart in the httpsession
			//else a cart exist in the httpsession, add item to httpsession cart and save to the database
		//else no customer in httpsession
			//if a cart does not exist in httpsession
				//create a new one, set the cart in the httpsession
			//else a cart exist in the httpsession, add item to httpsession cart and save to the database


		/**
		 *  Tested with the following :
		 * 	what if you add item in the shopping cart as an anonymous user
		 *  later on you log in to process with checkout but the system retrieves a previous shopping cart saved in the database for that customer
		 *  in that case we need to synchronize both carts and the original one (the one with the customer id) supercedes the current cart in session
		 *  the system will have to deal with the original one and remove the latest
		 */


		//**more implementation details
		//calculate the price of each item by using ProductPriceUtils in sm-core
		//for each product in the shopping cart get the product
		//invoke productPriceUtils.getFinalProductPrice
		//from FinalPrice get final price which is the calculated price given attributes and discounts
		//set each item price in ShoppingCartItem.price


		return shoppingCart;

	}



	/**
	 * Retrieves a Shopping cart from the database (regular shopping cart)
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequestMapping( value = { "/shoppingCart.html" }, method = RequestMethod.GET )
    public String displayShoppingCart( final Model model, final HttpServletRequest request, final HttpServletResponse response, final Locale locale )
        throws Exception
    {

        LOG.debug( "Starting to calculate shopping cart..." );
        
        Language language = (Language)request.getAttribute(Constants.LANGUAGE);
        
        
		//meta information
		PageInformation pageInformation = new PageInformation();
		pageInformation.setPageTitle(messages.getMessage("label.cart.placeorder", locale));
		request.setAttribute(Constants.REQUEST_PAGE_INFORMATION, pageInformation);
        
        
	    MerchantStore store = (MerchantStore) request.getAttribute(Constants.MERCHANT_STORE);
	    Customer customer = getSessionAttribute(  Constants.CUSTOMER, request );

        /** there must be a cart in the session **/
        String cartCode = (String)request.getSession().getAttribute(Constants.SHOPPING_CART);
        
        if(StringUtils.isBlank(cartCode)) {
        	//display empty cart
            StringBuilder template =
                    new StringBuilder().append( ControllerConstants.Tiles.ShoppingCart.shoppingCart ).append( "." ).append( store.getStoreTemplate() );
                return template.toString();
        }
                
        ShoppingCartData shoppingCart = shoppingCartFacade.getShoppingCartData(customer, store, cartCode, language);
        
        if(shoppingCart == null) {
        	//display empty cart
            StringBuilder template =
                    new StringBuilder().append( ControllerConstants.Tiles.ShoppingCart.shoppingCart ).append( "." ).append( store.getStoreTemplate() );
                return template.toString();
        }
        
        Language lang = languageUtils.getRequestLanguage(request, response);
        //Filter unavailables
        List<ShoppingCartItem> unavailables = new ArrayList<ShoppingCartItem>();
        List<ShoppingCartItem> availables = new ArrayList<ShoppingCartItem>();
        //Take out items no more available
        List<ShoppingCartItem> items = shoppingCart.getShoppingCartItems();
        for(ShoppingCartItem item : items) {
        	String code = item.getProductCode();
        	Product p =productService.getByCode(code, lang);
        	if(!p.isAvailable()) {
        		unavailables.add(item);
        	} else {
        		availables.add(item);
        	}
        	
        }
        shoppingCart.setShoppingCartItems(availables);
        shoppingCart.setUnavailables(unavailables);
        


        model.addAttribute( "cart", shoppingCart );
        
        
        
        

        /** template **/
        StringBuilder template =
            new StringBuilder().append( ControllerConstants.Tiles.ShoppingCart.shoppingCart ).append( "." ).append( store.getStoreTemplate() );
        return template.toString();

    }
    
    
	@RequestMapping(value={"/shoppingCartByCode"},  method = { RequestMethod.GET })
	public String displayShoppingCart(@ModelAttribute String shoppingCartCode, final Model model, HttpServletRequest request, HttpServletResponse response, final Locale locale) throws Exception{

			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
			Customer customer = getSessionAttribute(  Constants.CUSTOMER, request );
			
			Language language = (Language)request.getAttribute(Constants.LANGUAGE);
			
			if(StringUtils.isBlank(shoppingCartCode)) {
				return "redirect:/shop";
			}
			
			ShoppingCartData cart =  shoppingCartFacade.getShoppingCartData(customer,merchantStore,shoppingCartCode,language);
			if(cart==null) {
				return "redirect:/shop";
			}
			
			
	        Language lang = languageUtils.getRequestLanguage(request, response);
	        //Filter unavailables
	        List<ShoppingCartItem> unavailables = new ArrayList<ShoppingCartItem>();
	        List<ShoppingCartItem> availables = new ArrayList<ShoppingCartItem>();
	        //Take out items no more available
	        List<ShoppingCartItem> items = cart.getShoppingCartItems();
	        for(ShoppingCartItem item : items) {
	        	String code = item.getProductCode();
	        	Product p =productService.getByCode(code, lang);
	        	if(!p.isAvailable()) {
	        		unavailables.add(item);
	        	} else {
	        		availables.add(item);
	        	}
	        	
	        }
	        cart.setShoppingCartItems(availables);
	        cart.setUnavailables(unavailables);
			
			
			//meta information
			PageInformation pageInformation = new PageInformation();
			pageInformation.setPageTitle(messages.getMessage("label.cart.placeorder", locale));
			request.setAttribute(Constants.REQUEST_PAGE_INFORMATION, pageInformation);
			request.getSession().setAttribute(Constants.SHOPPING_CART, cart.getCode());
	        model.addAttribute("cart", cart);

	        /** template **/
	        StringBuilder template =
	            new StringBuilder().append( ControllerConstants.Tiles.ShoppingCart.shoppingCart ).append( "." ).append( merchantStore.getStoreTemplate() );
	        return template.toString();
			


	}


	/**
	 * Removes an item from the Shopping Cart (AJAX exposed method)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/removeShoppingCartItem.html"},   method = { RequestMethod.GET, RequestMethod.POST })

	String removeShoppingCartItem(final Long lineItemId, final HttpServletRequest request, final HttpServletResponse response) throws Exception {



		//Looks in the HttpSession to see if a customer is logged in

		//get any shopping cart for this user

		//** need to check if the item has property, similar items may exist but with different properties
		//String attributes = request.getParameter("attribute");//attributes id are sent as 1|2|5|
		//this will help with hte removal of the appropriate item

		//remove the item shoppingCartService.create

		//create JSON representation of the shopping cart

		//return the JSON structure in AjaxResponse

		//store the shopping cart in the http session

	    MerchantStore store = getSessionAttribute(Constants.MERCHANT_STORE, request);
	    Language language = (Language)request.getAttribute(Constants.LANGUAGE);
	    Customer customer = getSessionAttribute(  Constants.CUSTOMER, request );
        
        /** there must be a cart in the session **/
        String cartCode = (String)request.getSession().getAttribute(Constants.SHOPPING_CART);
        
        if(StringUtils.isBlank(cartCode)) {
        	return "redirect:/shop";
        }
                
        ShoppingCartData shoppingCart = shoppingCartFacade.getShoppingCartData(customer, store, cartCode, language);
                
		ShoppingCartData shoppingCartData=shoppingCartFacade.removeCartItem(lineItemId, shoppingCart.getCode(),store,language);

		if(shoppingCartData == null) {
			return "redirect:/shop";
		}
		
		if(CollectionUtils.isEmpty(shoppingCartData.getShoppingCartItems())) {
			shoppingCartFacade.deleteShoppingCart(shoppingCartData.getId(), store);
			return "redirect:/shop";
		}
		
		
		
		return Constants.REDIRECT_PREFIX + "/shop/cart/shoppingCart.html";




	}

	/**
	 * Update the quantity of an item in the Shopping Cart (AJAX exposed method)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/updateShoppingCartItem.html"},  method = { RequestMethod.POST })
	public @ResponseBody String updateShoppingCartItem( @RequestBody final ShoppingCartItem[] shoppingCartItems, final HttpServletRequest request, final  HttpServletResponse response)  {

		AjaxResponse ajaxResponse = new AjaxResponse();
		
		
		
	    MerchantStore store = getSessionAttribute(Constants.MERCHANT_STORE, request);
	    Language language = (Language)request.getAttribute(Constants.LANGUAGE);

        
        String cartCode = (String)request.getSession().getAttribute(Constants.SHOPPING_CART);
        
        if(StringUtils.isBlank(cartCode)) {
        	return "redirect:/shop";
        }
        
        try {
        	List<ShoppingCartItem> items = Arrays.asList(shoppingCartItems);
			ShoppingCartData shoppingCart = shoppingCartFacade.updateCartItems(items, store, language);
			ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);

		} catch (Exception e) {
			LOG.error("Excption while updating cart" ,e);
			ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}

        	return ajaxResponse.toJSONString();


	}


}
