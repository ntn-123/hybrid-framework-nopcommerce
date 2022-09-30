package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;

public class WordpressConnUtils {
	public static Connection getWordpressConnUtils() {
		String hostName = "localhost";
		String dbName = "automationfc";
		String userName = "root";
		String password = "";
		return getWordpressConnUtils(hostName, dbName, userName, password);
	}

	private static Connection getWordpressConnUtils(String hostName, String dbName, String userName, String password) {
		Connection conn = null;
		try {
			// Khai bao class Driver cho MySQL
			// Viec nay can thiet voi Java 5
			// Java 6 tu dong tim kiem Driver thich hop - khong bat buoc can dong nay
			// Class.forName("com.mysql.jdbc.Driver");
			
			// Cau truc url Connection danh cho MySQL
			// Vi du: jdbc:mysql://localhost:3306/automationfc
			String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
			conn = DriverManager.getConnection(connectionURL, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
