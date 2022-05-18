package com.salesmanager.core.business.services.catalog.product.instance;

import java.util.List;
import java.util.Optional;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceGroup;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface ProductInstanceGroupService extends SalesManagerEntityService<Long, ProductInstanceGroup> {

	
	Optional<ProductInstanceGroup> getById(Long id, MerchantStore store);
	
	Optional<ProductInstanceGroup> getByProductInstance(Long productInstanceId, MerchantStore store, Language language);
	
	List<ProductInstanceGroup> getByProductId(Product product, MerchantStore store);
	
	void saveOrUpdate(ProductInstanceGroup entity) throws ServiceException;
	
	
}
