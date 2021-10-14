package jspservlet.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jspservlet.dao.HumidifierDAO;
import jspservlet.db.DBConnect;
import jspservlet.vo.Door;
import jspservlet.vo.Humidifier;
import jspservlet.vo.Light;
import jspservlet.vo.UserLogin;

public class HumidifierDAOImpl implements HumidifierDAO {

	public ArrayList<Humidifier> allHumidifierid() throws Exception {
		ArrayList<Humidifier> huinfo = new ArrayList<Humidifier>();
		String sql = "select * from item i,equipment e,equipmenttype t where i.equipmentID=e.equipmentID and e.typeID=t.typeID and t.typeID='T00002' and homeID='"+UserLogin.homeID+"';";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try{    
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ;   
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
            	Humidifier Ahu=new Humidifier();
            	Ahu.setId(rs.getString("ItemID"));
            	Ahu.setType(rs.getString("equipmentName"));
            	Ahu.setImg(rs.getString("equipImage"));
                Ahu.setImg(rs.getString("equipImage"));
                Ahu.setName(rs.getString("name"));
                Ahu.setPosition(rs.getString("position"));
                String sql2="select humidity,temperature from data where itemID='"+rs.getString("ItemID")+"' and time=(select MAX(time) from data where itemID='"+rs.getString("ItemID")+"')";
                pstmt = dbc.getConnection().prepareStatement(sql2) ;
                ResultSet rs2 = pstmt.executeQuery();
                while(rs2.next()) {
                    Ahu.setHumidity(rs2.getString("humidity"));
                    Ahu.setTemperature(rs2.getString("temperature"));
                }
            	huinfo.add(Ahu);
            }   
            rs.close() ; 
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{ 
            dbc.close() ;   
        }   
		return huinfo;
	}

    public ArrayList<String> allHumidifierType(){
        ArrayList<String> humidifiertype = new ArrayList<String>();
        String sql = "select * from equipment e,equipmenttype t where e.typeID=t.typeID and t.typeID='T00002';";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try{
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                humidifiertype.add(rs.getString("equipmentName"));
            }
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            dbc.close() ;
        }
        return humidifiertype;
    }

    @Override
    public Humidifier queryHumidifier(String id) throws Exception {
        Humidifier Ahumidifier=new Humidifier();
        String sql = "select * from item i,equipment e,equipmenttype t,data d where i.equipmentID=e.equipmentID and e.typeID=t.typeID and i.ItemID=d.itemID and i.ItemID='"+id+"' and homeID='"+UserLogin.homeID+"' and d.time=(select MAX(time) from data where itemID='"+id+"')";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try{
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Ahumidifier.setId(rs.getString("ItemID"));
                Ahumidifier.setType(rs.getString("equipmentID"));
                Ahumidifier.setImg(rs.getString("equipImage"));
                Ahumidifier.setTemperature(rs.getString("temperature"));
                Ahumidifier.setHumidity(rs.getString("humidity"));
                Ahumidifier.setName(rs.getString("name"));
                Ahumidifier.setPosition(rs.getString("position"));
            }
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            dbc.close() ;
        }
        return Ahumidifier;
    }

    public void addhumidifier(Humidifier humidifier) {
        String sql3 = "select equipmentID from equipment where equipmentName='"+humidifier.getType()+"';";
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
                humidifier.setId(id);
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String time=df.format(new Date());
            String sql2 = "insert into item(ItemID,equipmentID,homeID,purchaseDate,label,name,position) VALUES('"+humidifier.getId()+"','"+eid+"','"+UserLogin.homeID+"','"+time+"',0,'"+humidifier.getName()+"','"+humidifier.getPosition()+"');";
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
            String sql4 = "insert into data(dataID,itemID,time,humidity,temperature,label) VALUES('"+did+"','"+humidifier.getId()+"','"+time+"',0,0,0);";
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
    public void deleteHumidifier(String id) throws Exception {
        String sql = "delete from item where ItemID="+id+" and homeID="+UserLogin.homeID;
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
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
