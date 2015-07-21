package com.salesmanager.core.business.generic.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.metamodel.SingularAttribute;

import com.salesmanager.core.business.generic.model.SalesManagerEntity;
import com.salesmanager.core.business.generic.util.GenericEntityUtils;

/**
 * @param <T> entity type
 */
public abstract class SalesManagerEntityDaoImpl<K extends Serializable & Comparable<K>, E extends SalesManagerEntity<K, ?>>
		extends SalesManagerJpaDaoSupport
		implements SalesManagerEntityDao<K, E> {
	
	private Class<E> objectClass;
	
	@SuppressWarnings("unchecked")
	public SalesManagerEntityDaoImpl() {
		this.objectClass = (Class<E>) GenericEntityUtils.getGenericEntityClassFromComponentDefinition(getClass());
	}
	
	protected final Class<E> getObjectClass() {
		return objectClass;
	}
	
	
	public E getEntity(Class<? extends E> clazz, K id) {
		return super.getEntity(getObjectClass(), id);
	}
	
	
	public E getById(K id) {
		return super.getEntity(getObjectClass(), id);
	}
	
	
	public <V> E getByField(SingularAttribute<? super E, V> attribute, V fieldValue) {
		return super.getByField(getObjectClass(), attribute, fieldValue);
	}
	
	
	public void update(E entity) {
		super.update(entity);
	}
	
	
	public void save(E entity) {
		super.save(entity);
	}
	
	
	public void delete(E entity) {
		super.delete(entity);
	}
	
	
	public E refresh(E entity) {
		return super.refresh(entity);
	}
	
	
	public List<E> list() {
		return super.listEntity(getObjectClass());
	}
	
	
	public <V> List<E> listByField(SingularAttribute<? super E, V> attribute, V fieldValue) {
		return super.listEntityByField(getObjectClass(), attribute, fieldValue);
	}
	
	
	public <T extends E> List<T> list(Class<T> objectClass, Expression<Boolean> filter, Integer limit, Integer offset, Order... orders) {
		return super.listEntity(objectClass, filter, limit, offset, orders);
	}
	
	
	public Long count() {
		return super.countEntity(getObjectClass());
	}
	
	
	public <V> Long countByField(SingularAttribute<? super E, V> attribute, V fieldValue) {
		return super.countEntityByField(getObjectClass(), attribute, fieldValue);
	}
	
	
	public Long count(Expression<Boolean> filter) {
		return super.countEntity(getObjectClass(), filter);
	}
	
	
	@Override
	public EntityManager getEntityManager() {
		return super.getEntityManager();
	}
}
