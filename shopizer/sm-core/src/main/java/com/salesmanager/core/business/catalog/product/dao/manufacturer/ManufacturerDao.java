package com.salesmanager.core.business.catalog.product.dao.manufacturer;

import java.util.List;

import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

public interface ManufacturerDao extends SalesManagerEntityDao<Long, Manufacturer> {

	List<Manufacturer> listByStore(MerchantStore store, Language language);

	List<Manufacturer> listByStore(MerchantStore store);
	
	int getCountManufAttachedProducts(  Manufacturer manufacturer  );

	List<Manufacturer> listByProductsByCategoriesId(MerchantStore store,
			List<Long> ids, Language language);
	
	Manufacturer getByCode(MerchantStore store, String code);

}
