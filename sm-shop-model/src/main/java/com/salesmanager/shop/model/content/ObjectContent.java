package com.salesmanager.shop.model.content;

import com.salesmanager.shop.model.constants.SmShopModelLiterals;
import com.salesmanager.shop.model.entity.ResourceUrlAccess;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Deprecated: Represents content object with SEO-friendly URL slug.
 *
 * @deprecated Replaced by newer content handling models.
 * @lastUpdated 2025-09-08 By Kuntal
 */
@Deprecated
@Getter
@Setter
@NoArgsConstructor
public class ObjectContent extends ContentPath implements ResourceUrlAccess {

    private static final long serialVersionUID = SmShopModelLiterals.SERIAL_VERSION_UID;

    private String slug;
    private String metaDetails;
    private String title;
    private String pageContent;
    private String language;
}