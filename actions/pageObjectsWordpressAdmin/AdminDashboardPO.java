package pageObjectsWordpressAdmin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsWordpressAdmin.AdminDashboardPageUI;

public class AdminDashboardPO extends BasePage{
	private WebDriver driver;

	protected AdminDashboardPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostSearchPO clickToPostMenuLink() {
		waitForElementClickable(driver, AdminDashboardPageUI.POST_MENU_LINK);
		clickToElement(driver, AdminDashboardPageUI.POST_MENU_LINK);
		return AdminPageGeneratorManager.getAdminPostSearchPage(driver);
	}
	
}
