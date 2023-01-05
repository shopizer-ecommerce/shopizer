package com.salesmanager.core.business.repositories.catalog.product.variation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.variation.ProductVariation;

public interface ProductVariationRepository extends JpaRepository<ProductVariation, Long> {

	@Query("select distinct p from ProductVariation p "
			+ "join fetch p.merchantStore pm "
			+ "left join fetch p.productOption po "
			+ "left join fetch po.descriptions pod "
			+ "left join fetch p.productOptionValue pv "
			+ "left join fetch pv.descriptions pvd where pm.id = ?1 and p.id = ?2 and pod.language.id = ?3")
	Optional<ProductVariation> findOne(Integer storeId, Long id, Integer language);
	
	@Query("select distinct p from ProductVariation p "
			+ "join fetch p.merchantStore pm "
			+ "left join fetch p.productOption po "
			+ "left join fetch po.descriptions pod "
			+ "left join fetch p.productOptionValue pv "
			+ "left join fetch pv.descriptions pvd where pm.id = ?1 and p.id = ?2")
	Optional<ProductVariation> findOne(Integer storeId, Long id);

	@Query("select distinct p from ProductVariation p join fetch p.merchantStore pm left join fetch p.productOption po left join fetch po.descriptions pod left join fetch p.productOptionValue pv left join fetch pv.descriptions pvd where p.code = ?1 and pm.id = ?2")
	Optional<ProductVariation> findByCode(String code, Integer storeId);
	
	@Query("select distinct p from ProductVariation p "
			+ "join fetch p.merchantStore pm "
			+ "left join fetch p.productOption po "
			+ "left join fetch po.descriptions pod "
			+ "left join fetch p.productOptionValue pv "
			+ "left join fetch pv.descriptions pvd where pm.id = ?1 and p.id in (?2)")
	List<ProductVariation> findByIds(Integer storeId, List<Long> ids);

}
