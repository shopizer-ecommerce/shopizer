package com.salesmanager.core.business.repositories.tax;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.tax.taxclass.TaxClass;

public interface TaxClassRepository extends JpaRepository<TaxClass, Long> {
	
	@Query("select t from TaxClass t join fetch t.merchantStore tm where tm.id=?1")
	List<TaxClass> findByStore(Integer id);
	
	@Query("select t from TaxClass t join fetch t.merchantStore tm where t.code=?1")
	TaxClass findByCode(String code);
	
	@Query("select t from TaxClass t join fetch t.merchantStore tm where tm.id=?1 and t.code=?2")
	TaxClass findByStoreAndCode(Integer id, String code);

}
