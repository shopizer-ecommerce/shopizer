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

public class TestAddToCartSteps{

	private WebDriver driver;
	private String baseUrl;

	@Before("@selenium3")
	public void setUp() throws Throwable {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		driver = new RemoteWebDriver( new URL("http://dockerselenium.azurewebsites.net/wd/hub"), capability);
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1920,1080));
		baseUrl = "http://jenkins2017.westeurope.cloudapp.azure.com:8080";
	    System.out.println("Test 3, selenium");
	}

	@Given("^user is on productpage \"([^\"]*)\"$")
	public void user_is_on_productpage(String arg1) throws Throwable {
	    System.out.println("Test 3, Given");
		WebDriverWait w = new WebDriverWait(driver, 30);
		driver.get(baseUrl + "/shop/product/"+arg1);
		w.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='shop']/div/div/div/div/ol/li[2]/a")));
	}
	
	@When("^user adds to cart$")
	public void AddProductToChart() throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250);");
		WebDriverWait w = new WebDriverWait(driver, 30);
		w.until(ExpectedConditions.presenceOfElementLocated((By.xpath("(//button[@type='button'])[7]"))));
  		driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
  		Thread.sleep(1000);//!
  		jse.executeScript("scroll(0, -250);");
	}

	@Then("^product is placed in cart$")
	public void ProductInChart() throws InterruptedException {
		Thread.sleep(1000);//!
		WebElement varuKorg = driver.findElement(By.xpath(".//*[@id='open-cart']"));
	    assertEquals("SHOPPING CART (1)",  varuKorg.getText());
	    System.out.println("Test 3, lägg i varukorgen: " + varuKorg.getText());
	}

	@After("@selenium3")
	public void tearDown(){
		driver.quit();
	}

}

