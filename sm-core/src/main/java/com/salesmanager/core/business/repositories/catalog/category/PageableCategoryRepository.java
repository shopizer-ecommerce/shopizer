package com.salesmanager.core.business.repositories.catalog.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;

public interface PageableCategoryRepository extends PagingAndSortingRepository<Manufacturer, Long> {
  
  //@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=?1 and cdl.id=?2 and (?3 is null or cd.name like %?3%) order by c.lineage, c.sortOrder asc")
  //Page<Category> listByStore(Integer storeId, Integer languageId, String name, Pageable pageable);

}
