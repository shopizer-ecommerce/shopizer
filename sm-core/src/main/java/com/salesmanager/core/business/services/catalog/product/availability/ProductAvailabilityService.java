package com.salesmanager.core.business.services.catalog.product.availability;

import org.springframework.data.domain.Page;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.merchant.MerchantStore;

import java.util.Optional;

public interface ProductAvailabilityService extends
		SalesManagerEntityService<Long, ProductAvailability> {

	void saveOrUpdate(ProductAvailability availability) throws ServiceException;
	
	Page<ProductAvailability> listByProduct(Product product, MerchantStore store, String child, int page, int count);

	Optional<ProductAvailability> getByStore(Product product, MerchantStore store);

	Optional<ProductAvailability> getById(Long availabilityId, MerchantStore store);

	Optional<ProductAvailability> getByInventoryId(Long productId, Long availabilityId, MerchantStore store);

	ProductAvailability getByOwner(Product product, String owner) throws ServiceException;
	
	int count(Product product);

}
