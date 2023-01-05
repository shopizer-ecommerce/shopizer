package com.salesmanager.core.business.repositories.catalog.product.attribute;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;

public interface PageableProductAttributeRepository extends PagingAndSortingRepository<Category, Long> {

	@Query(value = "select distinct p from ProductAttribute p "
			+ "join fetch p.product pr "
			+ "left join fetch p.productOption po "
			+ "left join fetch p.productOptionValue pov "
			+ "left join fetch po.descriptions pod "
			+ "left join fetch pov.descriptions povd "
			+ "left join fetch po.merchantStore pom "
			+ "where pom.id = ?1 and pr.id = ?2 and povd.language.id = ?3",
      countQuery = "select  count(p) "
      		+ "from ProductAttribute p "
      		+ "join p.product pr "
      		+ "join pr.merchantStore pm "
      		+ "where pm.id = ?1 and pr.id = ?2")
	Page<ProductAttribute> findByProductId(Integer storeId, Long productId, Integer languageId, Pageable pageable);
	
	@Query(value = "select distinct p from ProductAttribute p "
			+ "join fetch p.product pr "
			+ "left join fetch p.productOption po "
			+ "left join fetch p.productOptionValue pov "
			+ "left join fetch po.descriptions pod "
			+ "left join fetch pov.descriptions povd "
			+ "left join fetch po.merchantStore pom "
			+ "where pom.id = ?1 and pr.id = ?2",
      countQuery = "select  count(p) "
      		+ "from ProductAttribute p "
      		+ "join p.product pr "
      		+ "join pr.merchantStore pm "
      		+ "where pm.id = ?1 and pr.id = ?2")
	Page<ProductAttribute> findByProductId(Integer storeId, Long productId, Pageable pageable);

}
