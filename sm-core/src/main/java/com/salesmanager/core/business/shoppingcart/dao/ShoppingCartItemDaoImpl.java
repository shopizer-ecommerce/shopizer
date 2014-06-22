package com.salesmanager.core.business.shoppingcart.dao;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCartItem;

@Repository("shoppingCartItemDao")
public class ShoppingCartItemDaoImpl extends SalesManagerEntityDaoImpl<Long, ShoppingCartItem>
		implements ShoppingCartItemDao {
	


}
