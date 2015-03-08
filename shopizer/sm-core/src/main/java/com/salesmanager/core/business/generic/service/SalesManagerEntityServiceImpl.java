package com.salesmanager.core.business.generic.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.model.SalesManagerEntity;
import com.salesmanager.core.business.generic.util.GenericEntityUtils;

/**
 * @param <T> entity type
 */
public abstract class SalesManagerEntityServiceImpl<K extends Serializable & Comparable<K>, E extends SalesManagerEntity<K, ?>>
	implements SalesManagerEntityService<K, E> {
	
	/**
	 * Classe de l'entité, déterminé à partir des paramètres generics.
	 */
	private Class<E> objectClass;

	private SalesManagerEntityDao<K, E> genericDao;

	@SuppressWarnings("unchecked")
	public SalesManagerEntityServiceImpl(SalesManagerEntityDao<K, E> genericDao) {
		this.genericDao = genericDao;
		
		this.objectClass = (Class<E>) GenericEntityUtils.getGenericEntityClassFromComponentDefinition(getClass());
	}
	
	protected final Class<E> getObjectClass() {
		return objectClass;
	}

	
	public E getEntity(Class<? extends E> clazz, K id) {
		return genericDao.getEntity(clazz, id);
	}

	
	public E getById(K id) {
		return genericDao.getById(id);
	}

	/**
	 * @param fieldName condition field
	 * @param fieldValue field value
	 * @return entity
	 */
	protected <V> E getByField(SingularAttribute<? super E, V> fieldName, V fieldValue) {
		return genericDao.getByField(fieldName, fieldValue);
	}
	
	
	public void save(E entity) throws ServiceException {
		genericDao.save(entity);
	}
	
	
	public void create(E entity) throws ServiceException {
		createEntity(entity);
	}
	
	
	protected void createEntity(E entity) throws ServiceException {
		save(entity);
	}
	
	
	public final void update(E entity) throws ServiceException {
		updateEntity(entity);
	}
	
	protected void updateEntity(E entity) throws ServiceException {
		genericDao.update(entity);
	}
	
	
	public void delete(E entity) throws ServiceException {
		genericDao.delete(entity);
	}
	
	
	public void flush() {
		genericDao.flush();
	}
	
	
	public void clear() {
		genericDao.clear();
	}
	
	
	public E refresh(E entity) {
		return genericDao.refresh(entity);
	}

	
	public List<E> list() {
		return genericDao.list();
	}
	
	/**
	 * Renvoie la liste des entités dont le champ donné en paramètre a la bonne valeur.
	 * 
	 * @param fieldName le champ sur lequel appliquer la condition
	 * @param fieldValue valeur du champ
	 * @return liste d'entités
	 */
	protected <V> List<E> listByField(SingularAttribute<? super E, V> fieldName, V fieldValue) {
		return genericDao.listByField(fieldName, fieldValue);
	}
	
	
	public Long count() {
		return genericDao.count();
	}

}