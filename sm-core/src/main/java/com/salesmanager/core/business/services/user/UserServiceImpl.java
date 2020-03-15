package com.salesmanager.core.business.services.user;

import java.util.List;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.user.PageableUserRepository;
import com.salesmanager.core.business.repositories.user.UserRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.common.Criteria;
import com.salesmanager.core.model.common.GenericEntityList;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.user.User;



public class UserServiceImpl extends SalesManagerEntityServiceImpl<Long, User>
    implements UserService {


  private UserRepository userRepository;
  
  @Autowired
  private PageableUserRepository pageableUserRepository;

  @Inject
  public UserServiceImpl(UserRepository userRepository) {
    super(userRepository);
    this.userRepository = userRepository;

  }


  @Override
  public User getByUserName(String userName) throws ServiceException {
    return userRepository.findByUserName(userName);
  }
  


  @Override
  public void delete(User user) throws ServiceException {
    User u = this.getById(user.getId());
    super.delete(u);

  }

  @Override
  public List<User> listUser() throws ServiceException {
    try {
      return userRepository.findAll();
    } catch (Exception e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public List<User> listByStore(MerchantStore store) throws ServiceException {
    try {
      return userRepository.findByStore(store.getId());
    } catch (Exception e) {
      throw new ServiceException(e);
    }
  }


  @Override
  public void saveOrUpdate(User user) throws ServiceException {
    userRepository.save(user);
  }


  @Override
  public User findByStore(Long userId, String storeCode) throws ServiceException {
    return userRepository.findByUserAndStore(userId, storeCode);
  }


  @Override
  public GenericEntityList<User> listByCriteria(Criteria criteria) throws ServiceException {
    return userRepository.listByCriteria(criteria);
  }


  @Override
  public User getByUserName(String userName, String storeCode) throws ServiceException {
    return userRepository.findByUserName(userName, storeCode);
  }


@Override
public Page<User> listByCriteria(Criteria criteria, int page, int count) throws ServiceException {
	
	Pageable pageRequest = PageRequest.of(page, count);
	Page<User> users = null;
	if(StringUtils.isBlank(criteria.getStoreCode())) {
		
		users = pageableUserRepository.listAll(criteria.getUser(), pageRequest);
	} else {

		users = pageableUserRepository.listByStore(criteria.getStoreCode(), criteria.getUser(), pageRequest);
	}
	
	
	return users;
}


}
