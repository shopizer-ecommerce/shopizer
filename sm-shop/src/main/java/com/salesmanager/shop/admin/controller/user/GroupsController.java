package com.salesmanager.shop.admin.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.services.user.PermissionService;
import com.salesmanager.core.business.utils.ajax.AjaxPageableResponse;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.GroupType;
import com.salesmanager.shop.admin.model.permission.GroupDetails;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.utils.LabelUtils;

@Controller
public class GroupsController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GroupsController.class);

	@Inject
	LanguageService languageService;

	@Inject
	protected GroupService groupService;
	
	@Inject
	PermissionService permissionService;

	@Inject
	CountryService countryService;

	@Inject
	LabelUtils messages;



	@PreAuthorize("hasRole('STORE_ADMIN')")
	@RequestMapping(value = "/admin/groups/editGroup.html", method = RequestMethod.GET)
	public String displayGroup(@RequestParam("id") Integer groupId, Model model,
			Locale locale, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// display menu
		setMenu(model, request);
		
		GroupType[] groupTypes = GroupType.values();
		List<String> groups = new ArrayList<String>();

		for(GroupType t : groupTypes) {
			if(GroupType.ADMIN.name() != t.name()) {
				groups.add(t.name());
			}
		}

		Group group = groupService.getById(groupId);
		
		if(group == null) {
			return "redirect://admin/groups/groups.html";
			 
		} 

		GroupDetails groupDetails = new GroupDetails();
		groupDetails.setGroup(group);
		groupDetails.setTypes(groups);
		

		model.addAttribute("group", groupDetails);

		return "admin-user-group";
	}
	
	@PreAuthorize("hasRole('STORE_ADMIN')")
	@RequestMapping(value = "/admin/groups/delete.html", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> delete(Model model,
			Locale locale, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// display menu
		
		String sGroupId = request.getParameter("groupId");

		AjaxResponse resp = new AjaxResponse();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		

		try {
			
			Integer groupId = Integer.parseInt(sGroupId);
			Group group = groupService.getById(groupId);


			if(group==null){
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			if(!request.isUserInRole(Constants.GROUP_ADMIN)) {
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			if(GroupType.ADMIN.name().equals(group.getGroupType().name())) {
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}

			groupService.delete(group);
			
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting group", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
		
		
	}
	
	@PreAuthorize("hasRole('STORE_ADMIN')")
	@RequestMapping(value = "/admin/group/save.html", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("group") GroupDetails group, BindingResult result, Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// display menu
		setMenu(model, request);
		
		GroupType[] groupTypes = GroupType.values();
		List<String> groups = new ArrayList<String>();

		for(GroupType t : groupTypes) {
			if(GroupType.ADMIN.name() != t.name()) {
				groups.add(t.name());
			}
		}

		//check if already exists
		Group g = null;
		if(group.getGroup().getId()!=null) {
			Group gid = groupService.getById(group.getGroup().getId());
			if(gid != null && !gid.getGroupName().equals(group.getGroup().getGroupName())) {
				g = groupService.findByName(group.getGroup().getGroupName());
			}
		} else {
			 g = groupService.findByName(group.getGroup().getGroupName());
		}
		
		if(g != null) {
			 ObjectError error = new ObjectError("group.groupName", messages.getMessage("message.name.exist", locale));
			 result.addError(error);
			 model.addAttribute("error","error");
		} else {
			groupService.save(group.getGroup());
			model.addAttribute("success","success");
		}
		
		GroupDetails groupDetails = new GroupDetails();
		groupDetails.setGroup(group.getGroup());
		groupDetails.setTypes(groups);
		

		model.addAttribute("group", groupDetails);
		
		return "admin-user-group";
	}
	
	@PreAuthorize("hasRole('STORE_ADMIN')")
	@RequestMapping(value = "/admin/groups/new.html", method = RequestMethod.GET)
	public String displayGroup(Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// display menu
		setMenu(model, request);
		
		GroupType[] groupTypes = GroupType.values();
		List<String> groups = new ArrayList<String>();

		for(GroupType t : groupTypes) {
			if(GroupType.ADMIN.name() != t.name()) {
				groups.add(t.name());
			}
		}
		GroupDetails group = new GroupDetails();
		Group g = new Group();
		group.setGroup(g);
		group.setTypes(groups);
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
	@RequestMapping(value = "/admin/groups/paging.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<String> pageGroups(HttpServletRequest request,
			HttpServletResponse response, Locale locale) {

		AjaxResponse resp = new AjaxResponse();
		try {

				List<Group> groups = groupService.list();

				for(Group group : groups) {
					Map entry = new HashMap();
					entry.put("groupId", group.getId());
					entry.put("name", group.getGroupName());
					entry.put("type", group.getGroupType().name());

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
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
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
