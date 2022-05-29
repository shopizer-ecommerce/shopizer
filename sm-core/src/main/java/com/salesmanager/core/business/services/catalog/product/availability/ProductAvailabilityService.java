package com.salesmanager.core.business.services.catalog.product.availability;

import org.springframework.data.domain.Page;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.merchant.MerchantStore;

import java.util.List;
import java.util.Optional;

public interface ProductAvailabilityService extends
		SalesManagerEntityService<Long, ProductAvailability> {

	void saveOrUpdate(ProductAvailability availability) throws ServiceException;
	
	Page<ProductAvailability> listByProduct(Product product, MerchantStore store, String child, int page, int count);

	/**
	 * Get by product definition
	 * Check the inventory of a product for a specific store
	 * @param product
	 * @param store
	 * @return
	 */
	Optional<ProductAvailability> getByStore(Product product, MerchantStore store);
	
	
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
	 * All availability by product / product instance sku and store
	 * @param sku
	 * @param store
	 * @return
	 */
	List<ProductAvailability> getBySku(String sku, MerchantStore store);

	Optional<ProductAvailability> getById(Long availabilityId, MerchantStore store);

	Optional<ProductAvailability> getByInventoryId(Long productId, Long availabilityId, MerchantStore store);

	ProductAvailability getByOwner(Product product, String owner) throws ServiceException;
	
	int count(Product product);

}
