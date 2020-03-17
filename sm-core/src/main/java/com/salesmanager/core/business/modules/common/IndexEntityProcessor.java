package com.salesmanager.core.business.modules.common;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;

public class IndexEntityProcessor {
	
	@Value("${elasticsearch.server.host}")
	private List<String> hosts;
	
	@Value("${elasticsearch.server.protocole}")
	private String protocol;
	
	@Value("${elasticsearch.server.port}")
	private int port;
	
	protected RestHighLevelClient client() throws Exception {
		
		List<HttpHost> nodes = getHosts().stream().map(m -> new HttpHost(m, getPort(), getProtocol())).collect(Collectors.toList());
		RestClientBuilder builder = RestClient.builder(nodes.toArray(new HttpHost[nodes.size()]));
		RestHighLevelClient client = new RestHighLevelClient(builder);
		
		return client;

	}

	protected List<String> getHosts() {
		return hosts;
	}

	protected String getProtocol() {
		return protocol;
	}

	protected int getPort() {
		return port;
	}

}
