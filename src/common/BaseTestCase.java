package common;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import testpages.HomePage;
import testpages.InvalidLogInPage;
import testpages.LogInPage;
import testpages.SignUpPage;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTestCase {
	private WebDriver driver;
	private Reporter reporter;
	private ExtentReports extent;
	private  static long timestamp = new Date().getTime();

	protected LogInPage logInPage;
	protected HomePage homePage;
	protected InvalidLogInPage invalidLogInPage;
	protected SignUpPage signUpPage;



	@BeforeClass
	public void beforeClass() {
		this.extent = new ExtentReports(getReportFilePatch(), false);
		this.reporter = new Reporter();

	}

	private String getReportFilePatch() {
		
		String currentFoilder = Paths.get("").toAbsolutePath().toString(); // prazniq strin  oznachava dai mi tekushtiq folder
		String reportPath  = currentFoilder + "\\Report-"+timestamp+".html";
		File reportFile = new File(reportPath);
		return reportPath;
	}

	@BeforeMethod
	public void beforeMethod(Method testCase) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");
		this.driver = new ChromeDriver();
		
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.initTestPages();
		ExtentTest currentTest = this.extent.startTest(testCase.getName());
		this.reporter.setTest(currentTest);

	}

	private void initTestPages() {
		this.homePage = new HomePage(this.driver, this.reporter);
		this.invalidLogInPage = new InvalidLogInPage(this.driver, this.reporter);
		this.logInPage = new LogInPage(this.driver, reporter);
		this.signUpPage = new SignUpPage(this.driver, this.reporter);
	}

	@AfterMethod
	public void afterMethod() {
		ExtentTest currentTest = this.reporter.getTest();
		// end test
		extent.endTest(currentTest);

		// calling flush writes everything to the log file
		extent.flush();

		if (this.driver != null) {
			this.driver.quit();
		} else {
			this.reporter.error("Driver is not initialized");
		}
	}

	protected void open(String url) {
		if (this.driver != null) {
			this.driver.get(url);
		} else {
			this.reporter.error("Driver is not initialized");
		}

	}
}
