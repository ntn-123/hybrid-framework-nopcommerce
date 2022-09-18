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
import pageObjectsWordpress.AdminPostAddNewOrUpdatePO;
import pageObjectsWordpress.AdminPostSearchPO;
import pageObjectsWordpress.PageGeneratorManager;
import pageObjectsWordpress.UserHomePO;
import pageObjectsWordpress.UserPostDetailPO;
import pageObjectsWordpress.UserPostSearchPO;

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
		adminPostAddNewOrUpdatePage = adminPostSearchPage.clickToAddNewButton();
		
		log.info("Create_Post - Step 3-2: Click to close button at popup");
		adminPostAddNewOrUpdatePage.sleepInSecond(3);
		adminPostAddNewOrUpdatePage.clickToCloseButtonAtPopup();
		
		log.info("Create_Post - Step 04: Enter to post title");
		adminPostAddNewOrUpdatePage.enterToAddNewOrUpdatePostTitle(postTitle);
		
		log.info("Create_Post - Step 05: Enter to post body");
		adminPostAddNewOrUpdatePage.clickToAddNewPostBody();
		
		log.info("Create_Post - Step 06: Enter to post body");
		adminPostAddNewOrUpdatePage.enterToAddNewOrUpdatePostBody(postBody);
		
		log.info("Create_Post - Step 07: Click to 'Publish' button");
		adminPostAddNewOrUpdatePage.clickToPublishOrUpdateButton();
		
		log.info("Create_Post - Step 08: Click to 'Pre-Publish' button");
		adminPostAddNewOrUpdatePage.clickToPrePublishButton();
		
		log.info("Create_Post - Step 09: Verify 'Post published.' message is displayed");
		verifyTrue(adminPostAddNewOrUpdatePage.isPostPublishMessageDisplayed("Post published."));
		
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewOrUpdatePage.openSearchPostPageUrl(searchPostUrl);
	}
	
	@Test
	public void Post_02_Search_And_View_Post() {
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewOrUpdatePage.openSearchPostPageUrl(searchPostUrl);
		
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
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostTitle(postTitle));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithAuthor(postTitle, authorName));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithCurrentDay(postTitle, currentDay));
	}
	
	@Test
	public void Post_03_Edit_Post() {
		log.info("Edit_Post - Step 01: Open Admin site");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Edit_Post - Step 02: Click to 'Posts' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Edit_Post - Step 03: Enter to search post textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Edit_Post - Step 04: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Edit_Post - Step 05: Click to Post title link and navigate to edit post page");
		adminPostAddNewOrUpdatePage = adminPostSearchPage.clickToPostTitleLink("title", postTitle);

		log.info("Edit_Post - Step 06: Enter to post title");
		adminPostAddNewOrUpdatePage.enterToAddNewOrUpdatePostTitle(editPostTitle);
		
		log.info("Edit_Post - Step 07: Enter to post body");
		adminPostAddNewOrUpdatePage.enterToAddNewOrUpdatePostBody(editPostBody);
		
		log.info("Edit_Post - Step 08: Click to 'Update' button");
		adminPostAddNewOrUpdatePage.clickToPublishOrUpdateButton();
		
		log.info("Edit_Post - Step 09: Verify 'Post updated.' message is displayed");
		verifyTrue(adminPostAddNewOrUpdatePage.isPostPublishMessageDisplayed("Post updated."));
		
		log.info("Edit_Post - Step 10: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewOrUpdatePage.openSearchPostPageUrl(searchPostUrl);
		
		log.info("Edit_Post - Step 11: Enter to search post textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);
		
		log.info("Edit_Post - Step 12: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Edit_Post - Step 13: Verify search table contain '" + editPostTitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title", editPostTitle));
		
		log.info("Edit_Post - Step 14: Verify search table contain '" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));
		
		log.info("Edit_Post - Step 15: Open End User Site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Edit_Post - Step 16: Verify Post info displayed at Home page");
		verifyTrue(userHomePage.isPostInfoDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userHomePage.isPostInfoDisplayedWithPostBody(editPostTitle, editPostBody));
		verifyTrue(userHomePage.isPostInfoDisplayedWithAuthor(editPostTitle, authorName));
		verifyTrue(userHomePage.isPostInfoDisplayedWithCurrentDay(editPostTitle, currentDay));
		
		log.info("Edit_Post - Step 17: Click to Post tittle");
		userPostDetailPage = userHomePage.clickToPostTitle(editPostTitle);
		
		log.info("Edit_Post - Step 18: Verify Post info displayed at Post detail page");
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostBody(editPostTitle, editPostBody));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithAuthor(editPostTitle, authorName));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithCurrentDay(editPostTitle, currentDay));
	}
	
	@Test
	public void Post_04_Delete_Post() {
		log.info("Delete_Post - Step 01: Open Admin site");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Delete_Post - Step 02: Click to 'Posts' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Delete_Post - Step 03: Enter to search post textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete_Post - Step 04: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Delete_Post - Step 05: Select Post detail checkbox");
		adminPostSearchPage.selectPostCheckboxByTitle(editPostTitle);
		
		log.info("Delete_Post - Step 06: Select 'Move to Trash' item in dropdown");
		adminPostSearchPage.selectItemInActionDropdown("Move to Trash");
		
		log.info("Delete_Post - Step 07: Click to 'Apply' button");
		adminPostSearchPage.clickToApplyButton();
		
		log.info("Delete_Post - Step 08: Verify '1 post moved to the Trash' message is displayed");
		verifyTrue(adminPostSearchPage.isMoveToTrashMessageDisplayed("1 post moved to the Trash"));
		
		log.info("Delete_Post - Step 09: Enter to search post textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete_Post - Step 10: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Delete_Post - Step 11: Verify 'No posts found.' message is displayed");
		verifyTrue(adminPostSearchPage.isNoPostsFoundMessageDisplayed("No posts found."));

		log.info("Delete_Post - Step 12: Open End User Site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Delete_Post - Step 13: Verify Post title undisplayed at Home page");
		verifyTrue(userHomePage.isPostInfoUndisplayWithPostTitle(editPostTitle));

		log.info("Delete_Post - Step 14: Enter to search post textbox");
		userHomePage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete_Post - Step 15: Click to 'Search' button");
		userPostSearchPage = userHomePage.clickToSearchButton();
		
		log.info("Delete_Post - Step 16: Verify 'Nothing found' message is displayed");
		verifyTrue(userPostSearchPage.isNothingFoundMessageDisplayed("Nothing Found"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	private WebDriver driver;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPage;
	private AdminPostSearchPO adminPostSearchPage;
	private AdminPostAddNewOrUpdatePO adminPostAddNewOrUpdatePage;
	private UserHomePO userHomePage;
	private UserPostDetailPO userPostDetailPage;
	private UserPostSearchPO userPostSearchPage;
	private String adminUsername = "automationfc";
	private String adminPassword = "automationfc";
	private String searchPostUrl;
	private int randomNumber = getRandomNumber();
	private String postTitle = "Live Coding Title" + randomNumber;
	private String postBody = "Live Coding Body" + randomNumber;
	private String editPostTitle = "Edit Title" + randomNumber;
	private String editPostBody = "Edit Body" + randomNumber;
	private String authorName = "Automation Admin";
	private String adminUrl, endUserUrl;
	private String currentDay = getCurrentDay();
	
	
}
