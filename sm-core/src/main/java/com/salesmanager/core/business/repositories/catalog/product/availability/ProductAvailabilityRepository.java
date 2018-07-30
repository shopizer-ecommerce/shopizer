package com.salesmanager.core.business.repositories.catalog.product.availability;

import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAvailabilityRepository extends JpaRepository<ProductAvailability, Long> {

}
