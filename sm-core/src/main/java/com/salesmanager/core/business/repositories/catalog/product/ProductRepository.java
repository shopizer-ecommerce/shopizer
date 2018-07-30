package com.salesmanager.core.business.repositories.catalog.product;

import com.salesmanager.core.model.catalog.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

}
