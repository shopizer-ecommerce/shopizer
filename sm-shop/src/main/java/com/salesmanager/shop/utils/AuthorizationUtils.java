package com.salesmanager.shop.utils;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;

/**
 * Performs authorization check for REST Api
 * - check if user is in role
 * - check if user can perform actions on marchant
 * @author carlsamson
 *
 */
@Component
public class AuthorizationUtils {
	
	@Inject
	private UserFacade userFacade;
	
	public String authenticatedUser() {
		String authenticatedUser = userFacade.authenticatedUser();
		if (authenticatedUser == null) {
			throw new UnauthorizedException();
		}
		return authenticatedUser;
	}
	
	public void authorizeUser(String authenticatedUser, List<String> roles, MerchantStore store) {
		userFacade.authorizedGroup(authenticatedUser, roles);
		if (!userFacade.userInRoles(authenticatedUser, Arrays.asList(Constants.GROUP_SUPERADMIN))) {
			if (!userFacade.authorizedStore(authenticatedUser, store.getCode())) {
				throw new UnauthorizedException("Operation unauthorized for user [" + authenticatedUser
						+ "] and store [" + store.getCode() + "]");
			}
		}
	}

}
