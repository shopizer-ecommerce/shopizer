package com.salesmanager.core.business.order.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.Order;
import com.salesmanager.core.business.order.model.OrderCriteria;
import com.salesmanager.core.business.order.model.OrderList;
import com.salesmanager.core.business.order.model.OrderSummary;
import com.salesmanager.core.business.order.model.OrderTotalSummary;
import com.salesmanager.core.business.order.model.orderstatus.OrderStatusHistory;
import com.salesmanager.core.business.payments.model.Payment;
import com.salesmanager.core.business.payments.model.Transaction;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCart;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCartItem;

public interface OrderService extends SalesManagerEntityService<Long, Order> {

    void addOrderStatusHistory(Order order, OrderStatusHistory history)
                    throws ServiceException;

    /**
     * Can be used to calculates the final prices of all items contained in checkout page
     * @param orderSummary
     * @param customer
     * @param store
     * @param language
     * @return
     * @throws ServiceException
     */
    OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary,
                                         Customer customer, MerchantStore store, Language language)
                                                         throws ServiceException;

    /**
     * Can be used to calculates the final prices of all items contained in a ShoppingCart
     * @param orderSummary
     * @param store
     * @param language
     * @return
     * @throws ServiceException
     */
    OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary,
                                         MerchantStore store, Language language) throws ServiceException;


    /**
     * Can be used to calculates the final prices of all items contained in checkout page
     * @param shoppingCart
     * @param customer
     * @param store
     * @param language
     * @return  @return {@link OrderTotalSummary}
     * @throws ServiceException
     */
    OrderTotalSummary calculateShoppingCartTotal(final ShoppingCart shoppingCart,final Customer customer, final MerchantStore store, final Language language) throws ServiceException;

    /**
     * Can be used to calculates the final prices of all items contained in a ShoppingCart
     * @param shoppingCart
     * @param store
     * @param language
     * @return {@link OrderTotalSummary}
     * @throws ServiceException
     */
    OrderTotalSummary calculateShoppingCartTotal(final ShoppingCart shoppingCart,final MerchantStore store, final Language language) throws ServiceException;

    ByteArrayOutputStream generateInvoice(MerchantStore store, Order order,
                                          Language language) throws ServiceException;

    Order getOrder(Long id);

    List<Order> listByStore(MerchantStore merchantStore);

    

    
    
    OrderList listByStore(MerchantStore store, OrderCriteria criteria);

    void saveOrUpdate(Order order) throws ServiceException;

	Order processOrder(Order order, Customer customer,
			List<ShoppingCartItem> items, OrderTotalSummary summary,
			Payment payment, MerchantStore store) throws ServiceException;

	Order processOrder(Order order, Customer customer,
			List<ShoppingCartItem> items, OrderTotalSummary summary,
			Payment payment, Transaction transaction, MerchantStore store)
			throws ServiceException;



	
	/**
	 * Determines if an Order has download files
	 * @param order
	 * @return
	 * @throws ServiceException
	 */
	boolean hasDownloadFiles(Order order) throws ServiceException;

}
