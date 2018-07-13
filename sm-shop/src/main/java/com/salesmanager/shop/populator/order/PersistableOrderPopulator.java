package com.salesmanager.shop.populator.order;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.catalog.product.file.DigitalProductService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.currency.CurrencyService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.business.utils.CreditCardUtils;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.orderproduct.OrderProduct;
import com.salesmanager.core.model.order.orderstatus.OrderStatus;
import com.salesmanager.core.model.order.orderstatus.OrderStatusHistory;
import com.salesmanager.core.model.order.payment.CreditCard;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.currency.Currency;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.order.PersistableOrder;
import com.salesmanager.shop.model.order.PersistableOrderProduct;
import com.salesmanager.shop.model.order.total.OrderTotal;
import com.salesmanager.shop.utils.LocaleUtils;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;


public class PersistableOrderPopulator extends AbstractDataPopulator<PersistableOrder, Order> {

  private CustomerService customerService;

  private CountryService countryService;

  private CurrencyService currencyService;

  private ZoneService zoneService;

  private ProductService productService;

  private DigitalProductService digitalProductService;

  private ProductAttributeService productAttributeService;

  @Override
  public Order populate(PersistableOrder source, Order target,
      MerchantStore store, Language language) throws ServiceException {

    Validate.notNull(productService, "productService must be set");
    Validate.notNull(digitalProductService, "digitalProductService must be set");
    Validate.notNull(productAttributeService, "productAttributeService must be set");
    Validate.notNull(customerService, "customerService must be set");
    Validate.notNull(countryService, "countryService must be set");
    Validate.notNull(zoneService, "zoneService must be set");
    Validate.notNull(currencyService, "currencyService must be set");

    Map<String, Country> countriesMap = countryService.getCountriesMap(language);
    Map<String, Zone> zonesMap = zoneService.getZones(language);

    PersistableCustomer customer = source.getCustomer();
    if (customer != null) {
      if (customer.getId() != null && customer.getId() > 0) {
        Customer modelCustomer = customerService.getById(customer.getId());

        if (modelCustomer == null && modelCustomer.getMerchantStore().getId().intValue() != store
            .getId().intValue()) {
          throw new RuntimeException(
              "Customer id " + customer.getId() + " does not exists for store " + store.getCode());
        }
        target.setCustomerId(modelCustomer.getId());
        target.setBilling(modelCustomer.getBilling());
        target.setDelivery(modelCustomer.getDelivery());
        target.setCustomerEmailAddress(source.getCustomer().getEmailAddress());
      }
    }

    target.setLocale(LocaleUtils.getLocale(store));

    CreditCard creditCard = source.getCreditCard();
    if (creditCard != null) {
      String maskedNumber = CreditCardUtils.maskCardNumber(creditCard.getCcNumber());
      creditCard.setCcNumber(maskedNumber);
      target.setCreditCard(creditCard);
    }

    Currency currency = currencyService.getByCode(source.getCurrency());

    target.setCurrency(currency);
    target.setDatePurchased(source.getDatePurchased());
    target.setCurrencyValue(new BigDecimal(0));
    target.setMerchant(store);
    target.setStatus(source.getOrderStatus());
    target.setPaymentModuleCode(source.getPaymentModule());
    target.setPaymentType(source.getPaymentType());
    target.setShippingModuleCode(source.getShippingModule());
    target.setCustomerAgreement(source.isCustomerAgreed());
    target.setConfirmedAddress(source.isConfirmedAddress());

    if (source.getPreviousOrderStatus() != null) {
      List<OrderStatus> orderStatusList = source.getPreviousOrderStatus();
      for (OrderStatus status : orderStatusList) {
        OrderStatusHistory statusHistory = new OrderStatusHistory();
        statusHistory.setStatus(status);
        statusHistory.setOrder(target);
        target.getOrderHistory().add(statusHistory);
      }
    }

    if (!StringUtils.isBlank(source.getComments())) {
      OrderStatusHistory statusHistory = new OrderStatusHistory();
      statusHistory.setStatus(null);
      statusHistory.setOrder(target);
      statusHistory.setComments(source.getComments());
      target.getOrderHistory().add(statusHistory);
    }

    List<PersistableOrderProduct> products = source.getOrderProductItems();

    com.salesmanager.shop.populator.order.PersistableOrderProductPopulator orderProductPopulator = new PersistableOrderProductPopulator();
    orderProductPopulator.setProductAttributeService(productAttributeService);
    orderProductPopulator.setProductService(productService);
    orderProductPopulator.setDigitalProductService(digitalProductService);

    for (PersistableOrderProduct orderProduct : products) {
      OrderProduct modelOrderProduct = new OrderProduct();
      orderProductPopulator.populate(orderProduct, modelOrderProduct, store, language);
      target.getOrderProducts().add(modelOrderProduct);
    }

    List<OrderTotal> orderTotals = source.getTotals();
    if (CollectionUtils.isNotEmpty(orderTotals)) {
      for (OrderTotal total : orderTotals) {
        com.salesmanager.core.model.order.OrderTotal totalModel = new com.salesmanager.core.model.order.OrderTotal();
        totalModel.setModule(total.getModule());
        totalModel.setOrder(target);
        totalModel.setOrderTotalCode(total.getCode());
        totalModel.setTitle(total.getTitle());
        totalModel.setValue(total.getValue());
        target.getOrderTotal().add(totalModel);
      }
    }

    return target;
  }

  @Override
  protected Order createTarget() {
    return null;
  }

  public ProductService getProductService() {
    return productService;
  }

  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  public DigitalProductService getDigitalProductService() {
    return digitalProductService;
  }

  public void setDigitalProductService(DigitalProductService digitalProductService) {
    this.digitalProductService = digitalProductService;
  }

  public ProductAttributeService getProductAttributeService() {
    return productAttributeService;
  }

  public void setProductAttributeService(ProductAttributeService productAttributeService) {
    this.productAttributeService = productAttributeService;
  }

  public CustomerService getCustomerService() {
    return customerService;
  }

  public void setCustomerService(CustomerService customerService) {
    this.customerService = customerService;
  }

  public CountryService getCountryService() {
    return countryService;
  }

  public void setCountryService(CountryService countryService) {
    this.countryService = countryService;
  }

  public CurrencyService getCurrencyService() {
    return currencyService;
  }

  public void setCurrencyService(CurrencyService currencyService) {
    this.currencyService = currencyService;
  }

  public ZoneService getZoneService() {
    return zoneService;
  }

  public void setZoneService(ZoneService zoneService) {
    this.zoneService = zoneService;
  }
}
