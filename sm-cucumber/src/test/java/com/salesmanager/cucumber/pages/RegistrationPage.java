package com.salesmanager.cucumber.pages;

import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("http://bluebottle.westeurope.cloudapp.azure.com:8080/shop/customer/registration.html")
public class RegistrationPage  extends PageObject {

	public void register(String name) {
		open();
		
		getDriver().findElement(By.id("firstName")).clear();
		getDriver().findElement(By.id("firstName")).sendKeys(name);
		element(By.id("lastName")).clear();
	    element(By.id("lastName")).sendKeys("Test");
	    element(By.id("hidden_zones")).clear();
	    element(By.id("hidden_zones")).sendKeys("Test");
	    element(By.id("userName")).clear();	    
	    element(By.id("userName")).sendKeys(java.util.UUID.randomUUID().toString());
	    element(By.id("email")).clear();
	    element(By.id("email")).sendKeys("3bitstest@gmail.com");
	    element(By.id("password")).clear();
	    String password=java.util.UUID.randomUUID().toString();
	    element(By.id("password")).sendKeys(password);
	    element(By.id("passwordAgain")).clear();
	    element(By.id("passwordAgain")).sendKeys(password);
		
	}

	public void submitRegistration() {
	    getDriver().findElement(By.id("submitRegistration")).submit();		
	}

}
