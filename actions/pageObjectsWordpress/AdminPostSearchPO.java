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
		waitForElementVisible(driver, AdminPostSearchPageUI.POST_TEXTBOX);
		sendKeyToElement(driver, AdminPostSearchPageUI.POST_TEXTBOX, postTitle);
	}

	public void clickToSearchPostsButton() {
		waitForElementClickable(driver, AdminPostSearchPageUI.SEARCH_POSTS_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.SEARCH_POSTS_BUTTON);
	}

	public boolean isPostSearchTableDisplayed(String headerID, String cellValue) {
		int headerIndex = getElementsSize(driver, AdminPostSearchPageUI.TABLE_HEADER_INDEX_BY_HEADER_ID, headerID) + 1;
		waitForElementVisible(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
		return isElementDisplayed(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
	}


}