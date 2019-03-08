package com.salesmanager.admin.controller.user;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
  
  /**
   * Display current user
   * @param code
   * @param principal
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @RequestMapping("/admin/user")
  @Secured({"AUTH"})
  public String display(@RequestParam(value = "userName", required=false) String userName, Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      request.setAttribute("action", "READ");
      if(StringUtils.isBlank(userName)) {
        userName = principal.getName();
      }
      request.setAttribute("userName", userName);
      return "user/user";
  }
  
/*  @RequestMapping("/admin/user/create")
  @Secured({"ROLE_STORE"})
  public String create(Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      request.setAttribute("action", "CREATE");
      request.setAttribute("addressApiKey", addressAutocompleteApiKey);
      return "store/store";
  }*/
  
/*  @RequestMapping("/admin/user/list")
  @Secured({"ROLE_STORE"})
  public String list(Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      return "store/list";
  }*/

}
