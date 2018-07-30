package com.salesmanager.core.business.repositories.system;

import com.salesmanager.core.model.system.optin.Optin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OptinRepository extends JpaRepository<Optin, Long> {

    @Query("select distinct o from Optin as o  left join fetch o.merchant om where om.id = ?1")
    List<Optin> findByMerchant(Integer storeId);
}
