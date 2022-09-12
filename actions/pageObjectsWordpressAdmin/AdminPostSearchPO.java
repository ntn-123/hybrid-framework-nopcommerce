package pageObjectsWordpressAdmin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsWordpressAdmin.AdminPostSearchPageUI;

public class AdminPostSearchPO extends BasePage{
	private WebDriver driver;

	protected AdminPostSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostAddNewPO clickToAddNewButton() {
		waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		return AdminPageGeneratorManager.getAdminAddNewPage(driver);
	}

}
