package jdbcTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.xdevapi.PreparableStatement;

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
		
		//String sql = "SELECT * FROM BRANCH;";
		//String insertSQL = "INSERT INTO `branch`(`ADDRESS`, `CITY`, `NAME`, `STATE`, `ZIP_CODE`) VALUES ('3 Duy Tan', 'CW', 'AutomationFC', 'HN', '50000');";
		String paramSql = "SELECT `EMP_ID`, `FIRST_NAME`, `LAST_NAME`, `TITLE`, `DEPT_ID` FROM `employee` WHERE `TITLE` like ? and `DEPT_ID` = ?;";
		
		//int rowCount = statement.executeUpdate(insertSQL);
		//System.out.println("Row Count affected = " + rowCount);
		
		// Thuc thi doi tuong sql va tra ve doi tuong ResultSet
		//ResultSet rs = statement.executeQuery(sql);
		
		PreparedStatement pstm = conn.prepareStatement(paramSql);
		pstm.setString(1, "%ent");
		pstm.setInt(2, 3);
		
		ResultSet rs = pstm.executeQuery();
		
		// Duyet tren ket qua tra ve
		while(rs.next()) {
			// Di chuyen con tro xuong ban ghi ke tiep
			System.out.println("----------------");
//			System.out.println("Branch Id: "+ rs.getInt("BRANCH_ID"));
//			System.out.println("BRANCH ADDRESS: " + rs.getString("ADDRESS"));
//			System.out.println("BRANCH CITY: " + rs.getString("CITY"));
//			System.out.println("BRANCH NAME: " + rs.getString("NAME"));
//			System.out.println("BRANCH STATE: " + rs.getString("STATE"));
//			System.out.println("BRANCH ZIP_CODE: " + rs.getString("ZIP_CODE"));
			System.out.println("Emp ID: "+ rs.getInt("EMP_ID"));
			System.out.println("Emp Firstname: "+ rs.getString("FIRST_NAME"));
			System.out.println("Emp Lastname: "+ rs.getString("LAST_NAME"));
			System.out.println("Emp Title: "+ rs.getString("TITLE"));
			System.out.println("Emp DEPT ID: "+ rs.getString("DEPT_ID"));
		}
		// Dong ket noi
		conn.close();
		System.out.println("------Close connection-----------");
	}
	
}
