package com.salesmanager.core.business.services.common.generic;

import java.io.Serializable;
import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;


/**
 * <p>Service racine pour la gestion des entités.</p>
 *
 * @param <T> type d'entité
 * 
 * <p>Root service for managing entities.</p>
 *
 * @param <T> entity type
 */
public interface SalesManagerEntityService<K extends Serializable & Comparable<K>, E extends com.salesmanager.core.model.generic.SalesManagerEntity<K, ?>> extends TransactionalAspectAwareService{

	/**
	 * Crée l'entité dans la base de données. Mis à part dans les tests pour faire des sauvegardes simples, utiliser
	 * create() car il est possible qu'il y ait des listeners sur la création d'une entité.
	 * 
	 * @param entity entité
	 * 
	 * Creates the entity in the database. Except for tests to perform simple
     * saves, use create() because there may be listeners on entity creation.
     *
     * @param entity entity
	 */
	void save(E entity) throws ServiceException;
	
	/**
	 * Save all
	 */
	void saveAll(Iterable<E> entities) throws ServiceException;
	
	/**
	 * Met à jour l'entité dans la base de données.
	 * 
	 * @param entity entité
	 * 
	 * Updates the entity in the database.
     *
     * @param entity entity
	 */
	void update(E entity) throws ServiceException;
	
	/**
	 * Crée l'entité dans la base de données.
	 * 
	 * @param entity entité
	 * 
	 * Creates the entity in the database.
     *
     * @param entity entity
	 */
	void create(E entity) throws ServiceException;


	/**
	 * Supprime l'entité de la base de données
	 * 
	 * @param entity entité
	 * 
	 * Deletes the entity in the database.
     *
     * @param entity entity
	 */
	void delete(E entity) throws ServiceException;
	

	/**
	 * Retourne une entité à partir de son id.
	 * 
	 * @param id identifiant
	 * @return entité
	 * 
	 * Returns an entity by its ID.
     * 
     * @param id identifier
     * @return entity 
	 */
	E getById(K id);
	
	/**
	 * Renvoie la liste de l'ensemble des entités de ce type.
	 * 
	 * @return liste d'entités
	 * 
	 * Returns a list of the entities by type
	 * 
	 * @return list of entities
	 */
	List<E> list();
	
	/**
	 * Compte le nombre d'entités de ce type présentes dans la base.
	 * 
	 * @return nombre d'entités
	 * 
	 * Counts the number of entities of this type in the database.
     *
     * @return number of entities
	 */
	Long count();
	
	/**
	 * Flushe la session.
	 * 
	 * Flushes the session.
	 */
	void flush();
	


}
