package com.salesmanager.core.business.services.catalog.product.availability;

import javax.inject.Inject;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.product.availability.PageableProductAvailabilityRepository;
import com.salesmanager.core.business.repositories.catalog.product.availability.ProductAvailabilityRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.merchant.MerchantStore;

@Service("productAvailabilityService")
public class ProductAvailabilityServiceImpl extends
    SalesManagerEntityServiceImpl<Long, ProductAvailability> implements ProductAvailabilityService {


  private ProductAvailabilityRepository productAvailabilityRepository;

  @Inject
  private PageableProductAvailabilityRepository pageableProductAvailabilityRepository;

  @Inject
  public ProductAvailabilityServiceImpl(
      ProductAvailabilityRepository productAvailabilityRepository) {
    super(productAvailabilityRepository);
    this.productAvailabilityRepository = productAvailabilityRepository;
  }


  @Override
  public void saveOrUpdate(ProductAvailability availability) throws ServiceException {

    if (availability.getId() != null && availability.getId() > 0) {
      this.update(availability);
    } else {
      this.create(availability);
    }

  }



  /**
   * Returns inventory of a child store
   */
  @Override
  public ProductAvailability getByStore(Product product, MerchantStore store)
      throws ServiceException {
    Validate.notNull(product, "Product cannot be null");
    Validate.notNull(store, "MerchantStore cannot be null");
    return productAvailabilityRepository.getByStore(product.getId(), store.getCode());
  }


  @Override
  public ProductAvailability getByOwner(Product product, String owner) throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public Page<ProductAvailability> listByProduct(Product product, MerchantStore store, String child,
      int page, int count) throws ServiceException {
    Validate.notNull(product, "Product cannot be null");
    Validate.notNull(store, "MercantStore cannot be null");
    Pageable pageRequest = PageRequest.of(page, count);
    return pageableProductAvailabilityRepository.listByStore(product.getId(), store.getId(), child,
        pageRequest);
  }


  @Override
  public int count(Product product) {
    return productAvailabilityRepository.count(product.getId());
  }


  @Override
  public ProductAvailability getById(Long availabilityId, MerchantStore store)
      throws ServiceException {
    Validate.notNull(store, "Merchant must not be null");
    return productAvailabilityRepository.getById(availabilityId);
  }


  @Override
  public ProductAvailability getByInventoryId(Long productId, Long availabilityId,
      MerchantStore store) throws ServiceException {
    Validate.notNull(store, "Merchant must not be null");
    return productAvailabilityRepository.getByStore(productId, availabilityId);
  }



}
