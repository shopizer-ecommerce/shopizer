package com.salesmanager.core.business.configuration.events.products;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.catalog.product.variant.ProductVariant;

/**
 * Aspect class that will trigger an event once a product is created Code
 * inspired from http://www.discoversdk.com/blog/spring-event-handling-and-aop
 * 
 * create product
 * update product
 * delete product
 * 
 * decorate
 * 	product variant
 * 	product attribute
 *  product image
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
	
	/**
	 * Product
	 */

	// save product
	
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
	
	// save variant
	
	@Pointcut("execution(* com.salesmanager.core.business.services.catalog.product.variant.ProductVariantService.saveProductVariant(com.salesmanager.core.model.catalog.product.variant.ProductVariant))")
	public void saveProductVariantMethod() {
	}

	@Pointcut("serviceMethods() && saveProductVariantMethod()")
	public void entityProductVariantCreationMethods() {
	}

	@AfterReturning(value = "entityProductVariantCreationMethods()", returning = "entity")
	public void createProductVariantEvent(JoinPoint jp, Object entity) throws Throwable {
		eventPublisher.publishEvent(new SaveProductVariantEvent(eventPublisher, (ProductVariant)entity, ((ProductVariant)entity).getProduct()));
	}
	
	// delete product variant
	
	@After("execution(* com.salesmanager.core.business.services.catalog.product.variant.ProductVariantService.delete(com.salesmanager.core.model.catalog.product.variant.ProductVariant))")
	public void logBeforeDeleteProductVariant(JoinPoint joinPoint) {
	   Object[] signatureArgs = joinPoint.getArgs();
	   eventPublisher.publishEvent(new DeleteProductVariantEvent(eventPublisher, (ProductVariant)signatureArgs[0], ((ProductVariant)signatureArgs[0]).getProduct()));
	}
	
	//product image

	@Pointcut("execution(* com.salesmanager.core.business.services.catalog.product.image.ProductImageService.saveOrUpdate(com.salesmanager.core.model.catalog.product.image.ProductImage))")
	public void saveProductImageMethod() {
	}
	
	@Pointcut("serviceMethods() && saveProductImageMethod()")
	public void entityProductImageCreationMethods() {
	}

	@AfterReturning(value = "entityProductImageCreationMethods()", returning = "entity")
	public void createProductImageEvent(JoinPoint jp, Object entity) throws Throwable {
		eventPublisher.publishEvent(new SaveProductImageEvent(eventPublisher, (ProductImage)entity, ((ProductImage)entity).getProduct()));
	}
	
	@After("execution(* com.salesmanager.core.business.services.catalog.product.image.ProductImageService.delete(com.salesmanager.core.model.catalog.product.image.ProductImage))")
	public void logBeforeDeleteProductImage(JoinPoint joinPoint) {
	   Object[] signatureArgs = joinPoint.getArgs();
	   eventPublisher.publishEvent(new DeleteProductImageEvent(eventPublisher, (ProductImage)signatureArgs[0], ((ProductImage)signatureArgs[0]).getProduct()));
	}
	
	
	//attributes
	
	@Pointcut("execution(* com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService.saveOrUpdate(com.salesmanager.core.model.catalog.product.attribute.ProductAttribute))")
	public void saveProductAttributeMethod() {
	}
	
	@Pointcut("serviceMethods() && saveProductAttributeMethod()")
	public void entityProductAttributeCreationMethods() {
	}
	
	@AfterReturning(value = "entityProductAttributeCreationMethods()", returning = "entity")
	public void createProductAttributeEvent(JoinPoint jp, Object entity) throws Throwable {
		eventPublisher.publishEvent(new SaveProductAttributeEvent(eventPublisher, (ProductAttribute)entity, ((ProductAttribute)entity).getProduct()));
	}
	
	@After("execution(* com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService.delete(com.salesmanager.core.model.catalog.product.attribute.ProductAttribute))")
	public void logBeforeDeleteProductAttribute(JoinPoint joinPoint) {
	   Object[] signatureArgs = joinPoint.getArgs();
	   eventPublisher.publishEvent(new DeleteProductAttributeEvent(eventPublisher, (ProductAttribute)signatureArgs[0], ((ProductAttribute)signatureArgs[0]).getProduct()));
	}	

	

}
