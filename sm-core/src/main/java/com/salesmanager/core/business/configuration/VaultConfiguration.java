package com.salesmanager.core.business.configuration;


//Will be added with Spring 5 support
//@Configuration
//@PropertySource("classpath:vault.properties")
//@PropertySource("classpath:vault.properties")
//@Import(EnvironmentVaultConfiguration.class)
//public class VaultConfiguration {
public class VaultConfiguration  { //extends AbstractVaultConfiguration {
	
    //@Value("${vault.uri}")
    //URI vaultUri;
    
    //@Value("${vault.token}")
    //String vaultToken;

/*	@Override
	public VaultEndpoint vaultEndpoint() {
		
		*//**
		 * To be uncommented
		 *//*
	ClassPathResource resource = new ClassPathResource("vault.properties");
		try {
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			String uri = (String) props.get("vault.uri");
			System.out.println(uri);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new VaultEndpoint();
	}

	@Override
	public ClientAuthentication clientAuthentication() {
		return new TokenAuthentication("5d482864-92ee-f4a6-82d4-58240453cb80");
	}*/

}
