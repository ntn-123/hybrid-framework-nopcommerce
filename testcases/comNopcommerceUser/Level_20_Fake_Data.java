package comNopcommerceUser;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjectsNopcommerceUser.*;
import utilities.DataHelper;

public class Level_20_Fake_Data extends BaseTest{
	
	private WebDriver driver;
	private DataHelper dataFaker;
	private String firstName, lastName, emailAddress, password;
	private String date, month, year, gender;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManagerNopcommerce.getUserHomePage(driver);

		dataFaker = new DataHelper();
		firstName = dataFaker.getFirstName();
		lastName = dataFaker.getLastName();
		emailAddress = dataFaker.getEmailAddress();
		password = dataFaker.getPassword();
		date = "10";
		month = "September";
		year = "1998";
		gender = "Female";
	}
	
	@Test
	public void User_01_Register_Login() {

		log.info("Register - Step 1: Click to register link");
		registerPage = homePage.clickToRegisterLink();
		
		System.out.println("Register - Step 2: Input customer info with email = " + emailAddress + " password = " + password);
		registerPage.checkToRadioButtonByLabel(driver, gender);
		registerPage.inputTextboxByID(driver, "FirstName", firstName);
		registerPage.inputTextboxByID(driver, "LastName", lastName);
		registerPage.selectToDropdownByAttributeName(driver, "DateOfBirthDay", date);
		registerPage.selectToDropdownByAttributeName(driver, "DateOfBirthMonth", month);
		registerPage.selectToDropdownByAttributeName(driver, "DateOfBirthYear", year);
		registerPage.inputTextboxByID(driver, "Email", emailAddress);
		registerPage.checkToCheckboxByLabel(driver, "Newsletter");
		registerPage.inputTextboxByID(driver, "Password", password);
		registerPage.inputTextboxByID(driver, "ConfirmPassword", password);
		
		log.info("Register - Step 3: Click to register button");
		registerPage.clickToButtonByText(driver, "Register");
		
		
		log.info("Register - Step 4: Verify register success");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Register - Step 5: Click to logout link");
		homePage = registerPage.clickToLogoutLink();
	
	}

	@Test
	public void User_02_Login() {
		log.info("Login - Step 01: Click to login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login - Step 02: Input existing email and password");
		loginPage.inputTextboxByID(driver, "Email", emailAddress);
		loginPage.inputTextboxByID(driver, "Password", password);
		
		log.info("Login - Step 03: Click to login button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManagerNopcommerce.getUserHomePage(driver);
		
		log.info("Login - Step 04: Verify login success");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
		
	}
	
	@Test
	public void User_03_My_Account() {
		log.info("MyAccount - Step 01: Click to My Account link");
		customerInfoPage = homePage.clickToMyAccountLink();
		
		log.info("My account - Step 02: Verify Customer Info page is displayed");
		verifyTrue(customerInfoPage.isCustomerInfoPageDisplayed());
		
		log.info("My account - Step 03: Verify 'First Name' value is corrrectly");
		Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "FirstName"), firstName);
		
		log.info("My account - Step 03: Verify 'Last Name' value is corrrectly");
		Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "LastName"), lastName);
		
		log.info("My account - Step 03: Verify 'Email' value is corrrectly");
		Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "Email"), emailAddress);
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		//closeBrowserAndDriver();
	}
	

}
