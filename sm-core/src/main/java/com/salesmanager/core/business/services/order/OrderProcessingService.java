package com.salesmanager.core.business.services.order;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.OrderTotalSummary;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;

public interface OrderProcessingService {

    /**
     * Processes an order with the given details and payment.
     * 
     * @param order     The order to process.
     * @param customer  The customer placing the order.
     * @param items     The items in the shopping cart.
     * @param summary   The order total summary.
     * @param payment   The payment details.
     * @param store     The merchant store.
     * @return The processed order.
     * @throws ServiceException If an error occurs during processing.
     */
    Order processOrder(Order order, Customer customer, List<ShoppingCartItem> items, OrderTotalSummary summary,
            Payment payment, MerchantStore store) throws ServiceException;

    /**
     * Processes an order with the given details, payment, and transaction.
     * 
     * @param order       The order to process.
     * @param customer    The customer placing the order.
     * @param items       The items in the shopping cart.
     * @param summary     The order total summary.
     * @param payment     The payment details.
     * @param transaction The transaction details.
     * @param store       The merchant store.
     * @return The processed order.
     * @throws ServiceException If an error occurs during processing.
     */
    Order processOrder(Order order, Customer customer, List<ShoppingCartItem> items, OrderTotalSummary summary,
            Payment payment, Transaction transaction, MerchantStore store) throws ServiceException;

        /**
     * Generates an invoice for the given order.
     * 
     * @param store    The merchant store.
     * @param order    The order for which to generate an invoice.
     * @param language The language for the invoice.
     * @return A ByteArrayOutputStream containing the invoice data.
     * @throws ServiceException If an error occurs during generation.
     */
    ByteArrayOutputStream generateInvoice(MerchantStore store, Order order, Language language) throws ServiceException;
}
