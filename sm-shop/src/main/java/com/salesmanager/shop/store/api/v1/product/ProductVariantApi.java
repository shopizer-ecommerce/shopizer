package com.salesmanager.shop.store.api.v1.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableProductPrice;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductVariant;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductVariantValue;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableSelectedProductVariant;
import com.salesmanager.shop.populator.catalog.ReadableFinalPricePopulator;
import com.salesmanager.shop.store.controller.category.facade.CategoryFacade;
import com.salesmanager.shop.utils.ImageFilePath;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * API to get product variant
 *
 * @author Carl Samson
 */
@Controller
@RequestMapping("/api/v1")
public class ProductVariantApi {


  @Inject private PricingService pricingService;

  @Inject private ProductService productService;
  
  @Inject private CategoryFacade categoryFacade;

  @Inject
  @Qualifier("img")
  private ImageFilePath imageUtils;


  private static final Logger LOGGER = LoggerFactory.getLogger(ProductVariantApi.class);


  @RequestMapping(value = "/products/{id}/variant", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(
      httpMethod = "POST",
      value = "Get product price variation based on selected options",
      notes = "",
      produces = "application/json",
      response = ReadableProductPrice.class)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public ReadableProductPrice calculateVariant(
      @PathVariable final Long id,
      @RequestBody ReadableSelectedProductVariant options,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletResponse response)
      throws Exception {

    Product product = productService.getById(id);

    if (product == null) {
      response.sendError(404, "Product not fount for id " + id);
      return null;
    }

    List<ReadableProductVariantValue> ids = options.getOptions();

    if (CollectionUtils.isEmpty(ids)) {
      return null;
    }
    List<ReadableProductVariantValue> variants = options.getOptions();
    
    
    List<ProductAttribute> attributes = new ArrayList<ProductAttribute>();
    
    Set<ProductAttribute> productAttributes = product.getAttributes();
    for(ProductAttribute attribute : productAttributes) {
      Long option = attribute.getProductOption().getId();
      Long optionValue = attribute.getProductOptionValue().getId();
      for(ReadableProductVariantValue v : variants) {
        if(v.getOption().longValue() == option.longValue()
            && v.getValue().longValue() == optionValue.longValue()) {
          attributes.add(attribute);
        }
      }
      
    }

    FinalPrice price = pricingService.calculateProductPrice(product, attributes);
    ReadableProductPrice readablePrice = new ReadableProductPrice();
    ReadableFinalPricePopulator populator = new ReadableFinalPricePopulator();
    populator.setPricingService(pricingService);
    populator.populate(price, readablePrice, merchantStore, language);
    return readablePrice;
  }

  
  @RequestMapping(value = "/category/{id}/variants", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(
      httpMethod = "GET",
      value = "Get all variation for all items in a given category",
      notes = "",
      produces = "application/json",
      response = List.class)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public List<ReadableProductVariant> categoryVariantList(
      @PathVariable final Long id, //category id
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletResponse response)
      throws Exception {
    
    return categoryFacade.categoryProductVariants(id, merchantStore, language);
    
  }
  
}
