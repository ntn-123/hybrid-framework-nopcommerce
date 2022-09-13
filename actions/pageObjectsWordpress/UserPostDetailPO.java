package pageObjectsWordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserPostDetailPO extends BasePage{
	private WebDriver driver;

	protected UserPostDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPostInfoDisplayed(String postTitle) {
		// TODO Auto-generated method stub
		return false;
	}

}
