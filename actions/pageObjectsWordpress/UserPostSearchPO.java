package pageObjectsWordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsWordpress.UserPostDetailPageUI;

public class UserPostSearchPO extends BasePage{
	private WebDriver driver;

	protected UserPostSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isNothingFoundMessageDisplayed(String string) {
		// TODO Auto-generated method stub
		return false;
	}
}
