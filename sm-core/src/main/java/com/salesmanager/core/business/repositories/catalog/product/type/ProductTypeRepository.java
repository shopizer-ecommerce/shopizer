package com.salesmanager.core.business.repositories.catalog.product.type;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.salesmanager.core.model.catalog.product.type.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

	ProductType findByCode(String code);

}
