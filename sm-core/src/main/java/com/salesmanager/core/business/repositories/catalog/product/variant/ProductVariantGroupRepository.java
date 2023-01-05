package com.salesmanager.core.business.repositories.catalog.product.variant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.variant.ProductVariantGroup;

public interface ProductVariantGroupRepository extends JpaRepository<ProductVariantGroup, Long> {

	
	@Query("select distinct p from ProductVariantGroup p"
			+ " left join fetch p.productVariants pp"
			+ " left join fetch p.images ppi"
			+ " left join fetch ppi.descriptions ppid "
			+ " where p.id = ?1 and p.merchantStore.code = ?2")
	Optional<ProductVariantGroup> findOne(Long id, String storeCode);
	
	
	@Query("select distinct p from ProductVariantGroup p "
			+ "left join fetch p.productVariants pp "
			+ "left join fetch p.images ppi "
			+ "left join fetch ppi.descriptions ppid "
			+ "join fetch pp.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where pp.id = ?1 and p.merchantStore.code = ?2")
	Optional<ProductVariantGroup> finByProductVariant(Long productVariantId, String storeCode);
	
	@Query("select distinct p from ProductVariantGroup p "
			+ "left join fetch p.productVariants pp "
			+ "left join fetch p.images ppi "
			+ "left join fetch ppi.descriptions ppid "
			+ "join fetch pp.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where ppp.id = ?1 and p.merchantStore.code = ?2")
	List<ProductVariantGroup> finByProduct(Long productId, String storeCode);
	

}
