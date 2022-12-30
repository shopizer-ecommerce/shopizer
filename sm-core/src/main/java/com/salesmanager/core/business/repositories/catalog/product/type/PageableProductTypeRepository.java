package com.salesmanager.core.business.repositories.catalog.product.type;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.product.type.ProductType;

public interface PageableProductTypeRepository extends PagingAndSortingRepository<ProductType, Long> {
	
	
	@Query(value = "select distinct p from ProductType p left join fetch p.descriptions pd left join fetch p.merchantStore pm where pm.id=?1",
		 countQuery = "select count(p) from ProductType p left join p.merchantStore pm where pm.id = ?1")
	Page<ProductType> listByStore(Integer storeId, Pageable pageable);

}
