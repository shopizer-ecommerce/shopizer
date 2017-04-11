package com.salesmanager.admin.controller.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.admin.common.utils.DateUtil;
import com.salesmanager.admin.constants.Constants;
import com.salesmanager.admin.controller.ControllerConstants;
import com.salesmanager.admin.controller.pages.AbstractAdminController;
import com.salesmanager.admin.data.Menu;
import com.salesmanager.admin.data.order.OrderData;
import com.salesmanager.admin.data.order.OrderListData;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.business.services.order.orderproduct.OrderProductDownloadService;
import com.salesmanager.core.business.services.payments.PaymentService;
import com.salesmanager.core.business.services.payments.TransactionService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.services.system.EmailService;
import com.salesmanager.core.business.utils.ajax.AjaxDataTableResponse;
import com.salesmanager.core.model.common.CriteriaOrderBy;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.OrderCriteria;
import com.salesmanager.core.model.order.OrderList;
import com.salesmanager.core.model.order.OrderTotal;
import com.salesmanager.core.model.order.orderproduct.OrderProduct;
import com.salesmanager.core.model.order.orderproduct.OrderProductDownload;
import com.salesmanager.core.model.order.orderstatus.OrderStatusHistory;
import com.salesmanager.core.model.payments.PaymentType;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by umesh on 3/11/17.
 */
@Controller
public class OrderController extends AbstractAdminController{

    private static final Logger LOG= LoggerFactory.getLogger(OrderController.class);

    protected static final String ORDER_LIST="orderList";




    @Inject
    private OrderService orderService;

    @Inject
    CountryService countryService;

    @Inject
    ZoneService zoneService;

    @Inject
    PaymentService paymentService;

    @Inject
    CustomerService customerService;

    @Inject
    PricingService pricingService;

    @Inject
    TransactionService transactionService;

    @Inject
    EmailService emailService;

    @Inject
    OrderProductDownloadService orderProdctDownloadService;

    private final static String ORDER_STATUS_TMPL = "email_template_order_status.ftl";


    @PreAuthorize("hasRole('ORDER')")
    @RequestMapping(value = {"/admin/orderManagement.html","/admin/orderManagement"}, method = RequestMethod.GET)
    public String orderView(final Model model, final HttpServletRequest request) throws Exception {

        Map<String, String> activeMenus = new HashMap<String, String>();
        activeMenus.put("order", "order");
        activeMenus.put("order-list", "order-list");
        setMenu(model, (Map<String, Menu>) request.getAttribute("MENUMAP"), "order", activeMenus);

        return ControllerConstants.Tiles.Order.ordersDashBoard;
    }


    @PreAuthorize("hasRole('ORDER')")
    @RequestMapping(value = {"/admin/orders.html","/admin/orders"}, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> orders(final Model model
            , HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

        int startRow = Integer.parseInt(request.getParameter("start"));
        int endRow = Integer.parseInt(request.getParameter("length"));
        String orderBy = request.getParameter("order[0][dir]");
        LOG.info("Fetching order list {} .... {}", startRow,endRow);
        OrderCriteria criteria = new OrderCriteria();
        //System.out.println(CriteriaOrderBy.valueOf(orderBy));
        System.out.println(CriteriaOrderBy.valueOf(orderBy.toUpperCase()));
        criteria.setOrderBy(CriteriaOrderBy.valueOf(orderBy.toUpperCase()));
        criteria.setStartIndex(startRow);
        criteria.setMaxCount(endRow);
        // MapStruct will be used in future for data population
        MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
        AjaxDataTableResponse ajaxDataTableResponse = new AjaxDataTableResponse();
        OrderList orderList = orderService.listByStore(store, criteria);
        List<OrderListData> orders = new ArrayList<OrderListData>();
        if(CollectionUtils.isNotEmpty(orderList.getOrders())) {
            ajaxDataTableResponse.setiTotalRecords(orderList.getTotalCount());
            ajaxDataTableResponse.setiTotalDisplayRecords(orderList.getTotalCount());
            for(Order order : orderList.getOrders()) {
                OrderListData orderData = new OrderListData();
                orderData.setOrderNumber(order.getId());
                orderData.setCustomerId(order.getCustomerId());
                orderData.setOrderTotal(order.getTotal().doubleValue());
                orderData.setStatus(order.getStatus().name());
                orderData.setDate(order.getDatePurchased());

                orders.add(orderData);
            }
        }

        final HttpHeaders httpHeaders= new HttpHeaders();

        ajaxDataTableResponse.setData(orders);
        ObjectMapper mapper = new ObjectMapper();
        String returnString = mapper.writeValueAsString(ajaxDataTableResponse);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<String>(returnString,httpHeaders, HttpStatus.OK);


    }


    @PreAuthorize("hasRole('ORDER')")
    @RequestMapping(value="/admin/order/editOrder.html", method=RequestMethod.GET)
    public String displayOrderEdit(@RequestParam("id") long orderId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return displayOrder(orderId,model,request,response);
    }


    @PreAuthorize("hasRole('ORDER')")
    private String displayOrder(Long orderId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //display menu
        //setMenu(model,request);

        OrderData order = new OrderData();  // we will use MapStruct in future
        Language language = (Language)request.getAttribute("LANGUAGE");
        List<Country> countries = countryService.getCountries(language);
        if(orderId!=null && orderId!=0) {		//edit mode


            MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
            model.addAttribute("store",store);
            Set<OrderProduct> orderProducts = null;
            Set<OrderTotal> orderTotal = null;
            Set<OrderStatusHistory> orderHistory = null;
            Order dbOrder = orderService.getById(orderId);

            if(dbOrder==null) {
                return "redirect:/admin/orderManagement.html";
            }

            if(dbOrder.getMerchant().getId().intValue()!=store.getId().intValue()) {
                return "redirect:/admin/orderManagement.html";
            }

            order.setId( orderId );

            if( dbOrder.getDatePurchased() !=null ){
                order.setDatePurchased(DateUtil.formatDate(dbOrder.getDatePurchased()));
            }

            Long customerId = dbOrder.getCustomerId();

            if(customerId!=null && customerId>0) {

                try {

                    Customer customer = customerService.getById(customerId);
                    if(customer!=null) {
                        model.addAttribute("customer",customer);
                    }


                } catch(Exception e) {
                    LOG.error("Error while getting customer for customerId {} " , customerId, e);
                }

            }

            order.setOrder( dbOrder );
            order.setBilling( dbOrder.getBilling() );
            order.setDelivery(dbOrder.getDelivery() );


            orderProducts = dbOrder.getOrderProducts();
            orderTotal = dbOrder.getOrderTotal();
            orderHistory = dbOrder.getOrderHistory();

            //get capturable
            if(dbOrder.getPaymentType().name() != PaymentType.MONEYORDER.name()) {
                Transaction capturableTransaction = transactionService.getCapturableTransaction(dbOrder);
                if(capturableTransaction!=null) {
                    model.addAttribute("capturableTransaction",capturableTransaction);
                }
            }


            //get refundable
            if(dbOrder.getPaymentType().name() != PaymentType.MONEYORDER.name()) {
                Transaction refundableTransaction = transactionService.getRefundableTransaction(dbOrder);
                if(refundableTransaction!=null) {
                    model.addAttribute("capturableTransaction",null);//remove capturable
                    model.addAttribute("refundableTransaction",refundableTransaction);
                }
            }


            List<OrderProductDownload> orderProductDownloads = orderProdctDownloadService.getByOrderId(order.getId());
            if(CollectionUtils.isNotEmpty(orderProductDownloads)) {
                model.addAttribute("downloads",orderProductDownloads);
            }

        }

        model.addAttribute("countries", countries);
        model.addAttribute("order",order);

        return  ControllerConstants.Tiles.Order.ordersEdit;
    }


}
