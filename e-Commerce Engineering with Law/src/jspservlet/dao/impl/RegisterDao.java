package jspservlet.dao.impl;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;

import jspservlet.db.DBConnect;

import java.security.*;

public class RegisterDao {
 ArrayList<String> array = new ArrayList<String>();

 public int register(String rname, String rpwd, String rpwd1,String gender,String phone) throws SQLException
 {
  ResultSet rs = null;
  Connection conn = null;
  Statement st = null;
  Statement st1 = null;
  int count = 0;
  String encrypt = new String();
  try {
   Class.forName("com.mysql.cj.jdbc.Driver");
   String url = "jdbc:mysql://localhost:3306/smarthome2?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
   String user = "root";
   String password = "Zhc202020";
   conn = DriverManager.getConnection(url, user, password);
   String sql1 = "select memberName from member";
   String sql2 = "select RIGHT(MAX(RIGHT(memberID,5))+100001,5) as memberID from member";
   st = conn.createStatement();
   st1 = conn.createStatement();
   rs = st1.executeQuery(sql1);
   while (rs.next())
   {
    String sname = rs.getString("memberName");
    array.add(sname);
   }if (rname.length() >= 1 && rpwd.length() >= 1 && rpwd.equals(rpwd1)) {

    encrypt = stringToMD5(rpwd);
    rs = st1.executeQuery(sql2);
    while(rs.next()){
     String memberID = "M"+ rs.getString("memberID");
     String sql = "insert into member(memberID,memberName,memberPWD,memberGender,memberPhone,homeID,label) values('" + memberID + "','" + rname + "','" + encrypt + "','" + gender +"','" + phone + "','" + "H00001" + "'," + "0" +")";
     count = st.executeUpdate(sql);
    }
   }
  }catch (ClassNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } finally
  {
   if (rs != null)
   {
    rs.close();
   }
   if (st1 != null)
   {
    st.close();
   }
   if (st != null)
   {
    st.close();
   }
   if (conn != null)
   {
    conn.close();
   }
  }
  return count;
 }

 //encryption
 public String stringToMD5(String inStr) {
  MessageDigest md5 = null;
  try{
   md5 = MessageDigest.getInstance("MD5");
  }catch (Exception e){
   System.out.println(e.toString());
   e.printStackTrace();
   return "";
  }
  char[] charArray = inStr.toCharArray();
  byte[] byteArray = new byte[charArray.length];

  for (int i = 0; i < charArray.length; i++)
   byteArray[i] = (byte) charArray[i];
  byte[] md5Bytes = md5.digest(byteArray);
  StringBuffer hexValue = new StringBuffer();
  for (int i = 0; i < md5Bytes.length; i++){
   int val = ((int) md5Bytes[i]) & 0xff;
   if (val < 16)
    hexValue.append("0");
   hexValue.append(Integer.toHexString(val));
  }
  return hexValue.toString();
 }

}