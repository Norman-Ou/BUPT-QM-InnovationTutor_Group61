package jspservlet.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jspservlet.dao.WindowDAO;
import jspservlet.db.DBConnect;
import jspservlet.vo.Door;
import jspservlet.vo.Humidifier;
import jspservlet.vo.UserLogin;
import jspservlet.vo.Window;

public class WindowDAOImpl implements WindowDAO {

	public ArrayList<Window> allWindowid() throws Exception {
		ArrayList<Window> wininfo = new ArrayList<Window>();
		String sql = "select * from item i,equipment e,equipmenttype t where i.equipmentID=e.equipmentID and e.typeID=t.typeID and t.typeID='T00004' and homeID='"+UserLogin.homeID+"';";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try{    
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ;   
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
            	Window Awin=new Window();
            	Awin.setId(rs.getString("ItemID"));
            	Awin.setType(rs.getString("equipmentName"));
            	Awin.setImg(rs.getString("equipImage"));
                Awin.setImg(rs.getString("equipImage"));
                Awin.setName(rs.getString("name"));
                Awin.setPosition(rs.getString("position"));
                String sql2="select decibel from data where itemID='"+rs.getString("ItemID")+"' and time=(select MAX(time) from data where itemID='"+rs.getString("ItemID")+"')";
                pstmt = dbc.getConnection().prepareStatement(sql2) ;
                ResultSet rs2 = pstmt.executeQuery();
                while(rs2.next()) {
                    Awin.setDecibel(rs2.getString("decibel"));
                }
            	wininfo.add(Awin);
            }   
            rs.close() ; 
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{ 
            dbc.close() ;   
        }   
		return wininfo;
	}

    public ArrayList<String> allWindowType(){
        ArrayList<String> windowtype = new ArrayList<String>();
        String sql = "select * from equipment e,equipmenttype t where e.typeID=t.typeID and t.typeID='T00004';";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try{
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                windowtype.add(rs.getString("equipmentName"));
            }
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            dbc.close() ;
        }
        return windowtype;
    }

    @Override
    public Window queryWindow(String id) throws Exception {
        Window Awindow=new Window();
        String sql = "select * from item i,equipment e,equipmenttype t,data d where i.equipmentID=e.equipmentID and e.typeID=t.typeID and i.ItemID=d.itemID and i.ItemID='"+id+"' and homeID='"+UserLogin.homeID+"' and d.time=(select MAX(time) from data where itemID='"+id+"')";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try{
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Awindow.setId(rs.getString("ItemID"));
                Awindow.setType(rs.getString("equipmentID"));
                Awindow.setImg(rs.getString("equipImage"));
                Awindow.setDecibel(rs.getString("decibel"));
                Awindow.setName(rs.getString("name"));
                Awindow.setPosition(rs.getString("position"));
            }
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            dbc.close() ;
        }
        return Awindow;
    }

    public void addwindow(Window window) {
        String sql3 = "select equipmentID from equipment where equipmentName='"+window.getType()+"';";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        String eid = null;
        String did = null;
        try{
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql3) ;
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                eid=rs.getString("equipmentID");
            }
            String sql1 = "select RIGHT(MAX(RIGHT(ItemID,2))+101,2) as ItemID from item where LEFT(ItemID,6)='"+eid+"';";
            pstmt = dbc.getConnection().prepareStatement(sql1) ;
            rs = pstmt.executeQuery();
            while(rs.next()){
                String num=rs.getString("ItemID");
                if (num==null){
                    num="01";
                }
                String id=eid+"-"+num;
                window.setId(id);
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String time=df.format(new Date());
            String sql2 = "insert into item(ItemID,equipmentID,homeID,purchaseDate,label,name,position) VALUES('"+window.getId()+"','"+eid+"','"+UserLogin.homeID+"','"+time+"',0,'"+window.getName()+"','"+window.getPosition()+"');";
            pstmt = dbc.getConnection().prepareStatement(sql2) ;
            pstmt.execute();
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            time=df.format(new Date());
            String sql5 = "select RIGHT(MAX(RIGHT(dataID,5))+100001,5) as dataID from data";
            pstmt = dbc.getConnection().prepareStatement(sql5) ;
            rs = pstmt.executeQuery();
            while(rs.next()){
                did=rs.getString("dataID");
                if (did == null){
                    did="00001";
                }
                did="D"+did;
            }
            String sql4 = "insert into data(dataID,itemID,time,decibel,label) VALUES('"+did+"','"+window.getId()+"','"+time+"',0,0);";
            pstmt = dbc.getConnection().prepareStatement(sql4) ;
            pstmt.execute();
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            dbc.close() ;
        }
    }

    @Override
    public void deleteWindow(String id) throws Exception {
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        String sql = "delete from item where ItemID="+id+" and homeID="+UserLogin.homeID;
        try{
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            pstmt.execute();
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            dbc.close() ;
        }
    }
}
