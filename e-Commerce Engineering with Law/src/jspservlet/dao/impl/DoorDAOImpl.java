package jspservlet.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jspservlet.dao.DoorDAO;
import jspservlet.db.DBConnect;
import jspservlet.vo.Door;
import jspservlet.vo.Humidifier;
import jspservlet.vo.Light;
import jspservlet.vo.UserLogin;

public class DoorDAOImpl implements DoorDAO {

	public ArrayList<Door> allDoorid() throws Exception {
		ArrayList<Door> doorinfo = new ArrayList<Door>();
		String sql = "select * from item i,equipment e,equipmenttype t where i.equipmentID=e.equipmentID and e.typeID=t.typeID and t.typeID='T00003' and homeID='"+UserLogin.homeID+"';";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try{    
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ;   
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
            	Door Adoor=new Door();
            	Adoor.setId(rs.getString("ItemID"));
            	Adoor.setType(rs.getString("equipmentName"));
            	Adoor.setImg(rs.getString("equipImage"));
                Adoor.setImg(rs.getString("equipImage"));
                Adoor.setName(rs.getString("name"));
                Adoor.setPosition(rs.getString("position"));
                String sql2="select distance from data where itemID='"+rs.getString("ItemID")+"' and time=(select MAX(time) from data where itemID='"+rs.getString("ItemID")+"')";
                pstmt = dbc.getConnection().prepareStatement(sql2) ;
                ResultSet rs2 = pstmt.executeQuery();
                while(rs2.next()) {
                    Adoor.setDistance(rs2.getString("distance"));
                }
            	doorinfo.add(Adoor);
            }   
            rs.close() ; 
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{ 
            dbc.close() ;   
        }   
		return doorinfo;
	}

    public ArrayList<String> allDoorType(){
        ArrayList<String> doortype = new ArrayList<String>();
        String sql = "select * from equipment e,equipmenttype t where e.typeID=t.typeID and t.typeID='T00003';";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try{
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                doortype.add(rs.getString("equipmentName"));
            }
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            dbc.close() ;
        }
        return doortype;
    }

    @Override
    public Door queryDoor(String id) throws Exception {
        Door Adoor=new Door();
        String sql = "select * from item i,equipment e,equipmenttype t,data d where i.equipmentID=e.equipmentID and e.typeID=t.typeID and i.ItemID=d.itemID and i.ItemID='"+id+"' and homeID='"+UserLogin.homeID+"' and d.time=(select MAX(time) from data where itemID='"+id+"')";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try{
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Adoor.setId(rs.getString("ItemID"));
                Adoor.setType(rs.getString("equipmentID"));
                Adoor.setImg(rs.getString("equipImage"));
                Adoor.setDistance(rs.getString("distance"));
                Adoor.setName(rs.getString("name"));
                Adoor.setPosition(rs.getString("position"));
            }
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            dbc.close() ;
        }
        return Adoor;
    }

    public void adddoor(Door door) {
        String sql3 = "select equipmentID from equipment where equipmentName='"+door.getType()+"';";
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
                door.setId(id);
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String time=df.format(new Date());
            String sql2 = "insert into item(ItemID,equipmentID,homeID,purchaseDate,label,name,position) VALUES('"+door.getId()+"','"+eid+"','"+UserLogin.homeID+"','"+time+"',0,'"+door.getName()+"','"+door.getPosition()+"');";
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
            String sql4 = "insert into data(dataID,itemID,time,distance,label) VALUES('"+did+"','"+door.getId()+"','"+time+"',0,0);";
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
    public void deleteDoor(String id) throws Exception {
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
