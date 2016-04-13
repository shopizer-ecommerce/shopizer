package com.salesmanager.web.shop.controller.customer;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.salesmanager.core.business.catalog.product.service.PricingService;
import com.salesmanager.core.business.customer.CustomerRegistrationException;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.core.business.reference.zone.service.ZoneService;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCart;
import com.salesmanager.core.business.shoppingcart.service.ShoppingCartCalculationService;
import com.salesmanager.core.business.system.service.EmailService;
import com.salesmanager.core.utils.CoreConfiguration;
import com.salesmanager.web.constants.ApplicationConstants;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.entity.customer.AnonymousCustomer;
import com.salesmanager.web.entity.customer.CustomerEntity;
import com.salesmanager.web.entity.customer.SecuredShopPersistableCustomer;
import com.salesmanager.web.entity.shoppingcart.ShoppingCartData;
import com.salesmanager.web.populator.shoppingCart.ShoppingCartDataPopulator;
import com.salesmanager.web.shop.controller.AbstractController;
import com.salesmanager.web.shop.controller.ControllerConstants;
import com.salesmanager.web.shop.controller.customer.facade.CustomerFacade;
import com.salesmanager.web.utils.CaptchaRequestUtils;
import com.salesmanager.web.utils.EmailTemplatesUtils;
import com.salesmanager.web.utils.ImageFilePath;
import com.salesmanager.web.utils.LabelUtils;

/**
 * Registration of a new customer
 * @author Carl Samson
 *
 */

@SuppressWarnings( "deprecation" )
// http://stackoverflow.com/questions/17444258/how-to-use-new-passwordencoder-from-spring-security
@Controller
@RequestMapping("/shop/customer")
public class CustomerRegistrationController extends AbstractController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRegistrationController.class);
    
    
	@Autowired
	private CoreConfiguration coreConfiguration;

	@Autowired
	private LanguageService languageService;


	@Autowired
	private CountryService countryService;

	
	@Autowired
	private ZoneService zoneService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	EmailService emailService;

	@Autowired
	private LabelUtils messages;
	
	@Autowired
	private CustomerFacade customerFacade;
	
	@Autowired
    private AuthenticationManager customerAuthenticationManager;
	
	@Autowired
	private EmailTemplatesUtils emailTemplatesUtils;
	
	@Autowired
	private CaptchaRequestUtils captchaRequestUtils;
	
	@Autowired
	@Qualifier("img")
	private ImageFilePath imageUtils;
	
    @Autowired
    private ShoppingCartCalculationService shoppingCartCalculationService;
    
    @Autowired
    private PricingService pricingService;
	



	@RequestMapping(value="/registration.html", method=RequestMethod.GET)
	public String displayRegistration(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);

		model.addAttribute( "recapatcha_public_key", coreConfiguration.getProperty( ApplicationConstants.RECAPTCHA_PUBLIC_KEY ) );
		
		SecuredShopPersistableCustomer customer = new SecuredShopPersistableCustomer();
		AnonymousCustomer anonymousCustomer = (AnonymousCustomer)request.getAttribute(Constants.ANONYMOUS_CUSTOMER);
		if(anonymousCustomer!=null) {
			customer.setBilling(anonymousCustomer.getBilling());
		}
		
		model.addAttribute("customer", customer);

		/** template **/
		StringBuilder template = new StringBuilder().append(ControllerConstants.Tiles.Customer.register).append(".").append(store.getStoreTemplate());

		return template.toString();


	}

    @RequestMapping( value = "/register.html", method = RequestMethod.POST )
    public String registerCustomer( @Valid
    @ModelAttribute("customer") SecuredShopPersistableCustomer customer, BindingResult bindingResult, Model model,
                                    HttpServletRequest request, HttpServletResponse response, final Locale locale )
        throws Exception
    {
        MerchantStore merchantStore = (MerchantStore) request.getAttribute( Constants.MERCHANT_STORE );
        Language language = super.getLanguage(request);

        String userName = null;
        String password = null;
        
        model.addAttribute( "recapatcha_public_key", coreConfiguration.getProperty( ApplicationConstants.RECAPTCHA_PUBLIC_KEY ) );
        
        if(!StringUtils.isBlank(request.getParameter("g-recaptcha-response"))) {
        	boolean validateCaptcha = captchaRequestUtils.checkCaptcha(request.getParameter("g-recaptcha-response"));
        	
            if ( !validateCaptcha )
            {
                LOGGER.debug( "Captcha response does not matched" );
    			FieldError error = new FieldError("captchaChallengeField","captchaChallengeField",messages.getMessage("validaion.recaptcha.not.matched", locale));
    			bindingResult.addError(error);
            }
        }
        

        if ( StringUtils.isNotBlank( customer.getUserName() ) )
        {
            if ( customerFacade.checkIfUserExists( customer.getUserName(), merchantStore ) )
            {
                LOGGER.debug( "Customer with username {} already exists for this store ", customer.getUserName() );
            	FieldError error = new FieldError("userName","userName",messages.getMessage("registration.username.already.exists", locale));
            	bindingResult.addError(error);
            }
            userName = customer.getUserName();
        }
        
        
        if ( StringUtils.isNotBlank( customer.getPassword() ) &&  StringUtils.isNotBlank( customer.getCheckPassword() ))
        {
            if (! customer.getPassword().equals(customer.getCheckPassword()) )
            {
            	FieldError error = new FieldError("password","password",messages.getMessage("message.password.checkpassword.identical", locale));
            	bindingResult.addError(error);

            }
            password = customer.getPassword();
        }

        if ( bindingResult.hasErrors() )
        {
            LOGGER.debug( "found {} validation error while validating in customer registration ",
                         bindingResult.getErrorCount() );
            StringBuilder template =
                new StringBuilder().append( ControllerConstants.Tiles.Customer.register ).append( "." ).append( merchantStore.getStoreTemplate() );
            return template.toString();

        }

        @SuppressWarnings( "unused" )
        CustomerEntity customerData = null;
        try
        {
            //set user clear password
        	customer.setClearPassword(password);
        	customerData = customerFacade.registerCustomer( customer, merchantStore, language );
        }
        catch ( CustomerRegistrationException cre )
        {
            LOGGER.error( "Error while registering customer.. ", cre);
        	ObjectError error = new ObjectError("registration",messages.getMessage("registration.failed", locale));
        	bindingResult.addError(error);
            StringBuilder template =
                            new StringBuilder().append( ControllerConstants.Tiles.Customer.register ).append( "." ).append( merchantStore.getStoreTemplate() );
             return template.toString();
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error while registering customer.. ", e);
        	ObjectError error = new ObjectError("registration",messages.getMessage("registration.failed", locale));
        	bindingResult.addError(error);
            StringBuilder template =
                            new StringBuilder().append( ControllerConstants.Tiles.Customer.register ).append( "." ).append( merchantStore.getStoreTemplate() );
            return template.toString();
        }
              
        /**
         * Send registration email
         */
        emailTemplatesUtils.sendRegistrationEmail( customer, merchantStore, locale, request.getContextPath() );

        /**
         * Login user
         */
        
        try {
        	
	        //refresh customer
	        Customer c = customerFacade.getCustomerByUserName(customer.getUserName(), merchantStore);
	        //authenticate
	        customerFacade.authenticate(c, userName, password);
	        super.setSessionAttribute(Constants.CUSTOMER, c, request);
	        
	        StringBuilder cookieValue = new StringBuilder();
            cookieValue.append(merchantStore.getCode()).append("_").append(c.getNick());
	        
            //set username in the cookie
            Cookie cookie = new Cookie(Constants.COOKIE_NAME_USER, cookieValue.toString());
            cookie.setMaxAge(60 * 24 * 3600);
            cookie.setPath(Constants.SLASH);
            response.addCookie(cookie);
            
            
            String sessionShoppingCartCode= (String)request.getSession().getAttribute( Constants.SHOPPING_CART );
            if(!StringUtils.isBlank(sessionShoppingCartCode)) {
	            ShoppingCart shoppingCart = customerFacade.mergeCart( c, sessionShoppingCartCode, merchantStore, language );
	            ShoppingCartData shoppingCartData=this.populateShoppingCartData(shoppingCart, merchantStore, language);
	            if(shoppingCartData !=null) {
	                request.getSession().setAttribute(Constants.SHOPPING_CART, shoppingCartData.getCode());
	            }

	            //set username in the cookie
	            Cookie c1 = new Cookie(Constants.COOKIE_NAME_CART, shoppingCartData.getCode());
	            c1.setMaxAge(60 * 24 * 3600);
	            c1.setPath(Constants.SLASH);
	            response.addCookie(c1);
	            
            }

	        return "redirect:/shop/customer/dashboard.html";
        
        
        } catch(Exception e) {
        	LOGGER.error("Cannot authenticate user ",e);
        	ObjectError error = new ObjectError("registration",messages.getMessage("registration.failed", locale));
        	bindingResult.addError(error);
        }
        
        
        StringBuilder template =
                new StringBuilder().append( ControllerConstants.Tiles.Customer.register ).append( "." ).append( merchantStore.getStoreTemplate() );
        return template.toString();

    }
	
	
	@ModelAttribute("countryList")
	public List<Country> getCountries(final HttpServletRequest request){
	    
        Language language = (Language) request.getAttribute( "LANGUAGE" );
        try
        {
            if ( language == null )
            {
                language = (Language) request.getAttribute( "LANGUAGE" );
            }

            if ( language == null )
            {
                language = languageService.getByCode( Constants.DEFAULT_LANGUAGE );
            }
            
            List<Country> countryList=countryService.getCountries( language );
            return countryList;
        }
        catch ( ServiceException e )
        {
            LOGGER.error( "Error while fetching country list ", e );

        }
        return Collections.emptyList();
    }
	
	@ModelAttribute("zoneList")
    public List<Zone> getZones(final HttpServletRequest request){
	    return zoneService.list();
	}
	
	
	

	
	
    private ShoppingCartData populateShoppingCartData(final ShoppingCart cartModel , final MerchantStore store, final Language language){

        ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
        shoppingCartDataPopulator.setShoppingCartCalculationService( shoppingCartCalculationService );
        shoppingCartDataPopulator.setPricingService( pricingService );
        
        try
        {
            return shoppingCartDataPopulator.populate(  cartModel ,  store,  language);
        }
        catch ( ConversionException ce )
        {
           LOGGER.error( "Error in converting shopping cart to shopping cart data", ce );

        }
        return null;
    }
	


}
