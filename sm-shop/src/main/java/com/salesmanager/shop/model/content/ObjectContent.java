package com.salesmanager.shop.model.content;

import com.salesmanager.shop.model.entity.ResourceUrlAccess;

public class ObjectContent extends ContentPath implements ResourceUrlAccess {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private boolean isDisplayedInMenu;
  private String slug;
  private String code;
  private String metaDetails;
  private String title;
  private String pageContent;
  public String getPageContent() {
      return pageContent;
  }
  public void setPageContent(String pageContent) {
      this.pageContent = pageContent;
  }

  public boolean isDisplayedInMenu() {
    return isDisplayedInMenu;
  }

  public void setDisplayedInMenu(boolean isDisplayedInMenu) {
    this.isDisplayedInMenu = isDisplayedInMenu;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
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


}
