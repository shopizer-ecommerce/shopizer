package com.salesmanager.core.business.services.shoppingcart;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.ShippingProduct;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;

import java.util.List;

public interface ShoppingCartService extends SalesManagerEntityService<Long, ShoppingCart> {

    ShoppingCart getShoppingCart(Customer customer) throws ServiceException;

    void saveOrUpdate(ShoppingCart shoppingCart) throws ServiceException;

    ShoppingCart getById(Long id, MerchantStore store) throws ServiceException;

    ShoppingCart getByCode(String code, MerchantStore store) throws ServiceException;

    ShoppingCart getByCustomer(Customer customer) throws ServiceException;

    /**
     * Creates a list of ShippingProduct based on the ShoppingCart if items are
     * virtual return list will be null
     */
    List<ShippingProduct> createShippingProduct(ShoppingCart cart) throws ServiceException;

    /**
     * Looks if the items in the ShoppingCart are free of charges
     */
    boolean isFreeShoppingCart(ShoppingCart cart) throws ServiceException;

    boolean isFreeShoppingCart(List<ShoppingCartItem> items) throws ServiceException;

    /**
     * Populates a ShoppingCartItem from a Product and attributes if any
     */
    ShoppingCartItem populateShoppingCartItem(Product product) throws ServiceException;

    void deleteCart(ShoppingCart cart) throws ServiceException;

    void removeShoppingCart(ShoppingCart cart) throws ServiceException;

    ShoppingCart mergeShoppingCarts(final ShoppingCart userShoppingCart, final ShoppingCart sessionCart,
                                    final MerchantStore store) throws Exception;

    /**
     * Determines if the shopping cart requires shipping
     */
    boolean requiresShipping(ShoppingCart cart) throws ServiceException;

    /**
     * Removes a shopping cart item
     */
    void deleteShoppingCartItem(Long id);

}