package com.salesmanager.shop.mapper.catalog;

import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.category.CategoryDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ReadableCategoryMapper implements Mapper<Category, ReadableCategory> {

  @Override
  public ReadableCategory convert(Category source, MerchantStore store, Language language) {
    ReadableCategory target = new ReadableCategory();

    Optional<com.salesmanager.shop.model.catalog.category.CategoryDescription> categoryDescription =
        getCategoryDescription(source, language, target);
    categoryDescription.ifPresent(target::setDescription);

    Optional<com.salesmanager.shop.model.catalog.category.Category> parentCategory =
        createParentCategory(source);
    parentCategory.ifPresent(target::setParent);

    Optional.ofNullable(source.getDepth()).ifPresent(target::setDepth);

    target.setLineage(source.getLineage());
    target.setCode(source.getCode());
    target.setId(source.getId());
    target.setSortOrder(source.getSortOrder());
    target.setVisible(source.isVisible());
    target.setFeatured(source.isFeatured());
    return target;
  }

  private Optional<com.salesmanager.shop.model.catalog.category.CategoryDescription>
      getCategoryDescription(Category source, Language language, ReadableCategory target) {

    Optional<CategoryDescription> description =
        getCategoryDescription(source.getDescriptions(), source.getDescription(), language);
    if (source.getDescriptions() != null
        && !source.getDescriptions().isEmpty()
        && description.isPresent()) {
      return Optional.of(convertDescription(description.get(), source));
    } else {
      return Optional.empty();
    }
  }

  private com.salesmanager.shop.model.catalog.category.CategoryDescription convertDescription(
      CategoryDescription description, Category source) {
    final com.salesmanager.shop.model.catalog.category.CategoryDescription desc =
        new com.salesmanager.shop.model.catalog.category.CategoryDescription();

    desc.setFriendlyUrl(description.getSeUrl());
    desc.setName(description.getName());
    desc.setId(source.getId());
    desc.setDescription(description.getName());
    desc.setKeyWords(description.getMetatagKeywords());
    desc.setHighlights(description.getCategoryHighlight());
    desc.setTitle(description.getMetatagTitle());
    desc.setMetaDescription(description.getMetatagDescription());
    return desc;
  }

  private Optional<CategoryDescription> getCategoryDescription(
      List<CategoryDescription> categoryDescriptionsLang,
      CategoryDescription categoryDescription,
      Language language) {
    Optional<CategoryDescription> categoryDescriptionByLang =
        categoryDescriptionsLang.stream()
            .filter(desc -> desc.getLanguage().getCode().equals(language.getCode()))
            .findAny();
    if (categoryDescriptionByLang.isPresent()) {
      return categoryDescriptionByLang;
    } else {
      return Optional.ofNullable(categoryDescription);
    }
  }

  private Optional<com.salesmanager.shop.model.catalog.category.Category> createParentCategory(
      Category source) {
    return Optional.ofNullable(source.getParent())
        .map(
            parentValue -> {
              final com.salesmanager.shop.model.catalog.category.Category parent =
                  new com.salesmanager.shop.model.catalog.category.Category();
              parent.setCode(source.getParent().getCode());
              parent.setId(source.getParent().getId());
              return parent;
            });
  }
}
