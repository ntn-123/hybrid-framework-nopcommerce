package pageObjectsWordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserHomePO extends BasePage{
	private WebDriver driver;

	protected UserHomePO(WebDriver driver) {
		this.driver = driver;
	}

	public UserPostDetailPO clickToPostTitle(String postTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isPostInfoDisplayedWithPostTitle(String postTitle) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPostInfoDisplayedWithPostBody(String postTitle, String postBody) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPostInfoDisplayedWithAuthor(String postTitle, String authorName) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPostInfoDisplayedWithCurrentDay(String postTitle, String currentDay) {
		// TODO Auto-generated method stub
		return false;
	}

}
