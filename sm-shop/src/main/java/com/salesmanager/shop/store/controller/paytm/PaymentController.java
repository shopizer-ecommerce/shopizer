package com.salesmanager.shop.store.controller.paytm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.store.controller.AbstractController;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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
	
		
	@GetMapping("/")
	public String home() {
		return "home";
	}

	 @GetMapping(value = "/pgredirect/{CUST_ID}/{TXN_AMOUNT}/{ORDER_ID}")
	    public ModelAndView getRedirect(@PathVariable("CUST_ID") String customerId,
	    		@PathVariable("TXN_AMOUNT") String transactionAmount,
	    		@PathVariable("ORDER_ID") String orderId) throws Exception {

	  		    
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
	                    redirectURL = "redirect:" + Constants.SHOP_URI + "/order/commitPayTMAuthorized";
	                    updatedredirectURL.append(redirectURL).
	                    append("/").append(parameters.get("BANKTXNID")).
	                    append("/").append(parameters.get("ORDERID")).
	                    append("/").append(parameters.get("PAYMENTMODE")).
	                    append("/").append(parameters.get("RESPCODE")).
	                    append("/").append(parameters.get("STATUS")).
	                    append("/").append(parameters.get("TXNAMOUNT")).
	                    append("/").append(parameters.get("TXNDATE")).
	                    append("/").append(parameters.get("TXNID"));
	                } else {
	                    result = "Payment Failed";
	                    
	                    redirectURL = "redirect:" + Constants.SHOP_URI + "/order/checkout.html";
	                    updatedredirectURL.append(redirectURL);
	                }
	            } else {
	                result = "Checksum mismatched";
	            }
	        } catch (Exception e) {
	            result = e.toString();
	        }
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
	
	
	


}
