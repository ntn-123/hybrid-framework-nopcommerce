package pageObjectsSauceLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

	public boolean isProductNameSortByAscending() {
		// Khai bao ra 1 ArrayList de chua cac product name tren UI
		ArrayList<String> productUIList = new ArrayList<>();
		
		// Lay ra het tat ca cac text product name
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		
		// Dung vong lap de getText va add vao ArrayList tren
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}
		
		// Tao ra mot ArrayList moi de sort du lieu trong ArrayList cu xem dung hay khong
		ArrayList<String> productSortList = new ArrayList<>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		
		// Sort cai productSortList
		Collections.sort(productSortList);
		
		// So sanh 2 List da bang nhau
		return productSortList.equals(productUIList);
	}

	public boolean isProductNameSortByAscendingLamda() {
		List<WebElement> elementLists = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		List<String> names = elementLists.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortNames = new ArrayList<String>(names);
		Collections.sort(sortNames);
		return names.equals(sortNames);
	}
	
	public boolean isProductNameSortByDescending() {
		// Khai bao ra mot ArrayList de chua cac product name tren UI
		ArrayList<String> productUIList = new ArrayList<>();
		
		// Lay ra het tat ca cac text product name
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		
		// Dung vong lap de get text va add vao ArrayList tren
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}
		
		// Tao ra mot ArrayList moi de sort du lieu trong ArrayList cu xem dung hay khong
		ArrayList<String> productSortList = new ArrayList<>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		
		// Sort productSortList
		Collections.sort(productSortList);
		
		// Reverse productSortList
		Collections.reverse(productSortList);
		
		// So sanh 2 list da bang nhau
		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByAscending() {
		// Khai bao ra mot ArrayList de chua cac product price tren UI
		ArrayList<Float> productUIList = new ArrayList<>();
		
		// Lay ra het tat ca cac text product price
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);
		
		// Dung vong lap de get text va add vao ArrayList tren
		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}
		
		// Tao ra mot ArrayList moi de sort du lieu trong ArrayList cu xem dung hay khong
		ArrayList<Float> productSortList = new ArrayList<>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}
		
		// Sort productSortList
		Collections.sort(productSortList);
		
		// So sanh 2 list da bang nhau
		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByDescending() {
		// Khai bao ra mot ArrayList de chua cac product name tren UI
		ArrayList<Float> productUIList = new ArrayList<>();
		
		// Lay ra het tat ca cac text product name
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);
		
		// Dung vong lap de get text va add vao ArrayList tren
		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}
		
		// Tao ra mot ArrayList moi de sort du lieu trong ArrayList cu xem dung hay khong
		ArrayList<Float> productSortList = new ArrayList<>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}
		
		// Sort productSortList
		Collections.sort(productSortList);
		
		// Reverse productSortList
		Collections.reverse(productSortList);
		
		// So sanh 2 list da bang nhau
		return productSortList.equals(productUIList);
	}
	
	
}
