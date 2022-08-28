package comNopcommerceUser;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObjectsNopcommerceUser.PageGeneratorManagerNopcommerce;
import pageObjectsNopcommerceUser.UserHomePageObject;
import pageObjectsNopcommerceUser.UserLoginPageObject;
import pageObjectsNopcommerceUser.UserRegisterPageObject;


public class Level_15_Allure extends BaseTest{
	
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	
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
	
	@Description("Register to system")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void User_01_Register() {
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Description("Login to system")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void User_02_Login() {
		homePage = registerPage.clickToLogoutLink();
		loginPage = homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		
		homePage = loginPage.clickToLoginButton();
		
		Assert.assertFalse(homePage.isMyAccountLinkDisplayed());
	
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}