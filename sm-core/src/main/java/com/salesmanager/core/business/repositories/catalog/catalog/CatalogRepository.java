package com.salesmanager.core.business.repositories.catalog.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.catalog.Catalog;

import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
	
	
	@Query("select c from Catalog c "
			+ "join c.merchantStore cm "
			+ "left join fetch c.entry ce "
			//+ "left join fetch ce.product cep "
			+ "left join fetch ce.category cec where c.id=?1 and cm.id = ?2")
	Optional<Catalog> findById(Long catalogId, Integer merchantId);
	
	@Query("select c from Catalog c "
			+ "join c.merchantStore cm "
			+ "left join fetch c.entry ce "
			//+ "left join fetch ce.product cep "
			+ "left join fetch ce.category cec where c.code=?1 and cm.id = ?2")
	Optional<Catalog> findByCode(String code, Integer merchantId);
	
	@Query("SELECT COUNT(c) > 0 FROM Catalog c "
			+ "join c.merchantStore cm  "
			+ "WHERE c.code = ?1 and cm.id = ?2")
	boolean existsByCode(String code, Integer merchantId);

}
