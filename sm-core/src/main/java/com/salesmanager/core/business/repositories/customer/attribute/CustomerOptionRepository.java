package com.salesmanager.core.business.repositories.customer.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.customer.attribute.CustomerOption;

public interface CustomerOptionRepository extends JpaRepository<CustomerOption, Long> {

	
	@Query("select o from CustomerOption o join fetch o.merchantStore om left join fetch o.descriptions od where o.id = ?1")
	CustomerOption findOne(Long id);
	
	@Query("select o from CustomerOption o join fetch o.merchantStore om left join fetch o.descriptions od where om.id = ?1 and o.code = ?2")
	CustomerOption findByCode(Integer merchantId, String code);
	
	@Query("select o from CustomerOption o join fetch o.merchantStore om left join fetch o.descriptions od where om.id = ?1 and od.language.id = ?2")
	List<CustomerOption> findByStore(Integer merchantId, Integer languageId);

}
