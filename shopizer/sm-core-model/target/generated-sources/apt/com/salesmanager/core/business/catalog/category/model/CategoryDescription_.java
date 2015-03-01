package com.salesmanager.core.business.catalog.category.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CategoryDescription.class)
public abstract class CategoryDescription_ extends com.salesmanager.core.business.common.model.Description_ {

	public static volatile SingularAttribute<CategoryDescription, String> metatagTitle;
	public static volatile SingularAttribute<CategoryDescription, String> categoryHighlight;
	public static volatile SingularAttribute<CategoryDescription, Category> category;
	public static volatile SingularAttribute<CategoryDescription, String> metatagDescription;
	public static volatile SingularAttribute<CategoryDescription, String> metatagKeywords;
	public static volatile SingularAttribute<CategoryDescription, String> seUrl;

}

