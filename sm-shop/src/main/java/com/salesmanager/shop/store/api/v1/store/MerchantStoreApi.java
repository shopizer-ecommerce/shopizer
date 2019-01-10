package com.salesmanager.shop.store.api.v1.store;

import java.security.Principal;
import java.util.AbstractMap;
import java.util.Enumeration;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.merchant.MerchantStoreCriteria;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.model.shop.PersistableMerchantStore;
import com.salesmanager.shop.model.shop.ReadableMerchantStore;
import com.salesmanager.shop.model.shop.ReadableMerchantStoreList;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;
import com.salesmanager.shop.utils.LanguageUtils;
import com.salesmanager.shop.utils.ServiceRequestCriteriaBuilderUtils;
import io.swagger.annotations.ApiOperation;

@Controller
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
  @RequestMapping(value = {"/store/{store}"}, method = RequestMethod.GET)
  @ApiOperation(httpMethod = "GET", value = "Get merchant store", notes = "",
      produces = "application/json", response = ReadableMerchantStore.class)
  public @ResponseBody ReadableMerchantStore store(@PathVariable String store,
      @RequestParam(value = "lang", required = false) String lang, HttpServletRequest request,
      HttpServletResponse response) {

    Language l = languageUtils.getServiceLanguage(lang);



    ReadableMerchantStore readableStore = storeFacade.getByCode(store, l);


    return readableStore;
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = {"/private/store"}, method = RequestMethod.POST)
  @ApiOperation(httpMethod = "POST", value = "Creates a new store", notes = "",
      produces = "application/json", response = ReadableMerchantStore.class)
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
  @RequestMapping(value = {"/private/store/{code}"}, method = RequestMethod.PUT)
  @ApiOperation(httpMethod = "PUT", value = "Updates a store", notes = "",
      produces = "application/json", response = ReadableMerchantStore.class)
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
  @RequestMapping(value = {"/private/store/unique"}, method = RequestMethod.GET)
  @ApiOperation(httpMethod = "GET", value = "Check if store code already exists", notes = "",
      produces = "application/json", response = EntityExists.class)
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
  @RequestMapping(value = {"/private/stores"}, method = RequestMethod.GET)
  @ApiOperation(httpMethod = "GET", value = "Check list of stores", notes = "",
      produces = "application/json", response = EntityExists.class)
  public ResponseEntity<ReadableMerchantStoreList> list(
      @RequestParam(value = "start", required = false) Integer start,
      @RequestParam(value = "length", required = false) Integer count,
      @RequestParam(value = "code", required = false) String code, HttpServletRequest request,
      HttpServletResponse response) throws Exception {



    try {

      // Principal principal = request.getUserPrincipal();
      // String userName = principal.getName();
      
      Enumeration names =  request.getParameterNames();
      while(names.hasMoreElements()){
          //System.out.println(names.nextElement().toString());
          String param = names.nextElement().toString();
          String val = request.getParameter(param);
          System.out.println("param ->" + param + " Val ->" + val);
      }

      MerchantStoreCriteria criteria = (MerchantStoreCriteria) ServiceRequestCriteriaBuilderUtils.buildRequest(mappingFields, request);

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

}
