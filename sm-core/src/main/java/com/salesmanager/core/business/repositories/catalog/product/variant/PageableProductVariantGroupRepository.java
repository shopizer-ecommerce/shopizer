package com.salesmanager.core.business.repositories.catalog.product.variant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.product.variant.ProductVariantGroup;

public interface PageableProductVariantGroupRepository extends PagingAndSortingRepository<ProductVariantGroup, Long> {

	
	
	
	@Query(value = "select p from ProductVariantGroup p " 
			+ "join fetch p.productVariants pi " 
			+ "join fetch pi.product pip "
			+ "left join fetch p.images pim "
			+ "left join fetch pim.descriptions pimd "
			+ "left join fetch p.merchantStore pm "
			+ "where pip.id = ?2 and pm.id = ?1",
			countQuery = "select p from ProductVariantGroup p " + 
			"join fetch p.productVariants pi "
					+ "left join fetch p.merchantStore pm join fetch pi.product pip " + 
			"where pip.id = ?2 and pm.id = ?1")
	Page<ProductVariantGroup> findByProductId(Integer storeId, Long productId, Pageable pageable);
	
}
