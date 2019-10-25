package com.salesmanager.shop.store.controller.product.facade;

import java.util.List;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;

public interface ProductTypeFacade {
  
  List<ReadableProductType >getByMerchant(String merchant, Language language);

}
