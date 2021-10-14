package smarthome01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/smarthome2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "421132";
	

	public static void connectDB() throws ClassNotFoundException, SQLException{
		try{
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt = conn.createStatement();
			String sql = "SELECT memberName FROM member;";
			
			String sql3= " INSERT INTO data(dataID, itemID, time,temperature,label) VALUES ('D00028', 'E00002-01', now(),25.8,0)";


			
			stmt.executeUpdate(sql3);
//			ResultSet rs = stmt.executeQuery(sql);
//			while(rs.next()){
//                String memberName = rs.getString("memberName");
//                System.out.println("memberName: " + memberName);
//            }
//            rs.close();
            stmt.close();
            conn.close();
		}
		catch(SQLException se){
			System.out.println("SQLExpection:" + se);
		}
		catch(ClassNotFoundException e) {
			System.out.println("ClassNotFoundException:" + e);
		}
	}
	
	public static void main(String[] args) {
		try {
			JDBCDemo.connectDB();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

