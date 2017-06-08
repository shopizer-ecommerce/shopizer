package com.salesmanager.cucumberak;
 
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/com/salesmanager/cucumberak/"
		,glue={"classpath:"}
		)
 
public class RunTest {
 
}