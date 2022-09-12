package comWordpressAdmin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
	private int randomNumber = getRandomNumber();
	private String addNewPostTitle = "Live Coding Title" + randomNumber;
	private String addNewPostBody = "Live Coding Body" + randomNumber;
	private String author = "automationfc";
	
	@Parameters({"browser", "urlAdmin"})
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl) {
		log.info("Pre-condition - Step 01: Open browser and admin site");
		driver = getBrowserDriver(browserName, adminUrl);
		adminLoginPage = AdminPageGeneratorManager.getAdminLoginPage(driver);
		
		log.info("Pre-condition - Step 02: Enter to Username text box with value " + adminUsername);
		adminLoginPage.enterToUsernameTextbox(adminUsername);
		
		log.info("Pre-condition - Step 03: Enter to Password text box with value " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);
		
		log.info("Pre-condition - Step 04: Click to Login button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();
	}
	
	@Test
	public void Post_01_Create_New_Post() {
		log.info("Create_Post - Step 01: Click to 'Posts' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();

		log.info("Create_Post - Step 02: Get 'Search Posts' page Url");
		searchPostUrl = adminPostSearchPage.getPageUrl(driver);
		
		log.info("Create_Post - Step 03: Click to 'Add new' button");
		adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton();
		
		log.info("Create_Post - Step 3-2: Click to close button at popup");
		adminPostAddNewPage.sleepInSecond(3);
		adminPostAddNewPage.clickToCloseButtonAtPopup();
		
		log.info("Create_Post - Step 04: Enter to post title");
		adminPostAddNewPage.enterToAddNewPostTitle(addNewPostTitle);
		
		log.info("Create_Post - Step 05: Enter to post body");
		adminPostAddNewPage.enterToAddNewPostBody(addNewPostBody);
		
		log.info("Create_Post - Step 06: Click to 'Publish' button");
		adminPostAddNewPage.clickToPublishButton();
		
		log.info("Create_Post - Step 07: Click to 'Pre-Publish' button");
		adminPostAddNewPage.clickToPrePublishButton();
		
		log.info("Create_Post - Step 08: Verify 'Post updated.' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post published."));
	}
	
	@Test
	public void Post_02_Search_Post() {
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
		
		log.info("Search_Post - Step 02: Enter to search post textbox");
		//adminPostSearchPage.enterToSearchPostTextbox(addNewPostTitle);
		
		log.info("Search_Post - Step 03: Click to 'Search Posts' button");
		//adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Search_Post - Step 04: Verify search table contain '" + addNewPostTitle + "'");
		//verifyEquals(adminPostSearchPage, addNewPostTitle);
		log.info("Search_Post - Step 05: Verify search table contain '" + author + "'");
		
		log.info("Search_Post - Step 06: Open End User Site");
		log.info("Search_Post - Step 07: Verify Post info displayed at Home page");
		log.info("Search_Post - Step 08: Click to Post tittle");
		log.info("Search_Post - Step 09: Verify Post info displayed at Post detail page");
	}
	
	//@Test
	public void Post_03_View_Post() {
		
	}
	
	//@Test
	public void Post_04_Edit_Post() {
		
	}
	
	//@Test
	public void Post_05_Delete_Post() {
		
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	
}
