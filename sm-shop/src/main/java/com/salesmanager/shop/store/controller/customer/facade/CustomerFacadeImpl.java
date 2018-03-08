/**
 *
 */
package com.salesmanager.shop.store.controller.customer.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.customer.attribute.CustomerOptionService;
import com.salesmanager.core.business.services.customer.attribute.CustomerOptionValueService;
import com.salesmanager.core.business.services.customer.review.CustomerReviewService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartCalculationService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartService;
import com.salesmanager.core.business.services.system.EmailService;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.services.user.PermissionService;
import com.salesmanager.core.business.utils.CoreConfiguration;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.review.CustomerReview;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.GroupType;
import com.salesmanager.core.model.user.Permission;
import com.salesmanager.shop.admin.model.userpassword.UserReset;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.customer.Address;
import com.salesmanager.shop.model.customer.CustomerEntity;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.customer.PersistableCustomerReview;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.model.customer.ReadableCustomerReview;
import com.salesmanager.shop.model.customer.UserAlreadyExistException;
import com.salesmanager.shop.populator.customer.CustomerBillingAddressPopulator;
import com.salesmanager.shop.populator.customer.CustomerDeliveryAddressPopulator;
import com.salesmanager.shop.populator.customer.CustomerEntityPopulator;
import com.salesmanager.shop.populator.customer.CustomerPopulator;
import com.salesmanager.shop.populator.customer.PersistableCustomerBillingAddressPopulator;
import com.salesmanager.shop.populator.customer.PersistableCustomerReviewPopulator;
import com.salesmanager.shop.populator.customer.PersistableCustomerShippingAddressPopulator;
import com.salesmanager.shop.populator.customer.ReadableCustomerPopulator;
import com.salesmanager.shop.populator.customer.ReadableCustomerReviewPopulator;
import com.salesmanager.shop.utils.EmailTemplatesUtils;
import com.salesmanager.shop.utils.LocaleUtils;


/**
 * Customer Facade work as an abstraction layer between Controller and Service layer.
 * It work as an entry point to service layer.
 * @author Umesh Awasthi
 *
 */

@Service("customerFacade")
public class CustomerFacadeImpl implements CustomerFacade
{

	private static final Logger LOG = LoggerFactory.getLogger(CustomerFacadeImpl.class);
	private final static int USERNAME_LENGTH=6;
	
	public final static String ROLE_PREFIX = "ROLE_";//Spring Security 4


	 @Inject
     private CustomerService customerService;

     @Inject
     private ShoppingCartService shoppingCartService;

     @Inject
     private ShoppingCartCalculationService shoppingCartCalculationService;

     @Inject
     private PricingService pricingService;

     @Inject
     private ProductService productService;

     @Inject
     private ProductAttributeService productAttributeService;
     
     @Inject
     private LanguageService languageService;

     @Inject
     private CustomerOptionValueService customerOptionValueService;

     @Inject
     private CustomerOptionService customerOptionService;


     @Inject
     private CountryService countryService;

     @Inject
     private GroupService   groupService;
     
     @Inject
     private PermissionService   permissionService;

     @Inject
     private ZoneService zoneService;
     
     @SuppressWarnings( "deprecation" )
     @Inject
     private PasswordEncoder passwordEncoder;
     
 	 @Inject
 	 private EmailService emailService;
 	 
 	 @Inject
 	 private EmailTemplatesUtils emailTemplatesUtils;
 	 
 	 @Inject
     private AuthenticationManager customerAuthenticationManager;
 	 
 	 @Inject
 	 private CustomerReviewService customerReviewService;


 	@Inject
 	private CoreConfiguration coreConfiguration;

    /**
     * Method used to fetch customer based on the username and storecode.
     * Customer username is unique to each store.
     *
     * @param userName
     * @param store
     * @throws ConversionException
     */
    @Override
    public CustomerEntity getCustomerDataByUserName( final String userName, final MerchantStore store, final Language language ) throws Exception
    {
        LOG.info( "Fetching customer with userName" +userName);
        Customer customer=customerService.getByNick( userName );

        if(customer !=null){
            LOG.info( "Found customer, converting to CustomerEntity");
            try{
            CustomerEntityPopulator customerEntityPopulator=new CustomerEntityPopulator();
            return customerEntityPopulator.populate( customer, store, language ); //store, language

            }
            catch(ConversionException ex){
                LOG.error( "Error while converting Customer to CustomerEntity", ex );
                throw new Exception(ex);
            }
        }

        return null;

    }


    /* (non-Javadoc)
    *  @see com.salesmanager.web.shop.controller.customer.facade#mergeCart(final Customer customerModel, final String sessionShoppingCartId ,final MerchantStore store,final Language language)
    */
    @Override
    public ShoppingCart mergeCart( final Customer customerModel, final String sessionShoppingCartId ,final MerchantStore store,final Language language)
        throws Exception
    {

        LOG.debug( "Starting merge cart process" );
        if(customerModel != null){
            ShoppingCart customerCart = shoppingCartService.getByCustomer( customerModel );
            if(StringUtils.isNotBlank( sessionShoppingCartId )){
	            ShoppingCart sessionShoppingCart = shoppingCartService.getByCode( sessionShoppingCartId, store );
	            if(sessionShoppingCart != null){
	               if(customerCart == null){
	            	   if(sessionShoppingCart.getCustomerId()==null) {//saved shopping cart does not belong to a customer
		                   LOG.debug( "Not able to find any shoppingCart with current customer" );
		                   //give it to the customer
		                   sessionShoppingCart.setCustomerId( customerModel.getId() );
		                   shoppingCartService.saveOrUpdate( sessionShoppingCart );
		                   customerCart =shoppingCartService.getById( sessionShoppingCart.getId(), store );
		                   return customerCart;
		                   //return populateShoppingCartData(customerCart,store,language);
	            	   } else {
	            		   return null;
	            	   }
	               }
	               else{
	                    if(sessionShoppingCart.getCustomerId()==null) {//saved shopping cart does not belong to a customer
	                    	//assign it to logged in user
	                    	LOG.debug( "Customer shopping cart as well session cart is available, merging carts" );
	                    	customerCart=shoppingCartService.mergeShoppingCarts( customerCart, sessionShoppingCart, store );
	                    	customerCart =shoppingCartService.getById( customerCart.getId(), store );
	                    	return customerCart;
		                    //return populateShoppingCartData(customerCart,store,language);
	                    } else {
	                    	if(sessionShoppingCart.getCustomerId().longValue()==customerModel.getId().longValue()) {
	                    		if(!customerCart.getShoppingCartCode().equals(sessionShoppingCart.getShoppingCartCode())) {
		                    		//merge carts
		                    		LOG.info( "Customer shopping cart as well session cart is available" );
		                    		customerCart=shoppingCartService.mergeShoppingCarts( customerCart, sessionShoppingCart, store );
		                    		customerCart =shoppingCartService.getById( customerCart.getId(), store );
		                    		return customerCart;
		    	                    //return populateShoppingCartData(customerCart,store,language);
	                    		} else {
	                    			return customerCart;
	                    			//return populateShoppingCartData(sessionShoppingCart,store,language);
	                    		}
	                    	} else {
	                    		//the saved cart belongs to another user
	                    		return null;
	                    	}
	                    }
	            	    
	                    
	              }
	            }
            }
            else{
                 if(customerCart !=null){
                     //return populateShoppingCartData(customerCart,store,language);
                     return customerCart;
                 }
                 return null;

            }
        }
        LOG.info( "Seems some issue with system, unable to find any customer after successful authentication" );
        return null;

    }





 	@Override
 	public Customer getCustomerByUserName(String userName, MerchantStore store)
		throws Exception {
 		return customerService.getByNick( userName, store.getId() );
 	}


   /**
    * <p>
    * Method to check if given user exists for given username under given store.
    * System treat username as unique for a given store, 
    * customer is not allowed
    * to use same username twice for a given store, however it can be used for 
    * different stores.</p>
    * 
    * @param userName Customer slected userName
    * @param store store for which customer want to register
    * @return boolean flag indicating if user exists for given store or not
    * @throws Exception 
    * 
    */
   @Override
    public boolean checkIfUserExists( final String userName, final MerchantStore store )
        throws Exception
    {
        if ( StringUtils.isNotBlank( userName ) && store != null )
        {
            Customer customer = customerService.getByNick( userName, store.getId() );
            if ( customer != null )
            {
                LOG.info( "Customer with userName {} already exists for store {} ", userName, store.getStorename() );
                return true;
            }
            
            LOG.info( "No customer found with userName {} for store {} ", userName, store.getStorename());
            return false;

        }
        LOG.info( "Either userName is empty or we have not found any value for store" );
        return false;
    }


    @Override
    public PersistableCustomer registerCustomer( final PersistableCustomer customer,final MerchantStore merchantStore, Language language )
        throws Exception
    {
        LOG.info( "Starting customer registration process.." );
        
        if(this.userExist(customer.getUserName())) {
        	throw new UserAlreadyExistException("User already exist");
        }
        
        Customer customerModel= getCustomerModel(customer,merchantStore,language);
        if(customerModel == null){
            LOG.equals( "Unable to create customer in system" );
            //throw new CustomerRegistrationException( "Unable to register customer" );
            throw new Exception( "Unable to register customer" );
        }
        
        LOG.info( "About to persist customer to database." );
        customerService.saveOrUpdate( customerModel );
        
       LOG.info( "Returning customer data to controller.." );
       //return customerEntityPoulator(customerModel,merchantStore);
       customer.setId(customerModel.getId());
       return customer;
     }
    
    @Override
    public Customer getCustomerModel(final PersistableCustomer customer,final MerchantStore merchantStore, Language language) throws Exception {
        
        LOG.info( "Starting to populate customer model from customer data" );
        Customer customerModel=null;
        CustomerPopulator populator = new CustomerPopulator();
        populator.setCountryService(countryService);
        populator.setCustomerOptionService(customerOptionService);
        populator.setCustomerOptionValueService(customerOptionValueService);
        populator.setLanguageService(languageService);
        populator.setLanguageService(languageService);
        populator.setZoneService(zoneService);
        populator.setGroupService(groupService);


            customerModel= populator.populate( customer, merchantStore, language );
            //we are creating or resetting a customer
            if(StringUtils.isBlank(customerModel.getPassword()) && !StringUtils.isBlank(customer.getClearPassword())) {
            	customerModel.setPassword(customer.getClearPassword());
            }
			//set groups
            if(!StringUtils.isBlank(customerModel.getPassword()) && !StringUtils.isBlank(customerModel.getNick())) {
            	customerModel.setPassword(passwordEncoder.encode(customer.getClearPassword()));
            	setCustomerModelDefaultProperties(customerModel, merchantStore);
            }
            

          return customerModel;

    }
    



	@Override
	public void setCustomerModelDefaultProperties(Customer customer,
			MerchantStore store) throws Exception {
		Validate.notNull(customer, "Customer object cannot be null");
		if(customer.getId()==null || customer.getId()==0) {
			if(StringUtils.isBlank(customer.getNick())) {
				String userName = UserReset.generateRandomString(USERNAME_LENGTH);
				customer.setNick(userName);
			}
			if(StringUtils.isBlank(customer.getPassword())) {
	        	String password = UserReset.generateRandomString();
	        	String encodedPassword = passwordEncoder.encode(password);
	        	customer.setPassword(encodedPassword);
			}
		}
		
		if(CollectionUtils.isEmpty(customer.getGroups())) {
			List<Group> groups = groupService.listGroup(GroupType.CUSTOMER);
			for(Group group : groups) {
				  if(group.getGroupName().equals(Constants.GROUP_CUSTOMER)) {
					  customer.getGroups().add(group);
				  }
			}
			
		}
		
	}



	
	@SuppressWarnings("deprecation")
	public void authenticate(Customer customer, String userName, String password) throws Exception {
		
			Validate.notNull(customer, "Customer cannot be null");

        	Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			GrantedAuthority role = new SimpleGrantedAuthority(ROLE_PREFIX + Constants.PERMISSION_CUSTOMER_AUTHENTICATED);//required to login
			authorities.add(role); 
			List<Integer> groupsId = new ArrayList<Integer>();
			List<Group> groups = customer.getGroups();
			if(groups!=null) {
				for(Group group : groups) {
					groupsId.add(group.getId());
					
				}
				if(groupsId!=null && groupsId.size()>0) {
			    	List<Permission> permissions = permissionService.getPermissions(groupsId);
			    	for(Permission permission : permissions) {
			    		GrantedAuthority auth = new SimpleGrantedAuthority(permission.getPermissionName());
			    		authorities.add(auth);
			    	}
				}
			}

			Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, password, authorities);
    	
			Authentication authentication = customerAuthenticationManager.authenticate(authenticationToken);
    
			SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}


	@Override
    public Address getAddress( Long userId, final MerchantStore merchantStore,boolean isBillingAddress)
        throws Exception
    {
	     LOG.info( "Fetching customer for id {} ", userId);
        Address address=null;
        final Customer customerModel=customerService.getById( userId );
        
        if(customerModel == null){
            LOG.error( "Customer with ID {} does not exists..", userId);
            //throw new CustomerNotFoundException( "customer with given id does not exists" );
            throw new Exception( "customer with given id does not exists" );
        }
        
       if(isBillingAddress){
            LOG.info( "getting billing address.." );
            CustomerBillingAddressPopulator billingAddressPopulator=new CustomerBillingAddressPopulator();
            address =billingAddressPopulator.populate( customerModel, merchantStore, merchantStore.getDefaultLanguage() );
            address.setBillingAddress( true );
            return address;
        }
        
        LOG.info( "getting Delivery address.." );
        CustomerDeliveryAddressPopulator deliveryAddressPopulator=new CustomerDeliveryAddressPopulator();
        return deliveryAddressPopulator.populate( customerModel, merchantStore, merchantStore.getDefaultLanguage() );
    
    }


    @Override
    public void updateAddress( Long userId, MerchantStore merchantStore, Address address, final Language language )
        throws Exception
    {
       
     Customer customerModel=customerService.getById( userId );
       Map<String, Country> countriesMap = countryService.getCountriesMap( language );
       Country country = countriesMap.get( address.getCountry() );
      
      if(customerModel ==null){
           LOG.error( "Customer with ID {} does not exists..", userId);
           //throw new CustomerNotFoundException( "customer with given id does not exists" );
           throw new Exception( "customer with given id does not exists" );
           
       }
       if(address.isBillingAddress()){
           LOG.info( "updating customer billing address..");
           PersistableCustomerBillingAddressPopulator billingAddressPopulator=new PersistableCustomerBillingAddressPopulator();
           customerModel= billingAddressPopulator.populate( address, customerModel, merchantStore, merchantStore.getDefaultLanguage() );
           customerModel.getBilling().setCountry( country );
           if(StringUtils.isNotBlank( address.getZone() )){
               Zone zone = zoneService.getByCode(address.getZone());
               if(zone==null) {
                  throw new ConversionException("Unsuported zone code " + address.getZone());
               }
                   customerModel.getBilling().setZone( zone );
                   customerModel.getBilling().setState(null);
               
           } else {
        	   customerModel.getBilling().setZone(null);
           }
          
       }
       else{
           LOG.info( "updating customer shipping address..");
           PersistableCustomerShippingAddressPopulator shippingAddressPopulator=new PersistableCustomerShippingAddressPopulator();
           customerModel= shippingAddressPopulator.populate( address, customerModel, merchantStore, merchantStore.getDefaultLanguage() );
           customerModel.getDelivery().setCountry( country );
           if(StringUtils.isNotBlank( address.getZone() )){
               Zone zone = zoneService.getByCode(address.getZone());
               if(zone==null) {
                   throw new ConversionException("Unsuported zone code " + address.getZone());
               }

               customerModel.getDelivery().setZone( zone );
               customerModel.getDelivery().setState(null);
              
           } else {
        	   customerModel.getDelivery().setZone(null);
           }
           
       }
  
     
      // same update address with customer model
       this.customerService.saveOrUpdate( customerModel );
       
    }
    
	@Override
	public ReadableCustomer getCustomerById(final Long id, final MerchantStore merchantStore, final Language language) throws Exception {
		Customer customerModel = customerService.getById(id);
		if(customerModel==null) {
			return null;
		}
		
		ReadableCustomer readableCustomer = new ReadableCustomer();
		
		ReadableCustomerPopulator customerPopulator = new ReadableCustomerPopulator();
		customerPopulator.populate(customerModel,readableCustomer, merchantStore, language);
		
		return readableCustomer;
	}


	@Override
	public Customer populateCustomerModel(Customer customerModel,
			PersistableCustomer customer, MerchantStore merchantStore,
			Language language) throws Exception {
        LOG.info( "Starting to populate customer model from customer data" );
        CustomerPopulator populator = new CustomerPopulator();
        populator.setCountryService(countryService);
        populator.setCustomerOptionService(customerOptionService);
        populator.setCustomerOptionValueService(customerOptionValueService);
        populator.setLanguageService(languageService);
        populator.setLanguageService(languageService);
        populator.setGroupService(groupService);
        populator.setZoneService(zoneService);
        populator.setGroupService(groupService);


            customerModel= populator.populate( customer, customerModel, merchantStore, language );

            LOG.info( "About to persist customer to database." );
            customerService.saveOrUpdate( customerModel );
            return customerModel;
	}


	@Override
	public void create(PersistableCustomer customer, MerchantStore store) throws Exception {
		
		if(this.userExist(customer.getUserName())) {
			throw new UserAlreadyExistException("User already exist");
		}
		
		Customer c = this.populate(customer, store);
		
		customerService.save(c);
		customer.setId(c.getId());
		
		this.notifyNewCustomer(customer, store, c.getDefaultLanguage());
		
		/**
		 * For security reasons set empty passwords
		 */
		//customer.setEncodedPassword(null);
		//customer.setClearPassword(null);
		
	}
	
	private boolean userExist(String userName) throws Exception {
		boolean exist = false;
		Customer customer = customerService.getByNick(userName);
		if(customer != null) {
			exist = true;
		}
		return exist;
	}
	
	private Customer populate(PersistableCustomer customer, MerchantStore store) throws Exception {
		
		Customer cust = new Customer();
		
		CustomerPopulator populator = new CustomerPopulator();
		populator.setCountryService(countryService);
		populator.setCustomerOptionService(customerOptionService);
		populator.setCustomerOptionValueService(customerOptionValueService);
		populator.setLanguageService(languageService);
		populator.setLanguageService(languageService);
		populator.setZoneService(zoneService);
		populator.setGroupService(groupService);
		populator.populate(customer, cust, store, store.getDefaultLanguage());
		
		List<Group> groups = groupService.listGroup(GroupType.CUSTOMER);
		cust.setGroups(groups);

		Locale customerLocale = LocaleUtils.getLocale(cust.getDefaultLanguage());
		
		String password = customer.getClearPassword();
		if(StringUtils.isBlank(password)) {
			password = UserReset.generateRandomString();
			customer.setClearPassword(password);
		}

		@SuppressWarnings("deprecation")
		String encodedPassword = passwordEncoder.encode(password);
		if(!StringUtils.isBlank(customer.getEncodedPassword())) {
			encodedPassword = customer.getEncodedPassword();
			//customer.setClearPassword("");
		}
		
		cust.setPassword(encodedPassword);

		return cust;
		
	}
	
	private void notifyNewCustomer(PersistableCustomer customer, MerchantStore store, Language lang) throws Exception {
		
		
		Locale customerLocale = LocaleUtils.getLocale(lang);
		emailTemplatesUtils.sendRegistrationEmail(customer, store, customerLocale, (String)coreConfiguration.getProperty("SHOP_SCHEME"));

		
	}


	@Override
	public void update(PersistableCustomer customer, MerchantStore store) throws Exception {
		
		
		if(customer.getId() == null || customer.getId().longValue() == 0) {
			throw new Exception("Can't update a customer with null id");
		}
		
		Customer cust = customerService.getById(customer.getId());

		
		CustomerPopulator populator = new CustomerPopulator();
		populator.setCountryService(countryService);
		populator.setCustomerOptionService(customerOptionService);
		populator.setCustomerOptionValueService(customerOptionValueService);
		populator.setLanguageService(languageService);
		populator.setLanguageService(languageService);
		populator.setZoneService(zoneService);
		populator.setGroupService(groupService);
		populator.populate(customer, cust, store, store.getDefaultLanguage());
		

		String password = customer.getClearPassword();
		if(StringUtils.isBlank(password)) {
			password = UserReset.generateRandomString();
			customer.setClearPassword(password);
		}

		@SuppressWarnings("deprecation")
		String encodedPassword = passwordEncoder.encode(password);
		if(!StringUtils.isBlank(customer.getEncodedPassword())) {
			encodedPassword = customer.getEncodedPassword();
			customer.setClearPassword("");
		}
		
		customer.setEncodedPassword(encodedPassword);
		customerService.save(cust);
		customer.setId(cust.getId());

		
	}


	@Override
	public void saveOrUpdateCustomerReview(PersistableCustomerReview review, MerchantStore store, Language language)
			throws Exception {
		CustomerReview rev = new CustomerReview();
		
		PersistableCustomerReviewPopulator populator = new PersistableCustomerReviewPopulator();
		populator.setCustomerService(customerService);
		populator.setLanguageService(languageService);
		populator.populate(review, rev, store, language);
		
		customerReviewService.create(rev);
		
		review.setId(rev.getId());
		
	}


	@Override
	public List<ReadableCustomerReview> getAllCustomerReviewsByReviewed(Customer customer, MerchantStore store,
			Language language) throws Exception {
		Validate.notNull(customer,"Reviewed customer cannot be null");
		
		List<CustomerReview> reviews = customerReviewService.getByReviewedCustomer(customer);
		
		ReadableCustomerReviewPopulator populator = new ReadableCustomerReviewPopulator();
		
		
		List<ReadableCustomerReview> customerReviews = new ArrayList<ReadableCustomerReview>();
		
		for(CustomerReview review : reviews) {
			ReadableCustomerReview readableReview = new ReadableCustomerReview();
			populator.populate(review, readableReview, store, language);
			customerReviews.add(readableReview);
		}
		
		
		
		return customerReviews;
	}


	@Override
	public void deleteCustomerReview(CustomerReview review, MerchantStore store, Language language) throws Exception {
		customerReviewService.delete(review);
		
	}

}
