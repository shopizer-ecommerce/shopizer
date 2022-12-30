package com.salesmanager.shop.store.api.v1.shoppingCart;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.security.Principal;
import java.util.Arrays;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.shoppingcart.PersistableShoppingCartItem;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCart;
import com.salesmanager.shop.store.api.exception.OperationNotAllowedException;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.customer.facade.v1.CustomerFacade;
import com.salesmanager.shop.store.controller.shoppingCart.facade.ShoppingCartFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/api/v1")
@Api(tags = { "Shopping cart api" })
@SwaggerDefinition(tags = {
		@Tag(name = "Shopping cart resource", description = "Add, remove and retrieve shopping carts") })
public class ShoppingCartApi {

	@Inject
	private ShoppingCartFacade shoppingCartFacade;

	@Inject
	private com.salesmanager.shop.store.controller.shoppingCart.facade.v1.ShoppingCartFacade shoppingCartFacadev1;

	@Inject
	private CustomerService customerService;

	@Autowired
	private CustomerFacade customerFacadev1;
	
	@Autowired
	private com.salesmanager.shop.store.controller.customer.facade.CustomerFacade customerFacade;

	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartApi.class);

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/cart")
	@ApiOperation(httpMethod = "POST", value = "Add product to shopping cart when no cart exists, this will create a new cart id", notes = "No customer ID in scope. Add to cart for non authenticated users, as simple as {\"product\":1232,\"quantity\":1}", produces = "application/json", response = ReadableShoppingCart.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableShoppingCart addToCart(
			@Valid @RequestBody PersistableShoppingCartItem shoppingCartItem,
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {
		return shoppingCartFacade.addToCart(shoppingCartItem, merchantStore, language);
	}

	@PutMapping(value = "/cart/{code}")
	@ApiOperation(httpMethod = "PUT", value = "Add to an existing shopping cart or modify an item quantity", notes = "No customer ID in scope. Modify cart for non authenticated users, as simple as {\"product\":1232,\"quantity\":0} for instance will remove item 1234 from cart", produces = "application/json", response = ReadableShoppingCart.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ResponseEntity<ReadableShoppingCart> modifyCart(
			@PathVariable String code,
			@Valid @RequestBody PersistableShoppingCartItem shoppingCartItem, 
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, 
			HttpServletResponse response) {

		try {
			ReadableShoppingCart cart = shoppingCartFacade.modifyCart(code, shoppingCartItem, merchantStore, language);

			if (cart == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(cart, HttpStatus.CREATED);

		} catch (Exception e) {
			if(e instanceof ResourceNotFoundException) {
				throw (ResourceNotFoundException)e;
			} else {
				throw new ServiceRuntimeException(e);
			}

		} 
	}
	

	@PostMapping(value = "/cart/{code}/promo/{promo}")
	@ApiOperation(httpMethod = "POST", value = "Add promo / coupon to an existing cart", produces = "application/json", response = ReadableShoppingCart.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ResponseEntity<ReadableShoppingCart> modifyCart(
			@PathVariable String code,//shopping cart code
			@PathVariable String promo,
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, 
			HttpServletResponse response) {

		try {
			ReadableShoppingCart cart = shoppingCartFacade.modifyCart(code, promo, merchantStore, language);

			if (cart == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(cart, HttpStatus.CREATED);

		} catch (Exception e) {
			if(e instanceof ResourceNotFoundException) {
				throw (ResourceNotFoundException)e;
			} else {
				throw new ServiceRuntimeException(e);
			}

		} 
	}


	@PostMapping(value = "/cart/{code}/multi", consumes = { "application/json" }, produces = { "application/json" })
	@ApiOperation(httpMethod = "POST", value = "Add to an existing shopping cart or modify an item quantity", notes = "No customer ID in scope. Modify cart for non authenticated users, as simple as {\"product\":1232,\"quantity\":0} for instance will remove item 1234 from cart", produces = "application/json", response = ReadableShoppingCart.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ResponseEntity<ReadableShoppingCart> modifyCart(
			@PathVariable String code,
			@Valid @RequestBody PersistableShoppingCartItem[] shoppingCartItems, 
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		try {
			ReadableShoppingCart cart = shoppingCartFacade.modifyCartMulti(code, Arrays.asList(shoppingCartItems),
					merchantStore, language);

			return new ResponseEntity<>(cart, HttpStatus.CREATED);

		} catch (Exception e) {
			if(e instanceof ResourceNotFoundException) {
				throw (ResourceNotFoundException)e;
			} else {
				throw new ServiceRuntimeException(e);
			}

		}
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/cart/{code}", method = RequestMethod.GET)
	@ApiOperation(httpMethod = "GET", value = "Get a chopping cart by code", notes = "", produces = "application/json", response = ReadableShoppingCart.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableShoppingCart getByCode(@PathVariable String code,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, HttpServletResponse response) {

		try {
	
			ReadableShoppingCart cart = shoppingCartFacade.getByCode(code, merchantStore, language);

			if (cart == null) {
				response.sendError(404, "No ShoppingCart found for customer code : " + code);
				return null;
			}

			return cart;

		} catch (Exception e) {
			if(e instanceof ResourceNotFoundException) {
				throw (ResourceNotFoundException)e;
			} else {
				throw new ServiceRuntimeException(e);
			}

		}
	}

	@Deprecated
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/customers/{id}/cart", method = RequestMethod.POST)
	@ApiOperation(httpMethod = "POST", value = "Add product to a specific customer shopping cart", notes = "", produces = "application/json", response = ReadableShoppingCart.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableShoppingCart addToCart(@PathVariable Long id,
			@Valid @RequestBody PersistableShoppingCartItem shoppingCartItem, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletResponse response) {
		
		throw new OperationNotAllowedException("API is no more supported. Authenticate customer first then get customer cart");

	}

	@Deprecated
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/auth/customer/{id}/cart", method = RequestMethod.GET)
	@ApiOperation(httpMethod = "GET", value = "Get a shopping cart by customer id. Customer must be authenticated", notes = "", produces = "application/json", response = ReadableShoppingCart.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableShoppingCart getByCustomer(@PathVariable Long id, // customer
																					// id
			@RequestParam Optional<String> cart, // cart code
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, HttpServletRequest request,
			HttpServletResponse response) {

		Principal principal = request.getUserPrincipal();

		// lookup customer
		Customer customer = customerService.getById(id);

		if (customer == null) {
			throw new ResourceNotFoundException("No Customer found for id [" + id + "]");
		}

		customerFacadev1.authorize(customer, principal);

		ReadableShoppingCart readableCart = shoppingCartFacadev1.get(cart, id, merchantStore, language);

		if (readableCart == null) {
			throw new ResourceNotFoundException("No cart found for customerid [" + id + "]");
		}

		return readableCart;

	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/auth/customer/cart", method = RequestMethod.GET)
	@ApiOperation(httpMethod = "GET", value = "Get a shopping cart by authenticated customer", notes = "", produces = "application/json", response = ReadableShoppingCart.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableShoppingCart getByCustomer(
			@RequestParam Optional<String> cart, // cart code
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language, 
			HttpServletRequest request,
			HttpServletResponse response) {

		Principal principal = request.getUserPrincipal();
		Customer customer = null;
		try {
			customer = customerFacade.getCustomerByUserName(principal.getName(), merchantStore);
		} catch (Exception e) {
			throw new ServiceRuntimeException("Exception while getting customer [ " + principal.getName() + "]");
		}
		
		if (customer == null) {
			throw new ResourceNotFoundException("No Customer found for principal[" + principal.getName() + "]");
		}
		
		customerFacadev1.authorize(customer, principal);
		ReadableShoppingCart readableCart = shoppingCartFacadev1.get(cart, customer.getId(), merchantStore, language);

		if (readableCart == null) {
			throw new ResourceNotFoundException("No cart found for customer [" + principal.getName() + "]");
		}

		return readableCart;

	}

	@DeleteMapping(value = "/cart/{code}/product/{sku}", produces = { APPLICATION_JSON_VALUE })
	@ApiOperation(httpMethod = "DELETE", value = "Remove a product from a specific cart", notes = "If body set to true returns remaining cart in body, empty cart gives empty body. If body set to false no body ", produces = "application/json", response = ReadableShoppingCart.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en"),
			@ApiImplicitParam(name = "body", dataType = "boolean", defaultValue = "false"), })
	public ResponseEntity<ReadableShoppingCart> deleteCartItem(@PathVariable("code") String cartCode,
			@PathVariable("sku") String sku, 
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language,
			@RequestParam(defaultValue = "false") boolean body) throws Exception {

		ReadableShoppingCart updatedCart = shoppingCartFacade.removeShoppingCartItem(cartCode, sku, merchantStore,
				language, body);
		if (body) {
			return new ResponseEntity<>(updatedCart, HttpStatus.OK);
		}
		return new ResponseEntity<>(updatedCart, HttpStatus.NO_CONTENT);
	}
}
