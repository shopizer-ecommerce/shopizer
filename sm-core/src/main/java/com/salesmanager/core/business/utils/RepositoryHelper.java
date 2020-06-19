package com.salesmanager.core.business.utils;

import javax.persistence.Query;

import com.salesmanager.core.model.common.Criteria;
import com.salesmanager.core.model.common.GenericEntityList;

/**
 * Helper for Spring Data JPA
 * 
 * @author carlsamson
 *
 */
public class RepositoryHelper {

	@SuppressWarnings("rawtypes")
	public static Query paginateQuery(Query q, Number count, GenericEntityList entityList, Criteria criteria) {

		if (entityList == null) {
			entityList = new GenericEntityList();
		}

		if (criteria.isLegacyPagination()) {
			if (criteria.getMaxCount() > 0) {
				q.setFirstResult(criteria.getStartIndex());
				if (criteria.getMaxCount() < count.intValue()) {
					q.setMaxResults(criteria.getMaxCount());
				} else {
					q.setMaxResults(count.intValue());
				}
			}
		} else {
			int firstResult = ((criteria.getStartPage()==0?criteria.getStartPage()+1:criteria.getStartPage()) - 1) * criteria.getPageSize();
			q.setFirstResult(firstResult);
			q.setMaxResults(criteria.getPageSize());
			int lastPageNumber = (int) ((count.intValue() / criteria.getPageSize()) + 1);
			entityList.setTotalPages(lastPageNumber);
			entityList.setTotalCount(count.intValue());
		}

		return q;

	}

}
