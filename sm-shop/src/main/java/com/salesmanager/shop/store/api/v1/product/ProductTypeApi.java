package com.salesmanager.shop.store.api.v1.product;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;
import com.salesmanager.shop.store.controller.product.facade.ProductTypeFacade;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * API to create, read, update and delete a Product API to create Manufacturer
 *
 * @author Carl Samson
 */
@RestController
@RequestMapping("/api/v1")
public class ProductTypeApi {


  @Inject private ProductTypeFacade productTypeFacade;


  private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeApi.class);
  
  
  @GetMapping(value = "/products/types", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get product types list",
      notes = "", produces = "application/json", response = List.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public List<ReadableProductType> getContentPages(
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
    
    return productTypeFacade.getByMerchant(merchantStore.getCode(), language);
    
  }


}
