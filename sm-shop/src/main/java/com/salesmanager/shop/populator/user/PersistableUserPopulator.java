package com.salesmanager.shop.populator.user;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.User;
import com.salesmanager.shop.model.security.PersistableGroup;
import com.salesmanager.shop.model.user.PersistableUser;


@Component
public class PersistableUserPopulator extends AbstractDataPopulator<PersistableUser, User> {

  @Inject
  private LanguageService languageService;
  
  @Inject
  private GroupService groupService;
  
  @Inject
  private MerchantStoreService merchantStoreService;
  
  @Inject
  @Named("passwordEncoder")
  private PasswordEncoder passwordEncoder;
  
  @Override
  public User populate(PersistableUser source, User target, MerchantStore store, Language language)
      throws ConversionException {
    Validate.notNull(source, "PersistableUser cannot be null");
    Validate.notNull(store, "MerchantStore cannot be null");

    if (target == null) {
      target = new User();
    }

    target.setFirstName(source.getFirstName());
    target.setLastName(source.getLastName());
    target.setAdminEmail(source.getEmailAddress());
    target.setAdminName(source.getUserName());
    if(!StringUtils.isBlank(source.getPassword())) {
      target.setAdminPassword(passwordEncoder.encode(source.getPassword()));
    }
    
    if(!StringUtils.isBlank(source.getStore())) {
        try {
			MerchantStore userStore = merchantStoreService.getByCode(source.getStore());
			target.setMerchantStore(userStore);
		} catch (ServiceException e) {
			throw new ConversionException("Error while reading MerchantStore store [" + source.getStore() + "]",e);
		}
    } else {
    	target.setMerchantStore(store);
    }
    
    
    target.setActive(source.isActive());
    
    Language lang = null;
    try {
      lang = languageService.getByCode(source.getDefaultLanguage());
    } catch(Exception e) {
      throw new ConversionException("Cannot get language [" + source.getDefaultLanguage() + "]",e);
    }

    // set default language
    target.setDefaultLanguage(lang);

    List<Group> userGroups = new ArrayList<Group>();
    List<String> names = new ArrayList<String>();
    for (PersistableGroup group : source.getGroups()) {
      names.add(group.getName());
    }
    try {
      List<Group> groups = groupService.listGroupByNames(names);
      for(Group g: groups) {
        userGroups.add(g);
      }
    } catch (Exception e1) {
      throw new ConversionException("Error while getting user groups",e1);
    }
    
    target.setGroups(userGroups);

    return target;
  }

  @Override
  protected User createTarget() {
    // TODO Auto-generated method stub
    return null;
  }

}
