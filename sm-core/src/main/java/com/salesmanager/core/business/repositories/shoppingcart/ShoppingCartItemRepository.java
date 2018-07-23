package com.salesmanager.core.business.repositories.shoppingcart;

import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

}
