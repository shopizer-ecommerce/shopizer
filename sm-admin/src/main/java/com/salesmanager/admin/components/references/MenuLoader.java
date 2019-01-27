package com.salesmanager.admin.components.references;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.admin.model.web.Menu;
import com.salesmanager.admin.model.web.MenuType;

@Component
public class MenuLoader {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuLoader.class);
	
    @Value("classpath:menu/menu.json")
    private Resource resource;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Menu> loadMenu() throws Exception {
    	
        try{
            InputStream is = resource.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
          	
            Map<String,Object> data = mapper.readValue(is, Map.class);
			
			List<Menu> menus = new ArrayList<Menu>();
			List objects = (List)data.get("menus");
			for(Object object : objects) {
				Menu m = getMenu(object);
				menus.add(m);
			}
			
			return menus;

          	
      	}catch(IOException e){
      		logger.error("Error while loading menu " + e.getMessage());
      		throw e;
      	}
      	
      }
    
	private Menu getMenu(Object object) {
		
		Map o = (Map)object;
		Map menu = (Map)o.get("menu");
		
		Menu m = new Menu();
		m.setCode((String)menu.get("code"));
		m.setMenuType(MenuType.MAINMENU.name());
		if(menu.get("menuType") != null) {
		  m.setMenuType(MenuType.valueOf((String)menu.get("menuType")).name());
		}
		
		
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
