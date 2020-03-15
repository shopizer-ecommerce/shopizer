package com.salesmanager.shop.populator.order;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.catalog.product.file.DigitalProductService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.file.DigitalProduct;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.orderproduct.OrderProduct;
import com.salesmanager.core.model.order.orderproduct.OrderProductAttribute;
import com.salesmanager.core.model.order.orderproduct.OrderProductDownload;
import com.salesmanager.core.model.order.orderproduct.OrderProductPrice;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.ApplicationConstants;
import com.salesmanager.shop.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.shop.model.order.PersistableOrderProduct;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersistableOrderProductPopulator extends
		AbstractDataPopulator<PersistableOrderProduct, OrderProduct> {
	
	private ProductService productService;
	private DigitalProductService digitalProductService;
	private ProductAttributeService productAttributeService;


	public ProductAttributeService getProductAttributeService() {
		return productAttributeService;
	}

	public void setProductAttributeService(
			ProductAttributeService productAttributeService) {
		this.productAttributeService = productAttributeService;
	}

	public DigitalProductService getDigitalProductService() {
		return digitalProductService;
	}

	public void setDigitalProductService(DigitalProductService digitalProductService) {
		this.digitalProductService = digitalProductService;
	}

	/**
	 * Converts a ShoppingCartItem carried in the ShoppingCart to an OrderProduct
	 * that will be saved in the system
	 */
	@Override
	public OrderProduct populate(PersistableOrderProduct source, OrderProduct target,
			MerchantStore store, Language language) throws ConversionException {
		
		Validate.notNull(productService,"productService must be set");
		Validate.notNull(digitalProductService,"digitalProductService must be set");
		Validate.notNull(productAttributeService,"productAttributeService must be set");

		
		try {
			Product modelProduct = productService.getById(source.getProduct().getId());
			if(modelProduct==null) {
				throw new ConversionException("Cannot get product with id (productId) " + source.getProduct().getId());
			}
			
			if(modelProduct.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				throw new ConversionException("Invalid product id " + source.getProduct().getId());
			}

			DigitalProduct digitalProduct = digitalProductService.getByProduct(store, modelProduct);
			
			if(digitalProduct!=null) {
				OrderProductDownload orderProductDownload = new OrderProductDownload();	
				orderProductDownload.setOrderProductFilename(digitalProduct.getProductFileName());
				orderProductDownload.setOrderProduct(target);
				orderProductDownload.setDownloadCount(0);
				orderProductDownload.setMaxdays(ApplicationConstants.MAX_DOWNLOAD_DAYS);
				target.getDownloads().add(orderProductDownload);
			}

			target.setOneTimeCharge(source.getPrice());	
			target.setProductName(source.getProduct().getDescription().getName());
			target.setProductQuantity(source.getOrderedQuantity());
			target.setSku(source.getProduct().getSku());
			
			OrderProductPrice orderProductPrice = new OrderProductPrice();
			orderProductPrice.setDefaultPrice(true);
			orderProductPrice.setProductPrice(source.getPrice());
			orderProductPrice.setOrderProduct(target);
			

			
			Set<OrderProductPrice> prices = new HashSet<OrderProductPrice>();
			prices.add(orderProductPrice);

			/** DO NOT SUPPORT MUTIPLE PRICES **/
/*			//Other prices
			List<FinalPrice> otherPrices = finalPrice.getAdditionalPrices();
			if(otherPrices!=null) {
				for(FinalPrice otherPrice : otherPrices) {
					OrderProductPrice other = orderProductPrice(otherPrice);
					other.setOrderProduct(target);
					prices.add(other);
				}
			}*/
			
			target.setPrices(prices);
			
			//OrderProductAttribute
			List<ProductAttribute> attributeItems = source.getAttributes();
			if(!CollectionUtils.isEmpty(attributeItems)) {
				Set<OrderProductAttribute> attributes = new HashSet<OrderProductAttribute>();
				for(ProductAttribute attribute : attributeItems) {
					OrderProductAttribute orderProductAttribute = new OrderProductAttribute();
					orderProductAttribute.setOrderProduct(target);
					Long id = attribute.getId();
					com.salesmanager.core.model.catalog.product.attribute.ProductAttribute attr = productAttributeService.getById(id);
					if(attr==null) {
						throw new ConversionException("Attribute id " + id + " does not exists");
					}
					
					if(attr.getProduct().getMerchantStore().getId().intValue()!=store.getId().intValue()) {
						throw new ConversionException("Attribute id " + id + " invalid for this store");
					}
					
					orderProductAttribute.setProductAttributeIsFree(attr.getProductAttributeIsFree());
					orderProductAttribute.setProductAttributeName(attr.getProductOption().getDescriptionsSettoList().get(0).getName());
					orderProductAttribute.setProductAttributeValueName(attr.getProductOptionValue().getDescriptionsSettoList().get(0).getName());
					orderProductAttribute.setProductAttributePrice(attr.getProductAttributePrice());
					orderProductAttribute.setProductAttributeWeight(attr.getProductAttributeWeight());
					orderProductAttribute.setProductOptionId(attr.getProductOption().getId());
					orderProductAttribute.setProductOptionValueId(attr.getProductOptionValue().getId());
					attributes.add(orderProductAttribute);
				}
				target.setOrderAttributes(attributes);
			}

			
		} catch (Exception e) {
			throw new ConversionException(e);
		}
		
		
		return target;
	}

	@Override
	protected OrderProduct createTarget() {
		return null;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public ProductService getProductService() {
		return productService;
	}
	


}
