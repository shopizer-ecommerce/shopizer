package com.salesmanager.shop.store.controller.security.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import org.springframework.security.crypto.password.PasswordEncoder;
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
  
  //private static final String USER_PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{6,12})";
  private static final String USER_PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{6,12})";
  
  private Pattern userPasswordPattern = Pattern.compile(USER_PASSWORD_PATTERN);

  @Inject
  private PermissionService permissionService;

  @Inject
  private GroupService groupService;
  
  @Inject
  private PasswordEncoder passwordEncoder;

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
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public boolean validateUserPassword(String password) {

    Matcher matcher = userPasswordPattern.matcher(password);
    return matcher.matches();
  }

  @Override
  public String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }

  @Override
  public boolean matchPassword(String modelPassword, String newPassword) {
    return passwordEncoder.matches(newPassword, modelPassword);
  }
  
  

}
