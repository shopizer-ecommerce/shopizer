package com.salesmanager.core.business.repositories.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom {

	
	@Query("select c from Customer c join fetch c.merchantStore cm left join fetch c.defaultLanguage cl left join fetch c.attributes ca left join fetch ca.customerOption cao left join fetch ca.customerOptionValue cav left join fetch cao.descriptions caod left join fetch cav.descriptions left join fetch c.groups where c.id = ?1")
	Customer findOne(Long id);
	
	@Query("select distinct c from Customer c join fetch c.merchantStore cm left join fetch c.defaultLanguage cl left join fetch c.attributes ca left join fetch ca.customerOption cao left join fetch ca.customerOptionValue cav left join fetch cao.descriptions caod left join fetch cav.descriptions left join fetch c.groups  where c.billing.firstName = ?1")
	List<Customer> findByName(String name);
	
	@Query("select c from Customer c join fetch c.merchantStore cm left join fetch c.defaultLanguage cl left join fetch c.attributes ca left join fetch ca.customerOption cao left join fetch ca.customerOptionValue cav left join fetch cao.descriptions caod left join fetch cav.descriptions left join fetch c.groups  where c.nick = ?1")
	Customer findByNick(String nick);
	
	@Query("select c from Customer c "
			+ "join fetch c.merchantStore cm "
			+ "left join fetch c.defaultLanguage cl "
			+ "left join fetch c.attributes ca "
			+ "left join fetch ca.customerOption cao "
			+ "left join fetch ca.customerOptionValue cav "
			+ "left join fetch cao.descriptions caod "
			+ "left join fetch cav.descriptions  "
			+ "left join fetch c.groups  "
			+ "left join fetch c.delivery cd "
			+ "left join fetch c.billing cb "
			+ "left join fetch cd.country "
			+ "left join fetch cd.zone "
			+ "left join fetch cb.country "
			+ "left join fetch cb.zone "
			+ "where c.nick = ?1 and cm.id = ?2")
	Customer findByNick(String nick, int storeId);
	
	@Query("select distinct c from Customer c join fetch c.merchantStore cm left join fetch c.defaultLanguage cl left join fetch c.attributes ca left join fetch ca.customerOption cao left join fetch ca.customerOptionValue cav left join fetch cao.descriptions caod left join fetch cav.descriptions left join fetch c.groups  where cm.id = ?1")
	List<Customer> findByStore(int storeId);
	

}
