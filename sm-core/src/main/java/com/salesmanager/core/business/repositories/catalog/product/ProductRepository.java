package com.salesmanager.core.business.repositories.catalog.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.Product;


public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
	
	@Query("select case when count(p)> 0 then true else false end"
			+ " from Product p join p.merchantStore pm"
			+ " left join p.instances pinst "
			+ " where pinst.sku=?1 or p.sku=?1 and pm.id=?2")
	boolean existsBySku(String sku, Integer store);

}
