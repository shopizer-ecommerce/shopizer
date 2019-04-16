package com.salesmanager.shop.store.api.v1.customer;

import static com.salesmanager.core.business.constants.Constants.DEFAULT_STORE;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.security.Principal;
import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.CustomerCriteria;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.populator.customer.ReadableCustomerList;
import com.salesmanager.shop.store.controller.customer.facade.CustomerFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/api/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerApi.class);

  @Inject
  private CustomerFacade customerFacade;

  @Inject
  private CustomerService customerService;

  @Inject
  private StoreFacade storeFacade;

  @Inject
  private LanguageUtils languageUtils;

  /** Create new customer for a given MerchantStore */
  @PostMapping("/private/customers")
  @ApiOperation(
      httpMethod = "POST",
      value = "Creates a customer",
      notes = "Requires administration access",
      produces = "application/json",
      response = PersistableCustomer.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT")
  })
  public PersistableCustomer create(
      @ApiIgnore MerchantStore merchantStore,
      @Valid @RequestBody PersistableCustomer customer) {
      return customerFacade.create(customer, merchantStore);

  }

  @PutMapping("/private/customers/{username}")
  @ApiOperation(
      httpMethod = "PUT",
      value = "Updates a customer",
      notes = "Requires administration access",
      produces = "application/json",
      response = PersistableCustomer.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT")
  })
  public PersistableCustomer update(
      @PathVariable String userName,
      @ApiIgnore MerchantStore merchantStore,
      @Valid @RequestBody PersistableCustomer customer) {
      // TODO customer.setUserName
      // TODO more validation
      return customerFacade.update(customer, merchantStore);
  }

  @DeleteMapping("/private/customers/{userName}")
  @ApiOperation(
      httpMethod = "DELETE",
      value = "Deletes a customer",
      notes = "Requires administration access")
  public void delete(@PathVariable String userName) {
    customerFacade.deleteByNick(userName);
  }

  /**
   * Get all customers
   *
   * @param start
   * @param count
   * @param request
   * @return
   * @throws Exception
   */
  @GetMapping("/private/customers")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en")
  })
  public ReadableCustomerList getFilteredCustomers(
      @RequestParam(value = "start", required = false) Integer start,
      @RequestParam(value = "count", required = false) Integer count,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
    CustomerCriteria customerCriteria = createCustomerCriteria(start, count);
    return customerFacade.getListByStore(merchantStore, customerCriteria, language);
  }

  private CustomerCriteria createCustomerCriteria(Integer start, Integer count) {
    CustomerCriteria customerCriteria = new CustomerCriteria();
    Optional.ofNullable(start).ifPresent(customerCriteria::setStartIndex);
    Optional.ofNullable(count).ifPresent(customerCriteria::setMaxCount);
    return customerCriteria;
  }

  @GetMapping("/private/customers/{userName}")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en")
  })
  public ReadableCustomer get(@PathVariable String userName,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
      return customerFacade.getByUserName(userName, merchantStore, language);
  }

  @GetMapping("/auth/customers/profile")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en")
  })
  public ReadableCustomer get(
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletRequest request) {
    Principal principal = request.getUserPrincipal();
    String userName = principal.getName();
    return customerFacade.getCustomerByNick(userName, merchantStore, language);
  }
}
