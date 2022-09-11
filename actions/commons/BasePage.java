package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIsBasePage.BasePageJQueryUploadFileUI;
import pageUIsBasePage.BasePageNopcommerceUI;

public class BasePage {
	
	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Set<Cookie> getAllCookie(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setCookie(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(shortTimeout);
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		for (String id : allWindowIDs) {
			if(!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(WebDriver driver, String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if(actualTitle.equals(tabTitle)) {
				break;
			}
		}
	}
	
	public void closeAllTabWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		for (String id : allWindowIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}

	// locatorType: id=/ css=/ xpath=/ name=/ class=
	// locatorType: ID=/ CSS=/ XPATH=/ NAME=/ CLASS=
	// locatorType: Id=/ Css=/ Xpath=/ Name=/ Class=
	public By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));
		} 
		else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
			by = By.className(locatorType.substring(6));
		} 
		else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
			by = By.name(locatorType.substring(5));
		} 
		else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} 
		else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		} 
		else {
			throw new RuntimeException("Locator type is not support!");
		}
		return by;
	}
	
	public String getDynamicXpath(String locatorType, String... dynamicValues) {
		if(locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;
	}
	
	
	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	public List<WebElement> getListWebElement(WebDriver driver, String locatorType){
		return driver.findElements(getByLocator(locatorType));
	}
	
	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}
	
	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}
	
	public void sendKeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void sendKeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}
	
	public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textItem);
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocatorType, String childLocatorType, String expectedTextItem) {
		clickToElement(driver, parentLocatorType);
		sleepInSecond(1);
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocatorType)));
		for (WebElement item : allItems) {
			if(item.getText().trim().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}
	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementsSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}
	
	public int getElementsSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}
	
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		
		if(element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}
	
	public void overrideImplicitTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locatorType){
		System.out.println("Start time = " + new Date().toString());
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		overrideImplicitTimeout(driver, longTimeout);
		if(elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if(elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/ displayed");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}
	
	public boolean isElementEnable(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}
	
	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}
	
	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	public void highlightElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}
	
	public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}
	
	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}
	
	public void removeAttributeInDom(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}
	
	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		return status;
	}
	
	public boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		return status;
	}
	
	public WebElement getShadowDOM(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot;", getWebElement(driver, locatorType));
		return element;
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForAllElementsVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	
	public void waitForAllElementsVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	/*
	 * Wait for element undisplayed in DOM or not in DOM and override implicit timeout
	*/
	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(driver, longTimeout);
	}
	
	public void waitForAllElementsInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}
	
	public void waitForAllElementsInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		// Duong dan cua thu muc upload file
		String filePath = GlobalConstants.UPLOAD_FILE;
		System.out.println(filePath);
		
		// Duong dan cua tat ca cac file
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		//fullFileName = filePath + fileNames;
		System.out.println(fullFileName);
		getWebElement(driver, BasePageJQueryUploadFileUI.UPLOAD_FILE).sendKeys(fullFileName);
	}
	
//	// Toi uu o bai hoc Level_07_Switch page
//	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_LINK);
//		clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
//		return commons.PageGeneratorManager.getUserCustomerInfoPage(driver);
//	}
//	
//	public UserAddressesPageObject openAddressesPage(WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.ADDRESSES_LINK);
//		clickToElement(driver, BasePageUI.ADDRESSES_LINK);
//		return commons.PageGeneratorManager.getUserAddressesPage(driver);
//	}
//	
//	public UserOrdersPageObject openOrdersPage(WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.ORDERS_LINK);
//		clickToElement(driver, BasePageUI.ORDERS_LINK);
//		return commons.PageGeneratorManager.getUserOrdersPage(driver);
//	}
//	
//	public UserDownloadableProductsPageObject openDownloadableProductsPage(WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.DOWNLOADABLE_PRODUCTS_LINK);
//		clickToElement(driver, BasePageUI.DOWNLOADABLE_PRODUCTS_LINK);
//		return commons.PageGeneratorManager.getUserDownloadableProductsPage(driver);
//	}
//	
//	public UserBackInStockSubscripttionsPageObject openBackInStockSubscripttionsPage(WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.BACK_IN_STOCK_SUBCRIPTIONS_LINK);
//		clickToElement(driver, BasePageUI.BACK_IN_STOCK_SUBCRIPTIONS_LINK);
//		return commons.PageGeneratorManager.getUserBackInStockSubscripttionsPage(driver);
//	}
//	
//	public UserRewardPointsPageObject openRewardPointsPage(WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.REWARD_POINTS_LINK);
//		clickToElement(driver, BasePageUI.REWARD_POINTS_LINK);
//		return commons.PageGeneratorManager.getUserRewardPointsPage(driver);
//	}
//	
//	public UserChangePasswordPageObject openChangePasswordPage(WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.CHANGE_PASSWORD_LINK);
//		clickToElement(driver, BasePageUI.CHANGE_PASSWORD_LINK);
//		return commons.PageGeneratorManager.getUserChangePasswordPage(driver);
//	}
//
//	public UserMyProductReviewsPageObject openMyProductReviewsPage(WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEWS_LINK);
//		clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEWS_LINK);
//		return commons.PageGeneratorManager.getUserMyProductReviewsPage(driver);
//	}
//
//	// Toi uu o bai hoc Level_09_Dynamic_Locator
//	// Cach 1
//	public BasePage openPageAtMyAccountByName(WebDriver driver, String pageName) {
//		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
//		clickToElement(driver, BasePageUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
//		switch (pageName) {
//		case "Customer info":
//			return commons.PageGeneratorManager.getUserCustomerInfoPage(driver);
//		case "Addresses":
//			return commons.PageGeneratorManager.getUserAddressesPage(driver);
//		case "Orders":
//			return commons.PageGeneratorManager.getUserOrdersPage(driver);
//		case "Downloadable products":
//			return commons.PageGeneratorManager.getUserDownloadableProductsPage(driver);
//		case "Back in stock subscriptions":
//			return commons.PageGeneratorManager.getUserBackInStockSubscripttionsPage(driver);
//		case "Reward points":
//			return commons.PageGeneratorManager.getUserRewardPointsPage(driver);
//		case "Change password":
//			return commons.PageGeneratorManager.getUserChangePasswordPage(driver);
//		case "My product reviews":
//			return commons.PageGeneratorManager.getUserMyProductReviewsPage(driver);
//		default:
//			throw new RuntimeException("Invalid page name at My Account area");
//		}
//	}
//	
//	// Cach 2
//	public void openPageAtMyAccountByPageName(WebDriver driver, String pageName) {
//		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
//		clickToElement(driver, BasePageUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
//	}
//	
//	// Toi uu o bai loc Level_08_Switch_Role
//	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_USER_PAGE);
//		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER_PAGE);
//		return commons.PageGeneratorManager.getUserHomePage(driver);
//	}
//	
//	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN_PAGE);
//		clickToElementByJS(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN_PAGE);
//		return commons.PageGeneratorManager.getAdminLoginPage(driver);
//	}
	

	/**
	 * Enter to dynamic Textbox by ID
	 * @author NguyenNT
	 * @param driver
	 * @param textboxID
	 * @param value
	 */
	public void inputTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, BasePageNopcommerceUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendKeyToElement(driver, BasePageNopcommerceUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}
	
	/**
	 * Click to dynamic button by text
	 * @author NguyenNT
	 * @param driver
	 * @param buttonText
	 */
	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, BasePageNopcommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, BasePageNopcommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}

	/**
	 * Select to dynamic dropdown by attribute name
	 * @author NguyenNT
	 * @param driver
	 * @param attributeName
	 * @param itemValue
	 */
	public void selectToDropdownByAttributeName(WebDriver driver, String attributeName, String itemValue) {
		waitForElementClickable(driver, BasePageNopcommerceUI.DYNAMIC_DROPDOWN_BY_ATTRIBUTE_NAME, attributeName);
		selectItemInDefaultDropdown(driver, BasePageNopcommerceUI.DYNAMIC_DROPDOWN_BY_ATTRIBUTE_NAME, itemValue, attributeName);
	}

	/**
	 * Check to dynamic radio button by label
	 * @author NguyenNT
	 * @param driver
	 * @param genderLabel
	 */
	public void checkToRadioButtonByLabel(WebDriver driver, String radioButtonLabel) {
		waitForElementClickable(driver, BasePageNopcommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabel);
		checkToDefaultCheckboxOrRadio(driver, BasePageNopcommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabel);
	}
	
	/**
	 * Check to dynamic checkbox by label
	 * @author NguyenNT
	 * @param driver
	 * @param checkboxLabel
	 */
	public void checkToCheckboxByLabel(WebDriver driver, String checkboxLabel) {
		waitForElementClickable(driver, BasePageNopcommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabel);
		checkToDefaultCheckboxOrRadio(driver, BasePageNopcommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabel);
	}


	/**
	 * Get textbox value by attribute value
	 * @author NguyenNT
	 * @param driver
	 * @param textboxID
	 * @return
	 */
	public String getTextboxValueByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, BasePageNopcommerceUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageNopcommerceUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
	}
	
	
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHOTR_TIMEOUT;
	
}


