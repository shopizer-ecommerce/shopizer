package com.salesmanager.core.business.generic.dao;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("entityDao")
@Transactional
public class SalesManagerJpaDaoSupport {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Cree la requete et applique les conditions de limite / offset et retourne la {@link TypedQuery}
	 * correspondante.
	 * 
	 * @param <T> le type de l'entite retournee
	 * @param criteria
	 * @param limit null si pas de limite
	 * @param offset null si pas d'offset
	 * @return la {@link TypedQuery} avec limite et offset le cas echeant
	 */
	protected <T> TypedQuery<T> buildTypedQuery(CriteriaQuery<T> criteria, Integer limit, Integer offset) {
		TypedQuery<T> query = getEntityManager().createQuery(criteria);
		if (offset != null) {
			query.setFirstResult(offset);
		}
		if (limit != null) {
			query.setMaxResults(limit);
		}
		return query;
		
	}
	
	protected void filterCriteriaQuery(CriteriaQuery<?> criteria, Expression<Boolean> filter) {
		if (filter != null) {
			criteria.where(filter);
		}
	}
	
	protected <T> Root<T> rootCriteriaQuery(CriteriaBuilder builder, CriteriaQuery<?> criteria, Class<T> objectClass) {
		return criteria.from(objectClass);
	}
	
	public <T, K> T getEntity(Class<T> clazz, K id) {
		return getEntityManager().find(clazz, id);
	}
	
	public <T, V> T getByField(Class<T> clazz, SingularAttribute<? super T, V> attribute, V fieldValue) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(clazz);
		Root<T> root = criteria.from(clazz);
		criteria.where(builder.equal(root.get(attribute), fieldValue));
		
		try {
			return buildTypedQuery(criteria, null, null).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	protected <T> void update(T entity) {
		if (!getEntityManager().contains(entity)) {
			getEntityManager().merge(entity);
			//throw new PersistenceException("Updated entity must be attached");
		}
		//TODO: http://blog.xebia.com/2009/03/23/jpa-implementation-patterns-saving-detached-entities/
	}
	
	protected <T> void save(T entity) {
		getEntityManager().persist(entity);
	}
	
	protected <T> void delete(T entity) {
		if (!getEntityManager().contains(entity)) {
			getEntityManager().merge(entity);
			//throw new PersistenceException("Failed to delete a detached entity");
		}
		getEntityManager().remove(entity);
	}
	
	protected <T> T refresh(T entity) {
		getEntityManager().refresh(entity);
		
		return entity;
	}
	
	public void flush() {
		getEntityManager().flush();
	}
	
	public void clear() {
		getEntityManager().clear();
	}
	
	protected <T> List<T> listEntity(Class<T> objectClass, Expression<Boolean> filter, Integer limit, Integer offset, Order... orders) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(objectClass);
		rootCriteriaQuery(builder, criteria, objectClass);
		
		if (filter != null) {
			filterCriteriaQuery(criteria, filter);
		}
		if (orders != null && orders.length > 0) {
			criteria.orderBy(orders);
		}
		TypedQuery<T> query = buildTypedQuery(criteria, limit, offset);
		
		List<T> entities = query.getResultList();
		
		if (orders == null || orders.length == 0) {
			sort(entities);
		}
		
		return entities;
	}
	
	protected <T> List<T> listEntity(Class<T> objectClass) {
		return listEntity(objectClass, null, null, null);
	}
	
	protected <T> List<T> listEntity(Class<T> objectClass, Expression<Boolean> filter) {
		return listEntity(objectClass, filter, null, null);
	}
	
	protected <T, V> List<T> listEntityByField(Class<T> objectClass, SingularAttribute<? super T, V> attribute, V fieldValue) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(objectClass);
		
		Root<T> root = rootCriteriaQuery(builder, criteria, objectClass);
		criteria.where(builder.equal(root.get(attribute), fieldValue));
		
		List<T> entities = buildTypedQuery(criteria, null, null).getResultList();
		
		sort(entities);
		
		return entities;
	}
	
	protected <T> Long countEntity(Class<T> clazz) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<T> root = rootCriteriaQuery(builder, criteria, clazz);
		
		criteria.select(builder.count(root));
		
		return buildTypedQuery(criteria, null, null).getSingleResult();
	}
	
	protected <T, V> Long countEntityByField(Class<T> clazz, SingularAttribute<? super T, V> attribute, V fieldValue) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
		Root<T> root = rootCriteriaQuery(builder, criteria, clazz);
		criteria.select(builder.count(root));
		
		Expression<Boolean> filter = builder.equal(root.get(attribute), fieldValue);
		filterCriteriaQuery(criteria, filter);
		
		return buildTypedQuery(criteria, null, null).getSingleResult();
	}
	
	protected <T> Long countEntity(Class<T> clazz, Expression<Boolean> filter) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
		Root<T> root = rootCriteriaQuery(builder, criteria, clazz);
		criteria.select(builder.count(root));
		
		filterCriteriaQuery(criteria, filter);
		
		return buildTypedQuery(criteria, null, null).getSingleResult();
	}
	
	protected <E> E getSingleResultOrNull(CriteriaQuery<E> cq) {
		try {
			return getEntityManager().createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	protected <E> E getSingleResultOrNull(TypedQuery<E> tq) {
		try {
			return tq.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> void sort(List<T> entities) {
		Object[] a = entities.toArray();
		Arrays.sort(a);
		ListIterator<T> i = entities.listIterator();
		for (int j = 0; j < a.length; j++) {
			i.next();
			i.set((T) a[j]);
		}
	}

}
