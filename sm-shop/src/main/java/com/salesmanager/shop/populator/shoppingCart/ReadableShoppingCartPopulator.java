package com.salesmanager.shop.populator.shoppingCart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartCalculationService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValueDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderSummary;
import com.salesmanager.core.model.order.OrderTotalSummary;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.shop.model.order.total.ReadableOrderTotal;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCart;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCartAttribute;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCartAttributeOption;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCartAttributeOptionValue;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCartItem;
import com.salesmanager.shop.populator.catalog.ReadableProductPopulator;
import com.salesmanager.shop.utils.ImageFilePath;

public class ReadableShoppingCartPopulator extends AbstractDataPopulator<ShoppingCart, ReadableShoppingCart> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReadableShoppingCartPopulator.class);
	
	private PricingService pricingService;
    private ShoppingCartCalculationService shoppingCartCalculationService;
    private ProductAttributeService productAttributeService;
    
    private ImageFilePath imageUtils;
	
	@Override
	public ReadableShoppingCart populate(ShoppingCart source, ReadableShoppingCart target, MerchantStore store,
			Language language) throws ConversionException {
    	Validate.notNull(source, "Requires ShoppingCart");
    	Validate.notNull(language, "Requires Language not null");
    	Validate.notNull(store, "Requires MerchantStore not null");
    	Validate.notNull(pricingService, "Requires to set pricingService");
    	Validate.notNull(productAttributeService, "Requires to set productAttributeService");
    	Validate.notNull(shoppingCartCalculationService, "Requires to set shoppingCartCalculationService");
    	Validate.notNull(imageUtils, "Requires to set imageUtils");
    	
    	if(target == null) {
    		target = new ReadableShoppingCart();
    	}
    	target.setCode(source.getShoppingCartCode());
    	int cartQuantity = 0;
    	
    	target.setCustomer(source.getCustomerId());
    	
    	try {
    	
    		Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> items = source.getLineItems();

            if(items!=null) {

                for(com.salesmanager.core.model.shoppingcart.ShoppingCartItem item : items) {


                	ReadableShoppingCartItem shoppingCartItem = new ReadableShoppingCartItem();

                	ReadableProductPopulator readableProductPopulator = new ReadableProductPopulator();
                	readableProductPopulator.setPricingService(pricingService);
                	readableProductPopulator.setimageUtils(imageUtils);
                	readableProductPopulator.populate(item.getProduct(), shoppingCartItem,  store, language);



                    shoppingCartItem.setPrice(item.getItemPrice());
					shoppingCartItem.setFinalPrice(pricingService.getDisplayAmount(item.getItemPrice(),store));
			
                    shoppingCartItem.setQuantity(item.getQuantity());
                    
                    cartQuantity = cartQuantity + item.getQuantity();
                    
                    BigDecimal subTotal = pricingService.calculatePriceQuantity(item.getItemPrice(), item.getQuantity());
                    
                    //calculate sub total (price * quantity)
                    shoppingCartItem.setSubTotal(subTotal);

					shoppingCartItem.setDisplaySubTotal(pricingService.getDisplayAmount(subTotal,store));


                    Set<com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem> attributes = item.getAttributes();
                    if(attributes!=null) {
                        for(com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem attribute : attributes) {

                        	ProductAttribute productAttribute = productAttributeService.getById(attribute.getProductAttributeId());
                        	
                        	if(productAttribute==null) {
                        		LOGGER.warn("Product attribute with ID " + attribute.getId() + " not found, skipping cart attribute " + attribute.getId());
                        		continue;
                        	}
                        	
                        	ReadableShoppingCartAttribute cartAttribute = new ReadableShoppingCartAttribute();
                        	

                            cartAttribute.setId(attribute.getId());
                            
                            ProductOption option = productAttribute.getProductOption();
                            ProductOptionValue optionValue = productAttribute.getProductOptionValue();


                            List<ProductOptionDescription> optionDescriptions = option.getDescriptionsSettoList();
                            List<ProductOptionValueDescription> optionValueDescriptions = optionValue.getDescriptionsSettoList();
                            
                            String optName = null;
                            String optValue = null;
                            if(!CollectionUtils.isEmpty(optionDescriptions) && !CollectionUtils.isEmpty(optionValueDescriptions)) {
                            	
                            	optName = optionDescriptions.get(0).getName();
                            	optValue = optionValueDescriptions.get(0).getName();
                            	
                            	for(ProductOptionDescription optionDescription : optionDescriptions) {
                            		if(optionDescription.getLanguage() != null && optionDescription.getLanguage().getId().intValue() == language.getId().intValue()) {
                            			optName = optionDescription.getName();
                            			break;
                            		}
                            	}
                            	
                            	for(ProductOptionValueDescription optionValueDescription : optionValueDescriptions) {
                            		if(optionValueDescription.getLanguage() != null && optionValueDescription.getLanguage().getId().intValue() == language.getId().intValue()) {
                            			optValue = optionValueDescription.getName();
                            			break;
                            		}
                            	}

                            }
                            
                            if(optName != null) {
                            	ReadableShoppingCartAttributeOption attributeOption = new ReadableShoppingCartAttributeOption();
                            	attributeOption.setCode(option.getCode());
                            	attributeOption.setId(option.getId());
                            	attributeOption.setName(optName);
                            	cartAttribute.setOption(attributeOption);
                            }
                            
                            if(optValue != null) {
                            	ReadableShoppingCartAttributeOptionValue attributeOptionValue = new ReadableShoppingCartAttributeOptionValue();
                            	attributeOptionValue.setCode(optionValue.getCode());
                            	attributeOptionValue.setId(optionValue.getId());
                            	attributeOptionValue.setName(optValue);
                            	cartAttribute.setOptionValue(attributeOptionValue);
                            }
                            shoppingCartItem.getCartItemattributes().add(cartAttribute);  
                        }
                       
                    }
                    target.getProducts().add(shoppingCartItem);
                }
            }
            
            //Calculate totals using shoppingCartService
            //OrderSummary contains ShoppingCart items

            OrderSummary summary = new OrderSummary();
            List<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> productsList = new ArrayList<com.salesmanager.core.model.shoppingcart.ShoppingCartItem>();
            productsList.addAll(source.getLineItems());
            summary.setProducts(productsList);
            
            //OrdetTotalSummary contains all calculations
            
            OrderTotalSummary orderSummary = shoppingCartCalculationService.calculate(source, store, language );

            if(CollectionUtils.isNotEmpty(orderSummary.getTotals())) {
            	List<ReadableOrderTotal> totals = new ArrayList<ReadableOrderTotal>();
            	for(com.salesmanager.core.model.order.OrderTotal t : orderSummary.getTotals()) {
            		ReadableOrderTotal total = new ReadableOrderTotal();
            		total.setCode(t.getOrderTotalCode());
            		total.setValue(t.getValue());
            		total.setText(t.getText());
            		totals.add(total);
            	}
            	target.setTotals(totals);
            }
            
            target.setSubtotal(orderSummary.getSubTotal());
            target.setDisplaySubTotal(pricingService.getDisplayAmount(orderSummary.getSubTotal(), store));
           
            
            target.setTotal(orderSummary.getTotal());
            target.setDisplayTotal(pricingService.getDisplayAmount(orderSummary.getTotal(), store));

            
            target.setQuantity(cartQuantity);
            target.setId(source.getId());
            
            if(source.getOrderId() != null) {
            	target.setOrder(source.getOrderId());
            }
            
            
    	} catch(Exception e) {
    		throw new ConversionException(e);
    	}

        return target;
    	
 
	}

	@Override
	protected ReadableShoppingCart createTarget() {
		return null;
	}

	public PricingService getPricingService() {
		return pricingService;
	}

	public void setPricingService(PricingService pricingService) {
		this.pricingService = pricingService;
	}

	public ShoppingCartCalculationService getShoppingCartCalculationService() {
		return shoppingCartCalculationService;
	}

	public void setShoppingCartCalculationService(ShoppingCartCalculationService shoppingCartCalculationService) {
		this.shoppingCartCalculationService = shoppingCartCalculationService;
	}

	public ImageFilePath getImageUtils() {
		return imageUtils;
	}

	public void setImageUtils(ImageFilePath imageUtils) {
		this.imageUtils = imageUtils;
	}

	public ProductAttributeService getProductAttributeService() {
		return productAttributeService;
	}

	public void setProductAttributeService(ProductAttributeService productAttributeService) {
		this.productAttributeService = productAttributeService;
	}

}
