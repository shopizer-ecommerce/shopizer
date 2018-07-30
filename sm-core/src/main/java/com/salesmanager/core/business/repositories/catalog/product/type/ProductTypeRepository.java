package com.salesmanager.core.business.repositories.catalog.product.type;

import com.salesmanager.core.model.catalog.product.type.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    ProductType findByCode(String code);
}
