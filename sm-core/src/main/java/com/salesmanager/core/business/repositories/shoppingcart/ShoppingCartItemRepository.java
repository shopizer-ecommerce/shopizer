package com.salesmanager.core.business.repositories.shoppingcart;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {


}
