package pageUIsBasePage;

public class BasePageNopcommerceUI {
	public static String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
	public static String DYNAMIC_DROPDOWN_BY_ATTRIBUTE_NAME = "xpath=//select[@name='%s']";
	public static String DYNAMIC_RADIO_BUTTON_BY_LABEL = "xpath=//label[text()='%s']/preceding-sibling::input";
	public static String DYNAMIC_CHECKBOX_BY_LABEL = "xpath=//label[contains(text(),'%s')]/following-sibling::input";
}
