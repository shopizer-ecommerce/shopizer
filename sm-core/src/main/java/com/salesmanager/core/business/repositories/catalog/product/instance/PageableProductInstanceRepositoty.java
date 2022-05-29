package com.salesmanager.core.business.repositories.catalog.product.instance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.product.instance.ProductInstance;

public interface PageableProductInstanceRepositoty extends PagingAndSortingRepository<ProductInstance, Long> {


	
	@Query(value = "select p from ProductInstance p " 
			+ "join fetch p.product pr " 
			+ "left join fetch p.variant pv "
			+ "left join fetch pv.productOption pvpo " 
			+ "left join fetch pv.productOptionValue pvpov "
			+ "left join fetch pvpo.descriptions pvpod " 
			+ "left join fetch pvpov.descriptions pvpovd "

			+ "left join fetch p.variantValue pvv " 
			+ "left join fetch pvv.productOption pvvpo "
			+ "left join fetch pvv.productOptionValue pvvpov " 
			+ "left join fetch pvvpo.descriptions povvpod "
			+ "left join fetch pvpov.descriptions pvpovd "
			+ "left join fetch p.productInstanceGroup pig "
			+ "left join fetch pig.images pigi "
			+ "left join fetch pigi.descriptions pigid "

			+ "left join fetch pr.merchantStore prm " 
			+ "where pr.id = ?2 and prm.id = ?1",
			countQuery = "select p from ProductInstance p "
			+ "join fetch p.product pr "
					+ "left join fetch pr.merchantStore prm "
					+ "where pr.id = ?2 and prm.id = ?1")
	Page<ProductInstance> findByProductId(Integer storeId, Long productId, Pageable pageable);


}
