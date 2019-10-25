package com.salesmanager.shop.store.controller.manufacturer.facade;

import java.util.List;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.manufacturer.PersistableManufacturer;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturerList;
import com.salesmanager.shop.model.entity.ListCriteria;

/**
 * Manufacturer / brand / collection product grouping
 * @author carlsamson
 *
 */
public interface ManufacturerFacade {
  
  List<ReadableManufacturer> getByProductInCategory(MerchantStore store, Language language, Long categoryId);
  
  /**
   * Creates or saves a manufacturer
   * 
   * @param manufacturer
   * @param store
   * @param language
   * @throws Exception
   */
  void saveOrUpdateManufacturer(PersistableManufacturer manufacturer, MerchantStore store,
      Language language) throws Exception;

  /**
   * Deletes a manufacturer
   * 
   * @param manufacturer
   * @param store
   * @param language
   * @throws Exception
   */
  void deleteManufacturer(Manufacturer manufacturer, MerchantStore store, Language language)
      throws Exception;

  /**
   * Get a Manufacturer by id
   * 
   * @param id
   * @param store
   * @param language
   * @return
   * @throws Exception
   */
  ReadableManufacturer getManufacturer(Long id, MerchantStore store, Language language)
      throws Exception;

  /**
   * Get all Manufacturer
   * 
   * @param store
   * @param language
   * @return
   * @throws Exception
   */
  ReadableManufacturerList getAllManufacturers(MerchantStore store, Language language, ListCriteria criteria, int page, int count) ;
  
  /**
   * Determines if manufacturer code already exists
   * @param store
   * @param manufacturerCode
   * @return
   */
  boolean manufacturerExist(MerchantStore store, String manufacturerCode);

}
