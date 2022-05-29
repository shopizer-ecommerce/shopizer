package com.salesmanager.core.business.services.catalog.product.availability;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.product.availability.PageableProductAvailabilityRepository;
import com.salesmanager.core.business.repositories.catalog.product.availability.ProductAvailabilityRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.merchant.MerchantStore;

/**
 * Availability -> Inventory
 * 
 * @author carlsamson
 *
 */

@Service("productAvailabilityService")
public class ProductAvailabilityServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductAvailability>
		implements ProductAvailabilityService {

	private ProductAvailabilityRepository productAvailabilityRepository;

	@Inject
	private PageableProductAvailabilityRepository pageableProductAvailabilityRepository;

	@Inject
	public ProductAvailabilityServiceImpl(ProductAvailabilityRepository productAvailabilityRepository) {
		super(productAvailabilityRepository);
		this.productAvailabilityRepository = productAvailabilityRepository;
	}

	@Override
	public void saveOrUpdate(ProductAvailability availability) throws ServiceException {
		if (isPositive(availability.getId())) {
			update(availability);
		} else {
			create(availability);
		}
	}

	private boolean isPositive(Long id) {
		return Objects.nonNull(id) && id > 0;
	}

	/**
	 * Returns inventory of a child store
	 */
	@Override
	public Optional<ProductAvailability> getByStore(Product product, MerchantStore store) {
		Validate.notNull(product, "Product cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		return Optional.ofNullable(productAvailabilityRepository.getByStore(product.getId(), store.getCode()));
	}

	@Override
	public ProductAvailability getByOwner(Product product, String owner) throws ServiceException {
		throw new ServiceException("Not implemented");
	}

	@Override
	public Page<ProductAvailability> listByProduct(Product product, MerchantStore store, String child, int page,
			int count) {
		Validate.notNull(product, "Product cannot be null");
		Validate.notNull(store, "MercantStore cannot be null");
		Pageable pageRequest = PageRequest.of(page, count);
		return pageableProductAvailabilityRepository.listByStore(product.getId(), store.getId(), child, pageRequest);
	}

	@Override
	public int count(Product product) {
		return productAvailabilityRepository.count(product.getId());
	}

	@Override
	public Optional<ProductAvailability> getById(Long availabilityId, MerchantStore store) {
		Validate.notNull(store, "Merchant must not be null");
		return Optional.ofNullable(productAvailabilityRepository.getById(availabilityId));
	}

	@Override
	public Optional<ProductAvailability> getByInventoryId(Long productId, Long availabilityId, MerchantStore store) {
		Validate.notNull(store, "Merchant must not be null");
		return Optional.ofNullable(productAvailabilityRepository.getByStore(productId, availabilityId));
	}

	@Override
	public Page<ProductAvailability> getBySku(String sku, MerchantStore store, int page, int count) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Pageable pageRequest = PageRequest.of(page, count);
		return pageableProductAvailabilityRepository.getBySku(sku, store.getCode(), pageRequest);
	}

	@Override
	public Page<ProductAvailability> getBySku(String sku, int page, int count) {
		Pageable pageRequest = PageRequest.of(page, count);
		return pageableProductAvailabilityRepository.getBySku(sku, pageRequest);
	}

	@Override
	public List<ProductAvailability> getBySku(String sku, MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		return productAvailabilityRepository.getBySku(sku, store.getCode());
	}

}
