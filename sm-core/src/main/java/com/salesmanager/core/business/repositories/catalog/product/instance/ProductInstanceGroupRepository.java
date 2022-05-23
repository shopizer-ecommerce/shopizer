package com.salesmanager.core.business.repositories.catalog.product.instance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.instance.ProductInstanceGroup;

public interface ProductInstanceGroupRepository extends JpaRepository<ProductInstanceGroup, Long> {

	
	@Query("select distinct p from ProductInstanceGroup p"
			+ " left join fetch p.productInstances pp"
			+ " left join fetch p.images ppi"
			+ " left join fetch ppi.descriptions ppid "
			+ " where p.id = ?1 and p.merchantStore.code = ?2")
	Optional<ProductInstanceGroup> findOne(Long id, String storeCode);
	
	
	@Query("select distinct p from ProductInstanceGroup p "
			+ "left join fetch p.productInstances pp "
			+ "left join fetch p.images ppi "
			+ "left join fetch ppi.descriptions ppid "
			+ "join fetch pp.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where pp.id = ?1 and p.merchantStore.code = ?2")
	Optional<ProductInstanceGroup> finByProductInstance(Long productInstanceId, String storeCode);
	
	@Query("select distinct p from ProductInstanceGroup p "
			+ "left join fetch p.productInstances pp "
			+ "left join fetch p.images ppi "
			+ "left join fetch ppi.descriptions ppid "
			+ "join fetch pp.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where ppp.id = ?1 and p.merchantStore.code = ?2")
	List<ProductInstanceGroup> finByProduct(Long productId, String storeCode);
	

}
