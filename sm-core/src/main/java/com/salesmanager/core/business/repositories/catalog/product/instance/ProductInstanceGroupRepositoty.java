package com.salesmanager.core.business.repositories.catalog.product.instance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.instance.ProductInstanceGroup;

public interface ProductInstanceGroupRepositoty extends JpaRepository<ProductInstanceGroup, Long> {

	
	@Query("select p from ProductInstanceGroup p"
			+ " join fetch p.productInstances pp"
			+ " left join fetch p.images ppi"
			+ " left join fetch ppi.descriptions ppid "
			+ " where p.id = ?1")
	ProductInstanceGroup findOne(Long id);

	
	@Query("select p from ProductInstanceGroup p "
			+ "join fetch p.productInstances pp "
			+ "left join fetch p.images ppi "
			+ "left join fetch ppi.descriptions ppid "
			+ "join fetch pp.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where pp.id = ?1 and pppm.code = ?2")
	List<ProductInstanceGroup> finByProductInstance(Long productInstanceId, String storeCode);
	

}
