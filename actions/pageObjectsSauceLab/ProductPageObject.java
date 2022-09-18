package pageObjectsSauceLab;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsSauceLab.ProductPageUI;

public class ProductPageObject extends BasePage{
	private WebDriver driver;

	protected ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInProductSortDropdown(String itemValue) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_SORT_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductPageUI.PRODUCT_SORT_DROPDOWN, itemValue);
	}
	
	
}
