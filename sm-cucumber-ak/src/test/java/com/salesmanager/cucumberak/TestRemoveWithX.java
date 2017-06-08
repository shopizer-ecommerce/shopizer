package com.salesmanager.cucumberak;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestRemoveWithX {
	private WebDriver driver;
	private String baseUrl;

	@Before("@selenium8")
	public void setUp() throws Throwable {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		driver = new RemoteWebDriver( new URL("http://dockerselenium.azurewebsites.net/wd/hub"), capability);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1920,1080));
		baseUrl = "http://jenkins2017.westeurope.cloudapp.azure.com:8080";
		System.out.println("Labb4_8 RemoveWithX, Before" );
	}

	@Given("^Product add to cart \"([^\"]*)\"$")
	public void product_add_to_cart(String arg1) throws Throwable {
		//produkt1
		WebDriverWait w = new WebDriverWait(driver, 30);
		driver.get(baseUrl + "/shop/product/"+arg1);
		w.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='shop']/div/div/div/div/ol/li[2]/a")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250);");
		w.until(ExpectedConditions.presenceOfElementLocated((By.xpath("(//button[@type='button'])[7]"))));
		driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
	  	Thread.sleep(1000);//!
	  	jse.executeScript("scroll(0, -250);");
	  	Thread.sleep(1000);//!
		Thread.sleep(1000);//!
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("open-cart")));
		Thread.sleep(1000);//!
		driver.findElement(By.id("open-cart")).click();
		Thread.sleep(1000);//!
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Checkout")));
		driver.findElement(By.linkText("Checkout")).click();
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("quantity")));
		driver.findElement(By.name("quantity")).clear();
	    driver.findElement(By.name("quantity")).sendKeys("2");
	    driver.findElement(By.linkText("Recalculate")).click();
	    Thread.sleep(1000);//!
		System.out.println("Labb4_8 RemoveWithX, Given" );
	}

	@When("^I remove product in cart with X$")
	public void i_remove_product_in_chart_with_X() throws Throwable {
		Thread.sleep(1000);//!
		driver.findElement(By.xpath(".//*[@id='open-cart']")).click();
		Thread.sleep(1000);//!
		driver.findElement(By.xpath(".//*[@id='1']/td[4]/button")).click();
		System.out.println("Labb4_8 RemoveWithX, When" );
	}

	@Then("^Cart will have one product less$")
	public void chart_will_be_empty() throws Throwable {
		Thread.sleep(1000);//!
		driver.get(baseUrl + "/shop/");
		Thread.sleep(1000);//!
		WebElement varuKorg = driver.findElement(By.id("open-cart"));
		Thread.sleep(1000);//!
    	System.out.println("Test 8: " + varuKorg.getText());
    	assertEquals("SHOPPING CART (0)",  varuKorg.getText());
	}
	
	@After("@selenium8")
	public void tearDown(){
		driver.quit();
	}
}
