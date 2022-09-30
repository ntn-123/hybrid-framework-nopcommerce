package comWordpressAdmin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjectsWordpress.AdminDashboardPO;
import pageObjectsWordpress.AdminLoginPO;
import pageObjectsWordpress.AdminUserPO;
import pageObjectsWordpress.PageGeneratorManager;

public class User_01_View_User extends BaseTest{
	@Parameters({"browser", "urlAdmin"})
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl) {
		log.info("Pre-condition - Step 01: Open browser and admin site");
		driver = getBrowserDriver(browserName, adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		log.info("Pre-condition - Step 02: Enter to Username text box with value " + adminUsername);
		adminLoginPage.enterToUsernameTextbox(adminUsername);
		
		log.info("Pre-condition - Step 03: Enter to Password text box with value " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);
		
		log.info("Pre-condition - Step 04: Click to Login button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();
	}
	
	@Test
	public void TC_01_View_User() {
		log.info("TC_01 - Step 01: Click to 'Users' menu link");
		adminUserPage = adminDashboardPage.clickToUsersLink();
		
		log.info("TC_01 - Step 02: Get all users from UI");
		int totalUserAtUI = adminUserPage.getUserNumberAtUI();
		
		log.info("TC_01 - Step 03: Get all users from DB");
		int totalUserAtDB = adminUserPage.getUserNumberAtDB();
		
		log.info("TC_01 - Step 04: Verify the User are matching");
		verifyEquals(totalUserAtUI, totalUserAtDB);
		
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	private WebDriver driver;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPage;
	private AdminUserPO adminUserPage;
	private String adminUsername = "automationfc";
	private String adminPassword = "automationfc";
	
	
}
