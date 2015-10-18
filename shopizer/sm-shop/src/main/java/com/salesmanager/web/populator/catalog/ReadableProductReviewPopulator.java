package com.salesmanager.web.populator.catalog;

import java.util.Set;


import com.salesmanager.core.business.catalog.product.model.review.ProductReview;
import com.salesmanager.core.business.catalog.product.model.review.ProductReviewDescription;
import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.utils.AbstractDataPopulator;
import com.salesmanager.web.entity.catalog.product.ReadableProductReview;
import com.salesmanager.web.entity.customer.ReadableCustomer;
import com.salesmanager.web.populator.customer.ReadableCustomerPopulator;
import com.salesmanager.web.utils.DateUtil;

public class ReadableProductReviewPopulator extends
		AbstractDataPopulator<ProductReview, ReadableProductReview> {

	@Override
	public ReadableProductReview populate(ProductReview source,
			ReadableProductReview target, MerchantStore store, Language language)
			throws ConversionException {

		
		try {
			ReadableCustomerPopulator populator = new ReadableCustomerPopulator();
			ReadableCustomer customer = new ReadableCustomer();
			populator.populate(source.getCustomer(), customer, store, language);

			target.setDate(DateUtil.formatDate(source.getReviewDate()));
			target.setCustomer(customer);
			target.setRating(source.getReviewRating());
			target.setProductId(source.getProduct().getId());
			
			Set<ProductReviewDescription> descriptions = source.getDescriptions();
			if(descriptions!=null) {
				for(ProductReviewDescription description : descriptions) {
					target.setDescription(description.getDescription());
					target.setLanguage(description.getLanguage().getCode());
					break;
				}
			}

			return target;
			
		} catch (Exception e) {
			throw new ConversionException("Cannot populate ProductReview", e);
		}
		
		
		
	}

	@Override
	protected ReadableProductReview createTarget() {
		return null;
	}

}
