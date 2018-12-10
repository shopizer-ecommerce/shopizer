package com.salesmanager.core.business.repositories.merchant;

import org.springframework.data.jpa.repository.JpaRepository;


import com.salesmanager.core.model.merchant.MerchantStore;

public interface MerchantRepository extends JpaRepository<MerchantStore, Integer>,MerchantRepositoryCustom {



}
