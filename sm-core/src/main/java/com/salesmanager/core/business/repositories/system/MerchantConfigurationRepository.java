package com.salesmanager.core.business.repositories.system;

import com.salesmanager.core.model.system.MerchantConfiguration;
import com.salesmanager.core.model.system.MerchantConfigurationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MerchantConfigurationRepository extends JpaRepository<MerchantConfiguration, Long> {

    @Query("select m from MerchantConfiguration m join fetch m.merchantStore ms where ms.id=?1")
    List<MerchantConfiguration> findByMerchantStore(Integer id);

    @Query("select m from MerchantConfiguration m join fetch m.merchantStore ms where ms.id=?1 and m.key=?2")
    MerchantConfiguration findByMerchantStoreAndKey(Integer id, String key);

    @Query("select m from MerchantConfiguration m join fetch m.merchantStore ms where ms.id=?1 and m.merchantConfigurationType=?2")
    List<MerchantConfiguration> findByMerchantStoreAndType(Integer id, MerchantConfigurationType type);
}
