package com.salesmanager.core.business.repositories.catalog.product.instance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.product.instance.ProductInstanceGroup;

public interface PageableProductInstanceGroupRepository extends PagingAndSortingRepository<ProductInstanceGroup, Long> {

	
	
	
	@Query(value = "select p from ProductInstanceGroup p " 
			+ "join fetch p.productInstances pi " 
			+ "join fetch pi.product pip "
			+ "left join fetch p.images pim "
			+ "left join fetch pim.descriptions pimd "
			+ "left join fetch p.merchantStore pm "
			+ "where pip.id = ?2 and pm.id = ?1",
			countQuery = "select p from ProductInstanceGroup p " + 
			"join fetch p.productInstances pi "
					+ "left join fetch p.merchantStore pm join fetch pi.product pip " + 
			"where pip.id = ?2 and pm.id = ?1")
	Page<ProductInstanceGroup> findByProductId(Integer storeId, Long productId, Pageable pageable);
	
}
