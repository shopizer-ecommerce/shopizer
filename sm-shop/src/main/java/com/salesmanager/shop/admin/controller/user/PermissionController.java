package com.salesmanager.shop.admin.controller.user;

import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.services.user.PermissionService;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.user.Permission;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.utils.LabelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PermissionController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PermissionController.class);

	@Inject
	protected PermissionService permissionService;

	@Inject
	protected GroupService groupService;

	@Inject
	CountryService countryService;

	@Inject
	LabelUtils messages;






	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/admin/permissions/permissions.html", method = RequestMethod.GET)
	public String displayPermissions(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//setMenu(model, request);
		//return "admin-user-permissions";
		
		throw new Exception("Not implemented");
	}

	@SuppressWarnings("unchecked")
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/admin/permissions/paging.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<String> pagePermissions(HttpServletRequest request,
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
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
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
