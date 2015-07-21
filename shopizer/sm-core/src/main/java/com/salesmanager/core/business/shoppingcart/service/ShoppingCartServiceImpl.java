package com.salesmanager.core.business.shoppingcart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute;
import com.salesmanager.core.business.catalog.product.model.price.FinalPrice;
import com.salesmanager.core.business.catalog.product.service.PricingService;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.catalog.product.service.attribute.ProductAttributeService;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.shipping.model.ShippingProduct;
import com.salesmanager.core.business.shoppingcart.dao.ShoppingCartDao;
import com.salesmanager.core.business.shoppingcart.dao.ShoppingCartItemDao;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCart;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCartAttributeItem;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCartItem;

@Service("shoppingCartService")
public class ShoppingCartServiceImpl extends SalesManagerEntityServiceImpl<Long, ShoppingCart> implements ShoppingCartService {


	private final ShoppingCartDao shoppingCartDao;

	@Autowired
	private ProductService productService;

	@Autowired
	private ShoppingCartItemDao shoppingCartItemDao;

	@Autowired
	private PricingService pricingService;

	@Autowired
    private ProductAttributeService productAttributeService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

	@Autowired
	public ShoppingCartServiceImpl(
			final ShoppingCartDao shoppingCartDao) {
		super(shoppingCartDao);
		this.shoppingCartDao = shoppingCartDao;

	}


	/**
	 * Retrieve a {@link ShoppingCart} cart for a given customer
	 */
	@Override
    @Transactional
	public ShoppingCart getShoppingCart(final Customer customer) throws ServiceException {

		try {

			ShoppingCart shoppingCart = shoppingCartDao.getByCustomer(customer);
			populateShoppingCart(shoppingCart);
			if(shoppingCart!=null && shoppingCart.isObsolete()) {
				delete(shoppingCart);
				return null;
			} else {
				return shoppingCart;
			}


		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	/**
	 * Save or update a {@link ShoppingCart} for a given customer
	 */
	@Override
    public void saveOrUpdate(final ShoppingCart shoppingCart) throws ServiceException {
		if(shoppingCart.getId()==null || shoppingCart.getId().longValue()==0) {
			super.create(shoppingCart);
		} else {
			super.update(shoppingCart);
		}
	}

	/**
	 * Get a {@link ShoppingCart} for a given id and MerchantStore. Will update the shopping cart
	 * prices and items based on the actual inventory. This method will remove the shopping cart if
	 * no items are attached.
	 */
	@Override
	@Transactional
	public ShoppingCart getById(final Long id, final MerchantStore store) throws ServiceException {

		try {
			ShoppingCart shoppingCart = shoppingCartDao.getById(id, store);
			if(shoppingCart==null) {
				return null;
			}
			populateShoppingCart(shoppingCart);

			if(shoppingCart.isObsolete()) {
				delete(shoppingCart);
				return null;
			} else {
				return shoppingCart;
			}


		} catch (Exception e) {
			throw new ServiceException(e);
		}


	}

	/**
	 * Get a {@link ShoppingCart} for a given id. Will update the shopping cart
	 * prices and items based on the actual inventory. This method will remove the shopping cart if
	 * no items are attached.
	 */
	@Override
	public ShoppingCart getById(final Long id) {


		try {
			ShoppingCart shoppingCart = shoppingCartDao.getById(id);
			if(shoppingCart==null) {
				return null;
			}
			populateShoppingCart(shoppingCart);


			if(shoppingCart.isObsolete()) {
				delete(shoppingCart);
				return null;
			} else {
				return shoppingCart;
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

	}

	/**
	 * Get a {@link ShoppingCart} for a given code. Will update the shopping cart
	 * prices and items based on the actual inventory. This method will remove the shopping cart if
	 * no items are attached.
	 */
	@Override
	@Transactional
	public ShoppingCart getByCode(final String code, final MerchantStore store) throws ServiceException {

		try {
			ShoppingCart shoppingCart = shoppingCartDao.getByCode(code, store);
			if(shoppingCart==null) {
				return null;
			}
			populateShoppingCart(shoppingCart);


			if(shoppingCart.isObsolete()) {
				delete(shoppingCart);
				return null;
			} else {
				return shoppingCart;
			}

		}catch(javax.persistence.NoResultException nre) {
			return null;
		} catch (RuntimeException e) {
			throw new ServiceException(e);
		} catch (Exception ee) {
			throw new ServiceException(ee);
		} catch (Throwable t) {
			throw new ServiceException(t);
		} 


	}

	@Override
	public void deleteCart(final ShoppingCart shoppingCart) throws ServiceException {
		ShoppingCart cart = this.getById(shoppingCart.getId());
		if(cart!=null) {
			super.delete(cart);
		}
	}


	@Override
	public ShoppingCart getByCustomer(final Customer customer) throws ServiceException {

		try {
			ShoppingCart shoppingCart = shoppingCartDao.getByCustomer(customer);
			if(shoppingCart==null) {
				return null;
			}
			return populateShoppingCart(shoppingCart);


		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional(noRollbackFor={org.springframework.dao.EmptyResultDataAccessException.class})
	private ShoppingCart populateShoppingCart(final ShoppingCart shoppingCart) throws Exception {

		try {

			boolean cartIsObsolete = true;
			if(shoppingCart!=null) {

				Set<ShoppingCartItem> items = shoppingCart.getLineItems();
				if(items==null || items.size()==0) {
					shoppingCart.setObsolete(true);
					return shoppingCart;

				}

				//Set<ShoppingCartItem> shoppingCartItems = new HashSet<ShoppingCartItem>();
				for(ShoppingCartItem item : items) {
					LOGGER.debug("Populate item " + item.getId());
					populateItem(item);
					LOGGER.debug("Obsolete item ? " + item.isObsolete());
					if(item.isObsolete()) {
					} else {
						cartIsObsolete = false;
					}
				}

				//shoppingCart.setLineItems(shoppingCartItems);
				boolean refreshCart = false;
                Set<ShoppingCartItem> refreshedItems = new HashSet<ShoppingCartItem>();
                for(ShoppingCartItem item : items) {
                	if(!item.isObsolete()) {
                		refreshedItems.add(item);
                	} else {
                		refreshCart = true;
                	}
                }

                if(refreshCart) {
                	shoppingCart.setLineItems(refreshedItems);
                	update(shoppingCart);
                }

				if(cartIsObsolete) {
					shoppingCart.setObsolete(true);
				}
				return shoppingCart;
			}

		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return shoppingCart;

	}

	@Override
	public ShoppingCartItem populateShoppingCartItem(final Product product) throws ServiceException {
		Validate.notNull(product, "Product should not be null");
		Validate.notNull(product.getMerchantStore(), "Product.merchantStore should not be null");


		ShoppingCartItem item = new ShoppingCartItem(product);

		//Set<ProductAttribute> productAttributes = product.getAttributes();
		//Set<ShoppingCartAttributeItem> attributesList = new HashSet<ShoppingCartAttributeItem>();
		//if(!CollectionUtils.isEmpty(productAttributes)) {

		//	for(ProductAttribute productAttribute : productAttributes) {
		//			ShoppingCartAttributeItem attributeItem = new ShoppingCartAttributeItem();
		//			attributeItem.setShoppingCartItem(item);
		//			attributeItem.setProductAttribute(productAttribute);
		//			attributeItem.setProductAttributeId(productAttribute.getId());
		//			attributesList.add(attributeItem);

		//	}

		//	item.setAttributes(attributesList);
		//}
		
		item.setProductVirtual(product.isProductVirtual());

		//set item price
		FinalPrice price = pricingService.calculateProductPrice(product);
		item.setItemPrice(price.getFinalPrice());
		return item;


	}


	private void populateItem(final ShoppingCartItem item) throws Exception {

		Product product = null;

		Long productId = item.getProductId();
		product = productService.getById(productId);

		if(product==null) {
			item.setObsolete(true);
			return;
		}


		item.setProduct(product);
		
		if(product.isProductVirtual()) {
			item.setProductVirtual(true);
		}

		Set<ShoppingCartAttributeItem> attributes = item.getAttributes();
		Set<ProductAttribute> productAttributes = product.getAttributes();
		List<ProductAttribute> attributesList = new ArrayList<ProductAttribute>();
		if(productAttributes!=null && productAttributes.size()>0 && attributes!=null && attributes.size()>0) {
			for(ShoppingCartAttributeItem attribute : attributes) {
				long attributeId = attribute.getProductAttributeId().longValue();
				for(ProductAttribute productAttribute : productAttributes) {

					if(productAttribute.getId().longValue()==attributeId) {
						attribute.setProductAttribute(productAttribute);
						attributesList.add(productAttribute);
						break;
					}

				}

			}
		}

		//set item price
		FinalPrice price = pricingService.calculateProductPrice(product, attributesList);
		item.setItemPrice(price.getFinalPrice());
		item.setFinalPrice(price);



		BigDecimal subTotal = item.getItemPrice().multiply(new BigDecimal(item.getQuantity().intValue()));
		item.setSubTotal(subTotal);


	}

	@Override
	public List<ShippingProduct> createShippingProduct(final ShoppingCart cart) throws ServiceException {
		/**
		 * Determines if products are virtual
		 */
		Set<ShoppingCartItem> items = cart.getLineItems();
		List<ShippingProduct> shippingProducts = null;
		for(ShoppingCartItem item : items) {
			Product product = item.getProduct();
			if(!product.isProductVirtual() && product.isProductShipeable()) {
				if(shippingProducts==null) {
					shippingProducts = new ArrayList<ShippingProduct>();
				}
				ShippingProduct shippingProduct = new ShippingProduct(product);
				shippingProduct.setQuantity(item.getQuantity());
				shippingProduct.setFinalPrice(item.getFinalPrice());
				shippingProducts.add(shippingProduct);
			}
		}

		return shippingProducts;

	}


	@Override
	public boolean isFreeShoppingCart(final ShoppingCart cart) throws ServiceException {
		/**
		 * Determines if products are free
		 */
		Set<ShoppingCartItem> items = cart.getLineItems();
		for(ShoppingCartItem item : items) {
			Product product = item.getProduct();
			FinalPrice finalPrice = pricingService.calculateProductPrice(product);
			if(finalPrice.getFinalPrice().longValue()>0) {
				return false;
			}
		}
		
		return true;

	}
	
	@Override
	public boolean requiresShipping(final ShoppingCart cart) throws ServiceException {
		
		Validate.notNull(cart,"Shopping cart cannot be null");
		Validate.notNull(cart.getLineItems(),"ShoppingCart items cannot be null");
		boolean requiresShipping = false;
		for(ShoppingCartItem item : cart.getLineItems()) {
			Product product = item.getProduct();
			if(product.isProductShipeable()) {
				requiresShipping = true;
				break;
			}
		}
		
		return requiresShipping;
		
	}

    @Override
    public void  removeShoppingCart( final ShoppingCart cart )
        throws ServiceException
    {
         shoppingCartDao.removeShoppingCart( cart );
    }


    @Override
    public ShoppingCart mergeShoppingCarts( final ShoppingCart userShoppingModel, final ShoppingCart sessionCart,final MerchantStore store ) throws Exception
    {
        if(sessionCart.getCustomerId() !=null && sessionCart.getCustomerId() ==userShoppingModel.getCustomerId() ){
            LOGGER.info( "Session Shopping cart belongs to same logged in user" );
            if(CollectionUtils.isNotEmpty( userShoppingModel.getLineItems() ) && CollectionUtils.isNotEmpty( sessionCart.getLineItems() )){
                return userShoppingModel;
            }
        }

        LOGGER.info( "Starting merging shopping carts" );
        if(CollectionUtils.isNotEmpty( sessionCart.getLineItems() )){
            Set<ShoppingCartItem> shoppingCartItemsSet=getShoppingCartItems(sessionCart,store,userShoppingModel);
            boolean duplicateFound = false;
            if(CollectionUtils.isNotEmpty(shoppingCartItemsSet)) {
                for(ShoppingCartItem sessionShoppingCartItem : shoppingCartItemsSet){
                    if(CollectionUtils.isNotEmpty(userShoppingModel.getLineItems())){
                        for(ShoppingCartItem cartItem : userShoppingModel.getLineItems()){
                            if(cartItem.getProduct().getId().longValue()==sessionShoppingCartItem.getProduct().getId().longValue()) {
                                if(CollectionUtils.isNotEmpty(cartItem.getAttributes())) {
                                    if(!duplicateFound) {
                                        LOGGER.info( "Dupliate item found..updating exisitng product quantity" );
                                        cartItem.setQuantity(cartItem.getQuantity() + sessionShoppingCartItem.getQuantity());
                                        duplicateFound = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if(!duplicateFound) {
                        LOGGER.info( "New item found..adding item to Shopping cart" );
                        userShoppingModel.getLineItems().add( sessionShoppingCartItem );
                    }
                }

            }

        }
        LOGGER.info( "Shopping Cart merged successfully.....");
        saveOrUpdate( userShoppingModel );
        removeShoppingCart( sessionCart );

        return userShoppingModel;
    }



    private Set<ShoppingCartItem> getShoppingCartItems( final ShoppingCart sessionCart,final MerchantStore store,final ShoppingCart cartModel )
                    throws Exception
                {

                    Set<ShoppingCartItem> shoppingCartItemsSet=null;
                    if(CollectionUtils.isNotEmpty( sessionCart.getLineItems() )){
                        shoppingCartItemsSet=new HashSet<ShoppingCartItem>();
                        for(ShoppingCartItem shoppingCartItem : sessionCart.getLineItems()){
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

                            ShoppingCartItem item= populateShoppingCartItem( product );
                            item.setQuantity( shoppingCartItem.getQuantity() );
                            item.setShoppingCart( cartModel );

                            List<ShoppingCartAttributeItem> cartAttributes = new ArrayList<ShoppingCartAttributeItem>( shoppingCartItem.getAttributes() );
                            if(CollectionUtils.isNotEmpty( cartAttributes )){
                                for(ShoppingCartAttributeItem shoppingCartAttributeItem :cartAttributes ){
                                    ProductAttribute productAttribute =productAttributeService.getById( shoppingCartAttributeItem.getId() );
                                    if ( productAttribute != null
                                                    && productAttribute.getProduct().getId().longValue() == product.getId().longValue() ){

                                        ShoppingCartAttributeItem attributeItem=new ShoppingCartAttributeItem(item,productAttribute);
                                        if ( shoppingCartAttributeItem.getId() > 0 )
                                        {
                                            attributeItem.setId( shoppingCartAttributeItem.getId() );
                                        }
                                        item.addAttributes( attributeItem );

                                    }
                                }
                            }

                            shoppingCartItemsSet.add( item );
                        }

                    }
                     return shoppingCartItemsSet;
              }


	@Override
	public boolean isFreeShoppingCart(List<ShoppingCartItem> items)
			throws ServiceException {
		ShoppingCart cart = new ShoppingCart();
		Set<ShoppingCartItem> cartItems = new HashSet<ShoppingCartItem>(items);
		cart.setLineItems(cartItems);
		return this.isFreeShoppingCart(cart);
	}


}
