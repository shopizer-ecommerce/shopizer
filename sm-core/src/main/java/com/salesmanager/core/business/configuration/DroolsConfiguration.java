package com.salesmanager.core.business.configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.drools.decisiontable.DecisionTableProviderImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfiguration {

  @Value("${rules.table.customShipping}")
  private String customShipping;

  @Value("${rules.table.shippingDecision}")
  private String shippingDecision;

  @Value("${rules.table.manufacturerPricing}")
  private String manufacturerPricing;

  private KieServices kieServices = KieServices.Factory.get();



  /*
   * private void getKieRepository() { final KieRepository kieRepository =
   * kieServices.getRepository(); kieRepository.addKieModule(new KieModule() { public ReleaseId
   * getReleaseId() { return kieRepository.getDefaultReleaseId(); } }); }
   */
  /*
   * private KieFileSystem getKieFileSystem() throws IOException{ KieFileSystem kieFileSystem =
   * kieServices.newKieFileSystem(); List<String>
   * rules=Arrays.asList(customShipping,shippingDecision,manufacturerPricing); for(String
   * rule:rules){ kieFileSystem.write(ResourceFactory.newClassPathResource(rule)); } return
   * kieFileSystem;
   * 
   * }
   */

  /**
   * This container goes along with shipping custom payment module determines pricing based on
   * different criterias
   * 
   * @return
   */


  /*
   * public KieContainer getKieContainer() throws IOException { getKieRepository();
   * 
   * KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem()); kb.buildAll();
   * 
   * KieModule kieModule = kb.getKieModule(); KieContainer kContainer =
   * kieServices.newKieContainer(kieModule.getReleaseId());
   * 
   * return kContainer;
   * 
   * }
   */

  @Bean
  public KieContainer kieShippingCustomContainer() {
    // KieServices kieServices = KieServices.Factory.get();

    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    kieFileSystem.write(ResourceFactory.newClassPathResource(customShipping));
    KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
    kieBuilder.buildAll();
    KieModule kieModule = kieBuilder.getKieModule();

    return kieServices.newKieContainer(kieModule.getReleaseId());
  }

  //@Bean
  public KieSession kieShippingCustomContainerSession() {
    
    //DecisionTableConfiguration dtableconfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
    //dtableconfiguration.setInputType( DecisionTableInputType.XLS );
    //KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

    
    Resource xlsRes = ResourceFactory.newClassPathResource(customShipping,getClass());
    
/*    kbuilder.add( xlsRes,
        ResourceType.DTABLE,
        dtableconfiguration );*/

    KieFileSystem kieFileSystem = kieServices.newKieFileSystem().write(xlsRes);
    KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
    KieRepository kieRepository = kieServices.getRepository();


    ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();
    KieContainer kieContainer = kieServices.newKieContainer(krDefaultReleaseId);
    KieSession ksession = kieContainer.newKieSession();
    return ksession;
  }
  
  public String getDrlFromExcel() {
    DecisionTableConfiguration configuration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
    configuration.setInputType(DecisionTableInputType.XLS);

    Resource dt = ResourceFactory.newClassPathResource(customShipping, getClass());

    DecisionTableProviderImpl decisionTableProvider = new DecisionTableProviderImpl();

    String drl = decisionTableProvider.loadFromResource(dt, configuration);

    return drl;
}

  @Bean
  public KieContainer kieShippingDecisionContainer() {
    // KieServices kieServices = KieServices.Factory.get();

    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    kieFileSystem.write(ResourceFactory.newClassPathResource(shippingDecision));
    KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
    kieBuilder.buildAll();
    KieModule kieModule = kieBuilder.getKieModule();

    return kieServices.newKieContainer(kieModule.getReleaseId());
  }

  @Bean
  public KieContainer kieManufacturerBasedPricingContainer() {
    // KieServices kieServices = KieServices.Factory.get();

    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    kieFileSystem.write(ResourceFactory.newClassPathResource(manufacturerPricing));
    KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
    kieBuilder.buildAll();
    KieModule kieModule = kieBuilder.getKieModule();

    return kieServices.newKieContainer(kieModule.getReleaseId());
  }

}
