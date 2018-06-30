package com.selenium.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.web.WebAppConfiguration;

import domainLayer.CalculationResult;
import ultilities.DriverFactory;	
import webLayer.CalculatorWS;

@SpringBootTest(classes=CalculatorWS.class)
@ComponentScan(basePackages = { "domainLayer", "businessLayer", "businessLayer.api", "businessLayer.operations", "webLayer", "utils" })
@WebAppConfiguration

public class SeleniumTest {
	
	@Test
	public void myTest() {
		
	}
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


	@Test
	public void testAddition() throws Exception {
		driver.get("http://localhost:8080/");
		driver.findElement(By.id("numOneTf")).click();
		driver.findElement(By.id("numOneTf")).clear();
		driver.findElement(By.id("numOneTf")).sendKeys("XIV");
		driver.findElement(By.id("operationType")).click();
		driver.findElement(By.id("operationType")).click();
		driver.findElement(By.id("numTwoTf")).click();
		driver.findElement(By.id("numTwoTf")).clear();
		driver.findElement(By.id("numTwoTf")).sendKeys("LX");
		driver.findElement(By.id("calcBtn")).click();
		try {
			assertEquals("Result : XIV + LX = LXXIV", driver.findElement(By.id("result")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}


	@Test
	public void testVerifyErrorMessage() throws Exception {
		driver.get("http://localhost:8080/");
		driver.findElement(By.id("calcBtn")).click();
		try {
			assertEquals("Please Input a Roman Numeral.", driver.findElement(By.id("validationMessage")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void testVerifyInvalidInputMsg() throws Exception {
		driver.get("http://localhost:8080/");
		driver.findElement(By.id("numOneTf")).click();
		driver.findElement(By.id("numOneTf")).clear();
		driver.findElement(By.id("numOneTf")).sendKeys("23");
		driver.findElement(By.id("numTwoTf")).click();
		driver.findElement(By.id("numTwoTf")).clear();
		driver.findElement(By.id("numTwoTf")).sendKeys("23");
		driver.findElement(By.id("calcBtn")).click();
		try {
			assertEquals("Invalid Roman Numerical Format.", driver.findElement(By.id("validationMessage")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
