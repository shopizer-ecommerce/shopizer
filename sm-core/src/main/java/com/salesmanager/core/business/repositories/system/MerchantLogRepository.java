package com.salesmanager.core.business.repositories.system;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.MerchantLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantLogRepository extends JpaRepository<MerchantLog, Long> {

    List<MerchantLog> findByStore(MerchantStore store);
}
