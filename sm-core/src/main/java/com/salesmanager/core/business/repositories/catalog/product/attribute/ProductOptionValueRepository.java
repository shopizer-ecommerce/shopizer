package com.salesmanager.core.business.repositories.catalog.product.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;

public interface ProductOptionValueRepository extends JpaRepository<ProductOptionValue, Long> {

	@Query("select p from ProductOptionValue p join fetch p.merchantStore pm left join fetch p.descriptions pd where p.id = ?1")
	ProductOptionValue findOne(Long id);
	
	@Query("select p from ProductOptionValue p join fetch p.merchantStore pm left join fetch p.descriptions pd where p.id = ?2  and pm.id = ?1")
	ProductOptionValue findOne(Integer storeId, Long id);
	
	@Query("select distinct p from ProductOptionValue p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = ?1 and pd.language.id = ?2")
	List<ProductOptionValue> findByStoreId(Integer storeId, Integer languageId);
	
	@Query("select p from ProductOptionValue p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = ?1 and p.code = ?2")
	ProductOptionValue findByCode(Integer storeId, String optionValueCode);
	
	@Query("select p from ProductOptionValue p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = ?1 and (pd.name like %?2% or p.code like %?2%) and pd.language.id = ?3")
	List<ProductOptionValue> findByName(Integer storeId, String name, Integer languageId);

	@Query("select distinct p from ProductOptionValue p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = ?1 and pd.language.id = ?2 and p.productOptionDisplayOnly = ?3")
	List<ProductOptionValue> findByReadOnly(Integer storeId, Integer languageId, boolean readOnly);

}
