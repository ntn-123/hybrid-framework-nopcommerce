package pageObjectsSauceLab;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsSauceLab.LoginPageUI;

public class LoginPageObject extends BasePage{
	private WebDriver driver;

	protected LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUsernameTextbox(String username) {
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, username);
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public ProductPageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getProductPage(driver);
	}
	
	
}
