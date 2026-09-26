package com.salesmanager.test.shop.controller.cart;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.v1.shoppingCart.ShoppingCartApi;
import com.salesmanager.shop.store.controller.shoppingCart.facade.ShoppingCartFacade;

/**
 * Unit tests for ShoppingCartApi deleteCart endpoint.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartApiTest {

	@Mock
	private ShoppingCartFacade shoppingCartFacade;

	@InjectMocks
	private ShoppingCartApi shoppingCartApi;

	private MerchantStore merchantStore;

	@Before
	public void setUp() {
		merchantStore = new MerchantStore();
		merchantStore.setCode("DEFAULT");
	}

	@Test
	public void testDeleteCartSuccess() throws Exception {
		String cartCode = "cart-12345";
		ShoppingCart mockCart = mock(ShoppingCart.class);

		when(shoppingCartFacade.getShoppingCartModel(cartCode, merchantStore)).thenReturn(mockCart);

		shoppingCartApi.deleteCart(cartCode, merchantStore);

		verify(shoppingCartFacade).getShoppingCartModel(cartCode, merchantStore);
		verify(shoppingCartFacade).deleteShoppingCart(cartCode, merchantStore);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testDeleteCartNotFound() throws Exception {
		String cartCode = "non-existent-cart";

		when(shoppingCartFacade.getShoppingCartModel(cartCode, merchantStore)).thenReturn(null);

		shoppingCartApi.deleteCart(cartCode, merchantStore);
	}
}
