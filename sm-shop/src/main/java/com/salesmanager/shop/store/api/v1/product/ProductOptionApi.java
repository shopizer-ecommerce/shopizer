package com.salesmanager.shop.store.api.v1.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionList;
import com.salesmanager.shop.store.controller.product.facade.ProductOptionFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/api/v1")
@Api(tags = {"Product options / options values management resource (Product Option Management Api)"})
@SwaggerDefinition(tags = {
    @Tag(name = "Product options / options values management resource", description = "Edit product options and product option values")
})
public class ProductOptionApi {
  
  @Autowired
  private ProductOptionFacade productOptionFacade;
  
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(
      value = {"/private/product/option"},
      method = RequestMethod.POST)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody ReadableProductOptionEntity createOption(
      @Valid @RequestBody PersistableProductOptionEntity option,
            @ApiIgnore MerchantStore merchantStore,
            @ApiIgnore Language language,
      HttpServletRequest request,
      HttpServletResponse response) {
    
    ReadableProductOptionEntity entity = productOptionFacade.saveOption(option, merchantStore, language);
    return entity;

  }
  
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(
      value = {"/private/product/option/value"},
      method = RequestMethod.POST)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody PersistableProduct createOptionValue(
      @Valid @RequestBody PersistableProduct product,
            @ApiIgnore MerchantStore merchantStore,
            @ApiIgnore Language language,
      HttpServletRequest request,
      HttpServletResponse response) {

      //productFacade.saveProduct(merchantStore, product, language);
      //return product;
    
      return null;

  }
  
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(
      value = {"/private/product/option/{id}"},
      method = RequestMethod.PUT)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public void updateOption(
      @Valid @RequestBody PersistableProductOptionEntity option,
            @PathVariable Long optionId,
            @ApiIgnore MerchantStore merchantStore,
            @ApiIgnore Language language,
      HttpServletRequest request,
      HttpServletResponse response) {
    
    
    productOptionFacade.saveOption(option, merchantStore, language);
    return;


  }
  
  
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(
      value = {"/private/product/option/{id}"},
      method = RequestMethod.DELETE)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public void deleteOption(
            @PathVariable Long optionId,
            @ApiIgnore MerchantStore merchantStore,
            @ApiIgnore Language language,
      HttpServletRequest request,
      HttpServletResponse response) {
    
      productOptionFacade.deleteOption(optionId, merchantStore);
      return;

  }
  
  
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(
      value = {"/private/product/option/value/{id}"},
      method = RequestMethod.PUT)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody PersistableProduct updateOptionValue(
      @Valid @RequestBody PersistableProduct product,
            @PathVariable Long optionValueId,
            @ApiIgnore MerchantStore merchantStore,
            @ApiIgnore Language language,
      HttpServletRequest request,
      HttpServletResponse response) {

      //productFacade.saveProduct(merchantStore, product, language);
      //return product;
    
      return null;

  }
  
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(
      value = {"/private/product/option/value/{id}"},
      method = RequestMethod.DELETE)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public void deleteOptionValue(
            @PathVariable Long optionValueId,
            @ApiIgnore MerchantStore merchantStore,
            @ApiIgnore Language language,
      HttpServletRequest request,
      HttpServletResponse response) {

      //productFacade.saveProduct(merchantStore, product, language);
      //return product;
    
      return;

  }
  
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(
      value = {"/private/product/options"},
      method = RequestMethod.GET)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody ReadableProductOptionList options(
            @ApiIgnore MerchantStore merchantStore,
            @ApiIgnore Language language,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", required = false, defaultValue="0") Integer page,
            @RequestParam(value = "count", required = false, defaultValue="10") Integer count) {
    
    
    return productOptionFacade.options(merchantStore, language, name, page, count);

  }
  
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(
      value = {"/private/product/options/values"},
      method = RequestMethod.GET)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody ReadableProductOptionList optionsValues(
            @PathVariable Long id,
            @ApiIgnore MerchantStore merchantStore,
            @ApiIgnore Language language,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", required = false, defaultValue="0") Integer page,
            @RequestParam(value = "count", required = false, defaultValue="10") Integer count) {
    
    
    //return productOptionFacade.options(merchantStore, language, name, page, count);
    return null;

  }

}
