package com.salesmanager.core.business.configuration;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfiguration {

  private final static String SHIPPING_CUSTOM = "rules/shipping-custom-rules.xls";
  private final static String SHIPPING_DECISION = "rules/shipping-decision-rules.xls";
  private final static String MANUFACTURER_BASED_PRICING =
      "rules/manufacturer-shipping-ordertotal-rules.xls";


  /**
   * Drools containers defined here TODO used a builder utility instead of repetition
   */
  /**
   * This container goes along with shipping custom payment module determines pricing based on
   * different criterias
   * 
   * @return
   */
  @Bean
  public KieContainer kieShippingCustomContainer() {
    KieServices kieServices = KieServices.Factory.get();

    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    kieFileSystem.write(ResourceFactory.newClassPathResource(SHIPPING_CUSTOM));
    KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
    kieBuilder.buildAll();
    KieModule kieModule = kieBuilder.getKieModule();

    return kieServices.newKieContainer(kieModule.getReleaseId());
  }

  @Bean
  public KieContainer kieShippingDecisionContainer() {
    KieServices kieServices = KieServices.Factory.get();

    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    kieFileSystem.write(ResourceFactory.newClassPathResource(SHIPPING_DECISION));
    KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
    kieBuilder.buildAll();
    KieModule kieModule = kieBuilder.getKieModule();

    return kieServices.newKieContainer(kieModule.getReleaseId());
  }

  @Bean
  public KieContainer kieManufacturerBasedPricingContainer() {
    KieServices kieServices = KieServices.Factory.get();

    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    kieFileSystem.write(ResourceFactory.newClassPathResource(MANUFACTURER_BASED_PRICING));
    KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
    kieBuilder.buildAll();
    KieModule kieModule = kieBuilder.getKieModule();

    return kieServices.newKieContainer(kieModule.getReleaseId());
  }

}
