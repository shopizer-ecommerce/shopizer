package com.salesmanager.core.business.modules.integration.payment.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
//import com.paypal.core.rest.OAuthTokenCredential;
//import com.paypal.core.rest.PayPalRESTException;
import com.salesmanager.core.modules.integration.IntegrationException;
import com.salesmanager.core.modules.integration.payment.model.PaymentModule;


public class PayPalRestPayment implements PaymentModule {
	

	@Override
	public void validateModuleConfiguration(
			IntegrationConfiguration integrationConfiguration,
			MerchantStore store) throws IntegrationException {
		
		
		List<String> errorFields = null;
		
		//validate integrationKeys['account']
		Map<String,String> keys = integrationConfiguration.getIntegrationKeys();
		if(keys==null || StringUtils.isBlank(keys.get("client"))) {
			errorFields = new ArrayList<String>();
			errorFields.add("client");
		}
		
		if(keys==null || StringUtils.isBlank(keys.get("secret"))) {
			if(errorFields==null) {
				errorFields = new ArrayList<String>();
			}
			errorFields.add("secret");
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction authorize(MerchantStore store, Customer customer,
			List<ShoppingCartItem> items, BigDecimal amount, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		
		return null;

/*		
		// ###AccessToken
		// Retrieve the access token from
		// OAuthTokenCredential by passing in
		// ClientID and ClientSecret
		APIContext apiContext = null;
		String accessToken = null;
		
		try {
			
			String clientID = configuration.getIntegrationKeys().get("client");
			String secret = configuration.getIntegrationKeys().get("secret");
			
			accessToken = getAccessToken(clientID, secret);

			// ### Api Context
			// Pass in a `ApiContext` object to authenticate
			// the call and to send a unique request id
			// (that ensures idempotency). The SDK generates
			// a request id if you do not pass one explicitly.
			apiContext = new APIContext(accessToken);
			// Use this variant if you want to pass in a request id
			// that is meaningful in your application, ideally
			// a order id.
			
			 * String requestId = Long.toString(System.nanoTime(); APIContext
			 * apiContext = new APIContext(accessToken, requestId ));
			 

			// ###Authorization
			// Retrieve an Authorization Id
			// by making a Payment with intent
			// as 'authorize' and parsing through
			// the Payment object
			
			
			String authorizationID = null;

			// ###Details
			// Let's you specify details of a payment amount.
			//Details details = new Details();
			//details.setShipping("0.03");
			//details.setSubtotal("107.41");
			//details.setTax("0.03");

			// ###Amount
			// Let's you specify a payment amount.
			
			String sAmount = productPriceUtils.getAdminFormatedAmount(store, amount);
			
			
			Amount amnt = new Amount();
			amnt.setCurrency(store.getCurrency().getCode());
			amnt.setTotal(sAmount);
			//amnt.setDetails(details);

			// ###Transaction
			// A transaction defines the contract of a
			// payment - what is the payment for and who
			// is fulfilling it. Transaction is created with
			// a `Payee` and `Amount` types
			com.paypal.api.payments.Transaction transaction = new com.paypal.api.payments.Transaction();
			transaction.setAmount(amnt);
			//TODO change description
			transaction.setDescription("This is the payment transaction description.");

			// The Payment creation API requires a list of
			// Transaction; add the created `Transaction`
			// to a List
			List<com.paypal.api.payments.Transaction> transactions = new ArrayList<com.paypal.api.payments.Transaction>();
			transactions.add(transaction);

			// ###Payer
			// A resource representing a Payer that funds a payment
			// Payment Method
			// as 'paypal'
			Payer payer = new Payer();
			payer.setPaymentMethod("paypal");

			// ###Payment
			// A Payment Resource; create one using
			// the above types and intent as 'sale'
			com.paypal.api.payments.Payment ppayment = new com.paypal.api.payments.Payment();
			ppayment.setIntent("sale");
			ppayment.setPayer(payer);
			ppayment.setTransactions(transactions);

			// ###Redirect URLs
			RedirectUrls redirectUrls = new RedirectUrls();
			String guid = UUID.randomUUID().toString().replaceAll("-", "");
			redirectUrls.setCancelUrl(req.getScheme() + "://"
					+ req.getServerName() + ":" + req.getServerPort()
					+ req.getContextPath() + "/paymentwithpaypal?guid=" + guid);
			redirectUrls.setReturnUrl(req.getScheme() + "://"
					+ req.getServerName() + ":" + req.getServerPort()
					+ req.getContextPath() + "/paymentwithpaypal?guid=" + guid);
			payment.setRedirectUrls(redirectUrls);

			// Create a payment by posting to the APIService
			// using a valid AccessToken
			// The return object contains the status;
			try {
				Payment createdPayment = payment.create(apiContext);
				LOGGER.info("Created payment with id = "
						+ createdPayment.getId() + " and status = "
						+ createdPayment.getState());
				// ###Payment Approval Url
				Iterator<Links> links = createdPayment.getLinks().iterator();
				while (links.hasNext()) {
					Links link = links.next();
					if (link.getRel().equalsIgnoreCase("approval_url")) {
						req.setAttribute("redirectURL", link.getHref());
					}
				}
				req.setAttribute("response", Payment.getLastResponse());
				map.put(guid, createdPayment.getId());
			} catch (PayPalRESTException e) {
				req.setAttribute("error", e.getMessage());
			}
		} catch (PayPalRESTException e) {
			throw new IntegrationException(e);
		}
*/		
		
		
	}

/*	@Override
	public Transaction capture(MerchantStore store, Customer customer,
			List<ShoppingCartItem> items, BigDecimal amount, Payment payment, Transaction transaction,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public Transaction authorizeAndCapture(MerchantStore store,
			Customer customer, List<ShoppingCartItem> items, BigDecimal amount, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction refund(boolean partial, MerchantStore store,
			Transaction transaction, Order order, BigDecimal amount,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getAccessToken(String clientID, String clientSecret) throws Exception {

		// ###AccessToken
		// Retrieve the access token from
		// OAuthTokenCredential by passing in
		// ClientID and ClientSecret

		return null;
		//return new OAuthTokenCredential(clientID, clientSecret)
		//		.getAccessToken();
	}

	@Override
	public Transaction capture(MerchantStore store, Customer customer,
			Order order, Transaction capturableTransaction,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		// TODO Auto-generated method stub
		return null;
	}

}
