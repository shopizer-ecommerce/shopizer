package com.salesmanager.core.business.configuration.events.products;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Aspect class that will trigger an event once a product is created
 * Code inspired from http://www.discoversdk.com/blog/spring-event-handling-and-aop
 * @author carlsamson
 *
 */

@Component
@Aspect
public class PublishProductAspect {
	
	   private ApplicationEventPublisher eventPublisher;
	   
	   @Autowired
	   public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
	       this.eventPublisher = eventPublisher;
	   }
		
		  @Pointcut("@target(org.springframework.stereotype.Service)") 
		  public void serviceMethods() {}
		  
		  @Pointcut("execution(* com.salesmanager.core.business.services.catalog.product.ProductService.createProduct(com.salesmanager.core.model.catalog.product.Product))")
		  public void createProductMethod() {}

		  
		  @Pointcut("serviceMethods() && createProductMethod()") 
		  public void entityCreationMethods() {}
		  
		  @AfterReturning(value = "entityCreationMethods()", returning = "entity")
		   public void createEvent(JoinPoint jp, Object entity) throws Throwable {
			  //System.out.println("*********** INTO THE METHOD ******************");
		       //eventPublisher.publishEvent(
		    	//	   new FooCreationEvent(entity));
		  }
	  

}
