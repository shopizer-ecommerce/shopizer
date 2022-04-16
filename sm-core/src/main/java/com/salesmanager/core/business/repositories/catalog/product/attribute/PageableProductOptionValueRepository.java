package com.salesmanager.core.business.repositories.catalog.product.attribute;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;

public interface PageableProductOptionValueRepository extends PagingAndSortingRepository<ProductOptionValue, Long> {

	@Query(value = "select distinct p from ProductOptionValue p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = ?1 and (?2 is null or (pd.name like %?2% or p.code like %?2%))",
	    countQuery = "select count(p) from ProductOptionValue p join p.merchantStore pm left join p.descriptions pd where pm.id = ?1 and (?2 is null or (pd.name like %?2% or p.code like %?2%))")
	Page<ProductOptionValue> listOptionValues(int merchantStoreId, String name, Pageable pageable);


}
