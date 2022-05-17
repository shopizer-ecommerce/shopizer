package com.salesmanager.core.business.repositories.catalog.product.instance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.instance.ProductInstanceImage;

public interface ProductInstanceImageRepository extends JpaRepository<ProductInstanceImage, Long> {

	@Query("select p from ProductInstanceImage p"
			+ " left join fetch p.descriptions pd"
			+ " join fetch p.productInstanceGroup pg"
			+ " where p.id = ?1")
	ProductInstanceImage findOne(Long id);
	
	
	@Query("select p from ProductInstanceImage p "
			+ "left join fetch p.descriptions pd "
			+ "join fetch p.productInstanceGroup pg "
			+ "join fetch pg.productInstances pi "
			+ "join fetch pi.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where ppp.id = ?1 and pppm.code = ?2")
	List<ProductInstanceImage> finByProduct(Long productId, String storeCode);
	
	@Query("select p from ProductInstanceImage p "
			+ "left join fetch p.descriptions pd "
			+ "join fetch p.productInstanceGroup pg "
			+ "join fetch pg.productInstances pi "
			+ "join fetch pi.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where pg.id = ?1 and pppm.code = ?2")
	List<ProductInstanceImage> finByProductInstanceGroup(Long productInstanceGroupId, String storeCode);
	
    /**
	@Query("select p from ProductInstanceImage p "
			+ "left join fetch p.descriptions pd "
			+ "join fetch p.productInstanceGroup pg "
			+ "join fetch pg.productInstances pi "
			+ "join fetch pi.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where ppp.id = ?1 and pppm.code = ?2 and pd.language.code = ?3")
	List<ProductInstanceImage> finByProduct(Long productId, String storeCode, String lang);
	**/
	
	@Query("select p from ProductInstanceImage p "
			+ "left join fetch p.descriptions pd "
			+ "join fetch p.productInstanceGroup pg "
			+ "join fetch pg.productInstances pi "
			+ "join fetch pi.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where pi.id = ?1 and pppm.code = ?2 and pd.language.code = ?3")
	List<ProductInstanceImage> finByProductInstance(Long productInstanceId, String storeCode);

}
