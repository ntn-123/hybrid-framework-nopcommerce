package jdbcTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLServerJTDSTestConnection {
	public static Connection getMyConnection() throws SQLException, ClassNotFoundException{
		return SQLServerJTDSConnUtils.getSQLServerConnection();
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		System.out.println("Get connection ...");
		
		// Lay ra doi tuong Connection ket noi vao Database
		Connection conn = SQLServerJTDSTestConnection.getMyConnection();
		
		System.out.println("Opened connection: " + conn);
		
		Statement statement = conn.createStatement();
		
		String sql = "Select + From [automationtest].[dbo].[Product_Type];";
		
		// Thu thi cau lenh SQL tra ve doi tuong ResultSet
		ResultSet rs = statement.executeQuery(sql);
		
		// Duyet tren ket qua tra ve
		while(rs.next()) {
			// Di chuyen con tro xuong ban ghi ke tiep
			String empFirstName = rs.getString(1);
			String empLastName = rs.getString("Name");
			
			System.out.println("--------------");
			System.out.println("Emp Firstname: " + empFirstName);
			System.out.println("Emp Lastname: " + empLastName);
		}
		
		// Dong ket noi
		conn.close();
		System.out.println("-----------Close connection--------------");
		
	}
	
}
