package com.salesmanager.core.business.repositories.catalog.product.type;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.product.type.ProductType;

public interface PageableProductTypeRepository extends PagingAndSortingRepository<ProductType, Long> {
	
	//Page<ProductType> listByStore(Integer storeId, Pageable pageable);

}
