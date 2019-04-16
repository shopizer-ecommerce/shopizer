package com.salesmanager.shop.store.api.v1.product;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.relationship.ProductRelationshipService;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableProductList;
import com.salesmanager.shop.store.controller.items.facade.ProductItemsFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Used for product grouping such as featured items
 *
 * @author carlsamson
 */
@Controller
@RequestMapping("/api/v1")
public class ProductGroupApi {

  @Inject private LanguageService languageService;

  @Inject private LanguageUtils languageUtils;

  @Inject private ProductService productService;

  @Inject private ProductRelationshipService productRelationshipService;

  @Inject private MerchantStoreService merchantStoreService;

  @Inject private ProductItemsFacade productItemsFacade;

  @Inject private StoreFacade storeFacade;

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductGroupApi.class);

  /**
   * Query for a product group public/products/group/{code}?lang=fr|en no lang it will take session
   * lang or default store lang code can be any code used while creating product group, defeult
   * being FEATURED
   *
   * @param store
   * @param language
   * @param groupCode
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @RequestMapping("/products/group/{code}")
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public ReadableProductList getProductItemsByGroup(
      @PathVariable final String code,
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language,
      HttpServletResponse response)
      throws Exception {
    try {
      ReadableProductList list = productItemsFacade.listItemsByGroup(code, merchantStore, language);

      if (list == null) {
        response.sendError(404, "Group not fount for code " + code);
        return null;
      }

      return list;

    } catch (Exception e) {
      LOGGER.error("Error while getting products", e);
      response.sendError(503, "An error occured while retrieving products " + e.getMessage());
    }

    return null;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = "/private/products/{productId}/group/{code}", method = RequestMethod.POST)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody ReadableProductList addProductToGroup(
      @PathVariable Long productId,
      @PathVariable String code,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletResponse response) {

    try {
      // get the product
      Product product = productService.getById(productId);

      if (product == null) {
        response.sendError(404, "Product not fount for id " + productId);
        return null;
      }

      ReadableProductList list =
          productItemsFacade.addItemToGroup(product, code, merchantStore, language);

      return list;

    } catch (Exception e) {
      LOGGER.error("Error while adding product to group", e);
      try {
        response.sendError(503, "Error while adding product to group " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(
      value = "/private/products/{productId}/group/{code}",
      method = RequestMethod.DELETE)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody ReadableProductList removeProductFromCategory(
      @PathVariable Long productId,
      @PathVariable String code,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletRequest request,
      HttpServletResponse response) {

    try {
      // get the product
      Product product = productService.getById(productId);

      if (product == null) {
        response.sendError(404, "Product not fount for id " + productId);
        return null;
      }

      ReadableProductList list =
          productItemsFacade.removeItemFromGroup(product, code, merchantStore, language);

      return list;

    } catch (Exception e) {
      LOGGER.error("Error while removing product from category", e);
      try {
        response.sendError(503, "Error while removing product from category " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }
}
