package com.salesmanager.web.services.controller.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.catalog.product.service.attribute.ProductAttributeService;
import com.salesmanager.core.business.catalog.product.service.file.DigitalProductService;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.service.CustomerService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.merchant.service.MerchantStoreService;
import com.salesmanager.core.business.order.model.Order;
import com.salesmanager.core.business.order.service.OrderService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.entity.customer.PersistableCustomer;
import com.salesmanager.web.entity.order.PersistableOrder;
import com.salesmanager.web.entity.order.ReadableOrderList;
import com.salesmanager.web.populator.customer.CustomerPopulator;
import com.salesmanager.web.populator.order.PersistableOrderPopulator;
import com.salesmanager.web.shop.controller.order.facade.OrderFacade;

@Controller
@RequestMapping("/services/private")
public class OrderRESTController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderRESTController.class);
	
	
	@Autowired
	private MerchantStoreService merchantStoreService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductAttributeService productAttributeService;
	
	@Autowired
	private DigitalProductService digitalProductService;
	
	@Autowired
	private OrderFacade orderFacade;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LanguageService languageService;

	
	@RequestMapping( value="/{store}/orders", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public PersistableOrder createOrder(@PathVariable final String store, @Valid @RequestBody PersistableOrder order, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
		if(merchantStore!=null) {
			if(!merchantStore.getCode().equals(store)) {
				merchantStore = null;
			}
		}
		
		if(merchantStore== null) {
			merchantStore = merchantStoreService.getByCode(store);
		}
		
		if(merchantStore==null) {
			LOGGER.error("Merchant store is null for code " + store);
			response.sendError(503, "Merchant store is null for code " + store);
			return null;
		}
		
		
		PersistableCustomer cust = order.getCustomer();
		if(cust!=null) {
			CustomerPopulator customerPopulator = new CustomerPopulator();
			Customer customer = new Customer();
			customerPopulator.populate(cust, customer, merchantStore, merchantStore.getDefaultLanguage());
			customerService.save(customer);
			cust.setId(customer.getId());
		}
		
		
		Order modelOrder = new Order();
		PersistableOrderPopulator populator = new PersistableOrderPopulator();
		populator.setDigitalProductService(digitalProductService);
		populator.setProductAttributeService(productAttributeService);
		populator.setProductService(productService);
		
		populator.populate(order, modelOrder, merchantStore, merchantStore.getDefaultLanguage());
		
	
		orderService.save(modelOrder);
		order.setId(modelOrder.getId());
		
		return order;
	}
	
	
	/**
	 * Get a list of orders
	 * accept request parameter 'lang' [en,fr...] otherwise store dafault language
	 * accept request parameter 'start' start index for count
	 * accept request parameter 'max' maximum number count, otherwise returns all
	 * @param store
	 * @param order
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/{store}/orders/", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public ReadableOrderList listOrders(@PathVariable final String store, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
		if(merchantStore!=null) {
			if(!merchantStore.getCode().equals(store)) {
				merchantStore = null;
			}
		}
		
		if(merchantStore== null) {
			merchantStore = merchantStoreService.getByCode(store);
		}
		
		if(merchantStore==null) {
			LOGGER.error("Merchant store is null for code " + store);
			response.sendError(503, "Merchant store is null for code " + store);
			return null;
		}
		
		//get additional request parameters for orders
		String lang = request.getParameter(Constants.LANG);		
		String start = request.getParameter(Constants.START);
		String max = request.getParameter(Constants.MAX);
		
		int startCount = 0;
		int maxCount = 0;
		
		if(StringUtils.isBlank(lang)) {
			lang = merchantStore.getDefaultLanguage().getCode();
		}
		
		
		Language language = languageService.getByCode(lang);
		
		if(language==null) {
			LOGGER.error("Language is null for code " + lang);
			response.sendError(503, "Language is null for code " + lang);
			return null;
		}
		
		try {
			startCount = Integer.parseInt(start);
		} catch (Exception e) {
			LOGGER.info("Invalid value for start " + start);
		}
		
		try {
			maxCount = Integer.parseInt(max);
		} catch (Exception e) {
			LOGGER.info("Invalid value for max " + max);
		}
		
		
		
		ReadableOrderList returnList = orderFacade.getReadableOrderList(merchantStore, startCount, maxCount, language);

		return returnList;
	}
	
	/**
	 * Get a list of orders for a given customer
	 * accept request parameter 'lang' [en,fr...] otherwise store dafault language
	 * accept request parameter 'start' start index for count
	 * accept request parameter 'max' maximum number count, otherwise returns all
	 * @param store
	 * @param order
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/{store}/orders/customer/{id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public ReadableOrderList listOrders(@PathVariable final String store, @PathVariable final Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
		if(merchantStore!=null) {
			if(!merchantStore.getCode().equals(store)) {
				merchantStore = null;
			}
		}
		
		if(merchantStore== null) {
			merchantStore = merchantStoreService.getByCode(store);
		}
		
		if(merchantStore==null) {
			LOGGER.error("Merchant store is null for code " + store);
			response.sendError(503, "Merchant store is null for code " + store);
			return null;
		}
		
		//get additional request parameters for orders
		String lang = request.getParameter(Constants.LANG);		
		String start = request.getParameter(Constants.START);
		String max = request.getParameter(Constants.MAX);
		
		int startCount = 0;
		int maxCount = 0;
		
		if(StringUtils.isBlank(lang)) {
			lang = merchantStore.getDefaultLanguage().getCode();
		}
		
		
		Language language = languageService.getByCode(lang);
		
		if(language==null) {
			LOGGER.error("Language is null for code " + lang);
			response.sendError(503, "Language is null for code " + lang);
			return null;
		}
		
		try {
			startCount = Integer.parseInt(start);
		} catch (Exception e) {
			LOGGER.info("Invalid value for start " + start);
		}
		
		try {
			maxCount = Integer.parseInt(max);
		} catch (Exception e) {
			LOGGER.info("Invalid value for max " + max);
		}
		
		Customer customer = customerService.getById(id);
		
		if(customer==null) {
			LOGGER.error("Customer is null for id " + id);
			response.sendError(503, "Customer is null for id " + id);
			return null;
		}
		
		if(customer.getMerchantStore().getId().intValue()!=merchantStore.getId().intValue()) {
			LOGGER.error("Customer is null for id " + id + " and store id " + store);
			response.sendError(503, "Customer is null for id " + id + " and store id " + store);
			return null;
		}
		
		ReadableOrderList returnList = orderFacade.getReadableOrderList(merchantStore, startCount, maxCount, language);

		return returnList;
	}

}
