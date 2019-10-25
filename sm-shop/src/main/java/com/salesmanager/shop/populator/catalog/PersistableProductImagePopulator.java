package com.salesmanager.shop.populator.catalog;

import org.apache.commons.lang.Validate;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.PersistableImage;

public class PersistableProductImagePopulator extends AbstractDataPopulator<PersistableImage, ProductImage> {

	
	private Product product;
	
	@Override
	public ProductImage populate(PersistableImage source, ProductImage target, MerchantStore store, Language language)
			throws ConversionException {
		
		Validate.notNull(product,"Must set a product setProduct(Product)");
		Validate.notNull(product.getId(),"Product must have an id not null");
		Validate.notNull(source.getContentType(),"Content type must be set on persistable image");

		
		target.setDefaultImage(source.isDefaultImage());
		target.setImageType(source.getImageType());
		target.setProductImage(source.getName());
		if(source.getImageUrl() != null) {
			target.setProductImageUrl(source.getImageUrl());
		}
		target.setProduct(product);
		
		return target;
	}

	@Override
	protected ProductImage createTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
