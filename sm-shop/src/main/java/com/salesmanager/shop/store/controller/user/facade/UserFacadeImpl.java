package com.salesmanager.shop.store.controller.user.facade;

import java.util.ArrayList;
import java.util.List;

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
	public ReadableUser findByUserName(String userName, Language lang) throws Exception {
		
		User user = userService.getByUserName(userName);
		if(user == null) {
			return null;
		}
		
		ReadableUser readableUser = new ReadableUser();
		
		ReadableUserPopulator populator = new ReadableUserPopulator();
		populator.populate(user, readableUser, user.getMerchantStore(), lang);
		
		return readableUser;
	}

	@Override
	public List<ReadablePermission> findPermissionsByGroups(List<Integer> ids) throws Exception {
		

		List<Permission> permissions = permissionService.getPermissions(ids);
		List<ReadablePermission> values = new ArrayList<ReadablePermission>();
		for(Permission p : permissions) {
			ReadablePermission rp = new ReadablePermission();
			rp.setId(new Long(p.getId()));
			rp.setName(p.getPermissionName());
			values.add(rp);
		}
		
		
		return values;
	}

	@Override
	public boolean authorizedStore(String userName, String merchantStoreCode) {
		
	   try {
  		ReadableUser ruser = this.findByUserName(userName, languageService.defaultLanguage());
  		
  		
  		if(ruser==null) {//should not happen
  			throw new Exception("Error while creating store, invalid user " + userName);
  		}
  		
  		//unless superadmin
  		for(ReadableGroup group : ruser.getGroups()) {
  			if(Constants.GROUP_SUPERADMIN.equals(group.getName())) {
  				return true;
  			}
  		}
  
  		
  		boolean authorized = false; 
  		User user = userService.findByStore(ruser.getId(), merchantStoreCode);
  		if(user != null) {
  			authorized = true;
  		}
  		
  		return authorized;
	   } catch(Exception e) {
	     throw new ServiceRuntimeException("Cannot authorize user " + userName + " for store " + merchantStoreCode,e.getMessage());
	   }
	}

}
