package com.salesmanager.shop.model.system;


public class Configs {
	
	private String facebook;
	private String pinterest;
	private String ga;
	private String instagram;
	
	private boolean allowOnlinePurchase;
	private boolean displaySearchBox;
	private boolean displayContactUs;
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getPinterest() {
		return pinterest;
	}
	public void setPinterest(String pinterest) {
		this.pinterest = pinterest;
	}
	public String getGa() {
		return ga;
	}
	public void setGa(String ga) {
		this.ga = ga;
	}
	public String getInstagram() {
		return instagram;
	}
	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}
	public boolean isAllowOnlinePurchase() {
		return allowOnlinePurchase;
	}
	public void setAllowOnlinePurchase(boolean allowOnlinePurchase) {
		this.allowOnlinePurchase = allowOnlinePurchase;
	}
	public boolean isDisplaySearchBox() {
		return displaySearchBox;
	}
	public void setDisplaySearchBox(boolean displaySearchBox) {
		this.displaySearchBox = displaySearchBox;
	}
	public boolean isDisplayContactUs() {
		return displayContactUs;
	}
	public void setDisplayContactUs(boolean displayContactUs) {
		this.displayContactUs = displayContactUs;
	}


}
