package com.salesmanager.core.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class CredentialsReset {
	
	@Column (name ="RESET_CREDENTIALS_REQ", length=256)
	private String credentialsRequest;

	@Temporal(TemporalType.DATE)
	@Column(name = "RESET_CREDENTIALS_EXP")
	private Date credentialsRequestExpiry = new Date();

	public String getCredentialsRequest() {
		return credentialsRequest;
	}

	public void setCredentialsRequest(String credentialsRequest) {
		this.credentialsRequest = credentialsRequest;
	}

	public Date getCredentialsRequestExpiry() {
		return credentialsRequestExpiry;
	}

	public void setCredentialsRequestExpiry(Date credentialsRequestExpiry) {
		this.credentialsRequestExpiry = credentialsRequestExpiry;
	}

}
