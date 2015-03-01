package com.salesmanager.core.business.content.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ContentDescription.class)
public abstract class ContentDescription_ extends com.salesmanager.core.business.common.model.Description_ {

	public static volatile SingularAttribute<ContentDescription, String> metatagTitle;
	public static volatile SingularAttribute<ContentDescription, Content> content;
	public static volatile SingularAttribute<ContentDescription, String> metatagDescription;
	public static volatile SingularAttribute<ContentDescription, String> metatagKeywords;
	public static volatile SingularAttribute<ContentDescription, String> seUrl;

}

