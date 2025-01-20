package com.salesmanager.core.business.services.order;

import java.util.Date;
import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.OrderCriteria;
import com.salesmanager.core.model.order.OrderList;
import com.salesmanager.core.model.order.orderstatus.OrderStatusHistory;

public interface OrderManagementService {

        void addOrderStatusHistory(Order order, OrderStatusHistory history)
                    throws ServiceException;
                    
    /**
     * Retrieves an order by its ID and merchant store.
     * 
     * @param id    The ID of the order to retrieve.
     * @param store The merchant store associated with the order.
     * @return The retrieved order.
     */
    Order getOrder(Long id, MerchantStore store);

    /**
     * For finding orders. Mainly used in the administration tool.
     * 
     * @param store    The merchant store.
     * @param criteria The criteria for filtering orders.
     * @return A list of orders based on the criteria.
     */
    OrderList listByStore(MerchantStore store, OrderCriteria criteria);

    /**
     * Get all orders. Mainly used in the administration tool.
     * 
     * @param criteria The criteria for filtering orders.
     * @param store    The merchant store.
     * @return A list of orders matching the criteria.
     */
    OrderList getOrders(OrderCriteria criteria, MerchantStore store);

    /**
     * Save or update an order in the database.
     * 
     * @param order The order to save or update.
     * @throws ServiceException If an error occurs while saving or updating.
     */
    void saveOrUpdate(Order order) throws ServiceException;

    /**
     * Determines if an order has downloadable files.
     * 
     * @param order The order to check.
     * @return True if the order has downloadable files, false otherwise.
     * @throws ServiceException If an error occurs during the check.
     */
    boolean hasDownloadFiles(Order order) throws ServiceException;

    /**
     * List all orders that have been pre-authorized but not captured.
     * 
     * @param store     The merchant store.
     * @param startDate The start date for filtering orders.
     * @param endDate   The end date for filtering orders.
     * @return A list of capturable orders.
     * @throws ServiceException If an error occurs while retrieving orders.
     */
    List<Order> getCapturableOrders(MerchantStore store, Date startDate, Date endDate) throws ServiceException;
}
