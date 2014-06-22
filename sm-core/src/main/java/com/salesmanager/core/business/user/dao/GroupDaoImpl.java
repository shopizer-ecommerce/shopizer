package com.salesmanager.core.business.user.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.user.model.Group;
import com.salesmanager.core.business.user.model.GroupType;
import com.salesmanager.core.business.user.model.QGroup;

@Repository("groupDao")
public class GroupDaoImpl extends SalesManagerEntityDaoImpl<Integer, Group> implements
		GroupDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List<Group> getGroupsListBypermissions(Set permissionIds) {
		StringBuilder qs = new StringBuilder();
		qs.append("select g from Group as g ");
		qs.append("join fetch g.permissions perms ");
		qs.append("where perms.id in (:cid) ");



    	String hql = qs.toString();
		Query q = super.getEntityManager().createQuery(hql);

    	q.setParameter("cid", permissionIds);
	
    	@SuppressWarnings("unchecked")
		List<Group> groups =  q.getResultList();

    	
    	return groups;
	}
	
	@Override
	public List<Group> listGroupByIds(Set<Integer> ids) {
		
		StringBuilder qs = new StringBuilder();
		qs.append("select distinct g from Group as g ");
		qs.append("join fetch g.permissions perms ");
		qs.append("where g.id in (:gid) ");
		
    	String hql = qs.toString();
		Query q = super.getEntityManager().createQuery(hql);

    	q.setParameter("gid", ids);
	
    	@SuppressWarnings("unchecked")
		List<Group> groups =  q.getResultList();

    	
    	return groups;
		
	}

	@Override
	public List<Group> listGroup(GroupType groupType) {
		QGroup qGroup = QGroup.group;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qGroup)
			.where(qGroup.groupType.eq(groupType))
			.orderBy(qGroup.id.asc());
		
		return query.listDistinct(qGroup);
	}

}
