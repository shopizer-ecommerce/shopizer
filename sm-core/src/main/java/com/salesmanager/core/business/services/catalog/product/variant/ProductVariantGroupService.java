package com.salesmanager.core.business.services.catalog.product.variant;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.variant.ProductVariantGroup;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface ProductVariantGroupService extends SalesManagerEntityService<Long, ProductVariantGroup> {

	
	Optional<ProductVariantGroup> getById(Long id, MerchantStore store);
	
	Optional<ProductVariantGroup> getByProductVariant(Long productVariantId, MerchantStore store, Language language);

	Page<ProductVariantGroup> getByProductId(MerchantStore store, Long productId, Language language, int page, int count);

	ProductVariantGroup saveOrUpdate(ProductVariantGroup entity) throws ServiceException;
	
	
}
