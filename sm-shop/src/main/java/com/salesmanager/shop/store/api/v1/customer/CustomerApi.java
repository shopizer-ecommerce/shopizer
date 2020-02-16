package com.salesmanager.shop.store.api.v1.customer;

import java.security.Principal;
import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.core.model.customer.CustomerCriteria;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.populator.customer.ReadableCustomerList;
import com.salesmanager.shop.store.controller.customer.facade.CustomerFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = {"Customer management resource (Customer Management Api)"})
@SwaggerDefinition(tags = {
    @Tag(name = "Customer management resource", description = "Manage customer addresses")
})
public class CustomerApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerApi.class);

  @Inject
  private CustomerFacade customerFacade;


  /** Create new customer for a given MerchantStore */
  @PostMapping("/private/customer")
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
  
/*  *//**
   * Update authenticated customer adresses
   * @param userName
   * @param merchantStore
   * @param customer
   * @return
   *//*
  @PutMapping("/auth/customer/{id}")
  @ApiOperation(
      httpMethod = "PUT",
      value = "Updates a customer",
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
  }*/
  
  
  

  @PutMapping("/private/customer/{id}")
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
      @PathVariable Long id,
      @ApiIgnore MerchantStore merchantStore,
      @Valid @RequestBody PersistableCustomer customer) {

      customer.setId(id);
      return customerFacade.update(customer, merchantStore);
  }
  
  @PatchMapping("/private/customer/{id}/address")
  @ApiOperation(
      httpMethod = "PATCH",
      value = "Updates a customer",
      notes = "Requires administration access",
      produces = "application/json",
      response = Void.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT")
  })
  public void updateAddress(
      @PathVariable Long id,
      @ApiIgnore MerchantStore merchantStore,
      @RequestBody PersistableCustomer customer) {

      customer.setId(id);
      customerFacade.updateAddress(customer, merchantStore);
  }

  @DeleteMapping("/private/customer/{id}")
  @ApiOperation(
      httpMethod = "DELETE",
      value = "Deletes a customer",
      notes = "Requires administration access")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT")
  })
  public void delete(
      @PathVariable Long id,
      @ApiIgnore MerchantStore merchantStore
      ) {
    customerFacade.deleteById(id);
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

  @GetMapping("/private/customer/{id}")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en")
  })
  public ReadableCustomer get(
      @PathVariable Long id,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
      return customerFacade.getCustomerById(id, merchantStore, language);
  }

  /**
   * Get logged in customer profile
   * @param merchantStore
   * @param language
   * @param request
   * @return
   */
  @GetMapping("/private/customer/profile")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en")
  })
  public ReadableCustomer getAuthUser(
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language,
      HttpServletRequest request) {
    Principal principal = request.getUserPrincipal();
    String userName = principal.getName();
    return customerFacade.getCustomerByNick(userName, merchantStore, language);
  }
  
  @PatchMapping("/auth/customer/address")
  @ApiOperation(
      httpMethod = "PATCH",
      value = "Updates a loged in customer address",
      notes = "Requires authentication",
      produces = "application/json",
      response = Void.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT")
  })
  public void updateAuthUserAddress(
      @ApiIgnore MerchantStore merchantStore,
      @RequestBody PersistableCustomer customer,
      HttpServletRequest request) {
      Principal principal = request.getUserPrincipal();
      String userName = principal.getName();
      

      customerFacade.updateAddress(userName, customer, merchantStore);
  
  }
  
  @PutMapping("/auth/customer/{id}")
  @ApiOperation(
      httpMethod = "PUT",
      value = "Updates a loged in customer profile",
      notes = "Requires authentication",
      produces = "application/json",
      response = PersistableCustomer.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT")
  })
  public PersistableCustomer update(
      @ApiIgnore MerchantStore merchantStore,
      @Valid @RequestBody PersistableCustomer customer,
      HttpServletRequest request) {
      
      Principal principal = request.getUserPrincipal();
      String userName = principal.getName();

      return customerFacade.update(userName, customer, merchantStore);
  }
  
  
}
