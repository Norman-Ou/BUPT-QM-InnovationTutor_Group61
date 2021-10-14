package jspservlet.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import jspservlet.dao.HomeDAO;
import jspservlet.db.DBConnect;
import jspservlet.vo.Home;
import jspservlet.vo.Member;
import jspservlet.vo.UserLogin;

public class HomeDAOImpl implements HomeDAO{

	//query home information
	public Home showHome(String  homeID) throws Exception {
		DBConnect dbc = new DBConnect();
		dbc.getConnection();
		PreparedStatement pst = null;
		Home home1 = new Home();

		String sql = "select * from Home where homeID=?";

		try {
			pst = (PreparedStatement)dbc.getConnection().prepareStatement(sql);

			pst.setString(1, homeID);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				home1.setHomeID(rs.getString(1));
				home1.setHomeName(rs.getString(2));
				home1.setHomeAddress(rs.getString(3));
			}


		}catch(SQLException e) {
			e.printStackTrace();

		}
		return home1;
	}


	//change user's home PWD
	public int Userhomechange(Home home, String newPWD) throws Exception{
		int flag=0;


		String hid= home.getHomeID();

		DBConnect dbc = new DBConnect();
		dbc.getConnection();

		String sql = "select * from home where homeID='"+hid+"'";
		PreparedStatement pst;
		pst = (PreparedStatement)dbc.getConnection().prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		try {
			while(rs.next()) {

				if(home.getHomePWD().equals(rs.getString(5))) {
					PreparedStatement pst1 = null;
					String sql1 = "update home set homePWD=? where homeID='"+hid+"'";
					pst1=(PreparedStatement)dbc.getConnection().prepareStatement(sql1);
					pst1.setString(1, newPWD);
					pst1.executeUpdate();
					flag=1;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}

	//overwrite home: homeName, homeAdress
	public int create(Home home) throws Exception {
		int flag = 0;
		DBConnect dbc = new DBConnect();
		dbc.getConnection();
		PreparedStatement pst = null;

		String sql = "update Home set homeName=?, homeAddress=? where homeID=?";

		try {
			pst = (PreparedStatement)dbc.getConnection().prepareStatement(sql);

			pst.setString(1, home.getHomeName());
			pst.setString(2, home.getHomeAddress());
			pst.setString(3, home.getHomeID());
			pst.executeUpdate();

			flag = 1;
		}catch(SQLException e){
			e.printStackTrace();
		}

		return flag;

	}

	//change to another home
	public int anohomechange(Home home) throws Exception{
		int flag=0;
		DBConnect dbc = new DBConnect();
		dbc.getConnection();

		String sql = "select * from home where homeID='"+home.getHomeID()+"'";

		PreparedStatement pst;
		pst = (PreparedStatement)dbc.getConnection().prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		try {
			while(rs.next()) {
				if(home.getHomePWD().equals(rs.getString(5))) {
					flag=2;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		String sql1 = "update member set homeID=? where memberID='"+UserLogin.memberID+"';";

		if(flag==2) {
			try {
				pst = (PreparedStatement)dbc.getConnection().prepareStatement(sql1);
				pst.setString(1, home.getHomeID());
				pst.executeUpdate();

				flag = 1;
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return flag;

	}

	public String register(String name, String adress, String password) throws Exception{
		PreparedStatement pstmt = null ;
		DBConnect dbc = null;
		String homeID=null;
		try{
			dbc = new DBConnect() ;
			String sql1 = "select MAX(homeID) as homeID from home;";
			pstmt = dbc.getConnection().prepareStatement(sql1) ;
			pstmt.execute();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){


				homeID=rs.getString("homeID");
				String h=homeID.substring(1);
				Integer hid = null;
				if(h!=null){
					hid = Integer.valueOf(h);
				}
				hid = hid+100001;
				homeID = Integer.toString(hid);
				homeID = homeID.replaceFirst("1", "H");

				String sql ="insert into home(homeID,homeName,homeAddress,label,homePWD)VALUES('"+homeID+"','"+name+"','"+adress+"','"+0+"','"+password+"');";
				pstmt = dbc.getConnection().prepareStatement(sql) ;
				pstmt.execute();

				PreparedStatement pst;
				String sql2 = "update member set homeID=? where memberID='"+UserLogin.memberID+"';";
				pst = (PreparedStatement)dbc.getConnection().prepareStatement(sql2);
				pst.setString(1, homeID);
				pst.executeUpdate();


			}
			pstmt.close() ;
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}finally{
			dbc.close() ;
		}

		return homeID;

	}


}

