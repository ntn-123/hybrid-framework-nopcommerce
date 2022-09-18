package pageObjectsWordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsWordpress.UserHomePageUI;

public class UserHomePO extends BasePage{
	private WebDriver driver;

	protected UserHomePO(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isPostInfoDisplayedWithPostTitle(String postTitle) {
		waitForElementVisible(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
		return isElementDisplayed(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
	}

	public boolean isPostInfoDisplayedWithPostBody(String postTitle, String postBody) {
		waitForElementVisible(driver, UserHomePageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBody);
		return isElementDisplayed(driver, UserHomePageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBody);
	}

	public boolean isPostInfoDisplayedWithAuthor(String postTitle, String authorName) {
		waitForElementVisible(driver, UserHomePageUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle, authorName);
		return isElementDisplayed(driver, UserHomePageUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle, authorName);
	}

	public boolean isPostInfoDisplayedWithCurrentDay(String postTitle, String currentDay) {
		waitForElementVisible(driver, UserHomePageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, currentDay);
		return isElementDisplayed(driver, UserHomePageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, currentDay);
	}

	public UserPostDetailPO clickToPostTitle(String postTitle) {
		waitForElementClickable(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
		clickToElement(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
		return PageGeneratorManager.getUserPostDetailPage(driver);
	}
	public boolean isPostInfoUndisplayWithPostTitle(String editPostTitle) {
		//waitForElementInvisible(driver, UserHomePageUI.POST_TITLE_TEXT, editPostTitle);
		return isElementUndisplayed(driver, UserHomePageUI.POST_TITLE_TEXT, editPostTitle);
	}
	public void enterToSearchTextbox(String editPostTitle) {
		waitForElementClickable(driver, UserHomePageUI.SEARCH_TEXTBOX, editPostTitle);
		sendKeyToElement(driver, UserHomePageUI.SEARCH_TEXTBOX, editPostTitle);
	}
	public UserPostSearchPO clickToSearchButton() {
		waitForElementClickable(driver, UserHomePageUI.SEARCH_BUTTON);
		clickToElement(driver, UserHomePageUI.SEARCH_BUTTON);
		return PageGeneratorManager.getUserPostSearchPage(driver);
	}


}
