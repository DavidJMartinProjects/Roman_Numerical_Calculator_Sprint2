package com.cucumber.stepDefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import domainLayer.CalculationResult;
import ultilities.DriverFactory;
import webLayer.CalculatorWS;

public class CalculatorStepDefinitions {
	
	private final String browserType = "chrome";
	private final WebDriver driver = DriverFactory.open(browserType);
	private boolean acceptNextAlert = true;
	private final StringBuffer verificationErrors = new StringBuffer();

    @Autowired
    private WebDriver webDriver;
    
    @Autowired
    private CalculationResult calculationResult;
    
	@Before
	public void setUp() throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Given("^The browser is on the homepage$")
	public void whenGiven() throws Throwable {
		System.out.println("given");
		driver.get("http://localhost:8080/");
	}
	@When("^I complete action$")
	public void somethingHappens() throws Throwable {
		System.out.println("when");
		driver.findElement(By.id("numOneTf")).click();
		driver.findElement(By.id("numOneTf")).clear();
		driver.findElement(By.id("numOneTf")).sendKeys("XIV");
		driver.findElement(By.id("operationType")).click();
		driver.findElement(By.id("operationType")).click();
		driver.findElement(By.id("numTwoTf")).click();
		driver.findElement(By.id("numTwoTf")).clear();
		driver.findElement(By.id("numTwoTf")).sendKeys("LX");
		driver.findElement(By.id("calcBtn")).click();
		Thread.sleep(1000);
	
	}
	@Given("^I validate the outcomes$")
	public void whenThen() throws Throwable {
		System.out.println("then");
		try {
			assertEquals("Result : XIV + LX = LXXIV", driver.findElement(By.id("result")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		} finally {
			driver.quit();
		}
	}

}
