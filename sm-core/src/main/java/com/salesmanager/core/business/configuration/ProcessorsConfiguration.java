package com.salesmanager.core.business.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.salesmanager.core.business.modules.cart.ShoppingCartProcessor;
import com.salesmanager.core.business.modules.order.OrderProcessor;
import com.salesmanager.core.business.modules.order.total.PromoCodeCalculatorModule;
import com.salesmanager.core.modules.order.total.OrderTotalPostProcessorModule;

/**
 * Pre and post processors triggered during certain actions such as
 * order processing and shopping cart processing
 * 
 * 2 types of processors
 * - functions processors
 * 		Triggered during defined simple events - ex add to cart checkout
 * 
 * - calculation processors
 * 		Triggered during shopping cart and order total calculation
 * 
 * @author carlsamson
 *
 */
@Configuration
public class ProcessorsConfiguration {
	
	@Inject
	private OrderProcessor indexOrderProcessor;
	
	@Inject
	private ShoppingCartProcessor shippingCartProcessor;
	
	@Inject
	private PromoCodeCalculatorModule promoCodeCalculatorModule;

	
	/**
	 * Order function processors
	 * @return
	 */
	@Bean
	public List<OrderProcessor> orderPreProcessors() {
		return new ArrayList<OrderProcessor>();
	}
	
	@Bean
	public List<OrderProcessor> orderPostProcessors() {
		//processors.add(indexOrderProcessor);
		return new ArrayList<OrderProcessor>();
	}
	
	/**
	 * ShoppingCart function processor
	 * @return
	 */
	@Bean
	public List<ShoppingCartProcessor> shoppingCartPostProcessors() {
		//processors.add(shippingCartProcessor);
		return new ArrayList<ShoppingCartProcessor>();
	}
	
	
	/**
	 * Calculate processors
	 * @return
	 */
	@Bean
	public List<OrderTotalPostProcessorModule> orderTotalsPostProcessors() {
		
		List<OrderTotalPostProcessorModule> processors = new ArrayList<OrderTotalPostProcessorModule>();
		///processors.add(new com.salesmanager.core.business.modules.order.total.ManufacturerShippingCodeOrderTotalModuleImpl());
		processors.add(promoCodeCalculatorModule);
		return processors;
		
	}

}
