package com.salesmanager.core.business.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.salesmanager.core.business.modules.order.OrderProcessor;

@Configuration
public class OrderProcessConfiguration {
	
	@Inject
	private OrderProcessor indexOrderProcessor;
	
	@Bean
	public List<OrderProcessor> orderPreProcessors() {
		List<OrderProcessor> processors = new ArrayList<OrderProcessor>();
		return processors;
	}
	
	@Bean
	public List<OrderProcessor> orderPostProcessors() {
		List<OrderProcessor> processors = new ArrayList<OrderProcessor>();
		processors.add(indexOrderProcessor);
		return processors;
	}

}
