package testpages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import common.Reporter;

public class SignUpPage {
	private WebDriver driver;
	private Reporter reporter;

	public SignUpPage(WebDriver driver, Reporter reporter) {
		this.driver = driver;
		this.reporter = reporter;
	}
	public WebElement getFirstName() {
		return getWebElement(By.name("firstname"));
	}

	public WebElement getLastName() {
		return getWebElement(By.name("lastname"));
	}
	
	public WebElement getEmail() {
		return getWebElement(By.name("reg_email__"));
	}
	
	public WebElement getEmailConformation() {
		return getWebElement(By.name("reg_email_confirmation__"));
	}
	
	public WebElement getPassword() {
		return getWebElement(By.name("reg_passwd__"));
	}
	
	public Select getSelectDropDownMonth() {
		return  getSelectBox(By.id("month"));
	}
	
	public Select getselectDropDownDay() {
		return  getSelectBox(By.id("day"));
	}
	
	public Select getselectDropDownYear() {
		return  getSelectBox(By.id("year"));
	}
	
	private Select getSelectBox(By selector) {
		WebElement selectBoxElement = getWebElement(selector);
		return  new Select(selectBoxElement);
	}
	
	private WebElement getLogInButton(){
		return getWebElement(By.name("websubmit"));
	}
	
	private WebElement getMaleRadioButton(){
		return getWebElement(By.xpath("//input[@name='sex' and @value='2'] "));
	}
	private WebElement getFemaleRadioButton(){
		return getWebElement(By.xpath("//input[@name='sex' and @value='1'] "));
	}
	
	
	private WebElement getPasswordRequiredPopup(){
		return getWebElement(By.xpath("//*[contains(text(), 'Enter a combination of at least six numbers, ')]"));
	}
	
	//Verification
	
	public void VerifyPasswordRequiredPopupIsVisible(){
		WebElement passwprdPopup = this.getPasswordRequiredPopup();
	
		if(!passwprdPopup.isDisplayed()){
			this.reporter.error("Password require popup is displayed");
		}
		Assert.assertTrue(passwprdPopup.isDisplayed());
		this.reporter.pass("Popup is displayed ");
		
	}
	
	//Actions
	public void SelectBirthDate(String year,String month, String day){
		
		this.getselectDropDownYear().selectByVisibleText(year);
		this.reporter.info("Select year in dropdown " + year);
		this.getSelectDropDownMonth().selectByVisibleText(month);
		this.reporter.info("Select year in dropdown " + month);
		this.getselectDropDownDay().selectByVisibleText(day);
		this.reporter.info("Select year in dropdown " + day);
	
	}
	
	public void SignUp(String firstName, String lastName, String email, String emailConformation,
			String password, String year, String month, String day, boolean isMale){
		
		if(firstName != null || firstName.isEmpty()==true){
			this.getFirstName().sendKeys(firstName);
			this.reporter.info("Enter first name " + firstName+ " in frist name field");
		}
		
		this.getLastName().sendKeys(lastName);
		this.reporter.info("Enter last name " + lastName+ " in last name field");
		this.getEmail().sendKeys(email);
		this.reporter.info("Enter email " + email+ " in frist name field");
		
		this.getEmailConformation().sendKeys(emailConformation);
		this.reporter.info("Enter email conformation " + emailConformation+ "in email conformation field");
		this.getPassword().sendKeys(password);
		this.reporter.info("Enter password in password field");
		this.SelectBirthDate(year, month, day);
		
		if(isMale){
			this.getMaleRadioButton().click();
			
			this.reporter.info("Your gender is male");
		}else{
			getFemaleRadioButton().click();
			this.reporter.info("Your gender is female");
		}
		this.getLogInButton().click();
		this.reporter.info("Click Log in");
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

