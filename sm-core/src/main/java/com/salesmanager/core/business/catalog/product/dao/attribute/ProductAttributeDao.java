package com.salesmanager.core.business.catalog.product.dao.attribute;

import java.util.List;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

public interface ProductAttributeDao extends SalesManagerEntityDao<Long, ProductAttribute> {

	List<ProductAttribute> getByOptionId(MerchantStore store,
			Long id);

	List<ProductAttribute> getByOptionValueId(MerchantStore store,
			Long id);

	List<ProductAttribute> getByProduct(MerchantStore store,
			Product product, Language language);

	List<ProductAttribute> getByAttributeIds(MerchantStore store, List<Long> ids);

}
