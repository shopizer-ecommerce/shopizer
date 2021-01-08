package com.salesmanager.shop.store.facade.order;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.OrderTotal;
import com.salesmanager.core.model.order.orderproduct.OrderProduct;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.customer.ReadableCustomerMapper;
import com.salesmanager.shop.mapper.order.ReadableOrderProductMapper;
import com.salesmanager.shop.mapper.order.ReadableOrderTotalMapper;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.model.order.ReadableOrderProduct;
import com.salesmanager.shop.model.order.total.ReadableOrderTotal;
import com.salesmanager.shop.model.order.total.ReadableTotal;
import com.salesmanager.shop.model.order.v1.ReadableOrderConfirmation;
import com.salesmanager.shop.store.controller.order.facade.v1.OrderFacade;
import com.salesmanager.shop.utils.LabelUtils;

@Service("orderFacadev1")
public class OrderFacadeImpl implements OrderFacade {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderFacadeImpl.class);
	
	
	@Autowired
	private ReadableCustomerMapper readableCustomerMapper;
	
	@Autowired
	private ReadableOrderTotalMapper readableOrderTotalMapper;
	
	@Autowired
	private ReadableOrderProductMapper readableOrderProductMapper;
	
	@Autowired 
	private LabelUtils messages;
	
	@Autowired
	private LanguageService languageService;

	@Override
	public ReadableOrderConfirmation orderConfirmation(Order order, Customer customer, MerchantStore store,
			Language language) {
		Validate.notNull(order, "Order cannot be null");
		Validate.notNull(customer, "Customer cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		
		ReadableOrderConfirmation orderConfirmation = new ReadableOrderConfirmation();
		
		ReadableCustomer readableCustomer = readableCustomerMapper.convert(customer, store, language);
		orderConfirmation.setBilling(readableCustomer.getBilling());
		orderConfirmation.setDelivery(readableCustomer.getDelivery());
		
		ReadableTotal readableTotal = new ReadableTotal();
		
		Set<OrderTotal> totals = order.getOrderTotal();
		List<ReadableOrderTotal> readableTotals = totals.stream()
				.sorted(Comparator.comparingInt(OrderTotal::getSortOrder))
				.map(tot -> convertOrderTotal(tot, store, language))
				.collect(Collectors.toList());
		
		readableTotal.setTotals(readableTotals);
		
		
		Optional<ReadableOrderTotal> grandTotal = readableTotals.stream().filter(tot -> tot.getCode().equals("order.total.total")).findFirst();
		
		
		if(grandTotal.isPresent()) {
			readableTotal.setGrandTotal(grandTotal.get().getText());
		}
		orderConfirmation.setTotal(readableTotal);
		
		
		List<ReadableOrderProduct> products = order.getOrderProducts().stream().map(pr -> convertOrderProduct(pr, store, language)).collect(Collectors.toList());
		orderConfirmation.setProducts(products);
		
		if(!StringUtils.isBlank(order.getShippingModuleCode())) {
	        StringBuilder optionCodeBuilder = new StringBuilder();
	        try {
	
	          optionCodeBuilder
	              .append("module.shipping.")
	              .append(order.getShippingModuleCode());
	          String shippingName = messages.getMessage(optionCodeBuilder.toString(), new String[]{store.getStorename()},languageService.toLocale(language, store));
	          orderConfirmation.setShipping(shippingName);
	        } catch (Exception e) { // label not found
	          LOGGER.warn("No shipping code found for " + optionCodeBuilder.toString());
	        }
		}
		
		if(order.getPaymentType() != null) {
			orderConfirmation.setPayment(order.getPaymentType().name());
		}
		
		
		/**
		 * Confirmation may be formatted
		 */
		orderConfirmation.setId(order.getId());

		
		return orderConfirmation;
	}
	
	private ReadableOrderTotal convertOrderTotal(OrderTotal total, MerchantStore store, Language language) {

		return readableOrderTotalMapper.convert(total, store, language);
	}
	
	private ReadableOrderProduct convertOrderProduct(OrderProduct product, MerchantStore store, Language language) {
		
		return readableOrderProductMapper.convert(product, store, language);
		
	}

}
