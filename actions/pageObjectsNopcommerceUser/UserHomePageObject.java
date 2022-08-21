package pageObjectsNopcommerceUser;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsNopcommerceUser.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	
	private WebDriver driver;
	
	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserRegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
		clickToElement(driver, UserHomePageUI.REGISTER_LINK);
		
		// 2
		// return new RegisterPageObject(driver);
		
		return PageGeneratorManagerNopcommerce.getUserRegisterPage(driver);
	}

	public UserLoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, UserHomePageUI.LOGIN_LINK);
		clickToElement(driver, UserHomePageUI.LOGIN_LINK);
		
		// 2
		// return new LoginPageObject(driver);
		return PageGeneratorManagerNopcommerce.getUserLoginPage(driver);
	}

}
