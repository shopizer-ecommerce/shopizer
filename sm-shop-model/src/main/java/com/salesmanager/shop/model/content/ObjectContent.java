package com.salesmanager.shop.model.content;

import com.salesmanager.shop.model.entity.ResourceUrlAccess;

@Deprecated
public class ObjectContent extends ContentPath implements ResourceUrlAccess {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String slug;
  private String metaDetails;
  private String title;
  private String pageContent;
  private String language;
  public String getPageContent() {
      return pageContent;
  }
  public void setPageContent(String pageContent) {
      this.pageContent = pageContent;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getMetaDetails() {
    return metaDetails;
  }

  public void setMetaDetails(String metaDetails) {
    this.metaDetails = metaDetails;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  public String getLanguage() {
    return language;
  }
  public void setLanguage(String language) {
    this.language = language;
  }


}
