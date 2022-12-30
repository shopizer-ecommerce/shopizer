package com.salesmanager.core.model.common;

public final class UserContext implements AutoCloseable {
	
	private String ipAddress;
	
	private static ThreadLocal<UserContext> instance = new ThreadLocal<>();

	private UserContext() {}
	
    public static UserContext create() {
    	UserContext context = new UserContext();
        instance.set(context);
        return context;
    }


	@Override
	public void close() throws Exception {
		instance.remove();
	}
	
    public static UserContext getCurrentInstance() {
        return instance.get();
    }

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
