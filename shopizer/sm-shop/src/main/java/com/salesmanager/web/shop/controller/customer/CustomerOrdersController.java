package com.salesmanager.web.shop.controller.customer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.merchant.service.MerchantStoreService;
import com.salesmanager.core.business.order.model.orderproduct.OrderProductDownload;
import com.salesmanager.core.business.order.service.orderproduct.OrderProductDownloadService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.entity.order.ReadableOrder;
import com.salesmanager.web.entity.order.ReadableOrderList;
import com.salesmanager.web.entity.order.ReadableOrderProductDownload;
import com.salesmanager.web.populator.order.ReadableOrderProductDownloadPopulator;
import com.salesmanager.web.shop.controller.AbstractController;
import com.salesmanager.web.shop.controller.ControllerConstants;
import com.salesmanager.web.shop.controller.customer.facade.CustomerFacade;
import com.salesmanager.web.shop.controller.order.facade.OrderFacade;
import com.salesmanager.web.shop.model.paging.PaginationData;

@Controller
@RequestMapping(Constants.SHOP_URI + "/customer")
public class CustomerOrdersController extends AbstractController {
	
    @Autowired
	private MerchantStoreService merchantStoreService;
    
    @Autowired
    private LanguageService languageService;
    
    @Autowired
    private OrderFacade orderFacade;
    
    @Autowired
    private CustomerFacade customerFacade;
    
	@Autowired
	private OrderProductDownloadService orderProdctDownloadService;
    
    

	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerOrdersController.class);
	
	@PreAuthorize("hasRole('AUTH_CUSTOMER')")
	@RequestMapping(value="/orders.html", method={RequestMethod.GET,RequestMethod.POST})
	public String listOrders(Model model, @RequestParam(value = "page", defaultValue = "1") final int page, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
    	LOGGER.info( "Fetching orders for current customer" );
        MerchantStore store = getSessionAttribute(Constants.MERCHANT_STORE, request);
        Language language = getSessionAttribute(Constants.LANGUAGE, request);
        
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = null;
    	if(auth != null &&
        		 request.isUserInRole("AUTH_CUSTOMER")) {
    		customer = customerFacade.getCustomerByUserName(auth.getName(), store);

        }
    	
    	if(customer==null) {
    		return "redirect:/"+Constants.SHOP_URI;
    	}
        
        PaginationData paginaionData=createPaginaionData(page,Constants.MAX_ORDERS_PAGE);
        ReadableOrderList readable= orderFacade.getReadableOrderList(store, customer, (paginaionData.getOffset() -1),paginaionData.getPageSize(), language);
        
        model.addAttribute( "customerOrders", readable);
        if(readable!=null) {
        	model.addAttribute( "paginationData", calculatePaginaionData(paginaionData,Constants.MAX_ORDERS_PAGE, readable.getTotal()));
        } else {
        	model.addAttribute( "paginationData", null);
        }
        
        
        
        StringBuilder template = new StringBuilder().append(ControllerConstants.Tiles.Customer.customerOrders).append(".").append(store.getStoreTemplate());
        return template.toString();
	}
	

	@PreAuthorize("hasRole('AUTH_CUSTOMER')")
    @RequestMapping(value="/order.html", method={RequestMethod.GET,RequestMethod.POST})
    public String orderDetails(final Model model,final HttpServletRequest request,@RequestParam(value = "orderId" ,required=true) final String orderId) throws Exception{
        
		MerchantStore store = getSessionAttribute(Constants.MERCHANT_STORE, request);
		
		Language language = (Language)request.getAttribute(Constants.LANGUAGE);
		
		if(StringUtils.isBlank( orderId )){
        	LOGGER.error( "Order Id can not be null or empty" );
        }
        LOGGER.info( "Fetching order details for Id " +orderId);
        
        //get order id
        Long lOrderId = null;
        try {
        	lOrderId = Long.parseLong(orderId);
        } catch(NumberFormatException nfe) {
        	LOGGER.error("Cannot parse orderId to long " + orderId);
        	return "redirect:/"+Constants.SHOP_URI;
        }
        
        
        //check if order belongs to customer logged in
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = null;
    	if(auth != null &&
        		 request.isUserInRole("AUTH_CUSTOMER")) {
    		customer = customerFacade.getCustomerByUserName(auth.getName(), store);

        }
    	
    	if(customer==null) {
    		return "redirect:/"+Constants.SHOP_URI;
    	}
    	
    	ReadableOrder order = orderFacade.getReadableOrder(lOrderId, store, customer.getDefaultLanguage());

        model.addAttribute("order", order);
        
		//check if any downloads exist for this order
		List<OrderProductDownload> orderProductDownloads = orderProdctDownloadService.getByOrderId(order.getId());
		if(CollectionUtils.isNotEmpty(orderProductDownloads)) {
			ReadableOrderProductDownloadPopulator populator = new ReadableOrderProductDownloadPopulator();
			List<ReadableOrderProductDownload> downloads = new ArrayList<ReadableOrderProductDownload>();
			for(OrderProductDownload download : orderProductDownloads) {
				ReadableOrderProductDownload view = new ReadableOrderProductDownload();
				populator.populate(download, view,  store, language);
				downloads.add(view);
			}
			model.addAttribute("downloads", downloads);
		}

        StringBuilder template = new StringBuilder().append(ControllerConstants.Tiles.Customer.customerOrder).append(".").append(store.getStoreTemplate());
        return template.toString();
        
    }
}
