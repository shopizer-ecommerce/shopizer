package com.salesmanager.core.modules.integration.payment.model;

import java.math.BigDecimal;
import java.util.List;

import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.core.modules.integration.IntegrationException;

public interface PaymentModule {
	
	public void validateModuleConfiguration(IntegrationConfiguration integrationConfiguration, MerchantStore store) throws IntegrationException;
	

	/**
	 * Returns token-value related to the initialization of the transaction This
	 * method is invoked for paypal express checkout
	 * @param store MerchantStore
	 * @param customer Customer
	 * @param amount BigDecimal
	 * @param payment Payment
	 * @param configuration IntegrationConfiguration
	 * @param module IntegrationModule
	 * @return Transaction a Transaction
	 * @throws IntegrationException IntegrationException
	 */
	public Transaction initTransaction(
			MerchantStore store, Customer customer, BigDecimal amount, Payment payment, IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException;
	
	public Transaction authorize(
			MerchantStore store, Customer customer, List<ShoppingCartItem> items, BigDecimal amount, Payment payment, IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException;

	
	public Transaction capture(
			MerchantStore store, Customer customer, Order order, Transaction capturableTransaction, IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException;
	
	public Transaction authorizeAndCapture(
			MerchantStore store, Customer customer, List<ShoppingCartItem> items, BigDecimal amount, Payment payment, IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException;
	
	public Transaction refund(
			boolean partial, MerchantStore store, Transaction transaction, Order order, BigDecimal amount, IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException;

}
