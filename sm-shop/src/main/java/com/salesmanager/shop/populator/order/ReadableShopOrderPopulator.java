package com.salesmanager.shop.populator.order;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.Address;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.model.order.ReadableShopOrder;
import com.salesmanager.shop.model.order.ShopOrder;


public class ReadableShopOrderPopulator extends
    AbstractDataPopulator<ShopOrder, ReadableShopOrder> {

  @Override
  public ReadableShopOrder populate(ShopOrder source, ReadableShopOrder target, MerchantStore store,
      Language language) throws ServiceException {

    ReadableCustomer customer = new ReadableCustomer();
    PersistableCustomer persistableCustomer = source.getCustomer();

    customer.setEmailAddress(persistableCustomer.getEmailAddress());
    if (persistableCustomer.getBilling() != null) {
      Address address = new Address();
      address.setCity(persistableCustomer.getBilling().getCity());
      address.setCompany(persistableCustomer.getBilling().getCompany());
      address.setFirstName(persistableCustomer.getBilling().getFirstName());
      address.setLastName(persistableCustomer.getBilling().getLastName());
      address.setPostalCode(persistableCustomer.getBilling().getPostalCode());
      address.setPhone(persistableCustomer.getBilling().getPhone());
      if (persistableCustomer.getBilling().getCountry() != null) {
        address.setCountry(persistableCustomer.getBilling().getCountry());
      }
      if (persistableCustomer.getBilling().getZone() != null) {
        address.setZone(persistableCustomer.getBilling().getZone());
      }

      customer.setBilling(address);
    }

    if (persistableCustomer.getDelivery() != null) {
      Address address = new Address();
      address.setCity(persistableCustomer.getDelivery().getCity());
      address.setCompany(persistableCustomer.getDelivery().getCompany());
      address.setFirstName(persistableCustomer.getDelivery().getFirstName());
      address.setLastName(persistableCustomer.getDelivery().getLastName());
      address.setPostalCode(persistableCustomer.getDelivery().getPostalCode());
      address.setPhone(persistableCustomer.getDelivery().getPhone());
      if (persistableCustomer.getDelivery().getCountry() != null) {
        address.setCountry(persistableCustomer.getDelivery().getCountry());
      }
      if (persistableCustomer.getDelivery().getZone() != null) {
        address.setZone(persistableCustomer.getDelivery().getZone());
      }

      customer.setDelivery(address);
    }

    //TODO if ship to billing enabled, set delivery = billing

    target.setCustomer(customer);

    return target;
  }

  @Override
  protected ReadableShopOrder createTarget() {
    return null;
  }
}
