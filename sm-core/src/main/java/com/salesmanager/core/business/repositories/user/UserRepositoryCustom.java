package com.salesmanager.core.business.repositories.user;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.common.Criteria;
import com.salesmanager.core.model.common.GenericEntityList;
import com.salesmanager.core.model.user.User;

public interface UserRepositoryCustom {
  
  GenericEntityList<User> listByCriteria(Criteria criteria) throws ServiceException;

}
