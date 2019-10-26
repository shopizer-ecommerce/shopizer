package com.salesmanager.core.business.repositories.catalog.product.attribute;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;

public interface PageableProductOptionRepository extends PagingAndSortingRepository<ProductOption, Long> {

	@Query(value = "select distinct p from ProductOption p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = ?1 and pd.language.id = ?2 and (?3 is null or pd.name like %?3%)",
	    countQuery = "select count(p) from ProductOption p join p.merchantStore pm left join p.descriptions pd where pm.id = ?1 and pd.language.id = ?2 and (?3 is null or pd.name like %?3%)")
	Page<ProductOption> listOptions(int merchantStoreId, int languageId, String name, Pageable pageable);


}
