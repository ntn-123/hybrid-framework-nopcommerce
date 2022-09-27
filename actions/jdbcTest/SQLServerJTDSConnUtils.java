package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerJTDSConnUtils {
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
			// Khai bao class Driver cho SQLServer
			// Viec nay can thiet voi Java 5
			// Java 6 tu dong tim kiem Driver thich hop - khong bat buoc can dong nay
			// Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			// Cau truc URL Connection danh cho SQL Server
			// String connectionURL = "jdbc:sqlserver://" + hostName + ":1433" + ";instance=" + sqlInstanceName + ";databaseName=" + database;
			String connectionURL = "jdbc:jtds:sqlserver://" + hostName + ":1433/" + database + ";instance=" + sqlInstanceName;
			
			conn = DriverManager.getConnection(connectionURL, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
