package com.salesmanager.core.business.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.salesmanager.core.business.modules.cart.ShoppingCartProcessor;
import com.salesmanager.core.business.modules.order.OrderProcessor;

/**
 * Pre and post processors triggered during certain actions such as
 * order processing and shopping cart manipulation
 * @author carlsamson
 *
 */
@Configuration
public class ProcessConfiguration {
	
	@Inject
	private OrderProcessor indexOrderProcessor;
	
	@Inject
	private ShoppingCartProcessor shippingCartProcessor;
	
	
	/**
	 * Order processors
	 * @return
	 */
	@Bean
	public List<OrderProcessor> orderPreProcessors() {
		List<OrderProcessor> processors = new ArrayList<OrderProcessor>();
		return processors;
	}
	
	@Bean
	public List<OrderProcessor> orderPostProcessors() {
		List<OrderProcessor> processors = new ArrayList<OrderProcessor>();
		//processors.add(indexOrderProcessor);
		return processors;
	}
	
	/**
	 * ShoppingCart processor
	 * @return
	 */
	@Bean
	public List<ShoppingCartProcessor> shoppingCartPostProcessors() {
		List<ShoppingCartProcessor> processors = new ArrayList<ShoppingCartProcessor>();
		//processors.add(shippingCartProcessor);
		return processors;
	}

}
