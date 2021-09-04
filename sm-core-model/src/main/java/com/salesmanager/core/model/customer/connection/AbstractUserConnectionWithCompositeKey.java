package com.salesmanager.core.model.customer.connection;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.salesmanager.core.constants.SchemaConstant;

@Deprecated
@MappedSuperclass
@Table(name="USERCONNECTION", uniqueConstraints = { @UniqueConstraint(columnNames = { "userId",
		"providerId", "userRank" }) })
public abstract class AbstractUserConnectionWithCompositeKey extends
		AbstractUserConnection<UserConnectionPK> {

	@Id
	private UserConnectionPK primaryKey = new UserConnectionPK();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getProviderId() {
		return primaryKey.getProviderId();
	}

	@Override
	public void setProviderId(String providerId) {
		primaryKey.setProviderId(providerId);
	}

	@Override
	public String getProviderUserId() {
		return primaryKey.getProviderUserId();
	}

	@Override
	public void setProviderUserId(String providerUserId) {
		primaryKey.setProviderUserId(providerUserId);
	}

	@Override
	public String getUserId() {
		return primaryKey.getUserId();
	}

	@Override
	public void setUserId(String userId) {
		primaryKey.setUserId(userId);
	}

	@Override
	protected UserConnectionPK getId() {
		return primaryKey;
	}

}
