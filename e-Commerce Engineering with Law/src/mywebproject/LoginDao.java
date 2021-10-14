package mywebproject;
 
import java.sql.*;
import javax.servlet.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class LoginDao {
 
 public boolean login(String name, String pwd) throws ClassNotFoundException, SQLException
 
 {
 
  Statement st = null;
 
  Connection conn = null;
 
  ResultSet rs = null;
 
  PreparedStatement ps = null;
 
  boolean flag = false;
 
  try {
 
   Class.forName("com.mysql.cj.jdbc.Driver");
 
   String url = "jdbc:mysql://10.22.136.188:3306/smarthome2?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
 
   String user = "root";
 
   String password = "Zhc202020";
 
   conn = DriverManager.getConnection(url, user, password);
   

   String sql = "select * from member where memberName= ? and memberPWD= ?";
 
   ps = conn.prepareStatement(sql);
 
   ps.setString(1, name);
 
   ps.setString(2, pwd);
 
   rs = ps.executeQuery();
 
   if (rs.next())
 
   {
	   
    flag = true;
 
   }
 
  } catch (SQLException e) {
 
   // TODO Auto-generated catch block
 
   e.printStackTrace();
 
  } finally
 
  {
 
   if (rs != null)
 
   {
 
    rs.close();
 
   }
 
   if (ps != null)
 
   {
 
    ps.close();
 
   }
 
   if (conn != null)
 
   {
 
    conn.close();
 
   }
 
  }
 
  return flag;
 
 }
 
}
