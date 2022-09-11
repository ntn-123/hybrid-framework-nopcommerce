package pageObjectsWordpressAdmin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsWordpressAdmin.AdminLoginPageUI;

public class AdminLoginPO extends BasePage{
	private WebDriver driver;

	protected AdminLoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUsernameTextbox(String adminUsername) {
		waitForElementVisible(driver, AdminLoginPageUI.USERNAME_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.USERNAME_TEXTBOX, adminUsername);
	}

	public void enterToPasswordTextbox(String adminPassword) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, adminPassword);
	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
	}
	
}
