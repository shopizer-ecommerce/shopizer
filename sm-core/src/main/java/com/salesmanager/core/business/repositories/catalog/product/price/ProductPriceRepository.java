package com.salesmanager.core.business.repositories.catalog.product.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.price.ProductPrice;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {


	@Query("select p from ProductPrice p left join fetch p.descriptions pd inner join fetch p.productAvailability pa inner join fetch pa.product pap inner join fetch pap.merchantStore papm where p.id = ?1")
	ProductPrice findOne(Long id);
	
	//SELECT distinct pp.PRODUCT_PRICE_AMOUNT, p.SKU 
	//FROM SALESMANAGER.PRODUCT_PRICE AS pp
	//INNER JOIN SALESMANAGER.PRODUCT_AVAILABILITY AS pa ON pa.PRODUCT_AVAIL_ID = pp.PRODUCT_AVAIL_ID
	//INNER JOIN SALESMANAGER.PRODUCT AS p ON p.PRODUCT_ID = pa.PRODUCT_ID
	//INNER JOIN SALESMANAGER.PRODUCT_CATEGORY AS pc on p.PRODUCT_ID = pc.PRODUCT_ID
	//WHERE pc.CATEGORY_ID = 1
	//ORDER BY pp.PRODUCT_PRICE_AMOUNT;
	
	//@Query("select p from ProductPrice p join fetch p.productAvailability pd inner join fetch p.productAvailability pa inner join fetch pa.product pap inner join fetch pap.merchantStore papm where p.id = ?1")
	//List<ProductPrice> priceListByCategory(Long id, Integer storeId);
	
	
	
	
}
