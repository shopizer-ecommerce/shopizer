package com.salesmanager.shop.application;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.vault.config.EnvironmentVaultConfiguration;

@PropertySource("vault.properties")
@Import(EnvironmentVaultConfiguration.class)
public class VaultConfiguration {

}
