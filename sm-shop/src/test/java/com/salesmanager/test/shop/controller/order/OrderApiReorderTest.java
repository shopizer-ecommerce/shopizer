package com.salesmanager.test.shop.controller.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.model.order.ReadableOrderProduct;
import com.salesmanager.shop.model.order.v0.ReadableOrder;
import com.salesmanager.shop.model.shoppingcart.PersistableShoppingCartItem;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCart;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.api.v1.order.OrderApi;
import com.salesmanager.shop.store.controller.order.facade.OrderFacade;
import com.salesmanager.shop.store.controller.shoppingCart.facade.ShoppingCartFacade;

/**
 * Unit tests for OrderApi reorder endpoint.
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderApiReorderTest {

	@Mock
	private CustomerService customerService;

	@Mock
	private OrderFacade orderFacade;

	@Mock
	private ShoppingCartFacade shoppingCartFacade;

	@InjectMocks
	private OrderApi orderApi;

	private MerchantStore merchantStore;
	private Language language;
	private HttpServletRequest request;
	private Principal principal;

	@Before
	public void setUp() {
		merchantStore = new MerchantStore();
		merchantStore.setCode("DEFAULT");

		language = new Language();
		language.setCode("en");

		request = mock(HttpServletRequest.class);
		principal = mock(Principal.class);
		when(request.getUserPrincipal()).thenReturn(principal);
		when(principal.getName()).thenReturn("testuser");
	}

	@Test
	public void testCustomerReorderSuccess() throws Exception {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setNick("testuser");

		ReadableCustomer readableCustomer = new ReadableCustomer();
		readableCustomer.setId(1L);

		ReadableOrder order = new ReadableOrder();
		order.setId(100L);
		order.setCustomer(readableCustomer);

		List<ReadableOrderProduct> products = new ArrayList<>();
		ReadableOrderProduct product1 = new ReadableOrderProduct();
		product1.setSku("PRODUCT-1");
		product1.setOrderedQuantity(2);
		products.add(product1);

		ReadableOrderProduct product2 = new ReadableOrderProduct();
		product2.setSku("PRODUCT-2");
		product2.setOrderedQuantity(1);
		products.add(product2);

		order.setProducts(products);

		ReadableShoppingCart cart = new ReadableShoppingCart();
		cart.setCode("CART-100");

		when(customerService.getByNick("testuser")).thenReturn(customer);
		when(orderFacade.getReadableOrder(100L, merchantStore, language)).thenReturn(order);
		when(shoppingCartFacade.addToCart(eq(customer), any(PersistableShoppingCartItem.class), eq(merchantStore),
				eq(language))).thenReturn(cart);

		ReadableShoppingCart result = orderApi.reorder(100L, merchantStore, language, request);

		assertNotNull(result);
		assertEquals("CART-100", result.getCode());
		verify(shoppingCartFacade, times(2)).addToCart(eq(customer), any(PersistableShoppingCartItem.class),
				eq(merchantStore), eq(language));
	}

	@Test(expected = UnauthorizedException.class)
	public void testCustomerReorderUnauthorizedCustomerMismatch() throws Exception {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setNick("testuser");

		ReadableCustomer anotherCustomer = new ReadableCustomer();
		anotherCustomer.setId(2L);

		ReadableOrder order = new ReadableOrder();
		order.setId(100L);
		order.setCustomer(anotherCustomer);

		when(customerService.getByNick("testuser")).thenReturn(customer);
		when(orderFacade.getReadableOrder(100L, merchantStore, language)).thenReturn(order);

		orderApi.reorder(100L, merchantStore, language, request);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testCustomerReorderOrderNotFound() throws Exception {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setNick("testuser");

		when(customerService.getByNick("testuser")).thenReturn(customer);
		when(orderFacade.getReadableOrder(999L, merchantStore, language)).thenReturn(null);

		orderApi.reorder(999L, merchantStore, language, request);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testCustomerReorderEmptyProducts() throws Exception {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setNick("testuser");

		ReadableCustomer readableCustomer = new ReadableCustomer();
		readableCustomer.setId(1L);

		ReadableOrder order = new ReadableOrder();
		order.setId(100L);
		order.setCustomer(readableCustomer);
		order.setProducts(Collections.emptyList());

		when(customerService.getByNick("testuser")).thenReturn(customer);
		when(orderFacade.getReadableOrder(100L, merchantStore, language)).thenReturn(order);

		orderApi.reorder(100L, merchantStore, language, request);
	}

	@Test(expected = UnauthorizedException.class)
	public void testCustomerReorderCustomerNotFound() throws Exception {
		when(customerService.getByNick("testuser")).thenReturn(null);

		orderApi.reorder(100L, merchantStore, language, request);
	}
}
