package com.salesmanager.core.business.repositories.catalog.product.type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.type.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

	@Query(value = "select p from ProductType p join fetch p.merchantStore pm where p.code=?1")
	ProductType findByCode(String code);

	@Query(value = "select p from ProductType p left join fetch p.descriptions pd join fetch p.merchantStore pm where p.code=?1 and pm.id=?2")
	ProductType findByCode(String code, Integer store);
	
	@Query(value = "select p from ProductType p left join fetch p.descriptions pd join fetch p.merchantStore pm where p.id=?1 and pm.id=?2")
	ProductType findById(Long id, Integer store, int language);
	
	@Query(value = "select p from ProductType p left join fetch p.descriptions pd join fetch p.merchantStore pm where p.id in ?1 and pm.id=?2")
	List<ProductType> findByIds(List<Long> id, Integer store, int language);

}
