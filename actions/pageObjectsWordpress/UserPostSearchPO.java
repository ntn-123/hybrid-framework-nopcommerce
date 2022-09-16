package pageObjectsWordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsWordpress.UserPostDetailPageUI;
import pageUIsWordpress.UserPostSearchPageUI;

public class UserPostSearchPO extends BasePage{
	private WebDriver driver;

	protected UserPostSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isNothingFoundMessageDisplayed(String message) {
		waitForElementVisible(driver, UserPostSearchPageUI.NOTHING_FOUND_MESSAGE, message);
		return isElementDisplayed(driver, UserPostSearchPageUI.NOTHING_FOUND_MESSAGE, message);
	}
}
