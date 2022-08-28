package comNopcommerceCommon;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObjectsNopcommerceUser.PageGeneratorManagerNopcommerce;
import pageObjectsNopcommerceUser.UserHomePageObject;
import pageObjectsNopcommerceUser.UserRegisterPageObject;


public class Common_01_Register_Cookie extends BaseTest{
	
	private WebDriver driver;
	private String firstName, lastName;
	public static String emailAddress, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	
	
	@Parameters("browser")
	@BeforeTest(description = "Create new common user for all classes test")
	public void Register(String browserName) {
		
		driver = getBrowserDriver(browserName);
		
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
		
		log.info("Register - Step 4: Verify register success");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");
		
		log.info("Register - Step 5: Click to logout link");
		homePage = registerPage.clickToLogoutLink();
	
	}

	@AfterTest
	public void afterClass() {
		driver.quit();
	}
	

}