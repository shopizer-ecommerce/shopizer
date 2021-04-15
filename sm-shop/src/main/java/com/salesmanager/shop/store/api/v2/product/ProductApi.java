package com.salesmanager.shop.store.api.v2.product;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.shop.store.controller.product.facade.ProductFacade;
import com.salesmanager.shop.utils.ImageFilePath;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * API to create, read, update and delete a Product.
 *
 * @author Carl Samson
 */
@Controller("ProductApiV2")
@RequestMapping("/api/v2")
@Api(tags = {"Product definition api"})
@SwaggerDefinition(tags = {
    @Tag(name = "Product definition resource", description = "View product, Add product, edit product and delete product")
})
public class
ProductApi {


  @Inject private CategoryService categoryService;

  @Inject private ProductService productService;

  @Inject private ProductFacade productFacade;

  @Inject
  @Qualifier("img")
  private ImageFilePath imageUtils;

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductApi.class);


}
