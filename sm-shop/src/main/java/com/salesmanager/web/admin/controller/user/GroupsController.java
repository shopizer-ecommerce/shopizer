package com.salesmanager.web.admin.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.user.model.Group;
import com.salesmanager.core.business.user.model.GroupType;
import com.salesmanager.core.business.user.service.GroupService;
import com.salesmanager.core.business.user.service.PermissionService;
import com.salesmanager.core.utils.ajax.AjaxPageableResponse;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.utils.LabelUtils;

@Controller
public class GroupsController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GroupsController.class);

	@Autowired
	LanguageService languageService;

	@Autowired
	protected GroupService groupService;
	
	@Autowired
	PermissionService permissionService;

	@Autowired
	CountryService countryService;

	@Autowired
	LabelUtils messages;



	@PreAuthorize("hasRole('STORE_ADMIN')")
	@RequestMapping(value = "/admin/groups/editGroup.html", method = RequestMethod.GET)
	public String displayGroup(@RequestParam("id") Integer groupId, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// display menu
		setMenu(model, request);

		Group group = groupService.getById(groupId);

		model.addAttribute("group", group);

		return "admin-user-group";
	}



	@PreAuthorize("hasRole('STORE_ADMIN')")
	@RequestMapping(value = "/admin/groups/groups.html", method = RequestMethod.GET)
	public String displayGroups(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setMenu(model, request);
		List<Group> groups = groupService.listGroup(GroupType.ADMIN);
		model.addAttribute("groups", groups);

		return "admin-user-groups";
	}

	
	@PreAuthorize("hasRole('STORE_ADMIN')")
	@RequestMapping(value = "/admin/groups/paging.html", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	String pageGroups(HttpServletRequest request,
			HttpServletResponse response, Locale locale) {

		AjaxResponse resp = new AjaxResponse();
		try {

				List<Group> groups = groupService.list();

				for(Group group : groups) {
					Map entry = new HashMap();
					entry.put("groupId", group.getId());
					entry.put("name", group.getGroupName());

					StringBuilder key = new StringBuilder().append("security.group.description.").append(group.getGroupName());
					try {
					
						String message =  messages.getMessage(key.toString(), locale);
						entry.put("description",message);
					
					} catch(Exception noLabelException) {
						LOGGER.error("No label found for key [" + key.toString() + "]");
					}
					
					
					

					resp.addDataEntry(entry);
				}

			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_SUCCESS);
		
		} catch (Exception e) {
			LOGGER.error("Error while paging permissions", e);
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		return returnString;
	}
	

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
