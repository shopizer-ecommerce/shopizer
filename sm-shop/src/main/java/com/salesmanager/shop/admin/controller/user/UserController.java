package com.salesmanager.shop.admin.controller.user;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.email.Email;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.system.EmailService;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.services.user.UserService;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.GroupType;
import com.salesmanager.core.model.user.User;
import com.salesmanager.shop.admin.controller.ControllerConstants;
import com.salesmanager.shop.admin.model.secutity.Password;
import com.salesmanager.shop.admin.model.userpassword.UserReset;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.admin.security.SecurityQuestion;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.constants.EmailConstants;
import com.salesmanager.shop.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private LanguageService languageService;
	
	@Inject
	private UserService userService;

	@Inject
	private GroupService groupService;
	
	@Inject
	private CountryService countryService;
	
	@Inject
	private EmailService emailService;
	
	@Inject
	private MerchantStoreService merchantStoreService;
	
	@Inject
	LabelUtils messages;
	
	@Inject
	private FilePathUtils filePathUtils;
	
	@Inject
	private EmailUtils emailUtils;
	
	@Inject
	@Named("passwordEncoder")
	private PasswordEncoder passwordEncoder;
	
	private final static String QUESTION_1 = "question1";
	private final static String QUESTION_2 = "question2";
	private final static String QUESTION_3 = "question3";
	private final static String RESET_PASSWORD_TPL = "email_template_password_reset_user.ftl";	
	private final static String NEW_USER_TMPL = "email_template_new_user.ftl";
	
	@PreAuthorize("hasRole('STORE_ADMIN')")
	@RequestMapping(value="/admin/users/list.html", method=RequestMethod.GET)
	public String displayUsers(Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		//The users are retrieved from the paging method
		setMenu(model,request);
		return ControllerConstants.Tiles.User.users;
	}
	
	/**
	 * Displays a list of users that can be managed by admins
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PreAuthorize("hasRole('STORE_ADMIN')")
	@RequestMapping(value = "/admin/users/paging.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<String> pageUsers(HttpServletRequest request,
			HttpServletResponse response) {

		AjaxResponse resp = new AjaxResponse();
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		String sCurrentUser = request.getRemoteUser();
		
		
		try {

			User currentUser = userService.getByUserName(sCurrentUser);
			List<User> users = null;
			if(UserUtils.userInGroup(currentUser, Constants.GROUP_SUPERADMIN) ) {
				users = userService.listUser();
			} else {
				users = userService.listByStore(store);
			}
			 

			for (User user : users) {
				
				if(!UserUtils.userInGroup(user, Constants.GROUP_SUPERADMIN)) {
					
					if(!currentUser.equals(user.getAdminName())){

						@SuppressWarnings("rawtypes")
						Map entry = new HashMap();
						entry.put("userId", user.getId());
						entry.put("name", user.getFirstName() + " " + user.getLastName());
						entry.put("email", user.getAdminEmail());
						entry.put("active", user.isActive());
						resp.addDataEntry(entry);
					
					}
				}
			}

			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);

		} catch (Exception e) {
			LOGGER.error("Error while paging products", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}

		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,HttpStatus.OK);
	}

	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/users/password.html", method=RequestMethod.GET)
	public String displayChangePassword(Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		setMenu(model,request);
		String userName = request.getRemoteUser();
		User user = userService.getByUserName(userName);
		
		Password password = new Password();
		password.setUser(user);
		
		model.addAttribute("password",password);
		model.addAttribute("user",user);
		return ControllerConstants.Tiles.User.password;
	}
	
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/users/savePassword.html", method=RequestMethod.POST)
	public String changePassword(@ModelAttribute("password") Password password, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		setMenu(model,request);
		String userName = request.getRemoteUser();
		User dbUser = userService.getByUserName(userName);
		

		if(password.getUser().getId().longValue()!= dbUser.getId().longValue()) {
				return "redirect:/admin/users/displayUser.html";
		}
		
		//validate password not empty
		if(StringUtils.isBlank(password.getPassword())) {
			ObjectError error = new ObjectError("password",new StringBuilder().append(messages.getMessage("label.generic.password", locale)).append(" ").append(messages.getMessage("message.cannot.empty", locale)).toString());
			result.addError(error);
			return ControllerConstants.Tiles.User.password;
		}

		if(!passwordEncoder.matches(password.getPassword(), dbUser.getAdminPassword())) {
			ObjectError error = new ObjectError("password",messages.getMessage("message.password.invalid", locale));
			result.addError(error);
			return ControllerConstants.Tiles.User.password;
		}
		

		if(StringUtils.isBlank(password.getNewPassword())) {
			ObjectError error = new ObjectError("newPassword",new StringBuilder().append(messages.getMessage("label.generic.newpassword", locale)).append(" ").append(messages.getMessage("message.cannot.empty", locale)).toString());
			result.addError(error);
		}
		
		if(StringUtils.isBlank(password.getRepeatPassword())) {
			ObjectError error = new ObjectError("newPasswordAgain",new StringBuilder().append(messages.getMessage("label.generic.newpassword.repeat", locale)).append(" ").append(messages.getMessage("message.cannot.empty", locale)).toString());
			result.addError(error);
		}
		
		if(!password.getRepeatPassword().equals(password.getNewPassword())) {
			ObjectError error = new ObjectError("newPasswordAgain",messages.getMessage("message.password.different", locale));
			result.addError(error);
		}
		
		if(password.getNewPassword().length()<6) {
			ObjectError error = new ObjectError("newPassword",messages.getMessage("message.password.length", locale));
			result.addError(error);
		}
		
		if (result.hasErrors()) {
			return ControllerConstants.Tiles.User.password;
		}
		
		
		
		String pass = passwordEncoder.encode(password.getNewPassword());
		dbUser.setAdminPassword(pass);
		userService.update(dbUser);
		
		model.addAttribute("success","success");
		return ControllerConstants.Tiles.User.password;
	}
	
	@PreAuthorize("hasRole('STORE_ADMIN')")
	@RequestMapping(value="/admin/users/createUser.html", method=RequestMethod.GET)
	public String displayUserCreate(Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		return displayUser(null,model,request,response,locale);
	}
	

	/**
	 * From user list
	 * @param id
	 * @param model
	 * @param request
	 * @param response
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/users/displayStoreUser.html", method=RequestMethod.GET)
	public String displayUserEdit(@ModelAttribute("id") Long id, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		User dbUser = userService.getById(id);
		
		if(dbUser==null) {
			LOGGER.info("User is null for id " + id);
			return "redirect://admin/users/list.html";
		}
		
		
		return displayUser(dbUser,model,request,response,locale);

	}
	
	/**
	 * From user profile
	 * @param model
	 * @param request
	 * @param response
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/users/displayUser.html", method=RequestMethod.GET)
	public String displayUserEdit(Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		
		String userName = request.getRemoteUser();
		User user = userService.getByUserName(userName);
		return displayUser(user,model,request,response,locale);

	}
	
	private void populateUserObjects(User user, MerchantStore store, Model model, Locale locale) throws Exception {
		
		//get groups
		List<Group> groups = new ArrayList<Group>();
		List<Group> userGroups = groupService.listGroup(GroupType.ADMIN);
		for(Group group : userGroups) {
			if(!group.getGroupName().equals(Constants.GROUP_SUPERADMIN)) {
				groups.add(group);
			}
		}
		
		
		List<MerchantStore> stores = new ArrayList<MerchantStore>();
		//stores.add(store);
		stores = merchantStoreService.list();
		
		
		//questions
		List<SecurityQuestion> questions = new ArrayList<SecurityQuestion>();
		
		SecurityQuestion question = new SecurityQuestion();
		question.setId("1");
		question.setLabel(messages.getMessage("security.question.1", locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("2");
		question.setLabel(messages.getMessage("security.question.2", locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("3");
		question.setLabel(messages.getMessage("security.question.3", locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("4");
		question.setLabel(messages.getMessage("security.question.4", locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("5");
		question.setLabel(messages.getMessage("security.question.5", locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("6");
		question.setLabel(messages.getMessage("security.question.6", locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("7");
		question.setLabel(messages.getMessage("security.question.7", locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("8");
		question.setLabel(messages.getMessage("security.question.8", locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("9");
		question.setLabel(messages.getMessage("security.question.9", locale));
		questions.add(question);
		
		model.addAttribute("questions", questions);
		model.addAttribute("stores", stores);
		model.addAttribute("languages", store.getLanguages());
		model.addAttribute("groups", groups);
		
		
	}
	
	
	
	private String displayUser(User user, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		

		//display menu
		setMenu(model,request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);



		
		if(user==null) {
			user = new User();
		} else {
			user.setAdminPassword("TRANSIENT");
		}
		
		this.populateUserObjects(user, store, model, locale);
		

		model.addAttribute("user", user);
		
		

		return ControllerConstants.Tiles.User.profile;
	}
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/users/checkUserCode.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> checkUserCode(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		String code = request.getParameter("code");
		String id = request.getParameter("id");

		AjaxResponse resp = new AjaxResponse();
		
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		try {
			
			if(StringUtils.isBlank(code)) {
				resp.setStatus(AjaxResponse.CODE_ALREADY_EXIST);
				String returnString =  resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			User user = userService.getByUserName(code);
		
		
			if(!StringUtils.isBlank(id)&& user!=null) {
				try {
					Long lid = Long.parseLong(id);
					
					if(user.getAdminName().equals(code) && user.getId()==lid) {
						resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
						String returnString =  resp.toJSONString();
						return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
					}
				} catch (Exception e) {
					resp.setStatus(AjaxResponse.CODE_ALREADY_EXIST);
					String returnString =  resp.toJSONString();
					return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
				}
	
			}

			
			if(StringUtils.isBlank(code)) {
				resp.setStatus(AjaxResponse.CODE_ALREADY_EXIST);
				String returnString =  resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}

			if(user!=null) {
				resp.setStatus(AjaxResponse.CODE_ALREADY_EXIST);
				String returnString =  resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			

			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		} catch (Exception e) {
			LOGGER.error("Error while getting user", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);

	}
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/users/save.html", method=RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {


		setMenu(model,request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		
		this.populateUserObjects(user, store, model, locale);
		
		Language language = user.getDefaultLanguage();
		
		Language l = languageService.getById(language.getId());
		
		user.setDefaultLanguage(l);
		
		Locale userLocale = LocaleUtils.getLocale(l);
		
		
		
		User dbUser = null;
		
		//edit mode, need to get original user important information
		if(user.getId()!=null) {
			dbUser = userService.getByUserName(user.getAdminName());
			if(dbUser==null) {
				return "redirect:///admin/users/displayUser.html";
			}
		}

		List<Group> submitedGroups = user.getGroups();
		Set<Integer> ids = new HashSet<Integer>();
		for(Group group : submitedGroups) {
			ids.add(group.getId());
		}
		

		
		//validate security questions not empty
		if(StringUtils.isBlank(user.getAnswer1())) {
			ObjectError error = new ObjectError("answer1",messages.getMessage("security.answer.question1.message", locale));
			result.addError(error);
		}
		
		if(StringUtils.isBlank(user.getAnswer2())) {
			ObjectError error = new ObjectError("answer2",messages.getMessage("security.answer.question2.message", locale));
			result.addError(error);
		}
		
		if(StringUtils.isBlank(user.getAnswer3())) {
			ObjectError error = new ObjectError("answer3",messages.getMessage("security.answer.question3.message", locale));
			result.addError(error);
		}
		
		if(user.getQuestion1().equals(user.getQuestion2()) || user.getQuestion1().equals(user.getQuestion3())
				|| user.getQuestion2().equals(user.getQuestion1()) || user.getQuestion1().equals(user.getQuestion3())
				|| user.getQuestion3().equals(user.getQuestion1()) || user.getQuestion1().equals(user.getQuestion2()))
		
		
		{
			ObjectError error = new ObjectError("question1",messages.getMessage("security.questions.differentmessages", locale));
			result.addError(error);
		}
		
		
		Group superAdmin = null;
		
		if(user.getId()!=null && user.getId()>0) {
			if(user.getId().longValue()!=dbUser.getId().longValue()) {
				return "redirect:///admin/users/displayUser.html";
			}
			
			List<Group> groups = dbUser.getGroups();
			//boolean removeSuperAdmin = true;
			for(Group group : groups) {
				//can't revoke super admin
				if(group.getGroupName().equals("SUPERADMIN")) {
					superAdmin = group;
				}
			}

		} else {
			
			if(user.getAdminPassword().length()<6) {
				ObjectError error = new ObjectError("adminPassword",messages.getMessage("message.password.length", locale));
				result.addError(error);
			}
			
		}
		
		if(superAdmin!=null) {
			ids.add(superAdmin.getId());
		}

		
		List<Group> newGroups = groupService.listGroupByIds(ids);

		//set actual user groups
		user.setGroups(newGroups);
		
		if (result.hasErrors()) {
			return ControllerConstants.Tiles.User.profile;
		}
		
		String decodedPassword = user.getAdminPassword();
		if(user.getId()!=null && user.getId()>0) {
			user.setAdminPassword(dbUser.getAdminPassword());
		} else {
			String encoded = passwordEncoder.encode(user.getAdminPassword());
			user.setAdminPassword(encoded);
		}
		
		
		if(user.getId()==null || user.getId().longValue()==0) {
			
			//save or update user
			userService.saveOrUpdate(user);
			
			try {

				//creation of a user, send an email
				String userName = user.getFirstName();
				if(StringUtils.isBlank(userName)) {
					userName = user.getAdminName();
				}
				String[] userNameArg = {userName};
				
				
				Map<String, String> templateTokens = emailUtils.createEmailObjectsMap(request.getContextPath(), store, messages, userLocale);
				templateTokens.put(EmailConstants.EMAIL_NEW_USER_TEXT, messages.getMessage("email.greeting", userNameArg, userLocale));
				templateTokens.put(EmailConstants.EMAIL_USER_FIRSTNAME, user.getFirstName());
				templateTokens.put(EmailConstants.EMAIL_USER_LASTNAME, user.getLastName());
				templateTokens.put(EmailConstants.EMAIL_ADMIN_USERNAME_LABEL, messages.getMessage("label.generic.username",userLocale));
				templateTokens.put(EmailConstants.EMAIL_ADMIN_NAME, user.getAdminName());
				templateTokens.put(EmailConstants.EMAIL_TEXT_NEW_USER_CREATED, messages.getMessage("email.newuser.text",userLocale));
				templateTokens.put(EmailConstants.EMAIL_ADMIN_PASSWORD_LABEL, messages.getMessage("label.generic.password",userLocale));
				templateTokens.put(EmailConstants.EMAIL_ADMIN_PASSWORD, decodedPassword);
				templateTokens.put(EmailConstants.EMAIL_ADMIN_URL_LABEL, messages.getMessage("label.adminurl",userLocale));
				templateTokens.put(EmailConstants.EMAIL_ADMIN_URL, filePathUtils.buildAdminUri(store, request));
	
				
				Email email = new Email();
				email.setFrom(store.getStorename());
				email.setFromEmail(store.getStoreEmailAddress());
				email.setSubject(messages.getMessage("email.newuser.title",userLocale));
				email.setTo(user.getAdminEmail());
				email.setTemplateName(NEW_USER_TMPL);
				email.setTemplateTokens(templateTokens);
	
	
				
				emailService.sendHtmlEmail(store, email);
			
			} catch (Exception e) {
				LOGGER.error("Cannot send email to user",e);
			}
			
		} else {
			//save or update user
			userService.saveOrUpdate(user);
		}

		model.addAttribute("success","success");
		return ControllerConstants.Tiles.User.profile;
	}
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/users/remove.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> removeUser(HttpServletRequest request, Locale locale) throws Exception {
		
		//do not remove super admin
		
		String sUserId = request.getParameter("userId");

		AjaxResponse resp = new AjaxResponse();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		String userName = request.getRemoteUser();
		User remoteUser = userService.getByUserName(userName);

		
		try {
			
			Long userId = Long.parseLong(sUserId);
			User user = userService.getById(userId);
			
			/**
			 * In order to remove a User the logged in ser must be STORE_ADMIN
			 * or SUPER_USER
			 */
			

			if(user==null){
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

			
			//check if the user removed has group ADMIN
			boolean isAdmin = false;
			if(UserUtils.userInGroup(remoteUser, Constants.GROUP_ADMIN) || UserUtils.userInGroup(remoteUser, Constants.GROUP_SUPERADMIN)) {
				isAdmin = true;
			}

			
			if(!isAdmin) {
				resp.setStatusMessage(messages.getMessage("message.security.caanotremovesuperadmin", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			userService.delete(user);
			
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting user", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
		
	}
	
	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("profile", "profile");
		activeMenus.put("user", "create-user");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("profile");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}
	
	//password reset functionality  ---  Sajid Shajahan  
	@RequestMapping(value="/admin/users/resetPassword.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> resetPassword(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		
		AjaxResponse resp = new AjaxResponse();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
	    
		String userName = request.getParameter("username");
		
		
		
		/**
		 * Get User with userService.getByUserName
		 * Get 3 security questions from User.getQuestion1, user.getQuestion2, user.getQuestion3
		 */
		
		HttpSession session = request.getSession();
		session.setAttribute("username_reset", userName);
		
		try {
				if(!StringUtils.isBlank(userName)){
					
						User dbUser = userService.getByUserName(userName);
						
						if(dbUser==null) {
							resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
							resp.setStatusMessage(messages.getMessage("message.username.notfound", locale));
							String returnString = resp.toJSONString();
							return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
						}
					
						Map<String,String> entry = new HashMap<String,String>();
						entry.put(QUESTION_1, dbUser.getQuestion1());
						entry.put(QUESTION_2, dbUser.getQuestion2());
						entry.put(QUESTION_3, dbUser.getQuestion3());
						resp.addDataEntry(entry);
						resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
				
				}else
				{
						resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
						resp.setStatusMessage(messages.getMessage("User.resetPassword.Error", locale));
				
				}
			} catch (Exception e) {
						e.printStackTrace();
						resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
						resp.setStatusMessage(messages.getMessage("User.resetPassword.Error", locale));
						String returnString = resp.toJSONString();
						return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
	
		
		
		
		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	//password reset functionality  ---  Sajid Shajahan
	@RequestMapping(value="/admin/users/resetPasswordSecurityQtn.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> resetPasswordSecurityQtn(@ModelAttribute(value="userReset") UserReset userReset,HttpServletRequest request, HttpServletResponse response, Locale locale) {
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		Language userLanguage = null; 
		Locale userLocale =  null; 
		AjaxResponse resp = new AjaxResponse();


		String answer1 = request.getParameter("answer1");
		String answer2 = request.getParameter("answer2");
		String answer3 = request.getParameter("answer3");
		
		try {
			
			HttpSession session = request.getSession();
			User dbUser = userService.getByUserName((String) session.getAttribute("username_reset"));
			
			if(dbUser!= null){
				
				if(dbUser.getAnswer1().equals(answer1.trim()) && dbUser.getAnswer2().equals(answer2.trim()) && dbUser.getAnswer3().equals(answer3.trim())){
					userLanguage = dbUser.getDefaultLanguage();	
					userLocale =  LocaleUtils.getLocale(userLanguage);
					
					String tempPass = userReset.generateRandomString();
					String pass = passwordEncoder.encode(tempPass);
					
					dbUser.setAdminPassword(pass);
					userService.update(dbUser);
					
					//send email
					
					try {
						String[] storeEmail = {store.getStoreEmailAddress()};						
						
						Map<String, String> templateTokens = emailUtils.createEmailObjectsMap(request.getContextPath(), store, messages, userLocale);
						templateTokens.put(EmailConstants.EMAIL_RESET_PASSWORD_TXT, messages.getMessage("email.user.resetpassword.text", userLocale));
						templateTokens.put(EmailConstants.EMAIL_CONTACT_OWNER, messages.getMessage("email.contactowner", storeEmail, userLocale));
						templateTokens.put(EmailConstants.EMAIL_PASSWORD_LABEL, messages.getMessage("label.generic.password",userLocale));
						templateTokens.put(EmailConstants.EMAIL_USER_PASSWORD, tempPass);

						Email email = new Email();
						email.setFrom(store.getStorename());
						email.setFromEmail(store.getStoreEmailAddress());
						email.setSubject(messages.getMessage("label.generic.changepassword",userLocale));
						email.setTo(dbUser.getAdminEmail() );
						email.setTemplateName(RESET_PASSWORD_TPL);
						email.setTemplateTokens(templateTokens);
						
						emailService.sendHtmlEmail(store, email);
					
					} catch (Exception e) {
						LOGGER.error("Cannot send email to user",e);
					}
					
					resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
					resp.setStatusMessage(messages.getMessage("User.resetPassword.resetSuccess", locale));
				}
				else{
					  resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
					  resp.setStatusMessage(messages.getMessage("User.resetPassword.wrongSecurityQtn", locale));
					  
				  }
			  }else{
				  resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				  resp.setStatusMessage(messages.getMessage("User.resetPassword.userNotFound", locale));
				  
			  }
			
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setStatusMessage(messages.getMessage("User.resetPassword.Error", locale));
		}
		
		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	
	}
