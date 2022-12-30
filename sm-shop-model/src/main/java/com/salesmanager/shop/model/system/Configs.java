package com.salesmanager.shop.model.system;

public class Configs {

	private String facebook;
	private String pinterest;
	private String ga;
	private String instagram;

	private boolean allowOnlinePurchase;
	private boolean displaySearchBox;
	private boolean displayContactUs;
	private boolean displayShipping;

	private boolean displayCustomerSection =false;
	private boolean displayAddToCartOnFeaturedItems = false;
	private boolean displayCustomerAgreement = false;
	private boolean displayPagesMenu = true;

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

	public boolean isDisplayShipping() {
		return displayShipping;
	}

	public void setDisplayShipping(boolean displayShipping) {
		this.displayShipping = displayShipping;
	}

	public boolean isDisplayCustomerSection() {
		return displayCustomerSection;
	}

	public void setDisplayCustomerSection(boolean displayCustomerSection) {
		this.displayCustomerSection = displayCustomerSection;
	}

	public boolean isDisplayAddToCartOnFeaturedItems() {
		return displayAddToCartOnFeaturedItems;
	}

	public void setDisplayAddToCartOnFeaturedItems(boolean displayAddToCartOnFeaturedItems) {
		this.displayAddToCartOnFeaturedItems = displayAddToCartOnFeaturedItems;
	}

	public boolean isDisplayCustomerAgreement() {
		return displayCustomerAgreement;
	}

	public void setDisplayCustomerAgreement(boolean displayCustomerAgreement) {
		this.displayCustomerAgreement = displayCustomerAgreement;
	}

	public boolean isDisplayPagesMenu() {
		return displayPagesMenu;
	}

	public void setDisplayPagesMenu(boolean displayPagesMenu) {
		this.displayPagesMenu = displayPagesMenu;
	}
}
