package com.salesmanager.shop.store.controller.shoppingCart.facade.v1;

import java.util.Optional;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCart;

public interface ShoppingCartFacade {
	
	ReadableShoppingCart get(Optional<String> cart, Long customerId, MerchantStore store, Language language);

}
