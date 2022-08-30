package com.salesmanager.core.business.configuration.events.products;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;

/**
 * Aspect class that will trigger an event once a product is created Code
 * inspired from http://www.discoversdk.com/blog/spring-event-handling-and-aop
 * 
 * create product
 * update product
 * delete product
 * 
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
	public void serviceMethods() {
	}

	@Pointcut("execution(* com.salesmanager.core.business.services.catalog.product.ProductService.saveProduct(com.salesmanager.core.model.catalog.product.Product))")
	public void saveProductMethod() {
	}

	@Pointcut("serviceMethods() && saveProductMethod()")
	public void entityCreationMethods() {
	}

	@AfterReturning(value = "entityCreationMethods()", returning = "entity")
	public void createProductEvent(JoinPoint jp, Object entity) throws Throwable {
		eventPublisher.publishEvent(new SaveProductEvent(eventPublisher, (Product)entity));
	}

	// delete product
	
	@After("execution(* com.salesmanager.core.business.services.catalog.product.ProductService.delete(com.salesmanager.core.model.catalog.product.Product))")
	public void logBeforeDeleteProduct(JoinPoint joinPoint) {
	   Object[] signatureArgs = joinPoint.getArgs();
	   eventPublisher.publishEvent(new DeleteProductEvent(eventPublisher, (Product)signatureArgs[0]));
	}
	
	// save instance
	
	@Pointcut("execution(* com.salesmanager.core.business.services.catalog.product.instance.ProductInstanceService.saveProductInstance(com.salesmanager.core.model.catalog.product.instance.ProductInstance))")
	public void saveProductInstanceMethod() {
	}

	@Pointcut("serviceMethods() && saveProductInstanceMethod()")
	public void entityProductInstanceCreationMethods() {
	}

	@AfterReturning(value = "entityProductInstanceCreationMethods()", returning = "entity")
	public void createProductInstanceEvent(JoinPoint jp, Object entity) throws Throwable {
		eventPublisher.publishEvent(new SaveProductInstanceEvent(eventPublisher, (ProductInstance)entity, ((ProductInstance)entity).getProduct()));
	}
	
	// delete product instance
	
	@After("execution(* com.salesmanager.core.business.services.catalog.product.instance.ProductInstanceService.delete(com.salesmanager.core.model.catalog.product.instance.ProductInstance))")
	public void logBeforeDeleteProductInstance(JoinPoint joinPoint) {
	   Object[] signatureArgs = joinPoint.getArgs();
	   eventPublisher.publishEvent(new DeleteProductInstanceEvent(eventPublisher, (ProductInstance)signatureArgs[0], ((ProductInstance)signatureArgs[0]).getProduct()));
	}

	

}
