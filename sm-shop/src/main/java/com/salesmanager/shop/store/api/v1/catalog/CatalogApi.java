package com.salesmanager.shop.store.api.v1.catalog;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.catalog.PersistableCatalog;
import com.salesmanager.shop.model.catalog.catalog.PersistableCatalogEntry;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalog;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalogEntry;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalogEntryList;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalogList;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
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
  
  
  @GetMapping(value = "/private/catalogs")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(httpMethod = "GET", value = "Get catalogs by merchant", notes = "",
      response = ReadableCatalogList.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ReadableCatalogList getCatalogs(
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language,
      @RequestParam(value = "page", required = false, defaultValue="0") Integer page,
      @RequestParam(value = "count", required = false, defaultValue="10") Integer count,
      HttpServletRequest request) {

	  return catalogFacade.listCatalogs(catalogFilter(request), merchantStore, language, page, count);

  }
  
  
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/private/catalog/unique"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiImplicitParams({
    @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
    @ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en")
  })
  @ApiOperation(httpMethod = "GET", value = "Check if catalog code already exists", notes = "",
      response = EntityExists.class)
  public ResponseEntity<EntityExists> exists(
      @RequestParam(value = "code") String code,
      @ApiIgnore MerchantStore merchantStore, 
      @ApiIgnore Language language) {
      boolean existByCode = catalogFacade.uniqueCatalog(code, merchantStore);
      return new ResponseEntity<EntityExists>(new EntityExists(existByCode), HttpStatus.OK);
  }


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
    
	  return catalogFacade.saveCatalog(catalog, merchantStore, language);

  }
  
  @PatchMapping(value = "/private/catalog/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(httpMethod = "PATCH", value = "Update catalog", notes = "",
      response = Void.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public void updateCatalog(
	  @PathVariable Long id,
      @RequestBody @Valid PersistableCatalog catalog,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {
    
	  catalog.setId(id);
	  catalogFacade.updateCatalog(id, catalog, merchantStore, language);

  }
  
  @GetMapping(value = "/private/catalog/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(httpMethod = "GET", value = "Get catalog", notes = "",
      response = Void.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ReadableCatalog getCatalog(
	  @PathVariable Long id,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {

	  return catalogFacade.getCatalog(id, merchantStore, language);

  }
  


  @DeleteMapping(value = "/private/catalog/{id}")
  @ApiOperation(httpMethod = "DELETE", value = "Deletes a catalog", notes = "",
  response = Void.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public void deleteCatalog(
      @PathVariable Long id,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
    
	  catalogFacade.deleteCatalog(id, merchantStore, language);
  }
  
  @PostMapping(value = "/private/catalog/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(httpMethod = "POST", value = "Add catalog entry to catalog", notes = "",
      response = ReadableCatalogEntry.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ReadableCatalogEntry addCatalogEntry(
      @PathVariable Long id,
	  @RequestBody @Valid PersistableCatalogEntry catalogEntry,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {
    
	  
	  
	  ReadableCatalog c = catalogFacade.getCatalog(id, merchantStore, language);
	  
	  if(c == null) {
		  throw new ResourceNotFoundException("Catalog id [" + id + "] not found");
	  }
	  
	  catalogEntry.setCatalog(c.getCode());
	  return catalogFacade.addCatalogEntry(catalogEntry, merchantStore, language);


  }
  
  @DeleteMapping(value = "/private/catalog/{id}/entry/{entryId}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(httpMethod = "DELETE", value = "Remove catalog entry from catalog", notes = "",
      response = Void.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public void removeCatalogEntry(
      @PathVariable Long id,
      @PathVariable Long entryId,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {
	  
	  
	  catalogFacade.removeCatalogEntry(id, entryId, merchantStore, language);



  }
  
  @GetMapping(value = "/private/catalog/{id}/entry")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(httpMethod = "GET", value = "Get catalog entry by catalog", notes = "",
      response = ReadableCatalogEntryList.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ReadableCatalogEntryList getCatalogEntry(
	  @PathVariable(value="id") Long id,
      @ApiIgnore MerchantStore merchantStore, 
      @ApiIgnore Language language,
      @RequestParam(value = "page", required = false, defaultValue="0") Integer page,
      @RequestParam(value = "count", required = false, defaultValue="10") Integer count,
      HttpServletRequest request) {

	  return catalogFacade.listCatalogEntry(catalogEntryFilter(request), id, merchantStore, language, page, count);


  }
  
  private Optional<String> catalogFilter(HttpServletRequest request) {

	    return Optional.ofNullable((String)request.getAttribute("code"));
  }
  
  private Optional<String> catalogEntryFilter(HttpServletRequest request) {

	    return Optional.ofNullable((String)request.getAttribute("name"));
}
  
}
