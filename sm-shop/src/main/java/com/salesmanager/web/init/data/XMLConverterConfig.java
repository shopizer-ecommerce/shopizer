package com.salesmanager.web.init.data;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.oxm.castor.CastorMarshaller;

import com.salesmanager.core.business.user.model.Permission;


@Configuration
public class XMLConverterConfig {
	
	@Bean
	public XMLConverter getHandler() throws Exception{
	  XMLConverter handler= new XMLConverter();
	  handler.setMarshaller(getCastorMarshaller());
	  handler.setUnmarshaller(getCastorMarshaller());
	  return handler;
	}
	
	
	@Bean
	public CastorMarshaller getCastorMarshaller() throws Exception {
	  CastorMarshaller marshaller = new CastorMarshaller();
	  InputStream inputStream=getClass().getClassLoader().getResourceAsStream("mapping-permissions.xml");
	  marshaller.setMappingLocation(new ByteArrayResource(IOUtils.toByteArray(inputStream)));
	  marshaller.setTargetClass(Permission.class);
	  marshaller.afterPropertiesSet();
	  return marshaller;
		
	}

}

