package com.salesmanager.core.business.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import modules.commons.search.configuration.Credentials;
import modules.commons.search.configuration.SearchHost;

/**
 * Reads search related properties required for search starter
 * @author carlsamson
 *
 */


@Configuration
@ConfigurationProperties(prefix = "search")
@PropertySource("classpath:shopizer-core.properties")
public class ApplicationSearchConfiguration {
	
    private String clusterName;
    private Credentials credentials;
    private List<SearchHost> host;
    private List<String> searchLanguages;
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	public List<SearchHost> getHost() {
		return host;
	}
	public void setHost(List<SearchHost> host) {
		this.host = host;
	}
	public List<String> getSearchLanguages() {
		return searchLanguages;
	}
	public void setSearchLanguages(List<String> searchLanguages) {
		this.searchLanguages = searchLanguages;
	}


}
