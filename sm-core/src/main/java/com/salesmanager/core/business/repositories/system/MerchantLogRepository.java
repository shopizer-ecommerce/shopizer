package com.salesmanager.core.business.repositories.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.MerchantLog;

public interface MerchantLogRepository extends JpaRepository<MerchantLog, Long> {

	List<MerchantLog> findByStore(MerchantStore store);
}
