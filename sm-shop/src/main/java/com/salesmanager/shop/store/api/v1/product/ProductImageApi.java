package com.salesmanager.shop.store.api.v1.product;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.image.ProductImageService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.ImageContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.PersistableImage;
import com.salesmanager.shop.populator.catalog.PersistableProductImagePopulator;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/api/v1")
public class ProductImageApi {

  @Inject private ProductImageService productImageService;


  @Inject private ProductService productService;

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductImageApi.class);

  /**
   * To be used with MultipartFile
   *
   * @param id
   * @param uploadfiles
   * @param request
   * @param response
   * @throws Exception
   */
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(
      value = {"/private/products/{id}/images", "/auth/products/{id}/images"},
      method = RequestMethod.POST)
  @ApiImplicitParams({
    @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
    @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public void uploadImages(
      @PathVariable Long id,
      @RequestParam("files") MultipartFile[] uploadfiles,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {

    try {

      // get the product
      Product product = productService.getById(id);

      if (product == null) {
        throw new ResourceNotFoundException("Product not found");
      }
      
      //security validation
      //product belongs to merchant store
      if(product.getMerchantStore().getId().intValue() != merchantStore.getId().intValue()) {
        throw new UnauthorizedException("Resource not authorized for this merchant");
      }

      boolean hasDefaultImage = false;
      Set<ProductImage> images = product.getImages();
      if (!CollectionUtils.isEmpty(images)) {
        for (ProductImage image : images) {
          if (image.isDefaultImage()) {
            hasDefaultImage = true;
            break;
          }
        }
      }

      List<ProductImage> contentImagesList = new ArrayList<ProductImage>();

      for (MultipartFile multipartFile : uploadfiles) {
        if (!multipartFile.isEmpty()) {
          ProductImage productImage = new ProductImage();
          productImage.setImage(multipartFile.getInputStream());
          productImage.setProductImage(multipartFile.getOriginalFilename());
          productImage.setProduct(product);

          if (!hasDefaultImage) {
            productImage.setDefaultImage(true);
            hasDefaultImage = true;
          }

          contentImagesList.add(productImage);
        }
      }

      if (CollectionUtils.isNotEmpty(contentImagesList)) {
        productImageService.addProductImages(product, contentImagesList);
      }

    } catch (Exception e) {
      LOGGER.error("Error while creating ProductImage", e);
      throw new ServiceRuntimeException("Error while creating image");
    }
  }

  /**
   * @deprecated
   * Simple way of uploading image using Base64
   *
   * @param id
   * @param image
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(
      value = {"/private/products/{id}/images"},
      method = RequestMethod.POST)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody PersistableImage createImage(
      @PathVariable Long id,
      @Valid @RequestBody PersistableImage image,
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

    try {
      // get the product
      Product product = productService.getById(id);

      PersistableProductImagePopulator imagePopulator = new PersistableProductImagePopulator();
      imagePopulator.setProduct(product);
      ProductImage productImage = imagePopulator.populate(image, merchantStore, language);

      InputStream input = new ByteArrayInputStream(image.getBytes());

      ImageContentFile file = new ImageContentFile();
      file.setFile(input);
      file.setFileContentType(FileContentType.IMAGE);
      file.setFileName(productImage.getProductImage());
      file.setMimeType(image.getContentType());

      productImageService.addProductImage(product, productImage, file);
      image.setId(productImage.getId());

      return image;

    } catch (Exception e) {
      LOGGER.error("Error while creating ProductImage", e);
      throw new ServiceRuntimeException("Exception while creating image");
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(
      value = {"/private/products/images/{id}", "/auth/products/images/{id}"},
      method = RequestMethod.DELETE)
  public void deleteImage(
      @PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      ProductImage productImage = productImageService.getById(id);

      if (productImage != null) {
        productImageService.delete(productImage);
      } else {
        response.sendError(404, "No ProductImage found for ID : " + id);
      }

    } catch (Exception e) {
      LOGGER.error("Error while deleting ProductImage", e);
      try {
        response.sendError(503, "Error while deleting ProductImage " + e.getMessage());
      } catch (Exception ignore) {
      }
    }
  }
}
