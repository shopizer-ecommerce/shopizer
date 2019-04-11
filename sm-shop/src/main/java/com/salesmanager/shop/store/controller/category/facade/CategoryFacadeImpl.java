package com.salesmanager.shop.store.controller.category.facade;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.populator.catalog.PersistableCategoryPopulator;
import com.salesmanager.shop.populator.catalog.ReadableCategoryPopulator;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.converter.Converter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service(value = "categoryFacade")
public class CategoryFacadeImpl implements CategoryFacade {

  @Inject private CategoryService categoryService;

  @Inject private LanguageService languageService;

  @Inject private PersistableCategoryPopulator persistableCatagoryPopulator;

  @Inject private Converter<Category, ReadableCategory> categoryReadableCategoryConverter;

  private static final String FEATURED_CATEGORY = "featured";

  @Override
  public Category getOne(Long categoryId) {
    return Optional.ofNullable(categoryService.getById(categoryId))
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    String.format("No Category found for ID : %s", categoryId)));
  }

  @Override
  public List<ReadableCategory> getCategoryHierarchy(
      MerchantStore store, int depth, Language language, String filter) {
    List<Category> categories = getCategories(store, depth, language, filter);

    List<ReadableCategory> readableCategories =
        categories.stream()
            .filter(Category::isVisible)
            .map(cat -> categoryReadableCategoryConverter.convert(cat, store, language))
            .collect(Collectors.toList());

    Map<Long, ReadableCategory> readableCategoryMap =
        readableCategories.stream()
            .collect(Collectors.toMap(ReadableCategory::getId, Function.identity()));

    readableCategories.stream()
        .filter(ReadableCategory::isVisible)
        .filter(cat -> Objects.nonNull(cat.getParent()))
        .filter(cat -> readableCategoryMap.containsKey(cat.getParent().getId()))
        .forEach(
            readableCategory -> {
              ReadableCategory parentCategory =
                  readableCategoryMap.get(readableCategory.getParent().getId());
              if (parentCategory != null) {
                parentCategory.getChildren().add(readableCategory);
              }
            });

    return readableCategoryMap.values().stream()
        .filter(cat -> cat.getDepth() == 0)
        .sorted(Comparator.comparing(ReadableCategory::getSortOrder))
        .collect(Collectors.toList());
  }

  private List<Category> getCategories(
      MerchantStore store, int depth, Language language, String filter) {
    List<Category> categories;

    if (StringUtils.isNotBlank(filter)) {
      // as of 2.2.0 only filter by featured is supported
      if (FEATURED_CATEGORY.equals(filter)) {
        categories = categoryService.listByDepthFilterByFeatured(store, depth, language);
      } else {
        categories = categoryService.listByDepth(store, depth, language);
      }
    } else {
      categories = categoryService.listByDepth(store, depth, language);
    }
    return categories;
  }

  @Override
  public PersistableCategory saveCategory(MerchantStore store, PersistableCategory category) {
    try {

      /*		PersistableCategoryPopulator populator = new PersistableCategoryPopulator();
      populator.setCategoryService(categoryService);
      populator.setLanguageService(languageService);*/

      Category target = Optional.ofNullable(category.getId())
          .filter(id -> id > 0)
          .map(categoryService::getById)
          .orElse(new Category());

      Category dbCategory =
          persistableCatagoryPopulator.populate(
              category, target, store, store.getDefaultLanguage());

      saveCategory(store, dbCategory, null);

      // set category id
      category.setId(dbCategory.getId());
      return category;
    } catch (ConversionException | ServiceException e) {
      throw new ServiceRuntimeException("Error while updating category", e);
    }
  }

  private void saveCategory(MerchantStore store, Category category, Category parent)
      throws ServiceException {

    /**
     * c.children1
     *
     * <p>children1.children1 children1.children2
     *
     * <p>children1.children2.children1
     */

    /** set lineage * */
    if (parent != null) {
      category.setParent(category);

      String lineage = parent.getLineage();
      int depth = parent.getDepth();

      category.setDepth(depth + 1);
      category.setLineage(
          new StringBuilder().append(lineage).append(parent.getId()).append("/").toString());
    }

    category.setMerchantStore(store);

    // remove children
    List<Category> children = category.getCategories();
    category.setCategories(null);

    /** set parent * */
    if (parent != null) {
      category.setParent(parent);
    }

    categoryService.saveOrUpdate(category);

    if (!CollectionUtils.isEmpty(children)) {
      parent = category;
      for (Category sub : children) {

        saveCategory(store, sub, parent);
      }
    }
  }

  @Override
  public ReadableCategory getById(MerchantStore store, Long id, Language language) {
    Category categoryModel = getCategoryById(id, language);

    StringBuilder lineage =
        new StringBuilder().append(categoryModel.getLineage()).append(categoryModel.getId());

    // get children
    ReadableCategory readableCategory =
        categoryReadableCategoryConverter.convert(categoryModel, store, language);

    List<Category> children = getListByLineage(store, lineage);

    List<ReadableCategory> childrenCats =
        children.stream()
            .map(cat -> categoryReadableCategoryConverter.convert(cat, store, language))
            .collect(Collectors.toList());

    Map<Long, ReadableCategory> categoryMap =
        childrenCats.stream()
            .collect(Collectors.toMap(ReadableCategory::getId, Function.identity()));
    categoryMap.put(readableCategory.getId(), readableCategory);

    // traverse map and add child to parent
    for (ReadableCategory readable : childrenCats) {

      if (readable.getParent() != null) {

        ReadableCategory rc = categoryMap.get(readable.getParent().getId());
        rc.getChildren().add(readable);
      }
    }
    return readableCategory;
  }

  private List<Category> getListByLineage(MerchantStore store, StringBuilder lineage) {
    try {
      return categoryService.getListByLineage(store, lineage.toString());
    } catch (ServiceException e) {
      throw new ServiceRuntimeException(
          String.format("Error while getting root category %s", e.getMessage()), e);
    }
  }

  private Category getCategoryById(Long id, Language language) {
    return Optional.ofNullable(categoryService.getOneByLanguage(id, language))
        .orElseThrow(() -> new ResourceNotFoundException("Category id not found"));
  }

  @Override
  public void deleteCategory(Category category) {
    try {
      categoryService.delete(category);
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Error while deleting category", e);
    }
  }

  @Override
  public ReadableCategory getByCode(MerchantStore store, String code, Language language)
      throws Exception {

    Validate.notNull(code, "category code must not be null");
    ReadableCategoryPopulator categoryPopulator = new ReadableCategoryPopulator();
    ReadableCategory readableCategory = new ReadableCategory();

    Category category = categoryService.getByCode(store, code);
    categoryPopulator.populate(category, readableCategory, store, language);

    return readableCategory;
  }

  @Override
  public void deleteCategory(Long categoryId) {
    Category category = getOne(categoryId);
    deleteCategory(category);
  }
}
