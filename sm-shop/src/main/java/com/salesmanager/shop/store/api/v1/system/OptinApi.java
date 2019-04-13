package com.salesmanager.shop.store.api.v1.system;

import static com.salesmanager.core.business.constants.Constants.DEFAULT_STORE;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.system.ReadableOptin;
import com.salesmanager.shop.store.controller.optin.OptinFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;
import io.swagger.annotations.ApiOperation;

/** Optin a customer to events such s newsletter */
@RestController
@RequestMapping("/api/v1")
public class OptinApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(OptinApi.class);

  @Inject private OptinFacade optinFacade;

  @Inject private StoreFacade storeFacade;

  @Inject private LanguageUtils languageUtils;

  /** Create new optin */
  @PostMapping("/optin")
  @ApiOperation(
      httpMethod = "POST",
      value = "Creates an optin event type definition",
      notes = "",
      produces = "application/json")
  public ReadableOptin create(
      @Valid @RequestBody ReadableOptin optin, MerchantStore merchantStore, Language language) {
    return optinFacade.create(optin, merchantStore, language);
  }
}
