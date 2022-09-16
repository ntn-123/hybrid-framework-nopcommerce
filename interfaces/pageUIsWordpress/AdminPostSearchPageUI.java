package pageUIsWordpress;

public class AdminPostSearchPageUI {
	public static final String ADD_NEW_BUTTON = "xpath=//a[@class='page-title-action']";
	public static final String POST_TEXTBOX = "xpath=//input[@id='post-search-input']";
	public static final String SEARCH_POSTS_BUTTON = "xpath=//input[@id='search-submit']";
	public static final String TABLE_HEADER_INDEX_BY_HEADER_ID = "xpath=//table[contains(@class,'table-view-list posts')]/thead//th[@id='%s']/preceding-sibling::*";
	public static final String TABLE_ROW_VALUE_BY_HEADER_INDEX = "xpath=//table[contains(@class,'table-view-list posts')]//tbody/tr/*[%s]//a[text()='%s']";
	public static final String ROW_CHECKBOX_BY_POST_TITLE_NAME = "xpath=//a[text()='%s']//ancestor::td[@data-colname='Title']/preceding-sibling::th/input";
	public static final String ACTION_DROPDOWN = "xpath=//select[@id='bulk-action-selector-top']";
	public static final String APPLY_BUTTON = "xpath=//input[@id='doaction']";
	public static final String MOVE_TO_TRASH_MESSAGE = "xpath=//div[@id='message']/p[contains(text(),'%s')]";
	public static final String NO_POSTS_FOUND_MESSAGE = "xpath=//tbody[@id='the-list']//td[text()='%s']";
	
}
