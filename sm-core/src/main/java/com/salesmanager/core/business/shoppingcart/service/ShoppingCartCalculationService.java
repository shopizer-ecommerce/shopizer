/**
 *
 */
package com.salesmanager.core.business.shoppingcart.service;

import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.OrderTotalSummary;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCart;

/**
 * Interface declaring various methods used to calculate {@link ShoppingCart}
 * object details.
 * @author Umesh Awasthi
 * @since 1.2
 *
 */
public interface ShoppingCartCalculationService
{
    /**
     * Method which will be used to calculate price for each line items as
     * well Total and Sub-total for {@link ShoppingCart}.
     * @param cartModel ShoopingCart mode representing underlying DB object
     * @param customer
     * @param store
     * @param language
     * @throws ServiceException
     */
    public OrderTotalSummary calculate(final ShoppingCart cartModel,final Customer customer, final MerchantStore store, final Language language) throws ServiceException;


    /**
     * Method which will be used to calculate price for each line items as
     * well Total and Sub-total for {@link ShoppingCart}.
     * @param cartModel ShoopingCart mode representing underlying DB object
     * @param store
     * @param language
     * @throws ServiceException
     */
    public OrderTotalSummary calculate(final ShoppingCart cartModel, final MerchantStore store, final Language language) throws ServiceException;
}
