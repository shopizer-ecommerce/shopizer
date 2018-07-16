package com.salesmanager.shop.store.api.v1.order;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.model.order.PersistableOrderApi;
import com.salesmanager.shop.model.order.ReadableOrder;
import com.salesmanager.shop.model.order.ReadableOrderList;
import com.salesmanager.shop.populator.customer.ReadableCustomerPopulator;
import com.salesmanager.shop.store.controller.order.facade.OrderFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;

@Controller
@RequestMapping("/api/v1")
public class OrderApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderApi.class);

	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private LanguageUtils languageUtils;
	
	@Inject
	private CustomerService customerService;
	
	@Inject
	private OrderFacade orderFacade;
	
	
	
	/**
	 * Get a list of orders for a given customer
	 * accept request parameter 'start' start index for count
	 * accept request parameter 'max' maximum number count, otherwise returns all
	 * Used for administrators
	 * @param store
	 * @param order
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value={"/private/orders/customers/{id}"}, method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public ReadableOrderList listOrders(
			@PathVariable final Long id, 
			@RequestParam(value = "start", required=false) Integer start,
			@RequestParam(value = "count", required=false) Integer count,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		MerchantStore merchantStore = storeFacade.getByCode(request);
		Language language = languageUtils.getRESTLanguage(request, merchantStore);
		
	
		Customer customer = customerService.getById(id);
		
		if(customer==null) {
			LOGGER.error("Customer is null for id " + id);
			response.sendError(404, "Customer is null for id " + id);
			return null;
		}
		
		if(start == null) {
			start = new Integer(0);
		}
		if(count == null) {
			count = new Integer(100);
		}
		
		ReadableCustomer readableCustomer = new ReadableCustomer();
		ReadableCustomerPopulator customerPopulator = new ReadableCustomerPopulator();
		customerPopulator.populate(customer, readableCustomer, merchantStore, language);
		

		ReadableOrderList returnList = orderFacade.getReadableOrderList(merchantStore, customer, start, count, language);

		List<ReadableOrder> orders = returnList.getOrders();
		
		if(!CollectionUtils.isEmpty(orders)) {
			for(ReadableOrder order : orders) {
				order.setCustomer(readableCustomer);
			}
		}
		

		return returnList;
	}
	
	/**
	 * List orders for authenticated customers
	 * @param start
	 * @param count
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value={"/auth/orders"}, method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public ReadableOrderList listOrders(
			@RequestParam(value = "start", required=false) Integer start,
			@RequestParam(value = "count", required=false) Integer count,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		MerchantStore merchantStore = storeFacade.getByCode(request);
		Language language = languageUtils.getRESTLanguage(request, merchantStore);
		
		
		Principal principal = request.getUserPrincipal();
		String userName = principal.getName();
		
		Customer customer = customerService.getByNick(userName);
		
		if(customer == null) {
			response.sendError(401, "Error while listing orders, customer not authorized");
			return null;
		}

		if(start == null) {
			start = new Integer(0);
		}
		if(count == null) {
			count = new Integer(100);
		}
		
		ReadableCustomer readableCustomer = new ReadableCustomer();
		ReadableCustomerPopulator customerPopulator = new ReadableCustomerPopulator();
		customerPopulator.populate(customer, readableCustomer, merchantStore, language);
		
		ReadableOrderList returnList = orderFacade.getReadableOrderList(merchantStore, customer, start, count, language);

		if(returnList==null) {
			returnList = new ReadableOrderList();
		}
		
		List<ReadableOrder> orders = returnList.getOrders();
		if(!CollectionUtils.isEmpty(orders)) {
			for(ReadableOrder order : orders) {
				order.setCustomer(readableCustomer);
			}
		}
		

		return returnList;
	}
	
	/**
	 * Get a given order by id
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value={"/auth/orders/{id}"}, method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public ReadableOrder getOrder(
			@PathVariable final Long id, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		MerchantStore merchantStore = storeFacade.getByCode(request);
		Language language = languageUtils.getRESTLanguage(request, merchantStore);
	
		Principal principal = request.getUserPrincipal();
		String userName = principal.getName();
		
		Customer customer = customerService.getByNick(userName);
		
		if(customer == null) {
			response.sendError(401, "Error while performing checkout customer not authorized");
			return null;
		}
	
		ReadableOrder order = orderFacade.getReadableOrder(id, merchantStore, language);
		
		if(order==null) {
			LOGGER.error("Order is null for id " + id);
			response.sendError(404, "Order is null for id " + id);
			return null;
		}
		
		if(order.getCustomer()==null) {
			LOGGER.error("Order is null for customer " + principal);
			response.sendError(404, "Order is null for customer " + principal);
			return null;
		}
		
		if(order.getCustomer().getId()!=null && order.getCustomer().getId().longValue()!=customer.getId().longValue()) {
			LOGGER.error("Order is null for customer " + principal);
			response.sendError(404, "Order is null for customer " + principal);
			return null;
		}
		
		return order;
	}
	
	/**
	 * Action for performing a checkout on a given shopping cart
	 * @param id
	 * @param order
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value={"/auth/cart/{id}/checkout"}, method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public PersistableOrderApi checkout(
			@PathVariable final Long id, 
			@Valid @RequestBody PersistableOrderApi order,
			HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);
			
			Principal principal = request.getUserPrincipal();
			String userName = principal.getName();
			
			Customer customer = customerService.getByNick(userName);
			
			if(customer == null) {
				response.sendError(401, "Error while performing checkout customer not authorized");
				return null;
			}
			
			order.setShoppingCartId(id);
			order.setCustomerId(customer.getId());
			
	
			Order modelOrder = orderFacade.processOrder(order, customer, merchantStore, language, locale);
			Long orderId = modelOrder.getId();
			order.setId(orderId);
	
			
			return order;
		
		} catch (Exception e) {
			LOGGER.error("Error while processing checkout",e);
			try {
				response.sendError(503, "Error while processing checkout " + e.getMessage());
			} catch (Exception ignore) {
			}
			return null;
		}


	}

}
