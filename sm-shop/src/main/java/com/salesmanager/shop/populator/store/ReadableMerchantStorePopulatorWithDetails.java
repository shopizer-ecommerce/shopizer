package com.salesmanager.shop.populator.store;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.store.ReadableMerchantStore;
import com.salesmanager.shop.utils.ImageFilePath;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Populates MerchantStore core entity model object with more complete details than the traditional ReadableMerchantStorePopulator
 * @author rui pereira
 *
 */
public class ReadableMerchantStorePopulatorWithDetails extends
		ReadableMerchantStorePopulator {

	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public ReadableMerchantStore populate(MerchantStore source,
			ReadableMerchantStore target, MerchantStore store, Language language)
			throws ConversionException {

		target = super.populate(source, target, store, language);

		target.setTemplate(source.getStoreTemplate());

		// TODO Add more as needed

		return target;
	}

	@Override
	protected ReadableMerchantStore createTarget() {
		// TODO Auto-generated method stub
		return null;
	}

}
