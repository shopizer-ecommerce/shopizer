package com.salesmanager.shop.application.config;

import com.salesmanager.shop.utils.LocalImageFilePathUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"default","docker","gcp"})
public class LocalLocationImageConfig {

  @Bean
  public LocalImageFilePathUtils img() {
    LocalImageFilePathUtils localImageFilePathUtils = new LocalImageFilePathUtils();
    localImageFilePathUtils.setBasePath("/static");
    return localImageFilePathUtils;
  }
}
