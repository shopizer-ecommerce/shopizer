package com.salesmanager.core.business.repositories.catalog.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.salesmanager.core.model.catalog.category.Category;

public interface PageableCategoryRepository extends PagingAndSortingRepository<Category, Long> {
  
	
<<<<<<< HEAD
  @Query(value = "select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=?1 and cdl.id=?2 and (cd.name like %?3% or ?3 is null) order by c.lineage, c.sortOrder asc",
=======
  @Query(value = "select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=?1 and cdl.id=?2 and (cd.name like %?3% or ?3 is null) order by c.depth, c.sortOrder asc",
>>>>>>> a2316b73a7dd32791c9a9786e4f5dc6ae89a4743
      countQuery = "select  count(c) from Category c join c.descriptions cd join c.merchantStore cm where cm.id=?1 and cd.language.id=?2 and (cd.name like %?3% or ?3 is null)")
  Page<Category> listByStore(Integer storeId, Integer languageId, String name, Pageable pageable);

  

}
