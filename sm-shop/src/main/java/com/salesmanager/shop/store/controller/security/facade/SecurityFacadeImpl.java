package com.salesmanager.shop.store.controller.security.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.services.user.PermissionService;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.PermissionCriteria;
import com.salesmanager.core.model.user.PermissionList;
import com.salesmanager.shop.model.security.ReadablePermission;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;

@Service("securityFacade")
public class SecurityFacadeImpl implements SecurityFacade {

  @Inject
  private PermissionService permissionService;

  @Inject
  private GroupService groupService;

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Override
  public List<ReadablePermission> getPermissions(List<String> groups) {

    List<Group> userGroups = null;
    try {
      userGroups = groupService.listGroupByNames(groups);


      // TODO if groups == null

      List<Integer> ids = new ArrayList<Integer>();
      for (Group g : userGroups) {
        ids.add(g.getId());
      }

      PermissionCriteria criteria = new PermissionCriteria();
      criteria.setGroupIds(new HashSet(ids));

      PermissionList permissions = permissionService.listByCriteria(criteria);
      throw new ServiceRuntimeException("Not implemented");
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }

}
