package jspservlet.vo;

import jspservlet.db.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Alarm extends Thread{
    public void run(){
        while(true) {
            PreparedStatement pstmt = null;
            DBConnect dbc = new DBConnect();
            try {
                String sql = "select * from alarm where alarmTime=(select MAX(alarmTime) from alarm)";
                pstmt = dbc.getConnection().prepareStatement(sql);
                pstmt.execute();
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    if (rs.getString("label").equals("1")) {
                        UserLogin.alarm = 1;
                    }
                }
                pstmt.close();
                sleep(5000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                dbc.close();
            }
        }
    }
    public static void clean(){
        PreparedStatement pstmt = null;
        DBConnect dbc = new DBConnect();
        try {
            String sql = "insert into alarm(alarmTime,label) VALUES(now(),0)";
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.execute();
            pstmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbc.close();
        }
    }
}
