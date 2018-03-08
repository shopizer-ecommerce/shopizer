package com.salesmanager.shop.populator.order.transaction;

import org.apache.commons.lang.Validate;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.payments.PaymentType;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.payments.TransactionType;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.order.transaction.PersistableTransaction;
import com.shopizer.search.utils.DateUtil;

public class PersistableTransactionPopulator extends AbstractDataPopulator<PersistableTransaction, Transaction> {

	private OrderService orderService;
	private PricingService pricingService;
	
	@Override
	public Transaction populate(PersistableTransaction source, Transaction target, MerchantStore store,
			Language language) throws ConversionException {
		
		Validate.notNull(source,"PersistableTransaction must not be null");
		Validate.notNull(orderService,"OrderService must not be null");
		Validate.notNull(pricingService,"OrderService must not be null");
		
		if(target == null) {
			target = new Transaction();
		}
		
		
		try {
			

			target.setAmount(pricingService.getAmount(source.getAmount()));
			target.setDetails(source.getDetails());
			target.setPaymentType(PaymentType.valueOf(source.getPaymentType()));
			target.setTransactionType(TransactionType.valueOf(source.getTransactionType()));
			target.setTransactionDate(DateUtil.formatDate(source.getTransactionDate()));
			
			if(source.getOrderId()!=null && source.getOrderId().longValue() > 0) {
				Order order = orderService.getById(source.getOrderId());
/*				if(source.getCustomerId() == null) {
					throw new ConversionException("Cannot add a transaction for an Order without specyfing the customer");
				}*/
				
				if(order == null) {
					throw new ConversionException("Order with id " + source.getOrderId() + "does not exist");
				}
				target.setOrder(order);
			}
			
			return target;
			
			
		
		} catch(Exception e) {
			throw new ConversionException(e);
		}

	}

	@Override
	protected Transaction createTarget() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public PricingService getPricingService() {
		return pricingService;
	}

	public void setPricingService(PricingService pricingService) {
		this.pricingService = pricingService;
	}

}
