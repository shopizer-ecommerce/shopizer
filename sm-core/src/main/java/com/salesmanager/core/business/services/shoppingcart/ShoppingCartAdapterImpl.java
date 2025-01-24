package com.salesmanager.core.business.services.shoppingcart;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.ShippingProduct;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service("shoppingCartAdapter")
public class ShoppingCartAdapterImpl implements ShoppingCartAdapter {

    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public ShoppingCart getById(Long cartId, MerchantStore store) throws Exception {
        return shoppingCartService.getById(cartId, store);
    }

    @Override
    public void saveOrUpdate(ShoppingCart shoppingCart) throws Exception {
        shoppingCartService.saveOrUpdate(shoppingCart);
    }

    @Override
    public List<ShippingProduct> createShippingProducts(ShoppingCart cart) throws Exception {
        return shoppingCartService.createShippingProduct(cart);
    }
}