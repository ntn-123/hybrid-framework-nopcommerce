package pageObjectsNopcommerceUser;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIsNopcommerceUser.UserCustomerInfoPageUI;
import pageUIsNopcommerceUser.UserLoginPageUI;

public class UserCustomerInfoPageObject extends BasePage{

	private WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click to login button")
	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManagerNopcommerce.getUserHomePage(driver);
		
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	@Step("Input to email textbox with value {0}")
	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, email);
	}

	public String getErrorMessageUnsuccessful() {
		waitForElementVisible(driver, UserLoginPageUI.LOGIN_UNSUCCESS_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.LOGIN_UNSUCCESS_ERROR_MESSAGE);
	}

	@Step("Input to password textbox with value {0}")
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public UserHomePageObject loginAsUser(String emailAddress, String password) {
		inputToEmailTextbox(emailAddress);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
	}

	public boolean isCustomerInfoPageDisplayed() {
		waitForElementVisible(driver, UserCustomerInfoPageUI.CUSTOMER_INFO_PAGE_TITTLE);
		return isElementDisplayed(driver, UserCustomerInfoPageUI.CUSTOMER_INFO_PAGE_TITTLE);
	}

}
