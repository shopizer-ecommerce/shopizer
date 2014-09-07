package com.salesmanager.web.admin.controller.orders;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.catalog.product.service.PricingService;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.service.CustomerService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.Order;
import com.salesmanager.core.business.order.model.orderstatus.OrderStatusHistory;
import com.salesmanager.core.business.order.service.OrderService;
import com.salesmanager.core.business.payments.model.Transaction;
import com.salesmanager.core.business.payments.service.PaymentService;
import com.salesmanager.core.business.payments.service.TransactionService;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.zone.service.ZoneService;
import com.salesmanager.core.business.system.service.EmailService;
import com.salesmanager.core.modules.integration.IntegrationException;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.entity.orders.Refund;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.DateUtil;
import com.salesmanager.web.utils.EmailTemplatesUtils;
import com.salesmanager.web.utils.LabelUtils;
import com.salesmanager.web.utils.LocaleUtils;

/**
 * Manage order details
 * @author Carl Samson
 *
 */
@Controller
public class OrderActionsControler {
	
private static final Logger LOGGER = LoggerFactory.getLogger(OrderActionsControler.class);
	
	@Autowired
	private LabelUtils messages;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	ZoneService zoneService;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PricingService pricingService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	EmailTemplatesUtils emailTemplatesUtils;
	
	
	@PreAuthorize("hasRole('ORDER')")
	@RequestMapping(value="/admin/orders/captureOrder.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String captureOrder(HttpServletRequest request, HttpServletResponse response, Locale locale) {


		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		String sId = request.getParameter("id");
		
		AjaxResponse resp = new AjaxResponse();

		try {
			Long id = Long.parseLong(sId);
			
			Order order = orderService.getById(id);
			
			if(order==null) {
				
				LOGGER.error("Order {0} does not exists", id);
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			if(order.getMerchant().getId().intValue()!=store.getId().intValue()) {
				
				LOGGER.error("Merchant store does not have order {0}",id);
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			Customer customer = customerService.getById(order.getCustomerId());
			
			if(customer==null) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				resp.setStatusMessage(messages.getMessage("message.notexist.customer", locale));
				return resp.toJSONString();
			}
			
			paymentService.processCapturePayment(order, customer, store);

			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		} catch (Exception e) {
			LOGGER.error("Error while getting category", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
	
	@PreAuthorize("hasRole('ORDER')")
	@RequestMapping(value="/admin/orders/refundOrder.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String refundOrder(@RequestBody Refund refund, HttpServletRequest request, HttpServletResponse response, Locale locale) {


		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		
		AjaxResponse resp = new AjaxResponse();

		BigDecimal submitedAmount = null;
		
		try {
			
			Order order = orderService.getById(refund.getOrderId());
			
			if(order==null) {
				
				LOGGER.error("Order {0} does not exists", refund.getOrderId());
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			if(order.getMerchant().getId().intValue()!=store.getId().intValue()) {
				
				LOGGER.error("Merchant store does not have order {0}",refund.getOrderId());
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
		
			//parse amount
			try {
				submitedAmount = new BigDecimal(refund.getAmount());
				if(submitedAmount.doubleValue()==0) {
					resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
					resp.setStatusMessage(messages.getMessage("message.invalid.amount", locale));
					return resp.toJSONString();
				}
				
			} catch (Exception e) {
				LOGGER.equals("invalid refundAmount " + refund.getAmount());
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
				
				
				BigDecimal orderTotal = order.getTotal();
				if(submitedAmount.doubleValue()>orderTotal.doubleValue()) {
					resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
					resp.setStatusMessage(messages.getMessage("message.invalid.amount", locale));
					return resp.toJSONString();
				}
				
				if(submitedAmount.doubleValue()<=0) {
					resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
					resp.setStatusMessage(messages.getMessage("message.invalid.amount", locale));
					return resp.toJSONString();
				}
				
				Customer customer = customerService.getById(order.getCustomerId());
				
				if(customer==null) {
					resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
					resp.setStatusMessage(messages.getMessage("message.notexist.customer", locale));
					return resp.toJSONString();
				}
				
	
				paymentService.processRefund(order, customer, store, submitedAmount);

				resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
		} catch (IntegrationException e) {
			LOGGER.error("Error while processing refund", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorString(e.getMessageCode());
		} catch (Exception e) {
			LOGGER.error("Error while processing refund", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
	
	@PreAuthorize("hasRole('ORDER')")
	@RequestMapping(value="/admin/orders/printInvoice.html", method=RequestMethod.GET, produces="application/json")
	public void printInvoice(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		String sId = request.getParameter("id");
		
		try {
			
		Long id = Long.parseLong(sId);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		Order order = orderService.getById(id);
		
		if(order.getMerchant().getId().intValue()!=store.getId().intValue()) {
			throw new Exception("Invalid order");
		}
		

		Language lang = store.getDefaultLanguage();
		
		

		ByteArrayOutputStream stream  = orderService.generateInvoice(store, order, lang);
		StringBuilder attachment = new StringBuilder();
		//attachment.append("attachment; filename=");
		attachment.append(order.getId());
		attachment.append(".pdf");
		
        response.setHeader("Content-disposition", "attachment;filename=" + attachment.toString());

        //Set the mime type for the response
        response.setContentType("application/pdf");

		
		response.getOutputStream().write(stream.toByteArray());
		
		response.flushBuffer();
			
			
		} catch(Exception e) {
			LOGGER.error("Error while printing a report",e);
		}
			
		
	}
	

	@SuppressWarnings("unchecked")
	@PreAuthorize("hasRole('ORDER')")
	@RequestMapping(value="/admin/orders/listTransactions.html", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String listTransactions(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String sId = request.getParameter("id");
		

		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		
		AjaxResponse resp = new AjaxResponse();
		
		if(sId==null) {
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			return resp.toJSONString();
		}


		
		try {
			
			Long id = Long.parseLong(sId);
			

			Order dbOrder = orderService.getById(id);

			if(dbOrder==null) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			
			if(dbOrder.getMerchant().getId().intValue()!=store.getId().intValue()) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			

			
			List<Transaction> transactions = transactionService.listTransactions(dbOrder);
			
			if(transactions!=null) {
				
				for(Transaction transaction : transactions) {
					@SuppressWarnings("rawtypes")
					Map entry = new HashMap();
					entry.put("transactionId", transaction.getId());
					entry.put("transactionDate", DateUtil.formatLongDate(transaction.getTransactionDate()));
					entry.put("transactionType", transaction.getTransactionType().name());
					entry.put("paymentType", transaction.getPaymentType().name());
					entry.put("transactionAmount", pricingService.getStringAmount(transaction.getAmount(), store));
					entry.put("transactionDetails", transaction.getTransactionDetails());
					resp.addDataEntry(entry);
				}
				
				
			}
			
			
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
			
		} catch(Exception e) {
			LOGGER.error("Cannot get transactions for order id " + sId, e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		return resp.toJSONString();
		
		
	}
	

	@PreAuthorize("hasRole('ORDER')")
	@RequestMapping(value="/admin/orders/sendInvoice.html", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String sendInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String sId = request.getParameter("id");
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		
		AjaxResponse resp = new AjaxResponse();
		
		if(sId==null) {
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			return resp.toJSONString();
		}


		
		try {
			
			Long id = Long.parseLong(sId);
			

			Order dbOrder = orderService.getById(id);

			if(dbOrder==null) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			
			if(dbOrder.getMerchant().getId().intValue()!=store.getId().intValue()) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			//get customer
			Customer customer = customerService.getById(dbOrder.getCustomerId());
			
			if(customer==null) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				resp.setErrorString("Customer does not exist");
				return resp.toJSONString();
			}
			
			Locale customerLocale = LocaleUtils.getLocale(customer.getDefaultLanguage());
			
			emailTemplatesUtils.sendOrderEmail(customer, dbOrder, customerLocale, customer.getDefaultLanguage(), store, request.getContextPath());
			
			
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
			
		} catch(Exception e) {
			LOGGER.error("Cannot get transactions for order id " + sId, e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		return resp.toJSONString();
		
		
	}
	
	

	@PreAuthorize("hasRole('ORDER')")
	@RequestMapping(value="/admin/orders/updateStatus.html", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String updateStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String sId = request.getParameter("id");
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		
		AjaxResponse resp = new AjaxResponse();
		
		if(sId==null) {
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			return resp.toJSONString();
		}


		
		try {
			
			Long id = Long.parseLong(sId);
			

			Order dbOrder = orderService.getById(id);

			if(dbOrder==null) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			
			if(dbOrder.getMerchant().getId().intValue()!=store.getId().intValue()) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			//get customer
			Customer customer = customerService.getById(dbOrder.getCustomerId());
			
			if(customer==null) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				resp.setErrorString("Customer does not exist");
				return resp.toJSONString();
			}
			
			Locale customerLocale = LocaleUtils.getLocale(customer.getDefaultLanguage());
			
			
			Set<OrderStatusHistory> orderStatus = dbOrder.getOrderHistory();
			OrderStatusHistory lastHistory = null;
			if(orderStatus!=null) {
				int count = 1;
				for(OrderStatusHistory history : orderStatus) {
					if(count==orderStatus.size()) {
						lastHistory = history;
						break;
					}
					count++;
				}
			}
			
			if(lastHistory==null) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				resp.setErrorString("No history");
				return resp.toJSONString();
			}
			emailTemplatesUtils.sendUpdateOrderStatusEmail(customer, dbOrder, lastHistory, store, customerLocale, request.getContextPath());

			
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
			
		} catch(Exception e) {
			LOGGER.error("Cannot get transactions for order id " + sId, e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorString(e.getMessage());
			resp.setErrorMessage(e);
		}
		
		return resp.toJSONString();
		
		
	}
	
	@PreAuthorize("hasRole('ORDER')")
	@RequestMapping(value="/admin/orders/sendDownloadEmail.html", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String sendDownloadEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String sId = request.getParameter("id");
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		
		AjaxResponse resp = new AjaxResponse();
		
		if(sId==null) {
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			return resp.toJSONString();
		}


		
		try {
			
			Long id = Long.parseLong(sId);
			

			Order dbOrder = orderService.getById(id);

			if(dbOrder==null) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			
			if(dbOrder.getMerchant().getId().intValue()!=store.getId().intValue()) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			//get customer
			Customer customer = customerService.getById(dbOrder.getCustomerId());
			
			if(customer==null) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				resp.setErrorString("Customer does not exist");
				return resp.toJSONString();
			}
			
			Locale customerLocale = LocaleUtils.getLocale(customer.getDefaultLanguage());
			
			
			emailTemplatesUtils.sendOrderDownloadEmail(customer, dbOrder, store, customerLocale, request.getContextPath());
			
			
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
			
		} catch(Exception e) {
			LOGGER.error("Cannot get transactions for order id " + sId, e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorString(e.getMessage());
			resp.setErrorMessage(e);
		}
		
		return resp.toJSONString();
		
		
	}

	

}
