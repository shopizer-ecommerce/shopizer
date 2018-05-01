package com.salesmanager.admin.components.references;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class MenuLoader {
	
    @Value("classpath:menu/menu.json")
    private Resource resource;
    
    public void loadMenu() {
    	
        try{
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
          	
            String line;
            while ((line = br.readLine()) != null) {
       	       //System.out.println(line);
            } 
            br.close();
          	
      	}catch(IOException e){
      		e.printStackTrace();
      	}
      	
      }


}
