package com.salesmanager.shop.store.facade.category;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductVariant;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductVariantValue;
import com.salesmanager.shop.populator.catalog.PersistableCategoryPopulator;
import com.salesmanager.shop.populator.catalog.ReadableCategoryPopulator;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.category.facade.CategoryFacade;

@Service(value = "categoryFacade")
public class CategoryFacadeImpl implements CategoryFacade {

  @Inject private CategoryService categoryService;

  @Inject private PersistableCategoryPopulator persistableCatagoryPopulator;

  @Inject private Mapper<Category, ReadableCategory> categoryReadableCategoryConverter;
  
  @Inject private ProductAttributeService productAttributeService;

  private static final String FEATURED_CATEGORY = "featured";

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
  
  @Override
  public boolean existByCode(MerchantStore store, String code) {
    try {
      Category c =  categoryService.getByCode(store, code);
      return c!=null?true:false;
    } catch (ServiceException e) {
      throw new ServiceRuntimeException(e);
    }
  }

  private List<Category> getCategories(
      MerchantStore store, int depth, Language language, String filter) {
    if (StringUtils.isNotBlank(filter) && FEATURED_CATEGORY.equals(filter)) {
      return categoryService.getListByDepthFilterByFeatured(store, depth, language);
    } else {
      return categoryService.getListByDepth(store, depth, language);
    }
  }

  @Override
  public PersistableCategory saveCategory(MerchantStore store, PersistableCategory category) {
    try {

      Category target =
          Optional.ofNullable(category.getId())
              .filter(id -> id > 0)
              .map(categoryService::getById)
              .orElse(new Category());

      Category dbCategory = populateCategory(store, category, target);

      saveCategory(store, dbCategory, null);

      // set category id
      category.setId(dbCategory.getId());
      return category;
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Error while updating category", e);
    }
  }

  private Category populateCategory(
      MerchantStore store, PersistableCategory category, Category target) {
    try {
      return persistableCatagoryPopulator.populate(
          category, target, store, store.getDefaultLanguage());
    } catch (ConversionException e) {
      throw new ServiceRuntimeException(e);
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

    List<Category> children = getListByLineage(store, lineage.toString());

    List<ReadableCategory> childrenCats =
        children.stream()
            .map(cat -> categoryReadableCategoryConverter.convert(cat, store, language))
            .collect(Collectors.toList());

    addChildToParent(readableCategory, childrenCats);
    return readableCategory;
  }

  private void addChildToParent(ReadableCategory readableCategory,
      List<ReadableCategory> childrenCats) {
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
  }

  private List<Category> getListByLineage(MerchantStore store, String lineage) {
    try {
      return categoryService.getListByLineage(store, lineage);
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

  private Category getOne(Long categoryId) {
    return Optional.ofNullable(categoryService.getById(categoryId))
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    String.format("No Category found for ID : %s", categoryId)));
  }

  @Override
  public List<ReadableProductVariant> categoryProductVariants(Long categoryId, MerchantStore store,
      Language language) {
    Category category = categoryService.getById(categoryId);
    
    List<ReadableProductVariant> variants = new ArrayList<ReadableProductVariant>();
    
    if (category == null) {
      throw new ResourceNotFoundException("Category [" + categoryId + "] not found");
    }
    
    try {
      List<ProductAttribute> attributes = productAttributeService.getProductAttributesByCategoryLineage(store, category.getLineage(), language);
      
      /**
      Option NAME
        OptionValueName
        OptionValueName
      **/
      Map<String, List<ProductOptionValue>> rawFacet = new HashMap<String,List<ProductOptionValue>>();
      Map<String, ProductOption> references = new HashMap<String,ProductOption>();
      for(ProductAttribute attr : attributes) {
        references.put(attr.getProductOption().getCode(), attr.getProductOption());
        List<ProductOptionValue> values = rawFacet.get(attr.getProductOption().getCode());
        if(values == null) {
          values = new ArrayList<ProductOptionValue>();
          rawFacet.put(attr.getProductOption().getCode(), values);
        }
        values.add(attr.getProductOptionValue());
      }

      //for each reference set Option
      Iterator<Entry<String, ProductOption>> it = references.entrySet().iterator();
      while (it.hasNext()) {
        @SuppressWarnings("rawtypes")
        Map.Entry pair = (Map.Entry)it.next();
        ProductOption option = (ProductOption) pair.getValue();
        List<ProductOptionValue> values = rawFacet.get(option.getCode());

        ReadableProductVariant productVariant = new ReadableProductVariant();
        productVariant.setName(option.getDescriptionsSettoList().get(0).getName());
        List<ReadableProductVariantValue> optionValues = new ArrayList<ReadableProductVariantValue>();
        for(ProductOptionValue value : values) {
          ReadableProductVariantValue v = new ReadableProductVariantValue();
          v.setName(value.getDescriptionsSettoList().get(0).getName());
          v.setOption(option.getId());
          v.setValue(value.getId());
          optionValues.add(v);
        }
        productVariant.setOptions(optionValues);
        variants.add(productVariant);
      }

      return variants;
    } catch (Exception e) {
      throw new ServiceRuntimeException("An error occured while retrieving ProductAttributes",e);
    }
  }
}
