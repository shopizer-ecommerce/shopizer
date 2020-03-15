package com.salesmanager.core.business.repositories.catalog.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.catalog.CatalogEntry;

public interface PageableCatalogEntryRepository extends PagingAndSortingRepository<CatalogEntry, Long> {

	
	  @Query(value = "select distinct c from CatalogEntry c join fetch c.product cp "
	  		+ "join fetch c.category cc "
	  		+ "join fetch c.catalog cl "
	  		+ "join fetch cl.merchantStore clm "
	  		+ "left join fetch cp.descriptions cpd "
	  		+ "left join fetch cc.descriptions ccd "
	  		+ "where cl.id=?1 and "
	  		+ "clm.id=?2 and "
	  		+ "cpd.language.id=?3 and (?4 is null or cpd.name like %?4%)",
		      countQuery = "select  count(c) from CatalogEntry c join c.product cp join c.category cc join c.catalog cl join cl.merchantStore clm join cp.descriptions cpd where cl.id=?1 and clm.id=?2 and cpd.language.id=?3 and (?4 is null or cpd.name like %?4%)")
		  Page<CatalogEntry> listByCatalog(Long catalogId, Integer storeId, Integer languageId, String name, Pageable pageable);

	
}
