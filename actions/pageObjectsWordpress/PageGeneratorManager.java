package pageObjectsWordpress;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static AdminLoginPO getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPO(driver);
	}
	
	public static AdminDashboardPO getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPO(driver);
	}
	
	public static AdminPostSearchPO getAdminPostSearchPage(WebDriver driver) {
		return new AdminPostSearchPO(driver);
	}
	
	public static AdminUserPO getAdminUserPage(WebDriver driver) {
		return new AdminUserPO(driver);
	}
	
	public static AdminPostAddNewOrUpdatePO getAdminAddNewOrUpdatePage(WebDriver driver) {
		return new AdminPostAddNewOrUpdatePO(driver);
	}
	
	public static UserHomePO getUserHomePage(WebDriver driver) {
		return new UserHomePO(driver);
	}
	
	public static UserPostDetailPO getUserPostDetailPage(WebDriver driver) {
		return new UserPostDetailPO(driver);
	}

	public static UserPostSearchPO getUserPostSearchPage(WebDriver driver) {
		return new UserPostSearchPO(driver);
	}
}
