package com.salesmanager.shop.store.api.v1.catalog;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.catalog.PersistableCatalog;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalog;
import com.salesmanager.shop.store.controller.catalog.facade.CatalogFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = {"Catalog management resource (Catalog Management Api)"})
@SwaggerDefinition(tags = {
    @Tag(name = "Catalog management resource", description = "Manage catalogs and attached products")
})
public class CatalogApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(CatalogApi.class);
  
  @Autowired
  private CatalogFacade catalogFacade;


/*  @GetMapping(value = "/content/folder", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ContentFolder folder(@RequestParam(value = "path", required = false) String path,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) throws Exception {
    String decodedPath = decodeContentPath(path);
    return contentFacade.getContentFolder(decodedPath, merchantStore);
  }

*/



  @PostMapping(value = "/private/catalog")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(httpMethod = "POST", value = "Create catalog", notes = "",
      response = Void.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ReadableCatalog createCatalog(
      @RequestBody @Valid PersistableCatalog catalog,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {
    
	  return catalogFacade.saveCatalog(catalog, merchantStore);

  }
  
  @PutMapping(value = "/private/catalog/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(httpMethod = "PUT", value = "Update catalog", notes = "",
      response = Void.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public void updateCatalog(
	  @PathVariable Long id,
      @RequestBody @Valid PersistableCatalog catalog,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {
    
	  catalog.setId(id);
	  catalogFacade.saveCatalog(catalog, merchantStore);
  }
  


/*  @DeleteMapping(value = "/private/content/")
  @ApiOperation(httpMethod = "DETETE", value = "Deletes a file from CMS", notes = "Delete a file from server",
  response = Void.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public void deleteFile(
      @Valid ContentName name, 
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
    contentFacade.delete(merchantStore, name.getName(), name.getContentType());
  }*/
}
