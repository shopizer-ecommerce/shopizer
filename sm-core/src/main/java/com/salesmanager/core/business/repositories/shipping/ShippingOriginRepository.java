package com.salesmanager.core.business.repositories.shipping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.shipping.ShippingOrigin;

public interface ShippingOriginRepository extends JpaRepository<ShippingOrigin, Long> {

	@Query("select s from ShippingOrigin as s join fetch s.merchantStore sm where sm.id = ?1")
	ShippingOrigin findByStore(Integer storeId);
}
