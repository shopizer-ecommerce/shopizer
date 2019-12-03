package com.salesmanager.core.business.repositories.catalog.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.catalog.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
	
	
	@Query("select c from Catalog c join fetch c.merchantStore cm where c.id=?1 and cm.id = ?2")
	Catalog findById(Long catalogId, Integer merchantId);
	
	@Query("select c from Catalog c join fetch c.merchantStore cm where c.code=?1 and cm.id = ?2")
	Catalog findByCode(String code, Integer merchantId);
	
	@Query("SELECT COUNT(c) > 0 FROM Catalog c WHERE c.code = ?1")
	boolean existsByCode(String code);

}
