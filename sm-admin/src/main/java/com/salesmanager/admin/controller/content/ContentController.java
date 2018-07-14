package com.salesmanager.admin.controller.content;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesmanager.admin.components.content.ContentManager;

@Controller
public class ContentController {
	
	
	@Inject
	private ContentManager contentManager;
	
	@RequestMapping("/admin/content")
	@Secured({"ROLE_CONTENT"})
	public String display(Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return "content/files";
	}
	
	
	/**
	 * Content management actions
	 * @param principal
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/contentManagement/api")
	@Secured({"ROLE_CONTENT"})
	public void api(Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {

		contentManager.handleRequest(request, response);
	}

}
