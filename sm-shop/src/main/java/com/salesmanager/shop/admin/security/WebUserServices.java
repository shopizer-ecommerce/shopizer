package com.salesmanager.shop.admin.security;

import com.salesmanager.core.business.exception.ServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface WebUserServices extends UserDetailsService {

  void createDefaultAdmin() throws ServiceException;
}
