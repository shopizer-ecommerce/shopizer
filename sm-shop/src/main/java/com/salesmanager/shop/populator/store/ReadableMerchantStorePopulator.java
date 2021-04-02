package com.salesmanager.shop.populator.store;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.shop.model.content.ReadableImage;
import com.salesmanager.shop.model.entity.ReadableAudit;
import com.salesmanager.shop.model.references.MeasureUnit;
import com.salesmanager.shop.model.references.ReadableAddress;
import com.salesmanager.shop.model.references.ReadableLanguage;
import com.salesmanager.shop.model.references.WeightUnit;
import com.salesmanager.shop.model.store.ReadableMerchantStore;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.ImageFilePath;

/**
 * Populates MerchantStore core entity model object
 * @author carlsamson
 *
 */
@Component
public class ReadableMerchantStorePopulator extends
		AbstractDataPopulator<MerchantStore, ReadableMerchantStore> {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private CountryService countryService;
	@Autowired
	private ZoneService zoneService;
	@Autowired
	@Qualifier("img")
	private ImageFilePath filePath;
	@Autowired
	private LanguageService languageService;



	@Override
	public ReadableMerchantStore populate(MerchantStore source,
			ReadableMerchantStore target, MerchantStore store, Language language)
			throws ConversionException {
		Validate.notNull(countryService,"Must use setter for countryService");
		Validate.notNull(zoneService,"Must use setter for zoneService");
		
		if(target == null) {
			target = new ReadableMerchantStore();
		}
		
		target.setId(source.getId());
		target.setCode(source.getCode());
		if(source.getDefaultLanguage() != null) {
			target.setDefaultLanguage(source.getDefaultLanguage().getCode());
		}

		target.setCurrency(source.getCurrency().getCode());
		target.setPhone(source.getStorephone());
		
		ReadableAddress address = new ReadableAddress();
		address.setAddress(source.getStoreaddress());
		address.setCity(source.getStorecity());
		if(source.getCountry()!=null) {
			try {
				address.setCountry(source.getCountry().getIsoCode());
				Country c =countryService.getCountriesMap(language).get(source.getCountry().getIsoCode());
				if(c!=null) {
					address.setCountry(c.getIsoCode());
				}
			} catch (ServiceException e) {
				logger.error("Cannot get Country", e);
			}
		}
		
		if(source.getParent() != null) {
		  ReadableMerchantStore parent = populate(source.getParent(),
            new ReadableMerchantStore(), source, language);
		  target.setParent(parent);
		}
		
		if(target.getParent() == null) {
			target.setRetailer(true);
		} else {
			target.setRetailer(source.isRetailer()!=null?source.isRetailer().booleanValue():false);	
		}
		
		
		target.setDimension(MeasureUnit.valueOf(source.getSeizeunitcode()));
		target.setWeight(WeightUnit.valueOf(source.getWeightunitcode()));
		
		if(source.getZone()!=null) {
			address.setStateProvince(source.getZone().getCode());
			try {
				Zone z = zoneService.getZones(language).get(source.getZone().getCode());
				address.setStateProvince(z.getCode());
			} catch (ServiceException e) {
				logger.error("Cannot get Zone", e);
			}
		}
		
		
		if(!StringUtils.isBlank(source.getStorestateprovince())) {
			address.setStateProvince(source.getStorestateprovince());
		}
		
		if(!StringUtils.isBlank(source.getStoreLogo())) {
			ReadableImage image = new ReadableImage();
			image.setName(source.getStoreLogo());
			if(filePath!=null) {
				image.setPath(filePath.buildStoreLogoFilePath(source));
			}
			target.setLogo(image);
		}
		
		address.setPostalCode(source.getStorepostalcode());

		target.setAddress(address);
		
		target.setCurrencyFormatNational(source.isCurrencyFormatNational());
		target.setEmail(source.getStoreEmailAddress());
		target.setName(source.getStorename());
		target.setId(source.getId());
		target.setInBusinessSince(DateUtil.formatDate(source.getInBusinessSince()));
		target.setUseCache(source.isUseCache());

		if(!CollectionUtils.isEmpty(source.getLanguages())) {
			List<ReadableLanguage> supported = new ArrayList<ReadableLanguage>();
			for(Language lang : source.getLanguages()) {
				try {
					Language langObject = languageService.getLanguagesMap().get(lang.getCode());
					if(langObject != null) {
						ReadableLanguage l = new ReadableLanguage();
						l.setId(langObject.getId());
						l.setCode(langObject.getCode());
						supported.add(l);
					}
					
				} catch (ServiceException e) {
					logger.error("Cannot get Language [" + lang.getId() + "]");
				}
				
			}
			target.setSupportedLanguages(supported);
		}
		
		if(source.getAuditSection()!=null) {
			ReadableAudit audit = new ReadableAudit();
			if(source.getAuditSection().getDateCreated()!=null) {
				audit.setCreated(DateUtil.formatDate(source.getAuditSection().getDateCreated()));
			}
			if(source.getAuditSection().getDateModified()!=null) {
				audit.setModified(DateUtil.formatDate(source.getAuditSection().getDateCreated()));
			}
			audit.setUser(source.getAuditSection().getModifiedBy());
			target.setReadableAudit(audit);
		}

		return target;
	}

	@Override
	protected ReadableMerchantStore createTarget() {
		// TODO Auto-generated method stub
		return null;
	}


}
