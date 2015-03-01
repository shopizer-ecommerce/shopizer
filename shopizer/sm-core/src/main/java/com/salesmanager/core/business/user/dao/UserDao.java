package com.salesmanager.core.business.user.dao;

import java.util.List;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.user.model.User;

public interface UserDao extends SalesManagerEntityDao<Long, User> {

	User getByUserName(String userName);

	List<User> listUser();

	List<User> listUserByStore(MerchantStore store);



}
