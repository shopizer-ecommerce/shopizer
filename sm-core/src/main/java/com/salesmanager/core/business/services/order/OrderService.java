package com.salesmanager.core.business.services.order;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.*;
import com.salesmanager.core.model.order.orderstatus.OrderStatusHistory;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;


public interface OrderService extends SalesManagerEntityService<Long, Order> {

    void addOrderStatusHistory(Order order, OrderStatusHistory history)
            throws ServiceException;

    /**
     * Can be used to calculates the final prices of all items contained in checkout page
     *
     */
    OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary,
                                         Customer customer, MerchantStore store, Language language)
            throws ServiceException;

    /**
     * Can be used to calculates the final prices of all items contained in a ShoppingCart
     *
     */
    OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary,
                                         MerchantStore store, Language language) throws ServiceException;


    /**
     * Can be used to calculates the final prices of all items contained in checkout page
     *
     * @return @return {@link OrderTotalSummary}
     */
    OrderTotalSummary calculateShoppingCartTotal(final ShoppingCart shoppingCart, final Customer customer, final MerchantStore store, final Language language) throws ServiceException;

    /**
     * Can be used to calculates the final prices of all items contained in a ShoppingCart
     *
     */
    OrderTotalSummary calculateShoppingCartTotal(final ShoppingCart shoppingCart, final MerchantStore store, final Language language) throws ServiceException;

    ByteArrayOutputStream generateInvoice(MerchantStore store, Order order,
                                          Language language) throws ServiceException;

    Order getOrder(Long id);


    /**
     * For finding orders. Mainly used in the administration tool
     *
     */
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
     *
     */
    boolean hasDownloadFiles(Order order) throws ServiceException;

    /**
     * List all orders that have been pre-authorized but not captured
     *
     */
    List<Order> getCapturableOrders(MerchantStore store, Date startDate, Date endDate) throws ServiceException;

}
