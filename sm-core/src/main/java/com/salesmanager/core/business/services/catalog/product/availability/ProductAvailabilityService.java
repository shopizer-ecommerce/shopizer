package com.salesmanager.core.business.services.catalog.product.availability;

import org.springframework.data.domain.Page;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.merchant.MerchantStore;

public interface ProductAvailabilityService extends
		SalesManagerEntityService<Long, ProductAvailability> {

	void saveOrUpdate(ProductAvailability availability) throws ServiceException;
	
	Page<ProductAvailability> listByProduct(Product product, MerchantStore store, String child, int page, int count) throws ServiceException;
	
	ProductAvailability getByStore(Product product, MerchantStore store) throws ServiceException;
	
	ProductAvailability getById(Long availabilityId, MerchantStore store) throws ServiceException;
	
	ProductAvailability getByInventoryId(Long productId, Long availabilityId, MerchantStore store) throws ServiceException;

	ProductAvailability getByOwner(Product product, String owner) throws ServiceException;
	
	int count(Product product);

}
