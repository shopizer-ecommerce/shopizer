package com.salesmanager.shop.admin.controller.user;

import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.GroupType;
import com.salesmanager.shop.admin.model.web.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SecurityController {
	
	@Inject
	GroupService groupService;

	@RequestMapping(value="/admin/user/permissions.html", method=RequestMethod.GET)
	public String displayPermissions(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);
		return "admin-user-permissions";
		
		
	}
	
	
	@RequestMapping(value="/admin/user/groups.html", method=RequestMethod.GET)
	public String displayGroups(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);
		List<Group> groups = groupService.listGroup(GroupType.ADMIN);
		
		model.addAttribute("groups", groups);
		
		return "admin-user-groups";
		
		
	}
	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("profile", "profile");
		activeMenus.put("security", "security");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("profile");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}

}
