package com.salesmanager.core.business.services.catalog.product.instance;

import java.util.Locale;
import java.util.Optional;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface ProductInstanceService extends SalesManagerEntityService<Long, ProductInstance> {
	
	Optional<ProductInstance> retrieveById(Long id);

	Optional<ProductInstance> getByLocale(long productId, Language language, Locale locale) throws ServiceException;

	/**
	 * Get a product by sku (code) field  and the language
	 * @param productCode
	 * @param language
	 * @return
	 */
	Optional<ProductInstance> getByCode(String productCode, Language language);
	
	Optional<ProductInstance> getByCode(String productCode, MerchantStore merchant);


}
