package testpages;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Reporter;

public class InvalidLogInPage {
	private WebDriver driver;
	private Reporter reporter;

	public InvalidLogInPage(WebDriver driver, Reporter reporter) {
		this.driver = driver;
		this.reporter = reporter;
	}
	
	public WebElement forgotPasswordLink() {
		return getWebElement(By.linkText("Forgot password?"));
	}
	
	//Verification
	public void VerifyForgotPasswordLinkExist(){
		try {
			
			WebElement link = this.forgotPasswordLink();
			
			if(!link.isDisplayed()){
				this.reporter.error("Link is displayed");
			}
			Assert.assertTrue(link.isDisplayed());
			this.reporter.pass("Link is displayed ");
			
			
		} catch (NoSuchElementException e) {
			this.reporter.error("Forgot Link doesn't exist");
		} catch (Exception e) {
			this.reporter.error("Verification of forgot Link doesn't exist");
			
		}
	}
	
	//Actions
	

	
	
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
