package testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Reporter;

public class LogInPage {

	private WebDriver driver;
	private Reporter reporter;

	public LogInPage(WebDriver driver, Reporter reporter) {
		this.driver = driver;
		this.reporter = reporter;

	}

	public WebElement getEmailTextBox() {
		return getWebElement(By.id("email"));
	}

	public WebElement getPasswordTextBox() {
		return getWebElement(By.id("pass"));
	}

	public WebElement getLoginButton() {
		return getWebElement(By.id("loginbutton"));
	}

	// Actions
	public void LogIn(String email, String pass) {

		this.getEmailTextBox().sendKeys( email);
		this.reporter.info("Enter email "+ email + "in email field");
		this.getPasswordTextBox().sendKeys(pass);
		this.reporter.info("Enter password in password field");
		this.getLoginButton().click();
		this.reporter.info("Click the login button");
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
