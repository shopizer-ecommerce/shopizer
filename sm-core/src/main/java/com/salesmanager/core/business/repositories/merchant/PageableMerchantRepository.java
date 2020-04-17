package com.salesmanager.core.business.repositories.merchant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.merchant.MerchantStore;

public interface PageableMerchantRepository extends PagingAndSortingRepository<MerchantStore, Long> {
	
	  /*
	   * List by parent store
	   */
	  @Query(value = "select distinct m from MerchantStore m left join fetch m.parent mp left join fetch m.country mc left join fetch m.currency mc left join fetch m.zone mz left join fetch m.defaultLanguage md left join fetch m.languages mls where mp.code = ?1",
		      countQuery = "select count(distinct m) from MerchantStore m join m.parent mp where mp.code = ?1")
		  Page<MerchantStore> listByStore(String code, Pageable pageable);
	  
	  
	  @Query(value = "select distinct m from MerchantStore m left join fetch m.parent mp left join fetch m.country mc left join fetch m.currency mc left join fetch m.zone mz left join fetch m.defaultLanguage md left join fetch m.languages mls where (?1 is null or m.storename like %?1%)",
		      countQuery = "select count(distinct m) from MerchantStore m where (?1 is null or m.storename like %?1%)")
		  Page<MerchantStore> listAll(String storeName, Pageable pageable);
	  
	  @Query(value = "select distinct m from MerchantStore m left join fetch m.parent mp "
	  		+ "left join fetch m.country mc "
	  		+ "left join fetch m.currency mc left "
	  		+ "join fetch m.zone mz "
	  		+ "left join fetch m.defaultLanguage md "
	  		+ "left join fetch m.languages mls "
	  		+ "where m.retailer = true and (?1 is null or m.storename like %?1%)",
		      countQuery = "select count(distinct m) from MerchantStore m join m.parent "
		      		+ "where m.retailer = true and (?1 is null or m.storename like %?1%)")
		  Page<MerchantStore> listAllRetailers(String storeName, Pageable pageable);
	  
	  
	  @Query(value = "select distinct m from MerchantStore m left join m.parent mp "
		  		+ "left join fetch m.country pc "
		  		+ "left join fetch m.currency pcu "
		  		+ "left join fetch m.languages pl "
		  		+ "left join fetch m.zone pz "
	  			+ "where mp.code = ?1 or m.code = ?1 "
		  		+ "and (?2 is null or (m.storename like %?2% or mp.storename like %?2%))",
			      countQuery = "select count(distinct m) from MerchantStore m left join m.parent mp "
			      		+ "where mp.code = ?1 or m.code = ?1 and (?2 is null or (m.storename like %?2% or mp.storename like %?2%))")
			  Page<MerchantStore> listChilds(String storeCode, String storeName, Pageable pageable);
	  
	  /**
	  @Query(value = "select distinct p from MerchantStore p left join fetch p.stores ps "
	  			+ "with ps.parent.code = p.code "
	  			+ "where p.code = ?1 or ps.code = ?1 "
		  		+ "and (?2 is null or (p.storename like %?2% or ps.storename like %?2%))",
			      countQuery = "select count(distinct p) from MerchantStore p left join p.stores ps "
			      		+ "with ps.parent.code = p.code where p.code = ?1 or ps.code = ?1 and (?2 is null or (p.storename like %?2% or ps.storename like %?2%))")
			  Page<MerchantStore> listByGroup(String storeCode, String storeName, Pageable pageable);**/
	  
/*	  		@Query(value = "select distinct p from MerchantStore p left join fetch p.stores ps "
	  			+ "where p.code = ?1 and ps.code in (select ps.code from MerchantStore m where m.code = ?1)"
		  		+ "and (?2 is null or (p.storename like %?2% or ps.storename like %?2%))",
			      countQuery = "select count(distinct p) from MerchantStore p left join p.stores ps "
			      		+ "where p.code = ?1 and ps.code in (select ps.code from MerchantStore m where m.code = ?1) and (?2 is null or (p.storename like %?2% or ps.storename like %?2%))")
			  Page<MerchantStore> listByGroup(String storeCode, String storeName, Pageable pageable);*/
	  
	  
/*	  		@Query(
			  value = "select * from MERCHANT_STORE m "
			  		+ "left join MERCHANT_STORE p on m.MERCHANT_ID = p.MERCHANT_ID "
			  		+ "where m.STORE_CODE = ?1 or ?2 is null or p.MERCHANT_ID = ?2",
			  countQuery = "select count(*) from MERCHANT_STORE m left join MERCHANT_STORE p on m.MERCHANT_ID = p.MERCHANT_ID where m.STORE_CODE = ?1 or ?2 is null or p.MERCHANT_ID = ?2", 
			  nativeQuery = true)
			  Page<MerchantStore> listByGroupBeta(String storeCode, Integer id, String storeName, Pageable pageable);
	  		*/
	  		@Query(
			  value = "select * from MERCHANT_STORE m "
			  		+ "where (m.STORE_CODE = ?1 or (?2 is null or m.PARENT_ID = ?2)) "
			  		+ "and (?3 is null or m.STORE_NAME like %?3%)",
			  countQuery = "select count(*) from MERCHANT_STORE m where (m.STORE_CODE = ?1 or (?2 is null or m.PARENT_ID = ?2)) and (?3 is null or m.STORE_NAME like %?3%)", 
			  nativeQuery = true)
			  Page<MerchantStore> listByGroup(String storeCode, Integer id, String storeName, Pageable pageable);
	 

}
