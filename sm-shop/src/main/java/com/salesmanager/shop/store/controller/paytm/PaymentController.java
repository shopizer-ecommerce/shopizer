package com.salesmanager.shop.store.controller.paytm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.business.services.payments.TransactionService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.orderproduct.OrderProductDownload;
import com.salesmanager.core.model.order.orderstatus.OrderStatus;
import com.salesmanager.core.model.payments.PaymentType;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.payments.TransactionType;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.admin.model.userpassword.UserReset;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.order.ShopOrder;
import com.salesmanager.shop.store.controller.AbstractController;
import com.salesmanager.shop.store.controller.order.ShoppingOrderPaymentController;
import com.salesmanager.shop.utils.EmailTemplatesUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.paytm.pg.merchant.CheckSumServiceHelper;

@Controller
@RequestMapping(Constants.SHOP_URI+"/paytm")
public class PaymentController extends AbstractController{
	

	
	@Autowired
	private PaytmDetails paytmDetails;
	@Autowired
	private Environment env;
	@Inject
	private EmailTemplatesUtils emailTemplatesUtils;
	@Inject
	private OrderService orderService;
    @Inject
    private CustomerService customerService;
    
    @Inject
    private TransactionService transactionService;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PaymentController.class);
		
	@GetMapping("/")
	public String home() {
		return "home";
	}

	 @GetMapping(value = "/pgredirect/{CUST_ID}/{TXN_AMOUNT}/{ORDER_ID}")
	    public ModelAndView getRedirect(@PathVariable("CUST_ID") String customerId,
	    		@PathVariable("TXN_AMOUNT") String transactionAmount,
	    		@PathVariable("ORDER_ID") String orderId,HttpServletRequest request) throws Exception {

	  		
		    HttpSession session = request.getSession();
		    String sessionId = session.getId();
		    LOGGER.info("SESSIONID  pgredirect ######,{}", sessionId);
		    
					Enumeration<String> attributeNames = session.getAttributeNames();
					while (attributeNames.hasMoreElements()) {
					    String name = attributeNames.nextElement();
					    
					    LOGGER.info("session keys pgredirect ######,{}", name);
					}
			        ShopOrder order = super.getSessionAttribute(Constants.ORDER, request);
			        
			        LOGGER.info("order  pgredirect ######,{}", order);  
		 
		    ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetails.getPaytmUrl());
	        TreeMap<String, String> parameters = new TreeMap<>();
	        paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
	        
	        parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
	        parameters.put("EMAIL", env.getProperty("paytm.email"));
	        parameters.put("ORDER_ID", orderId);
	        parameters.put("TXN_AMOUNT", transactionAmount);
	        parameters.put("CUST_ID", customerId);
	       
	        String checkSum = getCheckSum(parameters);
	        parameters.put("CHECKSUMHASH", checkSum);
	        modelAndView.addAllObjects(parameters);
	        return modelAndView;
	    }
	 
	 
	 @PostMapping(value = "/pgresponse")
	    public String getResponseRedirect(HttpServletRequest request, Model model) {
	 
	        Map<String, String[]> mapData = request.getParameterMap();
	        TreeMap<String, String> parameters = new TreeMap<String, String>();
	        mapData.forEach((key, val) -> parameters.put(key, val[0]));
	        String paytmChecksum = "";
	        if (mapData.containsKey("CHECKSUMHASH")) {
	            paytmChecksum = mapData.get("CHECKSUMHASH")[0];
	        }
	        String result;
	        String redirectURL = null;
	        StringBuilder updatedredirectURL = new StringBuilder();
	       

	        boolean isValideChecksum = false;
	        System.out.println("RESULT : "+parameters.toString());
	        try {
	            isValideChecksum = validateCheckSum(parameters, paytmChecksum);
	            if (isValideChecksum && parameters.containsKey("RESPCODE")) {
	                if (parameters.get("RESPCODE").equals("01")) {
	                    result = "Payment Successful";
	                   // redirectURL = "redirect:" + Constants.SHOP_URI + "/order/commitPayTMAuthorized";
	                    redirectURL = "redirect:/shop/order/confirmation.html";
	                    updatedredirectURL.append(redirectURL);
	                    Long orderID = Long.parseLong(parameters.get("ORDERID"));
	                    finalizeOrder(orderID,request,Locale.getDefault(),parameters);
//	                    append("/").append(parameters.get("BANKTXNID")).
//	                    append("/").append(parameters.get("ORDERID")).
//	                    append("/").append(parameters.get("PAYMENTMODE")).
//	                    append("/").append(parameters.get("RESPCODE")).
//	                    append("/").append(parameters.get("STATUS")).
//	                    append("/").append(parameters.get("TXNAMOUNT")).
//	                    append("/").append(parameters.get("TXNDATE")).
//	                    append("/").append(parameters.get("TXNID"));
	                } else {
	                    result = "Payment Failed";
	                    
	                    redirectURL = "redirect:" + Constants.SHOP_URI + "/order/failedPayment";
	                    updatedredirectURL.append(redirectURL).
	                    append("/").append(parameters.get("RESPCODE"));
	                }
	            } else {
	                result = "Checksum mismatched";
	                
	                redirectURL = "redirect:" + Constants.SHOP_URI + "/order/failedPayment";
                    updatedredirectURL.append(redirectURL).
                    append("/").append(parameters.get("RESPCODE"));
	            }
	        } catch (Exception e) {
	            result = e.toString();
	        }
	        
	        HttpSession session = request.getSession();
	        String sessionId = session.getId();
		    LOGGER.info("SESSIONID  pgresponse ######,{}", sessionId);
		    
			Enumeration<String> attributeNames = session.getAttributeNames();
			while (attributeNames.hasMoreElements()) {
			    String name = attributeNames.nextElement();
			    
			    LOGGER.info("session keys pgresponse ######,{}", name);
			}
	        ShopOrder order = super.getSessionAttribute(Constants.ORDER, request);
	        
	        LOGGER.info("order  pgresponse ######,{}", order);
	        model.addAttribute("result",result);
	        parameters.remove("CHECKSUMHASH");
	        model.addAttribute("parameters",parameters);
	        return updatedredirectURL.toString();
	    }

	    private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
	        return CheckSumServiceHelper.getCheckSumServiceHelper().verifycheckSum(paytmDetails.getMerchantKey(),
	                parameters, paytmChecksum);
	    }


	private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
		return CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(paytmDetails.getMerchantKey(), parameters);
	}
	

	private Order finalizeOrder(Long orderId, HttpServletRequest request, Locale locale,Map<String,String> payTMtransactionDetails) throws Exception, ServiceException {
		
		LOGGER.info("Entering finalizeOrder");
		
		Customer modelCustomer = null;
		Order modelOrder = null;

		try {
			modelOrder = orderService.getById(orderId);
			modelCustomer = customerService.getById(modelOrder.getCustomerId());

			modelOrder.setPaymentType(PaymentType.PAYTM);
			modelOrder.setStatus(OrderStatus.PROCESSED);

			orderService.save(modelOrder);

			MerchantStore store = (MerchantStore) request.getAttribute(Constants.MERCHANT_STORE);
			Language language = (Language) request.getAttribute("LANGUAGE");

			// Call Finder Methods to get access to Entitie Orders & Customer
			LOGGER.debug("About to save transaction");
			Transaction payTMTransaction = new  Transaction();
			payTMTransaction.setOrder(modelOrder);
	      
	        	if(payTMtransactionDetails != null) {
	        		payTMTransaction.getTransactionDetails().put("BANKTXNID",payTMtransactionDetails.get("BANKTXNID"));
	        		payTMTransaction.getTransactionDetails().put("ORDERID",payTMtransactionDetails.get("ORDERID"));
	        		payTMTransaction.getTransactionDetails().put("PAYMENTMODE",payTMtransactionDetails.get("PAYMENTMODE"));
	        		payTMTransaction.getTransactionDetails().put("RESPCODE",payTMtransactionDetails.get("RESPCODE"));
	        		payTMTransaction.getTransactionDetails().put("STATUS",payTMtransactionDetails.get("STATUS"));
	        		payTMTransaction.getTransactionDetails().put("TXNAMOUNT",payTMtransactionDetails.get("TXNAMOUNT"));
	        		payTMTransaction.getTransactionDetails().put("TXNDATE",payTMtransactionDetails.get("TXNDATE"));
	        		payTMTransaction.getTransactionDetails().put("TXNID",payTMtransactionDetails.get("TXNID"));
	        		
	        		payTMTransaction.setAmount(new BigDecimal(payTMtransactionDetails.get("TXNAMOUNT")));
	        		payTMTransaction.setPaymentType(PaymentType.PAYTM);
	        		payTMTransaction.setTransactionType(TransactionType.AUTHORIZECAPTURE);
	        		payTMTransaction.setTransactionDate(new Date());
	        		
	        		//parse JSON string
	        		String transactionDetails = payTMTransaction.toJSONString();
	        		if(!StringUtils.isBlank(transactionDetails)) {
	        			payTMTransaction.setDetails(transactionDetails);
	        		}
	        		
	        	}
			
	       	transactionService.save(payTMTransaction);

			// save order id in session
			super.setSessionAttribute(Constants.ORDER_ID, orderId, request);
			// set a unique token for confirmation
			super.setSessionAttribute(Constants.ORDER_ID_TOKEN, orderId, request);
			LOGGER.debug("Transaction ended and order saved");

			LOGGER.debug("Refresh customer");

			// refresh customer --

			// send order confirmation email to customer
			emailTemplatesUtils.sendOrderEmail(modelCustomer.getEmailAddress(), modelCustomer, modelOrder, locale,
					language, store, request.getContextPath());

			// send order confirmation email to merchant
			emailTemplatesUtils.sendOrderEmail(store.getStoreEmailAddress(), modelCustomer, modelOrder, locale,
					language, store, request.getContextPath());

		} catch (Exception e) {
			LOGGER.error("Error while post processing order", e);
		}


		
		
        return modelOrder;
	
	
		
	
}
	
	
	


}
