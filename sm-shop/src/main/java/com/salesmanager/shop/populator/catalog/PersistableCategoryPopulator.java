package com.salesmanager.shop.populator.catalog;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.category.CategoryDescription;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;


public class PersistableCategoryPopulator extends
    AbstractDataPopulator<PersistableCategory, Category> {


  private CategoryService categoryService;
  private LanguageService languageService;

  public CategoryService getCategoryService() {
    return categoryService;
  }

  public void setCategoryService(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  public LanguageService getLanguageService() {
    return languageService;
  }

  public void setLanguageService(LanguageService languageService) {
    this.languageService = languageService;
  }

  @Override
  public Category populate(PersistableCategory source, Category target,
      MerchantStore store, Language language)
      throws ServiceException {

    Validate.notNull(categoryService, "Requires to set CategoryService");
    Validate.notNull(languageService, "Requires to set LanguageService");

    target.setMerchantStore(store);
    target.setCode(source.getCode());
    target.setSortOrder(source.getSortOrder());
    target.setVisible(source.isVisible());
    target.setFeatured(source.isFeatured());

    //get parent

    if (source.getParent() == null) {

      target.setParent(null);
      target.setLineage("/");
      target.setDepth(0);

    } else {
      Category parent = null;
      if (!StringUtils.isBlank(source.getParent().getCode())) {
        parent = categoryService.getByCode(store.getCode(), source.getParent().getCode());
      } else if (source.getParent().getId() != null) {
        parent = categoryService.getById(source.getParent().getId());
      }

      if (parent != null && !Objects.equals(parent.getMerchantStore().getId(), store.getId())) {
        throw new RuntimeException("Store id does not belong to specified parent id");
      }

      if (parent != null) {
        target.setParent(parent);
        String lineage = parent.getLineage();
        int depth = parent.getDepth();

        target.setDepth(depth + 1);
        target.setLineage(
            lineage + parent.getId() + "/");
      }
    }

    if (!CollectionUtils.isEmpty(source.getChildren())) {
      for (PersistableCategory cat : source.getChildren()) {
        Category persistCategory = this.populate(cat, new Category(), store, language);
        target.getCategories().add(persistCategory);
      }
    }

    if (!CollectionUtils.isEmpty(source.getDescriptions())) {
      List<com.salesmanager.core.model.catalog.category.CategoryDescription> descriptions = new ArrayList<com.salesmanager.core.model.catalog.category.CategoryDescription>();
      for (CategoryDescription description : source.getDescriptions()) {
        com.salesmanager.core.model.catalog.category.CategoryDescription desc = new com.salesmanager.core.model.catalog.category.CategoryDescription();
        desc.setCategory(target);
        desc.setCategoryHighlight(description.getHighlights());
        desc.setDescription(description.getDescription());
        desc.setName(description.getName());
        desc.setMetatagDescription(description.getMetaDescription());
        desc.setMetatagTitle(description.getTitle());
        desc.setSeUrl(description.getFriendlyUrl());
        Language lang = languageService.getByCode(description.getLanguage());
        desc.setLanguage(lang);
        descriptions.add(desc);
      }
      target.setDescriptions(descriptions);
    }

    return target;
  }

  @Override
  protected Category createTarget() {
    // TODO Auto-generated method stub
    return null;
  }
}
