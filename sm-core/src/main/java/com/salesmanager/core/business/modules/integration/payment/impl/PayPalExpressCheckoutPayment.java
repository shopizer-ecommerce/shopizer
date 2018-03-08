package com.salesmanager.core.business.modules.integration.payment.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.utils.CoreConfiguration;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.OrderTotal;
import com.salesmanager.core.model.order.OrderTotalSummary;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.PaymentType;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.payments.TransactionType;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.core.modules.integration.IntegrationException;
import com.salesmanager.core.modules.integration.payment.model.PaymentModule;

import urn.ebay.api.PayPalAPI.DoCaptureReq;
import urn.ebay.api.PayPalAPI.DoCaptureRequestType;
import urn.ebay.api.PayPalAPI.DoCaptureResponseType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentRequestType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsReq;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsRequestType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.RefundTransactionReq;
import urn.ebay.api.PayPalAPI.RefundTransactionRequestType;
import urn.ebay.api.PayPalAPI.RefundTransactionResponseType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.CompleteCodeType;
import urn.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentInfoType;
import urn.ebay.apis.eBLBaseComponents.RefundType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

public class PayPalExpressCheckoutPayment implements PaymentModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PayPalExpressCheckoutPayment.class);
	
	
	@Inject
	private PricingService pricingService;
	
	@Inject
	private CoreConfiguration coreConfiguration;

	@Override
	public void validateModuleConfiguration(
			IntegrationConfiguration integrationConfiguration,
			MerchantStore store) throws IntegrationException {
		
		
		List<String> errorFields = null;
		
		//validate integrationKeys['account']
		Map<String,String> keys = integrationConfiguration.getIntegrationKeys();
		if(keys==null || StringUtils.isBlank(keys.get("api"))) {
			errorFields = new ArrayList<String>();
			errorFields.add("api");
		}
		
		if(keys==null || StringUtils.isBlank(keys.get("username"))) {
			if(errorFields==null) {
				errorFields = new ArrayList<String>();
			}
			errorFields.add("username");
		}
		
		if(keys==null || StringUtils.isBlank(keys.get("signature"))) {
			if(errorFields==null) {
				errorFields = new ArrayList<String>();
			}
			errorFields.add("signature");
		}
		

		if(errorFields!=null) {
			IntegrationException ex = new IntegrationException(IntegrationException.ERROR_VALIDATION_SAVE);
			ex.setErrorFields(errorFields);
			throw ex;
			
		}
		
		
	}

	@Override
	public Transaction initTransaction(MerchantStore store, Customer customer,
			BigDecimal amount, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		
			throw new IntegrationException("Not imlemented");
	}

	@Override
	public Transaction authorize(MerchantStore store, Customer customer,
			List<ShoppingCartItem> items, BigDecimal amount, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {

		
		com.salesmanager.core.model.payments.PaypalPayment paypalPayment = (com.salesmanager.core.model.payments.PaypalPayment)payment;
		Validate.notNull(paypalPayment.getPaymentToken(), "A paypal payment token is required to process this transaction");
		
		return processTransaction(store, customer, items, amount, paypalPayment, configuration, module);
		
		
	}

/*	@Override
	public Transaction capture(MerchantStore store, Customer customer,
			List<ShoppingCartItem> items, BigDecimal amount, Payment payment, Transaction transaction,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		
		com.salesmanager.core.business.payments.model.PaypalPayment paypalPayment = (com.salesmanager.core.business.payments.model.PaypalPayment)payment;
		Validate.notNull(paypalPayment.getPaymentToken(), "A paypal payment token is required to process this transaction");
		
		return processTransaction(store, customer, items, amount, paypalPayment, configuration, module);
		
	}*/
	
	public Transaction initPaypalTransaction(MerchantStore store,
			List<ShoppingCartItem> items, OrderTotalSummary summary, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		
			Validate.notNull(configuration, "Configuration must not be null");
			Validate.notNull(payment, "Payment must not be null");
			Validate.notNull(summary, "OrderTotalSummary must not be null");
		

		try {
			
			
			PaymentDetailsType paymentDetails = new PaymentDetailsType();
			if(configuration.getIntegrationKeys().get("transaction").equalsIgnoreCase(TransactionType.AUTHORIZECAPTURE.name())) {
				paymentDetails.setPaymentAction(urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType.SALE);
			} else {
				paymentDetails.setPaymentAction(urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType.AUTHORIZATION);
			}
			

			List<PaymentDetailsItemType> lineItems = new ArrayList<PaymentDetailsItemType>();
			
			for(ShoppingCartItem cartItem : items) {
			
				PaymentDetailsItemType item = new PaymentDetailsItemType();
				BasicAmountType amt = new BasicAmountType();
				amt.setCurrencyID(urn.ebay.apis.eBLBaseComponents.CurrencyCodeType.fromValue(payment.getCurrency().getCode()));
				amt.setValue(pricingService.getStringAmount(cartItem.getFinalPrice().getFinalPrice(), store));
				//itemsTotal = itemsTotal.add(cartItem.getSubTotal());
				int itemQuantity = cartItem.getQuantity();
				item.setQuantity(itemQuantity);
				item.setName(cartItem.getProduct().getProductDescription().getName());
				item.setAmount(amt);
				//System.out.println(pricingService.getStringAmount(cartItem.getSubTotal(), store));
				lineItems.add(item);
			
			}
			
			
			List<OrderTotal> orderTotals = summary.getTotals();
			BigDecimal tax = null;
			for(OrderTotal total : orderTotals) {
				
				if(total.getModule().equals(Constants.OT_SHIPPING_MODULE_CODE)) {
					BasicAmountType shipping = new BasicAmountType();
					shipping.setCurrencyID(urn.ebay.apis.eBLBaseComponents.CurrencyCodeType.fromValue(store.getCurrency().getCode()));
					shipping.setValue(pricingService.getStringAmount(total.getValue(), store));
					//System.out.println(pricingService.getStringAmount(total.getValue(), store));
					paymentDetails.setShippingTotal(shipping);
				}
				
				if(total.getModule().equals(Constants.OT_HANDLING_MODULE_CODE)) {
					BasicAmountType handling = new BasicAmountType();
					handling.setCurrencyID(urn.ebay.apis.eBLBaseComponents.CurrencyCodeType.fromValue(store.getCurrency().getCode()));
					handling.setValue(pricingService.getStringAmount(total.getValue(), store));
					//System.out.println(pricingService.getStringAmount(total.getValue(), store));
					paymentDetails.setHandlingTotal(handling);
				}
				
				if(total.getModule().equals(Constants.OT_TAX_MODULE_CODE)) {
					if(tax==null) {
						tax = new BigDecimal("0");
					}
					tax = tax.add(total.getValue());
				}
				
			}
			
			if(tax!=null) {
				BasicAmountType taxAmnt = new BasicAmountType();
				taxAmnt.setCurrencyID(urn.ebay.apis.eBLBaseComponents.CurrencyCodeType.fromValue(store.getCurrency().getCode()));
				taxAmnt.setValue(pricingService.getStringAmount(tax, store));
				//System.out.println(pricingService.getStringAmount(tax, store));
				paymentDetails.setTaxTotal(taxAmnt);
			}
			
			

			BasicAmountType itemTotal = new BasicAmountType();
			itemTotal.setCurrencyID(urn.ebay.apis.eBLBaseComponents.CurrencyCodeType.fromValue(store.getCurrency().getCode()));
			itemTotal.setValue(pricingService.getStringAmount(summary.getSubTotal(), store));
			paymentDetails.setItemTotal(itemTotal);
			
			paymentDetails.setPaymentDetailsItem(lineItems);
			BasicAmountType orderTotal = new BasicAmountType();
			orderTotal.setCurrencyID(urn.ebay.apis.eBLBaseComponents.CurrencyCodeType.fromValue(store.getCurrency().getCode()));
			orderTotal.setValue(pricingService.getStringAmount(summary.getTotal(), store));
			//System.out.println(pricingService.getStringAmount(itemsTotal, store));
			paymentDetails.setOrderTotal(orderTotal);
			List<PaymentDetailsType> paymentDetailsList = new ArrayList<PaymentDetailsType>();
			paymentDetailsList.add(paymentDetails);
			
			StringBuilder RETURN_URL = new StringBuilder().append(
					coreConfiguration.getProperty("SHOP_SCHEME", "http")).append("://")
					.append(store.getDomainName()).append("/")
					.append(coreConfiguration.getProperty("CONTEXT_PATH", "sm-shop"));
					


			SetExpressCheckoutRequestDetailsType setExpressCheckoutRequestDetails = new SetExpressCheckoutRequestDetailsType();
			String returnUrl = RETURN_URL.toString() + new StringBuilder().append(Constants.SHOP_URI).append("/paypal/checkout").append(coreConfiguration.getProperty("URL_EXTENSION", ".html")).append("/success").toString();
			String cancelUrl = RETURN_URL.toString() + new StringBuilder().append(Constants.SHOP_URI).append("/paypal/checkout").append(coreConfiguration.getProperty("URL_EXTENSION", ".html")).append("/cancel").toString();
			
			setExpressCheckoutRequestDetails.setReturnURL(returnUrl);
			setExpressCheckoutRequestDetails.setCancelURL(cancelUrl);

			
			setExpressCheckoutRequestDetails.setPaymentDetails(paymentDetailsList);

			SetExpressCheckoutRequestType setExpressCheckoutRequest = new SetExpressCheckoutRequestType(setExpressCheckoutRequestDetails);
			setExpressCheckoutRequest.setVersion("104.0");

			SetExpressCheckoutReq setExpressCheckoutReq = new SetExpressCheckoutReq();
			setExpressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutRequest);

			
			String mode = "sandbox";
			String env = configuration.getEnvironment();
			if(Constants.PRODUCTION_ENVIRONMENT.equals(env)) {
				mode = "production";
			}

			Map<String,String> configurationMap = new HashMap<String,String>();
			configurationMap.put("mode", mode);
			configurationMap.put("acct1.UserName", configuration.getIntegrationKeys().get("username"));
			configurationMap.put("acct1.Password", configuration.getIntegrationKeys().get("api"));
			configurationMap.put("acct1.Signature", configuration.getIntegrationKeys().get("signature"));
			
			PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);
			SetExpressCheckoutResponseType setExpressCheckoutResponse = service.setExpressCheckout(setExpressCheckoutReq);
			
			String token = setExpressCheckoutResponse.getToken();
			String correlationID = setExpressCheckoutResponse.getCorrelationID();
			String ack = setExpressCheckoutResponse.getAck().getValue();
			
			if(!"Success".equals(ack)) {
				LOGGER.error("Wrong value from init transaction " + ack);
				throw new IntegrationException("Wrong paypal ack from init transaction " + ack);
			}
			
			Transaction transaction = new Transaction();
			transaction.setAmount(summary.getTotal());
			//transaction.setOrder(order);
			transaction.setTransactionDate(new Date());
			transaction.setTransactionType(TransactionType.INIT);
			transaction.setPaymentType(PaymentType.PAYPAL);
			transaction.getTransactionDetails().put("TOKEN", token);
			transaction.getTransactionDetails().put("CORRELATION", correlationID);
			

			return transaction;
			
			//redirect user to 
			//https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=EC-5LL13394G30048922
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new IntegrationException(e);
		}
		
		
	}

	@Override
	public Transaction authorizeAndCapture(MerchantStore store,
			Customer customer, List<ShoppingCartItem> items, BigDecimal amount, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {

		com.salesmanager.core.model.payments.PaypalPayment paypalPayment = (com.salesmanager.core.model.payments.PaypalPayment)payment;
		Validate.notNull(paypalPayment.getPaymentToken(), "A paypal payment token is required to process this transaction");
		
		return processTransaction(store, customer, items, amount, paypalPayment, configuration, module);

		
	}

	@Override
	public Transaction refund(boolean partial, MerchantStore store,
			Transaction transaction, Order order, BigDecimal amount,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {


		try {
			
			
			
			Validate.notNull(transaction,"Transaction cannot be null");
			Validate.notNull((String)transaction.getTransactionDetails().get("TRANSACTIONID"), "Transaction details must contain a TRANSACTIONID");
			Validate.notNull(order,"Order must not be null");
			Validate.notNull(order.getCurrency(),"Order nust contain Currency object");
			
			String mode = "sandbox";
			String env = configuration.getEnvironment();
			if(Constants.PRODUCTION_ENVIRONMENT.equals(env)) {
				mode = "production";
			}

			
			 RefundTransactionRequestType refundTransactionRequest = new RefundTransactionRequestType();
			 refundTransactionRequest.setVersion("104.0");

			 RefundTransactionReq refundRequest = new RefundTransactionReq();
			 refundRequest.setRefundTransactionRequest(refundTransactionRequest);


			 Map<String,String> configurationMap = new HashMap<String,String>();
			 configurationMap.put("mode", mode);
			 configurationMap.put("acct1.UserName", configuration.getIntegrationKeys().get("username"));
			 configurationMap.put("acct1.Password", configuration.getIntegrationKeys().get("api"));
			 configurationMap.put("acct1.Signature", configuration.getIntegrationKeys().get("signature"));
				
			 
			 PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);
			 
			 

			 RefundType refundType = RefundType.FULL;
			 if(partial) {
				 refundType = RefundType.PARTIAL;
			 }
			 
			 refundTransactionRequest.setRefundType(refundType);
			 
			 BasicAmountType refundAmount = new BasicAmountType();
			 refundAmount.setValue(pricingService.getStringAmount(amount, store));
			 refundAmount.setCurrencyID(urn.ebay.apis.eBLBaseComponents.CurrencyCodeType.fromValue(order.getCurrency().getCode()));

			 refundTransactionRequest.setAmount(refundAmount);
			 refundTransactionRequest.setTransactionID(transaction.getTransactionDetails().get("TRANSACTIONID"));
			 
			 RefundTransactionResponseType refundTransactionResponse = service.refundTransaction(refundRequest);
			 
			 String refundAck = refundTransactionResponse.getAck().getValue();
			 
			 
			 if(!"Success".equals(refundAck)) {
				LOGGER.error("Wrong value from transaction commit " + refundAck);
				throw new IntegrationException(ServiceException.EXCEPTION_TRANSACTION_DECLINED,"Paypal refund transaction code [" + refundTransactionResponse.getErrors().get(0).getErrorCode() + "], message-> " + refundTransactionResponse.getErrors().get(0).getShortMessage());
			 }

			 
			 Transaction newTransaction = new Transaction();
			 newTransaction.setAmount(amount);
			 newTransaction.setTransactionDate(new Date());
			 newTransaction.setTransactionType(TransactionType.REFUND);
			 newTransaction.setPaymentType(PaymentType.PAYPAL);
			 newTransaction.getTransactionDetails().put("TRANSACTIONID", refundTransactionResponse.getRefundTransactionID());
			 transaction.getTransactionDetails().put("CORRELATION", refundTransactionResponse.getCorrelationID());
							
			

			return newTransaction;
			
			
		} catch(Exception e) {
			if(e instanceof IntegrationException) {
				throw (IntegrationException)e;
			} else {
				throw new IntegrationException(e);
			}
		}

		
		
		
		
		
	}
	
	private Transaction processTransaction(MerchantStore store,
			Customer customer, List<ShoppingCartItem> items, BigDecimal amount, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		
		
		com.salesmanager.core.model.payments.PaypalPayment paypalPayment = (com.salesmanager.core.model.payments.PaypalPayment)payment;
		
		try {
			
			
			String mode = "sandbox";
			String env = configuration.getEnvironment();
			if(Constants.PRODUCTION_ENVIRONMENT.equals(env)) {
				mode = "production";
			}
			
	  
			 //get token from url and return the user to generate a payerid
			   
			 GetExpressCheckoutDetailsRequestType getExpressCheckoutDetailsRequest = new GetExpressCheckoutDetailsRequestType(paypalPayment.getPaymentToken());
			 getExpressCheckoutDetailsRequest.setVersion("104.0");

			 GetExpressCheckoutDetailsReq getExpressCheckoutDetailsReq = new GetExpressCheckoutDetailsReq();
			 getExpressCheckoutDetailsReq.setGetExpressCheckoutDetailsRequest(getExpressCheckoutDetailsRequest);

			 Map<String,String> configurationMap = new HashMap<String,String>();
			 configurationMap.put("mode", mode);
			 configurationMap.put("acct1.UserName", configuration.getIntegrationKeys().get("username"));
			 configurationMap.put("acct1.Password", configuration.getIntegrationKeys().get("api"));
			 configurationMap.put("acct1.Signature", configuration.getIntegrationKeys().get("signature"));
				
			 
			 PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);
			 GetExpressCheckoutDetailsResponseType getExpressCheckoutDetailsResponse = service.getExpressCheckoutDetails(getExpressCheckoutDetailsReq);

				
			 String token = getExpressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().getToken();
			 String correlationID = getExpressCheckoutDetailsResponse.getCorrelationID();
			 String ack = getExpressCheckoutDetailsResponse.getAck().getValue();
			 String payerId = getExpressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().getPayerInfo().getPayerID();
			 
			//TOKEN=EC-9VT64354BS889423P&CHECKOUTSTATUS=PaymentActionNotInitiated&TIMESTAMP=2014-01-26T17:30:17Z&CORRELATIONID=84dfe1d0939cc&ACK=Success&VERSION=104.0&BUILD=9285531&EMAIL=csamson777-facilitator@yahoo.com&PAYERID=XURV79Z6URDV4&PAYERSTATUS=verified&BUSINESS=facilitator account's Test Store&FIRSTNAME=facilitator&LASTNAME=account&COUNTRYCODE=US&SHIPTONAME=facilitator account's Test Store&SHIPTOSTREET=1 Main St&SHIPTOCITY=San Jose&SHIPTOSTATE=CA&SHIPTOZIP=95131&SHIPTOCOUNTRYCODE=US&SHIPTOCOUNTRYNAME=United States&ADDRESSSTATUS=Confirmed&CURRENCYCODE=USD&AMT=1.00&ITEMAMT=1.00&SHIPPINGAMT=0.00&HANDLINGAMT=0.00&TAXAMT=0.00&INSURANCEAMT=0.00&SHIPDISCAMT=0.00&L_NAME0=item&L_QTY0=1&L_TAXAMT0=0.00&L_AMT0=1.00&L_ITEMWEIGHTVALUE0=   0.00000&L_ITEMLENGTHVALUE0=   0.00000&L_ITEMWIDTHVALUE0=   0.00000&L_ITEMHEIGHTVALUE0=   0.00000&PAYMENTREQUEST_0_CURRENCYCODE=USD&PAYMENTREQUEST_0_AMT=1.00&PAYMENTREQUEST_0_ITEMAMT=1.00&PAYMENTREQUEST_0_SHIPPINGAMT=0.00&PAYMENTREQUEST_0_HANDLINGAMT=0.00&PAYMENTREQUEST_0_TAXAMT=0.00&PAYMENTREQUEST_0_INSURANCEAMT=0.00&PAYMENTREQUEST_0_SHIPDISCAMT=0.00&PAYMENTREQUEST_0_INSURANCEOPTIONOFFERED=false&PAYMENTREQUEST_0_SHIPTONAME=facilitator account's Test Store&PAYMENTREQUEST_0_SHIPTOSTREET=1 Main St&PAYMENTREQUEST_0_SHIPTOCITY=San Jose&PAYMENTREQUEST_0_SHIPTOSTATE=CA&PAYMENTREQUEST_0_SHIPTOZIP=95131&PAYMENTREQUEST_0_SHIPTOCOUNTRYCODE=US&PAYMENTREQUEST_0_SHIPTOCOUNTRYNAME=United States&PAYMENTREQUEST_0_ADDRESSSTATUS=Confirmed&PAYMENTREQUEST_0_ADDRESSNORMALIZATIONSTATUS=None&L_PAYMENTREQUEST_0_NAME0=item&L_PAYMENTREQUEST_0_QTY0=1&L_PAYMENTREQUEST_0_TAXAMT0=0.00&L_PAYMENTREQUEST_0_AMT0=1.00&L_PAYMENTREQUEST_0_ITEMWEIGHTVALUE0=   0.00000&L_PAYMENTREQUEST_0_ITEMLENGTHVALUE0=   0.00000&L_PAYMENTREQUEST_0_ITEMWIDTHVALUE0=   0.00000&L_PAYMENTREQUEST_0_ITEMHEIGHTVALUE0=   0.00000&PAYMENTREQUESTINFO_0_ERRORCODE=0
				
			 if(!"Success".equals(ack)) {
				LOGGER.error("Wrong value from anthorize and capture transaction " + ack);
				throw new IntegrationException("Wrong paypal ack from init transaction " + ack);
			 }
			
 
			 PaymentDetailsType paymentDetail = new PaymentDetailsType();
			 /** IPN **/
			 //paymentDetail.setNotifyURL("http://replaceIpnUrl.com");
			 BasicAmountType orderTotal = new BasicAmountType();
			 orderTotal.setValue(pricingService.getStringAmount(amount, store));
			 orderTotal.setCurrencyID(urn.ebay.apis.eBLBaseComponents.CurrencyCodeType.fromValue(payment.getCurrency().getCode()));
			 paymentDetail.setOrderTotal(orderTotal);
			 paymentDetail.setButtonSource("Shopizer_Cart_AP");
			 /** sale or pre-auth **/
			 if(payment.getTransactionType().name().equals(TransactionType.AUTHORIZE.name())) {
				 paymentDetail.setPaymentAction(urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType.AUTHORIZATION);
			 } else {
				 paymentDetail.setPaymentAction(urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType.SALE);
			 }
			 
			 List<PaymentDetailsType> paymentDetails = new ArrayList<PaymentDetailsType>();
			 paymentDetails.add(paymentDetail);
								
			 DoExpressCheckoutPaymentRequestDetailsType doExpressCheckoutPaymentRequestDetails = new DoExpressCheckoutPaymentRequestDetailsType();
			 doExpressCheckoutPaymentRequestDetails.setToken(token);
			 doExpressCheckoutPaymentRequestDetails.setPayerID(payerId);
			 doExpressCheckoutPaymentRequestDetails.setPaymentDetails(paymentDetails);
				
			 DoExpressCheckoutPaymentRequestType doExpressCheckoutPaymentRequest = new DoExpressCheckoutPaymentRequestType(doExpressCheckoutPaymentRequestDetails);
			 doExpressCheckoutPaymentRequest.setVersion("104.0");
				
			 DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq = new DoExpressCheckoutPaymentReq();
			 doExpressCheckoutPaymentReq.setDoExpressCheckoutPaymentRequest(doExpressCheckoutPaymentRequest);
				

			 DoExpressCheckoutPaymentResponseType doExpressCheckoutPaymentResponse = service.doExpressCheckoutPayment(doExpressCheckoutPaymentReq); 
			 String commitAck = doExpressCheckoutPaymentResponse.getAck().getValue();
			 
			 
			 if(!"Success".equals(commitAck)) {
				LOGGER.error("Wrong value from transaction commit " + ack);
				throw new IntegrationException("Wrong paypal ack from init transaction " + ack);
			 }
			 
			 
			 List<PaymentInfoType> paymentInfoList =  doExpressCheckoutPaymentResponse.getDoExpressCheckoutPaymentResponseDetails().getPaymentInfo();
			 String transactionId = null;
			 
			 for(PaymentInfoType paymentInfo : paymentInfoList) {
				 transactionId = paymentInfo.getTransactionID();
			 }
			 
			 
			 
			 
			 //TOKEN=EC-90U93956LU4997256&SUCCESSPAGEREDIRECTREQUESTED=false&TIMESTAMP=2014-02-16T15:41:03Z&CORRELATIONID=39d4ab666c1d7&ACK=Success&VERSION=104.0&BUILD=9720069&INSURANCEOPTIONSELECTED=false&SHIPPINGOPTIONISDEFAULT=false&PAYMENTINFO_0_TRANSACTIONID=4YA742984J1256935&PAYMENTINFO_0_TRANSACTIONTYPE=expresscheckout&PAYMENTINFO_0_PAYMENTTYPE=instant&PAYMENTINFO_0_ORDERTIME=2014-02-16T15:41:03Z&PAYMENTINFO_0_AMT=1.00&PAYMENTINFO_0_FEEAMT=0.33&PAYMENTINFO_0_TAXAMT=0.00&PAYMENTINFO_0_CURRENCYCODE=USD&PAYMENTINFO_0_PAYMENTSTATUS=Completed&PAYMENTINFO_0_PENDINGREASON=None&PAYMENTINFO_0_REASONCODE=None&PAYMENTINFO_0_PROTECTIONELIGIBILITY=Eligible&PAYMENTINFO_0_PROTECTIONELIGIBILITYTYPE=ItemNotReceivedEligible,UnauthorizedPaymentEligible&PAYMENTINFO_0_SECUREMERCHANTACCOUNTID=TWLK53YN7GDM6&PAYMENTINFO_0_ERRORCODE=0&PAYMENTINFO_0_ACK=Success
			 
			 Transaction transaction = new Transaction();
			 transaction.setAmount(amount);
			 transaction.setTransactionDate(new Date());
			 transaction.setTransactionType(payment.getTransactionType());
			 transaction.setPaymentType(PaymentType.PAYPAL);
			 transaction.getTransactionDetails().put("TOKEN", token);
			 transaction.getTransactionDetails().put("PAYERID", payerId);
			 transaction.getTransactionDetails().put("TRANSACTIONID", transactionId);
			 transaction.getTransactionDetails().put("CORRELATION", correlationID);
				
			

			return transaction;
			
			
		} catch(Exception e) {
			throw new IntegrationException(e);
		}

		
		
	}

	@Override
	public Transaction capture(MerchantStore store, Customer customer,
			Order order, Transaction capturableTransaction,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {

		

		try {
			
			
			
			Validate.notNull(capturableTransaction,"Transaction cannot be null");
			Validate.notNull((String)capturableTransaction.getTransactionDetails().get("TRANSACTIONID"), "Transaction details must contain a TRANSACTIONID");
			Validate.notNull(order,"Order must not be null");
			Validate.notNull(order.getCurrency(),"Order nust contain Currency object");
			
			String mode = "sandbox";
			String env = configuration.getEnvironment();
			if(Constants.PRODUCTION_ENVIRONMENT.equals(env)) {
				mode = "production";
			}


			 Map<String,String> configurationMap = new HashMap<String,String>();
			 configurationMap.put("mode", mode);
			 configurationMap.put("acct1.UserName", configuration.getIntegrationKeys().get("username"));
			 configurationMap.put("acct1.Password", configuration.getIntegrationKeys().get("api"));
			 configurationMap.put("acct1.Signature", configuration.getIntegrationKeys().get("signature"));
				
			 
			 DoCaptureReq doCaptureReq = new DoCaptureReq();



				
				 BasicAmountType amount = new BasicAmountType();
				 amount.setValue(pricingService.getStringAmount(order.getTotal(), store));
				 amount.setCurrencyID(urn.ebay.apis.eBLBaseComponents.CurrencyCodeType.fromValue(order.getCurrency().getCode()));

				// DoCaptureRequest which takes mandatory params:
				// 
				// Authorization ID - Authorization identification number of the
				// payment you want to capture. This is the transaction ID
				DoCaptureRequestType doCaptureRequest = new DoCaptureRequestType(
						(String)capturableTransaction.getTransactionDetails().get("TRANSACTIONID"), amount, CompleteCodeType.NOTCOMPLETE);

				doCaptureReq.setDoCaptureRequest(doCaptureRequest);

				// ## Creating service wrapper object
				// Creating service wrapper object to make API call and loading
				// configuration file for your credentials and endpoint
				PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);
				
				DoCaptureResponseType doCaptureResponse = null;

					// ## Making API call
					// Invoke the appropriate method corresponding to API in service
					// wrapper object
					 doCaptureResponse = service
							.doCapture(doCaptureReq);


				// ## Accessing response parameters
				// You can access the response parameters using getter methods in
				// response object as shown below
				// ### Success values
				if(!"Success".equals(doCaptureResponse.getAck().getValue())) {
							LOGGER.error("Wrong value from transaction commit " + doCaptureResponse.getAck().getValue());
							throw new IntegrationException("Wrong paypal ack from refund transaction " + doCaptureResponse.getAck().getValue());
				}
				//if (doCaptureResponse.getAck().getValue()
				//		.equalsIgnoreCase("success")) {
					
					// Authorization identification number
					//logger.info("Authorization ID:"
					//		+ doCaptureResponse.getDoCaptureResponseDetails()
					//				.getAuthorizationID());
				//}
				// ### Error Values
				// Access error values from error list using getter methods
				//else {
				//	List<ErrorType> errorList = doCaptureResponse.getErrors();
				//	logger.severe("API Error Message : "
				//			+ errorList.get(0).getLongMessage());
				//}

				//String refundAck = refundTransactionResponse.getAck().getValue();
			 
			 


			 
			 Transaction newTransaction = new Transaction();
			 newTransaction.setAmount(order.getTotal());
			 newTransaction.setTransactionDate(new Date());
			 newTransaction.setTransactionType(TransactionType.CAPTURE);
			 newTransaction.setPaymentType(PaymentType.PAYPAL);
			 newTransaction.getTransactionDetails().put("AUTHORIZATIONID", doCaptureResponse.getDoCaptureResponseDetails().getAuthorizationID());
			 newTransaction.getTransactionDetails().put("TRANSACTIONID", (String)capturableTransaction.getTransactionDetails().get("TRANSACTIONID"));

			return newTransaction;
			
			
		} catch(Exception e) {
			throw new IntegrationException(e);
		}
		
		
	}

}
