package comNopcommerceUser;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjectsNopcommerceUser.*;

public class Level_14_Log_ReportNG extends BaseTest{
	
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		homePage = PageGeneratorManagerNopcommerce.getUserHomePage(driver);

		firstName = "sa";
		lastName = "sa 1";
		emailAddress = "sa" + getRandomNumber() + "@hotmail.net";
		password = "123456";
		
	}
	
	@Test
	public void User_01_Register_Login() {
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
		
		log.info("Register - Step 4: Verify register success");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");
		
		log.info("Register - Step 5: Click to logout link");
		homePage = registerPage.clickToLogoutLink();
	
	}

	@Test
	public void User_02_Login() {
		log.info("Login - Step 01: Click to login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login - Step 02: Input existing email and password");
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Login - Step 03: Click to login button");
		homePage = loginPage.clickToLoginButton();
		
		log.info("Login - Step 04: Verify login success");
		verifyFalse(homePage.isMyAccountLinkDisplayed());
	
		log.info("Customer info - Step 01: Click to my account link");
		customerInfoPage = homePage.clickToMyAccountLink();
		
		log.info("Customer info - Step 02: Verify customer info page display");
		verifyFalse(customerInfoPage.isCustomerInfoPageDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
