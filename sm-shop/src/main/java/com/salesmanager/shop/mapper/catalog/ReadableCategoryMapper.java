package com.salesmanager.shop.mapper.catalog;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.category.CategoryDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategoryFull;

@Component
public class ReadableCategoryMapper implements Mapper<Category, ReadableCategory> {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ReadableCategoryMapper.class);

  @Override
  public ReadableCategory convert(Category source, MerchantStore store, Language language) {

    if (Objects.isNull(language)) {
      ReadableCategoryFull target = new ReadableCategoryFull();
      List<com.salesmanager.shop.model.catalog.category.CategoryDescription> descriptions = source.getDescriptions().stream()
              .map(this::convertDescription)
              .collect(Collectors.toList());
      target.setDescriptions(descriptions);
      fillReadableCategory(target, source);
      return target;
    } else {
      ReadableCategory target = new ReadableCategory();
      Optional<com.salesmanager.shop.model.catalog.category.CategoryDescription> description = source.getDescriptions().stream()
              .filter(d -> language.getId().equals(d.getLanguage().getId()))
              .map(this::convertDescription)
              .findAny();
      description.ifPresent(target::setDescription);
      fillReadableCategory(target, source);
      return target;
    }
  }

  private void fillReadableCategory(ReadableCategory target, Category source) {
    Optional<com.salesmanager.shop.model.catalog.category.Category> parentCategory =
        createParentCategory(source);
    parentCategory.ifPresent(target::setParent);

    Optional.ofNullable(source.getDepth()).ifPresent(target::setDepth);

    target.setLineage(source.getLineage());
    target.setStore(source.getMerchantStore().getCode());
    target.setCode(source.getCode());
    target.setId(source.getId());
    target.setSortOrder(source.getSortOrder());
    target.setVisible(source.isVisible());
    target.setFeatured(source.isFeatured());
  }

  private com.salesmanager.shop.model.catalog.category.CategoryDescription convertDescription(
      CategoryDescription description) {
    final com.salesmanager.shop.model.catalog.category.CategoryDescription desc =
        new com.salesmanager.shop.model.catalog.category.CategoryDescription();

    desc.setFriendlyUrl(description.getSeUrl());
    desc.setName(description.getName());
    desc.setId(description.getId());
    desc.setDescription(description.getDescription());
    desc.setKeyWords(description.getMetatagKeywords());
    desc.setHighlights(description.getCategoryHighlight());
    desc.setLanguage(description.getLanguage().getCode());
    desc.setTitle(description.getMetatagTitle());
    desc.setMetaDescription(description.getMetatagDescription());
    return desc;
  }


  private Optional<com.salesmanager.shop.model.catalog.category.Category> createParentCategory(
      Category source) {
    return Optional.ofNullable(source.getParent()).map(parentValue -> {
      final com.salesmanager.shop.model.catalog.category.Category parent =
          new com.salesmanager.shop.model.catalog.category.Category();
      parent.setCode(source.getParent().getCode());
      parent.setId(source.getParent().getId());
      return parent;
    });
  }

  @Override
  public ReadableCategory merge(Category source, ReadableCategory destination,
                                MerchantStore store, Language language) {
    return destination;
  }
}
