package com.salesmanager.core.business.repositories.catalog.product.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {

	@Query("select p from ProductAttribute p join fetch p.product pr left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch po.merchantStore where p.id = ?1")
	ProductAttribute findOne(Long id);
	
	@Query("select p from ProductAttribute p join fetch p.product pr left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch po.merchantStore pom where pom.id = ?1 and po.id = ?2")
	List<ProductAttribute> findByOptionId(Integer storeId, Long id);
	
	@Query("select distinct p from ProductAttribute p join fetch p.product pr left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch po.merchantStore pom where pom.id = ?1 and po.id = ?2")
	List<ProductAttribute> findByOptionValueId(Integer storeId, Long id);
	
	@Query("select distinct p from ProductAttribute p "
			+ "join fetch p.product pr "
			+ "left join fetch p.productOption po "
			+ "left join fetch p.productOptionValue pov "
			+ "left join fetch po.descriptions pod "
			+ "left join fetch pov.descriptions povd "
			+ "left join fetch pov.merchantStore povm "
			+ "where povm.id = ?1 and pr.id = ?2 and p.id in ?3")
	List<ProductAttribute> findByAttributeIds(Integer storeId, Long productId, List<Long> ids);

	@Query("select distinct p from ProductAttribute p join fetch p.product pr left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch po.merchantStore pom where pom.id = ?1")
	List<ProductAttribute> findByProductId(Integer storeId, Long productId);
	
	@Query(value="select distinct p from ProductAttribute p join fetch p.product pr left join fetch pr.categories prc left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch po.merchantStore pom where pom.id = ?1 and prc.id IN (select c.id from Category c where c.lineage like ?2% and povd.language.id = ?3)")
	List<ProductAttribute> findOptionsByCategoryLineage(Integer storeId, String lineage, Integer languageId);
}
