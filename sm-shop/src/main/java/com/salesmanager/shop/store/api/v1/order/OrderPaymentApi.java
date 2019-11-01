package com.salesmanager.shop.store.api.v1.order;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.business.services.payments.PaymentService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.shop.model.order.ReadableOrderList;
import com.salesmanager.shop.model.order.transaction.PersistablePayment;
import com.salesmanager.shop.model.order.transaction.ReadableTransaction;
import com.salesmanager.shop.populator.order.transaction.PersistablePaymentPopulator;
import com.salesmanager.shop.populator.order.transaction.ReadableTransactionPopulator;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.controller.order.facade.OrderFacade;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/api/v1")
public class OrderPaymentApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderPaymentApi.class);

  @Inject
  private CustomerService customerService;

  @Inject
  private OrderService orderService;

  @Inject
  private ShoppingCartService shoppingCartService;

  @Inject
  private PricingService pricingService;

  @Inject
  private PaymentService paymentService;

  @Inject
  private OrderFacade orderFacade;


  @RequestMapping(value = {"/cart/{code}/payment/init"}, method = RequestMethod.POST)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ReadableTransaction init(@Valid @RequestBody PersistablePayment payment,
      @PathVariable String code, @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) throws Exception {

    ShoppingCart cart = shoppingCartService.getByCode(code, merchantStore);
    if (cart == null) {
      throw new ResourceNotFoundException("Cart code " + code + " does not exist");
    }


    PersistablePaymentPopulator populator = new PersistablePaymentPopulator();
    populator.setPricingService(pricingService);

    Payment paymentModel = new Payment();

    populator.populate(payment, paymentModel, merchantStore, language);

    Transaction transactionModel =
        paymentService.initTransaction(null, paymentModel, merchantStore);

    ReadableTransaction transaction = new ReadableTransaction();
    ReadableTransactionPopulator trxPopulator = new ReadableTransactionPopulator();
    trxPopulator.setOrderService(orderService);
    trxPopulator.setPricingService(pricingService);

    trxPopulator.populate(transactionModel, transaction, merchantStore, language);

    return transaction;

  }



  @RequestMapping(value = {"/auth/cart/{code}/payment/init"}, method = RequestMethod.POST)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ReadableTransaction init(@Valid @RequestBody PersistablePayment payment,
      @PathVariable String code, @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language,
      HttpServletRequest request, HttpServletResponse response) throws Exception {

    try {
      Principal principal = request.getUserPrincipal();
      String userName = principal.getName();

      Customer customer = customerService.getByNick(userName);

      if (customer == null) {
        response.sendError(401, "Error while initializing the payment customer not authorized");
        return null;
      }

      ShoppingCart cart = shoppingCartService.getByCode(code, merchantStore);
      if (cart == null) {

        throw new ResourceNotFoundException("Cart code " + code + " does not exist");
      }

      if (cart.getCustomerId() == null) {
        response.sendError(404, "Cart code " + code + " does not exist for exist for user " + userName);
        return null;
      }

      if (cart.getCustomerId().longValue() != customer.getId().longValue()) {
        response.sendError(404, "Cart code " + code + " does not exist for exist for user " + userName);
        return null;
      }

      PersistablePaymentPopulator populator = new PersistablePaymentPopulator();
      populator.setPricingService(pricingService);

      Payment paymentModel = new Payment();

      populator.populate(payment, paymentModel, merchantStore, language);

      Transaction transactionModel =
          paymentService.initTransaction(customer, paymentModel, merchantStore);

      ReadableTransaction transaction = new ReadableTransaction();
      ReadableTransactionPopulator trxPopulator = new ReadableTransactionPopulator();
      trxPopulator.setOrderService(orderService);
      trxPopulator.setPricingService(pricingService);

      trxPopulator.populate(transactionModel, transaction, merchantStore, language);

      return transaction;

    } catch (Exception e) {
      LOGGER.error("Error while initializing the payment", e);
      try {
        response.sendError(503, "Error while initializing the payment " + e.getMessage());
      } catch (Exception ignore) {
      }
      return null;
    }
  }

  /**
   * An order can be pre-authorized but un captured. This metho returns all order subject to be
   * capturable For a given time frame
   *
   * @param startDate
   * @param endDate
   * @param request
   * @param response
   * @return ReadableOrderList
   * @throws Exception
   */
  @RequestMapping(value = {"/private/orders/payment/capturable"}, method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.ACCEPTED)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ReadableOrderList listCapturableOrders(
      @RequestParam(value = "startDate",
          required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
      @RequestParam(value = "endDate",
          required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language,
      HttpServletRequest request, HttpServletResponse response) {

    try {

      // if startdate or enddate are null use default range (last 24 hours) DD-1 to DD
      Calendar cal = Calendar.getInstance();
      Date sDate = null;

      if (startDate != null) {
        sDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
      } else {
        cal.add(Calendar.DATE, -1);
        sDate = cal.getTime();
      }

      Date eDate = null;

      if (endDate != null) {
        eDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
      } else {
        eDate = new Date();
      }

      ReadableOrderList returnList =
          orderFacade.getCapturableOrderList(merchantStore, sDate, eDate, language);

      return returnList;

    } catch (Exception e) {
      LOGGER.error("Error while getting capturable payments", e);
      try {
        response.sendError(503, "Error while getting capturable payments " + e.getMessage());
      } catch (Exception ignore) {
      }
      return null;
    }
  }

  /**
   * Capture payment transaction for a given order id
   *
   * @param id
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @RequestMapping(value = {"/private/orders/{id}/capture"}, method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.ACCEPTED)
  @ResponseBody
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ReadableTransaction caprurePayment(@PathVariable Long id,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language,
      HttpServletRequest request, HttpServletResponse response) {
    try {

      // need order
      Order order = orderService.getById(id);

      if (order == null) {
        response.sendError(404, "Order id " + id + " does not exist");
        return null;
      }

      // need customer
      Customer customer = customerService.getById(order.getCustomerId());

      if (customer == null) {
        response.sendError(404,
            "Order id " + id + " contains an invalid customer " + order.getCustomerId());
        return null;
      }

      ReadableTransaction transaction =
          orderFacade.captureOrder(merchantStore, order, customer, language);

      return transaction;

    } catch (Exception e) {
      LOGGER.error("Error while capturing payment", e);
      try {
        response.sendError(503, "Error while capturing payment " + e.getMessage());
      } catch (Exception ignore) {
      }
      return null;
    }
  }
}
