package comNopcommerceUser;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import comNopcommerceCommon.Common_01_Register_Cookie;
import comNopcommerceCommon.Common_01_Register_End_User;
import commons.BaseTest;
import pageObjectsNopcommerceUser.PageGeneratorManagerNopcommerce;
import pageObjectsNopcommerceUser.UserHomePageObject;
import pageObjectsNopcommerceUser.UserLoginPageObject;


public class Level_16_Share_Data_Cookie extends BaseTest{
	
	private WebDriver driver;
	private String emailAddress, password;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		homePage = PageGeneratorManagerNopcommerce.getUserHomePage(driver);

		emailAddress = Common_01_Register_End_User.emailAddress;
		password = Common_01_Register_End_User.password;
		
		log.info("Login - Step 01: Click to login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login - Step 02: Set cookie and reload page");
		loginPage.setCookie(driver, Common_01_Register_Cookie.loggedCookies);
		
		loginPage.refreshCurrentPage(driver);
		
		log.info("Login - Step 03: Verify login success");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}
	
	@Test
	public void Search_01_Empty_Data() {
		
	}
	
	@Test
	public void Search_02_Relative_Product_Name() {
		
	}
	
	@Test
	public void Search_03_Absolute_Product_Name() {
		
	}
	
	@Test
	public void Search_04_Parent_Category() {
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}