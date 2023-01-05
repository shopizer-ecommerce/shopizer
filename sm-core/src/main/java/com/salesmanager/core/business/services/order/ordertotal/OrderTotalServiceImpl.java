package com.salesmanager.core.business.services.order.ordertotal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderSummary;
import com.salesmanager.core.model.order.OrderTotal;
import com.salesmanager.core.model.order.OrderTotalVariation;
import com.salesmanager.core.model.order.RebatesOrderTotalVariation;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.core.modules.order.total.OrderTotalPostProcessorModule;

@Service("OrderTotalService")
public class OrderTotalServiceImpl implements OrderTotalService {
	
	@Autowired
	@Resource(name="orderTotalsPostProcessors")
	List<OrderTotalPostProcessorModule> orderTotalPostProcessors;
	
	@Inject
	private ProductService productService;


	@Override
	public OrderTotalVariation findOrderTotalVariation(OrderSummary summary, Customer customer, MerchantStore store, Language language)
			throws Exception {
	
		RebatesOrderTotalVariation variation = new RebatesOrderTotalVariation();
		
		List<OrderTotal> totals = null;
		
		if(orderTotalPostProcessors != null) {
			for(OrderTotalPostProcessorModule module : orderTotalPostProcessors) {
				//TODO check if the module is enabled from the Admin
				
				List<ShoppingCartItem> items = summary.getProducts();
				for(ShoppingCartItem item : items) {

					Product product = productService.getBySku(item.getSku(), store, language);
					//Product product = productService.getProductForLocale(productId, language, languageService.toLocale(language, store));
					
					OrderTotal orderTotal = module.caculateProductPiceVariation(summary, item, product, customer, store);
					if(orderTotal==null) {
						continue;
					}
					if(totals==null) {
						totals = new ArrayList<OrderTotal>();
						variation.setVariations(totals);
					}
					
					//if product is null it will be catched when invoking the module
					orderTotal.setText(StringUtils.isNoneBlank(orderTotal.getText())?orderTotal.getText():product.getProductDescription().getName());
					variation.getVariations().add(orderTotal);	
				}
			}
		}
		
		
		return variation;
	}

}
