package com.salesmanager.shop.store.controller.user.facade;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.user.PermissionService;
import com.salesmanager.core.business.services.user.UserService;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.user.Permission;
import com.salesmanager.core.model.user.User;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.security.ReadableGroup;
import com.salesmanager.shop.model.security.ReadablePermission;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.populator.user.ReadableUserPopulator;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;

@Service("userFacade")
public class UserFacadeImpl implements UserFacade {
	
	
	@Inject
	private UserService userService;

	@Inject
	private PermissionService permissionService;
	
	@Inject
	private LanguageService languageService;
	
	@Override
	public ReadableUser findByUserName(String userName, Language lang) {
		User user = getByUserName(userName);
    return convertUserToReadableUser(lang, user);
	}

  private ReadableUser convertUserToReadableUser(Language lang, User user) {
    ReadableUserPopulator populator = new ReadableUserPopulator();
    try{
      return populator.populate(user, new ReadableUser(), user.getMerchantStore(), lang);
    } catch (ConversionException e){
      throw new ConversionRuntimeException(e);
    }
  }

  private User getByUserName(String userName) {
	  try{
      return userService.getByUserName(userName);
    } catch (ServiceException e) {
	    throw new ServiceRuntimeException(e);
    }
  }

  @Override
	public ReadableUser findByUserNameWithPermissions(String userName, Language lang) {
    ReadableUser readableUser = findByUserName(userName, lang);

    /**
     * Add permissions on top of the groups
     */
    List<Integer> groupIds = readableUser.getGroups()
        .stream()
        .map(ReadableGroup::getId)
        .map(Long::intValue)
        .collect(Collectors.toList());

    List<ReadablePermission> permissions = findPermissionsByGroups(groupIds);
    readableUser.setPermissions(permissions);

    return readableUser;
	}

  @Override
  public List<ReadablePermission> findPermissionsByGroups(List<Integer> ids) {
    return getPermissionsByIds(ids).stream()
        .map(permission -> convertPermissionToReadablePermission(permission))
        .collect(Collectors.toList());
  }

  private ReadablePermission convertPermissionToReadablePermission(Permission permission) {
    ReadablePermission readablePermission = new ReadablePermission();
    readablePermission.setId(Long.valueOf(permission.getId()));
    readablePermission.setName(permission.getPermissionName());
    return readablePermission;
  }

  private List<Permission> getPermissionsByIds(List<Integer> ids) {
    try {
      return permissionService.getPermissions(ids);
    } catch (ServiceException e) {
      throw new ServiceRuntimeException(e);
    }
  }

  @Override
	public boolean authorizedStore(String userName, String merchantStoreCode) {
		
	   try {
  		ReadableUser readableUser = findByUserName(userName, languageService.defaultLanguage());

  		//unless superadmin
  		for(ReadableGroup group : readableUser.getGroups()) {
  			if(Constants.GROUP_SUPERADMIN.equals(group.getName())) {
  				return true;
  			}
  		}
  
  		
  		boolean authorized = false; 
  		User user = userService.findByStore(readableUser.getId(), merchantStoreCode);
  		if(user != null) {
  			authorized = true;
  		}
  		
  		return authorized;
	   } catch(Exception e) {
	     throw new ServiceRuntimeException("Cannot authorize user " + userName + " for store " + merchantStoreCode,e.getMessage());
	   }
	}

}
