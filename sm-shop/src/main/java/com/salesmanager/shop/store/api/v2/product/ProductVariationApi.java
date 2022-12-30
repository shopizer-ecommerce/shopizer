package com.salesmanager.shop.store.api.v2.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.catalog.pricing.PricingService;
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
import com.salesmanager.shop.model.catalog.product.variation.PersistableProductVariation;
import com.salesmanager.shop.model.catalog.product.variation.ReadableProductVariation;
import com.salesmanager.shop.model.entity.Entity;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.model.entity.ReadableEntityList;
import com.salesmanager.shop.populator.catalog.ReadableFinalPricePopulator;
import com.salesmanager.shop.store.controller.category.facade.CategoryFacade;
import com.salesmanager.shop.store.controller.product.facade.ProductVariationFacade;
import com.salesmanager.shop.utils.ImageFilePath;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

/**
 * API to manage product variant
 * 
 * The flow is the following
 * 
 * - create a product definition
 * - create a product variant
 *
 * @author Carl Samson
 */
@Controller
@RequestMapping("/api/v2")
@Api(tags = {"Product variation resource (Product variant Api)"})
@SwaggerDefinition(tags = {
    @Tag(name = "Product variation resource", description = "List variations of products by different grouping")
})
public class ProductVariationApi {


  @Inject private PricingService pricingService;

  @Inject private ProductService productService;
  
  @Inject private CategoryFacade categoryFacade;
  
  @Inject private ProductVariationFacade productVariationFacade;
	


  @Inject
  @Qualifier("img")
  private ImageFilePath imageUtils;


  private static final Logger LOGGER = LoggerFactory.getLogger(ProductVariationApi.class);

  /**
   * Calculates the price based on selected options if any
   * @param id
   * @param options
   * @param merchantStore
   * @param language
   * @param response
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/product/{id}/variation", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(
      httpMethod = "POST",
      value = "Get product price variation based on selected product",
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

  
  @RequestMapping(value = "/category/{id}/variations", method = RequestMethod.GET)
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

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = { "/private/product/variation" }, method = RequestMethod.POST)
	@ApiOperation(
		      httpMethod = "POST",
		      value = "Creates a new product variant",
		      notes = "",
		      produces = "application/json",
		      response = Void.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody Entity create(
			@Valid @RequestBody PersistableProductVariation variation, 
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		Long variantId = productVariationFacade.create(variation, merchantStore, language);
		return new Entity(variantId);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = { "/private/product/variation/unique" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
	@ApiOperation(httpMethod = "GET", value = "Check if option set code already exists", notes = "", response = EntityExists.class)
	public ResponseEntity<EntityExists> exists(
			@RequestParam(value = "code") String code,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {

		boolean isOptionExist = productVariationFacade.exists(code, merchantStore);
		return new ResponseEntity<EntityExists>(new EntityExists(isOptionExist), HttpStatus.OK);
	}


	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/variation/{variationId}" }, method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	@ResponseBody
	public ReadableProductVariation get(
			@PathVariable Long variationId, 
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		return productVariationFacade.get(variationId, merchantStore, language);

	}


	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/variation/{variationId}" }, method = RequestMethod.PUT)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void update(
			@Valid @RequestBody PersistableProductVariation variation, 
			@PathVariable Long variationId,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {
		
		variation.setId(variationId);
		productVariationFacade.update(variationId, variation, merchantStore, language);

	}


	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/variation/{variationId}" }, method = RequestMethod.DELETE)
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void delete(
			@PathVariable Long variationId,
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		productVariationFacade.delete(variationId, merchantStore);

	}
	

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/variations" }, method = RequestMethod.GET)
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableEntityList<ReadableProductVariation> list(
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language,
			@RequestParam(value = "page", required = false, defaultValue="0") Integer page,
		    @RequestParam(value = "count", required = false, defaultValue="10") Integer count) {

		return productVariationFacade.list(merchantStore, language, page, count);

		
	}
  
}
