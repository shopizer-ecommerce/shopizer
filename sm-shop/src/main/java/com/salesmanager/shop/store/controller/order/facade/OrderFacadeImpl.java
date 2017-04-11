package com.salesmanager.shop.store.controller.order.facade;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.catalog.product.file.DigitalProductService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.customer.attribute.CustomerOptionService;
import com.salesmanager.core.business.services.customer.attribute.CustomerOptionValueService;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.services.shipping.ShippingService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartService;
import com.salesmanager.core.business.utils.CreditCardUtils;
import com.salesmanager.core.model.common.Billing;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.*;
import com.salesmanager.core.model.order.orderproduct.OrderProduct;
import com.salesmanager.core.model.order.orderstatus.OrderStatus;
import com.salesmanager.core.model.order.payment.CreditCard;
import com.salesmanager.core.model.payments.*;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shipping.ShippingProduct;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.model.shipping.ShippingSummary;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.shop.model.customer.Address;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.model.order.*;
import com.salesmanager.shop.model.order.OrderTotal;
import com.salesmanager.shop.populator.customer.CustomerPopulator;
import com.salesmanager.shop.populator.customer.PersistableCustomerPopulator;
import com.salesmanager.shop.populator.order.OrderProductPopulator;
import com.salesmanager.shop.populator.order.ReadableOrderPopulator;
import com.salesmanager.shop.populator.order.ReadableOrderProductPopulator;
import com.salesmanager.shop.populator.order.ShoppingCartItemPopulator;
import com.salesmanager.shop.store.controller.customer.facade.CustomerFacade;
import com.salesmanager.shop.utils.ImageFilePath;
import com.salesmanager.shop.utils.LabelUtils;
import com.salesmanager.shop.utils.LocaleUtils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.inject.Inject;
import java.util.*;

@Service("orderFacade")
public class OrderFacadeImpl implements OrderFacade {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrderFacadeImpl.class);


	@Inject
	private OrderService orderService;
	@Inject
	private ProductService productService;
	@Inject
	private ProductAttributeService productAttributeService;
	@Inject
	private ShoppingCartService shoppingCartService;
	@Inject
	private DigitalProductService digitalProductService;
	@Inject
	private CustomerService customerService;
	@Inject
	private CountryService countryService;
	@Inject
	private ZoneService zoneService;
	@Inject
	private CustomerOptionService customerOptionService;
	@Inject
	private CustomerOptionValueService customerOptionValueService;
	@Inject
	private LanguageService languageService;
	@Inject
	private ShippingService shippingService;
	@Inject
	private CustomerFacade customerFacade;
	@Inject
	private PricingService pricingService;

	
	@Inject
	private LabelUtils messages;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;


	@Override
	public ShopOrder initializeOrder(MerchantStore store, Customer customer,
			ShoppingCart shoppingCart, Language language) throws Exception {

		// assert not null shopping cart items
		
		ShopOrder order = new ShopOrder();
		
		OrderStatus orderStatus = OrderStatus.ORDERED;
		order.setOrderStatus(orderStatus);
		
		if(customer==null) {
				customer = this.initEmptyCustomer(store);
		}
		
		PersistableCustomer persistableCustomer = persistableCustomer(customer, store, language);
		order.setCustomer(persistableCustomer);

		//keep list of shopping cart items for core price calculation
		List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>(shoppingCart.getLineItems());
		order.setShoppingCartItems(items);
		
		return order;
	}
	


	@Override
	public OrderTotalSummary calculateOrderTotal(MerchantStore store,
			ShopOrder order, Language language) throws Exception {
		

		Customer customer = customerFacade.getCustomerModel(order.getCustomer(), store, language);
		OrderTotalSummary summary = this.calculateOrderTotal(store, customer, order, language);
		this.setOrderTotals(order, summary);
		return summary;
	}

	@Override
	public OrderTotalSummary calculateOrderTotal(MerchantStore store,
			PersistableOrder order, Language language) throws Exception {
	
		List<PersistableOrderProduct> orderProducts = order.getOrderProductItems();
		
		ShoppingCartItemPopulator populator = new ShoppingCartItemPopulator();
		populator.setProductAttributeService(productAttributeService);
		populator.setProductService(productService);
		populator.setShoppingCartService(shoppingCartService);
		
		List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
		for(PersistableOrderProduct orderProduct : orderProducts) {
			ShoppingCartItem item = populator.populate(orderProduct, new ShoppingCartItem(), store, language);
			items.add(item);
		}
		

		Customer customer = customer(order.getCustomer(), store, language);
		
		OrderTotalSummary summary = this.calculateOrderTotal(store, customer, order, language);

		return summary;
	}
	
	private OrderTotalSummary calculateOrderTotal(MerchantStore store, Customer customer, PersistableOrder order, Language language) throws Exception {
		
		OrderTotalSummary orderTotalSummary = null;
		
		OrderSummary summary = new OrderSummary();
		
		
		if(order instanceof ShopOrder) {
			ShopOrder o = (ShopOrder)order;
			summary.setProducts(o.getShoppingCartItems());
			
			if(o.getShippingSummary()!=null) {
				summary.setShippingSummary(o.getShippingSummary());
			}
			orderTotalSummary = orderService.caculateOrderTotal(summary, customer, store, language);
		} else {
			//need Set of ShoppingCartItem
			//PersistableOrder not implemented
			throw new Exception("calculateOrderTotal not yet implemented for PersistableOrder");
		}

		return orderTotalSummary;
		
	}
	
	
	private PersistableCustomer persistableCustomer(Customer customer, MerchantStore store, Language language) throws Exception {
		
		PersistableCustomerPopulator customerPopulator = new PersistableCustomerPopulator();
		PersistableCustomer persistableCustomer = customerPopulator.populate(customer, new PersistableCustomer(), store, language);
		return persistableCustomer;
		
	}
	
	private Customer customer(PersistableCustomer customer, MerchantStore store, Language language) throws Exception {
		CustomerPopulator customerPopulator = new CustomerPopulator();
		Customer cust = customerPopulator.populate(customer, new Customer(), store, language);
		return cust;
		
	}
	
	private void setOrderTotals(OrderEntity order, OrderTotalSummary summary) {
		
		List<OrderTotal> totals = new ArrayList<OrderTotal>();
		List<com.salesmanager.core.model.order.OrderTotal> orderTotals = summary.getTotals();
		for(com.salesmanager.core.model.order.OrderTotal t : orderTotals) {
			OrderTotal total = new OrderTotal();
			total.setCode(t.getOrderTotalCode());
			total.setTitle(t.getTitle());
			total.setValue(t.getValue());
			totals.add(total);
		}
		
		order.setTotals(totals);
		
	}


	/**
	 * Submitted object must be valided prior to the invocation of this method
	 */
	@Override
	public Order processOrder(ShopOrder order, Customer customer, MerchantStore store,
			Language language) throws ServiceException {
				
		return this.processOrderModel(order, customer, null, store, language);

	}
	
	@Override
	public Order processOrder(ShopOrder order, Customer customer, Transaction transaction, MerchantStore store,
			Language language) throws ServiceException {
				
		return this.processOrderModel(order, customer, transaction, store, language);

	}
	
	private Order processOrderModel(ShopOrder order, Customer customer, Transaction transaction, MerchantStore store,
			Language language) throws ServiceException {
		
		try {
			
			if(order.isShipToBillingAdress()) {//customer shipping is billing
				PersistableCustomer orderCustomer = order.getCustomer();
				Address billing = orderCustomer.getBilling();
				orderCustomer.setDelivery(billing);
			}

 

			
			Order modelOrder = new Order();
			modelOrder.setDatePurchased(new Date());
			modelOrder.setBilling(customer.getBilling());
			modelOrder.setDelivery(customer.getDelivery());
			modelOrder.setPaymentModuleCode(order.getPaymentModule());
			modelOrder.setPaymentType(PaymentType.valueOf(order.getPaymentMethodType()));
			modelOrder.setShippingModuleCode(order.getShippingModule());
			modelOrder.setCustomerAgreement(order.isCustomerAgreed());
			modelOrder.setLocale(LocaleUtils.getLocale(store));//set the store locale based on the country for order $ formatting
	
			List<ShoppingCartItem> shoppingCartItems = order.getShoppingCartItems();
			Set<OrderProduct> orderProducts = new LinkedHashSet<OrderProduct>();
			
			OrderProductPopulator orderProductPopulator = new OrderProductPopulator();
			orderProductPopulator.setDigitalProductService(digitalProductService);
			orderProductPopulator.setProductAttributeService(productAttributeService);
			orderProductPopulator.setProductService(productService);
			
			for(ShoppingCartItem item : shoppingCartItems) {
				OrderProduct orderProduct = new OrderProduct();
				orderProduct = orderProductPopulator.populate(item, orderProduct , store, language);
				orderProduct.setOrder(modelOrder);
				orderProducts.add(orderProduct);
			}
			
			modelOrder.setOrderProducts(orderProducts);
			
			OrderTotalSummary summary = order.getOrderTotalSummary();
			List<com.salesmanager.core.model.order.OrderTotal> totals = summary.getTotals();

			//re-order totals
			Collections.sort(
					totals,
					new Comparator<com.salesmanager.core.model.order.OrderTotal>() {
					       public int compare(com.salesmanager.core.model.order.OrderTotal x, com.salesmanager.core.model.order.OrderTotal y) {
					            if(x.getSortOrder()==y.getSortOrder())
					            	return 0;
					            return x.getSortOrder() < y.getSortOrder() ? -1 : 1;
					        }
				
			});
			
			Set<com.salesmanager.core.model.order.OrderTotal> modelTotals = new LinkedHashSet<com.salesmanager.core.model.order.OrderTotal>();
			for(com.salesmanager.core.model.order.OrderTotal total : totals) {
				total.setOrder(modelOrder);
				modelTotals.add(total);
			}
			
			modelOrder.setOrderTotal(modelTotals);
			modelOrder.setTotal(order.getOrderTotalSummary().getTotal());
	
			//order misc objects
			modelOrder.setCurrency(store.getCurrency());
			modelOrder.setMerchant(store);

			
			
			//customer object
			orderCustomer(customer, modelOrder, language);
			
			//populate shipping information
			if(!StringUtils.isBlank(order.getShippingModule())) {
				modelOrder.setShippingModuleCode(order.getShippingModule());
			}
			
			String paymentType = order.getPaymentMethodType();
			Payment payment = new Payment();
			payment.setPaymentType(PaymentType.valueOf(paymentType));
			if(PaymentType.CREDITCARD.name().equals(paymentType)) {
				
				
				
				payment = new CreditCardPayment();
				((CreditCardPayment)payment).setCardOwner(order.getPayment().get("creditcard_card_holder"));
				((CreditCardPayment)payment).setCredidCardValidationNumber(order.getPayment().get("creditcard_card_cvv"));
				((CreditCardPayment)payment).setCreditCardNumber(order.getPayment().get("creditcard_card_number"));
				((CreditCardPayment)payment).setExpirationMonth(order.getPayment().get("creditcard_card_expirationmonth"));
				((CreditCardPayment)payment).setExpirationYear(order.getPayment().get("creditcard_card_expirationyear"));
				
				
				Map<String,String> paymentMetaData = order.getPayment();
				payment.setPaymentMetaData(paymentMetaData);
				

				
				CreditCardType creditCardType =null;
				String cardType = order.getPayment().get("creditcard_card_type");
				
				if(cardType.equalsIgnoreCase(CreditCardType.AMEX.name())) {
					creditCardType = CreditCardType.AMEX;
				} else if(cardType.equalsIgnoreCase(CreditCardType.VISA.name())) {
					creditCardType = CreditCardType.VISA;
				} else if(cardType.equalsIgnoreCase(CreditCardType.MASTERCARD.name())) {
					creditCardType = CreditCardType.MASTERCARD;
				} else if(cardType.equalsIgnoreCase(CreditCardType.DINERS.name())) {
					creditCardType = CreditCardType.DINERS;
				} else if(cardType.equalsIgnoreCase(CreditCardType.DISCOVERY.name())) {
					creditCardType = CreditCardType.DISCOVERY;
				}
	
				((CreditCardPayment)payment).setCreditCard(creditCardType);
			
				CreditCard cc = new CreditCard();
				cc.setCardType(creditCardType);
				cc.setCcCvv(((CreditCardPayment)payment).getCredidCardValidationNumber());
				cc.setCcOwner(((CreditCardPayment)payment).getCardOwner());
				cc.setCcExpires(((CreditCardPayment)payment).getExpirationMonth() + "-" + ((CreditCardPayment)payment).getExpirationYear());
			
				//hash credit card number
				String maskedNumber = CreditCardUtils.maskCardNumber(order.getPayment().get("creditcard_card_number"));
				cc.setCcNumber(maskedNumber);
				modelOrder.setCreditCard(cc);
				

			}
			
			if(PaymentType.PAYPAL.name().equals(paymentType)) {
				
				//check for previous transaction
				if(transaction==null) {
					throw new ServiceException("payment.error");
				}
				
				payment = new com.salesmanager.core.model.payments.PaypalPayment();
				
				((com.salesmanager.core.model.payments.PaypalPayment)payment).setPayerId(transaction.getTransactionDetails().get("PAYERID"));
				((com.salesmanager.core.model.payments.PaypalPayment)payment).setPaymentToken(transaction.getTransactionDetails().get("TOKEN"));
				
				
			}
			

			modelOrder.setPaymentModuleCode(order.getPaymentModule());
			payment.setModuleName(order.getPaymentModule());

			if(transaction!=null) {
				orderService.processOrder(modelOrder, customer, order.getShoppingCartItems(), summary, payment, store);
			} else {
				orderService.processOrder(modelOrder, customer, order.getShoppingCartItems(), summary, payment, transaction, store);
			}
			

			
			return modelOrder;
		
		} catch(ServiceException se) {//may be invalid credit card
			throw se;
		} catch(Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	private void orderCustomer(Customer customer, Order order, Language language) throws Exception {

		//populate customer
		order.setBilling(customer.getBilling());
		order.setDelivery(customer.getDelivery());
		order.setCustomerEmailAddress(customer.getEmailAddress());
		order.setCustomerId(customer.getId());

		
		
	}



	@Override
	public Customer initEmptyCustomer(MerchantStore store) {
		
		Customer customer = new Customer();
		Billing billing = new Billing();
		billing.setCountry(store.getCountry());
		billing.setZone(store.getZone());
		billing.setState(store.getStorestateprovince());
		/** empty postal code for initial quote **/
		//billing.setPostalCode(store.getStorepostalcode());
		customer.setBilling(billing);
		
		Delivery delivery = new Delivery();
		delivery.setCountry(store.getCountry());
		delivery.setZone(store.getZone());
		delivery.setState(store.getStorestateprovince());
		/** empty postal code for initial quote **/
		//delivery.setPostalCode(store.getStorepostalcode());
		customer.setDelivery(delivery);
		
		return customer;
	}



	@Override
	public void refreshOrder(ShopOrder order, MerchantStore store,
			Customer customer, ShoppingCart shoppingCart, Language language)
			throws Exception {
		if(customer==null && order.getCustomer()!=null) {
			order.getCustomer().setId(0L);//reset customer id
		}
		
		if(customer!=null) {
			PersistableCustomer persistableCustomer = persistableCustomer(customer, store, language);
			order.setCustomer(persistableCustomer);
		}
		
		List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>(shoppingCart.getLineItems());
		order.setShoppingCartItems(items);
		
		return;
	}
	
	@Override
	public ShippingQuote getShippingQuote(PersistableCustomer persistableCustomer, ShoppingCart cart, ShopOrder order, MerchantStore store, Language language) throws Exception {
		

		//create shipping products
		List<ShippingProduct> shippingProducts = shoppingCartService.createShippingProduct(cart);

		if(CollectionUtils.isEmpty(shippingProducts)) {
			return null;//products are virtual
		}
				
		Customer customer = customerFacade.getCustomerModel(persistableCustomer, store, language);

		
		Delivery delivery = new Delivery();
		
		//adjust shipping and billing
		if(order.isShipToBillingAdress()) {
			
			Billing billing = customer.getBilling();
			
			String postalCode = billing.getPostalCode();
			postalCode = validatePostalCode(postalCode);
			
			delivery.setAddress(billing.getAddress());
			delivery.setCompany(billing.getCompany());
			delivery.setCity(billing.getCity());
			delivery.setPostalCode(billing.getPostalCode());
			delivery.setState(billing.getState());
			delivery.setCountry(billing.getCountry());
			delivery.setZone(billing.getZone());
		} else {
			delivery = customer.getDelivery();
		}
		
		
		
		ShippingQuote quote = shippingService.getShippingQuote(store, delivery, shippingProducts, language);

		return quote;

	}
	
	private String validatePostalCode(String postalCode) {
		
		String patternString = "__";//this one is set in the template
		if(postalCode.contains(patternString)) {
			postalCode = null;
		}
		return postalCode;
	}
	
	@Override
	public List<Country> getShipToCountry(MerchantStore store, Language language) throws Exception {
		
		List<Country> shippingCountriesList = shippingService.getShipToCountryList(store, language);
		return shippingCountriesList;
		
	}
	



	/**
	 * ShippingSummary contains the subset of information
	 * of a ShippingQuote
	 */
	@Override
	public ShippingSummary getShippingSummary(ShippingQuote quote,
			MerchantStore store, Language language) {
		
		ShippingSummary summary = null;
		if(quote.getSelectedShippingOption()!=null) {
			

			summary = new ShippingSummary();
			summary.setFreeShipping(quote.isFreeShipping());
			summary.setTaxOnShipping(quote.isApplyTaxOnShipping());
			summary.setHandling(quote.getHandlingFees());
			summary.setShipping(quote.getSelectedShippingOption().getOptionPrice());
			summary.setShippingOption(quote.getSelectedShippingOption().getOptionName());
			summary.setShippingModule(quote.getShippingModuleCode());
			summary.setShippingOptionCode(quote.getSelectedShippingOption().getOptionCode());
			
			if(quote.getDeliveryAddress()!=null) {
				
				summary.setDeliveryAddress(quote.getDeliveryAddress());

				
			}
				
		
		} 
		
		return summary;
	}

	@Override
	public void validateOrder(ShopOrder order, BindingResult bindingResult, Map<String,String> messagesResult, MerchantStore store,  Locale locale) throws ServiceException {


		Validate.notNull(messagesResult,"messagesResult should not be null");
	
	
		try {
	

			//Language language = (Language)request.getAttribute("LANGUAGE");

			//validate order shipping and billing
			if(StringUtils.isBlank(order.getCustomer().getBilling().getFirstName())) {
				FieldError error = new FieldError("customer.billing.firstName","customer.billing.firstName",messages.getMessage("NotEmpty.customer.firstName", locale));
            	bindingResult.addError(error);
            	messagesResult.put("customer.billing.firstName",messages.getMessage("NotEmpty.customer.firstName", locale));
			}
			
			if(StringUtils.isBlank(order.getCustomer().getBilling().getLastName())) {
				FieldError error = new FieldError("customer.billing.lastName","customer.billing.lastName",messages.getMessage("NotEmpty.customer.lastName", locale));
            	bindingResult.addError(error);
            	messagesResult.put("customer.billing.lastName",messages.getMessage("NotEmpty.customer.lastName", locale));
			}
			
			if(StringUtils.isBlank(order.getCustomer().getEmailAddress())) {
				FieldError error = new FieldError("customer.emailAddress","customer.emailAddress",messages.getMessage("NotEmpty.customer.emailAddress", locale));
            	bindingResult.addError(error);
            	messagesResult.put("customer.emailAddress",messages.getMessage("NotEmpty.customer.emailAddress", locale));
			}
			
			if(StringUtils.isBlank(order.getCustomer().getBilling().getAddress())) {
				FieldError error = new FieldError("customer.billing.address","customer.billing.address",messages.getMessage("NotEmpty.customer.billing.address", locale));
            	bindingResult.addError(error);
            	messagesResult.put("customer.billing.address",messages.getMessage("NotEmpty.customer.billing.address", locale));
			}
			
			if(StringUtils.isBlank(order.getCustomer().getBilling().getCity())) {
				FieldError error = new FieldError("customer.billing.city","customer.billing.city",messages.getMessage("NotEmpty.customer.billing.city", locale));
            	bindingResult.addError(error);
            	messagesResult.put("customer.billing.city",messages.getMessage("NotEmpty.customer.billing.city", locale));
			}
			
			if(StringUtils.isBlank(order.getCustomer().getBilling().getCountry())) {
				FieldError error = new FieldError("customer.billing.country","customer.billing.country",messages.getMessage("NotEmpty.customer.billing.country", locale));
            	bindingResult.addError(error);
            	messagesResult.put("customer.billing.country",messages.getMessage("NotEmpty.customer.billing.country", locale));
			}
			
			if(StringUtils.isBlank(order.getCustomer().getBilling().getZone()) && StringUtils.isBlank(order.getCustomer().getBilling().getStateProvince())) {
				FieldError error = new FieldError("customer.billing.stateProvince","customer.billing.stateProvince",messages.getMessage("NotEmpty.customer.billing.stateProvince", locale));
            	bindingResult.addError(error);
            	messagesResult.put("customer.billing.stateProvince",messages.getMessage("NotEmpty.customer.billing.stateProvince", locale));
			}
			
			if(StringUtils.isBlank(order.getCustomer().getBilling().getPhone())) {
				FieldError error = new FieldError("customer.billing.phone","customer.billing.phone",messages.getMessage("NotEmpty.customer.billing.phone", locale));
            	bindingResult.addError(error);
            	messagesResult.put("customer.billing.phone",messages.getMessage("NotEmpty.customer.billing.phone", locale));
			}
			
			if(StringUtils.isBlank(order.getCustomer().getBilling().getPostalCode())) {
				FieldError error = new FieldError("customer.billing.postalCode","customer.billing.postalCode",messages.getMessage("NotEmpty.customer.billing.postalCode", locale));
            	bindingResult.addError(error);
            	messagesResult.put("customer.billing.postalCode",messages.getMessage("NotEmpty.customer.billing.postalCode", locale));
			}
			
			if(!order.isShipToBillingAdress()) {
				
				if(StringUtils.isBlank(order.getCustomer().getDelivery().getFirstName())) {
					FieldError error = new FieldError("customer.delivery.firstName","customer.delivery.firstName",messages.getMessage("NotEmpty.customer.shipping.firstName", locale));
	            	bindingResult.addError(error);
	            	messagesResult.put("customer.delivery.firstName",messages.getMessage("NotEmpty.customer.shipping.firstName", locale));
				}
				
				if(StringUtils.isBlank(order.getCustomer().getDelivery().getLastName())) {
					FieldError error = new FieldError("customer.delivery.lastName","customer.delivery.lastName",messages.getMessage("NotEmpty.customer.shipping.lastName", locale));
	            	bindingResult.addError(error);
	            	messagesResult.put("customer.delivery.lastName",messages.getMessage("NotEmpty.customer.shipping.lastName", locale));
				}
				
				if(StringUtils.isBlank(order.getCustomer().getDelivery().getAddress())) {
					FieldError error = new FieldError("customer.delivery.address","customer.delivery.address",messages.getMessage("NotEmpty.customer.shipping.address", locale));
	            	bindingResult.addError(error);
	            	messagesResult.put("customer.delivery.address",messages.getMessage("NotEmpty.customer.shipping.address", locale));
				}
				
				if(StringUtils.isBlank(order.getCustomer().getDelivery().getCity())) {
					FieldError error = new FieldError("customer.delivery.city","customer.delivery.city",messages.getMessage("NotEmpty.customer.shipping.city", locale));
	            	bindingResult.addError(error);
	            	messagesResult.put("customer.delivery.city",messages.getMessage("NotEmpty.customer.shipping.city", locale));
				}
				
				if(StringUtils.isBlank(order.getCustomer().getDelivery().getCountry())) {
					FieldError error = new FieldError("customer.delivery.country","customer.delivery.country",messages.getMessage("NotEmpty.customer.shipping.country", locale));
	            	bindingResult.addError(error);
	            	messagesResult.put("customer.delivery.country",messages.getMessage("NotEmpty.customer.shipping.country", locale));
				}
				
				if(StringUtils.isBlank(order.getCustomer().getDelivery().getZone()) && StringUtils.isBlank(order.getCustomer().getDelivery().getStateProvince())) {
					FieldError error = new FieldError("customer.delivery.stateProvince","customer.delivery.stateProvince",messages.getMessage("NotEmpty.customer.shipping.stateProvince", locale));
	            	bindingResult.addError(error);
	            	messagesResult.put("customer.delivery.stateProvince",messages.getMessage("NotEmpty.customer.shipping.stateProvince", locale));
				}
				
				
				if(StringUtils.isBlank(order.getCustomer().getDelivery().getPostalCode())) {
					FieldError error = new FieldError("customer.delivery.postalCode","customer.delivery.postalCode",messages.getMessage("NotEmpty.customer.shipping.postalCode", locale));
	            	bindingResult.addError(error);
	            	messagesResult.put("customer.delivery.postalCode",messages.getMessage("NotEmpty.customer.shipping.postalCode", locale));
				}
				
			}
			
	        if ( bindingResult.hasErrors() )
	        {
	            return;

	        }
			
			String paymentType = order.getPaymentMethodType();
			
			if(!shoppingCartService.isFreeShoppingCart(order.getShoppingCartItems()) && paymentType==null) {
				
			}
			
			//validate payment
			if(paymentType==null) {
				ServiceException serviceException = new ServiceException(ServiceException.EXCEPTION_VALIDATION,"payment.required");
				throw serviceException;
			}
			
			//validate shipping
			if(shippingService.requiresShipping(order.getShoppingCartItems(), store) && order.getSelectedShippingOption()==null) {
				ServiceException serviceException = new ServiceException(ServiceException.EXCEPTION_VALIDATION,"shipping.required");
				throw serviceException;
			}
			
			//pre-validate credit card
			if(PaymentType.CREDITCARD.name().equals(paymentType)) {
				String cco = order.getPayment().get("creditcard_card_holder");
				String cvv = order.getPayment().get("creditcard_card_cvv");
				String ccn = order.getPayment().get("creditcard_card_number");
				String ccm = order.getPayment().get("creditcard_card_expirationmonth");
				String ccd = order.getPayment().get("creditcard_card_expirationyear");
				
				if(StringUtils.isBlank(cco) || StringUtils.isBlank(cvv) || StringUtils.isBlank(ccn) ||
					StringUtils.isBlank(ccm) || StringUtils.isBlank(ccd)) {
					ObjectError error = new ObjectError("creditcard_card_holder",messages.getMessage("NotEmpty.customer.shipping.stateProvince", locale));
	            	bindingResult.addError(error);
	            	messagesResult.put("creditcard_card_holder",messages.getMessage("NotEmpty.customer.shipping.stateProvince", locale));
	            	return;
				}
				
				CreditCardType creditCardType =null;
				String cardType = order.getPayment().get("creditcard_card_type");
				
				if(cardType.equalsIgnoreCase(CreditCardType.AMEX.name())) {
					creditCardType = CreditCardType.AMEX;
				} else if(cardType.equalsIgnoreCase(CreditCardType.VISA.name())) {
					creditCardType = CreditCardType.VISA;
				} else if(cardType.equalsIgnoreCase(CreditCardType.MASTERCARD.name())) {
					creditCardType = CreditCardType.MASTERCARD;
				} else if(cardType.equalsIgnoreCase(CreditCardType.DINERS.name())) {
					creditCardType = CreditCardType.DINERS;
				} else if(cardType.equalsIgnoreCase(CreditCardType.DISCOVERY.name())) {
					creditCardType = CreditCardType.DISCOVERY;
				}
				
				if(creditCardType==null) {
					ServiceException serviceException = new ServiceException(ServiceException.EXCEPTION_VALIDATION,"cc.type");
					throw serviceException;
				}

			}
			
			
			

      
	        
        
		} catch(ServiceException se) {
			LOGGER.error("Error while commiting order",se);
			throw se;
		}

}



	@Override
	public ReadableOrderList getReadableOrderList(MerchantStore store,
			Customer customer, int start, int maxCount, Language language) throws Exception {
		
		OrderCriteria criteria = new OrderCriteria();
		criteria.setStartIndex(start);
		criteria.setMaxCount(maxCount);
		criteria.setCustomerId(customer.getId());

		return this.getReadableOrderList(criteria, store, language);
		
	}



	@Override
	public ShippingQuote getShippingQuote(Customer customer, ShoppingCart cart,
			PersistableOrder order, MerchantStore store, Language language)
			throws Exception {
		//create shipping products
		List<ShippingProduct> shippingProducts = shoppingCartService.createShippingProduct(cart);

		if(CollectionUtils.isEmpty(shippingProducts)) {
			return null;//products are virtual
		}
				

		
		Delivery delivery = new Delivery();
		
		//adjust shipping and billing
		if(order.isShipToBillingAdress()) {
			Billing billing = customer.getBilling();
			delivery.setAddress(billing.getAddress());
			delivery.setCity(billing.getCity());
			delivery.setCompany(billing.getCompany());
			delivery.setPostalCode(billing.getPostalCode());
			delivery.setState(billing.getState());
			delivery.setCountry(billing.getCountry());
			delivery.setZone(billing.getZone());
		} else {
			delivery = customer.getDelivery();
		}
		
		
		
		ShippingQuote quote = shippingService.getShippingQuote(store, delivery, shippingProducts, language);

		return quote;
	}
	
	

     private ReadableOrderList populateOrderList(final OrderList orderList,final MerchantStore store, final Language language){
        List<Order> orders = orderList.getOrders();
        ReadableOrderList returnList = new ReadableOrderList();
        if(CollectionUtils.isEmpty( orders)){
            LOGGER.info( "Order list if empty..Returning empty list" );
            returnList.setTotal(0);
            returnList.setMessage("No results for store code " + store);
            return null;
        }
        
        ReadableOrderPopulator orderPopulator = new ReadableOrderPopulator();
        Locale locale = LocaleUtils.getLocale(language);
        orderPopulator.setLocale(locale);
        
        List<ReadableOrder> readableOrders = new ArrayList<ReadableOrder>();
        for (Order order : orders) {
            ReadableOrder readableOrder = new ReadableOrder();
            try
            {
                orderPopulator.populate(order,readableOrder,store,language);
                setOrderProductList(order,locale,store,language,readableOrder);
            }
            catch ( ConversionException ex )
            {
                LOGGER.error( "Error while converting order to order data", ex );
                
            }
            readableOrders.add(readableOrder);
            
        }
        
        returnList.setTotal(orderList.getTotalCount());
        returnList.setOrders( readableOrders );
        return returnList;
       
    }
    
    private void setOrderProductList(final Order order, final Locale locale,final MerchantStore store, final Language language , final ReadableOrder readableOrder) throws ConversionException{
        List<ReadableOrderProduct> orderProducts = new ArrayList<ReadableOrderProduct>();
        for(OrderProduct p : order.getOrderProducts()) {
            ReadableOrderProductPopulator orderProductPopulator = new ReadableOrderProductPopulator();
            orderProductPopulator.setLocale(locale);
            orderProductPopulator.setProductService(productService);
            orderProductPopulator.setPricingService(pricingService);
            orderProductPopulator.setimageUtils(imageUtils);
            ReadableOrderProduct orderProduct = new ReadableOrderProduct();
            orderProductPopulator.populate(p, orderProduct, store, language);
            
            //image
            
            //attributes
            


            orderProducts.add(orderProduct);
        }
        
        readableOrder.setProducts(orderProducts);
    }


    private ReadableOrderList getReadableOrderList(OrderCriteria criteria, MerchantStore store, Language language) throws Exception {
		
		OrderList orderList = orderService.listByStore(store, criteria);
		
		ReadableOrderPopulator orderPopulator = new ReadableOrderPopulator();
		Locale locale = LocaleUtils.getLocale(language);
		orderPopulator.setLocale(locale);
		
		List<Order> orders = orderList.getOrders();
		ReadableOrderList returnList = new ReadableOrderList();
		
		if(CollectionUtils.isEmpty(orders)) {
			returnList.setTotal(0);
			returnList.setMessage("No results for store code " + store);
			return null;
		}

		List<ReadableOrder> readableOrders = new ArrayList<ReadableOrder>();
		for (Order order : orders) {
			ReadableOrder readableOrder = new ReadableOrder();
			orderPopulator.populate(order,readableOrder,store,language);
			readableOrders.add(readableOrder);
			
		}
		
		returnList.setTotal(orderList.getTotalCount());
		return this.populateOrderList(orderList, store, language);
    	
    	
	}

	@Override
	public ReadableOrderList getReadableOrderList(MerchantStore store,
			int start, int maxCount, Language language) throws Exception {
		
		OrderCriteria criteria = new OrderCriteria();
		criteria.setStartIndex(start);
		criteria.setMaxCount(maxCount);

		return this.getReadableOrderList(criteria, store, language);
	}



	@Override
	public ReadableOrder getReadableOrder(Long orderId, MerchantStore store,
			Language language) throws Exception {
		
		
		
		Order modelOrder = orderService.getById(orderId);
		if(modelOrder==null) {
			throw new Exception("Order not found with id " + orderId);
		}
		
		ReadableOrder readableOrder = new ReadableOrder();
		
		Long customerId = modelOrder.getCustomerId();
		if(customerId != null) {
			ReadableCustomer readableCustomer = customerFacade.getCustomerById(customerId, store, language);
			if(readableCustomer==null) {
				LOGGER.warn("Customer id " + customerId + " not found in order " + orderId);
			} else {
				readableOrder.setCustomer(readableCustomer);
			}
		}
		
		ReadableOrderPopulator orderPopulator = new ReadableOrderPopulator();
		orderPopulator.populate(modelOrder, readableOrder,  store, language);
		
		//order products
		List<ReadableOrderProduct> orderProducts = new ArrayList<ReadableOrderProduct>();
		for(OrderProduct p : modelOrder.getOrderProducts()) {
			ReadableOrderProductPopulator orderProductPopulator = new ReadableOrderProductPopulator();
			orderProductPopulator.setProductService(productService);
			orderProductPopulator.setPricingService(pricingService);
			orderProductPopulator.setimageUtils(imageUtils);
			
			ReadableOrderProduct orderProduct = new ReadableOrderProduct();
			orderProductPopulator.populate(p, orderProduct, store, language);
			orderProducts.add(orderProduct);
		}
		
		readableOrder.setProducts(orderProducts);
		
		return readableOrder;
	}

}
