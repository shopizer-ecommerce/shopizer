package com.salesmanager.shop.store.api.v2.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesmanager.shop.store.controller.product.facade.ProductInventoryFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;



/**
 * Api to manage Inventory
 * @author carlsamson
 *
 */
@Controller("ProductInventoryApiV2")
@RequestMapping("/api/v2")
@Api(tags = {"Product instances api"})
@SwaggerDefinition(tags = {
    @Tag(name = "Product instances resource", description = "Manage inventory for a given product")
})
public class ProductInventoryApi {
  
  @Autowired
  private ProductInventoryFacade productInventoryFacade;
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductInventoryApi.class);

}
