package com.salesmanager.shop.store.api.v1.search;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.ValueList;
import com.salesmanager.shop.model.catalog.SearchProductList;
import com.salesmanager.shop.model.catalog.SearchProductRequest;
import com.salesmanager.shop.store.controller.search.facade.SearchFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;

/**
 * Api for searching shopizer catalog based on search term when filtering products based on product
 * attribute is required, see /api/v1/product
 *
 * @author c.samson
 */
@RestController
@RequestMapping("/api/v1")
public class SearchApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(SearchApi.class);

  @Inject private SearchFacade searchFacade;

  @Inject private StoreFacade storeFacade;

  @Inject private LanguageUtils languageUtils;

  /**
   * Search products from underlying elastic search
   *
   * @param searchRequest
   * @param request
   * @return
   */
  @PostMapping("/search")
  public @ResponseBody SearchProductList search(
      @RequestBody SearchProductRequest searchRequest,
      MerchantStore merchantStore,
      Language language,
      HttpServletRequest request) {
    return searchFacade.search(merchantStore, language, searchRequest);
  }

  @PostMapping("/search/autocomplete")
  public @ResponseBody ValueList autocomplete(
      @RequestBody SearchProductRequest searchRequest,
      MerchantStore merchantStore,
      Language language,
      HttpServletRequest request) {
    return searchFacade.autocompleteRequest(searchRequest.getQuery(), merchantStore, language);
  }
}
