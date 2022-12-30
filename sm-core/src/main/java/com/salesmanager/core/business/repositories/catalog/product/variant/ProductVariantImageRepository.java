package com.salesmanager.core.business.repositories.catalog.product.variant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.variant.ProductVariantImage;

public interface ProductVariantImageRepository extends JpaRepository<ProductVariantImage, Long> {

	@Query("select p from ProductVariantImage p"
			+ " left join fetch p.descriptions pd"
			+ " join fetch p.productVariantGroup pg"
			+ " where p.id = ?1")
	ProductVariantImage findOne(Long id);
	
	
	@Query("select p from ProductVariantImage p "
			+ "left join fetch p.descriptions pd "
			+ "join fetch p.productVariantGroup pg "
			+ "join fetch pg.productVariants pi "
			+ "join fetch pi.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where ppp.id = ?1 and pppm.code = ?2")
	List<ProductVariantImage> finByProduct(Long productId, String storeCode);
	
	@Query("select p from ProductVariantImage p "
			+ "left join fetch p.descriptions pd "
			+ "join fetch p.productVariantGroup pg "
			+ "join fetch pg.productVariants pi "
			+ "join fetch pi.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where pg.id = ?1 and pppm.code = ?2")
	List<ProductVariantImage> finByProductVariantGroup(Long productVariantGroupId, String storeCode);
	
    /**
	@Query("select p from ProductVariantImage p "
			+ "left join fetch p.descriptions pd "
			+ "join fetch p.productVariantGroup pg "
			+ "join fetch pg.productVariants pi "
			+ "join fetch pi.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where ppp.id = ?1 and pppm.code = ?2 and pd.language.code = ?3")
	List<ProductVariantImage> finByProduct(Long productId, String storeCode, String lang);
	**/
	
	@Query("select p from ProductVariantImage p "
			+ "left join fetch p.descriptions pd "
			+ "join fetch p.productVariantGroup pg "
			+ "join fetch pg.productVariants pi "
			+ "join fetch pi.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where pi.id = ?1 and pppm.code = ?2 and pd.language.code = ?3")
	List<ProductVariantImage> finByProductVariant(Long productVariantId, String storeCode);

}
