package com.salesmanager.shop.populator.customer;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.review.CustomerReview;
import com.salesmanager.core.model.customer.review.CustomerReviewDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.PersistableCustomerReview;
import com.salesmanager.shop.utils.DateUtil;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;


public class PersistableCustomerReviewPopulator extends
    AbstractDataPopulator<PersistableCustomerReview, CustomerReview> {

  private static final Logger LOGGER = Logger.getLogger(PersistableCustomerReviewPopulator.class);

  private CustomerService customerService;

  private LanguageService languageService;

  public LanguageService getLanguageService() {
    return languageService;
  }

  public void setLanguageService(LanguageService languageService) {
    this.languageService = languageService;
  }

  @Override
  public CustomerReview populate(PersistableCustomerReview source, CustomerReview target,
      MerchantStore store,
      Language language) throws ServiceException {
    Validate.notNull(customerService, "customerService cannot be null");
    Validate.notNull(languageService, "languageService cannot be null");
    Validate.notNull(source.getRating(), "Rating cannot bot be null");

    if (target == null) {
      target = new CustomerReview();
    }

    if (source.getDate() == null) {
      String date = DateUtil.formatDate(new Date());
      source.setDate(date);
    }
    try {
      target.setReviewDate(DateUtil.getDate(source.getDate()));
    } catch (ParseException e) {
      LOGGER.error("Invalid date string: " + source.getDate());
      target.setReviewDate(null);
    }

    if (source.getId() != null && source.getId().longValue() == 0) {
      source.setId(null);
    } else {
      target.setId(source.getId());
    }

    Customer reviewer = customerService.getById(source.getCustomerId());
    Customer reviewed = customerService.getById(source.getReviewedCustomer());

    target.setReviewRating(source.getRating());

    target.setCustomer(reviewer);
    target.setReviewedCustomer(reviewed);

    Language lang = languageService.getByCode(language.getCode());

    CustomerReviewDescription description = new CustomerReviewDescription();
    description.setDescription(source.getDescription());
    description.setLanguage(lang);
    description.setName("-");
    description.setCustomerReview(target);

    Set<CustomerReviewDescription> descriptions = new HashSet<CustomerReviewDescription>();
    descriptions.add(description);

    target.setDescriptions(descriptions);

    return target;
  }

  @Override
  protected CustomerReview createTarget() {
    // TODO Auto-generated method stub
    return null;
  }

  public CustomerService getCustomerService() {
    return customerService;
  }

  public void setCustomerService(CustomerService customerService) {
    this.customerService = customerService;
  }

}
