package com.salesmanager.core.business.repositories.shoppingcart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.shoppingcart.ShoppingCart;
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	@Query("select c from ShoppingCart c left join fetch c.lineItems cl left join fetch cl.attributes cla join fetch c.merchantStore cm where c.id = ?1")
	ShoppingCart findOne(Long id);
	
	@Query("select c from ShoppingCart c left join fetch c.lineItems cl left join fetch cl.attributes cla join fetch c.merchantStore cm where c.shoppingCartCode = ?1")
	ShoppingCart findByCode(String code);
	
	@Query("select c from ShoppingCart c left join fetch c.lineItems cl left join fetch cl.attributes cla join fetch c.merchantStore cm where cm.id = ?1 and c.id = ?2")
	ShoppingCart findById(Integer merchantId, Long id);
	
	@Query("select c from ShoppingCart c left join fetch c.lineItems cl left join fetch cl.attributes cla join fetch c.merchantStore cm where cm.id = ?1 and c.shoppingCartCode = ?2")
	ShoppingCart findByCode(Integer merchantId, String code);
	
	@Query("select c from ShoppingCart c left join fetch c.lineItems cl left join fetch cl.attributes cla join fetch c.merchantStore cm where c.customerId = ?1")
	List<ShoppingCart> findByCustomer(Long customerId);
	
}
