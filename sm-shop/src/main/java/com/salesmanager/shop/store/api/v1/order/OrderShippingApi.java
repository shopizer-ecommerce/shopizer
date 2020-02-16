package com.salesmanager.shop.store.api.v1.order;

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartService;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shipping.ShippingOption;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.model.shipping.ShippingSummary;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.shop.model.customer.address.AddressLocation;
import com.salesmanager.shop.model.order.shipping.ReadableShippingSummary;
import com.salesmanager.shop.populator.order.ReadableShippingSummaryPopulator;
import com.salesmanager.shop.store.controller.order.facade.OrderFacade;
import com.salesmanager.shop.utils.LabelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/api/v1")
@Api(tags = {"Shipping Quotes and Calculation resource (Shipping Api)"})
@SwaggerDefinition(tags = {
    @Tag(name = "Shipping Quotes and Calculation resource", description = "Get shipping quotes for public api and loged in customers")
})
public class OrderShippingApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderShippingApi.class);

  @Inject private CustomerService customerService;

  @Inject private OrderFacade orderFacade;

  @Inject private ShoppingCartService shoppingCartService;

  @Inject private LabelUtils messages;

  @Inject private PricingService pricingService;
  
  @Inject private CountryService countryService;

  /**
   * Get shipping quote for a given shopping cart
   *
   * @param id
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @RequestMapping(
      value = {"/auth/cart/{code}/shipping"},
      method = RequestMethod.GET)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public ReadableShippingSummary shipping(
      @PathVariable final String code,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletRequest request,
      HttpServletResponse response) {

    try {
      Locale locale = request.getLocale();
      Principal principal = request.getUserPrincipal();
      String userName = principal.getName();

      // get customer id
      Customer customer = customerService.getByNick(userName);

      if (customer == null) {
        response.sendError(503, "Error while getting user details to calculate shipping quote");
      }

      ShoppingCart cart = shoppingCartService.getByCode(code, merchantStore);

      if (cart == null) {
        response.sendError(404, "Cart code " + code + " does not exist");
      }

      if (cart.getCustomerId() == null) {
        response.sendError(404, "Cart code " + code + " does not exist for exist for user " + userName);
      }

      if (cart.getCustomerId().longValue() != customer.getId().longValue()) {
        response.sendError(404, "Cart code " + code + " does not exist for exist for user " + userName);
      }

      ShippingQuote quote = orderFacade.getShippingQuote(customer, cart, merchantStore, language);

      ShippingSummary summary = orderFacade.getShippingSummary(quote, merchantStore, language);

      ReadableShippingSummary shippingSummary = new ReadableShippingSummary();
      ReadableShippingSummaryPopulator populator = new ReadableShippingSummaryPopulator();
      populator.setPricingService(pricingService);
      populator.populate(summary, shippingSummary, merchantStore, language);

      List<ShippingOption> options = quote.getShippingOptions();

      if (!CollectionUtils.isEmpty(options)) {

        for (ShippingOption shipOption : options) {

          StringBuilder moduleName = new StringBuilder();
          moduleName.append("module.shipping.").append(shipOption.getShippingModuleCode());

          String carrier =
              messages.getMessage(
                  moduleName.toString(), new String[] {merchantStore.getStorename()}, locale);

          String note = messages.getMessage(moduleName.append(".note").toString(), locale, "");

          shipOption.setDescription(carrier);
          shipOption.setNote(note);

          // option name
          if (!StringUtils.isBlank(shipOption.getOptionCode())) {
            // try to get the translate
            StringBuilder optionCodeBuilder = new StringBuilder();
            try {

              optionCodeBuilder
                  .append("module.shipping.")
                  .append(shipOption.getShippingModuleCode());
              String optionName = messages.getMessage(optionCodeBuilder.toString(), locale);
              shipOption.setOptionName(optionName);
            } catch (Exception e) { // label not found
              LOGGER.warn("No shipping code found for " + optionCodeBuilder.toString());
            }
          }
        }

        shippingSummary.setShippingOptions(options);
      }

      return shippingSummary;

    } catch (Exception e) {
      LOGGER.error("Error while getting shipping quote", e);
      try {
        response.sendError(503, "Error while getting shipping quote" + e.getMessage());
      } catch (Exception ignore) {
      }
      return null;
    }
  }

  /**
   * Get shipping quote based on postal code
   * @param code
   * @param address
   * @param merchantStore
   * @param language
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @RequestMapping(
      value = {"/cart/{code}/shipping"},
      method = RequestMethod.POST)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  public ReadableShippingSummary shipping(
      @PathVariable final Long id,
      @RequestBody AddressLocation address,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletRequest request,
      HttpServletResponse response)
      throws Exception {

    try {
      Locale locale = request.getLocale();

      ShoppingCart cart = shoppingCartService.getById(id, merchantStore);

      if (cart == null) {
        response.sendError(404, "Cart id " + id + " does not exist");
      }

      
      Delivery addr = new Delivery();
      addr.setPostalCode(address.getPostalCode());

      Country c = countryService.getByCode(address.getCountryCode());
      
      if(c==null) {
        response.sendError(503, "Error while getting shipping quote, Country code " + address.getCountryCode() + " not found");
        return null;
      }
      addr.setCountry(c);

      
      Customer temp = new Customer();
      temp.setAnonymous(true);
      temp.setDelivery(addr);
      
      ShippingQuote quote = orderFacade.getShippingQuote(temp, cart, merchantStore, language);

      ShippingSummary summary = orderFacade.getShippingSummary(quote, merchantStore, language);

      ReadableShippingSummary shippingSummary = new ReadableShippingSummary();
      ReadableShippingSummaryPopulator populator = new ReadableShippingSummaryPopulator();
      populator.setPricingService(pricingService);
      populator.populate(summary, shippingSummary, merchantStore, language);

      List<ShippingOption> options = quote.getShippingOptions();

      if (!CollectionUtils.isEmpty(options)) {

        for (ShippingOption shipOption : options) {

          StringBuilder moduleName = new StringBuilder();
          moduleName.append("module.shipping.").append(shipOption.getShippingModuleCode());

          String carrier =
              messages.getMessage(
                  moduleName.toString(), new String[] {merchantStore.getStorename()}, locale);

          String note = messages.getMessage(moduleName.append(".note").toString(), locale, "");

          shipOption.setDescription(carrier);
          shipOption.setNote(note);

          // option name
          if (!StringUtils.isBlank(shipOption.getOptionCode())) {
            // try to get the translate
            StringBuilder optionCodeBuilder = new StringBuilder();
            try {

              optionCodeBuilder
                  .append("module.shipping.")
                  .append(shipOption.getShippingModuleCode());
              String optionName = messages.getMessage(optionCodeBuilder.toString(), locale);
              shipOption.setOptionName(optionName);
            } catch (Exception e) { // label not found
              LOGGER.warn("No shipping code found for " + optionCodeBuilder.toString());
            }
          }
        }

        shippingSummary.setShippingOptions(options);
      }

      return shippingSummary;

    } catch (Exception e) {
      LOGGER.error("Error while getting shipping quote", e);
      try {
        response.sendError(503, "Error while getting shipping quote" + e.getMessage());
      } catch (Exception ignore) {
      }
      return null;
    }
  }
}
