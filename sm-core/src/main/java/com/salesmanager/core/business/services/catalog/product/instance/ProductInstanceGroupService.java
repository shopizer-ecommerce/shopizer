package com.salesmanager.core.business.services.catalog.product.instance;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceGroup;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface ProductInstanceGroupService extends SalesManagerEntityService<Long, ProductInstanceGroup> {

	
	Optional<ProductInstanceGroup> getById(Long id, MerchantStore store);
	
	Optional<ProductInstanceGroup> getByProductInstance(Long productInstanceId, MerchantStore store, Language language);

	Page<ProductInstanceGroup> getByProductId(MerchantStore store, Long productId, Language language, int page, int count);

	void saveOrUpdate(ProductInstanceGroup entity) throws ServiceException;
	
	
}
