package com.salesmanager.core.business.repositories.catalog.product.instance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.instance.ProductInstance;

public interface ProductInstanceRepository extends JpaRepository<ProductInstance, Long> {
	

	
	
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
			+ "where p.id = ?1 and pvm.id = ?2")
	Optional<ProductInstance> findOne(Long id, Integer storeId);
	
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
			+ "where p.id in (?1) and pvm.id = ?2")
	List<ProductInstance> findByIds(List<Long> ids, Integer storeId);
	
	
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
			
			+ "left join fetch pr.merchantStore prm "
			+ "where p.id = ?1 and pr.id = ?2 and prm.id = ?3")
	Optional<ProductInstance> findById(Long id, Long productId, Integer storeId);
	
	
	
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
			
			+ "left join fetch pr.merchantStore prm "
			+ "where pvpod.language.id = ?4 "
			+ "and pvpovd.language.id = ?4 "
			+ "and povvpod.language.id = ?4 "
			+ "and povvpovd.language.id = ?4 "
			+ "and pr.id = ?2 and p.code = ?1 and prm.id = ?3")
	Optional<ProductInstance> findBySku(String code, Long productId, Integer storeId, Integer languageId);
	
	
	/**
	 * Gets the whole graph
	 * @param storeId
	 * @param productId
	 * @return
	 */
	@Query(value = "select distinct p from ProductInstance as p " 
			+ "join fetch p.product pr " 
			+ "left join fetch p.variant pv "
			+ "left join fetch pv.productOption pvpo " 
			+ "left join fetch pv.productOptionValue pvpov "
			+ "left join fetch pvpo.descriptions pvpod " 
			+ "left join fetch pvpov.descriptions pvpovd "

			+ "left join fetch p.variantValue pvv " 
			+ "left join fetch pvv.productOption pvvpo "
			+ "left join fetch pvv.productOptionValue pvvpov " 
			+ "left join fetch pvvpo.descriptions povvpod "
			+ "left join fetch pvpov.descriptions pvpovd "
			+ "left join fetch p.productInstanceGroup pig "
			+ "left join fetch pig.images pigi "
			+ "left join fetch pigi.descriptions pigid "
			

			+ "left join fetch pv.merchantStore pvm " 
			+ "where pr.id = ?2 and pvm.id = ?1")
	List<ProductInstance> findByProductId(Integer storeId, Long productId);

	
	
	@Query("select p from ProductInstance p join fetch p.product pr where p.sku = ?1 and pr.id = ?2")
	boolean existsBySkuAndProduct(String sku, Long productId);
	

	

}
