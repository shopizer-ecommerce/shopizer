package com.salesmanager.cucumberak;

import org.openqa.selenium.WebDriver;

import pages.ParentPage;
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

public class TestRemoveChekOut extends ParentPage{
	private WebDriver driver;
	private String baseUrl;

	@Before("@selenium5")
	public void setUp() throws Throwable {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		driver = new RemoteWebDriver( new URL("http://dockerselenium.azurewebsites.net/wd/hub"), capability);
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1920,1080));
		baseUrl = "http://jenkins2017.westeurope.cloudapp.azure.com:8080";
		System.out.println("Remove before");
	}

	@Given("^Product \"([^\"]*)\" is loaded in cart$")
	public void product_is_loaded_in_cart(String arg1) throws Throwable {
		WebDriverWait w = new WebDriverWait(driver, 30);
		driver.get(baseUrl + "/shop/product/"+arg1);
		w.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='shop']/div/div/div/div/ol/li[2]/a")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250);");
		w.until(ExpectedConditions.presenceOfElementLocated((By.xpath("(//button[@type='button'])[7]"))));
		driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
	  	Thread.sleep(1000);//!
	  	jse.executeScript("scroll(0, -250);");
		System.out.println("Remove - Given " );
	}

	@When("^I push remove-button$")
	public void i_push_remove_button() throws Throwable {
		Thread.sleep(1000);//!
		WebDriverWait w = new WebDriverWait(driver, 40);
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("open-cart")));
		driver.findElement(By.id("open-cart")).click();
		driver.findElement(By.linkText("Checkout")).click();
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".btn-danger")));
		driver.findElement(By.cssSelector(".btn-danger")).click();
		System.out.println("Remove - When " );
	}

	@Then("^cart will be empty$")
	public void cart_will_be_empty() throws Throwable {
		WebDriverWait w = new WebDriverWait(driver, 40);
		Thread.sleep(1000);//!
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("open-cart")));
		WebElement varuKorg = driver.findElement(By.id("open-cart"));
		assertEquals("SHOPPING CART (0)",  varuKorg.getText());
		System.out.println("Test 5 remove: " + varuKorg.getText());
		System.out.println("Remove - Then " );

	}

	@After("@selenium5")
	public void tearDown(){
		driver.quit();
	}

}
