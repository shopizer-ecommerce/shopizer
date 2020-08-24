package com.salesmanager.shop.store.controller.order;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.integration.payment.impl.PayPalExpressCheckoutPayment;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.customer.attribute.CustomerOptionService;
import com.salesmanager.core.business.services.customer.attribute.CustomerOptionValueService;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.business.services.payments.PaymentService;
import com.salesmanager.core.business.services.payments.TransactionService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartService;
import com.salesmanager.core.business.utils.CoreConfiguration;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.OrderTotalSummary;
import com.salesmanager.core.model.order.orderproduct.OrderProductDownload;
import com.salesmanager.core.model.payments.PaymentType;
import com.salesmanager.core.model.payments.PaypalPayment;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shipping.ShippingSummary;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.core.modules.integration.payment.model.PaymentModule;
import com.salesmanager.shop.admin.model.userpassword.UserReset;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.order.ShopOrder;
import com.salesmanager.shop.store.controller.AbstractController;
import com.salesmanager.shop.store.controller.customer.facade.CustomerFacade;
import com.salesmanager.shop.store.controller.order.facade.OrderFacade;
import com.salesmanager.shop.store.controller.shoppingCart.facade.ShoppingCartFacade;
import com.salesmanager.shop.utils.LabelUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(Constants.SHOP_URI)
public class ShoppingOrderPaymentController extends AbstractController {
	
	private static final Logger LOGGER = LoggerFactory
	.getLogger(ShoppingOrderPaymentController.class);
	
	private final static String INIT_ACTION = "init";
	
	
	@Inject
	private ShoppingCartFacade shoppingCartFacade;
	
    @Inject
    private ShoppingCartService shoppingCartService;
	
    @Inject
	private LanguageService languageService;
	
	@Inject
	private PaymentService paymentService;	

	@Inject
	private OrderService orderService;
	
	@Inject
	private CountryService countryService;
	
	@Inject
	private ZoneService zoneService;
	
	@Inject
	private OrderFacade orderFacade;
	
	@Inject
	private LabelUtils messages;
	
	@Inject
	private PricingService pricingService;
	
	@Inject
	private CustomerService customerService;
	
	@Inject
	private CustomerOptionService customerOptionService;
	
	@Inject
	private CustomerOptionValueService customerOptionValueService;
	
	@Inject
	private TransactionService transactionService;
	
	@Inject
	private CoreConfiguration coreConfiguration;
	
	@Inject
	private CustomerFacade customerFacade;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	

	@Autowired
	private Environment env;
	
	
	/**
	 * Recalculates shipping and tax following a change in country or province
	 * @param order
	 * @param request
	 * @param response
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/order/payment/{action}/{paymentmethod}.html"}, method=RequestMethod.POST)
	public @ResponseBody String paymentAction(@Valid @ModelAttribute(value="order") ShopOrder order, @PathVariable String action, @PathVariable String paymentmethod, Device device, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		
		LOGGER.info("ShopOrder ######,{}", order);
		
		Language language = (Language)request.getAttribute("LANGUAGE");
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
		String shoppingCartCode  = getSessionAttribute(Constants.SHOPPING_CART, request);
		
		Validate.notNull(shoppingCartCode,"shoppingCartCode does not exist in the session");
		AjaxResponse ajaxResponse = new AjaxResponse();

		try {
			

			com.salesmanager.core.model.shoppingcart.ShoppingCart cart = shoppingCartFacade.getShoppingCartModel(shoppingCartCode, store);
			
			Set<ShoppingCartItem> items = cart.getLineItems();
			List<ShoppingCartItem> cartItems = new ArrayList<ShoppingCartItem>(items);
			order.setShoppingCartItems(cartItems);
			
			//validate order first
			Map<String,String> messages = new TreeMap<String,String>();
			orderFacade.validateOrder(order, new BeanPropertyBindingResult(order,"order"), messages, store, locale);
			
			if(CollectionUtils.isNotEmpty(messages.values())) {
				for(String key : messages.keySet()) {
					String value = messages.get(key);
					ajaxResponse.addValidationMessage(key, value);
				}
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_VALIDATION_FAILED);
				return ajaxResponse.toJSONString();
			}
			
			
			IntegrationConfiguration config = paymentService.getPaymentConfiguration(order.getPaymentModule(), store);
			IntegrationModule integrationModule = paymentService.getPaymentMethodByCode(store, order.getPaymentModule());

			
			//OrderTotalSummary orderTotalSummary = orderFacade.calculateOrderTotal(store, order, language);
			OrderTotalSummary orderTotalSummary = super.getSessionAttribute(Constants.ORDER_SUMMARY, request);
			if(orderTotalSummary==null) {
				orderTotalSummary = orderFacade.calculateOrderTotal(store, order, language);
				super.setSessionAttribute(Constants.ORDER_SUMMARY, orderTotalSummary, request);
			}
			
			ShippingSummary summary = (ShippingSummary)request.getSession().getAttribute("SHIPPING_SUMMARY");

			if(summary!=null) {
				order.setShippingSummary(summary);
			}

			// Temporary Fix
			if(paymentmethod.equals("PAYPAL")) {
				order.setPaymentMethodType(PaymentType.TEMPPAYTM.name());
			}
			
			if(action.equals(INIT_ACTION)) {
				if(paymentmethod.equals("PAYPAL")) {
					try {
						

					
						PaymentModule module = paymentService.getPaymentModule("paypal-express-checkout");
						PayPalExpressCheckoutPayment p = (PayPalExpressCheckoutPayment)module;
						PaypalPayment payment = new PaypalPayment();
						payment.setCurrency(store.getCurrency());
						Transaction transaction = p.initPaypalTransaction(store, cartItems, orderTotalSummary, payment, config, integrationModule);
						transactionService.create(transaction);
						
						super.setSessionAttribute(Constants.INIT_TRANSACTION_KEY, transaction, request);
						
						//https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout-mobile&token=tokenValueReturnedFromSetExpressCheckoutCall
						//For Desktop use
						//https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=tokenValueReturnedFromSetExpressCheckoutCall
						
						String payTMRedirectURL = env.getProperty("paytm.redirect.url");
						
						StringBuilder urlAppender = new StringBuilder(payTMRedirectURL);
						
//						if(device!=null) {
//							if(device.isNormal()) {
//								urlAppender.append(coreConfiguration.getProperty("PAYPAL_EXPRESSCHECKOUT_REGULAR"));
//							}
//							if(device.isTablet()) {
//								urlAppender.append(coreConfiguration.getProperty("PAYPAL_EXPRESSCHECKOUT_REGULAR"));
//							}
//							if(device.isMobile()) {
//								urlAppender.append(coreConfiguration.getProperty("PAYPAL_EXPRESSCHECKOUT_MOBILE"));
//							}
//						} else {
//							urlAppender.append(coreConfiguration.getProperty("PAYPAL_EXPRESSCHECKOUT_REGULAR"));
//						}
						
						OrderTotalSummary totalSummary = super.getSessionAttribute(Constants.ORDER_SUMMARY, request);
						
						if(totalSummary==null) {
							totalSummary = orderFacade.calculateOrderTotal(store, order, language);
							super.setSessionAttribute(Constants.ORDER_SUMMARY, totalSummary, request);
						}
						
						
						order.setOrderTotalSummary(totalSummary);
						
						Order modelOrder = commitOrder(order,request,locale,null);
						Long orderId =  modelOrder.getId();
						LOGGER.info("Model Order Id {} ",orderId);
						
						urlAppender.append("/"+transaction.getTransactionDetails().get("ORDER_UID")+"/"+transaction.getAmount() +"/"+orderId);
						
						ajaxResponse.addEntry("url", urlAppender.toString());
						
						
						
						
//						if(config.getEnvironment().equals(com.salesmanager.core.business.constants.Constants.PRODUCTION_ENVIRONMENT)) {
//							StringBuilder url = new StringBuilder().append(coreConfiguration.getProperty("PAYPAL_EXPRESSCHECKOUT_PRODUCTION")).append(urlAppender.toString());
//							ajaxResponse.addEntry("url", url.toString());
//						} else {
//							StringBuilder url = new StringBuilder().append(coreConfiguration.getProperty("PAYPAL_EXPRESSCHECKOUT_SANDBOX")).append(urlAppender.toString());
//							ajaxResponse.addEntry("url", url.toString());
//						}

						//keep order in session when user comes back from pp
						//super.setSessionAttribute(Constants.ORDER, order, request);
						
						
						ajaxResponse.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
					
					} catch(Exception e) {
						ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
					}
							
					
				}
			}
		
		} catch(Exception e) {
			LOGGER.error("Error while performing payment action " + action + " for payment method " + paymentmethod ,e);
			ajaxResponse.setErrorMessage(e);
			ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);

		}
		
		
		HttpSession session = request.getSession();
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
		    String name = attributeNames.nextElement();
		    
		    LOGGER.info("session keys paymentAction ######,{}", name);
		}
		
		LOGGER.info("ajaxRepsonse ######,{}", ajaxResponse.toJSONString());
		
		return ajaxResponse.toJSONString();
	}
	
	//cancel - success paypal order
	@RequestMapping(value={"/paypal/checkout.html/{code}"}, method=RequestMethod.GET)
	public  String returnPayPalPayment(@PathVariable String code, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		if(Constants.SUCCESS.equals(code)) {
			return "redirect:" + Constants.SHOP_URI + "/order/commitPreAuthorized.html";
		} else {//process as cancel
			return "redirect:" + Constants.SHOP_URI + "/order/checkout.html";
		}	
	}
	
	
	private Order commitOrder(ShopOrder order, HttpServletRequest request, Locale locale,Map<String,String> payTMtransactionDetails) throws Exception, ServiceException {
		
		
		LOGGER.info("Entering comitOrder");
	
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
		Language language = (Language)request.getAttribute("LANGUAGE");
		
		
		String userName = null;
		String password = null;
		
		PersistableCustomer customer = order.getCustomer();
		
        /** set username and password to persistable object **/
		LOGGER.info("Set username and password to customer");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer authCustomer = null;
    	if(auth != null &&
        		 request.isUserInRole("AUTH_CUSTOMER")) {
    		LOGGER.info("Customer authenticated");
    		authCustomer = customerFacade.getCustomerByUserName(auth.getName(), store);
    		//set id and authentication information
    		customer.setUserName(authCustomer.getNick());
    		//customer.setEncodedPassword(authCustomer.getPassword());
    		customer.setId(authCustomer.getId());
        } else {
        	//set customer id to null
        	customer.setId(null);
        }
	
        //if the customer is new, generate a password
    	LOGGER.info("New customer generate password");
        if(customer.getId()==null || customer.getId()==0) {//new customer
        	password = UserReset.generateRandomString();
        	String encodedPassword = passwordEncoder.encode(password);
        	//customer.setEncodedPassword(encodedPassword);
        }
        
        if(order.isShipToBillingAdress()) {
        	customer.setDelivery(customer.getBilling());
        }
        

        LOGGER.info("Before creating new volatile");
		Customer modelCustomer = null;
		try {//set groups
			if(authCustomer==null) {//not authenticated, create a new volatile user
				modelCustomer = customerFacade.getCustomerModel(customer, store, language);
				customerFacade.setCustomerModelDefaultProperties(modelCustomer, store);
				userName = modelCustomer.getNick();
				LOGGER.debug( "About to persist volatile customer to database." );
				if(modelCustomer.getDefaultLanguage() == null) {
					modelCustomer.setDefaultLanguage(languageService.toLanguage(locale));
				}
		        customerService.saveOrUpdate( modelCustomer );
			} else {//use existing customer
				LOGGER.info("Populate customer model");
				modelCustomer = customerFacade.populateCustomerModel(authCustomer, customer, store, language);
			}
		} catch(Exception e) {
			throw new ServiceException(e);
		}
        
       
		LOGGER.debug( "About to save transaction" );
        Order modelOrder = null;
        Transaction initialTransaction = (Transaction)super.getSessionAttribute(Constants.INIT_TRANSACTION_KEY, request);
        if(initialTransaction!=null) {
        	if(payTMtransactionDetails != null) {
        		initialTransaction.getTransactionDetails().put("BANKTXNID",payTMtransactionDetails.get("BANKTXNID"));
        		initialTransaction.getTransactionDetails().put("ORDERID",payTMtransactionDetails.get("ORDERID"));
        		initialTransaction.getTransactionDetails().put("PAYMENTMODE",payTMtransactionDetails.get("PAYMENTMODE"));
        		initialTransaction.getTransactionDetails().put("RESPCODE",payTMtransactionDetails.get("RESPCODE"));
        		initialTransaction.getTransactionDetails().put("STATUS",payTMtransactionDetails.get("STATUS"));
        		initialTransaction.getTransactionDetails().put("TXNAMOUNT",payTMtransactionDetails.get("TXNAMOUNT"));
        		initialTransaction.getTransactionDetails().put("TXNDATE",payTMtransactionDetails.get("TXNDATE"));
        		initialTransaction.getTransactionDetails().put("TXNID",payTMtransactionDetails.get("TXNID"));
        	}
        	
        	modelOrder=orderFacade.processOrder(order, modelCustomer, initialTransaction, store, language);
        } else {
        	modelOrder=orderFacade.processOrder(order, modelCustomer, store, language);
        }
        
        //save order id in session
        super.setSessionAttribute(Constants.ORDER_ID, modelOrder.getId(), request);
        //set a unique token for confirmation
        super.setSessionAttribute(Constants.ORDER_ID_TOKEN, modelOrder.getId(), request);
        LOGGER.debug( "Transaction ended and order saved" );
        
        
        LOGGER.debug( "Remove cart" );
		//get cart
		String cartCode = super.getSessionAttribute(Constants.SHOPPING_CART, request);
		if(StringUtils.isNotBlank(cartCode)) {
			try {
				shoppingCartFacade.deleteShoppingCart(cartCode, store);
			} catch(Exception e) {
				LOGGER.error("Cannot delete cart " + cartCode, e);
				throw new ServiceException(e);
			}
		}

		
        //cleanup the order objects
        super.removeAttribute(Constants.ORDER, request);
        super.removeAttribute(Constants.ORDER_SUMMARY, request);
        super.removeAttribute(Constants.INIT_TRANSACTION_KEY, request);
        super.removeAttribute(Constants.SHIPPING_OPTIONS, request);
        super.removeAttribute(Constants.SHIPPING_SUMMARY, request);
        super.removeAttribute(Constants.SHOPPING_CART, request);
 


		
		
        return modelOrder;
	
	
}


}
