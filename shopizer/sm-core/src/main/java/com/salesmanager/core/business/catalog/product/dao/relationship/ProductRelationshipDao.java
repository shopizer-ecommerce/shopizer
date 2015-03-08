package com.salesmanager.core.business.catalog.product.dao.relationship;

import java.util.List;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.relationship.ProductRelationship;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

public interface ProductRelationshipDao extends SalesManagerEntityDao<Long, ProductRelationship> {

	List<ProductRelationship> getByType(MerchantStore store, String type, Product product, Language language);

	List<ProductRelationship> getByType(MerchantStore store, String type,
			Product product);

	List<ProductRelationship> getByType(MerchantStore store, String type);

	List<ProductRelationship> listByProducts(Product product);

	List<ProductRelationship> getByType(MerchantStore store, String type,
			Language language);

	List<ProductRelationship> getGroups(MerchantStore store);

	List<ProductRelationship> getByGroup(MerchantStore store, String group);



}
