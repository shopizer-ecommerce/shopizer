/**
 *
 */
package com.salesmanager.shop.store.controller.shoppingCart.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartCalculationService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartService;
import com.salesmanager.core.business.utils.ProductPriceUtils;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.shoppingcart.CartModificationException;
import com.salesmanager.shop.model.shoppingcart.PersistableShoppingCartItem;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCart;
import com.salesmanager.shop.model.shoppingcart.ShoppingCartAttribute;
import com.salesmanager.shop.model.shoppingcart.ShoppingCartData;
import com.salesmanager.shop.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.shop.populator.shoppingCart.ReadableShoppingCartPopulator;
import com.salesmanager.shop.populator.shoppingCart.ShoppingCartDataPopulator;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.ImageFilePath;

/**
 * @author Umesh Awasthi
 * @version 1.0
 * @since 1.0
 */
@Service( value = "shoppingCartFacade" )
public class ShoppingCartFacadeImpl
    implements ShoppingCartFacade
{

    
    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartFacadeImpl.class);

    @Inject
    private ShoppingCartService shoppingCartService;

    @Inject
    ShoppingCartCalculationService shoppingCartCalculationService;

    @Inject
    private ProductPriceUtils productPriceUtils;

    @Inject
    private ProductService productService;

    @Inject
    private PricingService pricingService;

    @Inject
    private ProductAttributeService productAttributeService;

	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

    public void deleteShoppingCart(final Long id, final MerchantStore store) throws Exception {
    	ShoppingCart cart = shoppingCartService.getById(id, store);
    	if(cart!=null) {
    		shoppingCartService.deleteCart(cart);
    	}
    }
    
    @Override
    public void deleteShoppingCart(final String code, final MerchantStore store) throws Exception {
    	ShoppingCart cart = shoppingCartService.getByCode(code, store);
    	if(cart!=null) {
    		shoppingCartService.deleteCart(cart);
    	}
    }

    @Override
    public ShoppingCartData addItemsToShoppingCart( final ShoppingCartData shoppingCartData,
                                                    final ShoppingCartItem item, final MerchantStore store, final Language language,final Customer customer )
        throws Exception
    {

        ShoppingCart cartModel = null;
        if(item.getQuantity() < 1) item.setQuantity(1);
        /**
         * Sometimes a user logs in and a shopping cart is present in db (shoppingCartData
         * but ui has no cookie with shopping cart code so the cart code will have
         * to be added to the item in order to process add to cart normally
         */
        if(shoppingCartData != null && StringUtils.isBlank(item.getCode())) {
        	item.setCode(shoppingCartData.getCode());
        }
        
        
        if ( !StringUtils.isBlank( item.getCode() ) )
        {
            // get it from the db
            cartModel = getShoppingCartModel( item.getCode(), store );
            if ( cartModel == null )
            {
                cartModel = createCartModel( shoppingCartData.getCode(), store,customer );
            }

        }

        if ( cartModel == null )
        {

            final String shoppingCartCode =
                StringUtils.isNotBlank( shoppingCartData.getCode() ) ? shoppingCartData.getCode() : null;
            cartModel = createCartModel( shoppingCartCode, store,customer );

        }
        com.salesmanager.core.model.shoppingcart.ShoppingCartItem shoppingCartItem =
            createCartItem( cartModel, item, store );
        
        
        boolean duplicateFound = false;
        if(CollectionUtils.isEmpty(item.getShoppingCartAttributes())) {//increment quantity
        	//get duplicate item from the cart
        	Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> cartModelItems = cartModel.getLineItems();
        	for(com.salesmanager.core.model.shoppingcart.ShoppingCartItem cartItem : cartModelItems) {
        		if(cartItem.getProduct().getId().longValue()==shoppingCartItem.getProduct().getId().longValue()) {
        			if(CollectionUtils.isEmpty(cartItem.getAttributes())) {
        				if(!duplicateFound) {
        					if(!shoppingCartItem.isProductVirtual()) {
	        					cartItem.setQuantity(cartItem.getQuantity() + shoppingCartItem.getQuantity());
        					}
        					duplicateFound = true;
        					break;
        				}
        			}
        		}
        	}
        }
        
        if(!duplicateFound) {
        	//shoppingCartItem.getAttributes().stream().forEach(a -> {a.setProductAttributeId(productAttributeId);});
        	cartModel.getLineItems().add( shoppingCartItem );
        }
        
        /** Update cart in database with line items **/
        shoppingCartService.saveOrUpdate( cartModel );

        //refresh cart
        cartModel = shoppingCartService.getById(cartModel.getId(), store);

        shoppingCartCalculationService.calculate( cartModel, store, language );

        ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
        shoppingCartDataPopulator.setShoppingCartCalculationService( shoppingCartCalculationService );
        shoppingCartDataPopulator.setPricingService( pricingService );
        shoppingCartDataPopulator.setimageUtils(imageUtils);


        return shoppingCartDataPopulator.populate( cartModel, store, language );
    }

    private com.salesmanager.core.model.shoppingcart.ShoppingCartItem createCartItem( final ShoppingCart cartModel,
                                                                                               final ShoppingCartItem shoppingCartItem,
                                                                                               final MerchantStore store )
        throws Exception
    {

        Product product = productService.getById( shoppingCartItem.getProductId() );

        if ( product == null )
        {
            throw new Exception( "Item with id " + shoppingCartItem.getProductId() + " does not exist" );
        }

        if ( product.getMerchantStore().getId().intValue() != store.getId().intValue() )
        {
            throw new Exception( "Item with id " + shoppingCartItem.getProductId() + " does not belong to merchant "
                + store.getId() );
        }
        
		/**
		 * Check if product quantity is 0
		 * Check if product is available
		 * Check if date available <= now
		 */
        
        Set<ProductAvailability> availabilities = product.getAvailabilities();
        if(availabilities == null) {
        	
        	throw new Exception( "Item with id " + product.getId() + " is not properly configured" );
        	
        }
        	
        for(ProductAvailability availability : availabilities) {
        	if(availability.getProductQuantity() == null || availability.getProductQuantity().intValue() <= 0) {
                throw new Exception( "Item with id " + product.getId() + " is not available");
        	}
        }
        
        if(!product.isAvailable()) {
        	throw new Exception( "Item with id " + product.getId() + " is not available");
        }
        
        if(!DateUtil.dateBeforeEqualsDate(product.getDateAvailable(), new Date())) {
        	throw new Exception( "Item with id " + product.getId() + " is not available");
        }


        com.salesmanager.core.model.shoppingcart.ShoppingCartItem item =
            shoppingCartService.populateShoppingCartItem( product );

        item.setQuantity( shoppingCartItem.getQuantity() );
        item.setShoppingCart( cartModel );

        // attributes
        List<ShoppingCartAttribute> cartAttributes = shoppingCartItem.getShoppingCartAttributes();
        if ( !CollectionUtils.isEmpty( cartAttributes ) )
        {
            for ( ShoppingCartAttribute attribute : cartAttributes )
            {
                ProductAttribute productAttribute = productAttributeService.getById( attribute.getAttributeId() );
                if ( productAttribute != null
                    && productAttribute.getProduct().getId().longValue() == product.getId().longValue() )
                {
                    com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem attributeItem =
                        new com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem( item,
                                                                                                         productAttribute );

                    item.addAttributes( attributeItem );
                }
            }
        }
        return item;

    }

    
    //used for api
	private com.salesmanager.core.model.shoppingcart.ShoppingCartItem createCartItem(ShoppingCart cartModel,
			 PersistableShoppingCartItem shoppingCartItem, MerchantStore store) throws Exception {

		Product product = productService.getById(shoppingCartItem.getProduct());

		if (product == null) {
			throw new ResourceNotFoundException("Item with id " + shoppingCartItem.getProduct() + " does not exist");
		}

		if (product.getMerchantStore().getId().intValue() != store.getId().intValue()) {
			throw new ResourceNotFoundException("Item with id " + shoppingCartItem.getProduct() + " does not belong to merchant "
					+ store.getId());
		}
		
		/**
		 * Check if product quantity is 0
		 * Check if product is available
		 * Check if date available <= now
		 */
        
        Set<ProductAvailability> availabilities = product.getAvailabilities();
        if(availabilities == null) {
        	
        	throw new Exception( "Item with id " + product.getId() + " is not properly configured" );
        	
        }
        	
        for(ProductAvailability availability : availabilities) {
        	if(availability.getProductQuantity() == null || availability.getProductQuantity().intValue() <= 0) {
                throw new Exception( "Item with id " + product.getId() + " is not available");
        	}
        }
        
        if(!product.isAvailable()) {
        	throw new Exception( "Item with id " + product.getId() + " is not available");
        }
        
        if(!DateUtil.dateBeforeEqualsDate(product.getDateAvailable(), new Date())) {
        	throw new Exception( "Item with id " + product.getId() + " is not available");
        }
		

		com.salesmanager.core.model.shoppingcart.ShoppingCartItem item = shoppingCartService
				.populateShoppingCartItem(product);

		item.setQuantity(shoppingCartItem.getQuantity());
		item.setShoppingCart(cartModel);
		
		//set attributes
		List<com.salesmanager.shop.model.catalog.product.attribute.ProductAttribute> attributes = shoppingCartItem.getAttributes();
		if (!CollectionUtils.isEmpty(attributes)) {
			for(com.salesmanager.shop.model.catalog.product.attribute.ProductAttribute attribute : attributes) {
				
				ProductAttribute productAttribute = productAttributeService.getById(attribute.getId());
				
				if (productAttribute != null
						&& productAttribute.getProduct().getId().longValue() == product.getId().longValue()) {
					
					com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem attributeItem = new com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem(
							item, productAttribute);

					item.addAttributes(attributeItem);
				}				
			}
		}

		return item;

	}   
    

    @Override
    public ShoppingCart createCartModel( final String shoppingCartCode, final MerchantStore store,final Customer customer )
        throws Exception
    {
        final Long CustomerId = customer != null ? customer.getId() : null;
        ShoppingCart cartModel = new ShoppingCart();
        if ( StringUtils.isNotBlank( shoppingCartCode ) )
        {
            cartModel.setShoppingCartCode( shoppingCartCode );
        }
        else
        {
            cartModel.setShoppingCartCode( uniqueShoppingCartCode() );
        }

        cartModel.setMerchantStore( store );
        if ( CustomerId != null )
        {
            cartModel.setCustomerId( CustomerId );
        }
        shoppingCartService.create( cartModel );
        return cartModel;
    }





    private com.salesmanager.core.model.shoppingcart.ShoppingCartItem getEntryToUpdate( final long entryId,
                                                                                                 final ShoppingCart cartModel )
    {
        if ( CollectionUtils.isNotEmpty( cartModel.getLineItems() ) )
        {
            for ( com.salesmanager.core.model.shoppingcart.ShoppingCartItem shoppingCartItem : cartModel.getLineItems() )
            {
                if ( shoppingCartItem.getId().longValue() == entryId )
                {
                    LOG.info( "Found line item  for given entry id: " + entryId );
                    return shoppingCartItem;

                }
            }
        }
        LOG.info( "Unable to find any entry for given Id: " + entryId );
        return null;
    }

    private Object getKeyValue( final String key )
    {
        ServletRequestAttributes reqAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return reqAttr.getRequest().getAttribute( key );
    }

    @Override
    public ShoppingCartData getShoppingCartData( final Customer customer, final MerchantStore store,
                                                 final String shoppingCartId, Language language)
        throws Exception
    {

        ShoppingCart cart = null;
        try
        {
            if ( customer != null )
            {
                LOG.info( "Reteriving customer shopping cart..." );

                cart = shoppingCartService.getShoppingCart( customer );

            }

            else
            {
                if ( StringUtils.isNotBlank( shoppingCartId ) && cart == null )
                {
                    cart = shoppingCartService.getByCode( shoppingCartId, store );
                }

            }
        }
        catch ( ServiceException ex )
        {
            LOG.error( "Error while retriving cart from customer", ex );
        }
        catch( NoResultException nre) {
        	//nothing
        }

        if ( cart == null )
        {
            return null;
        }

        LOG.info( "Cart model found." );

        ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
        shoppingCartDataPopulator.setShoppingCartCalculationService( shoppingCartCalculationService );
        shoppingCartDataPopulator.setPricingService( pricingService );
        shoppingCartDataPopulator.setimageUtils(imageUtils);

        //Language language = (Language) getKeyValue( Constants.LANGUAGE );
        MerchantStore merchantStore = (MerchantStore) getKeyValue( Constants.MERCHANT_STORE );
        
        ShoppingCartData shoppingCartData = shoppingCartDataPopulator.populate( cart, merchantStore, language );
        
/*        List<ShoppingCartItem> unavailables = new ArrayList<ShoppingCartItem>();
        List<ShoppingCartItem> availables = new ArrayList<ShoppingCartItem>();
        //Take out items no more available
        List<ShoppingCartItem> items = shoppingCartData.getShoppingCartItems();
        for(ShoppingCartItem item : items) {
        	String code = item.getProductCode();
        	Product p =productService.getByCode(code, language);
        	if(!p.isAvailable()) {
        		unavailables.add(item);
        	} else {
        		availables.add(item);
        	}
        	
        }
        shoppingCartData.setShoppingCartItems(availables);
        shoppingCartData.setUnavailables(unavailables);*/
        
        return shoppingCartData;

    }

    //@Override
    public ShoppingCartData getShoppingCartData( final ShoppingCart shoppingCartModel, Language language)
        throws Exception
    {

        ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
        shoppingCartDataPopulator.setShoppingCartCalculationService( shoppingCartCalculationService );
        shoppingCartDataPopulator.setPricingService( pricingService );
        shoppingCartDataPopulator.setimageUtils(imageUtils);
        //Language language = (Language) getKeyValue( Constants.LANGUAGE );
        MerchantStore merchantStore = (MerchantStore) getKeyValue( Constants.MERCHANT_STORE );
        return shoppingCartDataPopulator.populate( shoppingCartModel, merchantStore, language );
    }

	@Override
    public ShoppingCartData removeCartItem( final Long itemID, final String cartId ,final MerchantStore store,final Language language )
        throws Exception
    {
        if ( StringUtils.isNotBlank( cartId ) )
        {

            ShoppingCart cartModel = getCartModel( cartId,store );
            if ( cartModel != null )
            {
                if ( CollectionUtils.isNotEmpty( cartModel.getLineItems() ) )
                {
                    Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> shoppingCartItemSet =
                        new HashSet<com.salesmanager.core.model.shoppingcart.ShoppingCartItem>();
                    for ( com.salesmanager.core.model.shoppingcart.ShoppingCartItem shoppingCartItem : cartModel.getLineItems() )
                    {
                        if(shoppingCartItem.getId().longValue() == itemID.longValue() )
                        {
                    		shoppingCartService.deleteShoppingCartItem(itemID);
                        } else {
                            shoppingCartItemSet.add(shoppingCartItem);
                        }
                    }
                    
                    cartModel.setLineItems(shoppingCartItemSet);


                    ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
                    shoppingCartDataPopulator.setShoppingCartCalculationService( shoppingCartCalculationService );
                    shoppingCartDataPopulator.setPricingService( pricingService );
                    shoppingCartDataPopulator.setimageUtils(imageUtils);
                    return shoppingCartDataPopulator.populate( cartModel, store, language );
                    

                }
            }
        }
        return null;
    }

    @Override
    public ShoppingCartData updateCartItem( final Long itemID, final String cartId, final long newQuantity,final MerchantStore store, final Language language )
        throws Exception
    {
        if ( newQuantity < 1 )
        {
            throw new CartModificationException( "Quantity must not be less than one" );
        }
        if ( StringUtils.isNotBlank( cartId ) )
        {
            ShoppingCart cartModel = getCartModel( cartId,store );
            if ( cartModel != null )
            {
                com.salesmanager.core.model.shoppingcart.ShoppingCartItem entryToUpdate =
                    getEntryToUpdate( itemID.longValue(), cartModel );

                if ( entryToUpdate == null )
                {
                    throw new CartModificationException( "Unknown entry number." );
                }

                entryToUpdate.getProduct();

                LOG.info( "Updating cart entry quantity to" + newQuantity );
                entryToUpdate.setQuantity( (int) newQuantity );
                List<ProductAttribute> productAttributes = new ArrayList<ProductAttribute>();
                productAttributes.addAll( entryToUpdate.getProduct().getAttributes() );
                final FinalPrice finalPrice =
                    productPriceUtils.getFinalProductPrice( entryToUpdate.getProduct(), productAttributes );
                entryToUpdate.setItemPrice( finalPrice.getFinalPrice() );
                shoppingCartService.saveOrUpdate( cartModel );

                LOG.info( "Cart entry updated with desired quantity" );
                ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
                shoppingCartDataPopulator.setShoppingCartCalculationService( shoppingCartCalculationService );
                shoppingCartDataPopulator.setPricingService( pricingService );
                shoppingCartDataPopulator.setimageUtils(imageUtils);
                return shoppingCartDataPopulator.populate( cartModel, store, language );

            }
        }
        return null;
    }
    
    @Override
    public ShoppingCartData updateCartItems( final List<ShoppingCartItem> shoppingCartItems, final MerchantStore store, final Language language )
            throws Exception
        {
    	
    		Validate.notEmpty(shoppingCartItems,"shoppingCartItems null or empty");
    		ShoppingCart cartModel = null;
    		Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> cartItems = new HashSet<com.salesmanager.core.model.shoppingcart.ShoppingCartItem>();
    		for(ShoppingCartItem item : shoppingCartItems) {
    			
    			if(item.getQuantity()<1) {
    				throw new CartModificationException( "Quantity must not be less than one" );
    			}
    			
    			if(cartModel==null) {
    				cartModel = getCartModel( item.getCode(), store );
    			}
    			
                com.salesmanager.core.model.shoppingcart.ShoppingCartItem entryToUpdate =
                        getEntryToUpdate( item.getId(), cartModel );

                if ( entryToUpdate == null ) {
                        throw new CartModificationException( "Unknown entry number." );
                }

                entryToUpdate.getProduct();

                LOG.info( "Updating cart entry quantity to" + item.getQuantity() );
                entryToUpdate.setQuantity( (int) item.getQuantity() );
                
                List<ProductAttribute> productAttributes = new ArrayList<ProductAttribute>();
                productAttributes.addAll( entryToUpdate.getProduct().getAttributes() );
                
                final FinalPrice finalPrice =
                        productPriceUtils.getFinalProductPrice( entryToUpdate.getProduct(), productAttributes );
                entryToUpdate.setItemPrice( finalPrice.getFinalPrice() );
                    

                cartItems.add(entryToUpdate);
    			
    			
    			
    			
    		}
    		
    		cartModel.setLineItems(cartItems);
    		shoppingCartService.saveOrUpdate( cartModel );

    		
            LOG.info( "Cart entry updated with desired quantity" );
            ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
            shoppingCartDataPopulator.setShoppingCartCalculationService( shoppingCartCalculationService );
            shoppingCartDataPopulator.setPricingService( pricingService );
            shoppingCartDataPopulator.setimageUtils(imageUtils);
            return shoppingCartDataPopulator.populate( cartModel, store, language );

        }


    private ShoppingCart getCartModel( final String cartId,final MerchantStore store )
    {
        if ( StringUtils.isNotBlank( cartId ) )
        {
           try
            {
                return shoppingCartService.getByCode( cartId, store );
            }
            catch ( ServiceException e )
            {
                LOG.error( "unable to find any cart asscoiated with this Id: " + cartId );
                LOG.error( "error while fetching cart model...", e );
                return null;
            }
            catch( NoResultException nre) {
           	//nothing
            }

        }
        return null;
    }

	@Override
	public ShoppingCartData getShoppingCartData(String code, MerchantStore store, Language language) {
		try {
			ShoppingCart cartModel = shoppingCartService.getByCode( code, store );
			if(cartModel!=null) {
				ShoppingCartData cart = getShoppingCartData(cartModel, language);
				return cart;
			}
		} catch( NoResultException nre) {
	        	//nothing

		} catch(Exception e) {
			LOG.error("Cannot retrieve cart code " + code,e);
		}


		return null;
	}

	@Override
	public ShoppingCart getShoppingCartModel(String shoppingCartCode,
			MerchantStore store) throws Exception {
		return shoppingCartService.getByCode( shoppingCartCode, store );
	}

	@Override
	public ShoppingCart getShoppingCartModel(Customer customer,
			MerchantStore store) throws Exception {
		return shoppingCartService.getByCustomer(customer);
	}

	@Override
	public void saveOrUpdateShoppingCart(ShoppingCart cart) throws Exception {
		shoppingCartService.saveOrUpdate(cart);
		
	}

	@Override
	public ReadableShoppingCart getCart(Customer customer, MerchantStore store, Language language) throws Exception {
		
		Validate.notNull(customer,"Customer cannot be null");
		Validate.notNull(customer.getId(),"Customer.id cannot be null or empty");
		
		//Check if customer has an existing shopping cart
		ShoppingCart cartModel = shoppingCartService.getByCustomer(customer);
		
		if(cartModel == null) {
			return null;
		}
		
        shoppingCartCalculationService.calculate( cartModel, store, language );
        
        ReadableShoppingCartPopulator readableShoppingCart = new ReadableShoppingCartPopulator();
        
        readableShoppingCart.setImageUtils(imageUtils);
        readableShoppingCart.setPricingService(pricingService);
        readableShoppingCart.setProductAttributeService(productAttributeService);
        readableShoppingCart.setShoppingCartCalculationService(shoppingCartCalculationService);
  
        ReadableShoppingCart readableCart = new  ReadableShoppingCart();
        
        readableShoppingCart.populate(cartModel, readableCart,  store, language);

		
		return readableCart;
	}
	
	@Override
	public ReadableShoppingCart addToCart(PersistableShoppingCartItem item, MerchantStore store,
			Language language) throws Exception {
		
		Validate.notNull(item,"PersistableShoppingCartItem cannot be null");
		
		//if cart does not exist create a new one

		ShoppingCart cartModel = new ShoppingCart();
		cartModel.setMerchantStore(store);
		cartModel.setShoppingCartCode(uniqueShoppingCartCode());


		return readableShoppingCart(cartModel,item,store,language);
	}
	

	@Override
	public void removeShoppingCartItem(String cartCode, Long productId,
	      MerchantStore merchant, Language language) throws Exception {
	    Validate.notNull(cartCode, "Shopping cart code must not be null");
	    Validate.notNull(productId, "product id must not be null");
	    Validate.notNull(merchant, "MerchantStore must not be null");
	    
	  
	    //get cart
	    ShoppingCart cart = getCartModel(cartCode, merchant);
	    
	    if(cart == null) {
	      throw new ResourceNotFoundException("Cart code [ " + cartCode + " ] not found");
	    }
	    
	    Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> items = new HashSet<com.salesmanager.core.model.shoppingcart.ShoppingCartItem>();
	    com.salesmanager.core.model.shoppingcart.ShoppingCartItem itemToDelete = null;
	    for ( com.salesmanager.core.model.shoppingcart.ShoppingCartItem shoppingCartItem : cart.getLineItems() )
        {
            if ( shoppingCartItem.getProduct().getId().longValue() == productId.longValue() )
            {
                //get cart item
                itemToDelete =
                    getEntryToUpdate( shoppingCartItem.getId(), cart );
                
                
                //break;

            } else {
              items.add(shoppingCartItem);
            }
        }
	    //delete item
	    if(itemToDelete!=null) {
	      shoppingCartService.deleteShoppingCartItem(itemToDelete.getId());
	    }
        
        //remaining items
	    if(items.size()>0) {
	    	cart.setLineItems(items);
	    } else {
	    	cart.getLineItems().clear();
	    }

        //if(items.size()>0) {
          shoppingCartService.saveOrUpdate(cart);//update cart with remaining items
          //ReadableShoppingCart readableShoppingCart = this.getByCode(cartCode, merchant, language);
        //}

	}
	
	private ReadableShoppingCart readableShoppingCart(ShoppingCart cartModel, PersistableShoppingCartItem item, MerchantStore store,
			Language language) throws Exception {
		
		
		com.salesmanager.core.model.shoppingcart.ShoppingCartItem itemModel = createCartItem(cartModel, item, store);
		
		//need to check if the item is already in the cart
        boolean duplicateFound = false;
        //only if item has no attributes
        if(CollectionUtils.isEmpty(item.getAttributes())) {//increment quantity
        	//get duplicate item from the cart
        	Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> cartModelItems = cartModel.getLineItems();
        	for(com.salesmanager.core.model.shoppingcart.ShoppingCartItem cartItem : cartModelItems) {
        		if(cartItem.getProduct().getId().longValue()==item.getProduct().longValue()) {
        			if(CollectionUtils.isEmpty(cartItem.getAttributes())) {
        				if(!duplicateFound) {
        					if(!itemModel.isProductVirtual()) {
	        					cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
        					}
        					duplicateFound = true;
        					break;
        				}
        			}
        		}
        	}
        } 
        
        if(!duplicateFound) {
        	cartModel.getLineItems().add( itemModel );
        }
        
        saveShoppingCart( cartModel );

        //refresh cart
        cartModel = shoppingCartService.getById(cartModel.getId(), store);

        shoppingCartCalculationService.calculate( cartModel, store, language );
        
        ReadableShoppingCartPopulator readableShoppingCart = new ReadableShoppingCartPopulator();
        
        readableShoppingCart.setImageUtils(imageUtils);
        readableShoppingCart.setPricingService(pricingService);
        readableShoppingCart.setProductAttributeService(productAttributeService);
        readableShoppingCart.setShoppingCartCalculationService(shoppingCartCalculationService);
  
        ReadableShoppingCart readableCart = new  ReadableShoppingCart();
        
        readableShoppingCart.populate(cartModel, readableCart,  store, language);

		
		return readableCart;
		
	}


	private ReadableShoppingCart modifyCart(ShoppingCart cartModel, PersistableShoppingCartItem item, MerchantStore store,
			Language language) throws Exception {
		
		
		com.salesmanager.core.model.shoppingcart.ShoppingCartItem itemModel = createCartItem(cartModel, item, store);

        boolean itemModified = false;
        //check if existing product
       	Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> items = cartModel.getLineItems();
       	//com.salesmanager.core.model.shoppingcart.ShoppingCartItem affectedItem = null;
       	if(!CollectionUtils.isEmpty(items)) {
       		Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> newItems = new HashSet<com.salesmanager.core.model.shoppingcart.ShoppingCartItem>();
       		Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> removeItems = new HashSet<com.salesmanager.core.model.shoppingcart.ShoppingCartItem>();
	    	for(com.salesmanager.core.model.shoppingcart.ShoppingCartItem anItem : items) {//take care of existing product
	    		if(itemModel.getProduct().getId().longValue() == anItem.getProduct().getId()) {
	    			if(item.getQuantity()==0) {//left aside item to be removed
	    				//don't add it to new list of item
	    				removeItems.add(anItem);
	    			} else {
	    				//new quantity
	    				anItem.setQuantity(item.getQuantity());
	    				newItems.add(anItem);
	    			}
	    			itemModified = true;
	    		} else {
	    			newItems.add(anItem);
	    		}
	    	}
	    	
	    	if(!removeItems.isEmpty()) {
	    		for(com.salesmanager.core.model.shoppingcart.ShoppingCartItem emptyItem : removeItems) {
	    			shoppingCartService.deleteShoppingCartItem(emptyItem.getId());
	    		}
	    		
	    	}
	    	
	    	if(!itemModified) {
	    	  newItems.add(itemModel);
	    	}
	    	
	    	if(newItems.isEmpty()) {
	    		newItems = null;
	    	}
	    	
	    	cartModel.setLineItems(newItems);
       	} else {
           	//new item
             if(item.getQuantity() > 0) {
                cartModel.getLineItems().add( itemModel );
             }
       	}

       	//if cart items are null just return cart with no items

        saveShoppingCart( cartModel );

        //refresh cart
        cartModel = shoppingCartService.getById(cartModel.getId(), store);
        
        if(cartModel==null) {
        	return null;
        }

        shoppingCartCalculationService.calculate( cartModel, store, language );
        
        ReadableShoppingCartPopulator readableShoppingCart = new ReadableShoppingCartPopulator();
        
        readableShoppingCart.setImageUtils(imageUtils);
        readableShoppingCart.setPricingService(pricingService);
        readableShoppingCart.setProductAttributeService(productAttributeService);
        readableShoppingCart.setShoppingCartCalculationService(shoppingCartCalculationService);
  
        ReadableShoppingCart readableCart = new  ReadableShoppingCart();
        
        readableShoppingCart.populate(cartModel, readableCart,  store, language);

		
		return readableCart;
		
	}

	@Override
	public ReadableShoppingCart addToCart(Customer customer, PersistableShoppingCartItem item, MerchantStore store,
			Language language) throws Exception {
		
		Validate.notNull(customer,"Customer cannot be null");
		Validate.notNull(customer.getId(),"Customer.id cannot be null or empty");
		if(item.getQuantity() < 1) item.setQuantity(1);
		//Check if customer has an existing shopping cart
		ShoppingCart cartModel = shoppingCartService.getByCustomer(customer);
		
		//if cart does not exist create a new one
		if(cartModel==null) {
			cartModel = new ShoppingCart();
			cartModel.setCustomerId(customer.getId());
			cartModel.setMerchantStore(store);
			cartModel.setShoppingCartCode(uniqueShoppingCartCode());
		}
		
		return readableShoppingCart(cartModel,item,store,language);
	}
	
	@Override
	public ReadableShoppingCart modifyCart(String cartCode, PersistableShoppingCartItem item, MerchantStore store,
			Language language) throws Exception {

		Validate.notNull(cartCode,"PString cart code cannot be null");
		Validate.notNull(item,"PersistableShoppingCartItem cannot be null");
		
		ShoppingCart cartModel = this.getCartModel(cartCode, store);


		return modifyCart(cartModel,item, store, language);
		
		
	}
	
	private void saveShoppingCart(ShoppingCart shoppingCart) throws Exception {
		shoppingCartService.save(shoppingCart);
	}
	
	private String uniqueShoppingCartCode() {
		return UUID.randomUUID().toString().replaceAll( "-", "" );
	}

	@Override
	public ReadableShoppingCart getById(Long shoppingCartId, MerchantStore store, Language language) throws Exception {

		ShoppingCart cart = shoppingCartService.getById(shoppingCartId);
		
		ReadableShoppingCart readableCart = null;
		
		if(cart != null) {
			
	        ReadableShoppingCartPopulator readableShoppingCart = new ReadableShoppingCartPopulator();
	        
	        readableShoppingCart.setImageUtils(imageUtils);
	        readableShoppingCart.setPricingService(pricingService);
	        readableShoppingCart.setProductAttributeService(productAttributeService);
	        readableShoppingCart.setShoppingCartCalculationService(shoppingCartCalculationService);

	        readableShoppingCart.populate(cart, readableCart,  store, language);
			
			
		}
		
		return readableCart;
	}

	@Override
	public ShoppingCart getShoppingCartModel(Long id, MerchantStore store) throws Exception {
		return shoppingCartService.getById(id);
	}

	@Override
	public ReadableShoppingCart getByCode(String code, MerchantStore store, Language language) throws Exception {
		
		ShoppingCart cart = shoppingCartService.getByCode(code, store);
		
		ReadableShoppingCart readableCart = null;
		
		if(cart != null) {
			
	        ReadableShoppingCartPopulator readableShoppingCart = new ReadableShoppingCartPopulator();
	        
	        readableShoppingCart.setImageUtils(imageUtils);
	        readableShoppingCart.setPricingService(pricingService);
	        readableShoppingCart.setProductAttributeService(productAttributeService);
	        readableShoppingCart.setShoppingCartCalculationService(shoppingCartCalculationService);

	        readableCart = readableShoppingCart.populate(cart, null,  store, language);
			
			
		}
		
		return readableCart;
		
	}



}
