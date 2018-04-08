package com.salesmanager.core.business.repositories.catalog.product.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.attribute.ProductOption;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

	@Query("select p from ProductOption p join fetch p.merchantStore pm left join fetch p.descriptions pd where p.id = ?1")
	ProductOption findOne(Long id);
	
	@Query("select p from ProductOption p join fetch p.merchantStore pm left join fetch p.descriptions pd where p.id = ?2 and pm.id = ?1")
	ProductOption findOne(Integer storeId, Long id);
	
	@Query("select distinct p from ProductOption p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = ?1 and pd.language.id = ?2")
	List<ProductOption> findByStoreId(Integer storeId, Integer languageId);
	
	@Query("select p from ProductOption p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = ?1 and pd.name like %?2% and pd.language.id = ?3")
	public List<ProductOption> findByName(Integer storeId, String name, Integer languageId);
	
	@Query("select p from ProductOption p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = ?1 and p.code = ?2")
	public ProductOption findByCode(Integer storeId, String optionCode);
	
	@Query("select distinct p from ProductOption p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = ?1 and p.code = ?2 and p.readOnly = ?3")
	public List<ProductOption> findByReadOnly(Integer storeId, Integer languageId, boolean readOnly);
	

}
