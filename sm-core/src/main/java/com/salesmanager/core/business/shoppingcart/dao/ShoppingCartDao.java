package com.salesmanager.core.business.shoppingcart.dao;

import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCart;

public interface ShoppingCartDao extends SalesManagerEntityDao<Long, ShoppingCart> {

	ShoppingCart getById(Long id, MerchantStore store);

	ShoppingCart getByCustomer(Customer customer);

	ShoppingCart getByCode(String code, MerchantStore store);

	/**
	 * Get a ShoppingCart object without collection objects
	 * @param id
	 * @return
	 */
	ShoppingCart getShoppingCartById(Long id);

	/**
	 * Get a ShoppingCart object without collection objects
	 * @param code
	 * @return
	 */
	ShoppingCart getShoppingCartByCode(String code);

	public void removeShoppingCart(ShoppingCart cart);



}
