package com.salesmanager.core.business.configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import modules.commons.search.configuration.SearchConfiguration;
import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import modules.commons.search.configuration.Credentials;
import modules.commons.search.configuration.SearchHost;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;


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
	private final static String SETTINGS = "search/SETTINGS";
	private final static String PRODUCT_MAPPING_DEFAULT = "search/MAPPINGS.json";
	private final static String KEYWORDS_MAPPING_DEFAULT = "{\"properties\":"
			+ "      {\n\"id\": {\n"
			+ "        \"type\": \"long\"\n"
			+ "      }\n"
			+ "     }\n"
			+ "    }";

	@Autowired
	private ResourceLoader resourceLoader;
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


	@PostConstruct
	public SearchConfiguration config() throws Exception {
		SearchConfiguration config = new SearchConfiguration();
		config.setClusterName(this.getClusterName());
		config.setHosts(this.getHost());
		config.setCredentials(this.getCredentials());

		List<String> languages = this.getSearchLanguages();
		if (languages != null) {
			config.setLanguages(languages);
			for (String l : languages) {
				try {
					mappings(config, l);
					this.settings(config, l);
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}
			}
		};
		return config;
	}
	private void settings(SearchConfiguration config, String language) throws Exception{
		Validate.notEmpty(language, "Configuration requires language");
		String settings = resourceAsText(loadSearchConfig(SETTINGS + "_DEFAULT.json"));
		//specific settings
		if(language.equals("en")) {
			settings = resourceAsText(loadSearchConfig(SETTINGS+ "_" + language +".json"));
		}

		config.getSettings().put(language, settings);

	}

	private void mappings(SearchConfiguration config, String language) throws Exception {
		Validate.notEmpty(language, "Configuration requires language");

		config.getProductMappings().put(language, resourceAsText(loadSearchConfig(PRODUCT_MAPPING_DEFAULT)));
		config.getKeywordsMappings().put(language,KEYWORDS_MAPPING_DEFAULT);

	}


	private String resourceAsText(Resource resource) throws Exception {
		InputStream mappingstream = resource.getInputStream();

		return new BufferedReader(
				new InputStreamReader(mappingstream, StandardCharsets.UTF_8))
				.lines()
				.collect(Collectors.joining("\n"));
	}

	private Resource loadSearchConfig(String file) {
		return resourceLoader.getResource(
				"classpath:" + file);
	}
}
