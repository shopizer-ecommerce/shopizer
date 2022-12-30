package com.salesmanager.core.business.services.catalog.product.availability;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.merchant.MerchantStore;

public interface ProductAvailabilityService extends
		SalesManagerEntityService<Long, ProductAvailability> {

	ProductAvailability saveOrUpdate(ProductAvailability availability) throws ServiceException;
	
	Page<ProductAvailability> listByProduct(Long productId, MerchantStore store, int page, int count);
	
	/**
	 * Get by product sku and store
	 * @param sku
	 * @param store
	 * @return
	 */
	Page<ProductAvailability> getBySku(String sku, MerchantStore store, int page, int count);
	
	
	/**
	 * Get by sku
	 * @param sku
	 * @return
	 */
	Page<ProductAvailability> getBySku(String sku, int page, int count);
	
	/**
	 * All availability by product / product variant sku and store
	 * @param sku
	 * @param store
	 * @return
	 */
	List<ProductAvailability> getBySku(String sku, MerchantStore store);

	Optional<ProductAvailability> getById(Long availabilityId, MerchantStore store);


}
