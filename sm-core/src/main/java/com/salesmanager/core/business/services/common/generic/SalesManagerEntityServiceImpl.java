package com.salesmanager.core.business.services.common.generic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SalesManagerEntityServiceImpl<K extends Serializable & Comparable<K>, E extends SalesManagerEntity<K, ?>>
    implements SalesManagerEntityService<K, E> {

  private final JpaRepository<E, K> repository;
  private final Class<E> objectClass = (Class<E>) ((ParameterizedType) getClass()
      .getGenericSuperclass()).getActualTypeArguments()[1];

  protected final Class<E> getObjectClass() {
    return objectClass;
  }

  public E getById(K id) {
    return repository.getOne(id);
  }

  public void save(E entity) throws ServiceException {
    repository.saveAndFlush(entity);
  }

  public void saveAll(Iterable<E> entities) throws ServiceException {
    repository.saveAll(entities);
  }

  public void create(E entity) throws ServiceException {
    save(entity);
  }

  public void update(E entity) throws ServiceException {
    save(entity);
  }

  public void delete(E entity) throws ServiceException {
    repository.delete(entity);
  }

  public void flush() {
    repository.flush();
  }

  public List<E> list() {
    return repository.findAll();
  }

  public Long count() {
    return repository.count();
  }

  protected E saveAndFlush(E entity) {
    return repository.saveAndFlush(entity);
  }
}
