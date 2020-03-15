package com.salesmanager.shop.populator.order;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.orderproduct.OrderProduct;
import com.salesmanager.core.model.order.orderproduct.OrderProductAttribute;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.order.ReadableOrderProduct;
import com.salesmanager.shop.model.order.ReadableOrderProductAttribute;
import com.salesmanager.shop.populator.catalog.ReadableProductPopulator;
import com.salesmanager.shop.utils.ImageFilePath;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReadableOrderProductPopulator extends
		AbstractDataPopulator<OrderProduct, ReadableOrderProduct> {
	
	private ProductService productService;
	private PricingService pricingService;
	private ImageFilePath imageUtils;



	public ImageFilePath getimageUtils() {
		return imageUtils;
	}

	public void setimageUtils(ImageFilePath imageUtils) {
		this.imageUtils = imageUtils;
	}

	@Override
	public ReadableOrderProduct populate(OrderProduct source,
			ReadableOrderProduct target, MerchantStore store, Language language)
			throws ConversionException {
		
		Validate.notNull(productService,"Requires ProductService");
		Validate.notNull(pricingService,"Requires PricingService");
		Validate.notNull(imageUtils,"Requires imageUtils");
		target.setId(source.getId());
		target.setOrderedQuantity(source.getProductQuantity());
		try {
			target.setPrice(pricingService.getDisplayAmount(source.getOneTimeCharge(), store));
		} catch(Exception e) {
			throw new ConversionException("Cannot convert price",e);
		}
		target.setProductName(source.getProductName());
		target.setSku(source.getSku());
		
		//subtotal = price * quantity
		BigDecimal subTotal = source.getOneTimeCharge();
		subTotal = subTotal.multiply(new BigDecimal(source.getProductQuantity()));
		
		try {
			String subTotalPrice = pricingService.getDisplayAmount(subTotal, store);
			target.setSubTotal(subTotalPrice);
		} catch(Exception e) {
			throw new ConversionException("Cannot format price",e);
		}
		
		if(source.getOrderAttributes()!=null) {
			List<ReadableOrderProductAttribute> attributes = new ArrayList<ReadableOrderProductAttribute>();
			for(OrderProductAttribute attr : source.getOrderAttributes()) {
				ReadableOrderProductAttribute readableAttribute = new ReadableOrderProductAttribute();
				try {
					String price = pricingService.getDisplayAmount(attr.getProductAttributePrice(), store);
					readableAttribute.setAttributePrice(price);
				} catch (ServiceException e) {
					throw new ConversionException("Cannot format price",e);
				}
				
				readableAttribute.setAttributeName(attr.getProductAttributeName());
				readableAttribute.setAttributeValue(attr.getProductAttributeValueName());
				attributes.add(readableAttribute);
			}
			target.setAttributes(attributes);
		}
		

			String productSku = source.getSku();
			if(!StringUtils.isBlank(productSku)) {
				Product product = productService.getByCode(productSku, language);
				if(product!=null) {
					
					
					
					ReadableProductPopulator populator = new ReadableProductPopulator();
					populator.setPricingService(pricingService);
					populator.setimageUtils(imageUtils);
					
					ReadableProduct productProxy = populator.populate(product, new ReadableProduct(), store, language);
					target.setProduct(productProxy);
					
					Set<ProductImage> images = product.getImages();
					ProductImage defaultImage = null;
					if(images!=null) {
						for(ProductImage image : images) {
							if(defaultImage==null) {
								defaultImage = image;
							}
							if(image.isDefaultImage()) {
								defaultImage = image;
							}
						}
					}
					if(defaultImage!=null) {
						target.setImage(defaultImage.getProductImage());
					}
				}
			}
		
		
		return target;
	}

	@Override
	protected ReadableOrderProduct createTarget() {

		return null;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public PricingService getPricingService() {
		return pricingService;
	}

	public void setPricingService(PricingService pricingService) {
		this.pricingService = pricingService;
	}

}
