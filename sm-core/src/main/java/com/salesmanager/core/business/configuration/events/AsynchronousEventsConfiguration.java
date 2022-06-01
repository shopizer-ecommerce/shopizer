package com.salesmanager.core.business.configuration.events;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * Events will be asynchronous (in a different thread)
 * @author carlsamson
 *
 */
@Configuration
public class AsynchronousEventsConfiguration {
	
	   @Bean(name = "applicationEventMulticaster")
	   public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
	       SimpleApplicationEventMulticaster eventMulticaster
	         = new SimpleApplicationEventMulticaster();
	        
	       eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
	       return eventMulticaster;
	   }

}
