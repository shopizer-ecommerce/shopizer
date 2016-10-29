package com.salesmanager.shop.populator.order;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.orderproduct.OrderProductDownload;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.shop.model.order.ReadableOrderProductDownload;

public class ReadableOrderProductDownloadPopulator extends
		AbstractDataPopulator<OrderProductDownload, ReadableOrderProductDownload> {

	@Override
	public ReadableOrderProductDownload populate(OrderProductDownload source,
			ReadableOrderProductDownload target, MerchantStore store,
			Language language) throws ConversionException {
		try {
			
			target.setProductName(source.getOrderProduct().getProductName());
			target.setDownloadCount(source.getDownloadCount());
			target.setDownloadExpiryDays(source.getMaxdays());
			target.setId(source.getId());
			target.setFileName(source.getOrderProductFilename());
			target.setOrderId(source.getOrderProduct().getOrder().getId());
			
			return target;
			
		} catch(Exception e) {
			throw new ConversionException(e);
		}
	}

	@Override
	protected ReadableOrderProductDownload createTarget() {
		return new ReadableOrderProductDownload();
	}
	

}
