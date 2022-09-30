package pageObjectsWordpress;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsWordpress.AdminUserPageUI;
import utilities.MySQLConnUtils;

public class AdminUserPO extends BasePage{
	private WebDriver driver;

	protected AdminUserPO(WebDriver driver) {
		this.driver = driver;
	}

	public int getUserNumberAtUI() {
		waitForElementVisible(driver, AdminUserPageUI.TOTAL_USER_TEXT);
		String totalUser = getElementText(driver, AdminUserPageUI.TOTAL_USER_TEXT);
		return Integer.parseInt(totalUser.split(" ")[0]);
	}

	public int getUserNumberAtDB() {
		Connection conn = MySQLConnUtils.getMySQLConnection();
		Statement statement;
		List<Integer> totalUsers = new ArrayList<Integer>();
		try {
			statement = conn.createStatement();
			String sql = "SELECT * FROM `wp_users`;";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				totalUsers.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totalUsers.size();
	}
}
