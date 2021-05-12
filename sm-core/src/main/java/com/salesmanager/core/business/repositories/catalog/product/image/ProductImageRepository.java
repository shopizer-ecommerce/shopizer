package com.salesmanager.core.business.repositories.catalog.product.image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.image.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {


	@Query("select p from ProductImage p left join fetch p.descriptions pd inner join fetch p.product pp inner join fetch pp.merchantStore ppm where p.id = ?1")
	ProductImage findOne(Long id);
	
	@Query("select p from ProductImage p left join fetch p.descriptions pd inner join fetch p.product pp inner join fetch pp.merchantStore ppm where pp.id = ?1 and ppm.code = ?2 and p.productImage = ?3")
	List<ProductImage> finByName(Long productId, String storeCode, String name);
	
	
}
