package com.salesmanager.core.business.repositories.merchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.merchant.MerchantStore;

public interface MerchantRepository extends JpaRepository<MerchantStore, Integer>, MerchantRepositoryCustom {

	@Query("select m from MerchantStore m left join fetch m.country mc left join fetch m.currency mc left join fetch m.zone mz left join fetch m.defaultLanguage md left join fetch m.languages mls where m.code = ?1")
	MerchantStore findByCode(String code);
	
	@Query("select m from MerchantStore m left join fetch m.country mc left join fetch m.currency mc left join fetch m.zone mz left join fetch m.defaultLanguage md left join fetch m.languages mls where m.id = ?1")
	MerchantStore getById(int id);

	@Query("SELECT COUNT(m) > 0 FROM MerchantStore m WHERE m.code = :code")
	boolean existsByCode(String code);
}
