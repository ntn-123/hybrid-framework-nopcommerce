package jdbcTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTestConnection {
	public static Connection getMyConnection() throws SQLException, ClassNotFoundException{
		return MySQLConnUtils.getMySQLConnection();
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		System.out.println("Get connection...");
		
		// Lay ra doi tuong ket noi vao database
		Connection conn = MySQLTestConnection.getMyConnection();
		
		System.out.println("Opened connection: " + conn);
		
		Statement statement = conn.createStatement();
		
		String sql = "Select Emp.Emp_Id, Emp.First_Name, Emp.Last_Name, Emp.Dept_Id From Employee Emp;";
		
		// Thuc thi doi tuong sql va tra ve doi tuong ResultSet
		ResultSet rs = statement.executeQuery(sql);
		
		// Duyet tren ket qua tra ve
		while(rs.next()) {
			// Di chuyen con tro xuong ban ghi ke tiep
			int empId = rs.getInt(1);
			String empFirstName = rs.getString(2);
			String empLastName = rs.getString("Last_Name");
			
			System.out.println("----------------");
			System.out.println("Emp Id: "+ empId);
			System.out.println("Emp Firstname: " + empFirstName);
			System.out.println("Emp Lastname: " + empLastName);
		}
		// Dong ket noi
		conn.close();
		System.out.println("------Close connection-----------");
	}
	
}
