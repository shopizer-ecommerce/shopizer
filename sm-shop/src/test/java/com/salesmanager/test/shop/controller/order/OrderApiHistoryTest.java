package com.salesmanager.test.shop.controller.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
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
import com.salesmanager.shop.model.order.history.ReadableOrderStatusHistory;
import com.salesmanager.shop.model.order.v0.ReadableOrder;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.api.v1.order.OrderApi;
import com.salesmanager.shop.store.controller.order.facade.OrderFacade;

/**
 * Unit tests for OrderApi getOrderHistory endpoint.
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderApiHistoryTest {

	@Mock
	private CustomerService customerService;

	@Mock
	private OrderFacade orderFacade;

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
	public void testCustomerOrderHistorySuccess() throws Exception {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setNick("testuser");

		ReadableCustomer readableCustomer = new ReadableCustomer();
		readableCustomer.setId(1L);

		ReadableOrder order = new ReadableOrder();
		order.setId(100L);
		order.setCustomer(readableCustomer);

		ReadableOrderStatusHistory history = new ReadableOrderStatusHistory();
		history.setId(1L);
		history.setOrderId(100L);
		history.setOrderStatus("ORDERED");

		when(customerService.getByNick("testuser")).thenReturn(customer);
		when(orderFacade.getReadableOrder(100L, merchantStore, language)).thenReturn(order);
		when(orderFacade.getReadableOrderHistory(100L, merchantStore, language))
				.thenReturn(Collections.singletonList(history));

		List<ReadableOrderStatusHistory> result = orderApi.getOrderHistory(100L, merchantStore, language, request);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("ORDERED", result.get(0).getOrderStatus());
	}

	@Test(expected = UnauthorizedException.class)
	public void testCustomerOrderHistoryUnauthorizedCustomerMismatch() throws Exception {
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

		orderApi.getOrderHistory(100L, merchantStore, language, request);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testCustomerOrderHistoryOrderNotFound() throws Exception {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setNick("testuser");

		when(customerService.getByNick("testuser")).thenReturn(customer);
		when(orderFacade.getReadableOrder(999L, merchantStore, language)).thenReturn(null);

		orderApi.getOrderHistory(999L, merchantStore, language, request);
	}
}
