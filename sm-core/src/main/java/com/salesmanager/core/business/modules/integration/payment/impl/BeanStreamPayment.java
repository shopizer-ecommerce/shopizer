package com.salesmanager.core.business.modules.integration.payment.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.services.system.MerchantLogService;
import com.salesmanager.core.business.utils.CreditCardUtils;
import com.salesmanager.core.business.utils.ProductPriceUtils;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.payments.CreditCardPayment;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.PaymentType;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.payments.TransactionType;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.core.model.system.MerchantLog;
import com.salesmanager.core.model.system.ModuleConfig;
import com.salesmanager.core.modules.integration.IntegrationException;
import com.salesmanager.core.modules.integration.payment.model.PaymentModule;


public class BeanStreamPayment implements PaymentModule {
	
	@Inject
	private ProductPriceUtils productPriceUtils;
	
	@Inject
	private MerchantLogService merchantLogService;
	

	
	private static final Logger LOGGER = LoggerFactory.getLogger(BeanStreamPayment.class);

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
		return processTransaction(store, customer, TransactionType.AUTHORIZE,
				amount,
				payment,
				configuration,
				module);
	}

	@Override
	public Transaction capture(MerchantStore store, Customer customer,
			Order order, Transaction capturableTransaction,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {


			try {
				

				
				//authorize a preauth 

		
				String trnID = capturableTransaction.getTransactionDetails().get("TRANSACTIONID");
				
				String amnt = productPriceUtils.getAdminFormatedAmount(store, order.getTotal());
				
				/**
				merchant_id=123456789&requestType=BACKEND
				&trnType=PAC&username=user1234&password=pass1234&trnID=1000
				2115 --> requires also adjId [not documented]
				**/
				
				StringBuilder messageString = new StringBuilder();
				messageString.append("requestType=BACKEND&");
				messageString.append("merchant_id=").append(configuration.getIntegrationKeys().get("merchantid")).append("&");
				messageString.append("trnType=").append("PAC").append("&");
				messageString.append("username=").append(configuration.getIntegrationKeys().get("username")).append("&");
				messageString.append("password=").append(configuration.getIntegrationKeys().get("password")).append("&");
				messageString.append("trnAmount=").append(amnt).append("&");
				messageString.append("adjId=").append(trnID).append("&");
				messageString.append("trnID=").append(trnID);
				
				LOGGER.debug("REQUEST SENT TO BEANSTREAM -> " + messageString.toString());
		


				Transaction response = this.sendTransaction(null, store, messageString.toString(), "PAC", TransactionType.CAPTURE, PaymentType.CREDITCARD, order.getTotal(), configuration, module);
				
				return response;
				
			} catch(Exception e) {
				
				if(e instanceof IntegrationException)
					throw (IntegrationException)e;
				throw new IntegrationException("Error while processing BeanStream transaction",e);
	
			} 

	}

	@Override
	public Transaction authorizeAndCapture(MerchantStore store, Customer customer,
			List<ShoppingCartItem> items, BigDecimal amount, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		return processTransaction(
				store,
				customer,
				TransactionType.AUTHORIZECAPTURE,
				amount,
				payment,
				configuration,
				module);
	}

	@Override
	public Transaction refund(boolean partial, MerchantStore store, Transaction transaction,
			Order order, BigDecimal amount,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {

		
		
		
		HttpURLConnection conn = null;
		
		try {
			
			
			boolean bSandbox = false;
			if (configuration.getEnvironment().equals("TEST")) {// sandbox
				bSandbox = true;
			}

			String server = "";


			ModuleConfig configs = module.getModuleConfigs().get("PROD");

			if (bSandbox == true) {
				configs = module.getModuleConfigs().get("TEST");
			} 
			
			if(configs==null) {
				throw new IntegrationException("Module not configured for TEST or PROD");
			}
			

			server = new StringBuffer().append(
					
					configs.getScheme()).append("://")
					.append(configs.getHost())
							.append(":")
							.append(configs.getPort())
							.append(configs.getUri()).toString();

			String trnID = transaction.getTransactionDetails().get("TRANSACTIONID");
			
			String amnt = productPriceUtils.getAdminFormatedAmount(store, amount);
			
			/**
			merchant_id=123456789&requestType=BACKEND
			&trnType=R&username=user1234&password=pass1234
			&trnOrderNumber=1234&trnAmount=1.00&adjId=1000
			2115
			**/
			StringBuilder messageString = new StringBuilder();



			messageString.append("requestType=BACKEND&");
			messageString.append("merchant_id=").append(configuration.getIntegrationKeys().get("merchantid")).append("&");
			messageString.append("trnType=").append("R").append("&");
			messageString.append("username=").append(configuration.getIntegrationKeys().get("username")).append("&");
			messageString.append("password=").append(configuration.getIntegrationKeys().get("password")).append("&");
			messageString.append("trnOrderNumber=").append(transaction.getTransactionDetails().get("TRNORDERNUMBER")).append("&");
			messageString.append("trnAmount=").append(amnt).append("&");
			messageString.append("adjId=").append(trnID);
			
			LOGGER.debug("REQUEST SENT TO BEANSTREAM -> " + messageString.toString());
	
			
		
			
			URL postURL = new URL(server.toString());
			conn = (HttpURLConnection) postURL.openConnection();
			


			
			Transaction response = this.sendTransaction(null, store, messageString.toString(), "R", TransactionType.REFUND, PaymentType.CREDITCARD, amount, configuration, module);
			
			return response;
			
		} catch(Exception e) {
			
			if(e instanceof IntegrationException)
				throw (IntegrationException)e;
			throw new IntegrationException("Error while processing BeanStream transaction",e);

		} finally {
			
			
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ignore) {
					// TODO: handle exception
				}
			}
		}
		
		
		
	}
	
	
	private Transaction sendTransaction(
			String orderNumber,
			MerchantStore store,
			String transaction, 
			String beanstreamType, 
			TransactionType transactionType,
			PaymentType paymentType,
			BigDecimal amount,
			IntegrationConfiguration configuration,
			IntegrationModule module
			) throws IntegrationException {
		
		String agent = "Mozilla/4.0";
		String respText = "";
		Map<String,String> nvp = null;
		DataOutputStream output = null;
		DataInputStream in = null;
		BufferedReader is = null;
		HttpURLConnection conn =null;
		try {
			
			//transaction = "requestType=BACKEND&merchant_id=300200260&trnType=P&username=carlito&password=shopizer001&orderNumber=caa71106-7e3f-4975-a657-a35904dc32a0&trnCardOwner=Carl Samson&trnCardNumber=5100000020002000&trnExpMonth=10&trnExpYear=14&trnCardCvd=123&trnAmount=77.01&ordName=Carl S&ordAddress1=358 Du Languedoc&ordCity=Victoria&ordProvince=BC&ordPostalCode=V8T2E7&ordCountry=CA&ordPhoneNumber=(444) 555-6666&ordEmailAddress=csamson777@yahoo.com";
			/**
			requestType=BACKEND&merchant_id=300200260
			&trnType=P
			&username=carlito&password=shopizer001
			&orderNumber=caa71106-7e3f-4975-a657-a35904dc32a0
			&trnCardOwner=Carl Samson
			&trnCardNumber=5100000020002000
			&trnExpMonth=10
			&trnExpYear=14
			&trnCardCvd=123
			&trnAmount=77.01
			&ordName=Carl S
			&ordAddress1=378 Du Languedoc
			&ordCity=Boucherville
			&ordProvince=QC
			&ordPostalCode=J4B8J9
			&ordCountry=CA
			&ordPhoneNumber=(444) 555-6666
			&ordEmailAddress=test@yahoo.com
			**/
			
			/**
			merchant_id=123456789&requestType=BACKEND
			&trnType=P&trnOrderNumber=1234TEST&trnAmount=5.00&trnCardOwner=Joe+Test
					&trnCardNumber=4030000010001234
					&trnExpMonth=10
					&trnExpYear=16
					&ordName=Joe+Test
					&ordAddress1=123+Test+Street
					&ordCity=Victoria
					&ordProvince=BC
					&ordCountry=CA
					&ordPostalCode=V8T2E7
					&ordPhoneNumber=5555555555
					&ordEmailAddress=joe%40testemail.com
			**/
			
			
			
			boolean bSandbox = false;
			if (configuration.getEnvironment().equals("TEST")) {// sandbox
				bSandbox = true;
			}

			String server = "";
			
			ModuleConfig configs = module.getModuleConfigs().get("PROD");

			if (bSandbox == true) {
				configs = module.getModuleConfigs().get("TEST");
			} 
			
			if(configs==null) {
				throw new IntegrationException("Module not configured for TEST or PROD");
			}
			

			server = new StringBuffer().append(
					
					configs.getScheme()).append("://")
					.append(configs.getHost())
							.append(":")
							.append(configs.getPort())
							.append(configs.getUri()).toString();
			
	
			
			URL postURL = new URL(server.toString());
			conn = (HttpURLConnection) postURL.openConnection();
			

			// Set connection parameters. We need to perform input and output,
			// so set both as true.
			conn.setDoInput(true);
			conn.setDoOutput(true);

			// Set the content type we are POSTing. We impersonate it as
			// encoded form data
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("User-Agent", agent);

			conn.setRequestProperty("Content-Length", String
					.valueOf(transaction.length()));
			conn.setRequestMethod("POST");

			// get the output stream to POST to.
			output = new DataOutputStream(conn.getOutputStream());
			output.writeBytes(transaction);
			output.flush();


			// Read input from the input stream.
			in = new DataInputStream(conn.getInputStream());
			int rc = conn.getResponseCode();
			if (rc != -1) {
				is = new BufferedReader(new InputStreamReader(conn
						.getInputStream()));
				String _line = null;
				while (((_line = is.readLine()) != null)) {
					respText = respText + _line;
				}
				
				LOGGER.debug("BeanStream response -> " + respText.trim());
				
				nvp = formatUrlResponse(respText.trim());
			} else {
				throw new IntegrationException("Invalid response from BeanStream, return code is " + rc);
			}
			
			//check
			//trnApproved=1&trnId=10003067&messageId=1&messageText=Approved&trnOrderNumber=E40089&authCode=TEST&errorType=N&errorFields=

			String transactionApproved = (String)nvp.get("TRNAPPROVED");
			String transactionId = (String)nvp.get("TRNID");
			String messageId = (String)nvp.get("MESSAGEID");
			String messageText = (String)nvp.get("MESSAGETEXT");
			String orderId = (String)nvp.get("TRNORDERNUMBER");
			String authCode = (String)nvp.get("AUTHCODE");
			String errorType = (String)nvp.get("ERRORTYPE");
			String errorFields = (String)nvp.get("ERRORFIELDS");
			if(!StringUtils.isBlank(orderNumber)) {
				nvp.put("INTERNALORDERID", orderNumber);
			}
			
			if(StringUtils.isBlank(transactionApproved)) {
				throw new IntegrationException("Required field transactionApproved missing from BeanStream response");
			}
			
			//errors
			if(transactionApproved.equals("0")) {

				merchantLogService.save(
						new MerchantLog(store,
						"Can't process BeanStream message "
								 + messageText + " return code id " + messageId));
	
				IntegrationException te = new IntegrationException(
						"Can't process BeanStream message " + messageText);
				te.setExceptionType(IntegrationException.EXCEPTION_PAYMENT_DECLINED);
				te.setMessageCode("message.payment.beanstream." + messageId);
				te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
				throw te;
			}
			
			//create transaction object

			//return parseResponse(type,transaction,respText,nvp,order);
			return this.parseResponse(transactionType, paymentType, nvp, amount);
			
			
		} catch(Exception e) {
			if(e instanceof IntegrationException) {
				throw (IntegrationException)e;
			}
			
			throw new IntegrationException("Error while processing BeanStream transaction",e);

		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception ignore) {
					// TODO: handle exception
				}
			}

			if (in != null) {
				try {
					in.close();
				} catch (Exception ignore) {
					// TODO: handle exception
				}
			}

			if (output != null) {
				try {
					output.close();
				} catch (Exception ignore) {
					// TODO: handle exception
				}
			}
			
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ignore) {
					// TODO: handle exception
				}
			}

		}

		
	}
	
	
	
	private Transaction processTransaction(MerchantStore store, Customer customer, TransactionType type,
			BigDecimal amount, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module) throws IntegrationException {
		

		
		
		
		boolean bSandbox = false;
		if (configuration.getEnvironment().equals("TEST")) {// sandbox
			bSandbox = true;
		}

		String server = "";

		ModuleConfig configs = module.getModuleConfigs().get("PROD");

		if (bSandbox == true) {
			configs = module.getModuleConfigs().get("TEST");
		} 
		
		if(configs==null) {
			throw new IntegrationException("Module not configured for TEST or PROD");
		}
		

		server = new StringBuffer().append(
				
				configs.getScheme()).append("://")
				.append(configs.getHost())
						.append(":")
						.append(configs.getPort())
						.append(configs.getUri()).toString();
		
		HttpURLConnection conn = null;
		
		try {
			
		String uniqueId = UUID.randomUUID().toString();//TODO
			
		String orderNumber = uniqueId;
		
		String amnt = productPriceUtils.getAdminFormatedAmount(store, amount);
		
		
		StringBuilder messageString = new StringBuilder();
		
		String transactionType = "P";
		if(type == TransactionType.AUTHORIZE) {
			transactionType = "PA";
		} else if(type == TransactionType.AUTHORIZECAPTURE) {
			transactionType = "P";
		} 
		
		CreditCardPayment creditCardPayment = (CreditCardPayment)payment;

		messageString.append("requestType=BACKEND&");
		messageString.append("merchant_id=").append(configuration.getIntegrationKeys().get("merchantid")).append("&");
		messageString.append("trnType=").append(transactionType).append("&");
		messageString.append("username=").append(configuration.getIntegrationKeys().get("username")).append("&");
		messageString.append("password=").append(configuration.getIntegrationKeys().get("password")).append("&");
		messageString.append("orderNumber=").append(orderNumber).append("&");
		messageString.append("trnCardOwner=").append(creditCardPayment.getCardOwner()).append("&");
		messageString.append("trnCardNumber=").append(creditCardPayment.getCreditCardNumber()).append("&");
		messageString.append("trnExpMonth=").append(creditCardPayment.getExpirationMonth()).append("&");
		messageString.append("trnExpYear=").append(creditCardPayment.getExpirationYear().substring(2)).append("&");
		messageString.append("trnCardCvd=").append(creditCardPayment.getCredidCardValidationNumber()).append("&");
		messageString.append("trnAmount=").append(amnt).append("&");
		
		StringBuilder nm = new StringBuilder();
		nm.append(customer.getBilling().getFirstName()).append(" ").append(customer.getBilling().getLastName());
		
		
		messageString.append("ordName=").append(nm.toString()).append("&");
		messageString.append("ordAddress1=").append(customer.getBilling().getAddress()).append("&");
		messageString.append("ordCity=").append(customer.getBilling().getCity()).append("&");
		
		String stateProvince = customer.getBilling().getState();
		if(customer.getBilling().getZone()!=null) {
			stateProvince = customer.getBilling().getZone().getCode();
		}
		
		String countryName = customer.getBilling().getCountry().getIsoCode();
		
		messageString.append("ordProvince=").append(stateProvince).append("&");
		messageString.append("ordPostalCode=").append(customer.getBilling().getPostalCode().replaceAll("\\s","")).append("&");
		messageString.append("ordCountry=").append(countryName).append("&");
		messageString.append("ordPhoneNumber=").append(customer.getBilling().getTelephone()).append("&");
		messageString.append("ordEmailAddress=").append(customer.getEmailAddress());
		
		
		
		
		/**
		 * 	purchase (P)
		 *  -----------
				REQUEST -> merchant_id=123456789&requestType=BACKEND&trnType=P&trnOrderNumber=1234TEST&trnAmount=5.00&trnCardOwner=Joe+Test&trnCardNumber=4030000010001234&trnExpMonth=10&trnExpYear=10&ordName=Joe+Test&ordAddress1=123+Test+Street&ordCity=Victoria&ordProvince=BC&ordCountry=CA&ordPostalCode=V8T2E7&ordPhoneNumber=5555555555&ordEmailAddress=joe%40testemail.com
				RESPONSE-> trnApproved=1&trnId=10003067&messageId=1&messageText=Approved&trnOrderNumber=E40089&authCode=TEST&errorType=N&errorFields=&responseType=T&trnAmount=10%2E00&trnDate=1%2F17%2F2008+11%3A36%3A34+AM&avsProcessed=0&avsId=0&avsResult=0&avsAddrMatch=0&avsPostalMatch=0&avsMessage=Address+Verification+not+performed+for+this+transaction%2E&rspCodeCav=0&rspCavResult=0&rspCodeCredit1=0&rspCodeCredit2=0&rspCodeCredit3=0&rspCodeCredit4=0&rspCodeAddr1=0&rspCodeAddr2=0&rspCodeAddr3=0&rspCodeAddr4=0&rspCodeDob=0&rspCustomerDec=&trnType=P&paymentMethod=CC&ref1=&ref2=&ref3=&ref4=&ref5=
		
			pre authorization (PA)
			----------------------

			Prior to processing a pre-authorization through the API, you must modify the transaction settings in your Beanstream merchant member area to allow for this transaction type.
			- Log in to the Beanstream online member area at www.beanstream.com/admin/sDefault.asp.
			- Navigate to administration - account admin - order settings in the left menu.
			Under the heading �Restrict Internet Transaction Processing Types,� select either of the last two options. The �Purchases or Pre-Authorization Only� option will allow you to process both types of transaction through your web interface. De-selecting the �Restrict Internet Transaction Processing Types� checkbox will allow you to process all types of transactions including returns, voids and pre-auth completions.
		
			capture (PAC) -> requires trnId
			-------------
		
			refund (R)
			-------------
				REQUEST -> merchant_id=123456789&requestType=BACKEND&trnType=R&username=user1234&password=pass1234&trnOrderNumber=1234&trnAmount=1.00&adjId=10002115
				RESPONSE-> trnApproved=1&trnId=10002118&messageId=1&messageText=Approved&trnOrderNumber=1234R&authCode=TEST&errorType=N&errorFields=&responseType=T&trnAmount=1%2E00&trnDate=8%2F17%2F2009+1%3A44%3A56+PM&avsProcessed=0&avsId=0&avsResult=0&avsAddrMatch=0&avsPostalMatch=0&avsMessage=Address+Verification+not+performed+for+this+transaction%2E&cardType=VI&trnType=R&paymentMethod=CC&ref1=&ref2=&ref3=&ref4=&ref5=
		

			//notes
			//On receipt of the transaction response, the merchant must display order amount, transaction ID number, bank authorization code (authCode), currency, date and �messageText� to the customer on a confirmation page.
		*/
		

		//String agent = "Mozilla/4.0";
		//String respText = "";
		//Map nvp = null;
		
		
		/** debug **/
		
		

			StringBuffer messageLogString = new StringBuffer();
			
			
			messageLogString.append("requestType=BACKEND&");
			messageLogString.append("merchant_id=").append(configuration.getIntegrationKeys().get("merchantid")).append("&");
			messageLogString.append("trnType=").append(type).append("&");
			messageLogString.append("orderNumber=").append(orderNumber).append("&");
			messageLogString.append("trnCardOwner=").append(creditCardPayment.getCardOwner()).append("&");
			messageLogString.append("trnCardNumber=").append(CreditCardUtils.maskCardNumber(creditCardPayment.getCreditCardNumber())).append("&");
			messageLogString.append("trnExpMonth=").append(creditCardPayment.getExpirationMonth()).append("&");
			messageLogString.append("trnExpYear=").append(creditCardPayment.getExpirationYear()).append("&");
			messageLogString.append("trnCardCvd=").append(creditCardPayment.getCredidCardValidationNumber()).append("&");
			messageLogString.append("trnAmount=").append(amnt).append("&");

			messageLogString.append("ordName=").append(nm.toString()).append("&");
			messageLogString.append("ordAddress1=").append(customer.getBilling().getAddress()).append("&");
			messageLogString.append("ordCity=").append(customer.getBilling().getCity()).append("&");
			

			
			messageLogString.append("ordProvince=").append(stateProvince).append("&");
			messageLogString.append("ordPostalCode=").append(customer.getBilling().getPostalCode()).append("&");
			messageLogString.append("ordCountry=").append(customer.getBilling().getCountry().getName()).append("&");
			messageLogString.append("ordPhoneNumber=").append(customer.getBilling().getTelephone()).append("&");
			messageLogString.append("ordEmailAddress=").append(customer.getEmailAddress());
			
			


			/** debug **/
	
	
			LOGGER.debug("REQUEST SENT TO BEANSTREAM -> " + messageLogString.toString());

			
			URL postURL = new URL(server.toString());
			conn = (HttpURLConnection) postURL.openConnection();
			

			
			Transaction response = this.sendTransaction(orderNumber, store, messageString.toString(), transactionType, type, payment.getPaymentType(), amount, configuration, module);
			
			return response;


			
		} catch(Exception e) {
			
			if(e instanceof IntegrationException)
				throw (IntegrationException)e;
			throw new IntegrationException("Error while processing BeanStream transaction",e);

		} finally {
			
			
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ignore) {}
			}
		}

	}
	
	
	
	private Transaction parseResponse(TransactionType transactionType,
			PaymentType paymentType, Map<String,String> nvp,
			BigDecimal amount) throws Exception {
		
		
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		//transaction.setOrder(order);
		transaction.setTransactionDate(new Date());
		transaction.setTransactionType(transactionType);
		transaction.setPaymentType(PaymentType.CREDITCARD);
		transaction.getTransactionDetails().put("TRANSACTIONID", (String)nvp.get("TRNID"));
		transaction.getTransactionDetails().put("TRNAPPROVED", (String)nvp.get("TRNAPPROVED"));
		transaction.getTransactionDetails().put("TRNORDERNUMBER", (String)nvp.get("TRNORDERNUMBER"));
		transaction.getTransactionDetails().put("MESSAGETEXT", (String)nvp.get("MESSAGETEXT"));
		if(nvp.get("INTERNALORDERID")!=null) {
			transaction.getTransactionDetails().put("INTERNALORDERID", (String)nvp.get("INTERNALORDERID"));
		}
		return transaction;
		
	}

	private Map formatUrlResponse(String payload) throws Exception {
		HashMap<String,String> nvp = new HashMap<String,String> ();
		StringTokenizer stTok = new StringTokenizer(payload, "&");
		while (stTok.hasMoreTokens()) {
			StringTokenizer stInternalTokenizer = new StringTokenizer(stTok
					.nextToken(), "=");
			if (stInternalTokenizer.countTokens() == 2) {
				String key = URLDecoder.decode(stInternalTokenizer.nextToken(),
						"UTF-8");
				String value = URLDecoder.decode(stInternalTokenizer
						.nextToken(), "UTF-8");
				nvp.put(key.toUpperCase(), value);
			}
		}
		return nvp;
	}

	@Override
	public void validateModuleConfiguration(
			IntegrationConfiguration integrationConfiguration,
			MerchantStore store) throws IntegrationException {
		
		
		List<String> errorFields = null;
		
		
		Map<String,String> keys = integrationConfiguration.getIntegrationKeys();
		
		//validate integrationKeys['merchantid']
		if(keys==null || StringUtils.isBlank(keys.get("merchantid"))) {
			errorFields = new ArrayList<String>();
			errorFields.add("merchantid");
		}
		
		//validate integrationKeys['username']
		if(keys==null || StringUtils.isBlank(keys.get("username"))) {
			if(errorFields==null) {
				errorFields = new ArrayList<String>();
			}
			errorFields.add("username");
		}
		
		
		//validate integrationKeys['password']
		if(keys==null || StringUtils.isBlank(keys.get("password"))) {
			if(errorFields==null) {
				errorFields = new ArrayList<String>();
			}
			errorFields.add("password");
		}


		
		if(errorFields!=null) {
			IntegrationException ex = new IntegrationException(IntegrationException.ERROR_VALIDATION_SAVE);
			ex.setErrorFields(errorFields);
			throw ex;
			
		}
		
		
		
	}



}
