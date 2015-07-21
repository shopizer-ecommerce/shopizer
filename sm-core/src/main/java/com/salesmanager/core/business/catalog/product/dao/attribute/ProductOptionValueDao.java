package com.salesmanager.core.business.catalog.product.dao.attribute;

import java.util.List;

import com.salesmanager.core.business.catalog.product.model.attribute.ProductOptionValue;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

public interface ProductOptionValueDao extends SalesManagerEntityDao<Long, ProductOptionValue> {

	List<ProductOptionValue> listByStore(MerchantStore store, Language language);

	ProductOptionValue getById(MerchantStore store, Long id);

	List<ProductOptionValue> getByName(MerchantStore store, String name,
			Language language);

	List<ProductOptionValue> listByStoreNoReadOnly(MerchantStore store,
			Language language);

	ProductOptionValue getByCode(MerchantStore store, String optionValueCode);

}
