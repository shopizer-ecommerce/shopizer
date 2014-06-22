package com.salesmanager.web.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.merchant.service.MerchantStoreService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.user.model.User;
import com.salesmanager.core.business.user.service.UserService;
import com.salesmanager.core.utils.CacheUtils;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;


public class AdminFilter extends HandlerInterceptorAdapter {
	

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminFilter.class);
	
	@Autowired
	private MerchantStoreService merchantService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private CacheUtils cache;
	
	public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		@SuppressWarnings("unchecked")
		Map<String,Menu> menus = (Map<String,Menu>) cache.getFromCache("MENUMAP");
		
		User user = (User)request.getSession().getAttribute(Constants.ADMIN_USER);
		
		

		
		
		
		String storeCode = MerchantStore.DEFAULT_STORE;
		MerchantStore store = (MerchantStore)request.getSession().getAttribute(Constants.ADMIN_STORE);
		
		
		String userName = request.getRemoteUser();
		
		if(userName==null) {//** IMPORTANT FOR SPRING SECURITY **//
			//response.sendRedirect(new StringBuilder().append(request.getContextPath()).append("/").append("/admin").toString());
		} else {
		
			if(user==null) {
				user = userService.getByUserName(userName);
				request.getSession().setAttribute(Constants.ADMIN_USER, user);
				if(user!=null) {
					storeCode = user.getMerchantStore().getCode();
				} else {
					LOGGER.warn("User name not found " + userName);
				}
				store=null;
			}
			
			if(user==null) {
				response.sendRedirect(request.getContextPath() + "/admin/unauthorized.html");
				return true;
			}
			
			if(!user.getAdminName().equals(userName)) {
				user = userService.getByUserName(userName);
				if(user!=null) {
					storeCode = user.getMerchantStore().getCode();
				} else {
					LOGGER.warn("User name not found " + userName);
				}
				store=null;
			}
		
		}
		
		if(store==null) {
				store = merchantService.getByCode(storeCode);
				request.getSession().setAttribute(Constants.ADMIN_STORE, store);
		}
		request.setAttribute(Constants.ADMIN_STORE, store);
		
		
		Language language = (Language) request.getSession().getAttribute("LANGUAGE");
		
		if(language==null) {
			
			//TODO get the Locale from Spring API, is it simply request.getLocale() ???
			//if so then based on the Locale language locale.getLanguage() get the appropriate Language
			//object as represented below
			if(user!=null) {
				language = user.getDefaultLanguage();
				if(language==null) {
					language = store.getDefaultLanguage();
				}
			} else {
				language = store.getDefaultLanguage();
			}
			
			
			
			request.getSession().setAttribute("LANGUAGE", language);
			

		}
		

		request.setAttribute(Constants.LANGUAGE, language);
		

		if(menus==null) {
			InputStream in = null;
			ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
			try {
				in =
					(InputStream) this.getClass().getClassLoader().getResourceAsStream("admin/menu.json");

				Map<String,Object> data = mapper.readValue(in, Map.class);

				Menu currentMenu = null;
				
				menus = new LinkedHashMap<String,Menu>();
				List objects = (List)data.get("menus");
				for(Object object : objects) {
					Menu m = getMenu(object);
					menus.put(m.getCode(),m);
				}

				cache.putInCache(menus,"MENUMAP");

			} catch (JsonParseException e) {
				LOGGER.error("Error while creating menu", e);
			} catch (JsonMappingException e) {
				LOGGER.error("Error while creating menu", e);
			} catch (IOException e) {
				LOGGER.error("Error while creating menu", e);
			} finally {
				if(in !=null) {
					try {
						in.close();
					} catch (Exception ignore) {
						// TODO: handle exception
					}
				}
			}
		
		} 
		
		
		List<Menu> list = new ArrayList<Menu>(menus.values());

		request.setAttribute("MENULIST", list);

		
		
		request.setAttribute("MENUMAP", menus);
		response.setCharacterEncoding("UTF-8");
		
		return true;
	}
	
	
	private Menu getMenu(Object object) {
		
		Map o = (Map)object;
		Map menu = (Map)o.get("menu");
		
		Menu m = new Menu();
		m.setCode((String)menu.get("code"));
		
		
		m.setUrl((String)menu.get("url"));
		m.setIcon((String)menu.get("icon"));
		m.setRole((String)menu.get("role"));
		
		List menus = (List)menu.get("menus");
		if(menus!=null) {
			for(Object oo : menus) {
				
				Menu mm = getMenu(oo);
				m.getMenus().add(mm);
			}
			
		}
		
		return m;
		
	}

}
