package com.salesmanager.core.business.repositories.catalog.product.availability;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;

public interface PageableProductAvailabilityRepository extends PagingAndSortingRepository<ProductAvailability, Long> {

	@Query(value = "select distinct p from ProductAvailability p " + "left join fetch p.merchantStore pm "
			+ "left join fetch p.prices pp " + "left join fetch pp.descriptions ppd " + "join fetch p.product ppr "
			+ "left join fetch ppr.merchantStore pprm " + "where ppr.id=?1 " + "and pprm.id=?2 "
			+ "and (?3 is null or pm.code like %?3%)", countQuery = "select  count(p) from ProductAvailability p "
					+ "join p.merchantStore pm " + "join p.prices pp " + "join pp.descriptions ppd "
					+ "join p.merchantStore pm " + "join p.product ppr " + "join ppr.merchantStore pprm "
					+ "where ppr.id=?1 " + "and pprm.id=?2 " + "and (?3 is null or pm.code like %?3%)")
	Page<ProductAvailability> listByStore(Long productId, Integer storeId, String child, Pageable pageable);

	@Query(value = "select distinct p from ProductAvailability p " + "left join fetch p.merchantStore pm "
			+ "left join fetch p.prices pp " + "left join fetch pp.descriptions ppd " + "join fetch p.product ppr "
			+ "left join fetch ppr.merchantStore pprm "
			+ "where pm.id=?1 ", countQuery = "select  count(p) from ProductAvailability p "
					+ "join p.merchantStore pm " + "where pm.id=?1 ")
	Page<ProductAvailability> listByStore(Integer storeId, Pageable pageable);

	@Query(value = "select distinct p from ProductAvailability p " + "left join fetch p.merchantStore pm "
			+ "left join fetch p.prices pp " 
			+ "left join fetch pp.descriptions ppd " 
			+ "join fetch p.product ppr "
			+ "join fetch ppr.merchantStore pprm "
			+ "left join fetch ppr.variants ppri "
			+ "left join fetch ppri.availabilities ppria "
			+ "left join fetch ppria.prices ppriap "
			+ "left join fetch ppriap.descriptions ppriapd "
			+ "where ppr.id=?1 and pm.code=?2", countQuery = "select  count(p) from ProductAvailability p "
					+ "join p.merchantStore pm " + "join p.product ppr left join ppr.variants ppri left join ppri.availabilities ppria " + "where ppr.id=?1 "
							+ "and pm.code=?2")
	Page<ProductAvailability> getByProductId(Long productId, String store, Pageable pageable);

	@Query(value = "select distinct p from ProductAvailability p " + "left join fetch p.merchantStore pm "
			+ "left join fetch p.prices pp " 
			+ "left join fetch pp.descriptions ppd " 
			+ "join fetch p.product ppr "
			+ "left join fetch p.productVariant ppi " 
			+ "where ppr.sku=?1 or ppi.sku=?1 "
			+ "and pm.code=?2", countQuery = "select  count(p) from ProductAvailability p " + "join p.merchantStore pm "
					+ "join p.product ppr " + "left join p.productVariant ppi " + "where ppr.sku=?1 or ppi.sku=?1 "
					+ "and pm.code=?2")
	Page<ProductAvailability> getBySku(String productCode, String store, Pageable pageable);

	@Query(value = "select distinct p from ProductAvailability p " 
			+ "left join fetch p.merchantStore pm "
			+ "left join fetch p.prices pp " 
			+ "left join fetch pp.descriptions ppd " + "join fetch p.product ppr "
			+ "left join fetch p.productVariant ppi "
			+ "where ppr.sku=?1 or ppi.sku=?1", countQuery = "select  count(p) from ProductAvailability p "
					+ "join p.merchantStore pm " + "join p.product ppr " + "left join p.productVariant ppi "
					+ "where ppr.sku=?1 or ppi.sku=?1 ")
	Page<ProductAvailability> getBySku(String productCode, Pageable pageable);

		
	
}
