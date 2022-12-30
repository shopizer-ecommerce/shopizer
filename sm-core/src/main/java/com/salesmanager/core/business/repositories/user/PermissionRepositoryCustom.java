package com.salesmanager.core.business.repositories.user;

import com.salesmanager.core.model.user.PermissionCriteria;
import com.salesmanager.core.model.user.PermissionList;




public interface PermissionRepositoryCustom {

	PermissionList listByCriteria(PermissionCriteria criteria);


}
