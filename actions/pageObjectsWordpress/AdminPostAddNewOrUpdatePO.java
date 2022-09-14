package pageObjectsWordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsWordpress.AdminPostAddNewPageUI;

public class AdminPostAddNewOrUpdatePO extends BasePage{
	private WebDriver driver;

	protected AdminPostAddNewOrUpdatePO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToAddNewOrUpdatePostTitle(String addNewPostTitle) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.TITTLE_TEXTBOX);
		sendKeyToElement(driver, AdminPostAddNewPageUI.TITTLE_TEXTBOX, addNewPostTitle);
	}

	public void clickToAddNewPostBody() {
		waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.BODY_BUTTON);
	}

	public void enterToAddNewOrUpdatePostBody(String postBody) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		clearValueInElementByDeleteKey(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		sendKeyToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX, postBody);
	}

	public void clickToPublishOrUpdateButton() {
		waitForElementClickable(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);
	}

	public boolean isPostPublishMessageDisplayed(String postPublishMessage) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.PUBLISHED_MESSAGE, postPublishMessage);
		return isElementDisplayed(driver, AdminPostAddNewPageUI.PUBLISHED_MESSAGE, postPublishMessage);
	}

	public AdminPostSearchPO openSearchPostPageUrl(String searchPostUrl) {
		openPageUrl(driver, searchPostUrl);
		return PageGeneratorManager.getAdminPostSearchPage(driver);
	}

	public void clickToCloseButtonAtPopup() {
		waitForElementClickable(driver, AdminPostAddNewPageUI.CLOSE_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.CLOSE_BUTTON);
	}

	public void clickToPrePublishButton() {
		waitForElementClickable(driver, AdminPostAddNewPageUI.PRE_PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.PRE_PUBLISH_BUTTON);
	}

}
