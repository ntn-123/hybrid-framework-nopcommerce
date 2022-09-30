package pageObjectsWordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsWordpress.AdminDashboardPageUI;

public class AdminDashboardPO extends BasePage{
	private WebDriver driver;

	protected AdminDashboardPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostSearchPO clickToPostMenuLink() {
		waitForElementClickable(driver, AdminDashboardPageUI.POST_MENU_LINK);
		clickToElement(driver, AdminDashboardPageUI.POST_MENU_LINK);
		return PageGeneratorManager.getAdminPostSearchPage(driver);
	}

	public AdminUserPO clickToUsersLink() {
		waitForElementClickable(driver, AdminDashboardPageUI.USERS_MENU_LINK);
		clickToElement(driver, AdminDashboardPageUI.USERS_MENU_LINK);
		return PageGeneratorManager.getAdminUserPage(driver);
	}
	
}
