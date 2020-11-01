/**
 *
 */
package com.salesmanager.shop.store.controller.customer.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.email.Email;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.customer.optin.CustomerOptinService;
import com.salesmanager.core.business.services.customer.review.CustomerReviewService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartService;
import com.salesmanager.core.business.services.system.EmailService;
import com.salesmanager.core.business.services.system.optin.OptinService;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.services.user.PermissionService;
import com.salesmanager.core.business.utils.CoreConfiguration;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.CustomerCriteria;
import com.salesmanager.core.model.customer.CustomerList;
import com.salesmanager.core.model.customer.review.CustomerReview;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.core.model.system.optin.CustomerOptin;
import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.core.model.system.optin.OptinType;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.GroupType;
import com.salesmanager.core.model.user.Permission;
import com.salesmanager.shop.admin.model.userpassword.UserReset;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.constants.EmailConstants;
import com.salesmanager.shop.model.customer.CustomerEntity;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.customer.PersistableCustomerReview;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.model.customer.ReadableCustomerReview;
import com.salesmanager.shop.model.customer.UserAlreadyExistException;
import com.salesmanager.shop.model.customer.address.Address;
import com.salesmanager.shop.model.customer.optin.PersistableCustomerOptin;
import com.salesmanager.shop.populator.customer.CustomerBillingAddressPopulator;
import com.salesmanager.shop.populator.customer.CustomerDeliveryAddressPopulator;
import com.salesmanager.shop.populator.customer.CustomerEntityPopulator;
import com.salesmanager.shop.populator.customer.CustomerPopulator;
import com.salesmanager.shop.populator.customer.PersistableCustomerBillingAddressPopulator;
import com.salesmanager.shop.populator.customer.PersistableCustomerReviewPopulator;
import com.salesmanager.shop.populator.customer.PersistableCustomerShippingAddressPopulator;
import com.salesmanager.shop.populator.customer.ReadableCustomerList;
import com.salesmanager.shop.populator.customer.ReadableCustomerPopulator;
import com.salesmanager.shop.populator.customer.ReadableCustomerReviewPopulator;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.utils.EmailTemplatesUtils;
import com.salesmanager.shop.utils.EmailUtils;
import com.salesmanager.shop.utils.ImageFilePath;
import com.salesmanager.shop.utils.LabelUtils;
import com.salesmanager.shop.utils.LocaleUtils;


/**
 * Customer Facade work as an abstraction layer between Controller and Service layer. It work as an
 * entry point to service layer.
 * 
 * @author Umesh Awasthi
 * @version 2.2.1, 2.8.0
 * @modified Carl Samson
 *
 */

@Service("customerFacade")
public class CustomerFacadeImpl implements CustomerFacade {

  private static final Logger LOG = LoggerFactory.getLogger(CustomerFacadeImpl.class);
  private final static int USERNAME_LENGTH = 6;

  private final static String RESET_PASSWORD_TPL = "email_template_password_reset_customer.ftl";

  public final static String ROLE_PREFIX = "ROLE_";// Spring Security 4


  @Inject
  private CustomerService customerService;

  @Inject
  private OptinService optinService;

  @Inject
  private CustomerOptinService customerOptinService;

  @Inject
  private ShoppingCartService shoppingCartService;

  @Inject
  private LanguageService languageService;

  @Inject
  private LabelUtils messages;

  @Inject
  private CountryService countryService;

  @Inject
  private GroupService groupService;

  @Inject
  private PermissionService permissionService;

  @Inject
  private ZoneService zoneService;

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
  
  @Autowired
  private CustomerPopulator customerPopulator;

  @Inject
  private EmailUtils emailUtils;

  @Inject
  @Qualifier("img")
  private ImageFilePath imageUtils;

  /**
   * Method used to fetch customer based on the username and storecode. Customer username is unique
   * to each store.
   *
   * @param userName
   * @param store
   * @throws ConversionException
   */
  @Override
  public CustomerEntity getCustomerDataByUserName(final String userName, final MerchantStore store,
      final Language language) throws Exception {
    LOG.info("Fetching customer with userName" + userName);
    Customer customer = customerService.getByNick(userName);

    if (customer != null) {
      LOG.info("Found customer, converting to CustomerEntity");
      try {
        CustomerEntityPopulator customerEntityPopulator = new CustomerEntityPopulator();
        return customerEntityPopulator.populate(customer, store, language); // store, language

      } catch (ConversionException ex) {
        LOG.error("Error while converting Customer to CustomerEntity", ex);
        throw new Exception(ex);
      }
    }

    return null;

  }


  /*
   * (non-Javadoc)
   * 
   * @see com.salesmanager.web.shop.controller.customer.facade#mergeCart(final Customer
   * customerModel, final String sessionShoppingCartId ,final MerchantStore store,final Language
   * language)
   */
  @Override
  public ShoppingCart mergeCart(final Customer customerModel, final String sessionShoppingCartId,
      final MerchantStore store, final Language language) throws Exception {

    LOG.debug("Starting merge cart process");
    if (customerModel != null) {
      ShoppingCart customerCart = shoppingCartService.getShoppingCart(customerModel);
      if (StringUtils.isNotBlank(sessionShoppingCartId)) {
        ShoppingCart sessionShoppingCart =
            shoppingCartService.getByCode(sessionShoppingCartId, store);
        if (sessionShoppingCart != null) {
          if (customerCart == null) {
            if (sessionShoppingCart.getCustomerId() == null) {// saved shopping cart does not belong
                                                              // to a customer
              LOG.debug("Not able to find any shoppingCart with current customer");
              // give it to the customer
              sessionShoppingCart.setCustomerId(customerModel.getId());
              shoppingCartService.saveOrUpdate(sessionShoppingCart);
              customerCart = shoppingCartService.getById(sessionShoppingCart.getId(), store);
              return customerCart;
              // return populateShoppingCartData(customerCart,store,language);
            } else {
              return null;
            }
          } else {
            if (sessionShoppingCart.getCustomerId() == null) {// saved shopping cart does not belong
                                                              // to a customer
              // assign it to logged in user
              LOG.debug("Customer shopping cart as well session cart is available, merging carts");
              customerCart =
                  shoppingCartService.mergeShoppingCarts(customerCart, sessionShoppingCart, store);
              customerCart = shoppingCartService.getById(customerCart.getId(), store);
              return customerCart;
              // return populateShoppingCartData(customerCart,store,language);
            } else {
              if (sessionShoppingCart.getCustomerId().longValue() == customerModel.getId()
                  .longValue()) {
                if (!customerCart.getShoppingCartCode()
                    .equals(sessionShoppingCart.getShoppingCartCode())) {
                  // merge carts
                  LOG.info("Customer shopping cart as well session cart is available");
                  customerCart = shoppingCartService.mergeShoppingCarts(customerCart,
                      sessionShoppingCart, store);
                  customerCart = shoppingCartService.getById(customerCart.getId(), store);
                  return customerCart;
                  // return populateShoppingCartData(customerCart,store,language);
                } else {
                  return customerCart;
                  // return populateShoppingCartData(sessionShoppingCart,store,language);
                }
              } else {
                // the saved cart belongs to another user
                return null;
              }
            }


          }
        }
      } else {
        if (customerCart != null) {
          // return populateShoppingCartData(customerCart,store,language);
          return customerCart;
        }
        return null;

      }
    }
    LOG.info(
        "Seems some issue with system, unable to find any customer after successful authentication");
    return null;

  }



  @Override
  public Customer getCustomerByUserName(String userName, MerchantStore store) throws Exception {
    return customerService.getByNick(userName, store.getId());
  }
  
  @Override
  public ReadableCustomer getByUserName(String userName, MerchantStore merchantStore, Language language) {
    Validate.notNull(userName,"Username cannot be null");
    Validate.notNull(merchantStore,"MerchantStore cannot be null");

    Customer customerModel = getCustomerByNickAndStoreId(userName, merchantStore);
    return convertCustomerToReadableCustomer(customerModel, merchantStore, language);
  }

  private Customer getCustomerByNickAndStoreId(String userName, MerchantStore merchantStore) {
    return Optional.ofNullable(customerService.getByNick(userName, merchantStore.getId()))
        .orElseThrow(() -> new ResourceNotFoundException("No Customer found for ID : " + userName));
  }


  /**
   * <p>
   * Method to check if given user exists for given username under given store. System treat
   * username as unique for a given store, customer is not allowed to use same username twice for a
   * given store, however it can be used for different stores.
   * </p>
   * 
   * @param userName Customer slected userName
   * @param store store for which customer want to register
   * @return boolean flag indicating if user exists for given store or not
   * @throws Exception
   * 
   */
  @Override
  public boolean checkIfUserExists(final String userName, final MerchantStore store)
      throws Exception {
    if (StringUtils.isNotBlank(userName) && store != null) {
      Customer customer = customerService.getByNick(userName, store.getId());
      if (customer != null) {
        LOG.info("Customer with userName {} already exists for store {} ", userName,
            store.getStorename());
        return true;
      }

      LOG.info("No customer found with userName {} for store {} ", userName, store.getStorename());
      return false;

    }
    LOG.info("Either userName is empty or we have not found any value for store");
    return false;
  }


  @Override
  public PersistableCustomer registerCustomer(final PersistableCustomer customer,
      final MerchantStore merchantStore, Language language) throws Exception {
    LOG.info("Starting customer registration process..");

    if (userExist(customer.getUserName())) {
      throw new UserAlreadyExistException("User already exist");
    }

    Customer customerModel = getCustomerModel(customer, merchantStore, language);
    if (customerModel == null) {
      LOG.equals("Unable to create customer in system");
      // throw new CustomerRegistrationException( "Unable to register customer" );
      throw new Exception("Unable to register customer");
    }

    LOG.info("About to persist customer to database.");
    customerService.saveOrUpdate(customerModel);

    LOG.info("Returning customer data to controller..");
    // return customerEntityPoulator(customerModel,merchantStore);
    customer.setId(customerModel.getId());
    return customer;
  }

  @Override
  public Customer getCustomerModel(final PersistableCustomer customer,
      final MerchantStore merchantStore, Language language) throws Exception {

    LOG.info("Starting to populate customer model from customer data");
    Customer customerModel = null;

    customerModel = customerPopulator.populate(customer, merchantStore, language);
    // we are creating or resetting a customer
    if (StringUtils.isBlank(customerModel.getPassword())
        && !StringUtils.isBlank(customer.getPassword())) {
      customerModel.setPassword(customer.getPassword());
    }
    // set groups
    if (!StringUtils.isBlank(customerModel.getPassword())
        && !StringUtils.isBlank(customerModel.getNick())) {
      customerModel.setPassword(passwordEncoder.encode(customer.getPassword()));
      setCustomerModelDefaultProperties(customerModel, merchantStore);
    }


    return customerModel;

  }



  @Override
  public void setCustomerModelDefaultProperties(Customer customer, MerchantStore store)
      throws Exception {
    Validate.notNull(customer, "Customer object cannot be null");
    if (customer.getId() == null || customer.getId() == 0) {
      if (StringUtils.isBlank(customer.getNick())) {
        String userName = UserReset.generateRandomString(USERNAME_LENGTH);
        customer.setNick(userName);
      }
      if (StringUtils.isBlank(customer.getPassword())) {
        String password = UserReset.generateRandomString();
        String encodedPassword = passwordEncoder.encode(password);
        customer.setPassword(encodedPassword);
      }
    }

    if (CollectionUtils.isEmpty(customer.getGroups())) {
      List<Group> groups = getListOfGroups(GroupType.CUSTOMER);
      for (Group group : groups) {
        if (group.getGroupName().equals(Constants.GROUP_CUSTOMER)) {
          customer.getGroups().add(group);
        }
      }

    }

  }



  public void authenticate(Customer customer, String userName, String password) throws Exception {

    Validate.notNull(customer, "Customer cannot be null");

    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    GrantedAuthority role =
        new SimpleGrantedAuthority(ROLE_PREFIX + Constants.PERMISSION_CUSTOMER_AUTHENTICATED);// required
                                                                                              // to
                                                                                              // login
    authorities.add(role);
    List<Integer> groupsId = new ArrayList<Integer>();
    List<Group> groups = customer.getGroups();
    if (groups != null) {
      for (Group group : groups) {
        groupsId.add(group.getId());

      }
      if (groupsId != null && groupsId.size() > 0) {
        List<Permission> permissions = permissionService.getPermissions(groupsId);
        for (Permission permission : permissions) {
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
  public Address getAddress(Long userId, final MerchantStore merchantStore,
      boolean isBillingAddress) throws Exception {
    LOG.info("Fetching customer for id {} ", userId);
    Address address = null;
    final Customer customerModel = customerService.getById(userId);

    if (customerModel == null) {
      LOG.error("Customer with ID {} does not exists..", userId);
      // throw new CustomerNotFoundException( "customer with given id does not exists" );
      throw new Exception("customer with given id does not exists");
    }

    if (isBillingAddress) {
      LOG.info("getting billing address..");
      CustomerBillingAddressPopulator billingAddressPopulator =
          new CustomerBillingAddressPopulator();
      address = billingAddressPopulator.populate(customerModel, merchantStore,
          merchantStore.getDefaultLanguage());
      address.setBillingAddress(true);
      return address;
    }

    LOG.info("getting Delivery address..");
    CustomerDeliveryAddressPopulator deliveryAddressPopulator =
        new CustomerDeliveryAddressPopulator();
    return deliveryAddressPopulator.populate(customerModel, merchantStore,
        merchantStore.getDefaultLanguage());

  }


  @Override
  public void updateAddress(Long userId, MerchantStore merchantStore, Address address,
      final Language language) throws Exception {

    Customer customerModel = customerService.getById(userId);
    Map<String, Country> countriesMap = countryService.getCountriesMap(language);
    Country country = countriesMap.get(address.getCountry());

    if (customerModel == null) {
      LOG.error("Customer with ID {} does not exists..", userId);
      // throw new CustomerNotFoundException( "customer with given id does not exists" );
      throw new Exception("customer with given id does not exists");

    }
    if (address.isBillingAddress()) {
      LOG.info("updating customer billing address..");
      PersistableCustomerBillingAddressPopulator billingAddressPopulator =
          new PersistableCustomerBillingAddressPopulator();
      customerModel = billingAddressPopulator.populate(address, customerModel, merchantStore,
          merchantStore.getDefaultLanguage());
      customerModel.getBilling().setCountry(country);
      if (StringUtils.isNotBlank(address.getZone())) {
        Zone zone = zoneService.getByCode(address.getZone());
        if (zone == null) {
          throw new ConversionException("Unsuported zone code " + address.getZone());
        }
        customerModel.getBilling().setZone(zone);
        customerModel.getBilling().setState(null);

      } else {
        customerModel.getBilling().setZone(null);
      }

    } else {
      LOG.info("updating customer shipping address..");
      PersistableCustomerShippingAddressPopulator shippingAddressPopulator =
          new PersistableCustomerShippingAddressPopulator();
      customerModel = shippingAddressPopulator.populate(address, customerModel, merchantStore,
          merchantStore.getDefaultLanguage());
      customerModel.getDelivery().setCountry(country);
      if (StringUtils.isNotBlank(address.getZone())) {
        Zone zone = zoneService.getByCode(address.getZone());
        if (zone == null) {
          throw new ConversionException("Unsuported zone code " + address.getZone());
        }

        customerModel.getDelivery().setZone(zone);
        customerModel.getDelivery().setState(null);

      } else {
        customerModel.getDelivery().setZone(null);
      }

    }


    // same update address with customer model
    this.customerService.saveOrUpdate(customerModel);

  }

  @Override
  public ReadableCustomer getCustomerById(final Long id, final MerchantStore merchantStore,
      final Language language) {

    Customer customerModel = Optional.ofNullable(customerService.getById(id))
        .orElseThrow(() -> new ResourceNotFoundException("No Customer found for ID : " + id));

    return convertCustomerToReadableCustomer(customerModel, merchantStore, language);
  }


  @Override
  public Customer populateCustomerModel(Customer customerModel, PersistableCustomer customer,
      MerchantStore merchantStore, Language language) throws Exception {
      LOG.info("Starting to populate customer model from customer data");


    customerModel = customerPopulator.populate(customer, customerModel, merchantStore, language);

    LOG.info("About to persist customer to database.");
    customerService.saveOrUpdate(customerModel);
    return customerModel;
  }


  @Override
  public ReadableCustomer create(PersistableCustomer customer, MerchantStore store, Language language) {

	Validate.notNull(customer, "Customer cannot be null");
	Validate.notNull(customer.getEmailAddress(), "Customer email address is required");

	//set customer user name
	customer.setUserName(customer.getEmailAddress());
    if (userExist(customer.getUserName())) {
      throw new ServiceRuntimeException("User already exist");
    }
    //end user exists

    Customer customerToPopulate = convertPersistableCustomerToCustomer(customer, store);
    try {
		setCustomerModelDefaultProperties(customerToPopulate, store);
	} catch (Exception e) {
		throw new ServiceRuntimeException("Cannot set default customer properties",e);
	}
    saveCustomer(customerToPopulate);
    customer.setId(customerToPopulate.getId());

    notifyNewCustomer(customer, store, customerToPopulate.getDefaultLanguage());
    //convert to readable
    return convertCustomerToReadableCustomer(customerToPopulate, store, language);
    

  }

  private void saveCustomer(Customer customerToPopulate) {
    try{
      customerService.save(customerToPopulate);
    } catch (ServiceException exception) {
      throw new ServiceRuntimeException(exception);
    }

  }

  private boolean userExist(String userName) {
    return Optional.ofNullable(customerService.getByNick(userName))
        .isPresent();
  }

  private List<Group> getListOfGroups(GroupType groupType) {
    try{
      return groupService.listGroup(groupType);
    } catch (ServiceException e) {
      throw new ServiceRuntimeException(e);
    }

  }

  private Customer convertPersistableCustomerToCustomer(PersistableCustomer customer, MerchantStore store) {

    Customer cust = new Customer();

    try{
      customerPopulator.populate(customer, cust, store, store.getDefaultLanguage());
    } catch (ConversionException e) {
      throw new ConversionRuntimeException(e);
    }


    List<Group> groups = getListOfGroups(GroupType.CUSTOMER);
    cust.setGroups(groups);

    String password = customer.getPassword();
    if (StringUtils.isBlank(password)) {
      password = UserReset.generateRandomString();
      customer.setPassword(password);
    }


    return cust;

  }

  @Async
  private void notifyNewCustomer(PersistableCustomer customer, MerchantStore store, Language lang) {
		System.out.println("Customer notification");
		long startTime = System.nanoTime();
	Locale customerLocale = LocaleUtils.getLocale(lang);
    String shopSchema = coreConfiguration.getProperty("SHOP_SCHEME");
    emailTemplatesUtils.sendRegistrationEmail(customer, store, customerLocale, shopSchema);
    long endTime = System.nanoTime();
    long duration = (endTime - startTime)/1000;
    System.out.println("End Notification " + duration);
  }


  @Override
  public PersistableCustomer update(PersistableCustomer customer, MerchantStore store) {

    if (customer.getId() == null || customer.getId() == 0) {
      throw new ServiceRuntimeException("Can't update a customer with null id");
    }

    Customer cust = customerService.getById(customer.getId());

    try{
      customerPopulator.populate(customer, cust, store, store.getDefaultLanguage());
    } catch (ConversionException e) {
      throw new ConversionRuntimeException(e);
    }

    String password = customer.getPassword();
    if (StringUtils.isBlank(password)) {
      password = UserReset.generateRandomString();
      customer.setPassword(password);
    }

    saveCustomer(cust);
    customer.setId(cust.getId());

    return customer;
  }


  @Override
  public PersistableCustomerReview saveOrUpdateCustomerReview(PersistableCustomerReview reviewTO, MerchantStore store,
      Language language) {
    CustomerReview review = convertPersistableCustomerReviewToCustomerReview(reviewTO, store, language);
    createReview(review);
    reviewTO.setId(review.getId());
    return reviewTO;
  }

  private void createReview(CustomerReview review) {
    try{
      customerReviewService.create(review);
    } catch (ServiceException e) {
      throw new ServiceRuntimeException(e);
    }

  }

  private CustomerReview convertPersistableCustomerReviewToCustomerReview(
      PersistableCustomerReview review, MerchantStore store, Language language) {
    PersistableCustomerReviewPopulator populator = new PersistableCustomerReviewPopulator();
    populator.setCustomerService(customerService);
    populator.setLanguageService(languageService);
    try{
      return populator.populate(review, new CustomerReview(), store, language);
    } catch (ConversionException e) {
      throw new ConversionRuntimeException(e);
    }
  }


  @Override
  public List<ReadableCustomerReview> getAllCustomerReviewsByReviewed(Long customerId,
      MerchantStore store, Language language) {

    //customer exist
    Customer customer = getCustomerById(customerId);
    Validate.notNull(customer, "Reviewed customer cannot be null");

    return customerReviewService.getByReviewedCustomer(customer)
        .stream()
        .map(
            customerReview ->
                convertCustomerReviewToReadableCustomerReview(customerReview, store, language))
        .collect(Collectors.toList());
  }

  private ReadableCustomerReview convertCustomerReviewToReadableCustomerReview(
      CustomerReview customerReview, MerchantStore store, Language language) {
    try{
      ReadableCustomerReviewPopulator populator = new ReadableCustomerReviewPopulator();
      return populator.populate(customerReview, new ReadableCustomerReview(), store, language);
    } catch (ConversionException e){
      throw new ConversionRuntimeException(e);
    }
  }

  private Customer getCustomerById(Long customerId) {
    return Optional.ofNullable(customerService.getById(customerId))
          .orElseThrow(() -> new ResourceNotFoundException("Customer id " + customerId + " does not exists"));
  }


  @Override
  public void deleteCustomerReview(Long customerId, Long reviewId, MerchantStore store, Language language) {

    CustomerReview customerReview = getCustomerReviewById(reviewId);

    if(!customerReview.getReviewedCustomer().getId().equals(customerId)) {
      throw new ResourceNotFoundException("Customer review with id " + reviewId + " does not exist for this customer");
    }
    deleteCustomerReview(customerReview);
  }

  private CustomerReview getCustomerReviewById(Long reviewId) {
    return Optional.ofNullable(customerReviewService.getById(reviewId))
        .orElseThrow(() -> new ResourceNotFoundException("Customer review with id " + reviewId + " does not exist"));
  }

  private void deleteCustomerReview(CustomerReview review) {
    try{
      customerReviewService.delete(review);
    } catch (ServiceException e) {
      throw new ServiceRuntimeException(e);
    }
  }


  @Override
  public void optinCustomer(PersistableCustomerOptin optin, MerchantStore store) {
    // check if customer optin exists
    Optin optinDef = getOptinByCode(store);

    CustomerOptin customerOptin = getCustomerOptinByEmailAddress(optin.getEmail(), store, OptinType.NEWSLETTER);

    if (customerOptin != null) {
      // exists update
      customerOptin.setEmail(optin.getEmail());
      customerOptin.setFirstName(optin.getFirstName());
      customerOptin.setLastName(optin.getLastName());
    } else {
      customerOptin = new com.salesmanager.core.model.system.optin.CustomerOptin();
      customerOptin.setEmail(optin.getEmail());
      customerOptin.setFirstName(optin.getFirstName());
      customerOptin.setLastName(optin.getLastName());
      customerOptin.setOptinDate(new Date());
      customerOptin.setOptin(optinDef);
      customerOptin.setMerchantStore(store);
    }
    saveCustomerOption(customerOptin);
  }

  private void saveCustomerOption(CustomerOptin customerOptin) {
    try {
      customerOptinService.save(customerOptin);
    } catch (ServiceException e) {
      throw new ServiceRuntimeException(e);
    }
  }

  private Optin getOptinByCode(MerchantStore store) {
    try{
      return Optional.ofNullable(optinService.getOptinByCode(store, OptinType.NEWSLETTER.name()))
          .orElseThrow(() -> new ResourceNotFoundException("Optin newsletter does not exist"));
    } catch (ServiceException e){
      throw new ServiceRuntimeException(e);
    }
  }

  private CustomerOptin getCustomerOptinByEmailAddress(String optinEmail,
      MerchantStore store, OptinType optinType) {
    try{
      return customerOptinService.findByEmailAddress(store, optinEmail, optinType.name());
    } catch (ServiceException e){
      throw new ServiceRuntimeException(e);
    }

  }


  @Override
  public void resetPassword(Customer customer, MerchantStore store, Language language)
      throws Exception {


    String password = UserReset.generateRandomString();
    String encodedPassword = passwordEncoder.encode(password);

    customer.setPassword(encodedPassword);
    customerService.saveOrUpdate(customer);

    Locale locale = languageService.toLocale(language, store);

    // send email

    try {

      // creation of a user, send an email
      String[] storeEmail = {store.getStoreEmailAddress()};


      Map<String, String> templateTokens =
          emailUtils.createEmailObjectsMap(imageUtils.getContextPath(), store, messages, locale);
      templateTokens.put(EmailConstants.LABEL_HI, messages.getMessage("label.generic.hi", locale));
      templateTokens.put(EmailConstants.EMAIL_CUSTOMER_FIRSTNAME,
          customer.getBilling().getFirstName());
      templateTokens.put(EmailConstants.EMAIL_CUSTOMER_LASTNAME,
          customer.getBilling().getLastName());
      templateTokens.put(EmailConstants.EMAIL_RESET_PASSWORD_TXT,
          messages.getMessage("email.customer.resetpassword.text", locale));
      templateTokens.put(EmailConstants.EMAIL_CONTACT_OWNER,
          messages.getMessage("email.contactowner", storeEmail, locale));
      templateTokens.put(EmailConstants.EMAIL_PASSWORD_LABEL,
          messages.getMessage("label.generic.password", locale));
      templateTokens.put(EmailConstants.EMAIL_CUSTOMER_PASSWORD, password);


      Email email = new Email();
      email.setFrom(store.getStorename());
      email.setFromEmail(store.getStoreEmailAddress());
      email.setSubject(messages.getMessage("label.generic.changepassword", locale));
      email.setTo(customer.getEmailAddress());
      email.setTemplateName(RESET_PASSWORD_TPL);
      email.setTemplateTokens(templateTokens);



      emailService.sendHtmlEmail(store, email);

    } catch (Exception e) {
      LOG.error("Cannot send email to customer", e);
    }


  }

  @Override
  public ReadableCustomer getCustomerByNick(String userName, MerchantStore merchantStore,
      Language language) {
    Customer customer = getByNick(userName);
    return convertCustomerToReadableCustomer(customer, merchantStore, language);
  }

  @Override
  public void deleteByNick(String userName) {
    Customer customer = getByNick(userName);
    delete(customer);
  }

  private Customer getByNick(String userName) {
    return Optional.ofNullable(customerService.getByNick(userName))
        .orElseThrow(() -> new ResourceNotFoundException("No Customer found for ID : " + userName));
  }

  @Override
  public void delete(Customer entity) {
    try{
      customerService.delete(entity);
    } catch (ServiceException e) {
      throw new ServiceRuntimeException(e);
    }
  }

  @Override
  public ReadableCustomerList getListByStore(MerchantStore store, CustomerCriteria criteria,
      Language language) {
    CustomerList customerList = customerService.getListByStore(store, criteria);
    return convertCustomerListToReadableCustomerList(customerList, store, language);
  }

  private ReadableCustomerList convertCustomerListToReadableCustomerList(
      CustomerList customerList, MerchantStore store, Language language) {
    List<ReadableCustomer> readableCustomers = customerList.getCustomers()
        .stream()
        .map(customer -> convertCustomerToReadableCustomer(customer, store, language))
        .collect(Collectors.toList());

    ReadableCustomerList readableCustomerList = new ReadableCustomerList();
    readableCustomerList.setCustomers(readableCustomers);
    readableCustomerList.setTotalPages(customerList.getTotalCount());
    return readableCustomerList;
  }

  private ReadableCustomer convertCustomerToReadableCustomer(Customer customer, MerchantStore merchantStore, Language language) {
    ReadableCustomerPopulator populator = new ReadableCustomerPopulator();
    try{
      return populator.populate(customer, new ReadableCustomer(), merchantStore, language);
    } catch (ConversionException e) {
      throw new ConversionRuntimeException(e);
    }
  }

  @Override
  public PersistableCustomerReview createCustomerReview(
      Long customerId,
      PersistableCustomerReview review,
      MerchantStore merchantStore,
      Language language) {

    // rating already exist
    Optional<CustomerReview> customerReview =
        Optional.ofNullable(
            customerReviewService.getByReviewerAndReviewed(review.getCustomerId(), customerId));

    if(customerReview.isPresent()) {
      throw new ServiceRuntimeException("A review already exist for this customer and product");
    }

    // rating maximum 5
    if (review.getRating() > Constants.MAX_REVIEW_RATING_SCORE) {
      throw new ServiceRuntimeException("Maximum rating score is " + Constants.MAX_REVIEW_RATING_SCORE);
    }

    review.setReviewedCustomer(customerId);

    saveOrUpdateCustomerReview(review, merchantStore, language);

    return review;
  }

  @Override
  public PersistableCustomerReview updateCustomerReview(Long id, Long reviewId, PersistableCustomerReview review,
      MerchantStore store, Language language) {

    CustomerReview customerReview = getCustomerReviewById(reviewId);

    if(! customerReview.getReviewedCustomer().getId().equals(id)) {
      throw new ResourceNotFoundException("Customer review with id " + reviewId + " does not exist for this customer");
    }

    //rating maximum 5
    if(review.getRating()>Constants.MAX_REVIEW_RATING_SCORE) {
      throw new ServiceRuntimeException("Maximum rating score is " + Constants.MAX_REVIEW_RATING_SCORE);
    }

    review.setReviewedCustomer(id);
    return review;
  }


  @Override
  public void deleteById(Long id) {
    Customer customer = getCustomerById(id);
    delete(customer);
    
  }


  @Override
  public void updateAddress(PersistableCustomer customer, MerchantStore store) {
    Validate.notNull(customer.getBilling(), "Billing address can not be null");
    Validate.notNull(customer.getBilling().getAddress(), "Billing address can not be null");
    Validate.notNull(customer.getBilling().getCity(), "Billing city can not be null");
    Validate.notNull(customer.getBilling().getPostalCode(), "Billing postal code can not be null");
    Validate.notNull(customer.getBilling().getCountryCode(), "Billing country can not be null");

    customer.getBilling().setBillingAddress(true);
    
    if(customer.getDelivery() == null) {
      customer.setDelivery(customer.getBilling());
      customer.getDelivery().setBillingAddress(false);
    } else {
      Validate.notNull(customer.getDelivery(), "Delivery address can not be null");
      Validate.notNull(customer.getDelivery().getAddress(), "Delivery address can not be null");
      Validate.notNull(customer.getDelivery().getCity(), "Delivery city can not be null");
      Validate.notNull(customer.getDelivery().getPostalCode(), "Delivery postal code can not be null");
      Validate.notNull(customer.getDelivery().getCountryCode(), "Delivery country can not be null");

      
    }
    
    try {
      //update billing
      updateAddress(customer.getId(), store, customer.getBilling(), store.getDefaultLanguage());
      //update delivery
      updateAddress(customer.getId(), store, customer.getDelivery(), store.getDefaultLanguage());
    } catch (Exception e) {
      throw new ServiceRuntimeException("Error while updating customer address");
    }
    

  }


  @Override
  public void updateAddress(String userName, PersistableCustomer customer, MerchantStore store) {
    
    ReadableCustomer customerModel = getByUserName(userName, store, store.getDefaultLanguage());
    customer.setId(customerModel.getId());
    customer.setUserName(userName);
    updateAddress(customer, store);
    
  }


  @Override
  public PersistableCustomer update(String userName, PersistableCustomer customer,
      MerchantStore store) {
    ReadableCustomer customerModel = getByUserName(userName, store, store.getDefaultLanguage());
    customer.setId(customerModel.getId());
    customer.setUserName(userName);
    return this.update(customer, store);
  }


  @Override
  public boolean passwordMatch(String rawPassword, Customer customer) {
    return passwordEncoder.matches(rawPassword, customer.getPassword());
  }


  @Override
  public void changePassword(Customer customer, String newPassword) {
    String encoded = passwordEncoder.encode(newPassword);
    customer.setPassword(encoded);
    try {
      customerService.update(customer);
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Exception while changing password", e);
    }
    
  }
}
