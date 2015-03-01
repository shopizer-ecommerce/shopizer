/**
 *
 */
package com.salesmanager.web.shop.controller.customer.facade;

import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.service.CustomerService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.web.entity.customer.Address;
import com.salesmanager.web.entity.customer.CustomerEntity;
import com.salesmanager.web.entity.customer.PersistableCustomer;
import com.salesmanager.web.entity.customer.ReadableCustomer;
import com.salesmanager.web.entity.shoppingcart.ShoppingCartData;

/**
 * <p>Customer facade working as a bridge between {@link CustomerService} and Controller
 * It will take care about interacting with Service API and doing any pre and post processing
 * </p>
 *
 * @author Umesh Awasthi
 * @version 1/2
 *
 *
 */
public interface CustomerFacade
{

    /**
     * Method used to fetch customer based on the username and storecode.
     * Customer username is unique to each store.
     *
     * @param userName
     * @param storeCode
     * @param store
     * @param language
     * @throws Exception
     *
     */
    public CustomerEntity getCustomerDataByUserName(final String userName,final MerchantStore store, final Language language) throws Exception;

    /**
     * Creates a ReadableCustomer
     * @param id
     * @param merchantStore
     * @param language
     * @return
     */
    public ReadableCustomer getCustomerById(final Long id, final MerchantStore merchantStore, final Language language) throws Exception;
    
    /**
     * <p>Method responsible for merging cart during authentication, 
     *     Method will perform following operations
     * <li> Merge Customer Shopping Cart with Session Cart if any.</li>
     * <li> Convert Customer to {@link CustomerEntity} </li>
     * </p>
     *
     * @param userName username of Customer
     * @param storeCode storeCode to which user is associated/
     * @param sessionShoppingCartId session shopping cart, if user already have few items in Cart.
     * @throws Exception
     */
    public ShoppingCartData mergeCart(final Customer customer,final String sessionShoppingCartId,final MerchantStore store,final Language language) throws Exception;
    
    public Customer getCustomerByUserName(final String userName, final MerchantStore store) throws Exception;
    
    public boolean checkIfUserExists(final String userName,final MerchantStore store) throws Exception;
    
    public CustomerEntity  registerCustomer( final PersistableCustomer customer,final MerchantStore merchantStore, final Language language) throws Exception;
    
    public Address getAddress(final Long userId, final MerchantStore merchantStore,boolean isBillingAddress) throws Exception;
    
    public void updateAddress( Long userId, MerchantStore merchantStore, Address address, final Language language )
                    throws Exception;

    public void setCustomerModelDefaultProperties(Customer customer, MerchantStore store) throws Exception; 
	//public Customer populateCustomerModel(PersistableCustomer customer,
	//		MerchantStore merchantStore) throws Exception;
	
	public void authenticate(Customer customer, String userName, String password) throws Exception;

	Customer getCustomerModel(PersistableCustomer customer,
			MerchantStore merchantStore, Language language) throws Exception;
	
	Customer populateCustomerModel(Customer customerModel, PersistableCustomer customer,
			MerchantStore merchantStore, Language language) throws Exception;
	

	

}
