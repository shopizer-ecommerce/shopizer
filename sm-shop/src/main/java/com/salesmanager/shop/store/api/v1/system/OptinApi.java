package com.salesmanager.shop.store.api.v1.system;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.system.PersistableOptin;
import com.salesmanager.shop.model.system.ReadableOptin;
import com.salesmanager.shop.store.controller.optin.OptinFacade;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/** Optin a customer to events such s newsletter */
@RestController
@RequestMapping("/api/v1")
public class OptinApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(OptinApi.class);

  @Inject private OptinFacade optinFacade;


  /** Create new optin */
  @PostMapping("/private/optin")
  @ApiOperation(
      httpMethod = "POST",
      value = "Creates an optin event type definition",
      notes = "",
      produces = "application/json")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public ReadableOptin create(
      @Valid @RequestBody PersistableOptin optin, 
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletRequest request) {
    LOGGER.debug("[" + request.getUserPrincipal().getName() + "] creating optin [" + optin.getCode() + "]");
    return optinFacade.create(optin, merchantStore, language);
  }
}
