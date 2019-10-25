package com.salesmanager.core.business.repositories.shoppingcart;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem;
public interface ShoppingCartAttributeRepository extends JpaRepository<ShoppingCartAttributeItem, Long> {


}
