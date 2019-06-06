package com.salesmanager.shop.store.api.v1.security;

import static com.salesmanager.core.business.constants.Constants.DEFAULT_STORE;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.services.user.PermissionService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.Permission;
import com.salesmanager.shop.model.security.ReadableGroup;
import com.salesmanager.shop.model.security.ReadablePermission;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import io.swagger.annotations.ApiOperation;


/**
 * Api for managing security
 * 
 * @author carlsamson
 *
 */
@RestController
@RequestMapping(value = "/api/v1/sec")
public class SecurityApi {

  @Inject
  private StoreFacade storeFacade;

  @Inject
  private PermissionService permissionService;

  @Inject
  private GroupService groupService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping({"/private/{group}/permissions"})
  @ApiOperation(httpMethod = "GET", value = "Get permissions by group", notes = "",
      produces = MediaType.APPLICATION_JSON_VALUE, response = List.class)
  public List<ReadablePermission> listPermissions(@PathVariable String group,
      @RequestParam(name = "store", defaultValue = DEFAULT_STORE,
          required = false) String storeCode,
      HttpServletRequest request) {

    String storeCd = storeCode;

    MerchantStore merchantStore = storeFacade.get(storeCd);
    return null;
    // return userFacade.findByUserName(name, language);
  }


  /**
   * Permissions Requires service user authentication
   * 
   * @return
   */
  @GetMapping("/private/permissions")
  public List<ReadablePermission> permissions() {
    List<Permission> permissions = permissionService.list();
    List<ReadablePermission> readablePermissions = new ArrayList<ReadablePermission>();
    for (Permission permission : permissions) {
      ReadablePermission readablePermission = new ReadablePermission();
      readablePermission.setName(permission.getPermissionName());
      readablePermissions.add(readablePermission);
    }
    return readablePermissions;
  }

  /**
   * Load groups Requires service user authentication
   * 
   * @return
   */
  @GetMapping("/private/groups")
  public List<ReadableGroup> groups() {
    List<Group> groups = groupService.list();
    List<ReadableGroup> readableGroups = new ArrayList<ReadableGroup>();
    for (Group group : groups) {
      ReadableGroup readableGroup = new ReadableGroup();
      readableGroup.setName(group.getGroupName());
      readableGroup.setId(group.getId().longValue());
      readableGroups.add(readableGroup);
    }
    return readableGroups;
  }

}
