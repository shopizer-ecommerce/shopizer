package com.salesmanager.shop.store.api.v1.store;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.ImageContentFile;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.merchant.MerchantStoreCriteria;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.PersistableImage;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.model.shop.PersistableMerchantStore;
import com.salesmanager.shop.model.shop.ReadableBrand;
import com.salesmanager.shop.model.shop.ReadableMerchantStore;
import com.salesmanager.shop.model.shop.ReadableMerchantStoreList;
import com.salesmanager.shop.populator.catalog.PersistableProductImagePopulator;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;
import com.salesmanager.shop.utils.LanguageUtils;
import com.salesmanager.shop.utils.ServiceRequestCriteriaBuilderUtils;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
public class MerchantStoreApi {



  @Inject
  private StoreFacade storeFacade;

  @Inject
  private LanguageUtils languageUtils;

  @Inject
  LanguageService languageService;

  @Inject
  UserFacade userFacade;

  private static final Map<String, String> mappingFields =
      Stream
          .of(new AbstractMap.SimpleImmutableEntry<>("name", "storename"),
              new AbstractMap.SimpleImmutableEntry<>("readableAudit.user",
                  "auditSection.modifiedBy"))
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

  private static final Logger LOGGER = LoggerFactory.getLogger(MerchantStoreApi.class);

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/store/{store}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get merchant store", notes = "",
      response = ReadableMerchantStore.class)
  public @ResponseBody ReadableMerchantStore store(@PathVariable String store,
      @RequestParam(value = "lang", required = false) String lang, HttpServletRequest request,
      HttpServletResponse response) {

    Language l = languageUtils.getServiceLanguage(lang);

    ReadableMerchantStore readableStore = storeFacade.getByCode(store, l);


    return readableStore;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/private/store"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "POST", value = "Creates a new store", notes = "",
      response = ReadableMerchantStore.class)
  public ResponseEntity<ReadableMerchantStore> create(
      @Valid @RequestBody PersistableMerchantStore store, HttpServletRequest request,
      HttpServletResponse response) throws Exception {



    // check if store code exists
    MerchantStore mStore = storeFacade.get(store.getCode());
    if (mStore != null) {
      response.sendError(503, "MerhantStore " + store.getCode() + " already exists");
    }

    try {

      storeFacade.create(store);

      ReadableMerchantStore readable =
          storeFacade.getByCode(store.getCode(), languageService.defaultLanguage());

      return new ResponseEntity<ReadableMerchantStore>(readable, HttpStatus.OK);

    } catch (Exception e) {
      LOGGER.error("Error while creating store ", e);
      try {
        response.sendError(503, "Error while creating store " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = {"/private/store/{code}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "PUT", value = "Updates a store", notes = "",
      response = ReadableMerchantStore.class)
  public ResponseEntity<ReadableMerchantStore> update(
      @Valid @RequestBody PersistableMerchantStore store, HttpServletRequest request,
      HttpServletResponse response) throws Exception {



    try {

      // user doing action must be attached to the store being modified
      Principal principal = request.getUserPrincipal();
      String userName = principal.getName();

      if (!userFacade.authorizedStore(userName, store.getCode())) {
        response.sendError(401, "User " + userName + " not authorized");
        return null;
      }
      storeFacade.update(store);

      ReadableMerchantStore readable =
          storeFacade.getByCode(store.getCode(), languageService.defaultLanguage());

      return new ResponseEntity<ReadableMerchantStore>(readable, HttpStatus.OK);

    } catch (Exception e) {
      LOGGER.error("Error while updating store ", e);
      try {
        response.sendError(503, "Error while updating store " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/private/store/{code}/marketing"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get store branding and marketing details", notes = "",
      response = ReadableBrand.class)
  public ResponseEntity<ReadableBrand> getStoreMarketing(@PathVariable String code,
      HttpServletRequest request, HttpServletResponse response) {

    try {

      // user doing action must be attached to the store being modified
      Principal principal = request.getUserPrincipal();
      String userName = principal.getName();

      if (!userFacade.authorizedStore(userName, code)) {
        throw new UnauthorizedException("User " + userName + " not authorized");
      }

      ReadableBrand brand = storeFacade.getBrand(code);
      return new ResponseEntity<ReadableBrand>(brand, HttpStatus.OK);


    } catch (Exception e) {
      throw new ServiceRuntimeException(
          "Exception while getting brand for store " + code + " " + e.getMessage());
    }
  }
  
  
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping( value={"/private/store/{code}/marketing/logo"}, method=RequestMethod.POST)
  public ResponseEntity<Void> createLogo(@PathVariable String code, @Valid @RequestBody PersistableImage image, HttpServletRequest request, HttpServletResponse response) throws Exception {
     
    try {

      // user doing action must be attached to the store being modified
      Principal principal = request.getUserPrincipal();
      String userName = principal.getName();

      if (!userFacade.authorizedStore(userName, code)) {
        throw new UnauthorizedException("User " + userName + " not authorized");
      }

      InputStream input = new ByteArrayInputStream(image.getBytes());
            
      InputContentFile cmsContentImage = new InputContentFile();
      cmsContentImage.setFileName(image.getName());
      cmsContentImage.setMimeType(image.getContentType());
      cmsContentImage.setFileContentType(FileContentType.LOGO);
      cmsContentImage.setFile( input);

      storeFacade.addStoreLogo(code, cmsContentImage);

      return new ResponseEntity<Void>(HttpStatus.OK);


    } catch (Exception e) {
      throw new ServiceRuntimeException(
          "Exception while adding brand logo for store " + code + " " + e.getMessage());
    } 

  }

  
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/private/store/{code}/marketing/logo"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "DELETE", value = "Delete store logo", notes = "",
      response = Void.class)
  public ResponseEntity<Void> deleteStoreLogo(@PathVariable String code,
      HttpServletRequest request, HttpServletResponse response) {

    try {

      // user doing action must be attached to the store being modified
      Principal principal = request.getUserPrincipal();
      String userName = principal.getName();

      if (!userFacade.authorizedStore(userName, code)) {
        throw new UnauthorizedException("User " + userName + " not authorized");
      }
      
      //delete store logo
      storeFacade.deleteLogo(code);

      return new ResponseEntity<Void>(HttpStatus.OK);


    } catch (Exception e) {
      throw new ServiceRuntimeException(
          "Exception while getting brand for store " + code + " " + e.getMessage());
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/private/store/unique"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Check if store code already exists", notes = "",
      response = EntityExists.class)
  public ResponseEntity<EntityExists> exists(@RequestParam(value = "code") String code,
      HttpServletRequest request, HttpServletResponse response) throws Exception {



    try {


      MerchantStore store = storeFacade.get(code);

      EntityExists exists = new EntityExists();
      if (store != null) {
        exists.setExists(true);
      }
      return new ResponseEntity<EntityExists>(exists, HttpStatus.OK);

    } catch (Exception e) {
      LOGGER.error("Error while updating store ", e);
      try {
        response.sendError(503, "Error while getting store " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/private/stores"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Check list of stores", notes = "",
      response = EntityExists.class)
  public ResponseEntity<ReadableMerchantStoreList> list(
      @RequestParam(value = "start", required = false) Integer start,
      @RequestParam(value = "length", required = false) Integer count,
      @RequestParam(value = "code", required = false) String code, HttpServletRequest request,
      HttpServletResponse response) throws Exception {



    try {

      // Principal principal = request.getUserPrincipal();
      // String userName = principal.getName();

      /*
       * Enumeration names = request.getParameterNames(); while (names.hasMoreElements()) { //
       * System.out.println(names.nextElement().toString()); String param =
       * names.nextElement().toString(); String val = request.getParameter(param);
       * System.out.println("param ->" + param + " Val ->" + val); }
       */

      MerchantStoreCriteria criteria = (MerchantStoreCriteria) ServiceRequestCriteriaBuilderUtils
          .buildRequest(mappingFields, request);

      if (start != null)
        criteria.setStartIndex(start);
      if (count != null)
        criteria.setMaxCount(count);
      if (!StringUtils.isBlank(criteria.getSearch())) {
        criteria.setCode(criteria.getSearch());
        criteria.setName(criteria.getSearch());
      }

      ReadableMerchantStoreList readableList =
          storeFacade.getByCriteria(criteria, languageService.defaultLanguage());
      readableList.setRecordsFiltered(readableList.getTotalCount());
      readableList.setRecordsTotal(readableList.getTotalCount());

      // datatable stuff
      String drawParam = request.getParameter("draw");
      if (!StringUtils.isEmpty(drawParam)) {
        readableList.setDraw(Integer.parseInt(request.getParameter("draw")));
      }


      return new ResponseEntity<ReadableMerchantStoreList>(readableList, HttpStatus.OK);

    } catch (Exception e) {
      LOGGER.error("Error while getting store list ", e);
      try {
        response.sendError(503, "Error while getting store list " + e.getMessage());
      } catch (Exception ignore) {
      }

      return null;
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = {"/private/store/{code}"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
  @ApiOperation(httpMethod = "DELETE", value = "Deletes a store", notes = "",
      response = ResponseEntity.class)
  public ResponseEntity<Void> delete(@PathVariable String code, HttpServletRequest request,
      HttpServletResponse response) {

    // user doing action must be attached to the store being modified
    Principal principal = request.getUserPrincipal();
    String userName = principal.getName();

    try { // TODO remove trycatch

      if (!userFacade.authorizedStore(userName, code)) {
        // response.sendError(401, "User " + userName + " not authorized");
        // return null;
        throw new UnauthorizedException("Not authorized");
      }

    } catch (Exception e) {
      // todo to be removed
    }


    storeFacade.delete(code);
    return new ResponseEntity<Void>(HttpStatus.OK);

  }

}
