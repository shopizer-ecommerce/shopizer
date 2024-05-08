package com.salesmanager.shop.store.api.v1.product;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.store.controller.product.facade.ProductFacade;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/api/v1")
public class ProductRelationshipApi {

  @Inject private ProductFacade productFacade;

  @Inject private ProductService productService;

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductRelationshipApi.class);


  @RequestMapping(value = "/product/{id}/related", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(
      httpMethod = "GET",
      value =
          "Get product related items. This is used for doing cross-sell and up-sell functionality on a product details page",
      notes = "",
      produces = "application/json",
      response = List.class)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public List<ReadableProduct> getAll(
      @PathVariable final Long id,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletResponse response)
      throws Exception {

    try {
      // product exist
      Product product = productService.getById(id);

      if (product == null) {
        response.sendError(404, "Product id " + id + " does not exists");
        return null;
      }

      List<ReadableProduct> relatedItems =
          productFacade.relatedItems(merchantStore, product, language);

      return relatedItems;

    } catch (Exception e) {
      LOGGER.error("Error while getting product reviews", e);
      try {
        response.sendError(503, "Error while getting product reviews" + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }



}
