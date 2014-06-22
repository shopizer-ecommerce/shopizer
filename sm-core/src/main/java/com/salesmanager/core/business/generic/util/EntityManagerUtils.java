package com.salesmanager.core.business.generic.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * <p>Classe permettant de manipuler le EntityManager.</p>
 * 
 * <p>Utilisée dans les tests pour ouvrir la session au début d'un test et la fermet à la fin</p>
 */
@Component
public class EntityManagerUtils {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Mise en place du EntityManager
	 */
	public EntityManager openEntityManager() {
		if (TransactionSynchronizationManager.hasResource(entityManagerFactory)) {
			return ((EntityManagerHolder) TransactionSynchronizationManager.getResource(entityManagerFactory)).getEntityManager();
		} else {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			TransactionSynchronizationManager.bindResource(entityManagerFactory, new EntityManagerHolder(entityManager));
			return entityManager;
		}
	}
	
	/**
	 * Suppression du EntityManager.
	 */
	public void closeEntityManager() {
		if (TransactionSynchronizationManager.hasResource(entityManagerFactory)) {
			EntityManagerHolder entityManagerHolder = (EntityManagerHolder) TransactionSynchronizationManager.unbindResource(entityManagerFactory);
			EntityManagerFactoryUtils.closeEntityManager(entityManagerHolder.getEntityManager());
		}
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
