package com.salesmanager.core.business.modules.integration.payment.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.utils.ProductPriceUtils;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.PaymentType;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.payments.TransactionType;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.core.modules.integration.IntegrationException;
import com.salesmanager.core.modules.integration.payment.model.PaymentModule;
import com.stripe.Stripe;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCaptureParams;
import com.stripe.param.PaymentIntentCreateParams;

// import com.stripe.exception.APIConnectionException;

public class Stripe3Payment implements PaymentModule {

	private static final Logger LOGGER = LoggerFactory.getLogger(Stripe3Payment.class);

	@Inject
	private ProductPriceUtils productPriceUtils;

	private final static String AUTHORIZATION = "Authorization";
	private final static String TRANSACTION = "Transaction";


	@Override
	public Transaction initTransaction(MerchantStore store, Customer customer,
									   BigDecimal amount, Payment payment,
									   IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {


		String strAmount = String.valueOf(amount);
		strAmount = strAmount.replace(".","");

		Transaction transaction = new Transaction();
		try {


			String apiKey = configuration.getIntegrationKeys().get("secretKey");

			if (StringUtils.isBlank(apiKey)) {
				IntegrationException te = new IntegrationException(
						"Can't process Stripe, missing payment.metaData");
				te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
				te.setMessageCode("message.payment.error");
				te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
				throw te;
			}

			Stripe.apiKey = apiKey;

			PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
					.setCurrency(store.getCurrency().getCode())
					.setAmount(Long.parseLong(strAmount))
					.setCaptureMethod(PaymentIntentCreateParams.CaptureMethod.MANUAL)
					.build();

			// Create a PaymentIntent with the order amount and currency
			PaymentIntent intent = PaymentIntent.create(createParams);

			intent.getClientSecret();

			transaction.setAmount(amount);
			//transaction.setOrder(order);
			transaction.setTransactionDate(new Date());
			transaction.setTransactionType(TransactionType.AUTHORIZE);
			transaction.setPaymentType(PaymentType.CREDITCARD);
			transaction.getTransactionDetails().put("TRANSACTIONID", intent.getId());
			transaction.getTransactionDetails().put("TRNAPPROVED", intent.getStatus());
			transaction.getTransactionDetails().put("TRNORDERNUMBER", intent.getId());
			transaction.getTransactionDetails().put("INTENTSECRET", intent.getClientSecret());
			transaction.getTransactionDetails().put("MESSAGETEXT", null);

		} catch (Exception e) {
			throw buildException(e);
		}

		return transaction;
	}

	@Override
	public void validateModuleConfiguration(
			IntegrationConfiguration integrationConfiguration,
			MerchantStore store) throws IntegrationException {
		
		
		List<String> errorFields = null;
		
		
		Map<String,String> keys = integrationConfiguration.getIntegrationKeys();
		
		//validate integrationKeys['secretKey']
		if(keys==null || StringUtils.isBlank(keys.get("secretKey"))) {
			errorFields = new ArrayList<String>();
			errorFields.add("secretKey");
		}
		
		//validate integrationKeys['publishableKey']
		if(keys==null || StringUtils.isBlank(keys.get("publishableKey"))) {
			if(errorFields==null) {
				errorFields = new ArrayList<String>();
			}
			errorFields.add("publishableKey");
		}
		
		
		if(errorFields!=null) {
			IntegrationException ex = new IntegrationException(IntegrationException.ERROR_VALIDATION_SAVE);
			ex.setErrorFields(errorFields);
			throw ex;
		}
	}


	/* -----------------------------------------  */

	@Override
	public Transaction authorize(MerchantStore store, Customer customer,
			List<ShoppingCartItem> items, BigDecimal amount, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		
		Transaction transaction = new Transaction();
		try {
			

			String apiKey = configuration.getIntegrationKeys().get("secretKey");

			if(payment.getPaymentMetaData()==null || StringUtils.isBlank(apiKey)) {
				IntegrationException te = new IntegrationException(
						"Can't process Stripe, missing payment.metaData");
				te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
				te.setMessageCode("message.payment.error");
				te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
				throw te;
			}
			
			/**
			 * This is the PaymentIntent ID created on the Frontend, that we will now store.
			 */
			String token = payment.getPaymentMetaData().get("stripe_token");
			
			if(StringUtils.isBlank(token)) {
				IntegrationException te = new IntegrationException(
						"Can't process Stripe, missing stripe token");
				te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
				te.setMessageCode("message.payment.error");
				te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
				throw te;
			}

			Stripe.apiKey = apiKey;
			
			PaymentIntent paymentIntent = PaymentIntent.retrieve(
					token
			);
			
			transaction.setAmount(amount);
			//transaction.setOrder(order);
			transaction.setTransactionDate(new Date());
			transaction.setTransactionType(TransactionType.AUTHORIZE);
			transaction.setPaymentType(PaymentType.CREDITCARD);
			transaction.getTransactionDetails().put("TRANSACTIONID", token);
			transaction.getTransactionDetails().put("TRNAPPROVED", paymentIntent.getStatus());
			transaction.getTransactionDetails().put("TRNORDERNUMBER", paymentIntent.getId());  // <---- We store the PI id here
			transaction.getTransactionDetails().put("MESSAGETEXT", null);
			
		} catch (Exception e) {
			
			throw buildException(e);

		} 
		
		return transaction;

		
	}

	@Override
	public Transaction capture(MerchantStore store, Customer customer,
			Order order, Transaction capturableTransaction,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {

		Transaction transaction = new Transaction();
		try {

			String apiKey = configuration.getIntegrationKeys().get("secretKey");

			if(StringUtils.isBlank(apiKey)) {
				IntegrationException te = new IntegrationException(
						"Can't process Stripe, missing payment.metaData");
				te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
				te.setMessageCode("message.payment.error");
				te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
				throw te;
			}

			String chargeId = capturableTransaction.getTransactionDetails().get("TRNORDERNUMBER");       // <---- We retrieve the PI id here

			if(StringUtils.isBlank(chargeId)) {
				IntegrationException te = new IntegrationException(
						"Can't process Stripe capture, missing TRNORDERNUMBER");
				te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
				te.setMessageCode("message.payment.error");
				te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
				throw te;
			}

			String amnt = productPriceUtils.getAdminFormatedAmount(store, order.getTotal());
			String strAmount = String.valueOf(amnt);
			strAmount = strAmount.replace(".","");

			Stripe.apiKey = apiKey;


			PaymentIntent paymentIntent = PaymentIntent.retrieve(
					chargeId
			);

			PaymentIntentCaptureParams params =
					PaymentIntentCaptureParams.builder()
							.setAmountToCapture(Long.parseLong(strAmount))
							.setStatementDescriptor(
									store.getStorename().length() > 22 ?
											store.getStorename().substring(0, 22) :
											store.getStorename()
							)
					.build();

			paymentIntent = paymentIntent.capture(params);

			transaction.setAmount(order.getTotal());
			transaction.setOrder(order);
			transaction.setTransactionDate(new Date());
			transaction.setTransactionType(TransactionType.CAPTURE);
			transaction.setPaymentType(PaymentType.CREDITCARD);
			transaction.getTransactionDetails().put("TRANSACTIONID", capturableTransaction.getTransactionDetails().get("TRANSACTIONID"));
			transaction.getTransactionDetails().put("TRNAPPROVED", paymentIntent.getStatus());
			transaction.getTransactionDetails().put("TRNORDERNUMBER", paymentIntent.getId());
			transaction.getTransactionDetails().put("MESSAGETEXT", null);

			return transaction;

		} catch (Exception e) {
			throw buildException(e);
		}
	}

	@Override
	public Transaction authorizeAndCapture(MerchantStore store, Customer customer,
			List<ShoppingCartItem> items, BigDecimal amount, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		
		String apiKey = configuration.getIntegrationKeys().get("secretKey");

		if(payment.getPaymentMetaData()==null || StringUtils.isBlank(apiKey)) {
			IntegrationException te = new IntegrationException(
					"Can't process Stripe, missing payment.metaData");
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;
		}
		
		String token = payment.getPaymentMetaData().get("stripe_token");
		if(StringUtils.isBlank(token)) { //possibly from api
		  token = payment.getPaymentMetaData().get("paymentToken");
		}
		
		if(StringUtils.isBlank(token)) {
			IntegrationException te = new IntegrationException(
					"Can't process Stripe, missing stripe token");
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;
		}
		


		Transaction transaction = new Transaction();
		try {
			
			String amnt = productPriceUtils.getAdminFormatedAmount(store, amount);
			
			//stripe does not support floating point
			//so amnt * 100 or remove floating point
			//553.47 = 55347
			
		
			String strAmount = String.valueOf(amnt);
			strAmount = strAmount.replace(".","");
			
			/*Map<String, Object> chargeParams = new HashMap<String, Object>();
			chargeParams.put("amount", strAmount);
			chargeParams.put("capture", true);
			chargeParams.put("currency", store.getCurrency().getCode());
			chargeParams.put("source", token); // obtained with Stripe.js
			chargeParams.put("description", new StringBuilder().append(TRANSACTION).append(" - ").append(store.getStorename()).toString());
			*/

			Stripe.apiKey = apiKey;
			

			PaymentIntent paymentIntent = PaymentIntent.retrieve(
					token
		  	);

			PaymentIntentCaptureParams params =
					PaymentIntentCaptureParams.builder()
							.setAmountToCapture(Long.parseLong(strAmount))
							.setStatementDescriptor(
									store.getStorename().length() > 22 ?
											store.getStorename().substring(0, 22) :
											store.getStorename()
							)
					.build();

			paymentIntent = paymentIntent.capture(params);
	
			//Map<String,String> metadata = ch.getMetadata();
			
			
			transaction.setAmount(amount);
			//transaction.setOrder(order);
			transaction.setTransactionDate(new Date());
			transaction.setTransactionType(TransactionType.AUTHORIZECAPTURE);
			transaction.setPaymentType(PaymentType.CREDITCARD);
			transaction.getTransactionDetails().put("TRANSACTIONID", token);
			transaction.getTransactionDetails().put("TRNAPPROVED", paymentIntent.getStatus());
			transaction.getTransactionDetails().put("TRNORDERNUMBER", paymentIntent.getId());
			transaction.getTransactionDetails().put("MESSAGETEXT", null);
			
		} catch (Exception e) {

			e.printStackTrace();

			throw buildException(e);
	
		} 
		
		return transaction;  
		
	}

	@Override
	public Transaction refund(boolean partial, MerchantStore store, Transaction transaction,
			Order order, BigDecimal amount,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		
		
		
		String apiKey = configuration.getIntegrationKeys().get("secretKey");

		if(StringUtils.isBlank(apiKey)) {
			IntegrationException te = new IntegrationException(
					"Can't process Stripe, missing payment.metaData");
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;
		}

		try {
		

			String trnID = transaction.getTransactionDetails().get("TRNORDERNUMBER");
			
			String amnt = productPriceUtils.getAdminFormatedAmount(store, amount);
			
			Stripe.apiKey = apiKey;
			
			//stripe does not support floating point
			//so amnt * 100 or remove floating point
			//553.47 = 55347
			
			String strAmount = String.valueOf(amnt);
			strAmount = strAmount.replace(".","");

			PaymentIntent paymentIntent = PaymentIntent.retrieve(
					trnID
			);

			Map<String, Object> params = new HashMap<>();
			params.put("payment_intent", paymentIntent.getId());
			params.put("amount", strAmount);
			Refund re = Refund.create(params);

			transaction = new Transaction();
			transaction.setAmount(order.getTotal());
			transaction.setOrder(order);
			transaction.setTransactionDate(new Date());
			transaction.setTransactionType(TransactionType.CAPTURE);
			transaction.setPaymentType(PaymentType.CREDITCARD);
			transaction.getTransactionDetails().put("TRANSACTIONID", transaction.getTransactionDetails().get("TRANSACTIONID"));
			transaction.getTransactionDetails().put("TRNAPPROVED", re.getReason());
			transaction.getTransactionDetails().put("TRNORDERNUMBER", re.getId());
			transaction.getTransactionDetails().put("MESSAGETEXT", null);

			return transaction;

			
		} catch(Exception e) {
			
			throw buildException(e);

		} 
		
		
		
	}
	
	private IntegrationException buildException(Exception ex) {
		
		
	if(ex instanceof CardException) {
		  CardException e = (CardException)ex;
		  // Since it's a decline, CardException will be caught
		  //System.out.println("Status is: " + e.getCode());
		  //System.out.println("Message is: " + e.getMessage());
		  
		  
			/**
			 * 
				invalid_number 	The card number is not a valid credit card number.
				invalid_expiry_month 	The card's expiration month is invalid.
				invalid_expiry_year 	The card's expiration year is invalid.
				invalid_cvc 	The card's security code is invalid.
				incorrect_number 	The card number is incorrect.
				expired_card 	The card has expired.
				incorrect_cvc 	The card's security code is incorrect.
				incorrect_zip 	The card's zip code failed validation.
				card_declined 	The card was declined.
				missing 	There is no card on a customer that is being charged.
				processing_error 	An error occurred while processing the card.
				rate_limit 	An error occurred due to requests hitting the API too quickly. Please let us know if you're consistently running into this error.
			 */
		
			
			String declineCode = e.getDeclineCode();
			
			if("card_declined".equals(declineCode)) {
				IntegrationException te = new IntegrationException(
						"Can't process stripe message " + e.getMessage());
				te.setExceptionType(IntegrationException.EXCEPTION_PAYMENT_DECLINED);
				te.setMessageCode("message.payment.declined");
				te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
				return te;
			}
			
			if("invalid_number".equals(declineCode)) {
				IntegrationException te = new IntegrationException(
						"Can't process stripe message " + e.getMessage());
				te.setExceptionType(IntegrationException.EXCEPTION_VALIDATION);
				te.setMessageCode("messages.error.creditcard.number");
				te.setErrorCode(IntegrationException.EXCEPTION_VALIDATION);
				return te;
			}
			
			if("invalid_expiry_month".equals(declineCode)) {
				IntegrationException te = new IntegrationException(
						"Can't process stripe message " + e.getMessage());
				te.setExceptionType(IntegrationException.EXCEPTION_VALIDATION);
				te.setMessageCode("messages.error.creditcard.dateformat");
				te.setErrorCode(IntegrationException.EXCEPTION_VALIDATION);
				return te;
			}
			
			if("invalid_expiry_year".equals(declineCode)) {
				IntegrationException te = new IntegrationException(
						"Can't process stripe message " + e.getMessage());
				te.setExceptionType(IntegrationException.EXCEPTION_VALIDATION);
				te.setMessageCode("messages.error.creditcard.dateformat");
				te.setErrorCode(IntegrationException.EXCEPTION_VALIDATION);
				return te;
			}
			
			if("invalid_cvc".equals(declineCode)) {
				IntegrationException te = new IntegrationException(
						"Can't process stripe message " + e.getMessage());
				te.setExceptionType(IntegrationException.EXCEPTION_VALIDATION);
				te.setMessageCode("messages.error.creditcard.cvc");
				te.setErrorCode(IntegrationException.EXCEPTION_VALIDATION);
				return te;
			}
			
			if("incorrect_number".equals(declineCode)) {
				IntegrationException te = new IntegrationException(
						"Can't process stripe message " + e.getMessage());
				te.setExceptionType(IntegrationException.EXCEPTION_VALIDATION);
				te.setMessageCode("messages.error.creditcard.number");
				te.setErrorCode(IntegrationException.EXCEPTION_VALIDATION);
				return te;
			}
			
			if("incorrect_cvc".equals(declineCode)) {
				IntegrationException te = new IntegrationException(
						"Can't process stripe message " + e.getMessage());
				te.setExceptionType(IntegrationException.EXCEPTION_VALIDATION);
				te.setMessageCode("messages.error.creditcard.cvc");
				te.setErrorCode(IntegrationException.EXCEPTION_VALIDATION);
				return te;
			}
			
			//nothing good so create generic error
			IntegrationException te = new IntegrationException(
					"Can't process stripe card  " + e.getMessage());
			te.setExceptionType(IntegrationException.EXCEPTION_VALIDATION);
			te.setMessageCode("messages.error.creditcard.number");
			te.setErrorCode(IntegrationException.EXCEPTION_VALIDATION);
			return te;
		

		  
	} else if (ex instanceof InvalidRequestException) {
		LOGGER.error("InvalidRequest error with stripe", ex.getMessage());
		InvalidRequestException e =(InvalidRequestException)ex;
		IntegrationException te = new IntegrationException(
				"Can't process Stripe, missing invalid payment parameters");
		te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
		te.setMessageCode("messages.error.creditcard.number");
		te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
		return te;
		
	} else if (ex instanceof AuthenticationException) {
		LOGGER.error("Authentication error with stripe", ex.getMessage());
		AuthenticationException e = (AuthenticationException)ex;
		  // Authentication with Stripe's API failed
		  // (maybe you changed API keys recently)
		IntegrationException te = new IntegrationException(
				"Can't process Stripe, missing invalid payment parameters");
		te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
		te.setMessageCode("message.payment.error");
		te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
		return te;
		
	} /*else if (ex instanceof APIConnectionException) {
		LOGGER.error("API connection error with stripe", ex.getMessage());
		APIConnectionException e = (APIConnectionException)ex;
		  // Network communication with Stripe failed
		IntegrationException te = new IntegrationException(
				"Can't process Stripe, missing invalid payment parameters");
		te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
		te.setMessageCode("message.payment.error");
		te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
		return te;
	} */else if (ex instanceof StripeException) {
		LOGGER.error("Error with stripe", ex.getMessage());
		StripeException e = (StripeException)ex;
		  // Display a very generic error to the user, and maybe send
		  // yourself an email
		IntegrationException te = new IntegrationException(
				"Can't process Stripe authorize, missing invalid payment parameters");
		te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
		te.setMessageCode("message.payment.error");
		te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
		return te;
		
		

	} else if (ex instanceof Exception) {
		LOGGER.error("Stripe module error", ex.getMessage());
		if(ex instanceof IntegrationException) {
			return (IntegrationException)ex;
		} else {
			IntegrationException te = new IntegrationException(
					"Can't process Stripe authorize, exception", ex);
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			return te;
		}


	} else {
		LOGGER.error("Stripe module error", ex.getMessage());
		IntegrationException te = new IntegrationException(
				"Can't process Stripe authorize, exception", ex);
		te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
		te.setMessageCode("message.payment.error");
		te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
		return te;
	}

	}
	
	



}
