package comWordpressAdmin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjectsWordpress.AdminDashboardPO;
import pageObjectsWordpress.AdminLoginPO;
import pageObjectsWordpress.AdminPostAddNewPO;
import pageObjectsWordpress.AdminPostSearchPO;
import pageObjectsWordpress.PageGeneratorManager;
import pageObjectsWordpress.UserHomePO;
import pageObjectsWordpress.UserPostDetailPO;

public class Post_01_Create_Read_Upload_Delete_Search extends BaseTest{
	@Parameters({"browser", "urlAdmin", "urlUser"})
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String endUserUrl) {
		log.info("Pre-condition - Step 01: Open browser and admin site");
		this.adminUrl = adminUrl;
		this.endUserUrl = endUserUrl;
		driver = getBrowserDriver(browserName, this.adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
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
		adminPostAddNewPage.enterToAddNewPostTitle(postTitle);
		
		log.info("Create_Post - Step 05: Enter to post body");
		adminPostAddNewPage.enterToAddNewPostBody(postBody);
		
		log.info("Create_Post - Step 06: Click to 'Publish' button");
		adminPostAddNewPage.clickToPublishButton();
		
		log.info("Create_Post - Step 07: Click to 'Pre-Publish' button");
		adminPostAddNewPage.clickToPrePublishButton();
		
		log.info("Create_Post - Step 08: Verify 'Post updated.' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post published."));
	}
	
	@Test
	public void Post_02_Search_And_View_Post() {
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
		
		log.info("Search_Post - Step 02: Enter to search post textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Search_Post - Step 03: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Search_Post - Step 04: Verify search table contain '" + postTitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title", postTitle));
		
		log.info("Search_Post - Step 05: Verify search table contain '" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));
		
		log.info("Search_Post - Step 06: Open End User Site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Search_Post - Step 07: Verify Post info displayed at Home page");
		verifyTrue(userHomePage.isPostInfoDisplayedWithPostTitle(postTitle));
		verifyTrue(userHomePage.isPostInfoDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userHomePage.isPostInfoDisplayedWithAuthor(postTitle, authorName));
		verifyTrue(userHomePage.isPostInfoDisplayedWithCurrentDay(postTitle, currentDay));
		
		log.info("Search_Post - Step 08: Click to Post tittle");
		userPostDetailPage = userHomePage.clickToPostTitle(postTitle);
		
		log.info("Search_Post - Step 09: Verify Post info displayed at Post detail page");
		verifyTrue(userPostDetailPage.isPostInfoDisplayed(postTitle));
		verifyTrue(userPostDetailPage.isPostInfoDisplayed(postBody));
		verifyTrue(userPostDetailPage.isPostInfoDisplayed(authorName));
		verifyTrue(userPostDetailPage.isPostInfoDisplayed(currentDay));
	}
	
	//@Test
	public void Post_03_Edit_Post() {
		
	}
	
	//@Test
	public void Post_04_Delete_Post() {
		
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	private WebDriver driver;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPage;
	private AdminPostSearchPO adminPostSearchPage;
	private AdminPostAddNewPO adminPostAddNewPage;
	private UserHomePO userHomePage;
	private UserPostDetailPO userPostDetailPage;
	private String adminUsername = "automationfc";
	private String adminPassword = "automationfc";
	private String searchPostUrl;
	private int randomNumber = getRandomNumber();
	private String postTitle = "Live Coding Title" + randomNumber;
	private String postBody = "Live Coding Body" + randomNumber;
	private String authorName = "Automation Admin";
	private String adminUrl, endUserUrl;
	private String currentDay = getCurrentDay();
	
	
}
