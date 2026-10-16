package com.salesmanager.core.business.services.order;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderSummary;
import com.salesmanager.core.model.order.OrderTotalSummary;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;

public interface OrderCalculationService {

    /**
     * Can be used to calculate the final prices of all items contained in the checkout page.
     * 
     * @param orderSummary The summary of the order.
     * @param customer     The customer associated with the order.
     * @param store        The merchant store.
     * @param language     The language used for calculations.
     * @return {@link OrderTotalSummary} containing the calculated totals.
     * @throws ServiceException If an error occurs during calculation.
     */
    OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary, Customer customer, MerchantStore store, Language language)
            throws ServiceException;

    /**
     * Can be used to calculate the final prices of all items contained in a ShoppingCart.
     * 
     * @param orderSummary The summary of the order.
     * @param store        The merchant store.
     * @param language     The language used for calculations.
     * @return {@link OrderTotalSummary} containing the calculated totals.
     * @throws ServiceException If an error occurs during calculation.
     */
    OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary, MerchantStore store, Language language)
            throws ServiceException;

    /**
     * Can be used to calculate the final prices of all items contained in the checkout page.
     * 
     * @param shoppingCart The shopping cart to calculate totals for.
     * @param customer     The customer associated with the shopping cart.
     * @param store        The merchant store.
     * @param language     The language used for calculations.
     * @return {@link OrderTotalSummary} containing the calculated totals.
     * @throws ServiceException If an error occurs during calculation.
     */
    OrderTotalSummary calculateShoppingCartTotal(final ShoppingCart shoppingCart, final Customer customer,
            final MerchantStore store, final Language language) throws ServiceException;

    /**
     * Can be used to calculate the final prices of all items contained in a ShoppingCart.
     * 
     * @param shoppingCart The shopping cart to calculate totals for.
     * @param store        The merchant store.
     * @param language     The language used for calculations.
     * @return {@link OrderTotalSummary} containing the calculated totals.
     * @throws ServiceException If an error occurs during calculation.
     */
    OrderTotalSummary calculateShoppingCartTotal(final ShoppingCart shoppingCart, final MerchantStore store,
            final Language language) throws ServiceException;
}
