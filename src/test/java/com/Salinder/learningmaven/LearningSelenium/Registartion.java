package com.Salinder.learningmaven.LearningSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Registartion {

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chrome driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

	}

	@Test
	public void test() {

		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");

		WebElement firstNameInputField = driver.findElement(By.cssSelector("#input-firstname"));
		firstNameInputField.sendKeys("FirstName");

		WebElement lastNameInputField = driver.findElement(By.cssSelector("#input-lastname"));
		lastNameInputField.sendKeys("LastName");

		WebElement emailInputField = driver.findElement(By.cssSelector("#input-email"));
		emailInputField.sendKeys("abc@xyz.com");

		WebElement telephoneInputField = driver.findElement(By.cssSelector("#input-telephone"));
		telephoneInputField.sendKeys("909-909-9099");

		WebElement passwordInputField = driver.findElement(By.cssSelector("#input-password"));
		passwordInputField.sendKeys("PasswordReloaded");

		WebElement confirmPasswordInputField = driver.findElement(By.cssSelector("#input-confirm"));
		confirmPasswordInputField.sendKeys("PasswordReloaded");

		WebElement continueButton = driver.findElement(By.cssSelector("input[value=\"Continue\"]"));
		continueButton.click();

		WebElement privacyWarning = driver.findElement(By.cssSelector("div.alert "));
		String warningMessage = privacyWarning.getText();
		System.out.println(warningMessage);

		Assert.assertEquals(warningMessage, "Warning: You must agree to the Privacy Policy!", "Not mached warning");

	}

	@AfterMethod
	public void teardown() throws InterruptedException {

		Thread.sleep(2000);
		driver.close();
	}

}
