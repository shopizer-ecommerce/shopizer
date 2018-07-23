package com.salesmanager.shop.populator.customer;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.customer.review.CustomerReview;
import com.salesmanager.core.model.customer.review.CustomerReviewDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.model.customer.ReadableCustomerReview;
import com.salesmanager.shop.utils.DateUtil;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;


public class ReadableCustomerReviewPopulator extends
    AbstractDataPopulator<CustomerReview, ReadableCustomerReview> {

  @Override
  public ReadableCustomerReview populate(CustomerReview source, ReadableCustomerReview target,
      MerchantStore store, Language language) throws ServiceException {

    if (target == null) {
      target = new ReadableCustomerReview();
    }

    if (source.getReviewDate() != null) {
      target.setDate(DateUtil.formatDate(source.getReviewDate()));
    }

    ReadableCustomer reviewed = new ReadableCustomer();
    reviewed.setId(source.getReviewedCustomer().getId());
    reviewed.setFirstName(source.getReviewedCustomer().getBilling().getFirstName());
    reviewed.setLastName(source.getReviewedCustomer().getBilling().getLastName());

    target.setId(source.getId());
    target.setCustomerId(source.getCustomer().getId());
    target.setReviewedCustomer(reviewed);
    target.setRating(source.getReviewRating());
    target.setReviewedCustomer(reviewed);
    target.setCustomerId(source.getCustomer().getId());

    Set<CustomerReviewDescription> descriptions = source.getDescriptions();
    if (CollectionUtils.isNotEmpty(descriptions)) {
      CustomerReviewDescription description = null;
      if (descriptions.size() > 1) {
        for (CustomerReviewDescription desc : descriptions) {
          if (desc.getLanguage().getCode().equals(language.getCode())) {
            description = desc;
            break;
          }
        }
      } else {
        description = descriptions.iterator().next();
      }

      if (description != null) {
        target.setDescription(description.getDescription());
        target.setLanguage(description.getLanguage().getCode());
      }

    }

    return target;
  }

  @Override
  protected ReadableCustomerReview createTarget() {
    // TODO Auto-generated method stub
    return null;
  }
}
