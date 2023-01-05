package com.salesmanager.shop.mapper.order;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.services.catalog.pricing.PricingService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderTotal;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.order.total.ReadableOrderTotal;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import com.salesmanager.shop.utils.LabelUtils;
import com.salesmanager.shop.utils.LocaleUtils;

@Component
public class ReadableOrderTotalMapper implements Mapper<OrderTotal, ReadableOrderTotal> {

	@Autowired
	private PricingService pricingService;

	@Autowired
	private LabelUtils messages;

	@Override
	public ReadableOrderTotal convert(OrderTotal source, MerchantStore store, Language language) {
		ReadableOrderTotal destination = new ReadableOrderTotal();
		return this.merge(source, destination, store, language);
	}

	@Override
	public ReadableOrderTotal merge(OrderTotal source, ReadableOrderTotal target, MerchantStore store,
			Language language) {

		Validate.notNull(source, "OrderTotal must not be null");
		Validate.notNull(target, "ReadableTotal must not be null");
		Validate.notNull(store, "MerchantStore must not be null");
		Validate.notNull(language, "Language must not be null");

		Locale locale = LocaleUtils.getLocale(language);

		try {

			target.setCode(source.getOrderTotalCode());
			target.setId(source.getId());
			target.setModule(source.getModule());
			target.setOrder(source.getSortOrder());

			target.setTitle(messages.getMessage(source.getOrderTotalCode(), locale, source.getOrderTotalCode()));
			target.setText(source.getText());

			target.setValue(source.getValue());
			target.setTotal(pricingService.getDisplayAmount(source.getValue(), store));

			if (!StringUtils.isBlank(source.getOrderTotalCode())) {
				if (Constants.OT_DISCOUNT_TITLE.equals(source.getOrderTotalCode())) {
					target.setDiscounted(true);
				}
			}

		} catch (Exception e) {
			throw new ConversionRuntimeException(e);
		}

		return target;

	}

}
