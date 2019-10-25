package com.salesmanager.shop.store.api.v1.system;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.shop.ContactForm;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.EmailTemplatesUtils;
import com.salesmanager.shop.utils.LanguageUtils;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1")
public class ContactApi {

  @Inject private StoreFacade storeFacade;

  @Inject private LanguageUtils languageUtils;

  @Inject private LanguageService languageService;

  @Inject private EmailTemplatesUtils emailTemplatesUtils;

  @PostMapping("/contact")
  @ApiOperation(
      httpMethod = "POST",
      value = "Sends an email contact us to store owner",
      notes = "",
      produces = "application/json")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public void contact(
      @Valid @RequestBody ContactForm contact,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletRequest request) {
    Locale locale = languageService.toLocale(language, merchantStore);
    emailTemplatesUtils.sendContactEmail(contact, merchantStore, locale, request.getContextPath());
  }
}
