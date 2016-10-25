package com.shopizer.search.utils;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class DynamicIndexNameUtil {
	
	@SuppressWarnings("rawtypes")
	public static String getIndexName(String name,Map indexData) {
		

			if(name.startsWith("%") && name.endsWith("%")) {
				
				String containedField = name.substring(1,name.length()-1);
				
				String f = (String)indexData.get(containedField);
				if(StringUtils.isBlank(f)) {
	
					return name;
				}
				return f;
			}

		
			return name;
		
	}

}
