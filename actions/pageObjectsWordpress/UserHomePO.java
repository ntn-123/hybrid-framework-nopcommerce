package pageObjectsWordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserHomePO extends BasePage{
	private WebDriver driver;

	protected UserHomePO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPostInfoDisplayed(String postTitle) {
		// TODO Auto-generated method stub
		return false;
	}

	public UserPostDetailPO clickToPostTitle(String postTitle) {
		// TODO Auto-generated method stub
		return null;
	}

}
