package testpages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import common.Reporter;

public class HomePage {
	private WebDriver driver;
	private Reporter reporter;

	public HomePage(WebDriver driver, Reporter reporter) {
		this.driver = driver;
		this.reporter = reporter;
	}

	// Element
	public WebElement getLogInUserName() {
		return getWebElement(By.className("_2dpb"));
	}

	// Verify
	public void VerifyLogInUserName(String userName) {
		try {
			String actualUsernameText = this.getLogInUserName().getText();
			Assert.assertEquals(actualUsernameText, userName, "User is not found");
			this.reporter.pass("User profile name is "+ userName +"as expected" );
		} catch (NoSuchElementException ex) {
			this.reporter.error("Profie name element not found!");
		} catch (Exception e) {
			this.reporter.error("Can't verify logged in username! ");
		}

	}

	public void VerifyProfileNameIsVisible() {
		try {

			WebElement username = this.getLogInUserName();
			if(!username.isDisplayed()){
				this.reporter.error("Profile name lable is not displayed");
			}
			Assert.assertTrue(username.isDisplayed());
			this.reporter.pass("Profile name lable is displayed ");
			
		} catch (NoSuchElementException ex) {
			this.reporter.error("Profie name element not found!");
		} catch (Exception e) {
			this.reporter.error("Can't verify logged in username! "
					+ e.getMessage());
		}

	}

	private WebElement getWebElement(By selector) {
		WebElement webElement = null;
		if (this.driver != null) {
			webElement = this.driver.findElement(selector);
		} else {
			this.reporter.error("Driver is not inializated!");
		}
		return webElement;
	}

}
