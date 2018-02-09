/**
 *
 */
package com.salesmanager.shop.store.controller.shoppingCart.facade;

import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.shop.model.shoppingcart.PersistableShoppingCartItem;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCart;
import com.salesmanager.shop.model.shoppingcart.ShoppingCartData;
import com.salesmanager.shop.model.shoppingcart.ShoppingCartItem;

import java.util.List;

/**
 * </p>Shopping cart Facade which provide abstraction layer between
 * SM core module and Controller.
 * Only Data Object will be exposed to controller by hiding model
 * object from view.</p>
 * @author Umesh Awasthi
 * @author Carl Samson
 * @version 1.0
 * @since1.0
 *
 */


public interface ShoppingCartFacade {

    public ShoppingCartData addItemsToShoppingCart(ShoppingCartData shoppingCart,final ShoppingCartItem item, final MerchantStore store,final Language language,final Customer customer) throws Exception;
    public ShoppingCart createCartModel(final String shoppingCartCode, final MerchantStore store,final Customer customer) throws Exception;
    /**
     * Method responsible for getting shopping cart from
     * either session or from underlying DB.
     */
    public ShoppingCartData getShoppingCartData(final Customer customer,final  MerchantStore store,final String shoppingCartId, Language language) throws Exception;
    public ShoppingCartData getShoppingCartData(final ShoppingCart shoppingCart, Language language) throws Exception;
    public ShoppingCartData getShoppingCartData(String code, MerchantStore store, Language lnguage) throws Exception;
    public ShoppingCartData removeCartItem(final Long itemID, final String cartId,final MerchantStore store,final Language language ) throws Exception;
    public ShoppingCartData updateCartItem(final Long itemID, final String cartId, final long quantity,final MerchantStore store,Language language ) throws Exception;
    public void deleteShoppingCart(final Long id, final MerchantStore store) throws Exception;
	ShoppingCartData updateCartItems(List<ShoppingCartItem> shoppingCartItems,
			MerchantStore store, Language language) throws Exception;
	public ShoppingCart getShoppingCartModel(final String shoppingCartCode, MerchantStore store) throws Exception;
	public ShoppingCart getShoppingCartModel(Long id, MerchantStore store) throws Exception;
	public ShoppingCart getShoppingCartModel(final Customer customer, MerchantStore store) throws Exception;
	void deleteShoppingCart(String code, MerchantStore store) throws Exception;
	void saveOrUpdateShoppingCart(ShoppingCart cart) throws Exception;
	
	/**
	 * Get ShoppingCart
	 * This method is used by the API
	 * @param customer
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableShoppingCart getCart(Customer customer, MerchantStore store, Language language) throws Exception;
	
	/**
	 * Add product to ShoppingCart
	 * This method is used by the API
	 * @param customer
	 * @param item
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableShoppingCart addToCart(Customer customer, PersistableShoppingCartItem item, MerchantStore store, Language language) throws Exception;

	/**
	 * Retrieves a shopping cart by ID
	 * @param shoppingCartId
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableShoppingCart getById(Long shoppingCartId, MerchantStore store, Language language) throws Exception;
}
