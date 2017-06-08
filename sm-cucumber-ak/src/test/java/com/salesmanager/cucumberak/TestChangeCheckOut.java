package com.salesmanager.cucumberak;
import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ParentPage;

public class TestChangeCheckOut  extends ParentPage {
	private WebDriver driver;
	private String baseUrl;
	
	@Before("@selenium4")
	public void setUp() throws Throwable {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		driver = new RemoteWebDriver( new URL("http://dockerselenium.azurewebsites.net/wd/hub"), capability);
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1920,1080));
		baseUrl = "http://jenkins2017.westeurope.cloudapp.azure.com:8080";
		System.out.println("ChangeCheckOut before");
	}
	
	@Given("^Cart is loaded with product \"([^\"]*)\"$")
	public void cart_is_loaded_with_product(String arg1) throws Throwable {
		WebDriverWait w = new WebDriverWait(driver, 30);
		driver.get(baseUrl + "/shop/product/"+arg1);
		w.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='shop']/div/div/div/div/ol/li[2]/a")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250);");
		w.until(ExpectedConditions.presenceOfElementLocated((By.xpath("(//button[@type='button'])[7]"))));
  		driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
  		Thread.sleep(1000);//!
  		jse.executeScript("scroll(0, -250);");
	    System.out.println("ChangeCheckOut given");
	}

	@When("^user increase quantity \"(.*)\"$")
	public void user_increase_quantity(String arg1) throws Throwable {
		WebDriverWait w = new WebDriverWait(driver, 50);
		Thread.sleep(1000);//!
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("open-cart")));
		Thread.sleep(1000);//!
		driver.findElement(By.id("open-cart")).click();
		Thread.sleep(1000);//!
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Checkout")));
		driver.findElement(By.linkText("Checkout")).click();
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("quantity")));
		driver.findElement(By.name("quantity")).clear();
	    driver.findElement(By.name("quantity")).sendKeys(arg1);
	    driver.findElement(By.linkText("Recalculate")).click();
	    Thread.sleep(1000);//!
	    System.out.println("ChangeCheckOut when");
	    //throw new PendingException();
	}

	@Then("^Quantity be changed$")
	public void quantity_be_changed() throws Throwable {
	    WebElement varuKorg = driver.findElement(By.id("open-cart"));
	    Thread.sleep(1000);//!
	    assertEquals("SHOPPING CART (300)",  varuKorg.getText());
	    System.out.println("Test 4 öka antal: " + varuKorg.getText());
	    System.out.println("ChangeCheckOut then");
	}
	
	@After("@selenium4")
	public void tearDown(){
		driver.quit();
	}
}
