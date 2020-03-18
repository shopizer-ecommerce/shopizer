package com.salesmanager.shop.store.api.v1.order;

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.helper.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.model.order.PersistableAnonymousOrderApi;
import com.salesmanager.shop.model.order.PersistableOrderApi;
import com.salesmanager.shop.model.order.ReadableOrder;
import com.salesmanager.shop.model.order.ReadableOrderList;
import com.salesmanager.shop.populator.customer.ReadableCustomerPopulator;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.customer.facade.CustomerFacade;
import com.salesmanager.shop.store.controller.order.facade.OrderFacade;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;
import com.salesmanager.shop.utils.LocaleUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1")
public class OrderApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderApi.class);

	@Inject
	private CustomerService customerService;

	@Inject
	private OrderFacade orderFacade;

	@Inject
	private ShoppingCartService shoppingCartService;

	@Autowired
	private CustomerFacade customerFacade;

	@Inject
	private UserFacade userFacade;

	/**
	 * Get a list of orders for a given customer accept request parameter
	 * 'start' start index for count accept request parameter 'max' maximum
	 * number count, otherwise returns all Used for administrators
	 *
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/private/orders/customers/{id}" }, method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
	public ReadableOrderList list(@PathVariable final Long id,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "count", required = false) Integer count, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletResponse response) throws Exception {

		Customer customer = customerService.getById(id);

		if (customer == null) {
			LOGGER.error("Customer is null for id " + id);
			response.sendError(404, "Customer is null for id " + id);
			return null;
		}

		if (start == null) {
			start = new Integer(0);
		}
		if (count == null) {
			count = new Integer(100);
		}

		ReadableCustomer readableCustomer = new ReadableCustomer();
		ReadableCustomerPopulator customerPopulator = new ReadableCustomerPopulator();
		customerPopulator.populate(customer, readableCustomer, merchantStore, language);

		ReadableOrderList returnList = orderFacade.getReadableOrderList(merchantStore, customer, start, count,
				language);

		List<ReadableOrder> orders = returnList.getOrders();

		if (!CollectionUtils.isEmpty(orders)) {
			for (ReadableOrder order : orders) {
				order.setCustomer(readableCustomer);
			}
		}

		return returnList;
	}

	/**
	 * List orders for authenticated customers
	 *
	 * @param start
	 * @param count
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/auth/orders" }, method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
	public ReadableOrderList list(@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "count", required = false) Integer count, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Principal principal = request.getUserPrincipal();
		String userName = principal.getName();

		Customer customer = customerService.getByNick(userName);

		if (customer == null) {
			response.sendError(401, "Error while listing orders, customer not authorized");
			return null;
		}

		if (start == null) {
			start = new Integer(0);
		}
		if (count == null) {
			count = new Integer(100);
		}

		ReadableCustomer readableCustomer = new ReadableCustomer();
		ReadableCustomerPopulator customerPopulator = new ReadableCustomerPopulator();
		customerPopulator.populate(customer, readableCustomer, merchantStore, language);

		ReadableOrderList returnList = orderFacade.getReadableOrderList(merchantStore, customer, start, count,
				language);

		if (returnList == null) {
			returnList = new ReadableOrderList();
		}

		List<ReadableOrder> orders = returnList.getOrders();
		if (!CollectionUtils.isEmpty(orders)) {
			for (ReadableOrder order : orders) {
				order.setCustomer(readableCustomer);
			}
		}
		return returnList;
	}

	/**
	 * This method returns list of all the orders for the admin.This is not
	 * bound to any specific stores and will get list of all the orders
	 * available for this instance
	 *
	 * @param start
	 * @param count
	 * @return List of orders
	 * @throws Exception
	 */
	@RequestMapping(
      value = {"private/orders"},
      method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.ACCEPTED)
  @ResponseBody
  public ReadableOrderList getOrders(
      @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
      @RequestParam(value = "count", required = false, defaultValue = "100") Integer count,
		@ApiIgnore MerchantStore merchantStore, 
		@ApiIgnore Language language) {


			// superadmin, admin and admin_catalogue
			String authenticatedUser = userFacade.authenticatedUser();
			if (authenticatedUser == null) {
				throw new UnauthorizedException();
			}
	
			userFacade.authorizedGroup(authenticatedUser, Stream.of(Constants.GROUP_SUPERADMIN, Constants.GROUP_ADMIN, Constants.GROUP_ADMIN_ORDER, Constants.GROUP_ADMIN_RETAIL).collect(Collectors.toList()));
	  
		  
			return orderFacade.getReadableOrderList(start, count, merchantStore);
		

  }

  /**
   * Get a given order by id
   *
   * @param id
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @RequestMapping(
      value = {"/auth/orders/{id}"},
      method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.ACCEPTED)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en")
  })
  public ReadableOrder getOrder(
      @PathVariable final Long id,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletRequest request,
      HttpServletResponse response)
      throws Exception {
    Principal principal = request.getUserPrincipal();
    String userName = principal.getName();

    Customer customer = customerService.getByNick(userName);

    if (customer == null) {
      response.sendError(401, "Error while performing checkout customer not authorized");
      return null;
    }

    ReadableOrder order = orderFacade.getReadableOrder(id, merchantStore, language);

    if (order == null) {
      LOGGER.error("Order is null for id " + id);
      response.sendError(404, "Order is null for id " + id);
      return null;
    }

    if (order.getCustomer() == null) {
      LOGGER.error("Order is null for customer " + principal);
      response.sendError(404, "Order is null for customer " + principal);
      return null;
    }

    if (order.getCustomer().getId() != null
        && order.getCustomer().getId().longValue() != customer.getId().longValue()) {
      LOGGER.error("Order is null for customer " + principal);
      response.sendError(404, "Order is null for customer " + principal);
      return null;
    }

    return order;
  }

	/**
	 * Action for performing a checkout on a given shopping cart
	 *
	 * @param id
	 * @param order
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/auth/cart/{code}/checkout" }, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
	public PersistableOrderApi checkout(@PathVariable final String code, @Valid @RequestBody PersistableOrderApi order,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, HttpServletRequest request,
			HttpServletResponse response, Locale locale) throws Exception {

		try {
			Principal principal = request.getUserPrincipal();
			String userName = principal.getName();

			Customer customer = customerService.getByNick(userName);

			if (customer == null) {
				response.sendError(401, "Error while performing checkout customer not authorized");
				return null;
			}

			ShoppingCart cart = shoppingCartService.getByCode(code, merchantStore);
			if (cart == null) {
				throw new ResourceNotFoundException("Cart code " + code + " does not exist");
			}

			order.setShoppingCartId(cart.getId());
			order.setCustomerId(customer.getId());

			Order modelOrder = orderFacade.processOrder(order, customer, merchantStore, language, locale);
			Long orderId = modelOrder.getId();
			order.setId(orderId);

			// hash payment token
			order.getPayment().setPaymentToken("***");

			return order;

		} catch (Exception e) {
			LOGGER.error("Error while processing checkout", e);
			try {
				response.sendError(503, "Error while processing checkout " + e.getMessage());
			} catch (Exception ignore) {
			}
			return null;
		}
	}

	@RequestMapping(value = { "/cart/{code}/checkout" }, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
	public PersistableOrderApi checkout(@PathVariable final String code,
			@Valid @RequestBody PersistableAnonymousOrderApi order, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		Validate.notNull(order.getCustomer(), "Customer must not be null");

		ShoppingCart cart;
		try {
			cart = shoppingCartService.getByCode(code, merchantStore);

			if (cart == null) {
				throw new ResourceNotFoundException("Cart code " + code + " does not exist");
			}

			Customer customer = new Customer();
			customer = customerFacade.populateCustomerModel(customer, order.getCustomer(), merchantStore, language);

			order.setShoppingCartId(cart.getId());

			Order modelOrder = orderFacade.processOrder(order, customer, merchantStore, language,
					LocaleUtils.getLocale(language));
			Long orderId = modelOrder.getId();
			order.setId(orderId);
			// set customer id
			order.getCustomer().setId(modelOrder.getCustomerId());

			// hash payment token
			order.getPayment().setPaymentToken("***");

			return order;

		} catch (Exception e) {
			throw new ServiceRuntimeException("Error during checkout " + e.getMessage(), e);
		}

	}
}
