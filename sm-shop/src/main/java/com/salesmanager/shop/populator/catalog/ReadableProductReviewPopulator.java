package com.salesmanager.shop.populator.catalog;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.product.review.ProductReview;
import com.salesmanager.core.model.catalog.product.review.ProductReviewDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableProductReview;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.populator.customer.ReadableCustomerPopulator;
import com.salesmanager.shop.utils.DateUtil;
import java.util.Set;


public class ReadableProductReviewPopulator extends
    AbstractDataPopulator<ProductReview, ReadableProductReview> {

  @Override
  public ReadableProductReview populate(ProductReview source,
      ReadableProductReview target, MerchantStore store, Language language)
      throws ServiceException {

    ReadableCustomerPopulator populator = new ReadableCustomerPopulator();
    ReadableCustomer customer = new ReadableCustomer();
    populator.populate(source.getCustomer(), customer, store, language);

    target.setId(source.getId());
    target.setDate(DateUtil.formatDate(source.getReviewDate()));
    target.setCustomer(customer);
    target.setRating(source.getReviewRating());
    target.setProductId(source.getProduct().getId());

    Set<ProductReviewDescription> descriptions = source.getDescriptions();
    if (descriptions != null) {
      for (ProductReviewDescription description : descriptions) {
        target.setDescription(description.getDescription());
        target.setLanguage(description.getLanguage().getCode());
        break;
      }
    }

    return target;
  }

  @Override
  protected ReadableProductReview createTarget() {
    return null;
  }
}
