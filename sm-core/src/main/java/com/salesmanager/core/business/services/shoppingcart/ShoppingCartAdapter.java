package com.salesmanager.core.business.services.shoppingcart;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.ShippingProduct;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;

import java.util.List;

/**
 * Adapter interface for shopping cart operations needed by the orders domain
 */
public interface ShoppingCartAdapter {

    /**
     * Get shopping cart by ID
     */
    ShoppingCart getById(Long cartId, MerchantStore store) throws Exception;

    /**
     * Save or update shopping cart
     */
    void saveOrUpdate(ShoppingCart shoppingCart) throws Exception;

    /**
     * Create shipping products from cart
     */
    List<ShippingProduct> createShippingProducts(ShoppingCart cart) throws Exception;
}