package com.salesmanager.core.business.repositories.catalog.category;

import com.salesmanager.core.model.catalog.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


public class PageableCategoryRepositoryImpl implements PageableCategoryRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	@SuppressWarnings("unchecked")
	@Override
	public Page<Category> listByStore(Integer storeId, Integer languageId, String name, Pageable pageable) {
	  Query query = em.createNamedQuery("CATEGORY.listByStore");
	  Query countQueryResult = em.createNamedQuery("CATEGORY.listByStore.count");
	  query.setParameter(1, storeId);
	  query.setParameter(2, languageId);
	  query.setParameter(3, name == null ? "" : name);
	  query.setMaxResults(pageable.getPageSize());
	  query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		return new PageImpl<Category>(
				query.getResultList(),
				pageable,
				countQueryResult.getMaxResults());
	}

}
