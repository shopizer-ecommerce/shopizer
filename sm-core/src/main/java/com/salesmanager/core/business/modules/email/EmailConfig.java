package com.salesmanager.core.business.modules.email;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class EmailConfig implements JSONAware {

	private String host;
	private String port;
	private String protocol;
	private String username;
	private String password;
	private boolean smtpAuth = false;
	private boolean starttls = false;
	
	private String emailTemplatesPath = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public String toJSONString() {
		JSONObject data = new JSONObject();
		data.put("host", this.getHost());
		data.put("port", this.getPort());
		data.put("protocol", this.getProtocol());
		data.put("username", this.getUsername());
		data.put("smtpAuth", this.isSmtpAuth());
		data.put("starttls", this.isStarttls());
		data.put("password", this.getPassword());
		return data.toJSONString();
	}
	
	

	public boolean isSmtpAuth() {
		return smtpAuth;
	}
	public void setSmtpAuth(boolean smtpAuth) {
		this.smtpAuth = smtpAuth;
	}
	public boolean isStarttls() {
		return starttls;
	}
	public void setStarttls(boolean starttls) {
		this.starttls = starttls;
	}
	public void setEmailTemplatesPath(String emailTemplatesPath) {
		this.emailTemplatesPath = emailTemplatesPath;
	}
	public String getEmailTemplatesPath() {
		return emailTemplatesPath;
	}



	public String getHost() {
		return host;
	}



	public void setHost(String host) {
		this.host = host;
	}



	public String getPort() {
		return port;
	}



	public void setPort(String port) {
		this.port = port;
	}



	public String getProtocol() {
		return protocol;
	}



	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

}
