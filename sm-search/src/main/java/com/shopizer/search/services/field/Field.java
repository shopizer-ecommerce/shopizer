package com.shopizer.search.services.field;

public abstract class Field {
	
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Object object;
	public void setValue(Object o) {
		this.object = o;
	}
	
	protected Object getValue() {
		return object;
	}
	

}
