package test;


import org.testng.annotations.Test;

import common.BaseTestCase;

public class MyTestFirstClass extends BaseTestCase {


//	@Test(enabled=false)
//	public void test0() {
//
//		open("https://facebook.com");
//		
//		logInPage.getEmailTextBox().sendKeys("test@email.com");
//		driver.findElement(By.name("pass")).sendKeys("PASS");
//		driver.findElement(By.partialLinkText("Why do I need to ")).click();
//		logInPage.getEmailTextBox().sendKeys("test2");
//		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("test3");
//		Select selectDropDown = new Select (driver.findElement(By.id("month")));
//		selectDropDown.selectByValue("12");
//	}
//
	//@Test(enabled=false)
	@Test
	public void SighnUpTest() {
		
		open("https://facebook.com");
		
		signUpPage.SignUp("Ivan", "Ivanov", "ivan@is-bg.net", "ivan@is-bg.net", "", "2007", "Dec", "19", true);		
//		driver.findElement(By.cssSelector("#u_0_b")).sendKeys("test");
//		driver.findElement(By.name("lastname")).sendKeys("user");
//		logInPage.getEmailTextBox().sendKeys("test3");
//		driver.findElement(By.name("reg_email__")).sendKeys("test@user.com");
//		driver.findElement(By.name("reg_email_confirmation__")).sendKeys("test@user.com");
//		
//		Select selectDropDownMonth = new Select (driver.findElement(By.id("month")));
//		selectDropDownMonth.selectByValue("3");
//		Select selectDropDownDay = new Select (driver.findElement(By.id("day")));
//		selectDropDownDay.selectByValue("3");
//		Select selectDropDownYear = new Select (driver.findElement(By.id("year")));
//		selectDropDownYear.selectByValue("2007");
//		
//		driver.findElement(By.xpath("//input[@name='sex' and @value='2'] ")).click();
//		driver.findElement(By.name("websubmit")).click();
//		
//		driver.findElement(By.xpath("//*[contains(text(), 'Enter a combination of at least six numbers, ')]")).isDisplayed();
		
		signUpPage.VerifyPasswordRequiredPopupIsVisible();
	}
	
	@Test
	public void ValidLogIn() {

		open("https://facebook.com");
			
		logInPage.LogIn("qademouser99@gmail.com", "one+two=3");
		
		homePage.VerifyLogInUserName("John");
		homePage.VerifyProfileNameIsVisible();
	}
	
	//@Test(enabled=false)
	@Test
	public void InvalidLogIn() {

		open("https://facebook.com");
			
		logInPage.LogIn("qademouser99@gmail.com", "one+two=4");
		invalidLogInPage.VerifyForgotPasswordLinkExist();
	}
	
}
