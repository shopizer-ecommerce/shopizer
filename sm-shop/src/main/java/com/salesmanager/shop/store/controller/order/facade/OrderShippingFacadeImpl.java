package com.salesmanager.shop.store.controller.order.facade;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.services.shipping.ShippingService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartService;
import com.salesmanager.core.model.common.Billing;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shipping.ShippingProduct;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.model.shipping.ShippingSummary;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.order.ShopOrder;
import com.salesmanager.shop.store.controller.customer.facade.CustomerFacade;

@Component
public class OrderShippingFacadeImpl {

    @Inject
    private ShippingService shippingService;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Inject
    private CustomerFacade customerFacade;

    public ShippingQuote getShippingQuote(PersistableCustomer persistableCustomer,
            ShoppingCart cart, ShopOrder order,
            MerchantStore store, Language language) throws Exception {

        List<ShippingProduct> shippingProducts = shoppingCartService.createShippingProduct(cart);
        if (CollectionUtils.isEmpty(shippingProducts)) {
            return null;
        }

        Customer customer = customerFacade.getCustomerModel(persistableCustomer, store, language);
        Delivery delivery = new Delivery();

        if (order.isShipToBillingAdress() && !order.isShipToDeliveryAddress()) {
            Billing billing = customer.getBilling();
            String postalCode = validatePostalCode(billing.getPostalCode());
            delivery.setAddress(billing.getAddress());
            delivery.setCompany(billing.getCompany());
            delivery.setCity(billing.getCity());
            delivery.setPostalCode(postalCode);
            delivery.setState(billing.getState());
            delivery.setCountry(billing.getCountry());
            delivery.setZone(billing.getZone());
        } else {
            delivery = customer.getDelivery();
        }

        return shippingService.getShippingQuote(cart.getId(), store, delivery,
                shippingProducts, language);
    }

    public ShippingQuote getShippingQuote(Customer customer, ShoppingCart cart,
            MerchantStore store, Language language) throws Exception {

        List<ShippingProduct> shippingProducts = shoppingCartService.createShippingProduct(cart);
        if (CollectionUtils.isEmpty(shippingProducts)) {
            return null;
        }

        Delivery delivery = customer.getDelivery();
        return shippingService.getShippingQuote(cart.getId(), store, delivery,
                shippingProducts, language);
    }

    public List<Country> getShipToCountry(MerchantStore store, Language language) throws Exception {
        return shippingService.getShipToCountryList(store, language);
    }

    public ShippingSummary getShippingSummary(ShippingQuote quote, MerchantStore store,
            Language language) {

        ShippingSummary summary = new ShippingSummary();
        if (quote.getSelectedShippingOption() != null) {
            summary.setShippingQuote(true);
            summary.setFreeShipping(quote.isFreeShipping());
            summary.setTaxOnShipping(quote.isApplyTaxOnShipping());
            summary.setHandling(quote.getHandlingFees());
            summary.setShipping(quote.getSelectedShippingOption().getOptionPrice());
            summary.setShippingOption(quote.getSelectedShippingOption().getOptionName());
            summary.setShippingModule(quote.getShippingModuleCode());
            summary.setShippingOptionCode(quote.getSelectedShippingOption().getOptionCode());
            if (quote.getDeliveryAddress() != null) {
                summary.setDeliveryAddress(quote.getDeliveryAddress());
            }
        }
        return summary;
    }

    private String validatePostalCode(String postalCode) {
        String patternString = "__";
        if (postalCode.contains(patternString)) {
            postalCode = null;
        }
        return postalCode;
    }
}
