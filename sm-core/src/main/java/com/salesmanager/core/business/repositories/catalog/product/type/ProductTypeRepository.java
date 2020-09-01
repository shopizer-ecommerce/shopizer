package com.salesmanager.core.business.repositories.catalog.product.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.type.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
	
	ProductType findByCode(String code);

	@Query(value = "select p from ProductType p left join fetch p.descriptions pd left join fetch p.merchantStore pm where p.code=?1 and pm.id=?2")
	ProductType findByCode(String code, Integer store);
	
	@Query(value = "select p from ProductType p left join fetch p.descriptions pd left join fetch p.merchantStore pm where p.id=?1 and pm.id=?2")
	ProductType findById(Long id, Integer store, int language);

}
