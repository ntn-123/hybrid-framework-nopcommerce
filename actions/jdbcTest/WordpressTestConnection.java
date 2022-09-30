package jdbcTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.xdevapi.PreparableStatement;

public class WordpressTestConnection {
	public static Connection getMyConnection() throws SQLException, ClassNotFoundException{
		return WordpressConnUtils.getWordpressConnUtils();
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		System.out.println("Get connection...");
		
		// Lay ra doi tuong ket noi vao database
		Connection conn = WordpressTestConnection.getMyConnection();
		
		System.out.println("Opened connection: " + conn);
		
		Statement statement = conn.createStatement();
		
		String sql = "SELECT * FROM `wp_users`;";
		
		// Thuc thi doi tuong sql va tra ve doi tuong ResultSet
		ResultSet rs = statement.executeQuery(sql);
		
		// Duyet tren ket qua tra ve
		while(rs.next()) {
			// Di chuyen con tro xuong ban ghi ke tiep
			System.out.println("----------------");
			System.out.println("User ID: "+ rs.getInt("ID"));
			System.out.println("User login: " + rs.getString("user_login"));
			System.out.println("User email: " + rs.getString("user_email"));
		}
		// Dong ket noi
		conn.close();
		System.out.println("------Close connection-----------");
	}
	
}
