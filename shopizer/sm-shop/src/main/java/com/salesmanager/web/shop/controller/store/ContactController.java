package com.salesmanager.web.shop.controller.store;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.content.model.Content;
import com.salesmanager.core.business.content.model.ContentDescription;
import com.salesmanager.core.business.content.service.ContentService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.utils.CoreConfiguration;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.entity.shop.ContactForm;
import com.salesmanager.web.entity.shop.PageInformation;
import com.salesmanager.web.shop.controller.AbstractController;
import com.salesmanager.web.shop.controller.ControllerConstants;
import com.salesmanager.web.utils.EmailTemplatesUtils;
import com.salesmanager.web.utils.LabelUtils;
import com.salesmanager.web.utils.LocaleUtils;

@Controller
public class ContactController extends AbstractController {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ContactController.class);
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private CoreConfiguration coreConfiguration;
	
	@Autowired
	private LabelUtils messages;
	
	@Autowired
	private EmailTemplatesUtils emailTemplatesUtils;
	
	private final static String CONTACT_LINK = "CONTACT";
	
	
	@RequestMapping("/shop/store/contactus.html")
	public String displayContact(Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
		
		request.setAttribute(Constants.LINK_CODE, CONTACT_LINK);

		Language language = (Language)request.getAttribute("LANGUAGE");
		
		ContactForm contact = new ContactForm();
		model.addAttribute("contact", contact);
		
		model.addAttribute( "recapatcha_public_key", coreConfiguration.getProperty( Constants.RECAPATCHA_PUBLIC_KEY ) );
		
		Content content = contentService.getByCode(Constants.CONTENT_CONTACT_US, store, language);
		ContentDescription contentDescription = null;
		if(content!=null && content.isVisible()) {
			contentDescription = content.getDescription();
		}
		
		if(contentDescription!=null) {

			//meta information
			PageInformation pageInformation = new PageInformation();
			pageInformation.setPageDescription(contentDescription.getMetatagDescription());
			pageInformation.setPageKeywords(contentDescription.getMetatagKeywords());
			pageInformation.setPageTitle(contentDescription.getTitle());
			pageInformation.setPageUrl(contentDescription.getName());
			
			request.setAttribute(Constants.REQUEST_PAGE_INFORMATION, pageInformation);
			
			model.addAttribute("content",contentDescription);

		} 

		
		
		/** template **/
		StringBuilder template = new StringBuilder().append(ControllerConstants.Tiles.Content.contactus).append(".").append(store.getStoreTemplate());
		return template.toString();
		
		
	}

	
	@RequestMapping(value={"/shop/store/{storeCode}/contact"}, method=RequestMethod.POST)
	public @ResponseBody String sendEmail(@ModelAttribute(value="contact") ContactForm contact, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		AjaxResponse ajaxResponse = new AjaxResponse();
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);

		try {
			
			if ( StringUtils.isBlank( contact.getCaptchaResponseField() )) {
    			FieldError error = new FieldError("captchaResponseField","captchaResponseField",messages.getMessage("NotEmpty.contact.captchaResponseField", locale));
    			bindingResult.addError(error);
	            ajaxResponse.setErrorString(bindingResult.getAllErrors().get(0).getDefaultMessage());
	            ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
	            return ajaxResponse.toJSONString();
			}

	        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
	        reCaptcha.setPublicKey( coreConfiguration.getProperty( Constants.RECAPATCHA_PUBLIC_KEY ) );
	        reCaptcha.setPrivateKey( coreConfiguration.getProperty( Constants.RECAPATCHA_PRIVATE_KEY ) );
	        
	        if ( StringUtils.isNotBlank( contact.getCaptchaChallengeField() )
	                && StringUtils.isNotBlank( contact.getCaptchaResponseField() ) )
	            {
	                ReCaptchaResponse reCaptchaResponse =
	                    reCaptcha.checkAnswer( request.getRemoteAddr(), contact.getCaptchaChallengeField(),
	                                           contact.getCaptchaResponseField() );
	                if ( !reCaptchaResponse.isValid() )
	                {
	                    LOGGER.debug( "Captcha response does not matched" );
	        			FieldError error = new FieldError("captchaChallengeField","captchaChallengeField",messages.getMessage("validaion.recaptcha.not.matched", locale));
	        			bindingResult.addError(error);
	                }

	            }
	        
	        if ( bindingResult.hasErrors() )
	        {
	            LOGGER.debug( "found {} validation error while validating in customer registration ",
	                         bindingResult.getErrorCount() );
	            ajaxResponse.setErrorString(bindingResult.getAllErrors().get(0).getDefaultMessage());
	            ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
	            return ajaxResponse.toJSONString();

	        }
	        
	        emailTemplatesUtils.sendContactEmail(contact, store, LocaleUtils.getLocale(store.getDefaultLanguage()), request.getContextPath());
			
			ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
		} catch(Exception e) {
			LOGGER.error("An error occured while trying to send an email",e);
			ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		return ajaxResponse.toJSONString();
		
		
	}
	
	
}
