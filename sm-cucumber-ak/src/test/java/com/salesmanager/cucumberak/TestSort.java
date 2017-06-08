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
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestSort {
	private WebDriver driver;
	private String baseUrl;

	@Before("@selenium9")
	public void setUp() throws Throwable {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		driver = new RemoteWebDriver( new URL("http://dockerselenium.azurewebsites.net/wd/hub"), capability);
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1920,1080));
		baseUrl = "http://jenkins2017.westeurope.cloudapp.azure.com:8080";
		System.out.println("Labb4_9 Sort, Before" );
	}

	@Given("^Select product categories \"([^\"]*)\"$")
	public void select_product_categories(String arg1) throws Throwable {		
		driver.get(baseUrl+ "/shop/category/"+arg1);
		Thread.sleep(3000);//!
		System.out.println("Labb4_9 Sort, Given" );
	}

	@When("^I sort product by price$")
	public void i_sort_product_by_price() throws Throwable {
		driver.findElement(By.xpath(".//*[@id='filter']")).click();
		Thread.sleep(1000);//!
		driver.findElement(By.xpath(".//*[@id='filter']/option[3]")).click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 300);");
		Thread.sleep(1000);
		System.out.println("Labb4_9 Sort, When" );
	}

	@Then("^Check first book \"([^\"]*)\"$")
	public void check_first_book(String arg1) throws Throwable {
		WebElement sortBook = driver.findElement(By.xpath(" .//*[@id='productsContainer']/div[1]/div/div[2]/div/div/h3"));
		assertEquals("Programming for PAAS",  sortBook.getText());
		System.out.println("Labb4_9 Sort, Then" );
	}
	
	@After("@selenium9")
	public void tearDown(){
		driver.quit();
	}
}
