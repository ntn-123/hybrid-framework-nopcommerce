package pageObjectsWordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsWordpress.AdminPostSearchPageUI;

public class AdminPostSearchPO extends BasePage{
	private WebDriver driver;

	protected AdminPostSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostAddNewPO clickToAddNewButton() {
		waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		return PageGeneratorManager.getAdminAddNewPage(driver);
	}

	public void enterToSearchTextbox(String postTitle) {
		// TODO Auto-generated method stub
		
	}

	public void clickToSearchPostsButton() {
		// TODO Auto-generated method stub
		
	}

	public void isPostSearchTableDisplayed(String string, String postTitle) {
		// TODO Auto-generated method stub
		
	}


}
