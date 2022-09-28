package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerConnUtils {
	public static Connection getSQLServerConnection() {
		String hostName = "localhost";
		String sqlInstanceName = "SQLEXPRESS";
		String database = "automationtest";
		String userName = "sa";
		String password = "automationfc";
		
		return getSQLServerConnection(hostName, sqlInstanceName, database, userName, password);
	}

	private static Connection getSQLServerConnection(String hostName, String sqlInstanceName, String database, String userName, String password) {
		Connection conn = null;
		try {
			// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			// Cau tru SQL Connection danh cho SQL Server
			String connectionURL = "jdbc:sqlserver://" + hostName + ":1433" + ";instance=" + sqlInstanceName + ";databaseName=" + database;
			conn = DriverManager.getConnection(connectionURL, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
