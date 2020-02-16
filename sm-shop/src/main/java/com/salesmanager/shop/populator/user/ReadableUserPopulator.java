package com.salesmanager.shop.populator.user;

import org.apache.commons.lang.Validate;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.User;
import com.salesmanager.shop.model.security.ReadableGroup;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.utils.DateUtil;

/**
 * Converts user model to readable user
 * 
 * @author carlsamson
 *
 */
public class ReadableUserPopulator extends AbstractDataPopulator<User, ReadableUser> {

  @Override
  public ReadableUser populate(User source, ReadableUser target, MerchantStore store,
      Language language) throws ConversionException {
    Validate.notNull(source, "User cannot be null");

    if (target == null) {
      target = new ReadableUser();
    }

    target.setFirstName(source.getFirstName());
    target.setLastName(source.getLastName());
    target.setEmailAddress(source.getAdminEmail());
    target.setUserName(source.getAdminName());
    target.setActive(source.isActive());

    if (source.getLastAccess() != null) {
      target.setLastAccess(DateUtil.formatLongDate(source.getLastAccess()));
    }

    // set default language
    target.setDefaultLanguage(Constants.DEFAULT_LANGUAGE);

    if (source.getDefaultLanguage() != null)
      target.setDefaultLanguage(source.getDefaultLanguage().getCode());
    target.setMerchant(store.getCode());
    target.setId(source.getId());


    for (Group group : source.getGroups()) {

      ReadableGroup g = new ReadableGroup();
      g.setName(group.getGroupName());
      g.setId(group.getId().longValue());
      target.getGroups().add(g);
    }

    /**
     * dates DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
     * myObjectMapper.setDateFormat(df);
     */


    return target;
  }

  @Override
  protected ReadableUser createTarget() {
    // TODO Auto-generated method stub
    return null;
  }

}
