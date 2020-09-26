package com.salesmanager.core.business.services.shoppingcart;

import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.ShippingProduct;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;

public interface ShoppingCartService extends SalesManagerEntityService<Long, ShoppingCart> {

	ShoppingCart getShoppingCart(Customer customer) throws ServiceException;

	void saveOrUpdate(ShoppingCart shoppingCart) throws ServiceException;

	ShoppingCart getById(Long id, MerchantStore store) throws ServiceException;

	ShoppingCart getByCode(String code, MerchantStore store) throws ServiceException;

	//ShoppingCart getByCustomer(Customer customer) throws ServiceException;

	/**
	 * Creates a list of ShippingProduct based on the ShoppingCart if items are
	 * virtual return list will be null
	 * 
	 * @param cart
	 * @return
	 * @throws ServiceException
	 */
	List<ShippingProduct> createShippingProduct(ShoppingCart cart) throws ServiceException;

	/**
	 * Looks if the items in the ShoppingCart are free of charges
	 * 
	 * @param cart
	 * @return
	 * @throws ServiceException
	 */
	//boolean isFreeShoppingCart(ShoppingCart cart) throws ServiceException;

	//boolean isFreeShoppingCart(List<ShoppingCartItem> items) throws ServiceException;

	/**
	 * Populates a ShoppingCartItem from a Product and attributes if any
	 * 
	 * @param product
	 * @return
	 * @throws ServiceException
	 */
	ShoppingCartItem populateShoppingCartItem(Product product) throws ServiceException;

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
	public ShoppingCart mergeShoppingCarts(final ShoppingCart userShoppingCart, final ShoppingCart sessionCart,
			final MerchantStore store) throws Exception;

	/**
	 * Determines if the shopping cart requires shipping
	 * 
	 * @param cart
	 * @return
	 * @throws ServiceException
	 */
	//boolean requiresShipping(ShoppingCart cart) throws ServiceException;
	
	/**
	 * Removes a shopping cart item
	 * @param item
	 */
	void deleteShoppingCartItem(Long id);

}