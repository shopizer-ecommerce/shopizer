package com.salesmanager.shop.application.config;

import static com.salesmanager.core.business.constants.Constants.DEFAULT_STORE;
import static org.apache.commons.lang.StringUtils.isBlank;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class MerchantStoreArgumentResolver implements HandlerMethodArgumentResolver {

  @Autowired private StoreFacade storeFacade;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().equals(MerchantStore.class);
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory)
      throws Exception {
    String store = webRequest.getParameter("store");
    String storeValue = isBlank(store) ? DEFAULT_STORE : store;
    return storeFacade.get(storeValue);
  }
}
