package com.salesmanager.shop.store.api.v1.category;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

import com.salemanager.shop.exception.ResourceNotFoundException;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.store.api.exception.RestApiException;
import com.salesmanager.shop.store.controller.category.facade.CategoryFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.ImageFilePath;
import com.salesmanager.shop.utils.LanguageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.salesmanager.core.business.constants.Constants.DEFAULT_STORE;

@Slf4j
@RestController
@Produces({MediaType.APPLICATION_JSON})
@Api(value = "/api/v1/category")
@RequestMapping("/api/v1")
public class CategoryApi {

    public static final int DEFAULT_CATEGORY_DEPTH = 0;
    @Inject
    private CategoryFacade categoryFacade;

    @Inject
    private CategoryService categoryService;

    @Inject
    @Qualifier("img")
    private ImageFilePath imageUtils;

    @Inject
    private StoreFacade storeFacade;

    @Inject
    private LanguageUtils languageUtils;

    @GetMapping("/category/{id}")
    @ApiOperation(httpMethod = "GET", value = "Get category list for an given Category id", notes = "List current Category and child category")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "List of category found", response = ReadableCategory.class)})
    public ReadableCategory get(@PathVariable final Long categoryId,
                                @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
                                @RequestParam(value = "lang", required = false) String lang,
                                HttpServletRequest request) {
        try {
            MerchantStore merchantStore = storeFacade.get(storeCode);
            Language language = languageUtils.getRESTLanguage(request, merchantStore);
            ReadableCategory categories = categoryFacade.getById(merchantStore, categoryId, language);
            return categories;
        } catch (Exception exception) {
            throw new RestApiException("Error while getting category", exception);
        }
    }

    /**
     * Get all category starting from root
     * filter can be used for filtering on fields
     * only featured is supported
     *
     * @param lang
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/category")
    public List<ReadableCategory> getFiltered(
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "lang", required = false) String lang,
            @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
            HttpServletRequest request, HttpServletResponse response) {

        try {
            MerchantStore merchantStore = storeFacade.get(storeCode);
            Language language = languageUtils.getRESTLanguage(request, merchantStore);
            List<ReadableCategory> categories = categoryFacade.getCategoryHierarchy(merchantStore, DEFAULT_CATEGORY_DEPTH, language, filter);
            return categories;
        } catch (Exception exception) {
            throw new RestApiException(String.format("Error while getting root category %s", exception.getMessage()),
                    exception);
        }
    }

    /**
     * Category creation
     */
    @PostMapping("/private/category")
    public PersistableCategory createCategory(@Valid @RequestBody PersistableCategory category,
                                              @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
                                              HttpServletRequest request,
                                              HttpServletResponse response) {
        try {
            MerchantStore merchantStore = storeFacade.get(storeCode);
            //TODO review maybe redundant
            Language language = languageUtils.getRESTLanguage(request, merchantStore);
            categoryFacade.saveCategory(merchantStore, category);

            category.setId(category.getId());
            return category;

        } catch (Exception exception) {
            throw new RestApiException("Error while creating category", exception);
        }
    }

    @PutMapping("/private/category/{id}")
    public PersistableCategory update(@PathVariable Long id,
                                      @Valid @RequestBody PersistableCategory category,
                                      @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        try {
            MerchantStore merchantStore = storeFacade.get(storeCode);
            categoryFacade.saveCategory(merchantStore, category);
            return category;
        } catch (Exception exception) {
            throw new RestApiException("Error while updating category", exception);
        }

    }

    @DeleteMapping("/private/category/{id}")
    public void delete(@PathVariable Long categoryId) {
        try {
            Category category = Optional.ofNullable(categoryService.getById(categoryId))
                    //TODO should be moved to categoryService
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("No Category found for ID : %s", categoryId)));

            categoryFacade.deleteCategory(category);
        } catch (Exception exception) {
            throw new RestApiException("Error while deleting category", exception);
        }
    }

}
