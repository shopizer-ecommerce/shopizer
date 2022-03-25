package com.salesmanager.shop.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {
	
	private String applicationHost = null;
	
	@Override
	public void onApplicationEvent(final WebServerInitializedEvent event) {
	    int port = event.getWebServer().getPort();
	    final String host = getHost();
	    setApplicationHost(String.join(":", host, String.valueOf(port)));

	}
	
    private String getHost() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "127.0.0.1";
        }
    }

	public String getApplicationHost() {
		return applicationHost;
	}

	public void setApplicationHost(String applicationHost) {
		this.applicationHost = applicationHost;
	}

}
