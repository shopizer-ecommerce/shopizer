package com.salesmanager.shop.store.facade.product;

import com.salesmanager.shop.model.entity.ReadableEntityList;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.availability.ProductAvailabilityService;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.inventory.PersistableInventoryMapper;
import com.salesmanager.shop.mapper.inventory.ReadableInventoryMapper;
import com.salesmanager.shop.model.catalog.product.inventory.PersistableInventory;
import com.salesmanager.shop.model.catalog.product.inventory.ReadableInventory;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductInventoryFacade;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.salesmanager.shop.util.ReadableEntityUtil.createReadableList;

@Service("productInventoryFacade")
@Profile({"default", "cloud", "gcp", "aws", "mysql"})
public class ProductInventoryFacadeImpl implements ProductInventoryFacade {

  @Autowired
  private ProductAvailabilityService productAvailabilityService;

  @Autowired
  private ProductService productService;

  @Autowired
  private MerchantStoreService merchantStoreService;

  @Autowired
  private ReadableInventoryMapper readableInventoryMapper;

  @Autowired
  private PersistableInventoryMapper productInventoryMapper;

  @Override
  public ReadableEntityList<ReadableInventory> getInventory(Long productId, MerchantStore store, String child,
      Language language, int page, int count) {
      Product product = getProductById(productId);
    validateProductHasSameStore(store, product);

    Page<ProductAvailability> availabilities =
            productAvailabilityService.listByProduct(product, store, child, page, count);

      List<ReadableInventory> inventories = availabilities.stream()
              .map(pa -> readableInventoryMapper.convert(pa, store, language))
              .collect(Collectors.toList());
      return createReadableList(availabilities, inventories);
  }

  private void validateProductHasSameStore(MerchantStore store, Product product) {
    if (!product.getMerchantStore().getId().equals(store.getId())) {
      throw new ResourceNotFoundException("Product with id [" + product.getId()
          + "] not found for store id [" + store.getInvoiceTemplate() + "]");
    }
  }

  @Override
  public void delete(Long inventoryId, MerchantStore store) {
    Optional<ProductAvailability> availability = productAvailabilityService.getById(inventoryId, store);
    try {
      if (availability.isPresent()) {
        productAvailabilityService.delete(availability.get());
      }
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Error while deleting inventory", e);
    }
  }

  @Override
  public ReadableInventory get(Long inventoryId, MerchantStore store, Language language) {
    ProductAvailability availability = getAvailabilityById(store, inventoryId);
      return readableInventoryMapper.convert(availability, store, language);
  }

  @Override
  public ReadableInventory get(Long productId, String child, Language language) {
      Product product = getProductById(productId);
      MerchantStore store = getMerchantStore(child);

      if (isStoreParentNotExist(store) || store.getParent().getId().equals(product
              .getMerchantStore().getId())) {
        throw new ResourceNotFoundException(
            "MerchantStore [" + child + "] is not a store of retailer [" + store.getCode() + "]");
      }

      ProductAvailability availability = productAvailabilityService.getByStore(product, store)
              .orElseThrow(() -> new ResourceNotFoundException("Inventory with not found"));

      return readableInventoryMapper.convert(availability, store, language);
  }

  private boolean isStoreParentNotExist(MerchantStore store) {
    return Objects.isNull(store.getParent());
  }

  private MerchantStore getMerchantStore(String child) {
    try {
      return Optional.ofNullable(merchantStoreService.getByCode(child))
              .orElseThrow(() -> new ResourceNotFoundException("MerchantStore [" + child + "] not found"));
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Error while getting inventory", e);
    }
  }

  private Product getProductById(Long productId) {
    return productService.retrieveById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id [" + productId + "] not found"));
  }

  @Override
  public ReadableInventory add(Long productId, PersistableInventory inventory, MerchantStore store,
      Language language) {
    Validate.notNull(store, "MerchantStore cannot be null");
    ProductAvailability availability = getProductAvailabilityToSave(inventory, store, productId);
    saveOrUpdate(availability);
    return get(availability.getId(), store, language);
  }

  private void saveOrUpdate(ProductAvailability availability) {
    try {
      productAvailabilityService.saveOrUpdate(availability);
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Cannot create Inventory", e);
    }
  }

  private ProductAvailability getProductAvailabilityToSave(PersistableInventory inventory, MerchantStore store, Long productId) {
    Product product = getProductById(productId);
    ProductAvailability availability =
        productInventoryMapper.convert(inventory, store, store.getDefaultLanguage());
    availability.setProduct(product);
    availability.setMerchantStore(store);
    return availability;
  }

  @Override
  public ReadableInventory get(Long productId, Long inventoryId, MerchantStore store,
      Language language) {

      Product product = getProductById(productId);
      if (product.getMerchantStore().getId().intValue() != store.getId().intValue()) {
        throw new ResourceNotFoundException(
            "Product with id [" + productId + "] not found for store [" + store.getCode() + "]");
      }

      ProductAvailability availability = productAvailabilityService.getByInventoryId(productId, inventoryId, store)
              .orElseThrow(() -> new ResourceNotFoundException("Inventory with id [" + inventoryId + "] not found"));
      return readableInventoryMapper.convert(availability, store, language);
  }

  @Override
  public void update(Long productId, PersistableInventory inventory, MerchantStore store,
      Language language) {

    Validate.notNull(productId, "Product id cannot be null");
    Validate.notNull(inventory, "Inventory cannot be null");
    Validate.notNull(store, "MerchantStore cannot be null");

    Product product = getProductById(productId);

    ProductAvailability availability = getAvailabilityById(store, inventory.getId());

    if (availability.getProduct().getId().longValue() != productId) {
      throw new ResourceNotFoundException(
              "Availability with id [" + inventory.getId() + "] not found for product id [" + productId + "]");
    }

    inventory.setProductId(product.getId());

    availability = productInventoryMapper.merge(inventory, availability, store, language);
    availability.setProduct(product);
    availability.setMerchantStore(store);
    saveOrUpdate(availability);
  }

  private ProductAvailability getAvailabilityById(MerchantStore store, Long id) {
    //TODO move to productAvailabilityService service
    return productAvailabilityService.getById(id, store)
            .orElseThrow(() -> new ResourceNotFoundException("Inventory with id [" + id + "] not found"));
  }

}
