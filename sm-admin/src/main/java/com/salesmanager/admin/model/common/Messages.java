package com.salesmanager.admin.model.common;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Messages {
	
	
	@Inject
	private MessageSource messageSource;

	public String get(String code, Locale locale) {
		MessageSourceAccessor accessor = new MessageSourceAccessor(messageSource, locale);
	    return accessor.getMessage(code);
	}

}
