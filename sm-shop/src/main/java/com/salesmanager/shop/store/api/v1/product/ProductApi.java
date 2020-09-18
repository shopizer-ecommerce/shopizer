package com.salesmanager.shop.store.api.v1.product;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.LightPersistableProduct;
import com.salesmanager.shop.model.catalog.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProductList;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.product.facade.ProductFacade;
import com.salesmanager.shop.utils.ImageFilePath;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

/**
 * API to create, read, update and delete a Product API to create Manufacturer
 *
 * @author Carl Samson
 */
@Controller
@RequestMapping("/api/v1")
@Api(tags = {"Product management resource (Product Management Api)"})
@SwaggerDefinition(tags = {
    @Tag(name = "Product management resource", description = "Add product, edit product and delete product")
})
public class ProductApi {


  @Inject private CategoryService categoryService;

  @Inject private ProductService productService;

  @Inject private ProductFacade productFacade;

  @Inject
  @Qualifier("img")
  private ImageFilePath imageUtils;

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductApi.class);

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(
      value = {"/private/product", "/auth/products"},//private for api //auth for user adding products
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

      productFacade.saveProduct(merchantStore, product, language);
      return product;

  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(
      value = {"/private/product/{id}", "/auth/product/{id}"},
      method = RequestMethod.PUT)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  @ApiOperation(httpMethod = "PUT", value = "Update product",
  notes = "", produces = "application/json", response = PersistableProduct.class)
  public @ResponseBody PersistableProduct update(
      @PathVariable Long id,
      @Valid @RequestBody PersistableProduct product,
      @ApiIgnore MerchantStore merchantStore,
      HttpServletRequest request,
      HttpServletResponse response) {

    try {
      product.setId(id);
      productFacade.saveProduct(merchantStore, product, merchantStore.getDefaultLanguage());
      return product;
    } catch (Exception e) {
      LOGGER.error("Error while updating product", e);
      try {
        response.sendError(503, "Error while updating product " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }
  /** updates price quantity **/
  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(
      value = "/private/product/{id}",
      produces = {APPLICATION_JSON_VALUE})
  @ApiOperation(httpMethod = "PATCH", value = "Update product inventory",
  notes = "Updates product inventory", produces = "application/json", response = Void.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en")
  })
  public void update(
      @PathVariable Long id,
      @Valid @RequestBody LightPersistableProduct product,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
      productFacade.update(id, product, merchantStore, language);
      return;

  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(
      value = {"/private/product/{id}", "/auth/product/{id}"},
      method = RequestMethod.DELETE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
		@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
  public void delete(
      @PathVariable Long id,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {

	  productFacade.deleteProduct(id, merchantStore);
  }

  /**
   * @RequestMapping( value="/private/{store}/manufacturer",
   * method=RequestMethod.POST) @ResponseStatus(HttpStatus.CREATED) @ResponseBody public
   * PersistableManufacturer createManufacturer(@PathVariable final String
   * store, @Valid @RequestBody PersistableManufacturer manufacturer, HttpServletRequest request,
   * HttpServletResponse response) throws Exception {
   *
   * <p>try {
   *
   * <p>@ApiIgnore MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
   * if(merchantStore!=null) { if(!merchantStore.getCode().equals(store)) { merchantStore = null; }
   * }
   *
   * <p>if(merchantStore== null) { merchantStore = merchantStoreService.getByCode(store); }
   *
   * <p>if(merchantStore==null) { LOGGER.error("Merchant store is null for code " + store);
   * response.sendError(503, "Merchant store is null for code " + store); return null; }
   *
   * <p>PersistableManufacturerPopulator populator = new PersistableManufacturerPopulator();
   * populator.setLanguageService(languageService);
   *
   * <p>com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer manuf = new
   * com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer();
   *
   * <p>populator.populate(manufacturer, manuf, merchantStore, merchantStore.getDefaultLanguage());
   *
   * <p>manufacturerService.save(manuf);
   *
   * <p>manufacturer.setId(manuf.getId());
   *
   * <p>return manufacturer;
   *
   * <p>} catch (Exception e) { LOGGER.error("Error while saving product",e); try {
   * response.sendError(503, "Error while saving product " + e.getMessage()); } catch (Exception
   * ignore) { }
   *
   * <p>return null; }
   *
   * <p>} @RequestMapping( value="/private/{store}/product/optionValue",
   * method=RequestMethod.POST) @ResponseStatus(HttpStatus.CREATED) @ResponseBody public
   * PersistableProductOptionValue createProductOptionValue(@PathVariable final String
   * store, @Valid @RequestBody PersistableProductOptionValue optionValue, HttpServletRequest
   * request, HttpServletResponse response) throws Exception {
   *
   * <p>try {
   *
   * <p>@ApiIgnore MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
   * if(merchantStore!=null) { if(!merchantStore.getCode().equals(store)) { merchantStore = null; }
   * }
   *
   * <p>if(merchantStore== null) { merchantStore = merchantStoreService.getByCode(store); }
   *
   * <p>if(merchantStore==null) { LOGGER.error("Merchant store is null for code " + store);
   * response.sendError(503, "Merchant store is null for code " + store); return null; }
   *
   * <p>PersistableProductOptionValuePopulator populator = new
   * PersistableProductOptionValuePopulator(); populator.setLanguageService(languageService);
   *
   * <p>com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue optValue = new
   * com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue();
   * populator.populate(optionValue, optValue, merchantStore, merchantStore.getDefaultLanguage());
   *
   * <p>productOptionValueService.save(optValue);
   *
   * <p>optionValue.setId(optValue.getId());
   *
   * <p>return optionValue;
   *
   * <p>} catch (Exception e) { LOGGER.error("Error while saving product option value",e); try {
   * response.sendError(503, "Error while saving product option value" + e.getMessage()); } catch
   * (Exception ignore) { }
   *
   * <p>return null; }
   *
   * <p>} @RequestMapping( value="/private/{store}/product/option",
   * method=RequestMethod.POST) @ResponseStatus(HttpStatus.CREATED) @ResponseBody public
   * PersistableProductOption createProductOption(@PathVariable final String
   * store, @Valid @RequestBody PersistableProductOption option, HttpServletRequest request,
   * HttpServletResponse response) throws Exception {
   *
   * <p>try {
   *
   * <p>@ApiIgnore MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
   * if(merchantStore!=null) { if(!merchantStore.getCode().equals(store)) { merchantStore = null; }
   * }
   *
   * <p>if(merchantStore== null) { merchantStore = merchantStoreService.getByCode(store); }
   *
   * <p>if(merchantStore==null) { LOGGER.error("Merchant store is null for code " + store);
   * response.sendError(503, "Merchant store is null for code " + store); return null; }
   *
   * <p>PersistableProductOptionPopulator populator = new PersistableProductOptionPopulator();
   * populator.setLanguageService(languageService);
   *
   * <p>com.salesmanager.core.model.catalog.product.attribute.ProductOption opt = new
   * com.salesmanager.core.model.catalog.product.attribute.ProductOption();
   * populator.populate(option, opt, merchantStore, merchantStore.getDefaultLanguage());
   *
   * <p>productOptionService.save(opt);
   *
   * <p>option.setId(opt.getId());
   *
   * <p>return option;
   *
   * <p>} catch (Exception e) { LOGGER.error("Error while saving product option",e); try {
   * response.sendError(503, "Error while saving product option" + e.getMessage()); } catch
   * (Exception ignore) { }
   *
   * <p>return null; } } @RequestMapping( value="/private/{store}/product/review",
   * method=RequestMethod.POST) @ResponseStatus(HttpStatus.CREATED) @ResponseBody public
   * PersistableProductReview createProductReview(@PathVariable final String
   * store, @Valid @RequestBody PersistableProductReview review, HttpServletRequest request,
   * HttpServletResponse response) throws Exception {
   *
   * <p>try {
   *
   * <p>@ApiIgnore MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
   * if(merchantStore!=null) { if(!merchantStore.getCode().equals(store)) { merchantStore = null; }
   * }
   *
   * <p>if(merchantStore== null) { merchantStore = merchantStoreService.getByCode(store); }
   *
   * <p>if(merchantStore==null) { LOGGER.error("Merchant store is null for code " + store);
   * response.sendError(500, "Merchant store is null for code " + store); return null; }
   *
   * <p>//rating already exist ProductReview prodReview =
   * productReviewService.getByProductAndCustomer(review.getProductId(), review.getCustomerId());
   * if(prodReview!=null) { response.sendError(500, "A review already exist for this customer and
   * product"); return null; }
   *
   * <p>//rating maximum 5 if(review.getRating()>Constants.MAX_REVIEW_RATING_SCORE) {
   * response.sendError(503, "Maximum rating score is " + Constants.MAX_REVIEW_RATING_SCORE); return
   * null; }
   *
   * <p>PersistableProductReviewPopulator populator = new PersistableProductReviewPopulator();
   * populator.setLanguageService(languageService); populator.setCustomerService(customerService);
   * populator.setProductService(productService);
   *
   * <p>com.salesmanager.core.model.catalog.product.review.ProductReview rev = new
   * com.salesmanager.core.model.catalog.product.review.ProductReview(); populator.populate(review,
   * rev, merchantStore, merchantStore.getDefaultLanguage());
   *
   * <p>productReviewService.create(rev);
   *
   * <p>review.setId(rev.getId());
   *
   * <p>return review;
   *
   * <p>} catch (Exception e) { LOGGER.error("Error while saving product review",e); try {
   * response.sendError(503, "Error while saving product review" + e.getMessage()); } catch
   * (Exception ignore) { }
   *
   * <p>return null; } } @RequestMapping("/public/products/{store}") @ResponseBody public
   * ReadableProductList getProducts(@PathVariable String store, HttpServletRequest request,
   * HttpServletResponse response) throws Exception {
   */
  /** default routine * */
  /*

  @ApiIgnore MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
  if(merchantStore!=null) {
  	if(!merchantStore.getCode().equals(store)) {
  		merchantStore = null;
  	}
  }

  if(merchantStore== null) {
  	merchantStore = merchantStoreService.getByCode(store);
  }

  if(merchantStore==null) {
  	LOGGER.error("Merchant store is null for code " + store);
  	response.sendError(503, "Merchant store is null for code " + store);
  	return null;
  }

  Language l = merchantStore.getDefaultLanguage();

  String lang = l.getCode();

  if(!StringUtils.isBlank(request.getParameter(Constants.LANG))) {

  	lang = request.getParameter(Constants.LANG);

  }


  */
  /** end default routine * */
  /*




  	return this.getProducts(0, 10000, store, lang, null, null, request, response);
  }*/

  /**
   * Filtering product lists based on product attributes ?category=1 &manufacturer=2 &type=...
   * &lang=en|fr NOT REQUIRED, will use request language &start=0 NOT REQUIRED, can be used for
   * pagination &count=10 NOT REQUIRED, can be used to limit item count
   *
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/products", method = RequestMethod.GET)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public ReadableProductList list(
      @RequestParam(value = "lang", required = false) String lang,
      @RequestParam(value = "category", required = false) Long category,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "sku", required = false) String sku,
      @RequestParam(value = "manufacturer", required = false) Long manufacturer,
      @RequestParam(value = "status", required = false) String status,
      @RequestParam(value = "owner", required = false) Long owner,
      @RequestParam(value = "start", required = false) Integer start,
      @RequestParam(value = "count", required = false) Integer count,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletRequest request,
      HttpServletResponse response)
      throws Exception {

    ProductCriteria criteria = new ProductCriteria();
    if (lang != null) {
      criteria.setLanguage(lang);
    } else {
      criteria.setLanguage(language.getCode());
    }
    if (!StringUtils.isBlank(status)) {
      criteria.setStatus(status);
    }
    if (category != null) {
      List<Long> categoryIds = new ArrayList<Long>();
      categoryIds.add(category);
      criteria.setCategoryIds(categoryIds);
    }
    if (manufacturer != null) {
      criteria.setManufacturerId(manufacturer);
    }

    if (owner != null) {
      criteria.setOwnerId(owner);
    }

    if (start != null) {
      criteria.setStartIndex(start);
    }

    if (count != null) {
      criteria.setMaxCount(count);
    }

    if(!StringUtils.isBlank(name)) {
    	criteria.setProductName(name);
    }

    if(!StringUtils.isBlank(sku)) {
    	criteria.setCode(sku);
    }

    // TODO
    // RENTAL add filter by owner
    // REPOSITORY to use the new filters

    try {
      return productFacade.getProductListsByCriterias(merchantStore, language, criteria);

    } catch (Exception e) {

      LOGGER.error("Error while filtering products product", e);
      try {
        response.sendError(503, "Error while filtering products " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }

  /**
   * API for getting a product
   *
   * @param id
   * @param lang ?lang=fr|en
   * @param response
   * @return ReadableProduct
   * @throws Exception
   *     <p>/api/v1/products/123
   */
  @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
  @ApiOperation(httpMethod = "GET", value = "Get a product by id", notes = "For administration and shop purpose. Specifying ?merchant is required otherwise it falls back to DEFAULT")
  @ApiResponses(value = {
			@ApiResponse(code = 200, message = "Single product found", response = ReadableProduct.class) })
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public ReadableProduct get(
      @PathVariable final Long id,
      @RequestParam(value = "lang", required = false) String lang,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletResponse response)
      throws Exception {
    ReadableProduct product = productFacade.getProduct(merchantStore, id, language);

    if (product == null) {
      response.sendError(404, "Product not fount for id " + id);
      return null;
    }

    return product;
  }

  /**
   * API for getting a product
   *
   * @param friendlyUrl
   * @param lang ?lang=fr|en
   * @param response
   * @return ReadableProduct
   * @throws Exception
   *     <p>/api/v1/products/123
   */
  @RequestMapping(value = "/products/slug/{friendlyUrl}", method = RequestMethod.GET)
  @ApiOperation(httpMethod = "GET", value = "Get a product by friendlyUrl (slug)", notes = "For administration and shop purpose. Specifying ?merchant is " +
          "required otherwise it falls back to DEFAULT")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Single product found", response = ReadableProduct.class) })
  @ResponseBody
  @ApiImplicitParams({
          @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
          @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public ReadableProduct getByfriendlyUrl(
          @PathVariable final String friendlyUrl,
          @RequestParam(value = "lang", required = false) String lang,
          @ApiIgnore MerchantStore merchantStore,
          @ApiIgnore Language language,
          HttpServletResponse response)
          throws Exception {
    ReadableProduct product = productFacade.getProductBySeUrl(merchantStore, friendlyUrl, language);

    if (product == null) {
      response.sendError(404, "Product not fount for id " + friendlyUrl);
      return null;
    }

    return product;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/private/product/unique"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiImplicitParams({
    @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT")
  })
  @ApiOperation(httpMethod = "GET", value = "Check if product code already exists", notes = "",
      response = EntityExists.class)
  public ResponseEntity<EntityExists> exists(
      @RequestParam(value = "code") String code,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {

    boolean exists = productFacade.exists(code, merchantStore);
    return new ResponseEntity<EntityExists>(new EntityExists(exists), HttpStatus.OK);

  }

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(
      value = {
        "/private/product/{productId}/category/{categoryId}",
        "/auth/product/{productId}/category/{categoryId}"
      },
      method = RequestMethod.POST)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody ReadableProduct addProductToCategory(
      @PathVariable Long productId,
      @PathVariable Long categoryId,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletResponse response)
      throws Exception {

    try {
      // get the product
      Product product = productService.getById(productId);

      if(product == null) {
    	  throw new ResourceNotFoundException("Product id [" + productId + "] is not found");
      }

      if(product.getMerchantStore().getId().intValue() != merchantStore.getId().intValue()) {
    	  throw new UnauthorizedException("Product id [" + productId + "] does not belong to store [" + merchantStore.getCode() + "]");
      }

      Category category = categoryService.getById(categoryId);

      if(category == null) {
    	  throw new ResourceNotFoundException("Category id [" + categoryId + "] is not found");
      }

      if(category.getMerchantStore().getId().intValue() != merchantStore.getId().intValue()) {
    	  throw new UnauthorizedException("Category id [" + categoryId + "] does not belong to store [" + merchantStore.getCode() + "]");
      }


      return productFacade.addProductToCategory(category, product, language);

    } catch (Exception e) {
      LOGGER.error("Error while adding product to category", e);
      try {
        response.sendError(503, "Error while adding product to category " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(
      value = {
        "/private/product/{productId}/category/{categoryId}",
        "/auth/product/{productId}/category/{categoryId}"
      },
      method = RequestMethod.DELETE)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody ReadableProduct removeProductFromCategory(
      @PathVariable Long productId,
      @PathVariable Long categoryId,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletResponse response) {

    try {
        Product product = productService.getById(productId);

        if(product == null) {
      	  throw new ResourceNotFoundException("Product id [" + productId + "] is not found");
        }

        if(product.getMerchantStore().getId().intValue() != merchantStore.getId().intValue()) {
      	  throw new UnauthorizedException("Product id [" + productId + "] does not belong to store [" + merchantStore.getCode() + "]");
        }

        Category category = categoryService.getById(categoryId);

        if(category == null) {
      	  throw new ResourceNotFoundException("Category id [" + categoryId + "] is not found");
        }

        if(category.getMerchantStore().getId().intValue() != merchantStore.getId().intValue()) {
      	  throw new UnauthorizedException("Category id [" + categoryId + "] does not belong to store [" + merchantStore.getCode() + "]");
        }

      return productFacade.removeProductFromCategory(category, product, language);

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
