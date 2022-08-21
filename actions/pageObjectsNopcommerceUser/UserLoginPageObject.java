package pageObjectsNopcommerceUser;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsNopcommerceUser.UserLoginPageUI;

public class UserLoginPageObject extends BasePage{

	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		
		// 2
		// return new HomePageObject(driver);
		return PageGeneratorManagerNopcommerce.getUserHomePage(driver);
		
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, email);
	}

	public String getErrorMessageUnsuccessful() {
		waitForElementVisible(driver, UserLoginPageUI.LOGIN_UNSUCCESS_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.LOGIN_UNSUCCESS_ERROR_MESSAGE);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public UserHomePageObject loginAsUser(String emailAddress, String password) {
		inputToEmailTextbox(emailAddress);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
	}
	
}
