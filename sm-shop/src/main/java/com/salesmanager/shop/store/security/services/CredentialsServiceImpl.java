package com.salesmanager.shop.store.security.services;

import org.apache.commons.lang3.StringUtils;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

/**
 * Internal credentials service
 * @author carlsamson
 *
 */
public class CredentialsServiceImpl implements CredentialsService {

	@Override
	public void validateCredentials(String password, String repeatPassword, MerchantStore store, Language language)
			throws CredentialsException {
		
		if(StringUtils.isBlank(password) || StringUtils.isBlank(repeatPassword) ) {
			throw new CredentialsException("Empty password not supported");
		}
		
		/**
		 * validate - both password match
		 */
		if(!password.equals(repeatPassword)) {
			throw new CredentialsException("Password don't match");
		}
		
		//create your own rules
		PasswordValidator passwordValidator = new PasswordValidator(
				  new CharacterRule(EnglishCharacterData.Digit, 1),//at least 1 digit
				  new CharacterRule(EnglishCharacterData.Special, 1),// at least 1 special character
				  new LengthRule(8, 12)//8 to 12 characters
		);
		
	      PasswordData passwordData = new PasswordData(password);        
	      RuleResult result = passwordValidator.validate(passwordData);
	        
	      if(!result.isValid()){
	         throw new CredentialsException("Password validation failed [" + passwordValidator.getMessages(result) + "]");
	      }

	}

	@Override
	public String generatePassword(MerchantStore store, Language language) throws CredentialsException {
		// TODO Auto-generated method stub
		return null;
	}

}
