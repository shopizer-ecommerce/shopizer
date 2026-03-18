package com.salesmanager.shop.utils;

import org.apache.commons.lang3.StringUtils;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;

public final class ProductDescriptionConverter {

    private ProductDescriptionConverter() {}

    public static com.salesmanager.shop.model.catalog.product.ProductDescription toReadableDescription(
            ProductDescription description) {
        if (description == null) {
            return null;
        }
        com.salesmanager.shop.model.catalog.product.ProductDescription targetDescription =
                new com.salesmanager.shop.model.catalog.product.ProductDescription();
        targetDescription.setFriendlyUrl(description.getSeUrl());
        targetDescription.setName(description.getName());
        targetDescription.setId(description.getId());
        if (!StringUtils.isBlank(description.getMetatagTitle())) {
            targetDescription.setTitle(description.getMetatagTitle());
        } else {
            targetDescription.setTitle(description.getName());
        }
        targetDescription.setMetaDescription(description.getMetatagDescription());
        targetDescription.setDescription(description.getDescription());
        targetDescription.setHighlights(description.getProductHighlight());
        targetDescription.setKeyWords(description.getMetatagKeywords());
        if (description.getLanguage() != null) {
            targetDescription.setLanguage(description.getLanguage().getCode());
        }
        return targetDescription;
    }
}
