package com.salesmanager.shop.store.facade.product;

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
import com.salesmanager.shop.model.catalog.product.inventory.ReadableInventoryList;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductInventoryFacade;

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
  public ReadableInventoryList getInventory(Long productId, MerchantStore store, String child,
      Language language, int page, int count) {

    try {
      Product product = productService.getById(productId);

      if (product == null) {
        throw new ResourceNotFoundException("Product with id [" + productId + "] not found");
      }

      if (product.getMerchantStore().getId().intValue() != store.getId().intValue()) {
        throw new ResourceNotFoundException("Product with id [" + productId
            + "] not found for store id [" + store.getInvoiceTemplate() + "]");
      }

      ReadableInventoryList returnList = new ReadableInventoryList();

      Page<ProductAvailability> availabilities =
          productAvailabilityService.listByProduct(product, store, child, page, count);
      returnList.setTotalPages(availabilities.getTotalPages());
      returnList.setRecordsTotal(availabilities.getTotalElements());
      returnList.setNumber(availabilities.getNumber());

      for (ProductAvailability availability : availabilities) {
        ReadableInventory inv = new ReadableInventory();
        inv = readableInventoryMapper.convert(availability, inv, store, language);
        returnList.getInventory().add(inv);
      }

      return returnList;


    } catch (ServiceException e) {
      throw new ServiceRuntimeException("An error occured while getting inventory list", e);
    }

  }



  @Override
  public void delete(Long inventoryId, MerchantStore store) {
    try {
      ProductAvailability availability = productAvailabilityService.getById(inventoryId, store);
      productAvailabilityService.delete(availability);
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Error while deleting inventory", e);
    }

  }

  @Override
  public ReadableInventory get(Long inventoryId, MerchantStore store, Language language) {
    try {

      ProductAvailability availability = productAvailabilityService.getById(inventoryId, store);
      if (availability == null) {
        throw new ResourceNotFoundException("Inventory with id [" + inventoryId + "] not found");
      }
      ReadableInventory inv = new ReadableInventory();
      inv = readableInventoryMapper.convert(availability, inv, store, language);
      return inv;


    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Error while getting availability " + inventoryId, e);
    }
  }



  @Override
  public ReadableInventory get(Long productId, String child, Language language) {

    try {
      Product product = productService.getById(productId);
      MerchantStore store = merchantStoreService.getByCode(child);

      if (product == null) {
        throw new ResourceNotFoundException("Product with id [" + productId + "] not found");
      }

      if (store == null) {
        throw new ResourceNotFoundException("MerchantStore [" + child + "] not found");
      }

      if (store.getParent() == null || store.getParent().getId().intValue() != product
          .getMerchantStore().getId().intValue()) {
        throw new ResourceNotFoundException(
            "MerchantStore [" + child + "] is not a store of retailer [" + store.getCode() + "]");
      }

      ProductAvailability availability;

      availability = productAvailabilityService.getByStore(product, store);
      if (availability == null) {
        throw new ResourceNotFoundException("Inventory with not found");
      }
      ReadableInventory inv = new ReadableInventory();
      inv = readableInventoryMapper.convert(availability, inv, store, language);
      return inv;
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Error while getting inventory", e);
    }

  }



  @Override
  public ReadableInventory add(Long productId, PersistableInventory inventory, MerchantStore store,
      Language language) {

    Validate.notNull(productId, "Product id cannot be null");
    Validate.notNull(inventory, "Inventory cannot be null");
    Validate.notNull(store, "MerchantStore cannot be null");

    Product product = productService.getById(productId);

    if (product == null) {
      throw new ResourceNotFoundException("Product with id [" + productId + "] not found");
    }

    ProductAvailability availability =
        productInventoryMapper.convert(inventory, store, store.getDefaultLanguage());
    availability.setProduct(product);
    availability.setMerchantStore(store);
    // add product

    try {
      productAvailabilityService.saveOrUpdate(availability);

      ReadableInventory returnObject = get(availability.getId(), store, language);

      return returnObject;
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Cannot create Inventory", e);
    }

  }



  @Override
  public ReadableInventory get(Long productId, Long inventoryId, MerchantStore store,
      Language language) {
    try {
      Product product = productService.getById(productId);

      if (product == null) {
        throw new ResourceNotFoundException("Product with id [" + productId + "] not found");
      }

      if (product.getMerchantStore().getId().intValue() != store.getId().intValue()) {
        throw new ResourceNotFoundException(
            "Product with id [" + productId + "] not found for store [" + store.getCode() + "]");
      }


      ProductAvailability availability;

      availability = productAvailabilityService.getByInventoryId(productId, inventoryId, store);
      if (availability == null) {
        throw new ResourceNotFoundException("Inventory with id [" + inventoryId + "] not found");
      }
      ReadableInventory inv = new ReadableInventory();
      inv = readableInventoryMapper.convert(availability, inv, store, language);
      return inv;
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Error while getting inventory", e);
    }
  }



  @Override
  public void update(Long productId, PersistableInventory inventory, MerchantStore store,
      Language language) {

    Validate.notNull(productId, "Product id cannot be null");
    Validate.notNull(inventory, "Inventory cannot be null");
    Validate.notNull(store, "MerchantStore cannot be null");

    try {
      Product product = productService.getById(productId);

      if (product == null) {
        throw new ResourceNotFoundException("Product with id [" + productId + "] not found");
      }

      ProductAvailability availability =
          productAvailabilityService.getById(inventory.getId(), store);

      if (availability == null) {
        throw new ResourceNotFoundException(
            "Availability with id [" + inventory.getId() + "] not found");
      }
      
      if(availability.getProduct().getId().longValue() != productId) {
          throw new ResourceNotFoundException(
                  "Availability with id [" + inventory.getId() + "] not found for product id [" + productId + "]");
      }
      
      inventory.setProductId(product.getId());

      availability = productInventoryMapper.convert(inventory, availability, store, language);
      availability.setProduct(product);
      availability.setMerchantStore(store);
      // add product


      productAvailabilityService.saveOrUpdate(availability);

    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Cannot create Inventory", e);
    }

  }

}
