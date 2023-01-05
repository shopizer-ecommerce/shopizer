package com.salesmanager.core.business.repositories.merchant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.merchant.MerchantStore;

public interface MerchantRepository extends JpaRepository<MerchantStore, Integer>, MerchantRepositoryCustom {


	@Query("select m from MerchantStore m "
			+ "left join fetch m.parent mp"
			+ "left join fetch m.country mc "
			+ "left join fetch m.currency mc "
			+ "left join fetch m.zone mz "
			+ "left join fetch m.defaultLanguage md "
			+ "left join fetch m.languages mls where m.code = ?1")
	MerchantStore findByCode(String code);
	
	@Query("select m from MerchantStore m left join fetch m.parent mp left join fetch m.country mc left join fetch m.currency mc left join fetch m.zone mz left join fetch m.defaultLanguage md left join fetch m.languages mls where m.id = ?1")
	MerchantStore getById(int id);
	

	@Query("select distinct m from MerchantStore m left join fetch m.parent mp left join fetch m.country mc left join fetch m.currency mc left join fetch m.zone mz left join fetch m.defaultLanguage md left join fetch m.languages mls where mp.code = ?1")
	List<MerchantStore> getByParent(String code);

	@Query("SELECT COUNT(m) > 0 FROM MerchantStore m WHERE m.code = :code")
	boolean existsByCode(String code);
	
	@Query("select new com.salesmanager.core.model.merchant.MerchantStore(m.id, m.code, m.storename) from MerchantStore m")
	List<MerchantStore> findAllStoreNames();
	
	 @Query(value = "select new com.salesmanager.core.model.merchant.MerchantStore(m.id, m.code, m.storename) from MerchantStore m left join m.parent mp "
	  			+ "where mp.code = ?1 or m.code = ?1")
	List<MerchantStore> findAllStoreNames(String storeCode);
	 
	 @Query(value = "select new com.salesmanager.core.model.merchant.MerchantStore(m.id, m.code, m.storename) from MerchantStore m left join m.parent mp "
	  			+ "where mp.code in ?1 or m.code in ?1")
	List<MerchantStore> findAllStoreNames(List<String> storeCode);

	@Query("select new com.salesmanager.core.model.merchant.MerchantStore(m.id, m.code, m.storename, m.storeEmailAddress) from MerchantStore m")
	List<MerchantStore> findAllStoreCodeNameEmail();
	
	@Query(
	  value = "select * from {h-schema}MERCHANT_STORE m "
	  		+ "where m.STORE_CODE = ?1 or ?2 is null or m.PARENT_ID = ?2", 
	  nativeQuery = true)
	  List<MerchantStore> listByGroup(String storeCode, Integer id);
}
