package com.salesmanager.core.business.repositories.catalog.product.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.price.ProductPrice;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {


	@Query("select p from ProductPrice p left join fetch p.descriptions pd inner join fetch p.productAvailability pa inner join fetch pa.product pap inner join fetch pap.merchantStore papm where p.id = ?1")
	ProductPrice findOne(Long id);
	
	
}
