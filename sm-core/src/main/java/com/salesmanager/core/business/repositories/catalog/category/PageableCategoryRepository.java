package com.salesmanager.core.business.repositories.catalog.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.salesmanager.core.model.catalog.category.Category;

public interface PageableCategoryRepository extends PagingAndSortingRepository<Category, Long> {
  
	
  @Query(value = "select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=?1 and cdl.id=?2 and (cd.name like %?3% or ?3 is null) order by c.lineage, c.sortOrder asc",
      countQuery = "select  count(c) from Category c join c.descriptions cd join c.merchantStore cm where cm.id=?1 and cd.language.id=?2 and (cd.name like %?3% or ?3 is null)")
  Page<Category> listByStore(Integer storeId, Integer languageId, String name, Pageable pageable);

  

}
