package com.salesmanager.core.business.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.user.model.QGroup;
import com.salesmanager.core.business.user.model.QUser;
import com.salesmanager.core.business.user.model.User;

@Repository("userDao")
public class UserDaoImpl extends SalesManagerEntityDaoImpl<Long, User> implements
		UserDao {
	
	@Override
	public User getByUserName(String userName) {
		
		
		QUser qUser = QUser.user;
		QGroup qGroup = QGroup.group;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qUser)
			.innerJoin(qUser.groups, qGroup).fetch()
			.innerJoin(qUser.merchantStore).fetch()
			.leftJoin(qUser.defaultLanguage).fetch()
			.where(qUser.adminName.eq(userName));
		
		

		User user = query.uniqueResult(qUser);
		return user;
	}
	
	@Override
	public User getById(Long id) {
		
		
		QUser qUser = QUser.user;
		QGroup qGroup = QGroup.group;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qUser)
			.innerJoin(qUser.groups, qGroup).fetch()
			.innerJoin(qUser.merchantStore).fetch()
			.leftJoin(qUser.defaultLanguage).fetch()
			.where(qUser.id.eq(id));
		
		

		User user = query.uniqueResult(qUser);
		return user;
	}

	@Override
	public List<User> listUser() {
		QUser qUser = QUser.user;
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qUser)
			.innerJoin(qUser.groups).fetch()
			.innerJoin(qUser.merchantStore).fetch()
			.leftJoin(qUser.defaultLanguage).fetch()
			.orderBy(qUser.id.asc());
		
		return query.listDistinct(qUser);
	}
	
	@Override
	public List<User> listUserByStore(MerchantStore store) {
		QUser qUser = QUser.user;
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qUser)
			.innerJoin(qUser.groups).fetch()
			.innerJoin(qUser.merchantStore).fetch()
			.leftJoin(qUser.defaultLanguage).fetch()
			.orderBy(qUser.id.asc())
			.where(qUser.merchantStore.id.eq(store.getId()));
		
		return query.listDistinct(qUser);
	}

}
