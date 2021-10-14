package topicCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import smarthome01.Login;

public class CommandContent {
	public static String commandContent() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
//		final String URL = "jdbc:mysql://localhost:3306/smarthome2?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
//		final String USERNAME = "root";
//		final String PASSWORD = "421132";
//		Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		Statement state = Login.conn.createStatement();
		String sql= "select count(*) as cmdNumber from command;";
		ResultSet rs = state.executeQuery(sql);
		rs.next();
		int cmdNumber= Integer.parseInt(rs.getString("cmdNumber"));//通过count得知现在table中有几条命令
//		System.out.println("commandNumber"+cmdNumber);
		rs.close();
		state.close();
		if(cmdNumber==0) {
//			conn.close();
			return "";//如果没有命令直接结束
		}
		
		
		Statement state2 = Login.conn.createStatement();
		String sql2= "select cmd from command where cmdid = "+cmdNumber+" ;";//选择出最后一条命令
		ResultSet rs2 = state2.executeQuery(sql2);
		rs2.next();
		String cmd= rs2.getString("cmd");
//		System.out.println("cmd:"+cmd);
		rs2.close();
		state2.close();
		
		
		Statement state3 = Login.conn.createStatement();
		String sql3= "delete from command where cmdid = "+cmdNumber+" ;";//把最后一条命令删除
		int rs3 = state3.executeUpdate(sql3);
		System.out.println("sql execute");
		state3.close();

//		conn.close();
		
		return cmd; 
	}
}
