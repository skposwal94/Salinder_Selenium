package com.Salinder.learningmaven.LearningSelenium;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegistartionPurchaseLogout {

	WebDriver driver;

	@BeforeTest
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chrome driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(priority = 1)
	public void Registration() throws InterruptedException {

		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");

		WebElement firstNameInputField = driver.findElement(By.id("input-firstname"));
		firstNameInputField.sendKeys("FirstName");

		WebElement lastNameInputField = driver.findElement(By.cssSelector("#input-lastname"));
		lastNameInputField.sendKeys("LastName");

		Random rnd = new Random();
		String randomEmail = "abc" + rnd.nextInt(99999) + "@gmail.com";

		WebElement emailInputField = driver.findElement(By.cssSelector("#input-email"));
		emailInputField.sendKeys(randomEmail);

		WebElement telephoneInputField = driver.findElement(By.cssSelector("#input-telephone"));
		telephoneInputField.sendKeys("909-909-9099");

		WebElement passwordInputField = driver.findElement(By.cssSelector("#input-password"));
		passwordInputField.sendKeys("PasswordReloaded");

		WebElement confirmPasswordInputField = driver.findElement(By.cssSelector("#input-confirm"));
		confirmPasswordInputField.sendKeys("PasswordReloaded");

		WebElement privacyOption = driver.findElement(By.cssSelector("input[type=\"checkbox\"]"));
		privacyOption.click();

		WebElement continueButton = driver.findElement(By.cssSelector("input[value=\"Continue\"]"));
		continueButton.click();

		///// ASSERTING REGISTARTION ////////////
		WebElement LoginConfirmation = driver.findElement(By.cssSelector("div#content h1"));
		String LoginConfirmationMessage = LoginConfirmation.getText();
		System.out.println(LoginConfirmationMessage);

		Assert.assertEquals(LoginConfirmationMessage, "Your Account Has Been Created!", "Not Registered");

	}

	@Test(priority = 2, dependsOnMethods = "Registration", enabled = true)
	public void purchasingAndCheckOut() throws InterruptedException {

		// continue button after registration
		WebElement continueButton = driver.findElement(By.cssSelector("a[class=\"btn btn-primary\"]"));
		continueButton.click();

		// selecting camera option from the top
		WebElement cameraOption = driver.findElement(By.cssSelector(
				"a[href=\"https://naveenautomationlabs.com/opencart/index.php?route=product/category&path=33\"]"));
		cameraOption.click();

		// adding Nikon D300 to the cart
		WebElement addToCartButton = driver.findElement(By.cssSelector("button[onclick=\"cart.add('31', '1');\"]"));
		addToCartButton.click();

		// Asserting if added to cart successful
		WebElement adddedToCartSuccess = driver
				.findElement(By.cssSelector("div.alert.alert-success  a:nth-of-type(1)"));
		String adddedToCartSuccessText = adddedToCartSuccess.getText();
		System.out.println("Added to cart " + adddedToCartSuccessText);
		Assert.assertEquals(adddedToCartSuccessText, "Nikon D300", "Not added to Cart");

		// Clicking check out button in top right corner
		WebElement checkOutButton = driver
				.findElement(By.cssSelector("a[title = \"Checkout\"] span[class=\"hidden-xs hidden-sm hidden-md\"]"));
		checkOutButton.click();

		// Filling out step 1 of Billing details
		driver.findElement(By.cssSelector("input#input-payment-firstname")).sendKeys("FirstName");
		driver.findElement(By.cssSelector("input#input-payment-lastname")).sendKeys("LastName");
		driver.findElement(By.cssSelector("input#input-payment-company")).sendKeys("CompanyReloaded");
		driver.findElement(By.cssSelector("input#input-payment-address-1")).sendKeys("This is Address Line 1");
		driver.findElement(By.cssSelector("input#input-payment-address-2")).sendKeys("This is Address Line 2");
		driver.findElement(By.cssSelector("input#input-payment-city")).sendKeys("CityReloaded");
		driver.findElement(By.cssSelector("input#input-payment-postcode")).sendKeys("P .O. CODE");

		// Country and state
		WebElement countryList = driver.findElement(By.cssSelector("select#input-payment-country"));
		Select country = new Select(countryList);
		country.selectByVisibleText("Turkmenistan");

		WebElement RegionStateList = driver.findElement(By.cssSelector("select#input-payment-zone"));
		Select RegionState = new Select(RegionStateList);
		RegionState.selectByVisibleText("Dashhowuz Welayaty");
		RegionState.selectByValue("3399");

		// Continue billing details
		driver.findElement(By.cssSelector("input#button-payment-address")).click();

		// Continue delivery details
		driver.findElement(By.cssSelector("input#button-shipping-address")).click();

		// Continue delivery method
		driver.findElement(By.cssSelector("input#button-shipping-method")).click();

		// Terms and condition method and continue
		driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();
		driver.findElement(By.cssSelector("input#button-payment-method")).click();

		// confirm order
		driver.findElement(By.cssSelector("input#button-confirm")).click();

		Thread.sleep(2000);
		driver.navigate().refresh();

		///////////// Asserting order confirmations//////

		WebElement orderPlacedMessage = driver.findElement(By.cssSelector("div#content h1"));
		String confirmationMessage = orderPlacedMessage.getText();
		System.out.println("This is the message: " + confirmationMessage);
		Assert.assertEquals(confirmationMessage, "Your order has been placed!", "Order not placed");

		// continue
		WebElement continueButtonHome = driver.findElement(By.cssSelector("div.buttons div.pull-right a"));
		continueButtonHome.click();

	}

	@Test(priority = 3, dependsOnMethods = { "Registration", "purchasingAndCheckOut" }, enabled = true)
	public void LogOut() throws InterruptedException {

		// clicking on checkout button than log out
		driver.findElement(By.cssSelector("li.dropdown span")).click();
		driver.findElement(By.cssSelector("li.dropdown ul.dropdown-menu li:nth-of-type(5) a")).click();

		//// Asserting logout confirmation ///

		Thread.sleep(2000);
		driver.navigate().refresh();

		String logOutConfirmation = driver.findElement(By.cssSelector("div#content h1")).getText();
		Assert.assertEquals(logOutConfirmation, "Account Logout", "You are still logged in");

		WebElement continueButtonHome = driver.findElement(By.cssSelector("div.buttons div.pull-right a"));
		continueButtonHome.click();

	}

	@AfterTest
	public void teardown() throws InterruptedException {

		Thread.sleep(2000);
//		driver.close();
	}

}
