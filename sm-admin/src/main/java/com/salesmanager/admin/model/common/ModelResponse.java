package com.salesmanager.admin.model.common;

public class ModelResponse<T> extends Response {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T value;
	
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}

}
