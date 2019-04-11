package com.salesmanager.shop.store.api.v1.category;

import static com.salesmanager.core.business.constants.Constants.DEFAULT_STORE;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.store.controller.category.facade.CategoryFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

@RestController
@Api(value = "/api/v1/category")
@RequestMapping(value = "/api/v1")
public class CategoryApi {

  private static final int DEFAULT_CATEGORY_DEPTH = 0;

  @Inject private CategoryFacade categoryFacade;
  @Inject private CategoryService categoryService;
  @Inject private StoreFacade storeFacade;
  @Inject private LanguageUtils languageUtils;

  @GetMapping(
      value = "/category/{id}",
      produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
  @ApiOperation(
      httpMethod = "GET",
      value = "Get category list for an given Category id",
      notes = "List current Category and child category")
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "List of category found",
            response = ReadableCategory.class)
      })
  public ReadableCategory get(
      @PathVariable(name = "id") final Long categoryId,
      @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
      @RequestParam(value = "lang", required = false) String lang,
      HttpServletRequest request) {
    MerchantStore merchantStore = getMerchantStore(storeCode);
    Language language = getLanguage(request, merchantStore);
    return categoryFacade.getById(merchantStore, categoryId, language);
  }

  /**
   * Get all category starting from root filter can be used for filtering on fields only featured is
   * supported
   *
   * @param lang
   * @param request
   * @return
   */
  @GetMapping(
      value = "/category",
      produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
  public List<ReadableCategory> getFiltered(
      @RequestParam(value = "filter", required = false) String filter,
      @RequestParam(value = "lang", required = false) String lang,
      @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
      HttpServletRequest request) {

    MerchantStore merchantStore = getMerchantStore(storeCode);
    Language language = getLanguage(request, merchantStore);
    return categoryFacade.getCategoryHierarchy(
        merchantStore, DEFAULT_CATEGORY_DEPTH, language, filter);
  }

  /** Category creation */
  @PostMapping(
      value = "/private/category",
      produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
  public PersistableCategory createCategory(
      @Valid @RequestBody PersistableCategory category,
      @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
      HttpServletRequest request) {
    MerchantStore merchantStore = getMerchantStore(storeCode);
    getLanguage(request, merchantStore);
    return categoryFacade.saveCategory(merchantStore, category);
  }

  @PutMapping(
      value = "/private/category/{id}",
      produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
  public PersistableCategory update(
      @PathVariable Long id,
      @Valid @RequestBody PersistableCategory category,
      @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode) {

    MerchantStore merchantStore = getMerchantStore(storeCode);
    return categoryFacade.saveCategory(merchantStore, category);
  }

  @DeleteMapping(
      value = "/private/category/{id}",
      produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
  @ResponseStatus(NO_CONTENT)
  public void delete(@PathVariable("id") Long categoryId) {
    categoryFacade.deleteCategory(categoryId);
  }

  private Language getLanguage(HttpServletRequest request, MerchantStore merchantStore) {
    return languageUtils.getRESTLanguage(request, merchantStore);
  }

  private MerchantStore getMerchantStore(String storeCode) {
    return storeFacade.get(storeCode);
  }
}
