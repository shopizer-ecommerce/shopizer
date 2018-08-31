package com.salesmanager.core.business.repositories.customer.optin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.system.optin.CustomerOptin;

public interface CustomerOptinRepository extends JpaRepository<CustomerOptin, Long> {

	@Query("select distinct c from CustomerOptin as c left join fetch c.optin o join fetch o.merchant om where om.id = ?1 and o.code = ?2")
	List<CustomerOptin> findByCode(Integer storeId, String code);
	
	@Query("select distinct c from CustomerOptin as c left join fetch c.optin o join fetch o.merchant om where om.id = ?1 and o.code = ?2 and c.email = ?3")
	CustomerOptin findByMerchantAndCodeAndEmail(Integer storeId, String code, String email);
}
