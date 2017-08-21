package com.salesmanager.cucumber.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.pages.PageObject;

public class CurrentPage  extends PageObject {

	public String readGreeting() {
		return getDriver().findElement(By.xpath("//*[@id='fat-menu']/a")).getText();
	}

}
