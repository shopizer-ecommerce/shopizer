package com.salesmanager.shop.store.api.v1.product;

import java.util.List;

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

import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.review.ProductReviewService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.review.ProductReview;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.catalog.product.PersistableProductReview;
import com.salesmanager.shop.model.catalog.product.ReadableProductReview;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.RestApiException;
import com.salesmanager.shop.store.controller.product.facade.ProductCommonFacade;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/api/v1")
public class ProductReviewApi {

  @Inject private ProductCommonFacade productCommonFacade;

  @Inject private ProductService productService;

  @Inject private ProductReviewService productReviewService;

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductReviewApi.class);

  @RequestMapping(
      value = {
        "/private/products/{id}/reviews",
        "/auth/products/{id}/reviews",
        "/auth/products/{id}/reviews",
        "/auth/products/{id}/reviews"
      },
      method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public PersistableProductReview create(
      @PathVariable final Long id,
      @Valid @RequestBody PersistableProductReview review,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletRequest request,
      HttpServletResponse response) {

    try {
      // Set product id from path variable BEFORE using it for queries
      review.setProductId(id);

      // rating maximum 5
      if (review.getRating() > Constants.MAX_REVIEW_RATING_SCORE) {
        throw new RestApiException("400",
            "Maximum rating score is " + Constants.MAX_REVIEW_RATING_SCORE);
      }

      // check if a review already exists for this customer and product
      ProductReview prodReview =
          productReviewService.getByProductAndCustomer(
              id, review.getCustomerId());
      if (prodReview != null) {
        throw new RestApiException("409",
            "A review already exists for this customer and product");
      }

      productCommonFacade.saveOrUpdateReview(review, merchantStore, language);

      return review;

    } catch (RestApiException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error("Error while saving product review", e);
      throw new RestApiException("500", "Error while saving product review: " + e.getMessage());
    }
  }

  @RequestMapping(value = "/product/{id}/reviews", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public List<ReadableProductReview> getAll(
      @PathVariable final Long id,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletResponse response) {

    try {
      // product exist
      Product product = productService.getById(id);

      if (product == null) {
        throw new ResourceNotFoundException("Product id " + id + " does not exist");
      }

      return productCommonFacade.getProductReviews(product, merchantStore, language);

    } catch (ResourceNotFoundException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error("Error while getting product reviews", e);
      throw new RestApiException("500", "Error while getting product reviews: " + e.getMessage());
    }
  }

  @RequestMapping(
      value = {
        "/private/products/{id}/reviews/{reviewid}",
        "/auth/products/{id}/reviews/{reviewid}"
      },
      method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public PersistableProductReview update(
      @PathVariable final Long id,
      @PathVariable final Long reviewId,
      @Valid @RequestBody PersistableProductReview review,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletRequest request,
      HttpServletResponse response) {

    try {
      ProductReview prodReview = productReviewService.getById(reviewId);
      if (prodReview == null) {
        throw new ResourceNotFoundException("Product review with id " + reviewId + " does not exist");
      }

      if (review.getCustomerId() == null
          || prodReview.getCustomer().getId().longValue() != review.getCustomerId().longValue()) {
        throw new ResourceNotFoundException("Product review with id " + reviewId + " does not exist");
      }

      // rating maximum 5
      if (review.getRating() > Constants.MAX_REVIEW_RATING_SCORE) {
        throw new RestApiException("400",
            "Maximum rating score is " + Constants.MAX_REVIEW_RATING_SCORE);
      }

      review.setProductId(id);

      productCommonFacade.saveOrUpdateReview(review, merchantStore, language);

      return review;

    } catch (ResourceNotFoundException | RestApiException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error("Error while saving product review", e);
      throw new RestApiException("500", "Error while saving product review: " + e.getMessage());
    }
  }

  @RequestMapping(
      value = {
        "/private/products/{id}/reviews/{reviewid}",
        "/auth/products/{id}/reviews/{reviewid}"
      },
      method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public void delete(
      @PathVariable final Long id,
      @PathVariable final Long reviewId,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletResponse response) {

    try {
      ProductReview prodReview = productReviewService.getById(reviewId);
      if (prodReview == null) {
        throw new ResourceNotFoundException("Product review with id " + reviewId + " does not exist");
      }

      if (prodReview.getProduct().getId().longValue() != id.longValue()) {
        throw new ResourceNotFoundException("Product review with id " + reviewId + " does not exist");
      }

      productCommonFacade.deleteReview(prodReview, merchantStore, language);

    } catch (ResourceNotFoundException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error("Error while deleting product review", e);
      throw new RestApiException("500", "Error while deleting product review: " + e.getMessage());
    }
  }
}

