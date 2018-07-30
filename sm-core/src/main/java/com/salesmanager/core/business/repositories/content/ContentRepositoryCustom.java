package com.salesmanager.core.business.repositories.content;

import com.salesmanager.core.model.content.ContentDescription;
import com.salesmanager.core.model.content.ContentType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

import java.util.List;


public interface ContentRepositoryCustom {

    List<ContentDescription> listNameByType(List<ContentType> contentType,
                                            MerchantStore store, Language language);

    ContentDescription getBySeUrl(MerchantStore store, String seUrl);


}
