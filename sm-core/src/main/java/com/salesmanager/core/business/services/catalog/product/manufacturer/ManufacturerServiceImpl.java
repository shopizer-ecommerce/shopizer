package com.salesmanager.core.business.services.catalog.product.manufacturer;


import java.util.HashSet;
import java.util.List;
import javax.inject.Inject;
import org.jsoup.helper.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.product.manufacturer.ManufacturerRepository;
import com.salesmanager.core.business.repositories.catalog.product.manufacturer.PageableManufacturerRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;



@Service("manufacturerService")
public class ManufacturerServiceImpl extends SalesManagerEntityServiceImpl<Long, Manufacturer>
    implements ManufacturerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ManufacturerServiceImpl.class);

  @Inject
  private PageableManufacturerRepository pageableManufacturerRepository;
  
  private ManufacturerRepository manufacturerRepository;

  @Inject
  public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
    super(manufacturerRepository);
    this.manufacturerRepository = manufacturerRepository;
  }

  @Override
  public void delete(Manufacturer manufacturer) throws ServiceException {
    manufacturer = this.getById(manufacturer.getId());
    super.delete(manufacturer);
  }

  @Override
  public Long getCountManufAttachedProducts(Manufacturer manufacturer) throws ServiceException {
    return manufacturerRepository.countByProduct(manufacturer.getId());
    // .getCountManufAttachedProducts( manufacturer );
  }


  @Override
  public List<Manufacturer> listByStore(MerchantStore store, Language language)
      throws ServiceException {
    return manufacturerRepository.findByStoreAndLanguage(store.getId(), language.getId());
  }

  @Override
  public List<Manufacturer> listByStore(MerchantStore store) throws ServiceException {
    return manufacturerRepository.findByStore(store.getId());
  }

  @Override
  public List<Manufacturer> listByProductsByCategoriesId(MerchantStore store, List<Long> ids,
      Language language) throws ServiceException {
    return manufacturerRepository.findByCategoriesAndLanguage(ids, language.getId());
  }

  @Override
  public void addManufacturerDescription(Manufacturer manufacturer,
      ManufacturerDescription description) throws ServiceException {


    if (manufacturer.getDescriptions() == null) {
      manufacturer.setDescriptions(new HashSet<ManufacturerDescription>());
    }

    manufacturer.getDescriptions().add(description);
    description.setManufacturer(manufacturer);
    update(manufacturer);
  }

  @Override
  public void saveOrUpdate(Manufacturer manufacturer) throws ServiceException {

    LOGGER.debug("Creating Manufacturer");

    if (manufacturer.getId() != null && manufacturer.getId().longValue() > 0) {
      super.update(manufacturer);

    } else {
      super.create(manufacturer);

    }
  }

  @Override
  public Manufacturer getByCode(com.salesmanager.core.model.merchant.MerchantStore store,
      String code) {
    return manufacturerRepository.findByCodeAndMerchandStore(code, store.getId());
  }
  
  @Override
  public Manufacturer getById(Long id) {
    return manufacturerRepository.findOne(id);
  }

  @Override
  public List<Manufacturer> listByProductsInCategory(MerchantStore store, Category category,
      Language language) throws ServiceException {
    Validate.notNull(store, "Store cannot be null");
    Validate.notNull(category,"Category cannot be null");
    Validate.notNull(language, "Language cannot be null");
    return manufacturerRepository.findByProductInCategoryId(store.getId(), category.getLineage(), language.getId());
  }

  @Override
  public Page<Manufacturer> listByStore(MerchantStore store, Language language, int page, int count)
      throws ServiceException {

    Pageable pageRequest = PageRequest.of(page, count);
    return pageableManufacturerRepository.findByStore(store.getId(), language.getId(), null, pageRequest);
  }

  @Override
  public int count(MerchantStore store) {
    Validate.notNull(store, "Merchant must not be null");
    return manufacturerRepository.count(store.getId());
  }

  @Override
  public Page<Manufacturer> listByStore(MerchantStore store, Language language, String name,
      int page, int count) throws ServiceException {

    Pageable pageRequest = PageRequest.of(page, count);
    return pageableManufacturerRepository.findByStore(store.getId(), language.getId(), name, pageRequest);
  }

  @Override
  public Page<Manufacturer> listByStore(MerchantStore store, String name, int page, int count)
      throws ServiceException {

    Pageable pageRequest = PageRequest.of(page, count);
    return pageableManufacturerRepository.findByStore(store.getId(), name, pageRequest);
  }
}
