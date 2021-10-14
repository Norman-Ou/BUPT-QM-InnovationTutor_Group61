package smarthome01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection conn() throws ClassNotFoundException,SQLException {
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
		String DB_URL = "jdbc:mysql://localhost:3306/smarthome2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		String USER = "root";
		String PASS = "421132";
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Connection established!");
		}
		catch(SQLException se){
			System.out.println("SQLExpection:" + se);
		}
		catch(ClassNotFoundException e) {
			System.out.println("ClassNotFoundException:" + e);
		}
		return conn;
	}
}

