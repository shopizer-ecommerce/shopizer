package com.salesmanager.core.business.repositories.catalog.product.instance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.instance.ProductInstance;

public interface ProductInstanceRepository extends JpaRepository<ProductInstance, Long> {
	

	//@Query("select p from ProductInstance p left join fetch c.parent cp left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm left join fetch c.categories where c.id = ?1")
	//Optional<ProductInstance> findById(Long id, MerchantStore store, Language language);

	//Optional<ProductInstance> getByProductId(long productId, Language language, Locale locale);


	
	//Optional<ProductInstance> getByCode(String sku, MerchantStore merchant);
	
	
	@Query("select p from ProductInstance p join fetch p.product pr "
			+ "left join fetch p.variant pv "
			+ "left join fetch pv.productOption pvpo "
			+ "left join fetch pv.productOptionValue pvpov "
			+ "left join fetch pvpo.descriptions pvpod "
			+ "left join fetch pvpov.descriptions pvpovd "
			
			+ "left join fetch p.variantValue pvv "
			+ "left join fetch pvv.productOption pvvpo "
			+ "left join fetch pvv.productOptionValue pvvpov "
			+ "left join fetch pvvpo.descriptions povvpod "
			+ "left join fetch pvpov.descriptions povvpovd "			
			
			+ "left join fetch pv.merchantStore pvm "
			+ "where pv.id = ?1 and pvm.id = ?2")
	Optional<ProductInstance> findById(Long id, Integer storeId);
	
	
	
	@Query("select p from ProductInstance p join fetch p.product pr "
			+ "left join fetch p.variant pv "
			+ "left join fetch pv.productOption pvpo "
			+ "left join fetch pv.productOptionValue pvpov "
			+ "left join fetch pvpo.descriptions pvpod "
			+ "left join fetch pvpov.descriptions pvpovd "
			
			+ "left join fetch p.variantValue pvv "
			+ "left join fetch pvv.productOption pvvpo "
			+ "left join fetch pvv.productOptionValue pvvpov "
			+ "left join fetch pvvpo.descriptions povvpod "
			+ "left join fetch pvpov.descriptions povvpovd "			
			
			+ "left join fetch pv.merchantStore pvm "
			+ "where pvpod.language.id = ?3 "
			+ "and pvpovd.language.id = ?3 "
			+ "and povvpod.language.id = ?3 "
			+ "and povvpovd.language.id = ?3 "
			+ "and pv.id = ?1 and pvm.id = ?2")
	Optional<ProductInstance> findBySku(String code, Integer storeId, Integer languageId);
	
	
	/*
	 * @Query("select p from ProductAttribute p join fetch p.product pr left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch po.merchantStore pom where pom.id = ?1 and po.id = ?2"
	 * ) List<ProductAttribute> findByOptionId(Integer storeId, Long id);
	 * 
	 * @Query("select distinct p from ProductAttribute p join fetch p.product pr left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch po.merchantStore pom where pom.id = ?1 and po.id = ?2"
	 * ) List<ProductAttribute> findByOptionValueId(Integer storeId, Long id);
	 * 
	 * @Query("select distinct p from ProductAttribute p join fetch p.product pr left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch pov.merchantStore povm where povm.id = ?1 and pr.id = ?2 and p.id in ?3"
	 * ) List<ProductAttribute> findByAttributeIds(Integer storeId, Long productId,
	 * List<Long> ids);
	 * 
	 * @Query("select distinct p from ProductAttribute p join fetch p.product pr left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch po.merchantStore pom where pom.id = ?1"
	 * ) List<ProductAttribute> findByProductId(Integer storeId, Long productId);
	 * 
	 * @Query(
	 * value="select distinct p from ProductAttribute p join fetch p.product pr left join fetch pr.categories prc left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch po.merchantStore pom where pom.id = ?1 and prc.id IN (select c.id from Category c where c.lineage like ?2% and povd.language.id = ?3)"
	 * ) List<ProductAttribute> findOptionsByCategoryLineage(Integer storeId, String
	 * lineage, Integer languageId);
	 */
}
