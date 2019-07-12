package com.salesmanager.shop.store.facade.manufacturer;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salesmanager.core.business.services.catalog.product.manufacturer.ManufacturerService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.category.CategoryDescription;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.manufacturer.ManufacturerDescription;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.store.controller.manufacturer.facade.ManufacturerFacade;

@Service("manufacturerFacade")
public class ManufacturerFacadeImpl implements ManufacturerFacade {

  //@Inject
  //private Mapper<Manufacturer, ReadableManufacturer> categoryReadableCategoryConverter;


  @Autowired
  private ManufacturerService manufacturerService;

  @Override
  public List<ReadableManufacturer> getByProductInCategory(MerchantStore store, Language language,
      Long categoryId) {
    // TODO Auto-generated method stub

    return null;
  }


/*  private Optional<ManufacturerDescription> manufacturerDescription(
      Manufacturer source, Language language, ReadableManufacturer target) {

    Optional<CategoryDescription> description =
        getCategoryDescription(source.getDescriptions(), source.getDescription(), language);
    if (source.getDescriptions() != null && !source.getDescriptions().isEmpty()
        && description.isPresent()) {
      return Optional.of(convertDescription(description.get(), source));
    } else {
      return Optional.empty();
    }
  }*/

/*  private com.salesmanager.shop.model.catalog.category.CategoryDescription convertDescription(
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
      List<CategoryDescription> categoryDescriptionsLang, CategoryDescription categoryDescription,
      Language language) {
    Optional<CategoryDescription> categoryDescriptionByLang = categoryDescriptionsLang.stream()
        .filter(desc -> desc.getLanguage().getCode().equals(language.getCode())).findAny();
    if (categoryDescriptionByLang.isPresent()) {
      return categoryDescriptionByLang;
    } else {
      return Optional.ofNullable(categoryDescription);
    }
  }*/

}
