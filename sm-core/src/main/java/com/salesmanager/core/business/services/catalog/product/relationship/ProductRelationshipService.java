package com.salesmanager.core.business.services.catalog.product.relationship;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationshipType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import java.util.List;


public interface ProductRelationshipService extends
    SalesManagerEntityService<Long, ProductRelationship> {

  void saveOrUpdate(ProductRelationship relationship) throws ServiceException;

  /**
   * Get product relationship List for a given type (RELATED, FEATURED...) and language allows to
   * return the product description in the appropriate language
   */
  List<ProductRelationship> getByType(MerchantStore store, Product product,
      ProductRelationshipType type, Language language) throws ServiceException;

  /**
   * Get product relationship List for a given type (RELATED, FEATURED...) and a given base product
   */
  List<ProductRelationship> getByType(MerchantStore store, Product product,
      ProductRelationshipType type)
      throws ServiceException;

  /**
   * Get product relationship List for a given type (RELATED, FEATURED...)
   */
  List<ProductRelationship> getByType(MerchantStore store,
      ProductRelationshipType type) throws ServiceException;

  List<ProductRelationship> listByProduct(Product product)
      throws ServiceException;

  List<ProductRelationship> getByType(MerchantStore store,
      ProductRelationshipType type, Language language)
      throws ServiceException;

  /**
   * Get a list of relationship acting as groups of products
   */
  List<ProductRelationship> getGroups(MerchantStore store);

  /**
   * Creates a product group
   */
  void addGroup(MerchantStore store, String groupName) throws ServiceException;

  List<ProductRelationship> getByGroup(MerchantStore store, String groupName)
      throws ServiceException;

  void deleteGroup(MerchantStore store, String groupName)
      throws ServiceException;

  void deactivateGroup(MerchantStore store, String groupName)
      throws ServiceException;

  void activateGroup(MerchantStore store, String groupName)
      throws ServiceException;

  List<ProductRelationship> getByGroup(MerchantStore store, String groupName,
      Language language) throws ServiceException;
}
