package com.salesmanager.shop.store.api.v1.shoppingCart;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.shoppingcart.PersistableShoppingCartItem;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCart;
import com.salesmanager.shop.store.controller.shoppingCart.facade.ShoppingCartFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/api/v1")
public class ShoppingCartApi {

  @Inject private StoreFacade storeFacade;

  @Inject private LanguageUtils languageUtils;

  @Inject private ShoppingCartFacade shoppingCartFacade;

  @Inject private CustomerService customerService;

  private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartApi.class);

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = "/cart", method = RequestMethod.POST)
  @ApiOperation(
      httpMethod = "POST",
      value = "Add product to shopping cart",
      notes =
          "No customer ID in scope. Add to cart for non authenticated users, as simple as {\"product\":1232,\"quantity\":1}",
      produces = "application/json",
      response = ReadableShoppingCart.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody ReadableShoppingCart addToCart(
      @Valid @RequestBody PersistableShoppingCartItem shoppingCartItem,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletResponse response) {

    try {
      ReadableShoppingCart cart =
          shoppingCartFacade.addToCart(shoppingCartItem, merchantStore, language);

      return cart;

    } catch (Exception e) {
      LOGGER.error("Error while adding product to cart", e);
      try {
        response.sendError(503, "Error while adding product to cart " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = "/cart/{code}", method = RequestMethod.PUT)
  @ApiOperation(
      httpMethod = "PUT",
      value = "Modify an existing shopping cart",
      notes =
          "No customer ID in scope. Modify cart for non authenticated users, as simple as {\"product\":1232,\"quantity\":0} for instance will remove item 1234 from cart",
      produces = "application/json",
      response = ReadableShoppingCart.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody ReadableShoppingCart modifyCart(
      @PathVariable String code,
      @Valid @RequestBody PersistableShoppingCartItem shoppingCartItem,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletResponse response) {

    try {
      ReadableShoppingCart cart =
          shoppingCartFacade.addToCart(code, shoppingCartItem, merchantStore, language);

      return cart;

    } catch (Exception e) {
      LOGGER.error("Error while modyfing cart " + code + " ", e);
      try {
        response.sendError(503, "Error while modyfing cart " + code + " " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/cart/{code}", method = RequestMethod.GET)
  @ApiOperation(
      httpMethod = "GET",
      value = "Get a chopping cart by code",
      notes = "",
      produces = "application/json",
      response = ReadableShoppingCart.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody ReadableShoppingCart getByCode(
      @PathVariable String code,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletResponse response) {

    try {
      ReadableShoppingCart cart = shoppingCartFacade.getByCode(code, merchantStore, language);

      if (cart == null) {
        response.sendError(404, "No ShoppingCart found for customer code : " + code);
        return null;
      }

      return cart;

    } catch (Exception e) {
      LOGGER.error("Error while getting cart", e);
      try {
        response.sendError(503, "Error while getting cart " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = "/customers/{id}/cart", method = RequestMethod.POST)
  @ApiOperation(
      httpMethod = "POST",
      value = "Add product to a specific customer shopping cart",
      notes = "",
      produces = "application/json",
      response = ReadableShoppingCart.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody ReadableShoppingCart addToCart(
      @PathVariable Long id,
      @Valid @RequestBody PersistableShoppingCartItem shoppingCartItem,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletResponse response) {

    try {
      // lookup customer
      Customer customer = customerService.getById(id);

      if (customer == null) {
        response.sendError(404, "No Customer found for ID : " + id);
        return null;
      }

      ReadableShoppingCart cart =
          shoppingCartFacade.addToCart(customer, shoppingCartItem, merchantStore, language);

      return cart;

    } catch (Exception e) {
      LOGGER.error("Error while adding product to cart", e);
      try {
        response.sendError(503, "Error while adding product to cart " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/customers/{id}/cart", method = RequestMethod.GET)
  @ApiOperation(
      httpMethod = "GET",
      value = "Get a chopping cart by id",
      notes = "",
      produces = "application/json",
      response = ReadableShoppingCart.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody ReadableShoppingCart get(
      @PathVariable Long id,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletResponse response) {

    try {
      // lookup customer
      Customer customer = customerService.getById(id);

      if (customer == null) {
        response.sendError(404, "No Customer found for ID : " + id);
        return null;
      }

      ReadableShoppingCart cart = shoppingCartFacade.getCart(customer, merchantStore, language);

      if (cart == null) {
        response.sendError(404, "No ShoppingCart found for customer ID : " + id);
        return null;
      }

      return cart;

    } catch (Exception e) {
      LOGGER.error("Error while getting cart", e);
      try {
        response.sendError(503, "Error while getting cart " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }
}
