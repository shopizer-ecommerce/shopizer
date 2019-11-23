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
	  
	  
	  @Query(value = "select distinct m from MerchantStore m left join fetch m.parent mp left join fetch m.country mc left join fetch m.currency mc left join fetch m.zone mz left join fetch m.defaultLanguage md left join fetch m.languages mls",
		      countQuery = "select count(distinct m) from MerchantStore m join m.parent")
		  Page<MerchantStore> listAll(String storeName, Pageable pageable);



}
