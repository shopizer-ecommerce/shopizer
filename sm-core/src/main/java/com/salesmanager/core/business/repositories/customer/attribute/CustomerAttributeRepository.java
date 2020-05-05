package com.salesmanager.core.business.repositories.customer.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.customer.attribute.CustomerAttribute;

public interface CustomerAttributeRepository extends JpaRepository<CustomerAttribute, Long> {

	
	@Query("select a from CustomerAttribute a left join fetch a.customerOption aco left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where a.id = ?1")
	CustomerAttribute findOne(Long id);
	
	@Query("select a from CustomerAttribute a join fetch a.customer ac left join fetch a.customerOption aco join fetch aco.merchantStore acom left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where acom.id = ?1 and ac.id = ?2 and aco.id = ?3")
	CustomerAttribute findByOptionId(Integer merchantId,Long customerId,Long id);
	
	@Query("select a from CustomerAttribute a join fetch a.customer ac left join fetch a.customerOption aco join fetch aco.merchantStore acom left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where acom.id = ?1 and aco.id = ?2")
	List<CustomerAttribute> findByOptionId(Integer merchantId,Long id);

	@Query("select distinct a from CustomerAttribute a join fetch a.customer ac left join fetch a.customerOption aco join fetch aco.merchantStore acom left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where acom.id = ?1 and ac.id = ?2")
	List<CustomerAttribute> findByCustomerId(Integer merchantId,Long customerId);
	
	@Query("select a from CustomerAttribute a join fetch a.customer ac left join fetch a.customerOption aco join fetch aco.merchantStore acom left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where acom.id = ?1 and acov.id = ?2")
	List<CustomerAttribute> findByOptionValueId(Integer merchantId,Long Id);
}
