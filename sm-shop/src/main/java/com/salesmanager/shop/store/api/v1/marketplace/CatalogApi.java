package com.salesmanager.shop.store.api.v1.marketplace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Controller
@RequestMapping("/api/v1")
@Api(tags = {"Catalog management resource (Catalog Management Api)"})
@SwaggerDefinition(tags = {
    @Tag(name = "Catalog management resource", description = "Catalogue management")
})
public class CatalogApi {

}
