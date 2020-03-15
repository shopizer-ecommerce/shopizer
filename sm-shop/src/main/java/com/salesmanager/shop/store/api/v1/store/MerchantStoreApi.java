package com.salesmanager.shop.store.api.v1.store;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.ImmutableMap;
import com.salesmanager.core.model.common.Criteria;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.merchant.MerchantStoreCriteria;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.model.store.PersistableBrand;
import com.salesmanager.shop.model.store.PersistableMerchantStore;
import com.salesmanager.shop.model.store.ReadableBrand;
import com.salesmanager.shop.model.store.ReadableMerchantStore;
import com.salesmanager.shop.model.store.ReadableMerchantStoreList;
import com.salesmanager.shop.store.api.exception.RestApiException;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;
import com.salesmanager.shop.utils.ServiceRequestCriteriaBuilderUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1")
@Api(tags = {"Merchant and store management resource (Merchant - Store Management Api)"})
@SwaggerDefinition(tags = {
    @Tag(name = "Merchant and store management", description = "Edit merchants (retailers) and stores")
})
public class MerchantStoreApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(MerchantStoreApi.class);

  private static final Map<String, String> MAPPING_FIELDS = ImmutableMap.<String, String>builder()
      .put("name", "storename").put("readableAudit.user", "auditSection.modifiedBy").build();

  @Inject
  private StoreFacade storeFacade;


  @Inject
  private UserFacade userFacade;

  @GetMapping(value = {"/store/{code}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get merchant store", notes = "",
      response = ReadableMerchantStore.class)
  public ReadableMerchantStore store(@PathVariable String code,
      @RequestParam(value = "lang", required = false) String lang) {
    return storeFacade.getByCode(code, lang);
  }
  
  @GetMapping(value = {"/private/merchant/{code}/stores"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get retailer child stores", notes = "Merchant (retailer) can have multiple stores",
      response = ReadableMerchantStore.class)
  @ApiImplicitParams({
    @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ReadableMerchantStoreList stores(
	  @PathVariable String code,
      @ApiIgnore Language language,
      @RequestParam(value = "page", required = false, defaultValue="0") Integer page,
      @RequestParam(value = "count", required = false, defaultValue="10") Integer count) {
	  
	  
	    String authenticatedUser = userFacade.authenticatedUser();
	    if (authenticatedUser == null) {
	      throw new UnauthorizedException();
	    }
	    
	    userFacade.authorizedGroup(authenticatedUser,
	            Stream.of("SUPERADMIN","ADMIN").collect(Collectors.toList()));
	  
    return storeFacade.getChildStores(language, code, page, count);
  }
  
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/private/stores"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get list of stores. Returns all retailers and stores", notes = "",
      response = ReadableMerchantStore.class)
  @ApiImplicitParams({
	    @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ReadableMerchantStoreList get(
		  @ApiIgnore Language language,
	      @RequestParam(value = "page", required = false, defaultValue="0") Integer page,
	      @RequestParam(value = "count", required = false, defaultValue="10") Integer count,
		  HttpServletRequest request) {
    
	   //requires superadmin to see all
	    String authenticatedUser = userFacade.authenticatedUser();
	    if (authenticatedUser == null) {
	      throw new UnauthorizedException();
	    }
	    
	    userFacade.authorizedGroup(authenticatedUser,
	            Stream.of("SUPERADMIN").collect(Collectors.toList()));


	  MerchantStoreCriteria criteria = createMerchantStoreCriteria(page, count, request);

	  return storeFacade.findAll(criteria, language, page, count);
  }
  
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/stores"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get list of store names. Returns all retailers and stores", notes = "",
      response = ReadableMerchantStore.class)
  public List<ReadableMerchantStore> get(
		  HttpServletRequest request) {

	  return storeFacade.getMerchantStoreNames();
  }


  @ResponseStatus(HttpStatus.OK)
  @PostMapping(value = {"/private/store"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "POST", value = "Creates a new store", notes = "",
      response = ReadableMerchantStore.class)
  public ReadableMerchantStore create(@Valid @RequestBody PersistableMerchantStore store) {
    return storeFacade.create(store);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = {"/private/store/{code}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "PUT", value = "Updates a store", notes = "",
      response = ReadableMerchantStore.class)
  public ReadableMerchantStore update(@PathVariable String code, @Valid @RequestBody PersistableMerchantStore store,
      HttpServletRequest request) {

    String userName = getUserFromRequest(request);
    validateUserPermission(userName, code);
    store.setCode(code);
    return storeFacade.update(store);
  }


  private String getUserFromRequest(HttpServletRequest request) {
    // user doing action must be attached to the store being modified
    Principal principal = request.getUserPrincipal();
    return principal.getName();
  }

  private void validateUserPermission(String userName, String code) {
    // TODO reviewed Spring Security should be used
    if (!userFacade.authorizedStore(userName, code)) {
      throw new UnauthorizedException("User " + userName + " not authorized");
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/private/store/{code}/marketing"},
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get store branding and marketing details", notes = "",
      response = ReadableBrand.class)
  public ReadableBrand getStoreMarketing(@PathVariable String code, HttpServletRequest request) {
    String userName = getUserFromRequest(request);
    validateUserPermission(userName, code);
    return storeFacade.getBrand(code);
  }
  
  /**
   * List child stores
   * @param code
   * @param request
   * @return
   */
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/private/merchant/{code}/children"},
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get child stores", notes = "",
      response = List.class)
  @ApiImplicitParams({
    @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ReadableMerchantStoreList children(
      @PathVariable String code,
      @ApiIgnore Language language,
      @RequestParam(value = "page", required = false, defaultValue="0") Integer page,
      @RequestParam(value = "count", required = false, defaultValue="10") Integer count,
      HttpServletRequest request) {
    
    String userName = getUserFromRequest(request);
    validateUserPermission(userName, code);
    return storeFacade.getChildStores(language, code, page, count);

  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = {"/private/store/{code}/marketing"},
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "POST", value = "Create or save store branding and marketing details",
      notes = "", response = ReadableBrand.class)
  public void saveStoreMarketing(@PathVariable String code, @RequestBody PersistableBrand brand,
      HttpServletRequest request) {
    String userName = getUserFromRequest(request);
    validateUserPermission(userName, code);
    storeFacade.createBrand(code, brand);
  }


  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = {"/private/store/{code}/marketing/logo"})
  @ApiOperation(httpMethod = "POST", value = "Add store logo", notes = "")
  public void addLogo(@PathVariable String code, @RequestParam("file") MultipartFile uploadfile,
      HttpServletRequest request) {

    // user doing action must be attached to the store being modified
    String userName = getUserFromRequest(request);

    validateUserPermission(userName, code);

    if (uploadfile.isEmpty()) {
      throw new RestApiException("Upload file is empty");
    }


    InputContentFile cmsContentImage = createInputContentFile(uploadfile);
    storeFacade.addStoreLogo(code, cmsContentImage);
  }


  private InputContentFile createInputContentFile(MultipartFile image) {

    InputContentFile cmsContentImage = null;

    try {

      InputStream input = new ByteArrayInputStream(image.getBytes());
      cmsContentImage = new InputContentFile();
      cmsContentImage.setFileName(image.getOriginalFilename());
      cmsContentImage.setMimeType(image.getContentType());
      cmsContentImage.setFileContentType(FileContentType.LOGO);
      cmsContentImage.setFile(input);


    } catch (IOException ioe) {
      throw new RestApiException(ioe);
    }

    return cmsContentImage;
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = {"/private/store/{code}/marketing/logo"})
  @ApiOperation(httpMethod = "DELETE", value = "Delete store logo", notes = "",
      response = Void.class)
  public void deleteStoreLogo(@PathVariable String code, HttpServletRequest request) {

    // user doing action must be attached to the store being modified
    String userName = getUserFromRequest(request);
    validateUserPermission(userName, code);

    // delete store logo
    storeFacade.deleteLogo(code);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/private/store/unique"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Check if store code already exists", notes = "",
      response = EntityExists.class)
  public ResponseEntity<EntityExists> exists(@RequestParam(value = "code") String code) {
    boolean isStoreExist = storeFacade.existByCode(code);
    return new ResponseEntity<EntityExists>(new EntityExists(isStoreExist), HttpStatus.OK);
  }


  private MerchantStoreCriteria createMerchantStoreCriteria(Integer start, Integer count,
      HttpServletRequest request) {
    MerchantStoreCriteria criteria = (MerchantStoreCriteria) ServiceRequestCriteriaBuilderUtils
        .buildRequest(MAPPING_FIELDS, request);

    
    
    return criteria;
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = {"/private/store/{code}"})
  @ApiOperation(httpMethod = "DELETE", value = "Deletes a store", notes = "", response = Void.class)
  public void delete(@PathVariable String code, HttpServletRequest request) {
    String userName = getUserFromRequest(request);
    validateUserPermission(userName, code);
    storeFacade.delete(code);
  }
  
  private MerchantStoreCriteria filter(HttpServletRequest request) {
	    Criteria criteria = ServiceRequestCriteriaBuilderUtils.buildRequest(MAPPING_FIELDS, request);
	    return (MerchantStoreCriteria)criteria;
   }

}
