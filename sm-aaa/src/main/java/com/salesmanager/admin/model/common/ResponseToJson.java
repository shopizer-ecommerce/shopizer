package com.salesmanager.admin.model.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseToJson {

	public static String toObject(String message) throws Exception {
		return createObject(null,null,message);
	}
	
	public static String toObject(String message, String code) throws Exception {
		return createObject(null,code,message);
	}
	
	public static String toObject(Object o) throws Exception {
		return createObject(o,null,null);
	}
	
	public static String toObject(Object o, String message) throws Exception {
		return createObject(o,null,message);
	}

	public static String toObject(Object o, String message, String code) throws Exception {
		return createObject(o,code,message);
	}
	
	@SuppressWarnings("unchecked")
	private static String createObject(Object o, String code, String message) throws JsonProcessingException {
		@SuppressWarnings("rawtypes")
		ModelResponse m = new ModelResponse();
		m.setMessage(message);
		m.setStatus(code);
		m.setValue(o);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(m);
	}

}
