package com.salesmanager.core.business.services.user;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.user.User;

import java.util.List;


public interface UserService extends SalesManagerEntityService<Long, User> {

    User getByUserName(String userName) throws ServiceException;

    List<User> listUser() throws ServiceException;

    void saveOrUpdate(User user) throws ServiceException;

    List<User> listByStore(MerchantStore store) throws ServiceException;


}
