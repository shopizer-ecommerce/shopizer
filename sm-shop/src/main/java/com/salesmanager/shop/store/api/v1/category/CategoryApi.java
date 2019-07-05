package com.salesmanager.shop.store.api.v1.category;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.store.controller.category.facade.CategoryFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(value = "/api/v1/category")
@RequestMapping(value = "/api/v1")
public class CategoryApi {

  private static final int DEFAULT_CATEGORY_DEPTH = 0;

  @Inject private CategoryFacade categoryFacade;
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
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en")
  })
  public ReadableCategory get(
      @PathVariable(name = "id") Long categoryId, 
      @ApiIgnore MerchantStore merchantStore, 
      @ApiIgnore Language language) {
    return categoryFacade.getById(merchantStore, categoryId, language);
  }
  
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/private/category/unique"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiImplicitParams({
    @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
    @ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en")
  })
  @ApiOperation(httpMethod = "GET", value = "Check if category code already exists", notes = "",
      response = EntityExists.class)
  public ResponseEntity<EntityExists> exists(
      @RequestParam(value = "code") String code,
      @ApiIgnore MerchantStore merchantStore, 
      @ApiIgnore Language language) {
    boolean isCategoryExist = categoryFacade.existByCode(merchantStore,code);
    return new ResponseEntity<EntityExists>(new EntityExists(isCategoryExist), HttpStatus.OK);
  }

  /**
   * Get all category starting from root filter can be used for filtering on fields only featured is
   * supported
   *
   * @return
   */
  @GetMapping(
      value = "/category",
      produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
  @ApiOperation(
      httpMethod = "GET",
      value = "Get category hierarchy from root",
      notes = "Does not return any product attached")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en")
  })
  public List<ReadableCategory> getFiltered(
      @RequestParam(value = "filter", required = false) String filter,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
    return categoryFacade.getCategoryHierarchy(
        merchantStore, DEFAULT_CATEGORY_DEPTH, language, filter);
  }
  

  @PostMapping(
      value = "/private/category",
      produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en")
  })
  public PersistableCategory createCategory(
      @Valid @RequestBody PersistableCategory category,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
    return categoryFacade.saveCategory(merchantStore, category);
  }

  @PutMapping(
      value = "/private/category/{id}",
      produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT")
  })
  public PersistableCategory update(
      @PathVariable Long id,
      @Valid @RequestBody PersistableCategory category,
      @ApiIgnore MerchantStore merchantStore) {
    return categoryFacade.saveCategory(merchantStore, category);
  }
  
/*  @PutMapping(
      value = "/private/category/{id}/move/{parent}",
      produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
  @ApiOperation(
      httpMethod = "PUT",
      value = "Move a category under another category",
      notes = "Move category {id} under category {parent}")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT")
  })
  public void move(
      @PathVariable Long id,
      @PathVariable Long parent,
      @ApiIgnore MerchantStore merchantStore) {
    return;
  }*/

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
