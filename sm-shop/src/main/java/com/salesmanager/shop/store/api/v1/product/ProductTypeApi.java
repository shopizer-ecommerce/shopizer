package com.salesmanager.shop.store.api.v1.product;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.PersistableProduct;
import com.salesmanager.shop.store.controller.product.facade.ProductFacade;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

/**
 * API to create, read, update and delete a Product API to create Manufacturer
 *
 * @author Carl Samson
 */
@Controller
@RequestMapping("/api/v1")
public class ProductTypeApi {



  @Inject private ProductService productService;

  @Inject private ProductFacade productFacade;


  private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeApi.class);

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(
      value = {"/private/product/type", "/auth/products"},
      method = RequestMethod.POST)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody PersistableProduct create(
      @Valid @RequestBody PersistableProduct product,
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language,
      HttpServletRequest request,
      HttpServletResponse response) {

    try {
      productFacade.saveProduct(merchantStore, product, language);
      return product;
    } catch (Exception e) {
      LOGGER.error("Error while creating product", e);
      try {
        response.sendError(503, "Error while creating product " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }

}
