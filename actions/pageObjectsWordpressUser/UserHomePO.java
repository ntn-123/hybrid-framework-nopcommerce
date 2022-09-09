package pageObjectsWordpressUser;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserHomePO extends BasePage{
	private WebDriver driver;

	protected UserHomePO(WebDriver driver) {
		this.driver = driver;
	}

}
