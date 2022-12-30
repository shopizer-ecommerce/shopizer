package com.salesmanager.core.business.repositories.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.system.MerchantConfiguration;
import com.salesmanager.core.model.system.MerchantConfigurationType;

public interface MerchantConfigurationRepository extends JpaRepository<MerchantConfiguration, Long> {

	//List<MerchantConfiguration> findByModule(String moduleName);
	
	//MerchantConfiguration findByCode(String code);
	
	@Query("select m from MerchantConfiguration m join fetch m.merchantStore ms where ms.id=?1")
	List<MerchantConfiguration> findByMerchantStore(Integer id);
	
	@Query("select m from MerchantConfiguration m join fetch m.merchantStore ms where ms.id=?1 and m.key=?2")
	MerchantConfiguration findByMerchantStoreAndKey(Integer id, String key);
	
	@Query("select m from MerchantConfiguration m join fetch m.merchantStore ms where ms.id=?1 and m.merchantConfigurationType=?2")
	List<MerchantConfiguration> findByMerchantStoreAndType(Integer id, MerchantConfigurationType type);
}
