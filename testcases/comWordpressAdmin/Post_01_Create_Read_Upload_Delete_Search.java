package comWordpressAdmin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjectsWordpressAdmin.AdminDashboardPO;
import pageObjectsWordpressAdmin.AdminLoginPO;
import pageObjectsWordpressAdmin.AdminPageGeneratorManager;
import pageObjectsWordpressAdmin.AdminPostAddNewPO;
import pageObjectsWordpressAdmin.AdminPostSearchPO;

public class Post_01_Create_Read_Upload_Delete_Search extends BaseTest{
	private WebDriver driver;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPage;
	private AdminPostSearchPO adminPostSearchPage;
	private AdminPostAddNewPO adminPostAddNewPage;
	private String adminUsername = "automationfc";
	private String adminPassword = "automationfc";
	private String searchPostUrl;
	private String addNewPostTitle = "";
	private String addNewPostBody = "";
	
	@Parameters({"browser", "urlAdmin"})
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl) {
		log.info("Pre-condition - Step 01: Open browser and admin Url");
		driver = getBrowserDriver(browserName, adminUrl);
		adminLoginPage = AdminPageGeneratorManager.getAdminLoginPage(driver);
		
		log.info("Pre-condition - Step 02: Enter to Username text box with value " + adminUsername);
		adminLoginPage.enterToUsernameTextbox(adminUsername);
		
		log.info("Pre-condition - Step 03: Enter to Password text box with value " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);
		
		log.info("Pre-condition - Step 04: Click to Login button");
		adminLoginPage.clickToLoginButton();
		
		adminDashboardPage = AdminPageGeneratorManager.getAdminDashboardPage(driver);
		
	}
	
	@Test
	public void Post_01_Create_New_Post() {
		log.info("Create_Post - Step 01: Click to 'Posts' menu link");
		searchPostUrl = "";
		adminDashboardPage.clickToPostMenuLink();
		adminPostSearchPage = AdminPageGeneratorManager.getAdminPostSearchPage(driver);
		
		log.info("Create_Post - Step 02: Click to 'Add new' button");
		adminPostSearchPage.clickToAddNewButton();
		adminPostAddNewPage = AdminPageGeneratorManager.getAdminAddNewPage(driver);
		
		log.info("Create_Post - Step 03: Enter to post title");
		adminPostAddNewPage.enterToAddNewPostTitle(addNewPostTitle);
		
		log.info("Create_Post - Step 04: Enter to post body");
		adminPostAddNewPage.enterToAddNewPostBody(addNewPostBody);
		
		log.info("Create_Post - Step 05: Click to 'Publish' button");
		adminPostAddNewPage.clickToPublishButton();
		
		log.info("Create_Post - Step 06: Verify 'Post updated.' message is displayed");
		Assert.assertTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post updated."));
	}
	
	@Test
	public void Post_02_Search_Post() {
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
		adminPostSearchPage = AdminPageGeneratorManager.getAdminPostSearchPage(driver);
	}
	
	@Test
	public void Post_03_View_Post() {
		
	}
	
	@Test
	public void Post_04_Edit_Post() {
		
	}
	
	@Test
	public void Post_05_Delete_Post() {
		
	}
	
	
}
