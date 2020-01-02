package com.salesmanager.shop.store.api.v1.search;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.SearchProductList;
import com.salesmanager.shop.model.catalog.SearchProductRequest;
import com.salesmanager.shop.model.entity.ValueList;
import com.salesmanager.shop.store.controller.search.facade.SearchFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Api for searching shopizer catalog based on search term when filtering products based on product
 * attribute is required, see /api/v1/product
 *
 * @author c.samson
 */
@RestController
@RequestMapping("/api/v1")
public class SearchApi {

  @Inject private SearchFacade searchFacade;

  @Inject private StoreFacade storeFacade;

  @Inject private LanguageUtils languageUtils;

  /**
   * Search products from underlying elastic search
   */
  @PostMapping("/search")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
    @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody SearchProductList search(
      @RequestBody SearchProductRequest searchRequest,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletRequest request) {
    return searchFacade.search(merchantStore, language, searchRequest);
  }

  @PostMapping("/search/autocomplete")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
    @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public @ResponseBody ValueList autocomplete(
      @RequestBody SearchProductRequest searchRequest,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletRequest request) {
    return searchFacade.autocompleteRequest(searchRequest.getQuery(), merchantStore, language);
  }
}
