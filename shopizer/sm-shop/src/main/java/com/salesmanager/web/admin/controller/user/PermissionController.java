package com.salesmanager.web.admin.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.user.model.Permission;
import com.salesmanager.core.business.user.service.GroupService;
import com.salesmanager.core.business.user.service.PermissionService;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.utils.LabelUtils;

@Controller
public class PermissionController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PermissionController.class);

	@Autowired
	protected PermissionService permissionService;

	@Autowired
	protected GroupService groupService;

	@Autowired
	CountryService countryService;

	@Autowired
	LabelUtils messages;






	@PreAuthorize("hasRole('STORE_ADMIN')")
	@RequestMapping(value = "/admin/permissions/permissions.html", method = RequestMethod.GET)
	public String displayPermissions(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//setMenu(model, request);
		//return "admin-user-permissions";
		
		throw new Exception("Not implemented");
	}

	@SuppressWarnings("unchecked")
	@PreAuthorize("hasRole('STORE_ADMIN')")
	@RequestMapping(value = "/admin/permissions/paging.html", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	String pagePermissions(HttpServletRequest request,
			HttpServletResponse response) {
		//String permissionName = request.getParameter("name");

		AjaxResponse resp = new AjaxResponse();

		try {

			List<Permission> permissions = null;
			permissions = permissionService.listPermission();

			for (Permission permission : permissions) {

				@SuppressWarnings("rawtypes")
				Map entry = new HashMap();
				entry.put("permissionId", permission.getId());
				entry.put("name", permission.getPermissionName());
				resp.addDataEntry(entry);

			}

			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);

		} catch (Exception e) {
			LOGGER.error("Error while paging permissions", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}

		String returnString = resp.toJSONString();

		return returnString;
	}

	@SuppressWarnings("unused")
	private void setMenu(Model model, HttpServletRequest request)
			throws Exception {

		// display menu
		Map<String, String> activeMenus = new HashMap<String, String>();
		activeMenus.put("profile", "profile");
		activeMenus.put("security", "security");

		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>) request
				.getAttribute("MENUMAP");

		Menu currentMenu = (Menu) menus.get("profile");
		model.addAttribute("currentMenu", currentMenu);
		model.addAttribute("activeMenus", activeMenus);
		//

	}

}
