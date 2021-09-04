package com.salesmanager.shop.application.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.utils.LanguageUtils;

@Component
public class LanguageArgumentResolver implements HandlerMethodArgumentResolver {

  @Autowired
  private LanguageUtils languageUtils;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().equals(Language.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

    HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

    return languageUtils.getRESTLanguage(request);
  }

}
