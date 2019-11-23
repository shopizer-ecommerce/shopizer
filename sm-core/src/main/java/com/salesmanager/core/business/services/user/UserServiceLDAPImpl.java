package com.salesmanager.core.business.services.user;

import java.util.List;

import org.springframework.data.domain.Page;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.common.Criteria;
import com.salesmanager.core.model.common.GenericEntityList;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.user.User;

public class UserServiceLDAPImpl implements UserService {

	@Override
	public void save(User entity) throws ServiceException {
		throw new ServiceException("Not implemented");

	}

	@Override
	public void update(User entity) throws ServiceException {
		throw new ServiceException("Not implemented");

	}

	@Override
	public void create(User entity) throws ServiceException {
		throw new ServiceException("Not implemented");

	}

	@Override
	public void delete(User entity) throws ServiceException {
		throw new ServiceException("Not implemented");

	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByUserName(String userName) throws ServiceException {
		// TODO Auto-generated method stub
		throw new ServiceException("Not implemented");
	}

	@Override
	public List<User> listUser() throws ServiceException {
		// TODO Auto-generated method stub
		throw new ServiceException("Not implemented");
	}

	@Override
	public void saveOrUpdate(User user) throws ServiceException {
		throw new ServiceException("Not implemented");

	}

	@Override
	public List<User> listByStore(MerchantStore store) throws ServiceException {
		// TODO Auto-generated method stub
		throw new ServiceException("Not implemented");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.salesmanager.core.business.services.common.generic.
	 * SalesManagerEntityService#flush()
	 */
	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByStore(Long userId, String storeCode) throws ServiceException {
		// TODO Auto-generated method stub
		throw new ServiceException("Not implemented");
	}

	@Override
	public GenericEntityList<User> listByCriteria(Criteria criteria) throws ServiceException {
		// TODO Auto-generated method stub
		throw new ServiceException("Not implemented");
	}

	@Override
	public User getByUserName(String userName, String storeCode) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> listByCriteria(Criteria criteria, int page, int count) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
