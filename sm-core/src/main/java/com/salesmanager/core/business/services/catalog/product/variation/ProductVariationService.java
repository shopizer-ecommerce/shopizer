package com.salesmanager.core.business.services.catalog.product.variation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.variation.ProductVariation;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface ProductVariationService extends SalesManagerEntityService<Long, ProductVariation> {
	

	void saveOrUpdate(ProductVariation entity) throws ServiceException;

	Optional<ProductVariation> getById(MerchantStore store, Long id, Language lang);
	
	Optional<ProductVariation> getById(MerchantStore store, Long id);
	
	Optional<ProductVariation> getByCode(MerchantStore store, String code);
	
	Page<ProductVariation> getByMerchant(MerchantStore store, Language language, String code, int page, int count);
	
	List<ProductVariation> getByIds(List<Long> ids, MerchantStore store);

}
