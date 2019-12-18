package com.salesmanager.core.business.configuration;

//@Configuration
public class DroolsConfiguration {
/*
  private final static String SHIPPING_CUSTOM = "rules/shipping-custom-rules.xls";
  private final static String SHIPPING_DECISION = "rules/shipping-decision-rules.xls";
  private final static String MANUFACTURER_BASED_PRICING =
      "rules/manufacturer-shipping-ordertotal-rules.xls";
  
  private static final String RULES_PATH = "com/salesmanager/drools/rules/";
  private KieServices kieServices=KieServices.Factory.get();
*/

  /**
   * Drools containers defined here TODO used a builder utility instead of repetition
   */
  /**
   * This container goes along with shipping custom payment module determines pricing based on
   * different criterias
   * 
   * @return
   */
/*  @Bean
  public KieContainer kieShippingCustomContainer() {
    KieServices kieServices = KieServices.Factory.get();

    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    kieFileSystem.write(ResourceFactory.newClassPathResource(SHIPPING_CUSTOM));
    KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
    kieBuilder.buildAll();
    KieModule kieModule = kieBuilder.getKieModule();

    return kieServices.newKieContainer(kieModule.getReleaseId());
  }*/

/*  @Bean
  public KieContainer kieShippingDecisionContainer() {
    KieServices kieServices = KieServices.Factory.get();

    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    kieFileSystem.write(ResourceFactory.newClassPathResource(SHIPPING_DECISION));
    KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
    kieBuilder.buildAll();
    KieModule kieModule = kieBuilder.getKieModule();

    return kieServices.newKieContainer(kieModule.getReleaseId());
  }*/

/*  @Bean
  public KieContainer kieManufacturerBasedPricingContainer() {
    KieServices kieServices = KieServices.Factory.get();

    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    kieFileSystem.write(ResourceFactory.newClassPathResource(MANUFACTURER_BASED_PRICING));
    KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
    kieBuilder.buildAll();
    KieModule kieModule = kieBuilder.getKieModule();

    return kieServices.newKieContainer(kieModule.getReleaseId());
  }*/
  
/*  public KieFileSystem kieFileSystem() throws IOException {
	    KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
	        for (Resource file : getRuleFiles()) {
	            kieFileSystem.write(
	              ResourceFactory.newClassPathResource(
	              RULES_PATH + file.getFilename(), "UTF-8"));
	        }
	        return kieFileSystem;
	}*/


}
