package com.salesmanager.core.business.repositories.catalog.product.manufacturer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

	@Query("select count(distinct p) from Product as p where p.manufacturer.id=?1")
	Long countByProduct(Long manufacturerId);
	
	@Query("select m from Manufacturer m left join fetch m.descriptions md join fetch m.merchantStore ms where ms.id=?1 and md.language.id=?2")
	List<Manufacturer> findByStoreAndLanguage(Integer storeId, Integer languageId);
	
	@Query("select m from Manufacturer m left join fetch  m.descriptions md join fetch m.merchantStore ms where m.id=?1")
	Manufacturer findOne(Long id);
	
	@Query("select m from Manufacturer m left join fetch m.descriptions md join fetch m.merchantStore ms where ms.id=?1")
	List<Manufacturer> findByStore(Integer storeId);
	
    @Query("select m from Manufacturer m join fetch m.descriptions md join fetch m.merchantStore ms join fetch md.language mdl where ms.id=?1 and mdl.id=?2 and (?3 is null or md.name like %?3%)")
	//@Query("select m from Manufacturer m join fetch m.descriptions md join fetch m.merchantStore ms join fetch md.language mdl where ms.id=?1 and mdl.id=?2")
	//@Query("select m from Manufacturer m left join m.descriptions md join fetch m.merchantStore ms where ms.id=?1")
	List<Manufacturer> findByStore(Integer storeId, Integer languageId, String name);
	

	@Query("select distinct manufacturer from Product as p join p.manufacturer manufacturer join manufacturer.descriptions md join p.categories categs where categs.id in (?1) and md.language.id=?2")
	List<Manufacturer> findByCategoriesAndLanguage(List<Long> categoryIds, Integer languageId);
	
	@Query("select m from Manufacturer m left join m.descriptions md join fetch m.merchantStore ms where m.code=?1 and ms.id=?2")
	Manufacturer findByCodeAndMerchandStore(String code, Integer storeId);
	
	@Query("select count(distinct m) from Manufacturer as m where m.merchantStore.id=?1")
	int count(Integer storeId);
	
	@Query(value="select distinct manufacturer from Product as p "
			+ "join p.manufacturer manufacturer "
			+ "left join manufacturer.descriptions pmd "
			+ "join fetch manufacturer.merchantStore pms "
			+ "join p.categories pc "
			+ "where pms.id = ?1 "
			+ "and pc.id IN (select c.id from Category c where c.lineage like %?2% and pmd.language.id = ?3)")
	List<Manufacturer> findByProductInCategoryId(Integer storeId, String lineage, Integer languageId);
}
