package com.salesmanager.core.business.modules.integration.payment.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.ValidationError;
import com.braintreegateway.Result;
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

public class BraintreePayment implements PaymentModule {

	@Override
	public void validateModuleConfiguration(IntegrationConfiguration integrationConfiguration, MerchantStore store)
			throws IntegrationException {
		List<String> errorFields = null;
		
		
		Map<String,String> keys = integrationConfiguration.getIntegrationKeys();
		
		//validate integrationKeys['merchant_id']
		if(keys==null || StringUtils.isBlank(keys.get("merchant_id"))) {
			errorFields = new ArrayList<String>();
			errorFields.add("merchant_id");
		}
		
		//validate integrationKeys['public_key']
		if(keys==null || StringUtils.isBlank(keys.get("public_key"))) {
			if(errorFields==null) {
				errorFields = new ArrayList<String>();
			}
			errorFields.add("public_key");
		}
		
		//validate integrationKeys['private_key']
		if(keys==null || StringUtils.isBlank(keys.get("private_key"))) {
			if(errorFields==null) {
				errorFields = new ArrayList<String>();
			}
			errorFields.add("private_key");
		}
		
		//validate integrationKeys['tokenization_key']
		if(keys==null || StringUtils.isBlank(keys.get("tokenization_key"))) {
			if(errorFields==null) {
				errorFields = new ArrayList<String>();
			}
			errorFields.add("tokenization_key");
		}
		
		
		if(errorFields!=null) {
			IntegrationException ex = new IntegrationException(IntegrationException.ERROR_VALIDATION_SAVE);
			ex.setErrorFields(errorFields);
			throw ex;
			
		}

	}

	@Override
	public Transaction initTransaction(MerchantStore store, Customer customer, BigDecimal amount, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module) throws IntegrationException {

		Validate.notNull(configuration,"Configuration cannot be null");
		
		String merchantId = configuration.getIntegrationKeys().get("merchant_id");
		String publicKey = configuration.getIntegrationKeys().get("public_key");
		String privateKey = configuration.getIntegrationKeys().get("private_key");
		
		Validate.notNull(merchantId,"merchant_id cannot be null");
		Validate.notNull(publicKey,"public_key cannot be null");
		Validate.notNull(privateKey,"private_key cannot be null");
		
		Environment environment= Environment.PRODUCTION;
		if (configuration.getEnvironment().equals("TEST")) {// sandbox
			environment= Environment.SANDBOX;
		}
		
	    BraintreeGateway gateway = new BraintreeGateway(
	    		   environment,
	    		   merchantId,
	    		   publicKey,
	    		   privateKey
				);
		
		String clientToken = gateway.clientToken().generate();

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDetails(clientToken);
		transaction.setPaymentType(payment.getPaymentType());
		transaction.setTransactionDate(new Date());
		transaction.setTransactionType(payment.getTransactionType());
		
		return transaction;
	}

	@Override
	public Transaction authorize(MerchantStore store, Customer customer, List<ShoppingCartItem> items,
			BigDecimal amount, Payment payment, IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {


		Validate.notNull(configuration,"Configuration cannot be null");
		
		String merchantId = configuration.getIntegrationKeys().get("merchant_id");
		String publicKey = configuration.getIntegrationKeys().get("public_key");
		String privateKey = configuration.getIntegrationKeys().get("private_key");
		
		Validate.notNull(merchantId,"merchant_id cannot be null");
		Validate.notNull(publicKey,"public_key cannot be null");
		Validate.notNull(privateKey,"private_key cannot be null");
		
		String nonce = payment.getPaymentMetaData().get("paymentToken");
		
	    if(StringUtils.isBlank(nonce)) {
			IntegrationException te = new IntegrationException(
						"Can't process Braintree, missing authorization nounce");
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;
	    }
		
		Environment environment= Environment.PRODUCTION;
		if (configuration.getEnvironment().equals("TEST")) {// sandbox
			environment= Environment.SANDBOX;
		}
		
	    BraintreeGateway gateway = new BraintreeGateway(
	    		   environment,
	    		   merchantId,
	    		   publicKey,
	    		   privateKey
				);
	    
	   

        TransactionRequest request = new TransactionRequest()
            .amount(amount)
            .paymentMethodNonce(nonce);

        Result<com.braintreegateway.Transaction> result = gateway.transaction().sale(request);

        String authorizationId = null;
        
        if (result.isSuccess()) {
        	com.braintreegateway.Transaction transaction = result.getTarget();
        	authorizationId  = transaction.getId();
        } else if (result.getTransaction() != null) {
        	com.braintreegateway.Transaction transaction = result.getTransaction();
        	authorizationId = transaction.getAuthorizedTransactionId();
        } else {
            String errorString = "";
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
               errorString += "Error: " + error.getCode() + ": " + error.getMessage() + "\n";
            }
            
			IntegrationException te = new IntegrationException(
					"Can't process Braintree authorization " + errorString);
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;

        }
        
        if(StringUtils.isBlank(authorizationId)) {
			IntegrationException te = new IntegrationException(
					"Can't process Braintree, missing authorizationId");
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;
        }
        
        Transaction trx = new Transaction();
        trx.setAmount(amount);
        trx.setTransactionDate(new Date());
        trx.setTransactionType(TransactionType.AUTHORIZE);
        trx.setPaymentType(PaymentType.CREDITCARD);
        trx.getTransactionDetails().put("TRANSACTIONID", authorizationId);
        trx.getTransactionDetails().put("TRNAPPROVED", null);
        trx.getTransactionDetails().put("TRNORDERNUMBER", authorizationId);
        trx.getTransactionDetails().put("MESSAGETEXT", null);
        
        return trx;
		
	}

	@Override
	public Transaction capture(MerchantStore store, Customer customer, Order order, Transaction capturableTransaction,
			IntegrationConfiguration configuration, IntegrationModule module) throws IntegrationException {
		Validate.notNull(configuration,"Configuration cannot be null");
		
		String merchantId = configuration.getIntegrationKeys().get("merchant_id");
		String publicKey = configuration.getIntegrationKeys().get("public_key");
		String privateKey = configuration.getIntegrationKeys().get("private_key");
		
		Validate.notNull(merchantId,"merchant_id cannot be null");
		Validate.notNull(publicKey,"public_key cannot be null");
		Validate.notNull(privateKey,"private_key cannot be null");
		
		String auth = capturableTransaction.getTransactionDetails().get("TRANSACTIONID");
		
	    if(StringUtils.isBlank(auth)) {
			IntegrationException te = new IntegrationException(
						"Can't process Braintree, missing authorization id");
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;
	    }
		
		Environment environment= Environment.PRODUCTION;
		if (configuration.getEnvironment().equals("TEST")) {// sandbox
			environment= Environment.SANDBOX;
		}
		
	    BraintreeGateway gateway = new BraintreeGateway(
	    		   environment,
	    		   merchantId,
	    		   publicKey,
	    		   privateKey
				);
	    
	   
	    BigDecimal amount = order.getTotal();

        Result<com.braintreegateway.Transaction> result = gateway.transaction().submitForSettlement(auth, amount);

        String trxId = null;
        
        if (result.isSuccess()) {
        	com.braintreegateway.Transaction settledTransaction = result.getTarget();
        	trxId = settledTransaction.getId();
        } else {
            String errorString = "";
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
               errorString += "Error: " + error.getCode() + ": " + error.getMessage() + "\n";
            }
            
			IntegrationException te = new IntegrationException(
					"Can't process Braintree refund " + errorString);
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;

        }
        
        if(StringUtils.isBlank(trxId)) {
			IntegrationException te = new IntegrationException(
					"Can't process Braintree, missing original transaction");
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;
        }
        
        Transaction trx = new Transaction();
        trx.setAmount(amount);
        trx.setTransactionDate(new Date());
        trx.setTransactionType(TransactionType.CAPTURE);
        trx.setPaymentType(PaymentType.CREDITCARD);
        trx.getTransactionDetails().put("TRANSACTIONID", trxId);
        trx.getTransactionDetails().put("TRNAPPROVED", null);
        trx.getTransactionDetails().put("TRNORDERNUMBER", trxId);
        trx.getTransactionDetails().put("MESSAGETEXT", null);
        
        return trx;
		
		
	}

	@Override
	public Transaction authorizeAndCapture(MerchantStore store, Customer customer, List<ShoppingCartItem> items,
			BigDecimal amount, Payment payment, IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {

		Validate.notNull(configuration,"Configuration cannot be null");
		
		String merchantId = configuration.getIntegrationKeys().get("merchant_id");
		String publicKey = configuration.getIntegrationKeys().get("public_key");
		String privateKey = configuration.getIntegrationKeys().get("private_key");
		
		Validate.notNull(merchantId,"merchant_id cannot be null");
		Validate.notNull(publicKey,"public_key cannot be null");
		Validate.notNull(privateKey,"private_key cannot be null");
		
		String nonce = payment.getPaymentMetaData().get("nonce");
		
	    if(StringUtils.isBlank(nonce)) {
			IntegrationException te = new IntegrationException(
						"Can't process Braintree, missing authorization nounce");
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;
	    }
		
		Environment environment= Environment.PRODUCTION;
		if (configuration.getEnvironment().equals("TEST")) {// sandbox
			environment= Environment.SANDBOX;
		}
		
	    BraintreeGateway gateway = new BraintreeGateway(
	    		   environment,
	    		   merchantId,
	    		   publicKey,
	    		   privateKey
				);
	    
	   

        TransactionRequest request = new TransactionRequest()
            .amount(amount)
            .paymentMethodNonce(nonce);

        Result<com.braintreegateway.Transaction> result = gateway.transaction().sale(request);

        String trxId = null;
        
        if (result.isSuccess()) {
        	com.braintreegateway.Transaction transaction = result.getTarget();
        	trxId  = transaction.getId();
        } else {
            String errorString = "";
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
               errorString += "Error: " + error.getCode() + ": " + error.getMessage() + "\n";
            }
            
			IntegrationException te = new IntegrationException(
					"Can't process Braintree auth + capture " + errorString);
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;

        }
        
        if(StringUtils.isBlank(trxId)) {
			IntegrationException te = new IntegrationException(
					"Can't process Braintree, missing trxId");
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;
        }
        
        Transaction trx = new Transaction();
        trx.setAmount(amount);
        trx.setTransactionDate(new Date());
        trx.setTransactionType(TransactionType.AUTHORIZECAPTURE);
        trx.setPaymentType(PaymentType.CREDITCARD);
        trx.getTransactionDetails().put("TRANSACTIONID", trxId);
        trx.getTransactionDetails().put("TRNAPPROVED", null);
        trx.getTransactionDetails().put("TRNORDERNUMBER", result.getTransaction().getId());
        trx.getTransactionDetails().put("MESSAGETEXT", null);
        
        return trx;
		
		
	}

	@Override
	public Transaction refund(boolean partial, MerchantStore store, Transaction transaction, Order order,
			BigDecimal amount, IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {

		
		String merchantId = configuration.getIntegrationKeys().get("merchant_id");
		String publicKey = configuration.getIntegrationKeys().get("public_key");
		String privateKey = configuration.getIntegrationKeys().get("private_key");
		
		Validate.notNull(merchantId,"merchant_id cannot be null");
		Validate.notNull(publicKey,"public_key cannot be null");
		Validate.notNull(privateKey,"private_key cannot be null");
		
		String auth = transaction.getTransactionDetails().get("TRANSACTIONID");
		
	    if(StringUtils.isBlank(auth)) {
			IntegrationException te = new IntegrationException(
						"Can't process Braintree refund, missing transaction id");
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;
	    }
		
		Environment environment= Environment.PRODUCTION;
		if (configuration.getEnvironment().equals("TEST")) {// sandbox
			environment= Environment.SANDBOX;
		}
		
	    BraintreeGateway gateway = new BraintreeGateway(
	    		   environment,
	    		   merchantId,
	    		   publicKey,
	    		   privateKey
				);
	    

        Result<com.braintreegateway.Transaction> result = gateway.transaction().refund(auth, amount);

        String trxId = null;
        
        if (result.isSuccess()) {
        	com.braintreegateway.Transaction settledTransaction = result.getTarget();
        	trxId = settledTransaction.getId();
        } else {
            String errorString = "";
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
               errorString += "Error: " + error.getCode() + ": " + error.getMessage() + "\n";
            }
            
			IntegrationException te = new IntegrationException(
					"Can't process Braintree refund " + errorString);
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;

        }
        
        if(StringUtils.isBlank(trxId)) {
			IntegrationException te = new IntegrationException(
					"Can't process Braintree refund, missing original transaction");
			te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
			te.setMessageCode("message.payment.error");
			te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
			throw te;
        }
        
        Transaction trx = new Transaction();
        trx.setAmount(amount);
        trx.setTransactionDate(new Date());
        trx.setTransactionType(TransactionType.REFUND);
        trx.setPaymentType(PaymentType.CREDITCARD);
        trx.getTransactionDetails().put("TRANSACTIONID", trxId);
        trx.getTransactionDetails().put("TRNAPPROVED", null);
        trx.getTransactionDetails().put("TRNORDERNUMBER", trxId);
        trx.getTransactionDetails().put("MESSAGETEXT", null);
        
        return trx;
		
	}

}
