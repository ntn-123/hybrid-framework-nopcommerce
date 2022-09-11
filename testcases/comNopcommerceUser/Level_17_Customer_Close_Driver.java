package comNopcommerceUser;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjectsNopcommerceUser.*;

public class Level_17_Customer_Close_Driver extends BaseTest{
	
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		//driver = null;
		homePage = PageGeneratorManagerNopcommerce.getUserHomePage(driver);

		firstName = "sa";
		lastName = "sa 1";
		emailAddress = "sa" + getRandomNumber() + "@hotmail.net";
		password = "123456";
		
		log.info("Register - Step 1: Click to register link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Register - Step 2: Input customer info");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		log.info("Register - Step 3: Click to register button");
		registerPage.clickToRegisterButton();
		
		//driver = null;
		
		log.info("Register - Step 4: Verify register success");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Register - Step 5: Click to logout link");
		homePage = registerPage.clickToLogoutLink();
		
		log.info("Login - Step 01: Click to login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login - Step 02: Input existing email and password");
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Login - Step 03: Click to login button");
		homePage = loginPage.clickToLoginButton();
		
		log.info("Login - Step 04: Verify login success");
		verifyFalse(homePage.isMyAccountLinkDisplayed());
		
	}
	
	@Test
	public void User_01_Register_Login() {
		
	
	}

	@Test
	public void User_02_Login() {
		
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
		//driver.quit();
	}
	

}
