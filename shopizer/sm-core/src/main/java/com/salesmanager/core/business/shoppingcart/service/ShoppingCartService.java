package com.salesmanager.core.business.shoppingcart.service;

import java.util.List;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.shipping.model.ShippingProduct;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCart;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCartItem;


public interface ShoppingCartService extends SalesManagerEntityService<Long, ShoppingCart> {

	ShoppingCart getShoppingCart(Customer customer) throws ServiceException;

	void saveOrUpdate(ShoppingCart shoppingCart) throws ServiceException;

	ShoppingCart getById(Long id, MerchantStore store) throws ServiceException;

	ShoppingCart getByCode(String code, MerchantStore store)
			throws ServiceException;

	ShoppingCart getByCustomer(Customer customer) throws ServiceException;

	/**
	 * Creates a list of ShippingProduct based on the ShoppingCart
	 * if items are virtual return list will be null
	 * @param cart
	 * @return
	 * @throws ServiceException
	 */
	List<ShippingProduct> createShippingProduct(ShoppingCart cart)
			throws ServiceException;



	/**
	 * Looks if the items in the ShoppingCart are free of charges
	 * @param cart
	 * @return
	 * @throws ServiceException
	 */
	boolean isFreeShoppingCart(ShoppingCart cart) throws ServiceException;
	
	boolean isFreeShoppingCart(List<ShoppingCartItem> items) throws ServiceException;

	/**
	 * Populates a ShoppingCartItem from a Product and attributes if any
	 * @param product
	 * @return
	 * @throws ServiceException
	 */
	ShoppingCartItem populateShoppingCartItem(Product product)
			throws ServiceException;
	
	void deleteCart(ShoppingCart cart) throws ServiceException;


	void removeShoppingCart(ShoppingCart cart) throws ServiceException;

	/**
	 *
	 * @param userShoppingModel
	 * @param sessionCart
	 * @param store
	 * @return {@link ShoppingCart} merged Shopping Cart
	 * @throws Exception
	 */
	public ShoppingCart mergeShoppingCarts(final ShoppingCart userShoppingCart,final ShoppingCart sessionCart,final MerchantStore store  ) throws Exception;

	/**
	 * Determines if the shopping cart requires shipping
	 * @param cart
	 * @return
	 * @throws ServiceException
	 */
	boolean requiresShipping(ShoppingCart cart) throws ServiceException;





}